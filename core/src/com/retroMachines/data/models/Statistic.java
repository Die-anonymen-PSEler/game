package com.retroMachines.data.models;

/**
 * Statistics Class This class holds all information regarding the statistics of
 * a single profile Every information stored within this class will be backed up
 * to the SQLite database
 * 
 * @author RetroFactory
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
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
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
	 * creates a new instance of Statistic and assigns all the variables to the
	 * instance
	 * 
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
	
	/**
	 * creates a new statistic object and attempts to fetch further information 
	 * from the background storage
	 * @param rowId the id of the row where the record is stored within the background storage
	 */
	public Statistic(int rowId) {
		super();
		this.rowId = rowId;
		fetchFromSQL();
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

	/*
	 * Getter and Setter
	 */

	/**
	 * getter which returns the number of different completed Levels
	 * 
	 * @return number of completed Levels
	 */
	public int getLevelsComplete() {
		return levelsComplete;
	}

	/**
	 * setter which stores the Number of Completed Levels
	 * 
	 * @param levelsComplete
	 *            number of completed Levels
	 */
	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
	}

	/**
	 * Getter for the Time the Player played the Game in sum
	 * 
	 * @return time the Player gamed
	 */
	public int getPlaytime() {
		return playtime;
	}

	/**
	 * Sets new Playtime
	 * 
	 * @param playtime
	 *            new Playtime
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	/**
	 * Returns the number of steps the player made with his GameCharacter in Sum
	 * 
	 * @return number of Steps of the Gamecharacter
	 */
	public int getStepCounter() {
		return stepCounter;
	}

	/**
	 * Sets number of Steps which the Gamecharacter made
	 * 
	 * @param stepCounter
	 *            new Number of Steps
	 */
	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}

	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
	}

}
