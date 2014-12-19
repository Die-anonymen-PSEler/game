package graph;

import java.util.Set;

public class Machines extends Vertex {

	private int id;
	private Set<Vertex> outputList;
	private LambdaTree input;
	
	public void betaReduction() {
		for (Vertex v : outputList) {
			if (v.getId() == id) {
				v.setContent(input);
			}
		}
	}
	
}
