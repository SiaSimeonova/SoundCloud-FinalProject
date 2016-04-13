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
@WebServlet("/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AudioFileDAO dao = new AudioFileDAO();
		dao.like((String)request.getSession(false).getAttribute("name"), Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("./Collections");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
