package factory;

public interface DateTime {

	public DateTime create(String valor) throws Exception;

	public boolean matches(String valor);

	public String show();
}
