package edu.northeastern.cs5500.dao;

import java.io.InputStream;
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
import edu.northeastern.cs5500.models.results.Result;



@Transactional
@Repository
public class ResultsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getAnonymousLogger();
	
	/**
	 * 
	 * @param sub1
	 * @return
	 */
	public Result findAverageResultForOneSubmission(int sub1){
		try {
			String sql = "SELECT Sum(percentage)/count(*) as percentage FROM cs5500.Result where submission1 = ? or submission2 = ?";
			RowMapper<Result> rowMapper = new BeanPropertyRowMapper<>(Result.class);
			return this.jdbcTemplate.queryForObject(sql, rowMapper, sub1, sub1);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Result getFileData(int id) {
		try {
			String sql = "SELECT * FROM Result WHERE id = ?";
			RowMapper<Result> rowMapper = new BeanPropertyRowMapper<>(Result.class);
			return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param sub1
	 * @param sub2
	 * @return
	 */
	public Result findAverageResultSubmission(int sub1, int sub2){
		try {
			String sql = "Select submission1, submission2, sum(percentage)/count(*) as percentage from Result where (Result.submission1 = ?  AND Result.submission2 = ?) OR (Result.submission2 = ? AND Result.submission1 = ?)";
			RowMapper<Result> rowMapper = new BeanPropertyRowMapper<>(Result.class);
			return this.jdbcTemplate.queryForObject(sql, rowMapper, sub1, sub2, sub1, sub2);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param sum1
	 * @param sum2
	 * @param fileName1
	 * @param fileName2
	 * @param file1
	 * @param file2
	 * @param percentage
	 * @return
	 */
	public int addResults(int sum1, int sum2, String fileName1, String fileName2, InputStream file1, InputStream file2, double percentage) {
		try {
			String sql = "INSERT INTO Result (submission1, submission2, filename1, filename2, file1, file2, percentage) VALUES(?, ?, ?, ?, ?, ?, ?)";
			return jdbcTemplate.update(sql, new Object[] {sum1, sum2, fileName1, fileName2, file1, file2, percentage});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	
	}
	
	/**
	 * 
	 * @param sum1
	 * @param sum2
	 * @return
	 */
	public int deleteResults(int sum1, int sum2) {
		try {
			String sql = "DELETE FROM Result WHERE (submission1 = ? and submission2 = ?) OR (submission1 = ? and submission2 = ?)";
			return jdbcTemplate.update(sql, new Object[] {sum1, sum2, sum2, sum1});
		} catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 
	 * @param sub1
	 * @param sub2
	 * @return
	 */
	public List<Result> findAllResultsForSubmission(int sub1, int sub2){
		try {
			String sql = "Select * from Result where (Result.submission1 = ? AND Result.submission2 = ?) OR (Result.submission2 = ? AND Result.submission1 = ?)";
			RowMapper<Result> rowMapper = new BeanPropertyRowMapper<>(Result.class);
			return this.jdbcTemplate.query(sql, rowMapper, sub1, sub2, sub1, sub2);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
}
