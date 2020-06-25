package activerecord_pool.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import activerecord_pool.exception.ConstraintViolationException;
import activerecord_pool.exception.InvalidColumnException;
import activerecord_pool.exception.InvalidValueException;
import activerecord_pool.model.Endereco;
import activerecord_pool.model.Funcionario;
import activerecord_pool.model.Pessoa;
import activerecord_pool.model.Regex;

public class PessoaAR extends ActiveRecord<Pessoa> {
	private Map<String, Object> map = new HashMap<>();
	private static PessoaAR INSTANCE = null;

	protected PessoaAR() {

	}

	public static PessoaAR getInstance() {
		if (INSTANCE == null) {
			synchronized (PessoaAR.class) {
				if (INSTANCE == null) {

					INSTANCE = new PessoaAR();
				}
			}
			INSTANCE = new PessoaAR();
		}
		return INSTANCE;

	}

	@Override
	public List<Pessoa> where(String coluna, String valor) throws InvalidColumnException {
		if (!containsField(coluna)) {
			throw new InvalidColumnException("Column ".concat(coluna).concat(" doenst exists"));
		} else {
			try (Connection connection = Pool.getInstance().getConnection()) {

				String sql = "SELECT * FROM Pessoa WHERE ".concat(coluna).concat("= ?;");

				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setObject(1, valor);

				ResultSet result = stmt.executeQuery();

				return rows(result);

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public boolean containsField(String coluna) {

		Field[] fields = Pessoa.class.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAccessible()) {
				fields[i].setAccessible(true);
			}

			if (fields[i].getName().equals(coluna)) {

				return true;
			}
		}
		return false;
	}

	private Pessoa row(ResultSet result) throws SQLException {
		Endereco de = null;
		List<String> ddd = new ArrayList<>();
		List<String> ppp = new ArrayList<>();

		Date d = result.getDate("nascimento");

		Pessoa p = new Pessoa(result.getString("nome"),
				result.getString("cpf").substring(0, 9).concat("-").concat(result.getString("cpf").substring(9, 11)),
				d.toLocalDate(), result.getString("genero").charAt(0), de, ppp, ddd);
		return p;
	}

	private List<Pessoa> rows(ResultSet result) throws SQLException {
		List<Pessoa> pessoas = new ArrayList<>();
		while (result.next()) {
			pessoas.add(row(result));
		}
		return pessoas;
	}

	@Override
	public void set(String coluna, Object valor) throws InvalidValueException, NoSuchFieldException, SecurityException {
		if (!containsField(coluna)) {
			throw new InvalidColumnException("Column ".concat(coluna).concat(" doenst exists"));
		}
		Field[] f = Pessoa.class.getDeclaredFields();

		for (int i = 0; i < f.length; i++) {
			f[i].setAccessible(true);

			if (f[i].getName().equals(coluna) && f[i].isAnnotationPresent(Regex.class)) {
				String regex = f[i].getAnnotation(Regex.class).value();
				if (!valor.toString().matches(regex)) {
					throw new InvalidValueException("Invalid Value on ".concat(f[i].getName()));
				} else {
					if (coluna.equals("cpf")) {
						String value = valor.toString().replace("-", "");
						this.map.put(coluna, value);
						return;
					}
					this.map.put(coluna, valor);
					return;
				}
			}
		}

	}

	@Override
	public void saveIt() {
		String sql = "INSERT INTO Pessoa (";

		Object[] keys = this.map.keySet().toArray();

		for (int i = 0; i < keys.length; i++) {
			sql += (i == keys.length - 1) ? keys[i] + ") VALUES (" : keys[i] + ",";
		}

		Object[] values = this.map.values().toArray();
		for (int i = 0; i < values.length; i++) {
			sql += (i == keys.length - 1) ? "?);" : "?,";
		}

		try (Connection connection = Pool.getInstance().getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);

			for (int i = 0; i < values.length; i++) {
				stmt.setObject(i + 1, values[i]);
				System.out.println(values[i]);
			}
			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createIt(Pessoa pessoa) {

		try (Connection connection = Pool.getInstance().getConnection()) {
			String sql = "INSERT INTO Pessoa (nome, cpf, nascimento, genero, endereco, telefones, emails) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement stmt = connection.prepareStatement(sql);

			if (pessoa.getNome() == null) {
				stmt.setNull(1, Types.NULL);
			} else {
				stmt.setString(1, pessoa.getNome());
			}
			if (pessoa.getCpf() == null) {
				stmt.setNull(2, Types.NULL);
			} else {
				stmt.setString(2, pessoa.getCpf());
			}
			if (pessoa.getDataascimento() == null) {
				stmt.setNull(3, Types.NULL);
			} else {
				stmt.setDate(3, Date.valueOf(pessoa.getDataascimento()));
			}
			if (Character.toString(pessoa.getGenero()) == null) {
				stmt.setNull(4, Types.NULL);
			} else {
				stmt.setString(4, Character.toString(pessoa.getGenero()));
			}
			if (pessoa.getEndereco() == null) {
				stmt.setNull(5, Types.NULL);
			} else {
				stmt.setString(5, pessoa.getEndereco().toString());
			}
			if (pessoa.getTelefones().isEmpty() || pessoa.getTelefones() == null) {
				stmt.setNull(6, Types.NULL);
			} else {
				stmt.setString(6, formatTelefones(pessoa.getTelefones()));
			}
			if (pessoa.getEmails().isEmpty() || pessoa.getEmails() == null) {
				stmt.setNull(7, Types.NULL);
			} else {
				stmt.setString(7, formatEmails(pessoa.getEmails()));
			}

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private String formatEmails(List<String> emailss) {
		String emails = "";
		for (int i = 0; i < emailss.size(); i++) {
			emails += (i == emailss.size() - 1) ? emailss.get(i) + ". " : emailss.get(i) + ", ";
		}
		return emails;
	}

	private String formatTelefones(List<String> telefoness) {
		String telefones = "";
		for (int i = 0; i < telefoness.size(); i++) {
			telefones += (i == telefoness.size() - 1) ? telefoness.get(i) + "." : telefoness.get(i) + ", ";
		}
		return telefones;
	}

	public void setEndereco(Endereco endereco) throws InvalidColumnException {
		this.map.put("endereco", endereco.toString());

	}

	public void set(String coluna, List<String> list) {
		Field[] fields = Pessoa.class.getDeclaredFields();
		if (!coluna.equals("emails") && !coluna.equals("telefones")) {
			throw new InvalidColumnException("");
		}
		String regex = "";
		for (int i = 0; i < fields.length; i++) {
			if (coluna.equals("emails") && fields[i].isAnnotationPresent(Regex.class)) {
				regex = fields[i].getAnnotation(Regex.class).value();
			}
			if (coluna.equals("telefones") && fields[i].isAnnotationPresent(Regex.class)) {
				regex = fields[i].getAnnotation(Regex.class).value();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).matches(regex)) {
				this.map.put(coluna, list.get(i));
			}
		}

	}

	@Override
	public void delete(Pessoa pessoa) {
		try (Connection connection = Pool.getInstance().getConnection()) {

			String sql = "DELETE FROM Pessoa WHERE cpf = ?;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, pessoa.getCpf());

			List<Funcionario> funcionarios = FuncionarioAR.getInstance().where("cpf", pessoa.getCpf());

			for (Iterator<Funcionario> i = funcionarios.iterator(); i.hasNext();) {
				if (i.next().getCpf().equals(pessoa.getCpf())) {
					throw new ConstraintViolationException(pessoa.getCpf().concat(" is on Funcionario Table"));
				}
			}

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void edit(Pessoa pessoa) {
		if (pessoa.getCpf() == null) {
			throw new InvalidValueException("Invalid value for cpf");
		}
		List<Pessoa> pessoas = this.where("cpf", pessoa.getCpf().replace("-", ""));
		boolean b = false;
		for (Iterator<Pessoa> i = pessoas.iterator(); i.hasNext();) {
			if (i.next().getCpf().equals(pessoa.getCpf().substring(0, 9).concat(pessoa.getCpf().substring(9, 11)))) {
				b = true;
			}
		}

		if (!b) {
			throw new InvalidValueException("Invalid value for cpf");
		} else {
			try (Connection connection = Pool.getInstance().getConnection()) {

				String sql = "UPDATE Pessoa SET nome = ?, nascimento = ?, genero = ?, endereco = ?, telefones = ?, emails = ? WHERE cpf = ?;";

				PreparedStatement stmt = connection.prepareStatement(sql);

				if (pessoa.getNome() == null) {
					stmt.setNull(1, Types.NULL);
				} else {
					stmt.setString(1, pessoa.getNome());
				}
				if (pessoa.getDataascimento() == null) {
					stmt.setNull(2, Types.NULL);
				} else {
					stmt.setDate(2, Date.valueOf(pessoa.getDataascimento()));
				}
				if (Character.toString(pessoa.getGenero()) == null) {
					stmt.setNull(3, Types.NULL);
				} else {
					stmt.setString(3, Character.toString(pessoa.getGenero()));
				}
				if (pessoa.getEndereco() == null) {
					stmt.setNull(4, Types.NULL);
				} else {
					stmt.setString(4, pessoa.getEndereco().toString());
				}
				if (pessoa.getTelefones() == null) {
					stmt.setNull(5, Types.NULL);
				} else {
					stmt.setString(5, formatTelefones(pessoa.getTelefones()));
				}
				if (pessoa.getEmails() == null) {
					stmt.setNull(6, Types.NULL);
				} else {
					stmt.setString(6, formatEmails(pessoa.getEmails()));
				}
				stmt.setString(7, pessoa.getCpf());

				stmt.execute();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<Pessoa> loadAll() {
		String sql = "SELECT * FROM Pessoa;";
		try (Connection connection = Pool.getInstance().getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			return rows(result);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
