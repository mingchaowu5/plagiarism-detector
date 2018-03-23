package edu.northeastern.cs5500.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Results.FileResults;
import edu.northeastern.cs5500.models.Results.StudentResults;
import edu.northeastern.cs5500.models.file.FilePath;
import edu.northeastern.cs5500.models.file.FileStructure;

@Transactional
@Repository
public class ResultsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addFileResults(int saId1, int saId2, int result) {
		try {
		String sql = "INSERT INTO FileFileResults (studentAssignmentFile1, studentAssignmentFile2, result) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {saId1, saId2, result});
		}
		catch(Exception e) {
		}
	
	}
	
	public String getFilePath(int fid) {
		String sql = "Select FilePaths.name from FilePaths WHERE FilePaths.id=?";
		RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
		List<String> path = this.jdbcTemplate.query(sql, rowMapper, fid);
		if(path.size() > 0)
			return path.get(0);
		return null;
	}
	
	public void addFilePaths(int fid, String name) {
		try {
		String sql = "INSERT INTO FilePaths (fid, name) VALUES(?, ?)";
		jdbcTemplate.update(sql, new Object[] {fid, name});
		}
		catch(Exception e) {
		}
	
	}
	
	public FilePath findFileDetailsForId(int id) {
		try {
			String sql = "Select StudentAssignmentFileMapping.file, FilePaths.name from StudentAssignmentFileMapping join FilePaths on StudentAssignmentFileMapping.id = FilePaths.fid WHERE StudentAssignmentFileMapping.id = ?";
			RowMapper<FilePath> rowMapper = new BeanPropertyRowMapper<>(FilePath.class);
			List<FilePath> results = this.jdbcTemplate.query(sql, rowMapper, id);
			if(results.size() > 0)
				return results.get(0);
			return null;
			}
			catch(Exception e) {
				return null;
			}
	}
	
	public List<FileResults> findAllFileResultsForStudentAssignment(int fileId) {
		try {
		String sql = "Select * from FileFileResults WHERE FileFileResults.studentAssignmentFile1 = ? or FileFileResults.studentAssignmentFile2=?";
		RowMapper<FileResults> rowMapper = new BeanPropertyRowMapper<>(FileResults.class);
		List<FileResults> results = this.jdbcTemplate.query(sql, rowMapper, fileId, fileId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	
	
	public void addStudentResults(int sa1, int sa2, int result) {
		try {
		String sql = "INSERT INTO StudentStudentResults (studentAssignment1, studentAssignment2, result) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {sa1, sa2, result});
		}
		catch(Exception e) {
		}
	
	}
	
	public List<StudentResults> findAllStudentResultsForStudentAssignment(int saId) {
		try {
		String sql = "Select * from StudentStudentResults WHERE StudentStudentResults.studentAssignment1 = ? or StudentStudentResults.studentAssignment2=?";
		RowMapper<StudentResults> rowMapper = new BeanPropertyRowMapper<>(StudentResults.class);
		List<StudentResults> results = this.jdbcTemplate.query(sql, rowMapper, saId, saId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
}
