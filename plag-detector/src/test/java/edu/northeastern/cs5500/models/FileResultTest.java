package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.file.FileResult;

public class FileResultTest {
	
	FileResult fr = new FileResult();
	
	@Test
	public void testName() {
		fr.setFile1("File1");;
		fr.setFile2("File1");
		assertEquals(fr.getFile1(), fr.getFile2());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		fr.setId1(20);
		fr.setId2(20);
		assertEquals(fr.getId1(), fr.getId2());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		fr.setName1("Name1");
		fr.setName2("Name1");
		assertEquals(fr.getName1(), fr.getName2());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testSome() {
		fr.setValue(20);
		assertEquals(20, fr.getValue());
	}

}
