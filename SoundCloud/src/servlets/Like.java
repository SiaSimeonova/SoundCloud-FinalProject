package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;

/**
 * Servlet implementation class Like
 */
@WebServlet("/Like/Collections/*")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url=request.getRequestURI();
		String URL=url.toString();
		System.err.println(URL);

		URL=URL.substring(URL.lastIndexOf("/")+1);

		
		AudioFileDAO dao = new AudioFileDAO();
		System.err.println(request.getRequestURL());
		dao.like((String)request.getParameter("songname"));
		response.sendRedirect("./Collections.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
