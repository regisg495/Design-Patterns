package adapter_decorator.factory;

import java.util.ArrayList;
import java.util.List;

public class Factory {
	private static final Factory instance = new Factory();
	private static List<DateTime> list = new ArrayList<DateTime>();

	private Factory() {
	}

	public static Factory getInstance() {
		return (instance);
	}

	public void register(DateTime datetime) {
		list.add(datetime);
	}

	public DateTime create(String value) throws Exception {
		for (DateTime datetime : list) {
			try {
				return (datetime.create(value));
			} catch (Exception e) {
			}
		}
		throw new Exception("nao e nenhum dos datetime!");
	}

}
