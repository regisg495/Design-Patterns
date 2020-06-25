package activerecord_pool.services;

public class ConnectionInUseException extends RuntimeException {
	
	public ConnectionInUseException(String msg) {
		super(msg);
	}

}
