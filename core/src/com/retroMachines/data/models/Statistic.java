package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the statistics of a user. It saves information on every
 * change that is made to this class.
 * 
 * @author RetroFactory
 */
public class Statistic extends Model {

	public static final int DEFAULT_PLAYTIME = 0;

	public static final int DEFAULT_STEPCOUNTER = 0;

	public static final int DEFAULT_LEVELCOMPLETED = 0;

	/**
	 * the name of the table where the statistics are stored
	 */
	public static final String TABLE_NAME = "statistics";

	private static final String KEY_ID = "id";

	private static final String KEY_PLAYTIME = "playtime";

	private static final String KEY_LEVELCOMPLETED = "levelCompleted";

	private static final String KEY_STEPCOUNTER = "stepCounter";

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
	public Statistic(int rowId, int playtime, int levelsComplete, int stepCounter) {
		super();
		this.setPlaytime(playtime);
		this.setLevelsComplete(levelsComplete);
		this.setStepCounter(stepCounter);
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		write();
	}

	/**
	 * creates a new statistic object and attempts to fetch further information
	 * from the background storage
	 * 
	 * @param rowId
	 *            the id of the row where the record is stored within the
	 *            background storage
	 */
	public Statistic(int rowId) {
		super();
		this.rowId = rowId;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		fetch();
	}

	/*
	 * implemented methods
	 */

	@Override
	public void write() {
		pref.putInteger(KEY_LEVELCOMPLETED, levelsComplete);
		pref.putInteger(KEY_PLAYTIME, playtime);
		pref.putInteger(KEY_STEPCOUNTER, stepCounter);
		pref.flush();
	}

	@Override
	public boolean hasRecord() {
		return false;
	}

	@Override
	public void fetch() {
		levelsComplete = pref.getInteger(KEY_LEVELCOMPLETED, DEFAULT_LEVELCOMPLETED);
		playtime = pref.getInteger(KEY_PLAYTIME, DEFAULT_PLAYTIME);
		stepCounter = pref.getInteger(KEY_STEPCOUNTER, DEFAULT_STEPCOUNTER);
	}

	@Override
	public void destroy() {
		pref.putInteger(KEY_LEVELCOMPLETED, DEFAULT_LEVELCOMPLETED);
		pref.putInteger(KEY_PLAYTIME, DEFAULT_PLAYTIME);
		pref.putInteger(KEY_STEPCOUNTER, DEFAULT_STEPCOUNTER);
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Set method to assign a new amount of levels that were completed by the
	 * user.
	 * 
	 * @param levelsComplete
	 *            the number of completed levels.
	 */
	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
		write();
	}

	/**
	 * Sets number of steps which the character made.
	 * 
	 * @param stepCounter
	 *            new number of steps made by the character.
	 */
	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
		write();
	}

	/**
	 * Assigns a new play time to this class.
	 * 
	 * @param playtime
	 *            the name play time as an integer.
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
		write();
	}
	
	/**
	 * Get method for the completed levels.
	 * 
	 * @return number of completed Levels
	 */
	public int getLevelsComplete() {
		return levelsComplete;
	}

	/**
	 * Returns the number of steps the player made with the character.
	 * 
	 * @return number of steps made by the character.
	 */
	public int getStepCounter() {
		return stepCounter;
	}
	
	/**
	 * Get method that returns the time the user placed as an integer.
	 * 
	 * @return the time the player has played already.
	 */
	public int getPlaytime() {
		return playtime;
	}
}
