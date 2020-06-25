package templatemethod.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import templatemethod.services.Field;

public class BirthDate extends Field<LocalDate> {

	public BirthDate(String inputtype) {
		super(inputtype);
	}

	@Override
	public boolean isValid(String value) {
		return (value.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"));

	}

	@Override
	public LocalDate convertFromString(String value) {
		return (LocalDate.parse(value));
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("xx-xx-xxxx");
		return this.getValue().format(formatter);
	}
	

}
