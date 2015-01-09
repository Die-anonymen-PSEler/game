package com.retroMachines.data.models;

public class Statistic extends Model {
	
	private static final String TABLE_NAME = "statistics";
	
	private static final String CREATE_TABLE_QUERY = "";
	
	private static final String UPDATE_TABLE_QUERY_PATTERN = "";
	
	private static final String DELETE_TABLE_QUERY_PATTERN = "";
	
	private static final String INSERT_TABLE_QUERY_PATTERN = "";
	
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
		super();
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

	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return false;
	}
}
