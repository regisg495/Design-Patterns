package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class Salary implements FieldStrategy<Double> {

	@Override
	public boolean isValid(String value) {
		return (value.matches("[0-9]+\\.[0-9]{0,2}"));
	}

	@Override
	public Double convertFromString(String value) {
		return Double.parseDouble(value);

	}

}
