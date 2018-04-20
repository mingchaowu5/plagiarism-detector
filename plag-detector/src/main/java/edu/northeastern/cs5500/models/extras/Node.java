package edu.northeastern.cs5500.models.extras;


/** 
 * 
 * @author varunnandu
 *
 */
public class Node {
	private int id;
	private double value;
	private String label;
	
	/** 
	 * 
	 * @param id
	 * @param value
	 * @param label
	 */
	
	public Node(int id, double value, String label) {
		this.id = id;
		this.value = value;
		this.label = label;
	}
	
	/**
	 * 
	 */
	public Node() {
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
	public double getValue() {
		return value;
	}
	
	/** 
	 * 
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/** 
	 * 
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
