package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Servlet implementation class SendAudioFile
 */
@WebServlet("/SendAudioFile")
public class sendAudioFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		    String fileName = (String) request.getParameter("file");
		    if (fileName == null || fileName.equals(""))
		      throw new ServletException(
		          "Invalid or non-existent file parameter.");

		      if (fileName.indexOf(".mp3") == -1)
		      fileName = fileName + ".mp3";

		    String mp3Dir = getServletContext().getInitParameter("mp3-dir");
		    if (mp3Dir == null || mp3Dir.equals(""))
		      throw new ServletException(
		          "Invalid or non-existent mp3Dir context-param.");

		    ServletOutputStream stream = null;
		    BufferedInputStream buf = null;
		    try {

		      stream = response.getOutputStream();
		      File mp3 = new File(mp3Dir + "/" + fileName);

		      //set response headers
		      response.setContentType("audio/mpeg");

		      response.addHeader("Content-Disposition", "attachment; filename="
		          + fileName);

		      response.setContentLength((int) mp3.length());

		      FileInputStream input = new FileInputStream(mp3);
		      buf = new BufferedInputStream(input);
		      int readBytes = 0;
		      //read from the file; write to the ServletOutputStream
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

		  public void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		    doGet(request, response);
		  }
}
