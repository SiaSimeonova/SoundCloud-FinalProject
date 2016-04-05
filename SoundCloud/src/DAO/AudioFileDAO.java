package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.AudioFile;

public class AudioFileDAO  extends AbstractDAO{
	
	private static final String INSERT_NEW_AUDIO_SQL = "INSERT INTO audiofiles VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	public int addAudio(AudioFile audio) throws UserDAOException {
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
					throw new UserDAOException("The audio cannot be added right now. Please try again.", e);
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
		throw new UserDAOException("The file cannot be added right now. Please try again.");
	}

}
