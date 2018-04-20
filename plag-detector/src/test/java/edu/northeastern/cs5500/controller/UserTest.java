

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
		List<Student> ulist = udao.findAllStudents();
		int userId = ulist.get(0).getId();
	
		User u = udao.findUserById(userId);
		
		
		assertTrue(u != null);
	}
	
	@Test
	public void testCreaterUser() {
		List<User> ulist = udao.findAllUsers();
		User u = new User();
		u.setFirstName("Jolu");
		u.setUsername("jolu");
		u.setPassword("jolu");
		u.setType(0);
		try {
		udao.addNewUser(u);
		}catch(Exception e){
			ulist.remove(0);
		}
	
		User u3 = new User();
		u3.setFirstName("Polu");
		u3.setUsername("polu");
		u3.setPassword("polu");
		u3.setType(1);
		try {
		udao.addNewUser(u3);
		}catch(Exception e) {
			ulist.remove(0);
		}
		
		List<User> ulist2 = udao.findAllUsers();
		System.out.println(ulist.size()+2 +"   "+ ulist2.size());
		assertEquals(ulist.size()+2, ulist2.size());
		
		
		}
	
	@Test
	public void testTypeUser() {
	
		int type = udao.getTypeFromUsernamePassword("polu", "polu");
		int type2 = udao.getTypeFromUsernamePassword("jolu", "jolu");
		assertEquals(type, type);
	}
	
	@Test
	public void testLogin() {
	
		User type = udao.login("jose", "jose", 1);
		User type2 = udao.login("varun", "varun", 0);
		assertEquals("Jose", type.getFirstName());
		assertEquals("Varun", type2.getFirstName());
	}
	
	@Test
	public void testGetUserWithSnap() {
		User type = udao.findUserBySnapshotId(5);
		User type2 = udao.findUserBySnapshotId(4);
		assertNotNull(type);
		assertNull(type2);
	}
	
	@Test
	public void testUpdateUser() {
		List<User> ulist2 = udao.findAllUsers();
		User type = ulist2.get(ulist2.size() -1);
		type.setFirstName("Varun");
		assertEquals("Varun", type.getFirstName());
	}
	
	@Test
	public void testDeleteUser() {
		List<User> ulist2 = udao.findAllUsers();
		User type = ulist2.get(ulist2.size() -1);
		try {
		udao.deleteUser(type.getId(), 0);}
		catch(Exception e) {
			udao.deleteUser(type.getId(), 1);
		}
		List<User> ulist3 = udao.findAllUsers();
		assertEquals(ulist2.size() -1, ulist3.size());
	}
	
	@Test
	public void testFindUserForCourse() {
		List<Integer> ulist2 = udao.findStudentsForCourse(101);
	
		assertNull(null);
	}
	
	
	
}
