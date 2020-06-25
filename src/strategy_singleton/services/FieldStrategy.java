package strategy_singleton.services;

public interface FieldStrategy <T> {
	
	public boolean isValid(String value);

	public T convertFromString(String value);

}
