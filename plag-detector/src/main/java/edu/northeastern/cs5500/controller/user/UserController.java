package edu.northeastern.cs5500.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5500.models.Person.Professor;
import edu.northeastern.cs5500.models.Person.Student;
import edu.northeastern.cs5500.models.Person.User;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/register")
	public ResponseEntity<User> register(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password, @RequestParam(value = "firstname")String firstname, 
			@RequestParam(value = "lastname")String lastname, @RequestParam(value = "type")String type, @RequestParam(value = "uid")String uid) {
	
	User createdUser = null;	
	if(type.equals("Professor")) {
		Professor user = new Professor();
		user.setEmail(email); user.setPassword(password); user.setUsername(username);
		user.setFirstName(firstname); user.setLastName(lastname); user.setOfficeLocation(uid);
		createdUser = userService.addUser(user);
	} else {
		Student user = new Student();
		Integer i = new Integer(uid);
		user.setEmail(email); user.setPassword(password); user.setUsername(username);
		user.setFirstName(firstname); user.setLastName(lastname); user.setUniversityId(i);
		createdUser = userService.addUser(user);
	}
	if(createdUser == null)
		return ResponseEntity.noContent().build();
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/login")
	public ResponseEntity<?> login(@RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password, @RequestParam(value = "type")String type){
		User user = userService.login(username, password, type);
		if(user == null)
			return ResponseEntity.noContent().build();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/available")
	public ResponseEntity<?> available(@RequestParam(value = "username") String username, @RequestParam(value = "useravail")boolean flag){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
