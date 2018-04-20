package edu.northeastern.cs5500.models.file;

public class FileStructure {
	
	private int id;
	private int studentAssignment;
	private String file;
	
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
	public int getStudentAssignment() {
		return studentAssignment;
	}
	
	/** 
	 * 
	 * @param studentAssignment
	 */
	public void setStudentAssignment(int studentAssignment) {
		this.studentAssignment = studentAssignment;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getFile() {
		return file;
	}
	
	/** 
	 * 
	 * @param file
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	/** 
	 * 
	 * @param id
	 * @param studentAssignment
	 * @param file
	 */
	public FileStructure(int id, int studentAssignment, String file) {
		this.id = id;
		this.studentAssignment = studentAssignment;
		this.file = file;
	}
	
	/** 
	 * 
	 */
	public FileStructure() {
	}
	
	
	
	
}
