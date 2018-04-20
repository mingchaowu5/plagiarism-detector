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
public class SnapshotTest extends PlagDetectorApplicationTests{

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * Test the end-point for all snapshots for assignments
	 * @throws Exception
	 */
	@Test
	public void testAllSnapshots() throws Exception {
		mockMvc.perform(get("/rest/snapshot/assignment").param("id", "10")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	
	/**
	 * Test the end-point for submissions
	 * @throws Exception
	 */
	@Test
	public void testSubmissions() throws Exception {
		mockMvc.perform(get("/rest/snapshot/submission").param("id1", "12").param("id2", "13")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	/**
	 * Test the end-point for results
	 * @throws Exception
	 */
	@Test
	public void testResults() throws Exception {
		mockMvc.perform(get("/rest/snapshot/result").param("id", "2")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
