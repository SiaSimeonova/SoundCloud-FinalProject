package POJO;

import DAO.IUserDAO;

public class User {
	private String userName;
	private String pass;
	private String firstName;
	private String surname;
	private int age;
	private String gender;
	private String mail;
	private String picPath;
	
	

	public User(String userName, String pass, String firstName, String surname, int age, String gender, String mail) {
		
		this.setUserName(userName);
		this.setPass(pass);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setAge(age);
		this.setGender(gender);
		this.setMail(mail);
	}

	public String getUserName() {
		return userName;
	}

	private void setUserName(String user) {
		if (user != null && !user.equals("")) {
			this.userName = user;
		}
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
		if (gender.equalsIgnoreCase("male") || (gender.equalsIgnoreCase("female"))) {
			this.gender = gender;
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

	
}
