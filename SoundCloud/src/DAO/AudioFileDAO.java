package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.classfmt.MethodInfoWithAnnotations;

import POJO.AudioFile;
import POJO.Comment;
import POJO.User;

public class AudioFileDAO extends AbstractDAO implements IAudioFileDAO {
	private static final String CHECK_IF_FILE_IS_LIKED = "select Likes from audiofiles where Name like ?;";
	private static final String INSERT_NEW_AUDIO_SQL = "INSERT INTO audiofiles VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_AUDIO_SQL = "DELETE FROM audiofiles WHERE idAudioFile=?";
	private static final String UPDATE_AUDIO_SQL = "UPDATE audiofiles SET URL = ?, Category = ?, Name = ?, Autor = ?, Description = ?, Picture = ?, isPrivate = ?, Likes = ?, Repost = ?, Shares = ?, Downloads = ?, Played = ?, Owner = ? WHERE idAudioFile = ?";
	private static final String LIKE_SQL = "UPDATE audiofiles SET Likes=? WHERE idAudioFile=?;";
	private static final String LIST_WONTED_AUDIOS_SQL = "select * from audiofiles a join users u on (a.Owner=u.idUser) where u.username=?";
																
																
	private static final String COMMENT_SQL = "insert into comments (user,idAudio,data) values (?,?,?)";
	private static final String COUNT_MY_LIKES_SQL = "select count(ID) from likes where idAudio = ?";
	private static final String LIST_MY_COMMENTS_SQL = "select * from COMMENTS where idAudio=?";
	private static final String GET_SONG_BY_ID_SQL = "SELECT URL FROM audiofiles  where idAudioFile like ?";
	private static final String GET_RANDOM_ID_SQL = "select * from audiofiles  where idAudioFile order by rand() limit 3;";
	private static final String GET_IMAGE_URL_SQL = "SELECT Picture FROM audiofiles  where idAudioFile like ?";
	private static final String GET_SONG_ID_SQL="SELECT idAudioFile FROM audiofiles where Name like ?";
	private static final String GET_SONG_BY_ID="SELECT * FROM audiofiles where idAudioFile=?";
	private static final String LIST_SEARCHED_AUDIOS_SQL = "SELECT * FROM mydb.audiofiles where Name like ? or Autor like ? or Category like ? or Description like ?";
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
				ps.setString(5, audio.getDescription());
				ps.setString(6, audio.getPicture());
				ps.setBoolean(7, audio.isPrivate());
				ps.setInt(8, audio.getLikes());
				ps.setInt(9, audio.getReposts());
				ps.setInt(10, audio.getShares());
				ps.setInt(11, audio.getDownloads());
				ps.setInt(12, audio.getTimesPlayed());
				ps.setString(13, null);
				ps.setInt(14, audio.getOwnersName());
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
	public int updateAudio(AudioFile audio) throws AudioDAOException {
		if (audio != null) {
			PreparedStatement ps = null;
			try {
				ps = getCon().prepareStatement(UPDATE_AUDIO_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, audio.getURL());
				ps.setString(2, audio.getCategory());
				ps.setString(3, audio.getName());
				ps.setString(4, audio.getAutor());
				ps.setString(5, audio.getDescription());
				ps.setString(6, audio.getPicture());
				ps.setBoolean(7, audio.isPrivate());
				ps.setInt(8, audio.getLikes());
				ps.setInt(9, audio.getReposts());
				ps.setInt(10, audio.getShares());
				ps.setInt(11, audio.getDownloads());
				ps.setInt(12, audio.getTimesPlayed());
				ps.setInt(13, audio.getOwnersName());
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
				throw new AudioDAOException("The file cannot be changed right now. Please try again.", e);
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
			throw new AudioDAOException("The file cannot be changed right now. Please try again.");
		}
	}

	@Override
	public void deleteAudio(AudioFile audio) throws AudioDAOException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_AUDIO_SQL);
			ps.setInt(1, audio.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new AudioDAOException("The file cannot be deleted right now, please try again.", e);
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
	
	public List<Integer> getUploadId(String owner){
		List<Integer> wantedAudio = new ArrayList<Integer>();
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_WONTED_AUDIOS_SQL);
			ps.setString(1, owner);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wantedAudio.add(rs.getInt("idAudioFile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return wantedAudio;
	}
	public List<AudioFile> getSearchedTracks(String key) throws AudioDAOException {
		List<AudioFile> wantedAudio = new ArrayList<AudioFile>();
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_SEARCHED_AUDIOS_SQL);
			ps.setString(1, key);
			ps.setString(2, key);
			ps.setString(3, key);
			ps.setString(4, key);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wantedAudio.add(new AudioFile(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
						rs.getInt(12), rs.getInt(13), rs.getInt(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return wantedAudio;
	}

	@Override
	public List<AudioFile> getUploads(String owner) throws AudioDAOException {
		List<AudioFile> wantedAudio = new ArrayList<AudioFile>();
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_WONTED_AUDIOS_SQL);
			ps.setString(1, owner);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wantedAudio.add(new AudioFile(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
						rs.getInt(12), rs.getInt(13), rs.getInt(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return wantedAudio;
	}
	
	
	public void like(String songname) {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(CHECK_IF_FILE_IS_LIKED);
			ps.setString(1, songname);			
			ResultSet rs = ps.executeQuery();
			rs.next();
			int likes = rs.getInt(1);
			PreparedStatement st = getCon().prepareStatement(LIKE_SQL);
			PreparedStatement getSongIdStatement=getCon().prepareStatement(GET_SONG_ID_SQL);
			getSongIdStatement.setString(1,songname );			
			ResultSet id=getSongIdStatement.executeQuery();
			id.next();
			st.setInt(1, ++likes);
			st.setInt(2, id.getInt("idAudioFile"));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void comment(String username, int audioID, String comment) {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(COMMENT_SQL);
			ps.setString(1, username);
			ps.setInt(2, audioID);
			ps.setString(3, comment);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int getPostLikes(AudioFile audio) throws AudioDAOException {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(COUNT_MY_LIKES_SQL);
			ps.setInt(1, audio.getId());
			ResultSet result = ps.executeQuery();
			result.next();
			res = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AudioDAOException("NA", e);
		} finally {
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
	public List<Comment> getAllCommentsOfPost(AudioFile audio) {
		List<Comment> comments = new ArrayList<Comment>();
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(LIST_MY_COMMENTS_SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(new Comment(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5).toString()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return comments;
	}
	public String getPathToSongById(int id) throws SQLException{
		PreparedStatement ps=getCon().prepareStatement(GET_SONG_BY_ID_SQL);
		ps.setInt(1, id);
		ResultSet result=ps.executeQuery();
		result.next();
		String path=result.getString(1);
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return path;
	}
	public String getPicPathById(int id) throws SQLException{
		PreparedStatement ps=getCon().prepareStatement(GET_IMAGE_URL_SQL);
		ps.setInt(1, id);
		ResultSet result=ps.executeQuery();
		result.next();
		String path=result.getString(1);
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return path;
	}
	public List<Integer> getRandomIdFromDb() throws SQLException{
		ResultSet result=getCon().createStatement().executeQuery(GET_RANDOM_ID_SQL);
		List<Integer> res=new ArrayList();
		int index=0;
		while(result.next()){
			res.add(result.getInt(1));
		}
			
		
		return res;
	}

	@Override
	public int getSongId(AudioFile song) {
		PreparedStatement ps = null;
		int result=0;
		try {
			ps = getCon().prepareStatement(GET_SONG_ID_SQL);
			ps.setString(1, song.getName());
			ResultSet rs = ps.executeQuery();
			rs.next();
			result= rs.getInt(1);
			}
		 catch (SQLException e) {
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
		return result;
	}
	public AudioFile getSongById(int id) throws SQLException{
		AudioFile song=new AudioFile();
		PreparedStatement ps=getCon().prepareStatement(GET_SONG_BY_ID);
		ps.setInt(1, id);
		ResultSet result=ps.executeQuery();
		result.next();
		song.setId(result.getInt("idAudioFile"));
		song.setURL(result.getString("URL"));
		
		song.setCategory(result.getString("Category"));
		song.setName(result.getString("Name"));
		song.setAutor(result.getString("Autor"));
		song.setDescription(result.getString("Description"));
		song.setPicture(result.getString("Picture"));
		song.setOwnersName(result.getInt("Owner"));
		
		return song;
	}
}


