package edu.northeastern.cs5500.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.dao.AssignmentDao;
import edu.northeastern.cs5500.dao.UserDao;
import edu.northeastern.cs5500.mail.MailClient;
import edu.northeastern.cs5500.models.assignment.Assignment;
import edu.northeastern.cs5500.models.person.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AssignmentDao assignmentDao;
	
	@Autowired
	private MailClient mail;
	
	/**
	 * Edit an user
	 * @param user: contains the details of the user to be edited
	 * @return	boolean: returns true iff and only if the user is successfully
	 * 					 edited in the database
	 */
	public User editUser(User user) {
		try {
			User oldUser = this.userDao.findUserById(user.getId());
			if(oldUser.getId() > 0) {
				if(oldUser.getId() != user.getId()) {
					//this.changeRole(user.getId(), oldUser.getType());
				}
			}
			userDao.updateUser(user);
			return user;
		}catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Add a new user
	 * @param user: contains the details of the user to be added
	 * @return	boolean: returns true iff and only if the user is successfully
	 * 					 added to the database
	 */
	public User addUser(User user) {
		try {
			return userDao.addNewUser(user);
			
		}catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Get all the users of the dashboard
	 * @return List: of all the users
	 */
	public List<User> getAllUsers(){
		return this.userDao.findAllUsers();
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
	 * Change role of the user
	 */
	public boolean changeRole(int id, int type) {
		if(type == 0) {
			this.userDao.deleteStudent(id);
			return this.userDao.insertProfessor(id) == 1?true:false;
		}else {
			this.userDao.deleteProfessor(id);
			return this.userDao.insertStudent(id) == 1?true:false;
		}
	}
	
	/**
	 * Get details of an user
	 * @param username:	of the user
	 * @param password:	of the user
	 * @param type:	of the user
	 * @return	User: the details of the user which is logging in the system.
	 */
	public User login(final String username, final String password, final int type) {
		return userDao.login(username, password, type);
	}
	
	/**
	 * Get list of users of a specific user type
	 * @param type:	is an integer which represents whether the user is professor or student
	 * @return	List:	of users of the specified type
	 */
	public List<User> getUsersOfType(int type){
		return this.userDao.findUserByType(type);
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
	
	/**
	 * Get user id
	 * @param snapId
	 * @return
	 */
	public User getUserId(int snapId) {
		return this.userDao.findUserBySnapshotId(snapId);
	}
	
	/**
	 * Send mail to the student
	 * @param sId
	 * @param pId
	 * @param aId
	 */
	public void sendMail(int sId1, int sId2, int pId, int aId) {
		User p = this.userDao.findUserById(pId);
		User s = this.userDao.findUserBySubmissionId(sId1);
		Assignment a = this.assignmentDao.findAssignmentById(aId);
		String name = (a == null)?"":a.getName();
		String text = "Hello "  +s.getFirstName() + ",\n" + "Plagiarism has been detected in your assignment, " + name
				 + "\n. Please go and meet the professor, " + p.getFirstName() + " " + p.getLastName();
		mail.sendSimpleMessage(s.getEmail(), "Plagiarism detected in your submission", text);
		s = this.userDao.findUserBySubmissionId(sId2);
		text = "Hello "  +s.getFirstName() + ",\n" + "Plagiarism has been detected in your assignment, " + name
				 + "\n. Please go and meet the professor, " + p.getFirstName() + " " + p.getLastName();
		mail.sendSimpleMessage(s.getEmail(), "Plagiarism detected in your submission", text);		
	}
}
