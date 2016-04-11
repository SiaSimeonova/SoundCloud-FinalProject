package DAO;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.AudioFile;
import POJO.User;
import db.DBConnection;

public class UserDAO extends AbstractDAO implements IUserDAO {
	private static final String INSERT_NEW_USER_SQL = "INSERT INTO users VALUES (null,?,?,null,null,0,null,?,null)";
	private static final String SELECT_USER_SQL = "SELECT * FROM users WHERE username=? and pasword=?";
	private static final String DELETE_USER_SQL = "DELETE FROM users WHERE username=?";
	private static final String UPDATE_USER_SQL = "UPDATE users SET pasword = ?, name = ?, surname = ?, years = ?, gender = ?, picture = ? WHERE username = ?";
	private static final String FIND_USER_BY_USERNAME_SQL = "SELECT * FROM users WHERE username = ?";
	private static final String COUNT_FOLLOWERS = "select count(username_follower) from mydb.followers where username_followed = ?";
	private static final String COUNT_FOLLOWING = "select count(username_followed) from mydb.followers where username_follower = ?";
	private static final String ADD_FOLLOWERS = "insert into followers values (?, ?)";
	private static final String COUNT_MY_AUDIOS_SQL = "select count(ID) from audiofiles where Owner = ?";
	private static final String LIST_MY_AUDIOS_SQL = "select from audiofiles where Owner = ?";
	private static final String LIST_MY_FOLLOWED_AUDIOS_SQL = "select * from audiofiles where owner in(select username_follower from followers where username_followed =?)";
	private static final String LIST_MY_FOLLOWED_AUDIOS_SORTED_SQL = "select * from audiofiles where owner in(select username_follower from followers where username_followed =?) Order by TIME";
	private static final String SELECT_USER_ID_SQL = "SELECT idUser FROM users WHERE username=?";
	public int addUser(User user) throws UserDAOException {
		if (user != null) {
			PreparedStatement ps = null;
			if (!isThereSuchUser(user.getUserName(), user.getPass())) {
				try {
					ps = getCon().prepareStatement(INSERT_NEW_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getUserName());
					ps.setString(2, user.getPass());							
					ps.setString(3, user.getMail());
					
					ps.executeUpdate();
					ResultSet result = ps.getGeneratedKeys();
					result.next();
					return result.getInt(1);
				}catch(SQLIntegrityConstraintViolationException e) {
					throw new UserDAOException("Email already taken", e);
				}
				catch (Exception e) {
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
				throw new UserDAOException("The username exist, please choose different name");
			}
		}
		throw new UserDAOException("The user cannot be added right now. Please try again.");
	}

	public boolean isThereSuchUser(String user, String password) throws UserDAOException {
		PreparedStatement ps = null;
		try {
			ps = DBConnection.getInstance().getCon().prepareStatement(SELECT_USER_SQL);
			ps.setString(1, user);
			ps.setString(2,password);
			ResultSet result = ps.executeQuery();
			return result.next();
		} catch (SQLException e) {
			throw new UserDAOException("The operation has not been completed, please try again!", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int editUser(User user) throws UserDAOException {
		if (user != null) {
			if (isThereSuchUser(user.getUserName(), user.getPass())) {
				PreparedStatement ps = null;
				try {
					ps = getCon().prepareStatement(UPDATE_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getPass());
					ps.setString(2, user.getFirstName());
					ps.setString(3, user.getSurname());
					ps.setInt(4, user.getAge());
					ps.setString(5, user.getGender());
					ps.setString(6, user.getPicPath());
					ps.setString(7, user.getUserName());
					ps.executeUpdate();
					ResultSet result = ps.getGeneratedKeys();
					if (result.next()) {
						result.next();
						return result.getInt(1);
					} else {
						return 0;
					}

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
				throw new UserDAOException(
						"There isn't such user, please check the username and the password and try again!");
			}
		} else {
			throw new UserDAOException("The user cannot be changed right now. Please try again. (null)");
		}
	}

	@Override
	public void deteteUser(User user) throws UserDAOException {
		PreparedStatement ps=null;
		try {
			ps = getCon().prepareStatement(DELETE_USER_SQL);
			ps.setString(1, user.getUserName());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserDAOException("The user cannot be deleted right now, please try again.", e);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public User getUser(String username) throws UserDAOException {
		User wantedUser = null;
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(FIND_USER_BY_USERNAME_SQL);
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
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int followUser(User userToFollow, User follower) throws UserDAOException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(ADD_FOLLOWERS);
			String fowolled1 = userToFollow.getUserName();
			String follower1 = follower.getUserName();
			ps.setString(1, fowolled1);
			ps.setString(2, follower1);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserDAOException("The user to follow can not be added at the moment, please try again later!", e);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int getFollowing(User user) throws UserDAOException {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(COUNT_FOLLOWING);
			ps.setString(1, user.getUserName());
			ResultSet result = ps.executeQuery();
			result.next();
			res = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserDAOException("Your followers cannot be listed at the moment, please try again later!", e);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	@Override
	public int getFollowers(User user) throws UserDAOException {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(COUNT_FOLLOWERS);
			ps.setString(1, user.getUserName());
			ResultSet result = ps.executeQuery();
			result.next();
			res = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserDAOException("Your followers cannot be listed at the moment, please try again later!", e);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	@Override
	public int getAudioFiles(User user) throws UserDAOException {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(COUNT_MY_AUDIOS_SQL);
			ps.setString(1, user.getUserName());
			ResultSet result = ps.executeQuery();
			result.next();
			res = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserDAOException("Your audios cannot be listed at the moment, please try again later!", e);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return res;

	}

	@Override
	public List<AudioFile> getMyAudios(User user) throws UserDAOException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_MY_AUDIOS_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.getMyAudios()
						.add(new AudioFile(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
								rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10),
								rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user.getMyAudios();
	}

	@Override
	public List<AudioFile> getFollowersAudios(User user) throws UserDAOException {
		List<AudioFile> audios = new ArrayList<AudioFile>();
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_MY_FOLLOWED_AUDIOS_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				audios.add(new AudioFile(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
								rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10),
								rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return audios;
	}

	@Override
	public List<AudioFile> getFollowersAudiosByDate(User user) throws UserDAOException {
		List<AudioFile> audios = new ArrayList<AudioFile>();
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_MY_FOLLOWED_AUDIOS_SORTED_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				audios.add(new AudioFile(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
								rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10),
								rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return audios;
	}
	public int getUserId(String  username) throws SQLException{
		PreparedStatement ps =getCon().prepareStatement(SELECT_USER_ID_SQL);
		ps.setString(1, username);
		ResultSet result=ps.executeQuery();
		if(!result.next()){
			return 0;
		}
		return result.getInt(1);
	}
	

}