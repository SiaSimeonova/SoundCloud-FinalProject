package DAO;

import java.util.List;

import POJO.AudioFile;

public interface IAudioFileDAO {

	public int addAudio(AudioFile audio) throws AudioDAOException;
	public int updateAudio(String name) throws AudioDAOException;
	public int deleteAudio(String name) throws AudioDAOException;
	public int searchAudio(String name) throws AudioDAOException;
	public void like(String username,int audioID);
	public void comment(String userID,String comment,int postID);
	public int getPostLikes(AudioFile audio);
	public List<String> getAllCommentsOfPost(AudioFile audio);
}