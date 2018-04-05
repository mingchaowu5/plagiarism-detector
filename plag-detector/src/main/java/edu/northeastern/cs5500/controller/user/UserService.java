package edu.northeastern.cs5500.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.dao.UserDao;
import edu.northeastern.cs5500.models.person.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * Add a new user
	 * @param user: contains the details of the user to be added
	 * @return	boolean: returns true iff and only if the user is successfully
	 * 					 added to the database
	 */
	public boolean addUser(User user) {
		try {
			
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if user-name already taken
	 * @param username:	to be checked in the database
	 * @return	boolean:	true iff and only if the user-name is not already in use.
	 */
	public boolean isUsernamePresent(String username) {
		return false;
	}
	
	/**
	 * Delete the user
	 * @param	id: of the user to be deleted
	 * 			type: of the user who is being deleted
	 * @return boolean: true iff and only if the user is successfully deleted
	 */
	public boolean delete(int id, int type) {
		return userDao.deleteUser(id, type) == 1 ? true : false;
	}
	
	/**
	 * Get details of an user
	 * @param username:	of the user
	 * @param password:	of the user
	 * @param type:	of the user
	 * @return	User: the details of the user which is logging in the system.
	 */
	public User login(final String username, final String password, final int type) {
		User user = userDao.login(username, password, type);
		if(user == null){
			return new User();
		}
		return user;
	}
	
	/**
	 * Get list of users of a specific user type
	 * @param type:	is an integer which represents whether the user is professor or student
	 * @return	List:	of users of the specified type
	 */
	public List<User> getUsersOfType(int type){
		return null;
	}
	
	/**
	 * Get a list of professors who take a specific course
	 * @param id: represents a course
	 * @return	List: is a list of professors who take a course
	 */
	public List<User> getProfessorsOfCourse(int id){
		return null;
	}
	
	/**
	 * Get a list of students who take a specific course
	 * @param id: represents a course
	 * @return	List: is a list of students who take a course
	 */
	public List<User> getStudentsInCourse(int id){
		return null;
	}
	
	/**
	 * Get a list of students who have submitted a specific assignment
	 * @param id: represents an assignment
	 * @return	List: is a list of students
	 */
	public List<User> getStudentOfAssignment(int id){
		return null;
	}
}
