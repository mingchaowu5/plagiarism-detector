package edu.northeastern.cs5500.controller.semester;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.dao.SemesterDao;
import edu.northeastern.cs5500.models.semester.Semester;

@Service
public class SemesterService {

	@Autowired
	private SemesterDao semesterDao;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Semester> getAllSemesters(){
		return this.semesterDao.getAllSemester();
	}
	
	public Semester getSemesterById(int id) {
		return this.semesterDao.findSemesterById(id);
	}
	
	/**
	 * Delete a particular semester from the system
	 * @param	id:	of the semester to be deleted
	 * @return	boolean:	true if and only iff the semester is successfully deleted
	 */
	public boolean deleteSemester(int id) {
		return true;
	}
	
	/**
	 * Insert a new semester in the system
	 * @param	name:	of the semester to be added
	 * @return	boolean:	true if and only iff the semester is successfully inserted
	 */
	public boolean insertSemester(String name) {
		return true;
	}
}
