package edu.northeastern.cs5500.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.database.UserConnection;

@Service
public class UserService {

	@Autowired
	private UserConnection userConnection;
	
	public User addUser(final User user) {
		return userConnection.addNewUser(user);
	}
	
	public User login(final String username, final String password) {
		return userConnection.login(username, password);
	}
}
