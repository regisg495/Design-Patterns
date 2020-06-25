package activerecord_pool.services;

public class ConfigurationBuilder {
	private ConfigurationWithParameters conf = NullConfiguration.getInstance();
	private static ConfigurationBuilder INSTANCE = null;

	private ConfigurationBuilder() {

	}

	public static ConfigurationBuilder getInstance() {
		if (INSTANCE == null) {
			synchronized (ConfigurationBuilder.class) {
				if (INSTANCE == null) {
					INSTANCE = new ConfigurationBuilder();
				}
			}
			INSTANCE = new ConfigurationBuilder();
		}

		return INSTANCE;
	}

	public ConfigurationBuilder setSGBD(String sgbd)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName("activerecord_pool.services.".concat(sgbd));
		this.conf.setSgbd((SGBDStrategy) clazz.newInstance());

		return this;
	}

	public ConfigurationBuilder setDB(String DB) {
		this.conf.setDb(DB);
		return this;
	}

	public ConfigurationBuilder setUserName(String username) {
		this.conf.setUserName(username);
		return this;
	}

	public ConfigurationBuilder setPassword(String password) {
		this.conf.setPassword(password);
		return this;
	}

	public ConfigurationBuilder setServername(String servername) {
		this.conf.setServerName(servername);
		return this;
	}

	public Configuration build() {

		this.conf.setPath(this.conf.getSgbd().getPath(this.conf));
		return this.conf;
	}

}
