package edu.northeastern.cs5500.models.assignment;

/** 
 * 
 * @author varunnandu
 *
 */
public class Language {

	private String name;
	private String code;
	private int id;
	
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
	public String getCode() {
		return code;
	}
	
	/** 
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param name
	 * @param code
	 * @param id
	 */
	public Language(String name, String code, int id) {
		super();
		this.name = name;
		this.code = code;
		this.id = id;
	}
	
	/** 
	 * 
	 */
	public Language() {
		
	}
}
