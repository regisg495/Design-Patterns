package activerecord_pool.exception;

public class ConstraintViolationException extends IllegalArgumentException {
	public ConstraintViolationException(String msg) {
		super(msg);
	}

}
