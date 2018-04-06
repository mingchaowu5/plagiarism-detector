package edu.northeastern.cs5500.models.extras;

public class Edge {
	private int from;
	private int to;
	private double value;
	
	public Edge(int from, int to, double value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
