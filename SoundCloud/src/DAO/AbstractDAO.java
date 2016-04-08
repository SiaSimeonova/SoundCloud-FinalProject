package DAO;

import java.sql.Connection;

import db.DBConnection;

public abstract class AbstractDAO {
	private final Connection con = DBConnection.getInstance().getCon();

	public Connection getCon() {
		return con;
	}
	
}

