package com.laptrinhjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDAO {
	
	protected static final String URL = "jdbc:sqlserver://DESKTOP-18QJCDR\\BAO:1433;databaseName=QLBH";
	protected static final String USER = "sa";
	protected static final String PASS = "1";

	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("connect successfully!");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connect failure!");
		}

		return connection;
	}
	
}
