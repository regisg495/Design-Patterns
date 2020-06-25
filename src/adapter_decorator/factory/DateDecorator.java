package adapter_decorator.factory;

import adapter_decorator.base.Date;
import adapter_decorator.base.DateInterface;

public final class DateDecorator implements DateTime, DateInterface {

	static {
		Factory.getInstance().register(new DateDecorator());
	}

	private Date date;

	public DateDecorator() {
		this.date = new Date();
	}

	public int getDay() {
		return (this.date.getDay());
	}

	public void setDay(int day) {
		this.date.setDay(day);
	}

	public int getMonth() {
		return (this.date.getMonth());
	}

	public void setMonth(int month) {
		this.date.setMonth(month);
	}

	public int getYear() {
		return (this.date.getYear());
	}

	public void setYear(int year) {
		this.date.setYear(year);
	}

	public String show() {
		return (this.date.show());
	}

	public DateTime create(String value) throws Exception {
		DateDecorator result = new DateDecorator();
		if (value.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			String array[] = value.split("-");
			result.setYear(Integer.parseInt(array[0]));
			result.setMonth(Integer.parseInt(array[1]));
			result.setDay(Integer.parseInt(array[2]));
			return (result);
		}
		if (value.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			String array[] = value.split("/");
			result.setYear(Integer.parseInt(array[2]));
			result.setMonth(Integer.parseInt(array[1]));
			result.setDay(Integer.parseInt(array[0]));
			return (result);
		}
		throw new Exception("nao e data!");
	}

}
