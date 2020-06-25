package strategy_singleton.model;

import strategy_singleton.services.FieldStrategy;

public class OperationalSystem implements FieldStrategy<String> {

	@Override
	public boolean isValid(String value) {
		return value.matches("[M]+ac|[W]+indows|[A]ndroid|[L]+inux]"); 
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

}
