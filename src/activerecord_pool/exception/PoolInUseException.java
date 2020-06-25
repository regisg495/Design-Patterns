package activerecord_pool.exception;

public class PoolInUseException extends IllegalArgumentException {
	public PoolInUseException(String msg) {
		super(msg);
	}

}
