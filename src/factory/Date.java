package factory;


public final class Date extends AbstractDateTime {

	static {
		Factory.getInstance().register(new Date());
	}

	private int dia, mes, ano;

	private Date() { // FALTA CRIAR PARA OUTROS PADRÕES DE DATA... (ATENÇÃO AO CONSTRUTOR PRIVADO)
		this.regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}"; // [0-9]{2}/[0-9]{2}/[0-9]{4}
		this.dia = 1;
		this.mes = 1;
		this.ano = 1900;
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

	public DateTime create(String valor) throws Exception {
		if (this.matches(valor)) {
			Date data = new Date();
			String array[] = valor.split("-");
			data.setAno(Integer.parseInt(array[0]));
			data.setMes(Integer.parseInt(array[1]));
			data.setDia(Integer.parseInt(array[2]));
			return data;
		}
		throw new Exception("nao e data!");
	}

	public String show() {
		return "DATE:" + String.valueOf(this.dia) + "/" + String.valueOf(this.mes) + "/" + String.valueOf(this.ano);
	}

}
