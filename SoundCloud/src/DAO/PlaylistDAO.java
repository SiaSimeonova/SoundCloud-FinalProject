package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.AudioFile;
import POJO.Playlist;
import POJO.User;

public class PlaylistDAO  extends AbstractDAO implements IPlaylistDAO {

	private static final String INSERT_NEW_AUDIO_SQL = "INSERT INTO playlist VALUES (null,?,?,?)";
	private static final String DELETE_PLAYLIST_AUDIO_SQL = "DELETE FROM playlist WHERE name=? and owner=?";
	
	@Override
	public int createPlaylist(String name, User owner) throws PlaylistDAOException {
		if (name != null) {
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(INSERT_NEW_AUDIO_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, owner.getUserName());
				ps.setBoolean(3, false);
				ps.executeUpdate();
				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PlaylistDAOException("The playlist cannot be added right now. Please try again.", e);
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
		throw new PlaylistDAOException("The playlist cannot be added right now. Please try again.");
	}

	@Override
	public void deletePlaylist(String name,  User owner) throws PlaylistDAOException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_PLAYLIST_AUDIO_SQL);
			ps.setString(1, name);
			ps.setString(2, owner.getUserName());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new PlaylistDAOException("The file cannot be deleted right now, please try again.", e);
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
	public int addAudio(AudioFile audio) throws PlaylistDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Playlist getPlaylist(String name) throws PlaylistDAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
