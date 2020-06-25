package templatemethod.model;

import templatemethod.services.Field;

public class Telephone extends Field<String> {

	public Telephone(String inputtype) {
		super(inputtype);
	}

	@Override
	public boolean isValid(String value) {
		return value.matches("[0]{2}-(21)-(55)-[0-5]{2}-[0-9]{8}");
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
