package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class Age implements FieldStrategy<Integer> {

	@Override
	public boolean isValid(String value) {
		return (value.matches("[0-9]+"));
	}

	@Override
	public Integer convertFromString(String value) {
		return Integer.parseInt(value);
	}

}
