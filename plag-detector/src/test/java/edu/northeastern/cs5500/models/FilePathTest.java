package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.file.FilePath;

public class FilePathTest {
FilePath stuass = new FilePath();
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		stuass.setFile("File1");;
		assertEquals(stuass.getFile(), "File1");
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		stuass.setName("Name");;
		assertEquals("Name", stuass.getName());
	}
	
}
