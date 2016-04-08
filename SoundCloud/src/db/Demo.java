package db;

import java.sql.SQLException;

import DAO.AudioFileDAO;
import DAO.UserDAO;

//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import org.apache.tomcat.jni.User;
//
//import DAO.IUserDAO;
//import DAO.UserDAO;
//
//import java.sql.Connection;

public class Demo {

	public static void main(String[] args) throws SQLException {
		
//		Connection con=DBConnection.getInstance().getCon();
//		PreparedStatement ps=con.prepareStatement("insert into users values(null,'ico','parola','slavozar',"
//				+ "'vargulev',24,null,'ico@abv.bg',null)");
//		ps.execute();
		
		//int user=new UserDAO().addUser(new POJO.User("ibrik", "13", "ICKO", "STOICHKOV", 20, "male", "fan@abv.bg"));
		//System.out.println(new UserDAO().isThereSuchUser("ibrik", "13"));
		
//		System.out.println(new UserDAO().getUserId("user1"));
		AudioFileDAO dao=new AudioFileDAO(); 
		//System.out.println(dao.getPathToSongById(dao.getRandomIdFromDb()));
		System.out.println(dao.getPicPathById(dao.getRandomIdFromDb()));

	}

}
