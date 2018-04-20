package edu.northeastern.cs5500.models.assignment;

/** 
 * 
 * @author varunnandu
 *
 */
public class Assignment {
	
	private int id;
	private String name;
	private int course;
	private int langId;
	
	/** 
	 * 
	 * @return
	 */

	public int getLangId() {
		return langId;
	}
	
	/** 
	 * 
	 * @param langId
	 */
	public void setLangId(int langId) {
		this.langId = langId;
	}
	
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
	public String getName() {
		return name;
	}
	
	/** 
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getCourse() {
		return course;
	}
	
	/** 
	 * 
	 * @param course
	 */
	public void setCourse(int course) {
		this.course = course;
	}
	
	/** 
	 * 
	 * @param id
	 * @param name
	 * @param course
	 * @param lang
	 */
	public Assignment(int id, String name, int course, int lang) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.langId = lang;
	}
	/** 
	 * 
	 */
	public Assignment() {
		super();
	}
	
	

}
