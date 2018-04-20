package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.northeastern.cs5500.models.assignment.Assignment;
import edu.northeastern.cs5500.models.person.Professor;
import edu.northeastern.cs5500.models.person.Student;
import edu.northeastern.cs5500.models.person.User;

public class UserTest {
	
	private User user = new User();
	private User u2 = new User(3, "Harsha", "Rahul", "h@h.com", "harsha", "harsha");
	
	/**
	 * Test Name
	 */
	@Test
	public void testId() {
		user.setId(3);
		assertEquals(3, user.getId());
		assertEquals(user.getId(), u2.getId());
		
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testName() {
		user.setFirstName("Varun");
		user.setLastName("Nandu");
		assertEquals(user.getFirstName()+user.getLastName(), "VarunNandu");
	}
	
	/**
	 * Test Id
	 */
	@Test
	public void testEmail() {
		user.setEmail("h@h.com");
		assertEquals(user.getEmail(), "h@h.com");
	}
	
	/**
	 * Test Id
	 */
	@Test
	public void testCredentials() {
		user.setUsername("varun");
		user.setPassword("varun");
		assertEquals("varun", user.getUsername());
		assertEquals("varun", user.getPassword());
	}
	
	/**
	 * Test Id
	 */
	@Test
	public void testType() {
		user.setType(1);
		assertEquals(1, user.getType());
	}
	
	Student stu = new Student();
	Student stu2 = new Student(33, "Google", "Facebook", "g@f.com", "google", "facebook", 25);
	
	@Test
	public void testStudent() {
		stu.setUniversityId(25);
		assertEquals(stu.getUniversityId(), 25);
		assertEquals(stu.getUniversityId(), stu2.getUniversityId());
	}
	
	Professor pro = new Professor();
	Professor pro2 = new Professor(33, "Google", "Facebook", "g@f.com", "google", "facebook", "WVH");
	
	@Test
	public void testProfessor() {
		pro.setOfficeLocation("WVH");
		assertEquals(pro.getOfficeLocation(), "WVH");
		assertEquals(pro.getOfficeLocation(), pro2.getOfficeLocation());
	}

}
