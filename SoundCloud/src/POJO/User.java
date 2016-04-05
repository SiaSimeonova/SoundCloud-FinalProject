package POJO;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private String pass;
	private String firstName;
	private String surname;
	private int age;
	private String gender;
	private String mail;
	private String picPath;
	private List<AudioFile> myAudios;
	private List<Playlist> playlists;

	public User(String userName, String pass, String firstName, String surname, int age, String gender, String mail,
			String picPath) {
		this(userName, pass, mail);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setAge(age);
		this.setGender(gender);
		this.setPicPath(picPath);
	}

	public User(String userName, String pass, String mail) {
		this.userName = userName;
		this.setPass(pass);
		this.setMail(mail);
		this.setFirstName("empty");
		this.setSurname("empty");
		this.setAge(0);
		this.setGender("empty");
		this.setPicPath("empty");
		myAudios = new ArrayList<AudioFile>();
		playlists = new ArrayList<Playlist>();
	}

	public String getUserName() {
		return userName;
	}

	public String getPass() {
		return pass;
	}

	private void setPass(String pass) {
		if (pass != null && !pass.equals("")) {
			this.pass = pass;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName != null && !firstName.equals("")) {
			this.firstName = firstName;
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname != null && !surname.equals("")) {
			this.surname = surname;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age > 0) {
			this.age = age;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
			this.gender = gender;
		} else {
			this.gender = "undefined";
		}
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<AudioFile> getMyAudios() {
		return myAudios;
	}

	public void setMyAudios(List<AudioFile> myAudios) {
		this.myAudios = myAudios;
	}

}
