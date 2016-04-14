package DAO;

import java.util.List;

import POJO.AudioFile;
import POJO.Comment;

public interface IAudioFileDAO {

	public int addAudio(AudioFile audio) throws AudioDAOException;
	public int updateAudio(AudioFile audio) throws AudioDAOException;
	public void deleteAudio(AudioFile audio) throws AudioDAOException;
	public List<AudioFile> getUploads(String  owner) throws AudioDAOException;
	
	public void comment(String username, int audioID, String comment);
	public int getPostLikes(AudioFile audio) throws AudioDAOException ;
	public List<Comment> getAllCommentsOfPost(AudioFile audio);
	public int getSongId(AudioFile song);
}