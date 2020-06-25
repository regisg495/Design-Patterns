package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class Email implements FieldStrategy<String>{

	@Override
	public boolean isValid(String value) {
		return (value.matches("[a-z]+@[a-z]+\\.(com|edu|mil|gov|org)(\\.[a-z]{2})?"));
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

}
