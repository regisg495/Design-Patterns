package templatemethod.model;

import templatemethod.services.Field;

public class Gender extends Field<String> {

	public Gender(String inputtype) {
		super(inputtype);
	}

	@Override
	public boolean isValid(String value) {
		return (value.matches("[MF]"));
	}

	@Override
	public String convertFromString(String value) {
		return value;
	}

	@Override
	public String toString() {
		return (this.getValue().equals("M") ? "Sr" : "Ms");
	}

}
