package adapter_decorator.base;

public class Timestamp implements TimestampInterface {
	private DateInterface date;
	private TimeInterface time;

	public Timestamp() {
		this.date = new Date();
		this.time = new Time();
	}

	public Date getDate() {
		return (Date) (this.date);
	}

	public void setDate(DateInterface date) {
		this.date = date;
	}

	public Time getTime() {
		return (Time) (this.time);
	}

	public void setTime(TimeInterface time) {
		this.time = time;
	}

	public String show() {
		return ("TIMESTAMP:" + String.valueOf(this.date.getDay()) + "/" + String.valueOf(this.date.getMonth()) + "/"
				+ String.valueOf(this.date.getYear()) + " " + String.valueOf(this.time.getHours()) + ":"
				+ String.valueOf(this.time.getMinutes()) + ":" + String.valueOf(this.time.getSeconds()));
	}

}
