package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.file.FileStructure;


public class FileStructureTest {
FileStructure fr = new FileStructure();
FileStructure f2 = new FileStructure(30, 40, "File1");
	
	@Test
	public void testName() {
		fr.setFile("File1");;
		assertEquals(fr.getFile(), "File1");
		assertEquals(f2.getFile(), fr.getFile());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		fr.setId(20);
		assertEquals(fr.getId(), 20);
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		fr.setStudentAssignment(30);
		assertEquals(fr.getStudentAssignment(), 30);
		}
	

}
