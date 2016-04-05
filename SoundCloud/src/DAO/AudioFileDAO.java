package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import POJO.AudioFile;

public class AudioFileDAO  extends AbstractDAO implements IAudioFileDAO{
	
	private static final String INSERT_NEW_AUDIO_SQL = "INSERT INTO audiofiles VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_AUDIO_SQL = "SELECT * FROM audiofiles WHERE idAudioFile=?";
	private static final String DELETE_AUDIO_SQL = "DELETE FROM audiofiles WHERE idAudioFile=?";
	private static final String UPDATE_AUDIO_SQL = "UPDATE audiofiles SET URL = ?, Category = ?, Name = ?, Autor = ?, Description = ?, Picture = ?, isPrivate = ?, Likes = ?, Repost = ?, Shares = ?, Downloads = ?, Played = ?, Owner = ? WHERE idAudioFile = ?";
	private static final String LIKE_SQL = "select count(user) from likes where user = ? and idAudio = ? ";
	

	
	@Override
	public int addAudio(AudioFile audio) throws AudioDAOException {
		if (audio != null) {
			PreparedStatement ps = null;
				try {
					ps = getCon().prepareStatement(INSERT_NEW_AUDIO_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, audio.getURL());
					ps.setString(2, audio.getCategory());
					ps.setString(3, audio.getName());
					ps.setString(4, audio.getAutor());
					ps.setString(5,audio.getDescription());
					ps.setString(6, audio.getPicture());
					ps.setBoolean(7, audio.isPrivate());
					ps.setInt(8, audio.getLikes());
					ps.setInt(9, audio.getReposts());
					ps.setInt(10, audio.getShares());
					ps.setInt(11, audio.getDownloads());
					ps.setInt(12, audio.getTimesPlayed());
					ps.setString(13, audio.getOwnersName());
					ps.executeUpdate();
					ResultSet result = ps.getGeneratedKeys();
					result.next();
					return result.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AudioDAOException("The audio cannot be added right now. Please try again.", e);
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
		throw new AudioDAOException("The file cannot be added right now. Please try again.");
	}

	@Override
	public int updateAudio(String name) throws AudioDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAudio(String name) throws AudioDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int searchAudio(String name) throws AudioDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void like(String username, int audioID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comment(String userID, String comment, int postID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPostLikes(AudioFile audio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAllCommentsOfPost(AudioFile audio) {
		// TODO Auto-generated method stub
		return null;
	}

}
