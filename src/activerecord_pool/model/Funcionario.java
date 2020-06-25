package activerecord_pool.model;

import java.time.LocalDate;

public class Funcionario extends Pessoa {
	private double salario;
	private int dependentes;
	private LocalDate contratacao;

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getDependentes() {
		return dependentes;
	}

	public void setDependentes(int dependentes) {
		this.dependentes = dependentes;
	}

	public LocalDate getContratacao() {
		return contratacao;
	}

	public void setContratacao(LocalDate contratacao) {
		this.contratacao = contratacao;
	}

	public Funcionario() {

	}

	public Funcionario(double salario, int dependentes, LocalDate contratacao) {
		super();
		this.salario = salario;
		this.dependentes = dependentes;
		this.contratacao = contratacao;
	}

	@Override
	public String toString() {
		return "Funcionario [salario=" + salario + ", dependentes=" + dependentes + ", contratacao=" + contratacao
				+ "]";
	}

}
