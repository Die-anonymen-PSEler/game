package com.retroMachines.util.lambda;

import java.util.LinkedList;

public class Application extends Vertex {
	
	private LinkedList<Character> idlist;
	private boolean checked;

	
	public Application() {
		idlist = new LinkedList<Character>();
		checked = false;
	}
	
	public boolean setChecked() {
		checked = true;
		return checked;
	}

}
