package activerecord_pool.exception;

public class InvalidColumnException extends IllegalArgumentException {
	public InvalidColumnException(String msg) {
		super(msg);
	}

}
