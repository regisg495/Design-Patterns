package templatemethod.services;

import java.util.ArrayList;
import java.util.Scanner;

import templatemethod.exception.ValueDoenstExist;

public class Form {
	private ArrayList<Field> form = new ArrayList<>();

	public ArrayList<Field> getForm() {
		return form;
	}

	public void setForm(ArrayList<Field> form) {
		form = form;
	}

	public void add(Field field) {
		this.getForm().add(field);

	}

	public Form() {
		this.setForm(new ArrayList<Field>());

	}

	public boolean nextable(Field f, String s) {
		boolean b = false;
		Scanner sc = new Scanner(System.in);
		do {

			try {
				f.setValue(s);
				b = true;
			} catch (ValueDoenstExist e) {
				if (f.getInputtype().equals("Operational System")) {
					System.out.println("Chose your Operational System: Mac, Windows, Android or Linux:");
					System.out.println("Re-enter your " + f.getInputtype() + ":");
					s = sc.nextLine();
					b = false;
				}
				System.out.println("Re-enter your " + f.getInputtype() + ":");
				s = sc.nextLine();
				b = false;
			}
		} while (!b);
		return b;
	}

	public void fill(Scanner scanner) {
		String key;
		for (Field field : form) {
			System.out.println(field.getInputtype() + ":");
			key = scanner.nextLine();
			if (!nextable(field, key)) {
				do {

				} while (nextable(field, key));
			}

		}

	}

	public Field get(String string) {
		for (Field field : form) {
			if (field.getInputtype().equals(string))
				return field;
		}
		throw new IllegalArgumentException("N�o encontrado");
	}

	public Field get(int i) {
		if (i < 0 || this.getForm().size() <= i)
			throw new IllegalArgumentException("N�o existe");
		return this.getForm().get(i);
	}

	public String getValues() {
		if(this.getForm().size() == 0) {
			throw new RuntimeException("N�o foi prenchido nenhum campo!");
		}
		StringBuffer s = new StringBuffer();
		for (Field field : form) {
			s.append(field.getInputtype().concat(": ").concat(field.getValue().toString())
					.concat(System.lineSeparator()));
		}
		String k = s.toString();
		return k;

	}
}
