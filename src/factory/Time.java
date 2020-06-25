package factory;

public final class Time extends AbstractDateTime {

	static {
		Factory.getInstance().register(new Time());
	}

	private int hora, minuto, segundo;

	private Time() {
		this.regex = "[0-9]{2}:[0-9]{2}:[0-9]{2}"; // [0-9]{2}:[0-9]{2}
		this.hora = 0;
		this.minuto = 0;
		this.segundo = 0;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}

	public DateTime create(String valor) throws Exception {
		if (this.matches(valor)) {
			Time hora = new Time();
			String array[] = valor.split(":");
			hora.setHora(Integer.parseInt(array[0]));
			hora.setMinuto(Integer.parseInt(array[1]));
			hora.setSegundo(Integer.parseInt(array[2]));
			return hora;
		}
		throw new Exception("nao e hora!");
	}

	public String show() {
		return "TIME:" + String.valueOf(this.hora) + ":" + String.valueOf(this.minuto) + ":"
				+ String.valueOf(this.segundo);
	}

}
