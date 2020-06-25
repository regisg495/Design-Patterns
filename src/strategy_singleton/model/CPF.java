package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class CPF implements FieldStrategy<String>{

	@Override
	public boolean isValid(String value) {
		return value.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}
	

}
