package databaseservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseImpl implements Database {

	private Connection con;
	private final String driver;
	private String url;
	private String username;
	private String password;

	public DatabaseImpl() {
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://localhost:3306/pharmacy_management?characterEncoding=utf8";
		this.username = "root";
		this.password = "root";
	}

	@SuppressWarnings("finally")
	@Override
	public Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successfull");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL Error");
			System.out.println(e.getMessage());
		} finally {
			return con;
		}
	}

}
