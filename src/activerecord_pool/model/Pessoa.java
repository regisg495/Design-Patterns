package activerecord_pool.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import activerecord_pool.exception.InvalidValueException;
import activerecord_pool.services.PessoaAR;

public class Pessoa extends PessoaAR {
	@Regex(value = "[0-9]{9}-[0-9]{2}")
	private String cpf; // [0-9]{9}-[0-9]{2}
	@Regex(value = "[A-Z][a-z]+( [A-Z][a-z]+)+")
	private String nome; // [A-Z][a-z]+( [A-Z][a-z]+)+

	private LocalDate nascimento;
	@Regex(value = "[MF]")
	private char genero; // [MF]

	private Endereco endereco;

	@Regex(value = "[0-9]{8}")
	private List<String> telefones = new ArrayList<>(); // (0[0-9]{2})?[0-9]{8,9}

	@Regex(value = "[a-z0-9]+([_\\.][a-z0-9]+)*@[a-z0-9]+([_\\.][a-z0-9]+)*\\.[a-z]{3}(\\.[a-z]{2})?")
	private List<String> emails = new ArrayList<>(); // [a-z0-9]+([_\.][a-z0-9]+)*@[a-z0-9]+([_\.][a-z0-9]+)*\.[a-z]{3}(\.[a-z]{2})?

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws InvalidValueException {
		if (!cpf.matches("[0-9]{9}-[0-9]{2}")) {
			throw new InvalidValueException("Valor invalido no CPF, campo n�o adicionado");

		} else {
			this.cpf = cpf.substring(0, 9).concat(cpf.substring(10, 12));
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws InvalidValueException {
		if (!nome.matches("[A-Z][a-z]+( [A-Z][a-z]+)+")) {
			throw new InvalidValueException("Valor invalido no nome, campo n�o adicionado");
		} else {
			this.nome = nome;
		}
	}

	public LocalDate getDataascimento() {
		return nascimento;
	}

	public void setDataascimento(LocalDate dataascimento) {
		this.nascimento = dataascimento;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) throws InvalidValueException {
		if (!Character.toString(genero).matches("[MF]")) {
			throw new InvalidValueException("Invalid value on Genero");
		} else {
			this.genero = genero;
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) throws InvalidValueException {
		for (int i = 0; i < telefones.size(); i++) {
			if (!telefones.get(i).matches("(0[0-9]{2})?[0-9]{8,9}")) {
				throw new InvalidValueException("Invalid value on telephone ");
			} else {
				this.telefones.add(telefones.get(i));
			}
		}
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) throws InvalidValueException {
		for (int i = 0; i < emails.size(); i++) {
			if (!emails.get(i)
					.matches("[a-z0-9]+([_\\.][a-z0-9]+)*@[a-z0-9]+([_\\.][a-z0-9]+)*\\.[a-z]{3}(\\.[a-z]{2})?")) {
				throw new InvalidValueException("Invalid value on email ");
			} else {
				this.emails.add(emails.get(i));
			}
		}
	}

	public Pessoa(String nome, String cpf, LocalDate dataascimento, char genero, Endereco endereco,
			List<String> telefones, List<String> emails) throws InvalidValueException {
		super();
		this.setNome(nome);
		this.setCpf(cpf);
		this.setDataascimento(dataascimento);
		this.setGenero(genero);
		this.setEndereco(endereco);
		this.setTelefones(telefones);
		this.setEmails(emails);

	}

	public Pessoa() {

	}

	@Override
	public String toString() {
		return "Pessoa [cpf=" + cpf + ", nome=" + nome + ", dataascimento=" + nascimento + ", genero=" + genero
				+ ", endereco=" + endereco + ", telefones=" + telefones + ", emails=" + emails + "]";
	}

}
