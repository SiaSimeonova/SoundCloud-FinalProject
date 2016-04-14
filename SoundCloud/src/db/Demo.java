package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AudioDAOException;
import DAO.AudioFileDAO;
import DAO.UserDAO;
import DAO.UserDAOException;
import POJO.AudioFile;

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

	public static void main(String[] args) throws SQLException, UserDAOException, AudioDAOException {
		
//		Connection con=DBConnection.getInstance().getCon();
//		PreparedStatement ps=con.prepareStatement("insert into users values(null,'ico','parola','slavozar',"
//				+ "'vargulev',24,null,'ico@abv.bg',null)");
//		ps.execute();
		
		//int user=new UserDAO().addUser(new POJO.User("ibrik", "13", "ICKO", "STOICHKOV", 20, "male", "fan@abv.bg"));
		//System.out.println(new UserDAO().isThereSuchUser("ibrik", "13"));
		
//		System.out.println(new UserDAO().getUserId("user1"));
		AudioFileDAO dao=new AudioFileDAO(); 
		//System.out.println(dao.getPathToSongById(dao.getRandomIdFromDb()));
		//System.out.println(dao.getPicPathById(dao.getRandomIdFromDb()));
		//System.out.println(new UserDAO().getUser("slavozar").getPicPath());
//		
//		
//		List<AudioFile> list=new AudioFileDAO().getUploads("slavozar");
//		
//		for(AudioFile song: list){
//			System.out.println(song.getId());
//		}
//		List<AudioFile> list1=new AudioFileDAO().getUploads("slavozar");
//		System.out.println("Vtori pat");
//		for(AudioFile song: list1){
//			System.out.println(song.getId());
//		}
		
		dao.like("Bounce");
	}

}
