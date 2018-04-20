package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.northeastern.cs5500.models.notification.Notification;

public class NotificationTest {
	
Notification stuass = new Notification();
Notification stuass2 = new Notification(2, "ass1", 2, "ass1");
	
	/**
	 * Test Name
	 */
	@Test
	public void testName() {
		stuass.setDate("ass1");
		assertEquals(stuass.getDate(), "ass1");
		assertEquals(stuass.getDate(), stuass2.getDate());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testCourse() {
		stuass.setSnapshot(30);
		assertEquals(30, stuass.getSnapshot());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testEquality() {
		stuass.setId(40);
		assertEquals(40, stuass.getId());
	}
	
	/**
	 * Test course
	 */
	@Test
	public void testSome() {
		stuass.setText("stud1");
		assertEquals("stud1", stuass.getText());
	}
	

}
