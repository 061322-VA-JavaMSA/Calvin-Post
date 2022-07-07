package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Connection c;

	public static Connection getConnectionFromFile() throws IOException, SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("connection.properties"));
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		if (c == null || c.isClosed()) {
			c = DriverManager.getConnection(url, username, password);
		}
		return c;
	}
	
	public static Connection getConnectionFromEnv() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		if (c == null || c.isClosed()) {
			c = DriverManager.getConnection(url, username, password);
		}
		return c;
	}
}
