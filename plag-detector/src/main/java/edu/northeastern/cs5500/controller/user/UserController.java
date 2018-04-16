package edu.northeastern.cs5500.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.person.User;

/**
 * User Controller Class, 
 * handle user related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * Get all the users in the dashboard
	 * @return List: of all the users
	 */
	@GetMapping(value = "all")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = this.userService.getAllUsers();
		if(users == null)
			return ResponseEntity.notFound().build();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	/**
	 * Register new user
	 * @param email:	of the user
	 * @param username:	of the user
	 * @param password:	of the user
	 * @param firstname:	of the user
	 * @param lastname:	of the user
	 * @param type:	of the user, 0 => Student, 1 => Faculty
	 * @return	boolean: true iff and only if the user is successfully registered.
	 */
	@GetMapping(value = "/register")
	public ResponseEntity<User> register(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password, @RequestParam(value = "firstname")String firstname, 
			@RequestParam(value = "lastname")String lastname, @RequestParam(value = "type") int type) {
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		user.setUsername(username);
		user.setType(type);
		User u = this.userService.addUser(user);	
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
	
	/**
	 * Register new user
	 * @param email:	of the user
	 * @param username:	of the user
	 * @param password:	of the user
	 * @param firstname:	of the user
	 * @param lastname:	of the user
	 * @param type:	of the user, 0 => Student, 1 => Faculty
	 * @return	boolean: true iff and only if the user is successfully registered.
	 */
	@GetMapping(value = "/update")
	public ResponseEntity<User> update(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password, @RequestParam(value = "firstname")String firstname, 
			@RequestParam(value = "lastname")String lastname, @RequestParam(value = "type") int type, @RequestParam(value = "id")int id) {
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		user.setUsername(username);
		user.setType(type);
		user.setId(id);
		User u = this.userService.editUser(user);	
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
	
	/**
	 * Get the details for the user from the database
	 * @param username:	of the user
	 * @param password:	of the user
	 * @param type:	of the user
	 * @return	User: return null if the user is not present or else returns the user object
	 */
	@GetMapping(value = "/login")
	public ResponseEntity<User> login(@RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password, @RequestParam(value = "type") int type){
		User user = null;
		user = this.userService.login(username, password, type);
		if(user == null)
			return ResponseEntity.notFound().build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/**
	 * Send Mail to the student
	 * @param username:	of the user
	 * @param password:	of the user
	 * @param type:	of the user
	 * @return	User: return null if the user is not present or else returns the user object
	 */
	@GetMapping(value = "/mail")
	public ResponseEntity<Boolean> sendMail(@RequestParam(value = "student_id") int studentId, 
			@RequestParam(value = "professor_id")int profId, @RequestParam(value = "assignment_id") int assignmentId){
		this.userService.sendMail(studentId, profId, assignmentId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	
	/**
	 * Delete the user
	 * @param	id: of the user to be deleted
	 * 			type: of the user who is being deleted
	 * @return boolean: true iff and only if the user is successfully deleted
	 */
	@GetMapping(value = "/delete")
	public ResponseEntity<Boolean> delete(@RequestParam(value = "id") int id, @RequestParam(value = "type") int type){
		boolean flag = this.userService.delete(id, type);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	
	/**
	 * Checks if a username is available or not
	 * @param username
	 * @return	boolean: true iff and only if the username is already present in the database
	 */
	@GetMapping(value = "/available")
	public ResponseEntity<Boolean> available(@RequestParam(value = "username") String username){
		boolean flag = this.userService.isUsernamePresent(username);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Checks if a username is available or not
	 * @param username
	 * @return	boolean: true iff and only if the username is already present in the database
	 */
	@GetMapping(value = "/change")
	public ResponseEntity<Boolean> change(@RequestParam(value = "id") int id, @RequestParam(value = "type") int type){
		boolean flag = this.userService.changeRole(id, type);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Get all users who are professor
	 * @return	List: of professors 
	 */
	@GetMapping(value = "/professor")
	public ResponseEntity<List<User>> getAllProfessors(){
		int type = 1;	//professor-type
		List<User> users = this.userService.getUsersOfType(type);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	/**
	 * Get all users who are students
	 * @return	List: of students
	 */
	@GetMapping(value = "/student")
	public ResponseEntity<List<User>> getAllStudents(){
		int type = 0;	//student-type
		List<User> users = this.userService.getUsersOfType(type);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	/**
	 * Get all users who are professor take a particular course
	 * @param	course_id: is an integer which represents a course id
	 * @return	List: of professors of a course
	 */
	@GetMapping(value = "/professor/course")
	public ResponseEntity<List<User>> getProfessorsOfCourse(@RequestParam("course_id") int id){
		List<User> users = this.userService.getProfessorsOfCourse(id);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	/**
	 * Get all users who are student in a particular course
	 * @param	course_id: is an integer which represents a course id
	 * @return	List: of students of a course
	 */
	@GetMapping(value = "/student/course")
	public ResponseEntity<List<User>> getStudentInCourse(@RequestParam("course_id") int id){
		List<User> users = this.userService.getStudentsInCourse(id);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	/**
	 * Get all users who are students and have submitted a specific assignment
	 * @param	assignment_id: is an integer which represents a assignment id
	 * @return	List: of students
	 */
	@GetMapping(value = "/student/assignment")
	public ResponseEntity<List<User>> getStudentOfAssignment(@RequestParam("assignment_id") int id){
		List<User> users = this.userService.getStudentOfAssignment(id);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
