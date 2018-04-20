package edu.northeastern.cs5500.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import edu.northeastern.cs5500.PlagDetectorApplicationTests;
import edu.northeastern.cs5500.dao.SemesterDao;
import edu.northeastern.cs5500.models.semester.Semester;


/** 
 * 
 * @author varunnandu
 *
 */
public class SemesterTest extends PlagDetectorApplicationTests{
	
	@Autowired
	private SemesterDao semesterDao;
	
	@Before
	public void setup() {
		
	}
	
	/**
	 * Test the end-point for all semester
	 * @throws Exception
	 */
	@Test
	public void testAllSemesters() throws Exception {
		List<Semester> list = this.semesterDao.getAllSemester();
		assertTrue(list.size() > 0);
	}
	
	
	/**
	 * Test the end-point for semester mapped to a id
	 * @throws Exception
	 */
	@Test
	public void testSemesterForId() throws Exception {
		List<Semester> list = this.semesterDao.getAllSemester();
		if(list.size() > 0) {
			int id = list.get(0).getId();
			Semester s = this.semesterDao.findSemesterById(id);
			assertEquals(list.get(0).getName(), s.getName());
		}
	}
	
	/**
	 * Test the end-point for CRUD operations on semester
	 * @throws Exception
	 */
	@Test
	public void testCRUDOnSemester() throws Exception {
		String name = "Test Assignment123";
		this.semesterDao.addSemester(name);
		List<Semester> list = this.semesterDao.getAllSemester();
		if(list.size() > 0) {
			int id = -1;
			for(Semester s : list) {
				if(s.getName().equals(name)) {
					id = s.getId();
					break;
				}
			}
			if(id > -1) {
				assertTrue(this.semesterDao.deleteSemester(id) == 1);
			}
		}
	}
}
