package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.northeastern.cs5500.models.course.Course;

public class CourseTest {
	
	private Course course = new Course();
	
	@Test
	public void testId() {
		course.setId(90);
		assertEquals(course.getId(), 90);
	}
	
	@Test
	public void testName() {
		course.setName("Course 1");
		assertTrue(course.getName() == "Course 1");
	}
	
	@Test
	public void testSemester() {
		course.setSemester(23);;
		assertTrue(course.getSemester() == 23);
	}

}
