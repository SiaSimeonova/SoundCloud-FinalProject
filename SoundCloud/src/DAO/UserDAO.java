package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.User;
import db.DBConnection;

public class UserDAO extends AbstractDAO implements IUserDAO {
	private static final String INSERT_NEW_USER_SQL = "INSERT INTO Users VALUES (null,?,?,?,?,?,?,?)";
	private static final String SELECT_USER_SQL = "SELECT * FROM Users WHERE username=?";
	private static final String DELETE_USER_SQL = "DELETE * FROM Users WHERE username=? and pasword=?";
	private static final String UPDATE_USER_SQL = "UPDATE Users SET pasword = ?,"
			+ " name = ?, surname = ?, years = ?, gender = ?, e-mail = ? WHERE username = ?;";
	private static final String FIND_USER_BY_USERNAME_SQL = "SELECT * FROM Users WHERE username = ?";

	public int addUser(User user) throws UserDAOException {
		if (user != null) {
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(INSERT_NEW_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getPass());
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getSurname());
				ps.setInt(5, user.getAge());
				ps.setString(6, user.getGender());
				ps.setString(7, user.getMail());
				ps.setString(7, user.getPicPath());
				ps.executeUpdate();
				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserDAOException("The user cannot be added right now. Please try again.", e);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new UserDAOException("The user cannot be added right now. Please try again.");
		}
	}

	public boolean isThereSuchUser(String user) throws UserDAOException {
		PreparedStatement ps;
		try {
			ps = DBConnection.getInstance().getCon().prepareStatement(SELECT_USER_SQL);
			ps.setString(1, user);
			ResultSet result = ps.executeQuery();
			return result.next();
		} catch (SQLException e) {
			throw new UserDAOException("The operation has not be completed, please try again!", e);
		}
	}

	@Override
	public int editUser(User user) throws UserDAOException {
		if (user != null) {
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(UPDATE_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getPass());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getSurname());
				ps.setInt(4, user.getAge());
				ps.setString(5, user.getGender());
				ps.setString(6, user.getMail());
				ps.setString(7, user.getUserName());
				ps.executeUpdate();
				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(2);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserDAOException("The user cannot be changed right now. Please try again.", e);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new UserDAOException("The user cannot be changed right now. Please try again.");
		}
	}

	@Override
	public void deteteUser(User user) throws UserDAOException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(DELETE_USER_SQL);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPass());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserDAOException("The user cannot be deleted right now, please try again.", e);
		}
	}

	@Override
	public User getUser(String username) throws UserDAOException {
		User wantedUser = null;
		try {
			PreparedStatement ps = getCon().prepareStatement(FIND_USER_BY_USERNAME_SQL);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			result.next();
			String userName = result.getString(2);
			String pass = result.getString(3);
			String firstName = result.getString(4);
			String surname = result.getString(5);
			int age = result.getInt(6);
			String gender = result.getString(7);
			String mail = result.getString(8);
			String picPath = result.getString(9);
			wantedUser = new User(userName, pass, firstName, surname, age, gender, mail, picPath);
			return wantedUser;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserDAOException("The user with username " + username + " cannot be found!", e);
		}
	}

	@Override
	public int followUser(User user,User userToFollow) throws UserDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFollowing(User user) throws UserDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFollowers(User user) throws UserDAOException {
		// TODO Auto-generated method stub
		return 0;
	}
}