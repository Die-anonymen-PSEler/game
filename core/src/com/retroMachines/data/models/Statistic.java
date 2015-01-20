package com.retroMachines.data.models;

/**
 * This class is part of the model of RetroMachines.
 * It has knowledge about all attributes regarding the statistics of a user.
 * It saves information on every change that is made to this class.
 * @author RetroFactory
 */
public class Statistic extends Model {

	/**
	 * the name of the table where the statistics are stored
	 */
	public static final String TABLE_NAME = "statistics";

	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `statistics` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`playtime`\tINTEGER NOT NULL DEFAULT 0,\n" +
            "\t`levelCompleted`\tINTEGER NOT NULL DEFAULT 0,\n" +
            "\t`stepCounter`\tINTEGER NOT NULL DEFAULT 0\n" +
            ");";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `" + TABLE_NAME + "` SET `playtime` = '%s', `levelCompleted` = '%s', `stepCounter` = '%s' WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `" + TABLE_NAME + "` WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `" + TABLE_NAME + "` VALUES (null, '%s', '%s', '%s');";

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
	
	
	
	
	/*
	 * implemented methods
	 */

	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
	}

	
	
	/*
	 * Getter and Setter
	 */

	/**
	 * Get method for the completed levels.
	 * @return number of completed Levels
	 */
	public int getLevelsComplete() {
		return levelsComplete;
	}

	/**
	 * Set method to assign a new amount of levels that were completed by the user.
	 * @param levelsComplete the number of completed levels.
	 */
	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
	}

	/**
	 * Get method that returns the time the user placed as an integer.
	 * @return the time the player has played already.
	 */
	public int getPlaytime() {
		return playtime;
	}

	/**
	 * Assigns a new play time to this class.
	 * @param playtime the name play time as an integer.
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	/**
	 * Returns the number of steps the player made with the character.
	 * @return number of steps made by the character.
	 */
	public int getStepCounter() {
		return stepCounter;
	}

	/**
	 * Sets number of steps which the character made.
	 * @param stepCounter new number of steps made by the character.
	 */
	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}
}
