package edu.northeastern.cs5500.models.extras;

import java.util.List;


/** 
 * 
 * @author varunnandu
 *
 */
public class Graph {
	private List<Node> nodes;
	private List<Edge> edges;
	
	/** 
	 * 
	 * @param nodes
	 * @param edges
	 */
	
	public Graph(List<Node> nodes, List<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	
	/** 
	 * 
	 * @return
	 */
	public List<Node> getNodes() {
		return nodes;
	}
	
	/** 
	 * 
	 * @param nodes
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	/** 
	 * 
	 * @return
	 */
	public List<Edge> getEdges() {
		return edges;
	}
	
	/** 
	 * 
	 * @param edges
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	
}
