package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.northeastern.cs5500.models.assignment.Assignment;

public class AssignmentTest {

	private Assignment assignment;

	@Before
	public void before() {
		this.assignment = new Assignment();
	}
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		assertNull(assignment.getName());
		assignment.setName("Name");
		assertEquals("Name", assignment.getName());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		assertTrue(assignment.getCourse() == 0);
		assignment.setCourse(1);
		assertEquals(1, assignment.getCourse());
	}
	
	/**
	 * Test Id
	 */
	@Test
	public void testId() {
		assertTrue(assignment.getId() == 0);
		assignment.setId(1);
		assertEquals(1, assignment.getId());
	}
}
