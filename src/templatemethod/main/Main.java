package templatemethod.main;

import java.time.LocalDate;
import java.util.Scanner;

import templatemethod.services.Field;
import templatemethod.services.Form;

public class Main {

	public static void main(String[] args) {
		Form form = new Form();
		form.add(new Field<String>("Name") {
			public boolean isValid(String value) {
				return (value.matches("[A-Z][a-z]+( [A-Z][a-z]+)+"));
			}

			public String convertFromString(String value) {
				return (value);
			}
		});
		form.add(new Field<String>("Gender") {
			public boolean isValid(String value) {
				return (value.matches("[MF]"));
			}

			public String convertFromString(String value) {
				return (value);
			}
		});
		form.add(new Field<Integer>("Age") {
			public boolean isValid(String value) {
				return (value.matches("[0-9]+"));
			}

			public Integer convertFromString(String value) {
				return (Integer.parseInt(value));
			}
		});
		form.add(new Field<Double>("Payment") {
			public boolean isValid(String value) {
				return (value.matches("[0-9]+\\.[0-9]{0,2}"));
			}

			public Double convertFromString(String value) {
				return (Double.parseDouble(value));
			}
		});
		form.add(new Field<LocalDate>("Birthdate") {
			public boolean isValid(String value) { // return(value.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
				try {
					return (LocalDate.parse(value) != null);
				} catch (Exception e) {
					return (false);
				}
			}

			public LocalDate convertFromString(String value) {
				return (LocalDate.parse(value));
			}
		});
		form.add(new Field<String>("Email") {
			public boolean isValid(String value) {
				return (value.matches("[a-z]+@[a-z]+\\.(com|edu|mil|gov|org)(\\.[a-z]{2})?"));
			}

			public String convertFromString(String value) {
				return (value);
			}
		});
		form.add(new Field<String>("Profession") {
			public boolean isValid(String value) {
				return (value.matches("[A-Z][a-z]+|( [A-Z][a-z]+)+"));
			}

			public String convertFromString(String value) {
				return (value);
			}
		});
		form.add(new Field<String>("Telephone") {
			public boolean isValid(String value) {
				return (value.matches("[0]{2}-(21)-(55)-[0-5]{2}-[0-9]{8}"));
			}

			public String convertFromString(String value) {
				return (value);
			}
		});
		form.add(new Field<String>("Operational System") {
			public boolean isValid(String value) {
				return (value.matches("[M]+ac|[W]+indows|[A]ndroid|[L]+inux]"));
			}

			public String convertFromString(String value) {
				return (value);
			}
		});
		form.fill(new Scanner(System.in));
		System.out.println("Hello " + (("M".equals(form.get(1).getValue())) ? "Sr" : "Ms") + ". "
				+ form.get("Name").getValue() + "!");
		System.out.println("You born in " + form.get(4).getValue().toString() + " and are "
				+ form.get(2).getValue().toString() + " years old.");
		System.out.println("With a 10% up, your payment will be $"
				+ String.format("%.02f", (Double) form.get("Payment").getValue() * 1.1) + ".");
		System.out.println("Your Profession is: " + form.get(6).getValue() + " and your " + "Telephone " + " is: "
				+ form.get(7).getValue());
		System.out.println("You're using " + form.get(8).getValue() + " " + form.get(8).getInputtype());

	}
}
