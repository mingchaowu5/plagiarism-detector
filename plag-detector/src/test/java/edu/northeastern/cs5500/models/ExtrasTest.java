package edu.northeastern.cs5500.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.northeastern.cs5500.models.extras.Edge;
import edu.northeastern.cs5500.models.extras.Graph;
import edu.northeastern.cs5500.models.extras.Node;

public class ExtrasTest {
	Node n1 = new Node(10, 20.8, "Node 1");
	Node n2 = new Node(30, 40.90, "Node 2");
	
	Edge e1 = new Edge(30, 40, 89.6);
	Edge e2 = new Edge(50, 90, 95.6);
	
	List<Node> nlist = new ArrayList();
	List<Edge> elist = new ArrayList();
	
	
	@Test
	public void testNode() {
		Node n3 = new Node();
		n3.setId(100);
		n3.setValue(45.6);
		n3.setLabel("Node3");
		assertEquals(n3.getId(), 100);
		assertEquals(n3.getLabel(), "Node3");
		assertNotNull(n3.getValue());
	}
	
	@Test
	public void testEdge() {
		Edge n3 = new Edge();
		n3.setTo(20);;
		n3.setFrom(10);
		n3.setValue(90.2);
		assertNotNull(n3.getValue());
		assertEquals(n3.getTo(), 20);
		assertTrue(n3.getFrom() == 10);
	}
	
	@Test
	public void testGraph() {
		
		Graph n3 = new Graph(nlist, elist);
		nlist.add(n1);
		nlist.add(n2);
		
		elist.add(e1);
		elist.add(e2);
		n3.setEdges(elist);
		n3.setNodes(nlist);
		
		assertEquals(n3.getEdges(), elist);
		assertEquals(n3.getNodes(), nlist);
	}
	
}
