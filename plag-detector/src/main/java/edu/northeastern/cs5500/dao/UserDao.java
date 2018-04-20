package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private Logger log = Logger.getAnonymousLogger();
	
	/**
	 * Find professor details by a snapshot id
	 * @param snapshotId
	 * @return
	 */
	public User findUserBySnapshotId(int snapshotId) {
		try {
			String sql = "SELECT User.id, User.firstName, User.lastName, User.email FROM User join Professor on User.id = Professor.id join ProfessorSnapshotMapping on ProfessorSnapshotMapping.Professor = Professor.id WHERE ProfessorSnapshotMapping.snapshot = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			return jdbcTemplate.queryForObject(sql, mapper, snapshotId);			
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * Find user by ID
	 * @param userId
	 * @return
	 */
	public User findUserById(int userId) {
		try {
			String sql = "SELECT User.id, User.firstName, User.lastName, User.email FROM User WHERE User.id = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			return jdbcTemplate.queryForObject(sql, mapper, userId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * Find user by ID
	 * @param userId
	 * @return
	 */
	public User findUserBySubmissionId(int subId) {
		try {
			String sql = "SELECT User.id, User.firstName, User.lastName, User.email FROM Submission JOIN User ON Submission.student = User.id WHERE Submission.id = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			return jdbcTemplate.queryForObject(sql, mapper, subId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * Find User by type
	 * @param type
	 * @return
	 */
	public List<User> findUserByType(int type) {
		try {
			String sql = "SELECT User.id, User.firstName, User.lastName, User.email FROM User WHERE User.type = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			return jdbcTemplate.query(sql, mapper, type);			
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * Get User details for a submission ID
	 * @param submissionId
	 * @return
	 */
	public String getNameOfStudent(int submissionId) {
		try {
			String sql = "Select User.firstName, User.lastName from User join Student on User.id = Student.id join Submission on Student.id = Submission.student where Submission.id = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			User user =  jdbcTemplate.queryForObject(sql, mapper, submissionId);
			return user.getFirstName() + " " + user.getLastName();
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return "";
		}
	}
	
	/**
	 * Delete Student from the table
	 * @param id
	 * @return
	 */
	public int deleteStudent(int id) {
		try {
			String sql = "DELETE FROM Student WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Delete Student from the table
	 * @param id
	 * @return
	 */
	public int deleteProfessor(int id) {
		try {
			String sql = "DELETE FROM Professor WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Insert Student into the table
	 * @param id
	 * @return
	 */
	public int insertStudent(int id) {
		try {
			String sql = "INSERT INTO Student(id) VALUES(?)";
			return jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Delete Student from the table
	 * @param id
	 * @return
	 */
	public int insertProfessor(int id) {
		try {
			String sql = "INSERT INTO Professor(id) VALUES(?)";
			return jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
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
				log.log(Level.INFO, e.getMessage());
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
				log.log(Level.INFO, e.getMessage());
			}
		}
		return this.login(user.getUsername(), user.getPassword(), type);
	}
	
	/**
	 * 
	 * @param user
	 */
	public void updateUser(final User user) {
		try {
			String sql = "UPDATE User SET firstName = ?, lastName = ?, email = ?, userName = ?, password = ? WHERE id = ?";
			jdbcTemplate.update(sql, new Object[] {user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getId()});
		}
		catch(Exception e){
			log.log(Level.INFO, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public int deleteUser(int id, int type) {
		try {
			String sql = "DELETE FROM User WHERE id = ?";
			jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
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
				log.log(Level.INFO, e.getMessage());
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	public User login(final String username, final String password, int type) {
		if(type == 1) {
			try {
				String sql = "SELECT * FROM User join Professor on  Professor.id = User.id WHERE User.username = ? AND User.password = ?";
				RowMapper<Professor> mapper = new BeanPropertyRowMapper<>(Professor.class);
				return jdbcTemplate.queryForObject(sql, mapper, username, password);				
			}
			catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
				return null;
			}
			
		} else if(type == 0) {
			
			try {
			String sql = "SELECT * FROM User join Student on  Student.id = User.id WHERE User.username = ? AND User.password = ?";
			RowMapper<Student> mapper = new BeanPropertyRowMapper<>(Student.class);
			return jdbcTemplate.queryForObject(sql, mapper, username, password);
			}
			catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<User> findAllUsers(){
		try {
			List<User> results = new ArrayList<>();
			List<Professor> temp1 = this.findAllProfessors();
			for(Professor p : temp1) {
				User u = new User(p.getId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getUsername(), p.getPassword());
				u.setType(1);
				results.add(u);
			}
			List<Student> temp2 = this.findAllStudents();
			for(Student s : temp2) {
				User u = new User(s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getUsername(), s.getPassword());
				u.setType(0);
				results.add(u);
			}
			return results;
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Integer getTypeFromUsernamePassword(String username, String password) {
		try {
			String sql = "Select * from User where User.username = ? AND User.password = ?";
			RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
			User user =  jdbcTemplate.queryForObject(sql, rowMapper, username, password);
			try {
			String sql2 = "Select * from Student where Student.id = ?";
			RowMapper<Student> map1 = new BeanPropertyRowMapper<>(Student.class);
			Student s =  jdbcTemplate.queryForObject(sql2, map1, user.getId());
			} catch(Exception e) {
				System.out.println(user.getId());
				String sql3 = "Select * from Professor where Professor.id = ?";
				RowMapper<Professor> map2 = new BeanPropertyRowMapper<>(Professor.class);
				Professor p =  jdbcTemplate.queryForObject(sql3, map2, user.getId());
				if(p != null)
					return 1;
			}
			return 0;
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return -1;
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Integer findId(final User user) {	
		try {
			String sql = "SELECT * FROM User WHERE User.username = ? AND User.password = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			User u =  jdbcTemplate.queryForObject(sql, mapper, user.getUsername(), user.getPassword());
			return u.getId();
			
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Professor> findAllProfessors(){
		try {
			String sql = "Select User.* from User join Professor on User.id = Professor.id";
			RowMapper<Professor> rowMapper = new BeanPropertyRowMapper<>(Professor.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Student> findAllStudents(){
		try {
		String sql = "Select User.* from User join Student on User.id = Student.id";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
		return this.jdbcTemplate.query(sql, rowMapper);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	
	
	/**
	 * 
	 * @param courseId
	 * @return
	 */
	public List<Integer> findStudentsForCourse(int courseId) {
	
		try {
			String sql = "SELECT StudentCourseMapping.student FROM StudentCourseMapping WHERE StudentCourseMapping.course = ?";
			RowMapper<Integer> mapper = new BeanPropertyRowMapper<>(Integer.class);
			return  jdbcTemplate.query(sql, mapper, courseId);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
			}
	}
	
	/**
	 * 
	 * @param studentId
	 * @param assignmentId
	 * @return
	 */
	public Integer findStudentAssignmentId(int studentId, int assignmentId) {
		try {
			String sql = "SELECT * FROM StudentAssignmentMapping WHERE StudentAssignmentMapping.student = ? AND StudentAssignmentMapping.assignment = ?";
			RowMapper<StudentAssignmentMap> mapper = new BeanPropertyRowMapper<>(StudentAssignmentMap.class);
			StudentAssignmentMap user =  jdbcTemplate.queryForObject(sql, mapper, studentId, assignmentId);
			return user.getId();
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param assignmentId
	 * @return
	 */
	public List<Student> findAllStudentsForAssignment(int assignmentId) {
		try {
			String sql = "Select Student.id, User.firstName, User.lastName, User.email, User.userName, User.password, Student.universityID from StudentAssignmentMapping join Student on Student.id = StudentAssignmentMapping.student join User on User.id = Student.id WHERE StudentAssignmentMapping.assignment = ?";
			RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
			return this.jdbcTemplate.query(sql, rowMapper, assignmentId);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	

	/**
	 * 
	 * @param sa
	 * @return
	 */
	public Integer findStudentIdFromStudentAssignment(int sa) {
		try {
			String sql = "SELECT * FROM StudentAssignmentMapping WHERE StudentAssignmentMapping.id = ?";
			RowMapper<StudentAssignmentMap> mapper = new BeanPropertyRowMapper<>(StudentAssignmentMap.class);
			StudentAssignmentMap user =  jdbcTemplate.queryForObject(sql, mapper, sa);
			return user.getStudent();
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}

}