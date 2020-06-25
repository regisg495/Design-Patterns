package activerecord_pool.services;

public class PostgreSQL implements SGBDStrategy {

	@Override
	public String getPath(ConfigurationWithParameters configuration) {
		return "jdbc:postgresql://".concat(configuration.getServerName()).concat("/").concat(configuration.getDb())
				.concat("?user=").concat(configuration.getUserName()).concat("&password=")
				.concat(configuration.getPassword());
	}

}
