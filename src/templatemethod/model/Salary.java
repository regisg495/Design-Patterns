package templatemethod.model;

import templatemethod.services.Field;

public class Salary extends Field<Double> {

	public Salary(String inputtype) {
		super(inputtype);
		
	}

	@Override
	public boolean isValid(String value) {
		return(value.matches("[0-9]+\\.[0-9]{0,2}"));
	}

	@Override
	public Double convertFromString(String value) {
		return Double.parseDouble(value);
	}

	@Override
	public String toString() {
		return Double.toString(this.getValue());
	}
	


}
