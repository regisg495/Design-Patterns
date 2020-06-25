package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class Telephone implements FieldStrategy<String> {

	@Override
	public boolean isValid(String value) {
		return value.matches("(55)\\-?[0-5]{2}\\-?[0-9]{8}");
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

}
