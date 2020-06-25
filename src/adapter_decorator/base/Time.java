package adapter_decorator.base;

public class Time implements TimeInterface {
	private int hours, minutes, seconds;

	public Time() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}

	public int getHours() {
		return (this.hours);
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return (this.minutes);
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return (this.seconds);
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String show() {
		return ("TIME:" + String.valueOf(this.hours) + ":" + String.valueOf(this.minutes) + ":"
				+ String.valueOf(this.seconds));
	}

}
