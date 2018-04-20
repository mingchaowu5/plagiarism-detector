package edu.northeastern.cs5500.models.results;

/** 
 * 
 * @author varunnandu
 *
 */
public class Result {
	
	private int id;
	private int submission1;
	private int submission2;
	private String filename1;
	private String filename2;
	private byte[] file1;
	private byte[] file2;
	private double percentage;
	
	/** 
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/** 
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getSubmission1() {
		return submission1;
	}
	
	/** 
	 * 
	 * @param submission1
	 */
	public void setSubmission1(int submission1) {
		this.submission1 = submission1;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getSubmission2() {
		return submission2;
	}
	
	/** 
	 * 
	 * @param submission2
	 */
	public void setSubmission2(int submission2) {
		this.submission2 = submission2;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getFilename1() {
		return filename1;
	}
	
	/** 
	 * 
	 * @param filename1
	 */
	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getFilename2() {
		return filename2;
	}
	
	/** 
	 * 
	 * @param filename2
	 */
	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}
	
	/** 
	 * 
	 * @return
	 */
	public byte[] getFile1() {
		return file1;
	}
	
	/** 
	 * 
	 * @param file1
	 */
	public void setFile1(byte[] file1) {
		this.file1 = file1;
	}
	
	/** 
	 * 
	 * @return
	 */
	public byte[] getFile2() {
		return file2;
	}
	
	/** 
	 * 
	 * @param file2
	 */
	public void setFile2(byte[] file2) {
		this.file2 = file2;
	}
	
	/** 
	 * 
	 * @return
	 */
	public double getPercentage() {
		return percentage;
	}
	
	/** 
	 * 
	 * @param percentage
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	/** 
	 * 
	 * @param id
	 * @param submission1
	 * @param submission2
	 * @param filename1
	 * @param filename2
	 * @param file1
	 * @param file2
	 * @param percentage
	 */
	public Result(int id, int submission1, int submission2, String filename1, String filename2, byte[] file1, byte[] file2,
			double percentage) {
		this.id = id;
		this.submission1 = submission1;
		this.submission2 = submission2;
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.file1 = file1;
		this.file2 = file2;
		this.percentage = percentage;
	}
	
	/**
	 * 
	 */
	public Result() {
	}
	
	
	
	
	

}
