package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class Gender implements FieldStrategy<String> {

	@Override
	public boolean isValid(String value) {
		return (value.matches("[MF]"));
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

}
