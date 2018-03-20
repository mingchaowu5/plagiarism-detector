package edu.northeastern.cs5500.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/register")
	public ResponseEntity<User> register(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password) {
		User user = new User();
		user.setEmail(email); user.setPassword(password); user.setUsername(username);
		User createdUser = userService.addUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/login")
	public ResponseEntity<?> login(@RequestParam(value = "username") String username, 
			@RequestParam(value = "password")String password){
		User user = userService.login(username, password);
		if(user == null)
			return ResponseEntity.noContent().build();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/available")
	public ResponseEntity<?> available(@RequestParam(value = "username") String username, @RequestParam(value = "useravail")boolean flag){
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
