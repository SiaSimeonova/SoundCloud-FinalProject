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
 * Servlet implementation class audioServlet
 */
@WebServlet("/audioServlet/*")
public class audioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer url=request.getRequestURL();
		String URL=url.toString();
		URL=URL.substring(URL.lastIndexOf("/")+1);
		
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {
		  stream = response.getOutputStream();
		  File mp3 = new File(new AudioFileDAO().getPathToSongById(Integer.parseInt(URL)));
		
		  //set response headers
		  response.setContentType("audio/mpeg"); 

		  response.addHeader("Content-Disposition", "attachment; filename=" + mp3.getName());

		  response.setContentLength((int) mp3.length());

		  FileInputStream input = new FileInputStream(mp3);
		  buf = new BufferedInputStream(input);
		  int readBytes = 0;
		  //read from the file; write to the ServletOutputStream
		  while ((readBytes = buf.read()) != -1)
		    stream.write(readBytes);
		} catch (IOException | SQLException ioe) {
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
