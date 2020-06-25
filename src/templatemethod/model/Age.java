package templatemethod.model;

import templatemethod.services.Field;

public class Age extends Field<Integer> {

	public Age(String inputtype) {
		super(inputtype);
	}

	@Override
	public boolean isValid(String value) {
		 return(value.matches("[0-9]+"));
	}

	@Override
	public Integer convertFromString(String value) {
		return Integer.parseInt(value);
	}

	@Override
	public String toString() {
		return Integer.toString(this.getValue());
	}

}
