package strategy_singleton.model;

import java.time.LocalDate;

import strategy_singleton.services.FieldStrategy;

public class BirthDate implements FieldStrategy<LocalDate> {

	@Override
	public boolean isValid(String value) {
		return (value.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
	}

	@Override
	public LocalDate convertFromString(String value) {
		return (LocalDate.parse(value));
	}

}
