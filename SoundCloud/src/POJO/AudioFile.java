package POJO;

public class AudioFile {

	private String URL;
	private String category;
	private String name;
	private String autor;
	private String description;
	private String picture;
	private boolean isPrivate;
	private int likes;
	private int reposts;
	private int shares;
	private int downloads;
	private int timesPlayed;
	private User owner;
	
	
	public AudioFile(String uRL, String category, String name, String autor, String description, String picture,
			boolean isPrivate, int likes, int reposts, int shares, int downloads, int timesPlayed, User owner) {
		super();
		URL = uRL;
		this.category = category;
		this.name = name;
		this.autor = autor;
		this.description = description;
		this.picture = picture;
		this.isPrivate = isPrivate;
		this.likes = likes;
		this.reposts = reposts;
		this.shares = shares;
		this.downloads = downloads;
		this.timesPlayed = timesPlayed;
		this.owner = owner;
	}


	public String getURL() {
		return URL;
	}


	public void setURL(String uRL) {
		URL = uRL;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public boolean isPrivate() {
		return isPrivate;
	}


	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public int getReposts() {
		return reposts;
	}


	public void setReposts(int reposts) {
		this.reposts = reposts;
	}


	public int getShares() {
		return shares;
	}


	public void setShares(int shares) {
		this.shares = shares;
	}


	public int getDownloads() {
		return downloads;
	}


	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}


	public int getTimesPlayed() {
		return timesPlayed;
	}


	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
