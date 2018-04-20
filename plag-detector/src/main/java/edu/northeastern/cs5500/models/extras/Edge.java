package edu.northeastern.cs5500.models.extras;

/** 
 * 
 * @author varunnandu
 *
 */
public class Edge {
	private int from;
	private int to;
	private double value;
	
	/** 
	 * 
	 * @param from
	 * @param to
	 * @param value
	 */
	public Edge(int from, int to, double value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
	
	/** 
	 * 
	 */
	public Edge() {
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getFrom() {
		return from;
	}
	
	/** 
	 * 
	 * @param from
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getTo() {
		return to;
	}
	
	/**
	 * 
	 * @param to
	 */
	public void setTo(int to) {
		this.to = to;
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
	
	
}
