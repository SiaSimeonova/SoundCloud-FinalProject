package DAO;

import java.util.List;

import POJO.AudioFile;
import POJO.User;

public interface IUserDAO {
	public int addUser(User user) throws UserDAOException;
	public boolean  isThereSuchUser(String user,String password) throws UserDAOException;
	public int editUser(User user) throws UserDAOException;
	public void deteteUser(User user) throws UserDAOException;
	public User getUser(String name) throws UserDAOException;
	public int followUser(User user,User userToFollow) throws UserDAOException;
	public int getFollowing(User user) throws UserDAOException;
	public int getFollowers(User user) throws UserDAOException;
	public int getAudioFiles(User user) throws UserDAOException;
	public List<AudioFile> getFollowersAudios(User user) throws UserDAOException;
	public List<AudioFile> getMyAudios(User user) throws UserDAOException;
	public List<AudioFile> getFollowersAudiosByDate(User user) throws UserDAOException;
}
