package edu.northeastern.cs5500.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.northeastern.cs5500.PlagDetectorApplicationTests;

/** 
 * 
 * @author varunnandu
 *
 */
public class CourseTest extends PlagDetectorApplicationTests{

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * Test the end-point for all courses
	 * @throws Exception
	 */
	@Test
	public void testAllCourses() throws Exception {
		mockMvc.perform(get("/rest/course/all")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	
	/**
	 * Test the end-point for courses mapped with a semester
	 * @throws Exception
	 */
	@Test
	public void testCoursesForSemester() throws Exception {
		mockMvc.perform(get("/rest/course/semester").param("id", "1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	/**
	 * Test the end-point for courses mapped with a professor
	 * @throws Exception
	 */
	@Test
	public void testCoursesForProfessor() throws Exception {
		mockMvc.perform(get("/rest/course/professor").param("id", "1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	/**
	 * Test the end-point for courses mapped with a student
	 * @throws Exception
	 */
	@Test
	public void testCoursesForStudent() throws Exception {
		mockMvc.perform(get("/rest/course/student").param("id", "1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
