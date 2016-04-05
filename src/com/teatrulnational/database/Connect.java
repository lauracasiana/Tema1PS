package com.teatrulnational.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	private Connection conn;

	public Connect() {

		String url = "jdbc:mysql://localhost:8080/";
		String dbName = "teatrul_national";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "Parola.777";
		try {
			Class.forName(driver).newInstance();
			this.conn = DriverManager.getConnection(url + dbName, userName,
					password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	// deconecteaza de la baza de date
	public void DisConect() {

		try {
			conn.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
