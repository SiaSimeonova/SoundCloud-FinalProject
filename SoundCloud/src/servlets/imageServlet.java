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

/**
 * Servlet implementation class imageServlet
 */
@WebServlet("/imageServlet/*")
public class imageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer url=request.getRequestURL();
		String URL=url.toString();
		System.out.println(URL);

		URL=URL.substring(URL.lastIndexOf("/")+1);
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {
		  stream = response.getOutputStream();
		  File image = new File(new AudioFileDAO().getPicPathById(Integer.parseInt(URL)));
		  System.out.println(new AudioFileDAO().getPicPathById(Integer.parseInt(URL)));
		 // System.out.println(new AudioFileDAO().getPicPathById(Integer.parseInt(URL)));
		  
		  //set response headers
		  response.setContentType("image"); 

		  response.addHeader("Content-Disposition", "attachment; filename=" + image.getName());

		  response.setContentLength((int) image.length());

		  FileInputStream input = new FileInputStream(image);
		  buf = new BufferedInputStream(input);
		  int readBytes = 0;
		  //read from the file; write to the ServletOutputStream
		  while ((readBytes = buf.read()) != -1)
		    stream.write(readBytes);
		} catch (IOException | SQLException ioe) {
			ioe.printStackTrace();
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
