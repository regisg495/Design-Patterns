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

import activerecord_pool.exception.InvalidColumnException;
import activerecord_pool.exception.InvalidValueException;
import activerecord_pool.model.Funcionario;

public class FuncionarioAR extends ActiveRecord<Funcionario> {
	private Map<String, Object> mapa = new HashMap<>();
	private static FuncionarioAR INSTANCE = null;

	protected FuncionarioAR() {

	}

	public static FuncionarioAR getInstance() {
		if (INSTANCE == null) {
			synchronized (FuncionarioAR.class) {
				if (INSTANCE == null) {
					INSTANCE = new FuncionarioAR();
				}
			}
			INSTANCE = new FuncionarioAR();
		}
		return INSTANCE;

	}

	@Override
	public List<Funcionario> where(String coluna, String valor) {
		if (!containsField(coluna) && !coluna.equals("cpf")) {
			throw new InvalidColumnException("Column ".concat(coluna).concat(" doenst exists"));
		} else {
			try (Connection connection = Pool.getInstance().getConnection()) {

				String sql = "SELECT * FROM Funcionario WHERE ".concat(coluna).concat(" = ?;");

				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setObject(1, valor, Types.OTHER);

				ResultSet result = stmt.executeQuery();

				return rows(result);

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void saveIt() {
		String sql = "INSERT INTO Funcionario (";

		Object[] keys = this.mapa.keySet().toArray();

		for (int i = 0; i < keys.length; i++) {
			sql += (i == keys.length - 1) ? keys[i] + ") VALUES (" : keys[i] + ",";
		}

		Object[] values = this.mapa.values().toArray();
		for (int i = 0; i < values.length; i++) {
			sql += (i == keys.length - 1) ? "?);" : "?,";
		}
		try (Connection connection = Pool.getInstance().getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(sql);

			for (int i = 0; i < values.length; i++) {
				stmt.setObject(i + 1, values[i]);
			}
			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean containsField(String coluna) {
		Field[] fields = Funcionario.class.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(coluna)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void set(String coluna, Object valor) throws InvalidValueException, NoSuchFieldException, SecurityException {
		if (!containsField(coluna) && !coluna.equals("cpf")) {
			throw new InvalidColumnException("Column ".concat(coluna).concat(" doenst exists"));
		} else {
			this.mapa.putIfAbsent(coluna, valor);
		}

	}

	@Override
	public void createIt(Funcionario funcionario) {
		try (Connection connection = Pool.getInstance().getConnection()) {
			String sql = "INSERT INTO Funcionario (cpf, salario, dependentes, contratacao) VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = connection.prepareStatement(sql);

			if (funcionario.getCpf() == null) {
				stmt.setNull(1, Types.NULL);
			} else {
				stmt.setString(1, funcionario.getCpf());
			}
			stmt.setDouble(2, funcionario.getSalario());

			stmt.setInt(3, funcionario.getDependentes());

			if (funcionario.getContratacao() == null) {
				stmt.setNull(4, Types.NULL);
			} else {
				stmt.setDate(4, Date.valueOf(funcionario.getContratacao()));

			}

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Funcionario row(ResultSet result) throws SQLException {
		Date d = result.getDate("contratacao");
		Funcionario funcionario = new Funcionario(result.getDouble("salario"), result.getInt("dependentes"),
				d.toLocalDate());

		return funcionario;

	}

	private List<Funcionario> rows(ResultSet result) throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<>();
		while (result.next()) {
			funcionarios.add(row(result));
		}
		return funcionarios;
	}

	@Override
	public void delete(Funcionario funcionario) {
		try (Connection connection = Pool.getInstance().getConnection()) {

			String sql = "DELETE FROM Funcionario WHERE cpf = ?;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, funcionario.getCpf());

			stmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Funcionario> loadAll() {
		String sql = "SELECT * FROM Funcionario;";
		try (Connection connection = Pool.getInstance().getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			return rows(result);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void edit(Funcionario funcionario) {
		if (funcionario.getCpf() == null) {
			throw new InvalidValueException("Invalid value for CPF");
		}
		List<Funcionario> funcionarios = this.where("cpf", funcionario.getCpf().replace("-", ""));
		boolean b = false;
		for (Iterator<Funcionario> i = funcionarios.iterator(); i.hasNext();) {
			if (i.next().getCpf().equals(funcionario.getCpf().replace("-", ""))) {
				b = true;
			}
		}
		if (!b) {
			throw new InvalidValueException("Invalid CPF");
		} else {
			try (Connection connection = Pool.getInstance().getConnection()) {

				String sql = "UPDATE Funcionario set dependentes = ?, contratacao = ? WHERE cpf = ?;";

				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setInt(1, funcionario.getDependentes());

				if (funcionario.getContratacao() == null) {
					stmt.setNull(2, Types.NULL);
				} else {
					stmt.setDate(2, Date.valueOf(funcionario.getContratacao()));
				}

				stmt.setString(3, funcionario.getCpf());

				stmt.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
