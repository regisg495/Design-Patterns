package adapter_decorator.base;

public class Date implements DateInterface {
	private int day, month, year;

	public Date() {
		this.day = 1;
		this.month = 1;
		this.year = 1900;
	}

	public int getDay() {
		return (this.day);
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return (this.month);
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return (this.year);
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String show() {
		return ("DATE:" + String.valueOf(this.day) + "/" + String.valueOf(this.month) + "/"
				+ String.valueOf(this.year));
	}

}
