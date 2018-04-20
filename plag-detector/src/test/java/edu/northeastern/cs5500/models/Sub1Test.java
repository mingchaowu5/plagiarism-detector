package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.submission.Sub1;

public class Sub1Test {
	Sub1 stuass = new Sub1();
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		stuass.setAssignment("ass1");
		assertEquals(stuass.getAssignment(), "ass1");
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		stuass.setCourse("course1");
		assertEquals("course1", stuass.getCourse());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		stuass.setDate("tomorrow");
		assertEquals("tomorrow", stuass.getDate());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testSome() {
		stuass.setStudent("stud1");
		assertEquals("stud1", stuass.getStudent());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void test2() {
		stuass.setSubmission(20);
		assertEquals(stuass.getSubmission(), 20);
	}
}
