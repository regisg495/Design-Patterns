package factory;

import java.util.ArrayList;
import java.util.List;

public class Factory {
	private static final Factory instance = new Factory();
	private static List<DateTime> lista = new ArrayList<DateTime>();

	private Factory() {
	}

	public static Factory getInstance() {
		return instance;
	}

	public void register(DateTime datetime) {
		lista.add(datetime);
	}

	public DateTime create(String valor) throws Exception {
		for (DateTime datetime : lista) {
			try {
				return datetime.create(valor);
			} catch (Exception e) {
			}
		}
		throw new Exception("nao e nenhum dos datetime!");
	}

}
