package activerecord_pool.services;

public abstract class Configuration {
	private SGBDStrategy sgbd;
	private String db;
	private String serverName;
	private String path;

	public SGBDStrategy getSgbd() {
		return sgbd;
	}

	public String getDb() {
		return db;
	}

	public String getServerName() {
		return serverName;
	}

	public String getPath() {
		return path;
	}

	public void setSgbd(SGBDStrategy sgbd) {
		this.sgbd = sgbd;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
