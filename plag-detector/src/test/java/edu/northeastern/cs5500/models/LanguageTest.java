package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.assignment.Language;

/** 
 * 
 * @author varunnandu
 *
 */
public class LanguageTest {
	private Language language = new Language();
	private Language lan = new Language("Nami", "Name", 2);
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		language.setCode("Name");
		language.setName("Nami");
		assertEquals("Name", language.getCode());
		assertEquals(language.getName(), "Nami");
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		language.setId(2);
		assertEquals(2, language.getId());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		language.setCode("Name");
		assertEquals(lan.getCode(), language.getCode());
	}
	
	

}
