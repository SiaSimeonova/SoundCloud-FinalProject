package servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AudioFileDAO;
import POJO.User;

/**
 * Servlet implementation class imageServlet
 */
@WebServlet("/profilePicServlet")
public class profilePicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {File image =null;
			stream = response.getOutputStream();
			if (((User) (request.getSession().getAttribute("user"))).getPicPath() != null) {
				image = new File(((User) (request.getSession().getAttribute("user"))).getPicPath());
			}
			else{
				image= new File("C:\\Users\\Slavozar\\Desktop\\LAST WORKING VERSION\\SoundCloud3\\WebContent\\defProfile.jpg");
			}
			// System.out.println(new
			// AudioFileDAO().getPicPathById(Integer.parseInt(URL)));

			// set response headers
			response.setContentType("image");

			response.addHeader("Content-Disposition", "attachment; filename=" + image.getName());

			response.setContentLength((int) image.length());

			FileInputStream input = new FileInputStream(image);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1)
				stream.write(readBytes);
		} catch (IOException ioe) {
			throw new ServletException(ioe.getMessage());
		} finally {
			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
