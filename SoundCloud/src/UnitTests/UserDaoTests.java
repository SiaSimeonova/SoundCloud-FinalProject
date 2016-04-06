package UnitTests;
import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DAO.UserDAOException;
import POJO.User;

public class UserDaoTests {

	@DataProvider(name = "user")
	public static Object[][] users() {
		return new Object[][] {
				{ new User("user4", "pass3", "mail4@abv.bg") } };
	}


	@Test(dataProvider = "user")
	public static void testAddUser(User user) throws UserDAOException {
		int id = 0;
		try {
			id = DAO.UserDAO.class.newInstance().addUser(user);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("Returned " + id);
	}
	
	@Test(dependsOnMethods = {"testAddUser"}, dataProvider = "user")
	public static void getUser(User user) throws InstantiationException, IllegalAccessException, UserDAOException{
		String wantedUsername = user.getUserName();
		User wanted = DAO.UserDAO.class.newInstance().getUser(wantedUsername);
	 	assertEquals(wanted.getUserName(), user.getUserName());
	}
	
	@Test(dependsOnMethods = {"testAddUser","getUser"}, dataProvider = "user")
	public static void editUser(User user) throws UserDAOException{
		int age = 0;
		user.setAge(15);
		try {
			DAO.UserDAO.class.newInstance().editUser(user);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("Returned " + age);
	}
	
	
}
