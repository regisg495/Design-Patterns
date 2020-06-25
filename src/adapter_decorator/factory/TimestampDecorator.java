package adapter_decorator.factory;

import adapter_decorator.base.DateInterface;
import adapter_decorator.base.TimeInterface;
import adapter_decorator.base.Timestamp;
import adapter_decorator.base.TimestampInterface;

public final class TimestampDecorator implements DateTime, TimestampInterface {

	static {
		Factory.getInstance().register(new TimestampDecorator());
	}

	private Timestamp timestamp;

	public TimestampDecorator() {
		this.timestamp = new Timestamp();
	}

	public DateInterface getDate() {
		return (this.timestamp.getDate());
	}

	public void setDate(DateInterface date) {
		this.timestamp.setDate(date);
	}

	public TimeInterface getTime() {
		return (this.timestamp.getTime());
	}

	public void setTime(TimeInterface time) {
		this.timestamp.setTime(time);
	}

	public String show() {
		return (this.timestamp.show());
	}

	public DateTime create(String value) throws Exception {
		try {
			TimestampDecorator result = new TimestampDecorator();
			String array[] = value.split(" ");
			result.timestamp.setDate((DateDecorator) new DateDecorator().create(array[0]));
			result.timestamp.setTime((TimeDecorator) new TimeDecorator().create(array[1]));
			return (result);
		} catch (Exception e) {
			throw new Exception("nao e timestamp!");
		}
	}

}
