package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.IUserDAO;
import DAO.UserDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String user=request.getParameter("username");
		String pass=request.getParameter("password");
		boolean isThereSuchUser=false;
		IUserDAO dao=new UserDAO();
		try {
			isThereSuchUser=dao.isThereSuchUser(user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isThereSuchUser){
			HttpSession session=request.getSession();
			try {
				session.setAttribute("user", dao.getUser(user));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("./profile.jsp");
		}
		else{
			response.getWriter().println("Nema takuv");
		}
	}

}
