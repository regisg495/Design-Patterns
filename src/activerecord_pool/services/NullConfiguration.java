package activerecord_pool.services;

public class NullConfiguration extends ConfigurationWithParameters {
	private static NullConfiguration INSTANCE = null;


	public static NullConfiguration getInstance() {
		if (INSTANCE == null) {
			synchronized (NullConfiguration.class) {
				if (INSTANCE == null) {
					INSTANCE = new NullConfiguration();
				}
			}
			INSTANCE = new NullConfiguration();
		}

		return INSTANCE;
	}
}
