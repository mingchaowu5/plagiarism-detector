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

public class SemesterTest extends PlagDetectorApplicationTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * Test the end-point for all semester
	 * @throws Exception
	 */
	@Test
	public void testAllSemesters() throws Exception {
		mockMvc.perform(get("/rest/semester/all")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	
	/**
	 * Test the end-point for semester mapped to a id
	 * @throws Exception
	 */
	@Test
	public void testSemesterForId() throws Exception {
		mockMvc.perform(get("/rest/semester/").param("id", "-1")).andExpect(status().isNoContent());
	}
}
