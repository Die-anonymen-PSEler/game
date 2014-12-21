package com.retroMachines.data.models;

public class Statistic {
	
	/**
	 * the play time the player has spent on the game in minutes
	 */
	private int playtime = 0;
	
	/**
	 * the amount of levels that where completed by the player
	 */
	private int levelsComplete = 0;
	
	/**
	 * the amount of steps the player has made with his game character
	 */
	private int stepCounter = 0;
	
	/**
	 * creates a new instance of Statistic and assigns all the variables to the instance
	 * @param playtime
	 * @param levelsComplete
	 * @param stepCounter
	 */
	public Statistic(int playtime, int levelsComplete, int stepCounter) {
		this.setPlaytime(playtime);
		this.setLevelsComplete(levelsComplete);
		this.setStepCounter(stepCounter);
	}

	public int getLevelsComplete() {
		return levelsComplete;
	}

	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
	}

	public int getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	public int getStepCounter() {
		return stepCounter;
	}

	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}
}
