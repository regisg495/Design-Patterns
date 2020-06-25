package activerecord_pool.services;

public class ConfigurationWithParameters extends Configuration {
	private String userName;
	private String password;
	private static ConfigurationWithParameters INSTANCE = null;

	public ConfigurationWithParameters() {

	}

	public static ConfigurationWithParameters getInstance() {
		if (INSTANCE == null) {
			synchronized (ConfigurationWithParameters.class) {
				if (INSTANCE == null) {
					INSTANCE = new ConfigurationWithParameters();
				}
			}
			INSTANCE = new ConfigurationWithParameters();
		}

		return INSTANCE;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
