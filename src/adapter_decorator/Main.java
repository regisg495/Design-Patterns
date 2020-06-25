package adapter_decorator;

import java.util.Scanner;

import adapter_decorator.factory.DateDecorator;
import adapter_decorator.factory.DateTime;
import adapter_decorator.factory.Factory;
import adapter_decorator.factory.TimeDecorator;
import adapter_decorator.factory.TimestampDecorator;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			Class.forName(DateDecorator.class.getName());
			Class.forName(TimeDecorator.class.getName());
			Class.forName(TimestampDecorator.class.getName());
		} catch (ClassNotFoundException e) {
		}

		System.out.print("data, hora, timestamp: ");
		DateTime datetime = Factory.getInstance().create(new Scanner(System.in).nextLine());
		System.out.println(datetime.show());
	}

}
