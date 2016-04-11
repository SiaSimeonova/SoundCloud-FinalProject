package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DAO.UserDAOException;
import POJO.User;

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
		try {
			isThereSuchUser=new UserDAO().isThereSuchUser(user, pass);
		} catch (UserDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(isThereSuchUser){
			HttpSession session=request.getSession();
			User userToBeAdded=null;
			try {
				
				userToBeAdded = new UserDAO().getUser(user);
			} catch (UserDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("user", userToBeAdded);
			response.sendRedirect("./header.jsp");
		}
		else{
			request.setAttribute("error","There is no such user");
			request.getRequestDispatcher("./signIn.jsp").forward(request, response);
		}
	}

}
