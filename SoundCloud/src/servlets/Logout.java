package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Cache-Control","no-cache,post-check=0,pre-check=0");
		response.setHeader("Pragma","no-cache");   response.setDateHeader("Expires", 0);
		response.setDateHeader("max-age", 0);
		response.setIntHeader ("Expires", -1);
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect("./");
	}
}
