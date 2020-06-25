package adapter_decorator.factory;

import adapter_decorator.base.Time;
import adapter_decorator.base.TimeInterface;

public final class TimeDecorator implements DateTime, TimeInterface {

	static {
		Factory.getInstance().register(new TimeDecorator());
	}

	private Time time;

	public TimeDecorator() {
		this.time = new Time();
	}

	public int getHours() {
		return (this.time.getHours());
	}

	public void setHours(int hours) {
		this.time.setHours(hours);
		;
	}

	public int getMinutes() {
		return (this.time.getMinutes());
	}

	public void setMinutes(int minutes) {
		this.time.setMinutes(minutes);
		;
	}

	public int getSeconds() {
		return (this.time.getSeconds());
	}

	public void setSeconds(int seconds) {
		this.time.setSeconds(seconds);
	}

	public String show() {
		return (this.time.show());
	}

	public DateTime create(String value) throws Exception {
		TimeDecorator result = new TimeDecorator();
		if (value.matches("[0-9]{2}:[0-9]{2}(:[0-9]{2})?")) {
			String array[] = value.split(":");
			result.setHours(Integer.parseInt(array[0]));
			result.setMinutes(Integer.parseInt(array[1]));
			result.setSeconds((array.length == 3) ? Integer.parseInt(array[2]) : 0);
			return (result);
		}
		throw new Exception("nao e hora!");
	}

}
