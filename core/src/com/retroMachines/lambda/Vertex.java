package com.retroMachines.lambda;

import java.util.Set;

public abstract class Vertex {
	
	private Set<Vertex> edges; //set of adjecent vertices
	
	public boolean setEdge(Vertex v) {
		return edges.add(v);
	}
	
	public abstract String toString();

}
