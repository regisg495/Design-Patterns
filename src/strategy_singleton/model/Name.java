package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class Name implements FieldStrategy<String> {

	@Override
	public boolean isValid(String value) {
		return (value.matches("[A-Z][a-z]+( [A-Z][a-z]+)+"));
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

}
