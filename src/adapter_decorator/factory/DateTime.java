package adapter_decorator.factory;

public interface DateTime {

	public DateTime create(String value) throws Exception;

	public String show();

}
