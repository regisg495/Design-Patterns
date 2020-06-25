package templatemethod.model;

import templatemethod.services.Field;

public class OperationalSystem extends Field<String> {

	public OperationalSystem(String inputtype) {
		super(inputtype);
	
	}

	@Override
	public boolean isValid(String value) {
		return value.matches("[M]+ac|[W]+indows|[A]ndroid|[L]+inux]"); 
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
	

}
