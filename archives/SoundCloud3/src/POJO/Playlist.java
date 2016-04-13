package POJO;

import java.util.List;

public class Playlist {

	private String name;
	private List <AudioFile> myFiles;
	
	public Playlist(String name, List<AudioFile> myFiles) {
		super();
		this.name = name;
		this.myFiles = myFiles;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<AudioFile> getMyFiles() {
		return myFiles;
	}


	public void setMyFiles(List<AudioFile> myFiles) {
		this.myFiles = myFiles;
	}
	
	
}
