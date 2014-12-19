package graph;

import java.util.Set;

public abstract class Vertex extends LambdaTree{
	
	private int id;
	private Set<Vertex> edgeList; //list of adjacent vertices
	private LambdaTree content;
	
	
	public int getId() {
		return id;
	}
	
	public void setContent(LambdaTree tree) {
		content = tree;
	}
	

}
