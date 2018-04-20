package edu.northeastern.cs5500.models.extras;

public class Node {
	private int id;
	private double value;
	private String label;
	
	public Node(int id, double value, String label) {
		this.id = id;
		this.value = value;
		this.label = label;
	}
	
	public Node() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
