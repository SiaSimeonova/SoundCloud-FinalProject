package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.AudioDAOException;
import DAO.AudioFileDAO;
import DAO.UserDAO;
import DAO.UserDAOException;
import POJO.AudioFile;
import POJO.User;
import UnitTests.UserDaoTests;



@WebServlet("/Upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Upload extends HttpServlet {

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		Part pic = request.getPart("pic"); // Retrieves <input type="file" name="pic">
	    String fileName = filePart.getSubmittedFileName();
	    InputStream fileContent = filePart.getInputStream();
	    InputStream picContent=pic.getInputStream();
	    String folder=((User)request.getSession().getAttribute("user")).getUserName();
	  
	    File uploads = new File("E:\\STORAGE\\"+folder+"\\uploads");
	   
	   
	    File picFile= new File(uploads,request.getParameter("trackName")+".jpg");
	    File file = new File(uploads, request.getParameter("trackName")+".mp3");
	    picFile.delete();
	    file.delete();
	    uploads.mkdirs();
	    int userID=0;
		try {
			userID = new UserDAO().getUserId(folder);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try (InputStream input = filePart.getInputStream()) {
	        Files.copy(fileContent, file.toPath());
	    }
	    try(InputStream picInput = pic.getInputStream()){
	    	Files.copy(picContent, picFile.toPath());
	    }
	  // System.out.println(file.getAbsolutePath());
    try {
    	AudioFile fileToUpload=new AudioFile( file.getAbsolutePath(), request.getParameter("trackName"),userID);
    	fileToUpload.setAutor(request.getParameter("artistName"));
    	fileToUpload.setCategory(request.getParameter("category"));
    	fileToUpload.setDescription(request.getParameter("description"));
    	fileToUpload.setPicture(picFile.getAbsolutePath());
		int uploadId=new AudioFileDAO().addAudio(fileToUpload);
		User currentUser=(User)request.getSession().getAttribute("user");
		currentUser.addUpload(uploadId);
		request.getSession().setAttribute("user", currentUser);
	} catch (AudioDAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//    try {
//		request.getSession().setAttribute("uploads", new AudioFileDAO().getUploads(folder));
//	} catch (AudioDAOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
    response.sendRedirect("./Collection.jsp");
		}
	

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	
}