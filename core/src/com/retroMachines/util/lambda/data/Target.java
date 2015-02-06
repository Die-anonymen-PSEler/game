
package com.retroMachines.util.lambda.data;

import java.util.List;

public class Target{
   	private Number color;
   	private List<Target> target;
   	private String type;

 	public Number getColor(){
		return this.color;
	}
	public void setColor(Number color){
		this.color = color;
	}
 	public List<Target> getTarget(){
		return this.target;
	}
	public void setTarget(List<Target> target){
		this.target = target;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
