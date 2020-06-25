package strategy_singleton.main;

import java.util.Scanner;

import strategy_singleton.model.Age;
import strategy_singleton.model.BirthDate;
import strategy_singleton.model.CNPJ;
import strategy_singleton.model.CPF;
import strategy_singleton.model.Email;
import strategy_singleton.model.Gender;
import strategy_singleton.model.Name;
import strategy_singleton.model.OperationalSystem;
import strategy_singleton.model.Profession;
import strategy_singleton.model.Salary;
import strategy_singleton.model.Telephone;
import strategy_singleton.services.Field;
import strategy_singleton.services.Form;


public class Main {

	public static void main(String[] args) {

		Form form = new Form();
		Field f = new Field(new Name());

		form.add(f);
		f.setStrategy(new Gender());
		form.add(f);
		f.setStrategy();
		form.add(f);
		f.setStrategy(new Age());
		form.add(f);
		f.setStrategy(new Salary());
		form.add(f);
		Field k = new Field();
		form.add(k);
		f.setStrategy(new BirthDate());
		form.add(f);
		f.setStrategy(new Email());
		form.add(f);
		f.setStrategy();
		form.add(f);
		f.setStrategy(new Profession());
		form.add(f);
		f.setStrategy(new Telephone());
		form.add(f);
		f.setStrategy(new OperationalSystem());
		form.add(f);
		f.setStrategy(new CPF());
		form.add(f);
		f.setStrategy(new CNPJ());
		form.add(f);

		form.fill(new Scanner(System.in));
		System.out.println("Hello " + ("M".equals(form.get(1).getValue()) ? "Sr" : "Ms") + ". "
				+ form.get("Name").getValue() + "!");
		System.out.println("You born in " + form.get(4).getValue().toString() + " and are "
				+ form.get(2).getValue().toString() + " years old.");
		System.out.println("With a 10% up, your payment will be $"
				+ String.format("%.02f", (Double) form.get("Salary").getValue() * 1.1) + ".");
		System.out.println("Your Profession is " + form.get(6).getValue() + " and your " + "Telephone is: "
				+ form.get(7).getValue());
		System.out.println(
				"Youre using " + ("Windows".equals(form.get(8).getValue()) ? "the perfect Windows Operational System!"
						: form.get(8).getValue() + " Operational System"));
		System.out.println("Your identification in the Brazil country is: " + form.get(9).getValue() + "."
				+ System.lineSeparator() + "Your CNPJ is: " + form.get(10).getValue() + ".");

		// System.out.println(form.toString());

	}

}
