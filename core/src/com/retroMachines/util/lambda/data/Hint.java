
package com.retroMachines.util.lambda.data;

import java.util.List;

public class Hint{
   	private Number color;
   	private List<Hint> hint;
   	private String type;

 	public Number getColor(){
		return this.color;
	}
	public void setColor(Number color){
		this.color = color;
	}
 	public List<Hint> getHint() {
		return this.hint;
	}
	public void setHint(List<Hint> hint){
		this.hint = hint;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
