package edu.northeastern.cs5500.controller.semester;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.database.SemesterConnection;
import edu.northeastern.cs5500.database.UserConnection;
import edu.northeastern.cs5500.models.Semester.Semester;

@Service
public class SemesterService {

	@Autowired
	private SemesterConnection semesterConnection;
	
	public List<Semester> getAllSemesters(){
		return this.semesterConnection.getAllSemester();
	}
	
}
