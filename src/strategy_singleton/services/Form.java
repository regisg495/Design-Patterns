package strategy_singleton.services;

import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import strategy_singleton.exception.InvalidValueException;
import strategy_singleton.exception.NullFieldException;

public class Form {
	private List<Field> form;
	private Log log = Log.getInstance();
	
	public void add(Field f) {
		try {
			if (f.getStrategy() instanceof NullField)
				throw new NullFieldException();
		} catch (NullFieldException e) {
				System.out.println("The field is blank, this will have no effect");
		}
		Field k = new Field(f.getStrategy());
		this.form.add(k);
	}

	public Form() {
		this.form = new ArrayList();
	}

	private String addTime() {
		DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		String s = timeStampPattern.format(java.time.LocalDateTime.now());
		return s;

	}

	private String getType(Field f) {
		String tipo = "You need to declare this field";
		Method array[] = f.getStrategy().getClass().getMethods();
		for (Method method : array) {
			if (method.getName().toString().equals("convertFromString")) {
				tipo = method.getReturnType().getSimpleName().equals("Object") ? "String"
						: method.getReturnType().getSimpleName();
			}
		}

		return tipo;

	}

	private void removeNullField() {
		List<Field> NovoForm = new ArrayList<Field>();
		for (Field field : form) {
			if (!(field.getStrategy() instanceof NullField)) {
				Field k = new Field(field.getStrategy());
				NovoForm.add(k);
			}
		}
		this.form = NovoForm;
	}

	public void fill(Scanner scanner) {
		if (form.size() == 0) {
			System.out.println("You have not registered any fields");
			return;
		}
		removeNullField();
		beginLog();

		for (Field f : form) {

			int contador = 0;
			System.out.println("Enter your " + f.getStrategy().getClass().getSimpleName() + ":");
			String s = scanner.nextLine();
			boolean b = true;
			String tipo = getType(f);
			while (b) {
				try {
					f.setValue(s);
					b = false;
				} catch (InvalidValueException e) {
					contador++;
					log.getPrintwriter().println(invalidValuePrint(f, s));
					if (contador == 3) {
						log.getPrintwriter().println(threeTimesInvalidPrint(f, s));
					}
					System.out.println("Re-enter " + f.getStrategy().getClass().getSimpleName() + ":");
					s = scanner.nextLine();
					b = true;
				}

			}
			log.getPrintwriter().println(acceptedValuePrint(f, s));

		}
		endLog();

		log.getPrintwriter().close();

	}

	private void beginLog() {
		log.getPrintwriter().println("[".concat(addTime()).concat("]").concat(" form ")
				.concat(getClass().getSimpleName()).concat(" filling started"));
	}

	private void endLog() {
		log.getPrintwriter().println("[".concat(addTime()).concat("] ").concat("form ")
				.concat(this.getClass().getSimpleName()).concat(" filling done").concat(System.lineSeparator()));
	}

	private String invalidValuePrint(Field f, String value) {
		String tipo = getType(f);
		StringBuffer sb = new StringBuffer();
		String s = "";
		sb.append(("[".concat(addTime()).concat("] ").concat("Invalid Value ").concat("\"").concat(value).concat("\"")
				.concat(" for ").concat(tipo).concat(" field ").concat(f.getStrategy().getClass().getSimpleName())
				.concat(" on form ").concat(this.getClass().getSimpleName())));
		s = sb.toString();
		return s;

	}

	private String threeTimesInvalidPrint(Field f, String value) {
		String tipo = getType(f);
		StringBuffer sb = new StringBuffer();
		String s = "";
		sb.append(("[".concat(addTime()).concat("] ").concat("3 Invalid attemps for field ")
				.concat(f.getStrategy().getClass().getSimpleName()).concat(" on form ")
				.concat(this.getClass().getSimpleName())));
		s = sb.toString();
		return s;
	}

	private String acceptedValuePrint(Field f, String value) {
		String tipo = getType(f);
		StringBuffer sb = new StringBuffer();
		String s = "";
		sb.append(("[".concat(addTime()).concat("]").concat(" Accepted value ").concat("\"").concat(value).concat("\"")
				.concat(" for ").concat(tipo).concat(" field ")
				.concat(f.getStrategy().getClass().getSimpleName().concat(" on form "))
				.concat(this.getClass().getSimpleName())));
		s = sb.toString();
		return s;

	}

	public Field get(String name) {
		for (Field field : form) {
			if (field.getStrategy().getClass().getSimpleName().toString().equals(name))
				return field;
		}

		throw new IllegalArgumentException("Not found");

	}

	public Field get(int index) {
		try {
			return this.form.get(index);
		} catch (NullPointerException e) {
			System.out.println("Not found");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Value greater or less than the number of fields");
		} catch (Exception e) {
			System.out.println("There was an error, we're working on it!");
		}
		return null;

	}

	public String toString() {
		try {
			StringBuffer s = new StringBuffer();
			for (Field field : form) {
				s.append(field.getStrategy().getClass().getSimpleName().concat(": ").concat(field.getValue().toString())
						.concat(System.lineSeparator()));
			}
			String k = s.toString();
			return k;

		} catch (RuntimeException e) {
			return "No fields were filled in";
		} catch (Exception e) {
			return "An error has occurred";
		}

	}
}
