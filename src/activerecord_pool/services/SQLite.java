package activerecord_pool.services;

public class SQLite implements SGBDStrategy {

	@Override
	public String getPath(ConfigurationWithParameters configuration) {
		return "jdbc:sqlite:".concat("/").concat(configuration.getDb()).concat(".db");
	}

}
