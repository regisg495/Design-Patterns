package templatemethod.model;

import templatemethod.services.Field;

public class Name extends Field<String> {

	public Name(String inputtype) {
		super(inputtype);

	}

	@Override
	public boolean isValid(String value) {
		return (value.matches("[A-Z][a-z]+( [A-Z][a-z]+)+"));
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
