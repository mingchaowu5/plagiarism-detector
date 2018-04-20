package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.submission.Submission;

/** 
 * 
 * @author varunnandu
 *
 */
public class SubmissionTest {
	
Submission stuass = new Submission();
Submission sub2 = new Submission(10, 30, 30, "Hello");
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		stuass.setAssignment(30);
		assertEquals(stuass.getAssignment(), 30);
		assertEquals(stuass.getAssignment(), sub2.getAssignment());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		stuass.setId(20);
		assertEquals(20, stuass.getId());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		stuass.setDateTime("tomorrow");;
		assertEquals("tomorrow", stuass.getDateTime());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testSome() {
		stuass.setStudent(30);
		assertEquals(30, stuass.getStudent());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void test2() {
		stuass.setName("Sub1");
		assertEquals(stuass.getName(), "Sub1");
	}

}
