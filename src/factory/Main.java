package factory;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		try {
			Class.forName(Date.class.getName());
			Class.forName(Time.class.getName());
			Class.forName(Timestamp.class.getName());
		} catch (ClassNotFoundException e) {
		}

		System.out.println("data, hora, datahora: ");
		DateTime datahora = Factory.getInstance().create(new Scanner(System.in).nextLine()); // TD SERÁ GERADO ATRAVEZ DO FACTORY
		System.out.println(datahora.show());

	}

}
