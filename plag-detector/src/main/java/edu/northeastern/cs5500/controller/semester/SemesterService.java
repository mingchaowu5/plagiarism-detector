package edu.northeastern.cs5500.controller.semester;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Dao.SemesterDao;
import edu.northeastern.cs5500.models.Semester.Semester;

@Service
public class SemesterService {

	@Autowired
	private SemesterDao semesterConnection;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Semester> getAllSemesters(){
		return this.semesterConnection.getAllSemester();
	}
	
}