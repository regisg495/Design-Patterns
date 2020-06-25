package strategy_singleton.services;

public class NullField implements FieldStrategy<Object> {
	private static NullField INSTANCE = null;

	private NullField() {

	}

	public static NullField getInstance() {
		if (INSTANCE == null) {
			synchronized (NullField.class) {
				if (INSTANCE == null) {
					INSTANCE = new NullField();
				}
			}
			INSTANCE = new NullField();
		}
		return INSTANCE;

	}

	@Override
	public boolean isValid(String value) {
		return false;
	}

	@Override
	public Object convertFromString(String value) {
		return new Object();
	}

}
