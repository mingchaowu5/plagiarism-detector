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

public class UserTest extends PlagDetectorApplicationTests{

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * Test the end-point for all users
	 * @throws Exception
	 */
	@Test
	public void testAllUser() throws Exception {
		mockMvc.perform(get("/rest/user/all")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	/**
	 * Test the end-point for username
	 * @throws Exception
	 */
	@Test
	public void testUsername() throws Exception {
		mockMvc.perform(get("/rest/user/available").param("username", "varun")).andExpect(status().isOk());
	}
	
	/**
	 * Test the end-point for all users who are professor
	 * @throws Exception
	 */
	@Test
	public void testAllProfessor() throws Exception {
		mockMvc.perform(get("/rest/user/professor")).andExpect(status().isOk());
	}
	
	/**
	 * Test the end-point for all users who are students
	 * @throws Exception
	 */
	@Test
	public void testAllStudent() throws Exception {
		mockMvc.perform(get("/rest/user/student")).andExpect(status().isOk());
	}
	
	
	
}
