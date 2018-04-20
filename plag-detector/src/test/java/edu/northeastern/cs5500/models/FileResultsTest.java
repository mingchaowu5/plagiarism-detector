package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.results.FileResults;

/** 
 * 
 * @author varunnandu
 *
 */
public class FileResultsTest {
	
	FileResults fr = new FileResults();
	FileResults f2 = new FileResults(10, 20, 30, 40);
	
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
		fr.setStudentAssignmentFile1(12);
		assertEquals(fr.getStudentAssignmentFile1(), 12);
		fr.setStudentAssignmentFile2(15);
		assertEquals(fr.getStudentAssignmentFile2(), 15);
	}
	

}
