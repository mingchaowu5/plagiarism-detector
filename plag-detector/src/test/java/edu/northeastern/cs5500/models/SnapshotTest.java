package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.northeastern.cs5500.models.snapshot.Snapshot;

public class SnapshotTest {

	
	private Snapshot semester = new Snapshot();
	private Snapshot sem2 = new Snapshot(3, 3, "Fall 2018");
	
	@Test
	public void test1() {
		semester.setId(3);
		assertNotNull(semester.getId());
		assertEquals(semester.getId(), sem2.getId());
	}
	
	@Test
	public void test2() {
		semester.setSubmission(30);;
		assertEquals(30, semester.getSubmission());
	}
	
	@Test
	public void test3() {
		semester.setDateTime("today");
		assertEquals(semester.getDateTime(), "today");
	}
	
	@Test
	public void test4() {
		semester.setType(2);;
		assertEquals(2, semester.getType());
	}
}
