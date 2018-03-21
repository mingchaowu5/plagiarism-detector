package edu.northeastern.cs5500.Dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.northeastern.cs5500.models.Course.Course;

public class CourseDaoTests {
	CourseDao cdao = CourseDao.getInstance();

	@Test
	public void test1() {
		List<Course> courseList = cdao.findAllCourses();
		assertEquals(8, courseList.size());
	}

}
