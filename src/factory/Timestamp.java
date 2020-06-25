package factory;

public final class Timestamp extends AbstractDateTime {

	static {
		Factory.getInstance().register(new Timestamp());
	}

	private int dia, mes, ano;
	private int hora, minuto, segundo;
	// CRIAR UM PADRAO DATA E HORA...

	private Timestamp() {
		this.regex = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
		// [0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}
		// [0-9]{2}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}
		// [0-9]{2}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}

		this.dia = 1;
		this.mes = 1;
		this.ano = 1900;
		this.hora = 0;
		this.minuto = 0;
		this.segundo = 0;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
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
			Timestamp datahora = new Timestamp();
			String array1[] = valor.split(" ");
			String array2[] = array1[0].split("-");
			datahora.setAno(Integer.parseInt(array2[0]));
			datahora.setMes(Integer.parseInt(array2[1]));
			datahora.setDia(Integer.parseInt(array2[2]));
			String array3[] = array1[1].split(":");
			datahora.setHora(Integer.parseInt(array3[0]));
			datahora.setMinuto(Integer.parseInt(array3[1]));
			datahora.setSegundo(Integer.parseInt(array3[2]));
			return datahora;
		}
		throw new Exception("nao e timestamp!");
	}

	public String show() {
		return "TIMESTAMP:" + String.valueOf(this.dia) + "/" + String.valueOf(this.mes) + "/" + String.valueOf(this.ano)
				+ " " + String.valueOf(this.hora) + ":" + String.valueOf(this.minuto) + ":"
				+ String.valueOf(this.segundo);
	}

}
