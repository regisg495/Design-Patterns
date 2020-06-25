package strategy_singleton.exception;

public class InvalidValueException extends IllegalArgumentException {

	@Override
	public String toString() {
		return "You entered a invalid value";
	}

}
