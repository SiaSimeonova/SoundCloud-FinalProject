package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String DB_PASS = "Silna@Parola!16";
	private static final String DB_USER = "root";
	private static final String DB_NAME = "mydb";
	private static final String DB_PORT = "3306";
	private static final String DB_HOST = "localhost";

	private static DBConnection instance = null;
	private Connection con;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER,
					DB_PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		synchronized (DBConnection.class) {
			if (instance == null) {
				instance = new DBConnection();
			}
		}
		return instance;
	}

	public Connection getCon() {
		return con;
	}

}
