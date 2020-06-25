package templatemethod.exception;

public class ValueDoenstExist extends IllegalArgumentException {

	@Override
	public String toString() {
		return "Valor invalido";
	}

}
