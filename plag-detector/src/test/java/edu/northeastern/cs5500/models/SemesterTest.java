package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.semester.Semester;

public class SemesterTest {

	private Semester semester = new Semester();
	private Semester sem2 = new Semester(3, "Fall 2018");
	
	@Test
	public void test1() {
		semester.setId(3);
		assertEquals(3, semester.getId());
		assertEquals(semester.getId(), sem2.getId());
	}
	
	@Test
	public void test2() {
		semester.setName("Fall 2018");
		assertEquals("Fall 2018", semester.getName());
	}
}
