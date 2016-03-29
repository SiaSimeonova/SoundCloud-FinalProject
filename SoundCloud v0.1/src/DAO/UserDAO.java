package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.User;
import db.DBConnection;

public class UserDAO implements IUserDAO{
	private static final String INSERT_NEW_USER_SQL = "INSERT INTO Users VALUES (null,?,?,?,?,?,?,?,null)";
	private static final String SELECT_USER_SQL = "SELECT * FROM Users WHERE username=? and pasword=?";
	public int addUser(User user) throws SQLException{
		PreparedStatement ps=DBConnection.getInstance().getCon().prepareStatement(INSERT_NEW_USER_SQL,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPass());
		ps.setString(3, user.getFirstName());
		ps.setString(4, user.getSurname());
		ps.setInt(5, user.getAge());
		ps.setString(6, user.getGender());
		ps.setString(7, user.getMail());
		ps.executeUpdate();
		ResultSet result = ps.getGeneratedKeys();
		result.next();
		return result.getInt(1);
	}
	public boolean  isThereSuchUser(String user, String pass) throws SQLException{
		
		PreparedStatement ps= DBConnection.getInstance().getCon().prepareStatement(SELECT_USER_SQL);
		
		ps.setString(1, user);
		ps.setString(2, pass);
		
		ResultSet result = ps.executeQuery();
		
		return result.next();
		
	}
}
