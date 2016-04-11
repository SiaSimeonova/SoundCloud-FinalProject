package UnitTests;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DAO.UserDAOException;
import POJO.User;

public class UserDaoTests {

	@DataProvider(name = "user")
	public static Object[][] users() {
		return new Object[][] { { new User("user5", "pass7", "mail5@abv.bg") },
				{ new User("user6", "pass8", "mail6@abv.bg") }, { new User("user7", "pass8", "mail7@abv.bg") },
				{ new User("user8", "pass8", "mail8@abv.bg") }, { new User("user9", "pass8", "mail9@abv.bg") },
				{ new User("user10", "pass8", "mail10@abv.bg") }, { new User("user11", "pass8", "mail11@abv.bg") },
				{ new User("user12", "pass8", "mail12@abv.bg") }, { new User("user13", "pass8", "mail13@abv.bg") },
				{ new User("user14", "pass8", "mail14@abv.bg") }, { new User("user15", "pass9", "mail15@abv.bg") } };
	}

	@DataProvider(name = "followers")
	public static Object[][] followers() {
		return new Object[][] { 
				{ new User("user6", "pass8", "mail6@abv.bg") , new User("user10", "pass7", "mail5@abv.bg") },
				{ new User("user7", "pass8", "mail6@abv.bg") , new User("user9", "pass7", "mail5@abv.bg") },
				{ new User("user6", "pass8", "mail6@abv.bg") , new User("user6", "pass7", "mail5@abv.bg") },
				{ new User("user8", "pass8", "mail6@abv.bg") , new User("user7", "pass7", "mail5@abv.bg") },
				{ new User("user5", "pass8", "mail6@abv.bg") , new User("user7", "pass7", "mail5@abv.bg") }};
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
		System.out.println("Aded " + id);
	}

	@Test(dependsOnMethods = { "testAddUser" }, dataProvider = "user", enabled = false)
	public static void getUser(User user) throws InstantiationException, IllegalAccessException, UserDAOException {
		String wantedUsername = user.getUserName();
		User wanted = DAO.UserDAO.class.newInstance().getUser(wantedUsername);
		assertEquals(wanted.getUserName(), user.getUserName());
	}

	@Test(dependsOnMethods = { "testAddUser", "getUser" }, dataProvider = "user", enabled = false)
	public static void editUser(User user) throws UserDAOException {
		int age = 0;
		user.setAge(15);
		age = user.getAge();
		try {
			DAO.UserDAO.class.newInstance().editUser(user);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("Edited " + age);
	}

	@Test(dataProvider = "followers", enabled = false)
	public static void addFollower(User userToFollow, User follower) throws UserDAOException {
		try {
			int result = DAO.UserDAO.class.newInstance().followUser(userToFollow, follower);
			System.out.println(result);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test (dataProvider = "user", enabled = false)
	public static void TestCountFollowers(User followed) throws UserDAOException {
		try {
			int followers = DAO.UserDAO.class.newInstance().getFollowers(followed);
			System.out.println(followers);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test (dataProvider = "user", enabled = false)
	public static void TestCountFollowing(User follower) throws UserDAOException {
		try {
			int followers = DAO.UserDAO.class.newInstance().getFollowing(follower);
			System.out.println(followers);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "testAddUser", "getUser", "editUser" }, dataProvider = "user", enabled = false)
	public static void deleteUser(User user) throws UserDAOException {
		try {
			String userName = user.getUserName();
			String password = user.getPass();
			DAO.UserDAO.class.newInstance().deteteUser(user);
			assertEquals(DAO.UserDAO.class.newInstance().isThereSuchUser(userName, password), false);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
