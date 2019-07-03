package br.com.soc.exame.ws.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/exames";
	private static final String USER = "root";
	private static final String PASSWORD = "blablabla";

	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	
	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
		
	}

}
