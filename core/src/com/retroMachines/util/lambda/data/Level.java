
package com.retroMachines.util.lambda.data;

import java.util.List;

public class Level{
   	private Data data;
   	private String description;
   	private boolean hasTutorialScreen;
   	private Number levelid;

 	public Data getData(){
		return this.data;
	}
	public void setData(Data data){
		this.data = data;
	}
 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public boolean getHasTutorialScreen(){
		return this.hasTutorialScreen;
	}
	public void setHasTutorialScreen(boolean hasTutorialScreen){
		this.hasTutorialScreen = hasTutorialScreen;
	}
 	public Number getLevelid(){
		return this.levelid;
	}
	public void setLevelid(Number levelid){
		this.levelid = levelid;
	}
}
