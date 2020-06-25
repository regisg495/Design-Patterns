package adapter_decorator.base;

public interface TimestampInterface {
	public DateInterface getDate();

	public void setDate(DateInterface date);

	public TimeInterface getTime();

	public void setTime(TimeInterface time);

	public String show();

}
