package DAO;

import POJO.AudioFile;
import POJO.Playlist;
import POJO.User;

public interface IPlaylistDAO {

	public int addAudio(String name,  User owner, AudioFile audio) throws PlaylistDAOException;
	public int createPlaylist(String name,  User owner) throws PlaylistDAOException;
	public void deletePlaylist(String name, User owner) throws PlaylistDAOException;
	public Playlist getPlaylist(String name, User owner) throws PlaylistDAOException;
}