package edu.northeastern.cs5500.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.file.FileStructure;
import edu.northeastern.cs5500.models.person.Professor;
import edu.northeastern.cs5500.models.person.Student;
import edu.northeastern.cs5500.models.person.StudentAssignmentMap;
import edu.northeastern.cs5500.models.person.User;

@Transactional
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String getNameOfStudent(int submissionId) {
		try {
			String sql = "Select User.firstName, User.lastName from User join Student on User.id = Student.id join Submission on Student.id = Submission.student where Submission.id = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			User user =  jdbcTemplate.queryForObject(sql, mapper, submissionId);
			return user.getFirstName() + " " + user.getLastName();
			
		}
		catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 
	 * @param user
	 */
	public User addNewUser(final User user) {
		int type = 0;
		try {
			String sql = "INSERT INTO User(firstName, lastName, email, userName, password) VALUES(?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, new Object[] {user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword()});
		}
		catch(Exception e) {
			return null;
		}
		int rId = findId(user);
		if(user.getType() == 0) {
			type = 0;
			try {
				String sql2 = "INSERT INTO Student (id) VALUES(?)";
				jdbcTemplate.update(sql2, new Object[] {rId});
			}
			catch(Exception e) {
				return null;
			}
		
		}
		else if(user.getType() == 1){
			try {
				type = 1;
				String sql2 = "INSERT INTO Professor (id) VALUES(?)";
				jdbcTemplate.update(sql2, new Object[] {rId});	
			}
			catch(Exception e) {
				
			}
		}
		return this.login(user.getUsername(), user.getPassword(), type);
	}
	
	public void updateUser(final User user) {
		try {
			String sql = "UPDATE User SET firstName = ?, lastName = ?, email = ?, userName = ?, password = ? WHERE id = ?";
			jdbcTemplate.update(sql, new Object[] {user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getId()});
		}
		catch(Exception e){
			
		}
	}
	
	public int deleteUser(int id, int type) {
		try {
			String sql = "DELETE FROM User WHERE id = ?";
			jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e) {
			
		}
		
		if(type == 0) {
			try {
				String sql2 = "DELETE FROM Student WHERE id = ?";
				return jdbcTemplate.update(sql2, new Object[] {id});
			}
			catch(Exception e) {
			}
		}
		else if(type == 1){
			try {
				String sql2 = "DELETE FROM Professor WHERE id = ?";
				return jdbcTemplate.update(sql2, new Object[] {id});	
			}
			catch(Exception e) {
				
			}
		}
		return 0;
	}
	
	public User login(final String username, final String password, int type) {
		if(type == 1) {
			try {
				String sql = "SELECT * FROM User join Professor on  Professor.id = User.id WHERE User.username = ? AND User.password = ?";
				RowMapper<Professor> mapper = new BeanPropertyRowMapper<>(Professor.class);
				Professor user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
				return user;
				
			}
			catch(Exception e) {
				return null;
			}
			
		} else if(type == 0) {
			
			try {
			String sql = "SELECT * FROM User join Student on  Student.id = User.id WHERE User.username = ? AND User.password = ?";
			RowMapper<Student> mapper = new BeanPropertyRowMapper<>(Student.class);
			Student user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
			return user;
			}
			catch(Exception e) {
				return null;
			}
		}
		return null;
	}
	
	public List<User> findAllUsers(){
		try {
		String sql = "Select * from User";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		List<User> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public Integer getTypeFromUsernamePassword(String username, String password) {
		try {
			String sql = "Select * from User where User.username = ? AND User.password = ?";
			RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
			User user =  jdbcTemplate.queryForObject(sql, rowMapper, username, password);
			try {
				String sql2 = "Select * from Student where Student.id = ?";
				RowMapper<Student> map1 = new BeanPropertyRowMapper<>(Student.class);
				Student s =  jdbcTemplate.queryForObject(sql2, map1, user.getId());
				return 1;
			} catch(Exception e) {
				try {
					String sql3 = "Select * from Professor where Professor.id = ?";
					RowMapper<Professor> map2 = new BeanPropertyRowMapper<>(Professor.class);
					Professor p =  jdbcTemplate.queryForObject(sql3, map2, user.getId());
					return 0;
				} catch(Exception ee) {
					return -1;
				}
				
			}
			
			
			}
			catch(Exception e) {
				return null;
			}
	}
	
	
	
public Integer findId(final User user) {
		
		try {
			String sql = "SELECT * FROM User WHERE User.username = ? AND User.password = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			User u =  jdbcTemplate.queryForObject(sql, mapper, user.getUsername(), user.getPassword());
			return u.getId();
			
		}
		catch(Exception e) {
			return null;
		}
	}

public List<Professor> findAllProfessors(){
	try {
	String sql = "Select * from User join Professor on User.id = Professor.id";
	RowMapper<Professor> rowMapper = new BeanPropertyRowMapper<>(Professor.class);
	List<Professor> results = this.jdbcTemplate.query(sql, rowMapper);
	return results;
	}
	catch(Exception e) {
		return null;
	}
}

public void addFileForStudentAssignment(int studentId, int assignmentId, String fname) {
	int saId = findStudentAssignmentId(studentId, assignmentId);
	try {
	String sql = "INSERT INTO StudentAssignmentFileMapping (studentAssignment, file) VALUES(?, ?)";
	jdbcTemplate.update(sql, new Object[] {saId, fname});
	}
	catch(Exception e) {
	}
	
}

public Integer findStudentFileId(int saId, String fname) {
	
	try {
		String sql = "SELECT * FROM StudentAssignmentFileMapping WHERE StudentAssignmentFileMapping.studentAssignment = ? AND StudentAssignmentFileMapping.file = ?";
		RowMapper<FileStructure> mapper = new BeanPropertyRowMapper<>(FileStructure.class);
		FileStructure u =  jdbcTemplate.queryForObject(sql, mapper, saId, fname);
		return u.getId();
		
	}
	catch(Exception e) {
		return null;
	}
}

public List<Integer> findStudentsForCourse(int courseId) {

try {
	String sql = "SELECT StudentCourseMapping.student FROM StudentCourseMapping WHERE StudentCourseMapping.course = ?";
	RowMapper<Integer> mapper = new BeanPropertyRowMapper<>(Integer.class);
	List<Integer> u =  jdbcTemplate.query(sql, mapper, courseId);
	return u;
	
}
catch(Exception e) {
	return null;
}
}

public Integer findStudentAssignmentId(int studentId, int assignmentId) {
	
	try {
		String sql = "SELECT * FROM StudentAssignmentMapping WHERE StudentAssignmentMapping.student = ? AND StudentAssignmentMapping.assignment = ?";
		RowMapper<StudentAssignmentMap> mapper = new BeanPropertyRowMapper<>(StudentAssignmentMap.class);
		StudentAssignmentMap user =  jdbcTemplate.queryForObject(sql, mapper, studentId, assignmentId);
		return user.getId();
		
	}
	catch(Exception e) {
		return null;
	}
}

public List<Student> findAllStudentsForAssignment(int assignmentId) {
	try {
	String sql = "Select Student.id, User.firstName, User.lastName, User.email, User.userName, User.password, Student.universityID from StudentAssignmentMapping join Student on Student.id = StudentAssignmentMapping.student join User on User.id = Student.id WHERE StudentAssignmentMapping.assignment = ?";
	RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
	List<Student> results = this.jdbcTemplate.query(sql, rowMapper, assignmentId);
	return results;
	}
	catch(Exception e) {
		return null;
	}
}

public List<FileStructure> findAllFileStructuresStudent(int studentId, int assignmentId) {
	int saId = findStudentAssignmentId(studentId, assignmentId);
	try {
	String sql = "Select * from StudentAssignmentFileMapping WHERE StudentAssignmentFileMapping.studentAssignment = ?";
	RowMapper<FileStructure> rowMapper = new BeanPropertyRowMapper<>(FileStructure.class);
	List<FileStructure> results = this.jdbcTemplate.query(sql, rowMapper, saId);
	return results;
	}
	catch(Exception e) {
		return null;
	}
}


public Integer findStudentIdFromStudentAssignment(int sa) {
	
	try {
		String sql = "SELECT * FROM StudentAssignmentMapping WHERE StudentAssignmentMapping.id = ?";
		RowMapper<StudentAssignmentMap> mapper = new BeanPropertyRowMapper<>(StudentAssignmentMap.class);
		StudentAssignmentMap user =  jdbcTemplate.queryForObject(sql, mapper, sa);
		return user.getStudent();
		
	}
	catch(Exception e) {
		return null;
	}
}

}