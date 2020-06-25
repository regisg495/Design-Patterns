package templatemethod.model;

import templatemethod.services.Field;

public class Email extends Field<String> {

	public Email(String inputtype) {
		super(inputtype);

	}

	@Override
	public boolean isValid(String value) {
		return (value.matches("[a-z]+@[a-z]+\\.(com|edu|mil|gov|org)(\\.[a-z]{2})?"));
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
