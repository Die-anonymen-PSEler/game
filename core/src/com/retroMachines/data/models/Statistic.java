package com.retroMachines.data.models;

/**
 * Statistics Class
 * This class holds all information regarding the statistics of a single profile
 * Every information stored within this class will be backed up to the sqlite database
 * @author Retro Factory
 *
 */
public class Statistic extends Model {
	
	/**
	 * the name of the table where the statistics are stored
	 */
	public static final String TABLE_NAME = "statistics";
	
	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that updates a
	 * row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that deletes a
	 * row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that inserts a
	 * row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "";
	
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
