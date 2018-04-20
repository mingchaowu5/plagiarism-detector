package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.results.StudentResults;

/** 
 * 
 * @author varunnandu
 *
 */
public class StudentResultsTest {
	
	StudentResults fr = new StudentResults();
	StudentResults f2 = new StudentResults(10, 20, 30, 40);
	
	/** 
	 * Test
	 */
	@Test
	public void testName() {
		fr.setId(10);
		assertEquals(fr.getId(), 10);
		assertEquals(fr.getId(), f2.getId());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		fr.setResult(20);
		assertEquals(fr.getResult(), 20);
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		fr.setStudentAssignment1(12);
		assertEquals(fr.getStudentAssignment1(), 12);
		fr.setStudentAssignment2(15);
		assertEquals(fr.getStudentAssignment2(), 15);
	}

}
