package servlets;

import javax.servlet.http.HttpServletRequest;

import DAO.UserDAOException;

public class CommonFunctions {

	public static void isLoggedIn(HttpServletRequest request) throws DAO.UserDAOException{
		if(request.getSession(false)==null || request.getSession(false).getAttribute("username")==null){
			throw new DAO.UserDAOException("You are not logged in!");
		}
	}
}