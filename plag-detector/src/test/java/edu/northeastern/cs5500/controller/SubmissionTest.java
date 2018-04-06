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

public class SubmissionTest extends PlagDetectorApplicationTests{

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * Test the end-point for all submission for assignments
	 * @throws Exception
	 */
	@Test
	public void testAllSubmissionsForAssignment() throws Exception {
		mockMvc.perform(get("/rest/submission/assignment").param("id", "10")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	/**
	 * Test the end-point for submissions
	 * @throws Exception
	 */
	@Test
	public void testAllSubmissions() throws Exception {
		mockMvc.perform(get("/rest/submission/all").param("assignment_id", "10").param("student_id", "2")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
}