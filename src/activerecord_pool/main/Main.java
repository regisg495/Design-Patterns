package activerecord_pool.main;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import activerecord_pool.exception.InvalidValueException;
import activerecord_pool.model.Endereco;
import activerecord_pool.model.Funcionario;
import activerecord_pool.model.Pessoa;
import activerecord_pool.services.Configuration;
import activerecord_pool.services.ConfigurationBuilder;
import activerecord_pool.services.FuncionarioAR;
import activerecord_pool.services.PessoaAR;
import activerecord_pool.services.Pool;

public class Main {

	public static void main(String[] args) throws InvalidValueException, NoSuchFieldException, SecurityException,
			InstantiationException, IllegalAccessException {
		
		try {
			List<String> telefones = new ArrayList<>();
			List<String> emails = new ArrayList<>();

			Configuration configuration = ConfigurationBuilder.getInstance().setSGBD("PostgreSQL").setDB("siamelhorado")
					.setServername("localhost").setUserName("postgres").setPassword("postgres").build();

			Pool.getInstance().Configure(configuration);

			telefones.add("32381775");
			emails.add("regisg@gmail.com.br");

			PessoaAR pessoaAR = PessoaAR.getInstance();
			Pessoa p = new Pessoa("Regis Guimaraes", "033227670-86", LocalDate.now(), 'M', new Endereco(), telefones,
					emails);

			pessoaAR.createIt(p);

			// Pessoa e = new Pessoa("fsdafg36", "54545", LocalDate.now(), 'K', new
			// Endereco(),
			// telefones, emails);

			PessoaAR pessoaar = PessoaAR.getInstance();

			List<String> telefones2 = new ArrayList<>();

			telefones2.add("32381775");

			pessoaar.set("cpf", "033227670-85");
			pessoaar.set("nome", "Regis Guimaraes");
			pessoaar.set("telefones", telefones2);
			pessoaar.set("emails", emails);
			pessoaar.setEndereco(new Endereco());
			pessoaar.set("genero", "M");

			pessoaar.saveIt(); // Salva

			List<Pessoa> pessoas = pessoaar.where("nome", "Regis Guimaraes");

			Iterator<Pessoa> i = pessoas.iterator();

			while (i.hasNext()) {
				System.out.println(i.next().toString());
			}

			List<Funcionario> funcionarios = FuncionarioAR.getInstance().where("dependentes", "3");

			System.out.println(funcionarios.toString());

			Funcionario funcionario2 = new Funcionario(1000.0, 3, LocalDate.now());

			funcionario2.setCpf("033227670-85");

			FuncionarioAR funcionarioAR = FuncionarioAR.getInstance();

			funcionarioAR.createIt(funcionario2);

			funcionarioAR.delete(funcionario2);
			// Deletando funcionario

			pessoaar.delete(p);

			funcionarioAR.set("cpf", "03322767085");
			funcionarioAR.set("contratacao", LocalDate.now());
			funcionarioAR.set("salario", 2000.0);
			funcionarioAR.set("dependentes", 2);

			funcionarioAR.saveIt();

		} catch (InvalidValueException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
