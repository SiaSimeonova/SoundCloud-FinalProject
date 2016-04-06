package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import POJO.AudioFile;
import POJO.Playlist;
import POJO.User;

public class PlaylistDAO  extends AbstractDAO implements IPlaylistDAO {

	private static final String INSERT_NEW_PLAYLIST_SQL = "INSERT INTO playlist VALUES (null,?,?,?)";
	private static final String DELETE_PLAYLIST_PLAYLIST_SQL = "DELETE FROM playlist WHERE name=? and owner=?";
	private static final String ADD_AUDIO_SQL = "INSERT INTO playlists/audiofiles VALUES (?,?)";
	private static final String LIST_MY_AUDIOS_SQL = "select * from audiofiles where owner =? ) ";
	
	@Override
	public int createPlaylist(String name, User owner) throws PlaylistDAOException {
		if (name != null) {
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(INSERT_NEW_PLAYLIST_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
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
			ps = getCon().prepareStatement(DELETE_PLAYLIST_PLAYLIST_SQL);
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
	public int addAudio(String name,  User owner, AudioFile audio) throws PlaylistDAOException {
		if (name != null) {
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(ADD_AUDIO_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setInt(1, audio.getId());
				ps.setString(3, owner.getUserName());
				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PlaylistDAOException("The file cannot be added right now. Please try again.", e);
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
		throw new PlaylistDAOException("The file cannot be added right now. Please try again.");
	}

	@Override
	public Playlist getPlaylist(String name, User owner) throws PlaylistDAOException {
			Playlist playlist = new Playlist(name);
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(LIST_MY_AUDIOS_SQL);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					playlist.getMyFiles().add(new AudioFile(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
									rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10),
									rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getString(14)));
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
			return playlist;
		}
}
