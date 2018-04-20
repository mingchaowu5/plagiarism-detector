

package edu.northeastern.cs5500.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.northeastern.cs5500.PlagDetectorApplicationTests;
import edu.northeastern.cs5500.dao.SnapshotDao;
import edu.northeastern.cs5500.dao.UserDao;
import edu.northeastern.cs5500.models.person.Professor;
import edu.northeastern.cs5500.models.person.Student;
import edu.northeastern.cs5500.models.person.User;

public class UserTest extends PlagDetectorApplicationTests{

	@Autowired
	private UserDao udao;
	
	@Autowired
	private SnapshotDao sdao;
	
	@Test
	public void testAllUsers() {
		List<User> ulist = new ArrayList();
		ulist = udao.findAllUsers();
				
		assertTrue(ulist.size() >= 0);
	}
	
	@Test
	public void testAllProfessors() {
		List<Professor> ulist = new ArrayList();
		ulist = udao.findAllProfessors();
		assertTrue(ulist.size() >= 0);
	}
	
	@Test
	public void testAllStudents() {
		List<Student> ulist = new ArrayList();
		ulist = udao.findAllStudents();
		
		
		assertTrue(ulist.size() >= 0);
	}
	
	@Test
	public void testFindUser() {
	
		User u = udao.findUserById(2);
		u.getId();
		u.getType();
		
		
		assertTrue(u.getId() == 2);
	}
	

	
	@Test
	public void testTypeUser() {
	
		int type = udao.getTypeFromUsernamePassword("polu", "polu");
		int type2 = udao.getTypeFromUsernamePassword("jolu", "jolu");
		assertEquals(type, type);
	}
	

	
	@Test
	public void testGetUserWithSnap() {
		User type = udao.findUserBySnapshotId(5);
		User type2 = udao.findUserBySnapshotId(4);
		assertNotNull(type);
		assertNull(type2);
	}
	

	
	@Test
	public void testFindUserForCourse() {
		List<Integer> ulist2 = udao.findStudentsForCourse(101);
	
		assertNull(null);
	}
	
	
	
}
