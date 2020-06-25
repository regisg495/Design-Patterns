package activerecord_pool.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import activerecord_pool.exception.ConfigurationEmptyException;
import activerecord_pool.exception.PoolInUseException;

public final class Pool {
	private static Pool INSTANCE = null;
	private String url;
	private static String db;
	private static List<Connection> inUse = new ArrayList<>();
	private static List<Connection> readyToUse = new ArrayList<>();
	private static final int maxSize = 2;

	private Pool() {

	}

	public static Pool getInstance() {
		if (INSTANCE == null) {
			synchronized (Pool.class) {
				if (INSTANCE == null) {
					INSTANCE = new Pool();
				}
			}
			INSTANCE = new Pool();
		}

		return INSTANCE;
	}

	public void Configure(Configuration configuration) throws SQLException {
	
		if (db != null) {
			throw new PoolInUseException("Pool in Use");
		}
		if (!(configuration instanceof NullConfiguration)) {
			throw new ConfigurationEmptyException("You need to configure it");
		}

		db = configuration.getSgbd().getClass().getSimpleName();
		this.url = configuration.getPath();
		System.out.println(this.url);
		Connection connection = DriverManager.getConnection(this.url);

		for (int i = 0; i < maxSize; i++) {
			readyToUse.add(connection);
		}
	}

	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(url);
		
		return con;
		
		/*
		if (readyToUse.size() <= 0) {
			throw new ConnectionInUseException("Connection in Use");
		}
		Connection connection = readyToUse.get(0);

		inUse.add(connection);
		readyToUse.remove(connection);

		return connection;
		 */
	}

	public static void release(Connection connection) {
		readyToUse.add(connection);
		inUse.remove(connection);
	}

	public static void closeAll() {
		readyToUse = new ArrayList<>();
		inUse = new ArrayList<>();
		INSTANCE = new Pool();
	}

}
