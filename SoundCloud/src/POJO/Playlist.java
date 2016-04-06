package POJO;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private static int commentCounter=0;
	private int ID;
	private String name;
	private List <AudioFile> myFiles;
	private boolean isPrivate;
	
	public Playlist(String name) {
		ID = ++commentCounter;
		this.name = name;
		this.myFiles = new ArrayList <AudioFile>();
		isPrivate=false;
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


	public boolean isPrivate() {
		return isPrivate;
	}


	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}
	
	
}
