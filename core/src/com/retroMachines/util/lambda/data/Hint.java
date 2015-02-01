
package com.retroMachines.util.lambda.data;

import java.util.List;

public class Hint{
   	private Number color;
   	private List<Family> family;
   	private String type;

 	public Number getColor(){
		return this.color;
	}
	public void setColor(Number color){
		this.color = color;
	}
 	public List<Family> getFamily(){
		return this.family;
	}
	public void setFamily(List<Family> family){
		this.family = family;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
