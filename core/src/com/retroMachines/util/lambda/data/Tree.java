package com.retroMachines.util.lambda.data;

import java.util.List;

public class Tree {
	
	private Number color;
	private List<Family> family;
	
	public Number getColor() {
		return this.color;
	}
	public void setColor(Number color) {
		this.color = color;
	}
	public List<Family> getFamiliy() {
		return this.family;
	}
	public void setFamily(List<Family> family) {
		this.family = family;
	}

}
