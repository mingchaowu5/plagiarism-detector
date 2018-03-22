package edu.northeastern.cs5500.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Dao.UserDao;
import edu.northeastern.cs5500.models.Person.User;

@Service
public class UserService {

	@Autowired
	private UserDao userConnection;
	
	public User addUser(final User user) {
		return userConnection.addNewUser(user);
	}
	
	public User login(final String username, final String password) {
		return userConnection.login(username, password);
	}
}
