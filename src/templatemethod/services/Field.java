package templatemethod.services;

import templatemethod.exception.ValueDoenstExist;

public abstract class Field<T> {

	protected T value;
	private String inputtype;

	public String getInputtype() {
		return inputtype;
	}

	public void setInputtype(String inputtype) {
		this.inputtype = inputtype;
	}

	public Field(String inputtype) {
		this.setInputtype(inputtype);
	}

	public T getValue() {
		return value;
	}

	public void setValue(String value) throws ValueDoenstExist {
		if (!isValid(value))
			throw new ValueDoenstExist();
		this.value = convertFromString(value);
	}

	abstract protected boolean isValid(String value);

	abstract protected T convertFromString(String value);
}
