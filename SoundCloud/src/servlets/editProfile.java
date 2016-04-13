package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.UserDAO;
import DAO.UserDAOException;
import POJO.User;

/**
 * Servlet implementation class editProfile
 */
@WebServlet("/editProfile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class editProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User userToBeUpdated = null;
		try {
			userToBeUpdated = new UserDAO().getUser(((User) (request.getSession().getAttribute("user"))).getUserName());
		} catch (UserDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Part pic = request.getPart("pic");
		InputStream picContent = pic.getInputStream();
		String folder = ((User) request.getSession().getAttribute("user")).getUserName();
		File uploads = new File("E:\\STORAGE\\" + folder);

		File picFile = new File(uploads, "profile.jpg");
		picFile.delete();
		uploads.mkdirs();
		try (InputStream picInput = pic.getInputStream()) {
			Files.copy(picContent, picFile.toPath());
		}
		if (userToBeUpdated != null) {
			userToBeUpdated.setFirstName(request.getParameter("firstname"));
			userToBeUpdated.setSurname(request.getParameter("lastname"));
			if (!request.getParameter("age").equals("")) {
				userToBeUpdated.setAge(Integer.parseInt(request.getParameter("age")));
			}
			userToBeUpdated.setMail(request.getParameter("email"));
			userToBeUpdated.setGender(request.getParameter("gender"));
			userToBeUpdated.setPicPath(picFile.getAbsolutePath());
		}
		try {
			new UserDAO().editUser(userToBeUpdated);
		} catch (UserDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./profile.jsp");
	}
}
