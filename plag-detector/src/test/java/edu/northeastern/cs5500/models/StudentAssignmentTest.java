package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.person.StudentAssignmentMap;

/** 
 * 
 * @author varunnandu
 *
 */
public class StudentAssignmentTest {
	
	StudentAssignmentMap stuass = new StudentAssignmentMap();
	StudentAssignmentMap stuass2 = new StudentAssignmentMap(20, 30, 40);
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		stuass.setId(20);
		assertEquals(stuass.getId(), 20);
		assertEquals(stuass.getId(), stuass2.getId());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		stuass.setAssignment(30);
		assertEquals(30, stuass.getAssignment());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		stuass.setStudent(40);
		assertEquals(40, stuass.getStudent());
	}

}
