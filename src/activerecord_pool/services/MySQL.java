package activerecord_pool.services;

public class MySQL implements SGBDStrategy {

	@Override
	public String getPath(ConfigurationWithParameters configuration) {
		return "jdbc:mysql://".concat(configuration.getServerName()).concat("/").concat(configuration.getDb())
				.concat("?user=").concat(configuration.getUserName()).concat("&password=")
				.concat(configuration.getPassword());
	}

}
