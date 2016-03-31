package DAO;

import java.sql.SQLException;

import POJO.User;

public interface IUserDAO {
	public int addUser(User user) throws SQLException;

}
