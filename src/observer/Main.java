package observer;

public class Main {

	public static void main(String[] args) throws Exception {
		ModuloFinanceiro mf = new ModuloFinanceiro();
		LogObserver log = new LogObserver();
		mf.attach(log);

		String login = "borges";
		mf.incluir(login);

		mf.dettach(log);
	}

}
