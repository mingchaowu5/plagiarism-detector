package edu.northeastern.cs5500.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import edu.northeastern.cs5500.PlagDetectorApplicationTests;
import edu.northeastern.cs5500.dao.AssignmentDao;
import edu.northeastern.cs5500.models.assignment.Assignment;


public class AssignmentTest extends PlagDetectorApplicationTests{

	@Autowired
	private AssignmentDao assignmentDao;
	
	
	
	@Before
	public void setup() {

	}
	
	/**
	 * Test the end-point for all assignments
	 * @throws Exception
	 */
	@Test
	public void testAllAssignments() throws Exception {
		List<Assignment> list = assignmentDao.findAllAssignments();
		assertTrue(list.size() > 0);
	}
	
	
	/**
	 * Test the end-point for assignments for a id
	 * @throws Exception
	 */
	@Test
	public void testAssignmentsById() throws Exception {
		List<Assignment> list = assignmentDao.findAllAssignments();
		if(list.size() > 0) {
			int id = list.get(0).getId();
			Assignment a = this.assignmentDao.findAssignmentById(id);
			assertNotNull(a);
			assertEquals(list.get(0).getName(), a.getName());
		}
	}
	
	/**
	 * Test the end-point for assignments mapped with a course
	 * @throws Exception
	 */
	@Test
	public void testAssignmentsForCourse() throws Exception {
		List<Assignment> list = assignmentDao.findAllAssignments();
		if(list.size() > 0) {
			int id = list.get(0).getCourse();
			int aid = list.get(0).getId();
			list = this.assignmentDao.findAllAssignmentsForCourse(id);
			assertTrue(list.size() > 0);
			Set<Integer> set = new HashSet<>();
			for(Assignment a : list) {
				set.add(a.getId());
			}
			assertTrue(set.contains(aid));
		}
	}
	
	/**
	 * Test the end-point for updating an assignments 
	 * @throws Exception
	 */
	@Test
	public void testUpdateAssignment() throws Exception {
		List<Assignment> list = assignmentDao.findAllAssignments();
		if(list.size() > 0) {
			int aid = list.get(0).getId();
			String name = list.get(0).getName();
			this.assignmentDao.updateAssignment(aid, "Test", list.get(0).getCourse(), list.get(0).getLangId());
			Assignment a = this.assignmentDao.findAssignmentById(aid);
			assertEquals("Test", a.getName());
			this.assignmentDao.updateAssignment(aid, name, list.get(0).getCourse(), list.get(0).getLangId());
		}
	}
	
	/**
	 * Test the end-point for adding and deleting an assignment
	 * @throws Exception
	 */
	@Test
	public void testCRUDOnAssignment() throws Exception {
		String name = "Test Assignment123";
		List<Assignment> list = assignmentDao.findAllAssignments();
		if(list.size() > 0) {
			int cid = list.get(0).getCourse();
			this.assignmentDao.addAssignment(name, cid, list.get(0).getLangId());
			list = this.assignmentDao.findAllAssignmentsForCourse(cid);
			int id = -1;
			for(Assignment a : list) {
				if(a.getName().equals(name)) {
					id = a.getId();
					break;
				}
			}
			if(id > -1) {
				assertTrue(this.assignmentDao.deleteAssignment(id) == 1);
			}
		}
	}
}
