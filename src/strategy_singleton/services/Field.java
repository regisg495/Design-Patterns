package strategy_singleton.services;

import strategy_singleton.exception.InvalidValueException;

public class Field<T> {
	private T value;
	private FieldStrategy strategy = NullField.getInstance();

	public Field(FieldStrategy strategy) {
		this.strategy = strategy;

	}

	public Field() {
		this.strategy = NullField.getInstance();
	}

	public void setStrategy(FieldStrategy strategy) throws NullPointerException {
		if (strategy != null)
			this.strategy = strategy;
		else
			throw new NullPointerException("You need to declare this field");
	}

	public void setStrategy() {
		this.strategy = NullField.getInstance();
	}

	public T getValue() {
		return value;
	}

	public FieldStrategy getStrategy() {
		return strategy;
	}

	public void setValue(String value) throws InvalidValueException {
		if (!isValid(value))
			throw new InvalidValueException();
		this.value = (T) this.strategy.convertFromString(value);
	}

	private boolean isValid(String value) {
		return this.strategy.isValid(value);
	}

}
