package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import edu.northeastern.cs5500.models.results.Result;


/** 
 * 
 * @author varunnandu
 *
 */
public class ResultTest {
	
	Result r = new Result();
	Result r2 = new Result(10, 10, 10, "Hello", "World", null, null, 10);
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		r.setFile1(null);
		assertNull(r.getFile1());
		r.setFile2(null);
		assertNull(r.getFile2());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		r.setFilename1("Hello");
		r.setFilename2("World");
		assertEquals("Hello", r.getFilename1());
		assertEquals("World", r.getFilename2());
		assertEquals(r2.getFilename1(), r.getFilename1());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		r.setId(30);
		r.setSubmission1(20);
		r.setSubmission2(25);
		assertEquals(r.getId(), 30);
		assertEquals(r.getSubmission1(), 20);
		assertEquals(r.getSubmission2(), 25);
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testSome() {
		r.setPercentage(95.6);
		assertNotNull(r.getPercentage());
	}

}
