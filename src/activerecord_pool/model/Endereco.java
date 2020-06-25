package activerecord_pool.model;

import activerecord_pool.exception.InvalidValueException;

public class Endereco {

	private String tipo; // (Rua|Avenida|Travessa|Alameda|Via|Estrada|Rodovia|...)
	private String logradouro; // ([A-Z][a-z]+|[0-9]+)( ([A-Z][a-z]+|[0-9]+))+
	private String numero; // [0-9]+[A-Z]*
	private String CEP; // [0-9]{5}-[0-9]{3}
	private String cidade; // [A-Z][a-z]+( [A-Z][a-z]+)+
	private String UF; // (AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) throws InvalidValueException {
		if (!tipo.matches("(Rua|Avenida|Travessa|Alameda|Via|Estrada|Rodovia")) {
			throw new InvalidValueException("Invalid value on tipo");
		} else {
			this.tipo = tipo;
		}
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) throws InvalidValueException {
		if (!logradouro.matches("([A-Z][a-z]+|[0-9]+)( ([A-Z][a-z]+|[0-9]+))+")) {
			throw new InvalidValueException("Invalid value on logradouro");
		} else {
			this.logradouro = logradouro;
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) throws InvalidValueException {
		if (!numero.matches("[0-9]+[A-Z]*")) {
			throw new InvalidValueException("Invalid value on numero");
		} else {
			this.numero = numero;
		}
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) throws InvalidValueException {
		if (!cEP.matches("[0-9]{5}-[0-9]{3}")) {
			throw new InvalidValueException("Invalid value on CEP");
		} else {
			this.CEP = cEP;
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) throws InvalidValueException {
		if (!cidade.matches("[A-Z][a-z]+( [A-Z][a-z]+)+")) {
			throw new InvalidValueException("Invalid value on cidade");
		} else {
			this.cidade = cidade;
		}
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) throws InvalidValueException {
		if (!uF.matches("(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)")) {
			throw new InvalidValueException("Invalid value on UF");
		} else {
			this.UF = uF;
		}
	}

	public Endereco() {

	}

	public Endereco(String tipo, String logradouro, String numero, String cEP, String cidade, String uF) {
		super();
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.CEP = cEP;
		this.cidade = cidade;
		this.UF = uF;
	}

	@Override
	public String toString() {
		return "Endereco: tipo =" + tipo + ", logradouro =" + logradouro + ", numero =" + numero + ", CEP =" + CEP
				+ ", cidade =" + cidade + ", UF =" + UF + "]";
	}

}
