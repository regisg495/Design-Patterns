package adapter_decorator.base;

public interface TimeInterface {
	public int getHours();

	public void setHours(int hours);

	public int getMinutes();

	public void setMinutes(int minutes);

	public int getSeconds();

	public void setSeconds(int seconds);

	public String show();
}
