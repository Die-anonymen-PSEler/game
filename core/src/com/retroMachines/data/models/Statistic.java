package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the statistics of a user. It saves information on every
 * change that is made to this class.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Statistic extends Model {

	/**
	 * The key where the play-time value is stored.
	 */
	private static final String KEY_PLAYTIME = "playtime";

	/**
	 * The key where the level-completed value is stored.
	 */
	private static final String KEY_LEVELCOMPLETED = "levelCompleted";

	/**
	 * The key where the step-counter value is stored.
	 */
	private static final String KEY_STEPCOUNTER = "stepCounter";
	
	/**
	 * The default value regarding the play-time.
	 */
	public static final int DEFAULT_PLAYTIME = 0;

	/**
	 * The default value regarding the step-counter.
	 */
	public static final int DEFAULT_STEPCOUNTER = 0;

	/**
	 * The default value regarding if the level is completed.
	 */
	public static final int DEFAULT_LEVELCOMPLETED = 0;

	/**
	 * The name of the table where the statistics are stored.
	 */
	public static final String TABLE_NAME = "statistics";

	/**
	 * The play time the player has spent on the game in minutes.
	 */
	private float playtime = 0;

	/**
	 * The amount of levels that where completed by the player.
	 */
	private int levelsComplete = 0;

	/**
	 * The amount of steps the player has made with his game character.
	 */
	private int stepCounter = 0;

	/**
	 * Creates a new instance of "Statistic" and assigns all the variables to the
	 * instance.
	 * 
	 * @param rowId
	 *            The ID of the row where it should be stored.
	 * @param playtime The time that was already played.
	 * @param levelsComplete How many levels were completed.
	 * @param stepCounter How many steps were made.
	 */
	public Statistic(int rowId, int playtime, int levelsComplete,
			int stepCounter) {
		this(rowId, (float) playtime, levelsComplete, stepCounter);
	}

	/**
	 * Creates a new instance of "Statistic" and assigns all the variables to the
	 * instance.
	 * 
	 * @param rowId The ID of the row where it should be stored.
	 * @param playtime The time that was already played.
	 * @param levelComplete How many levels were completed.
	 * @param stepCounter How many steps were made.
	 */
	public Statistic(int rowId, float playtime, int levelComplete,
			int stepCounter) {
		super();
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		this.setPlaytime(playtime);
		this.setLevelsComplete(levelComplete);
		this.setStepCounter(stepCounter);
		write();
	}

	/**
	 * Creates a new statistic object and attempts to fetch further information
	 * from the background storage.
	 * 
	 * @param rowId
	 *            The ID of the row where the record is stored within the
	 *            background storage.
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

	/**
	 * To edit the statistics.
	 */
	@Override
	public void write() {
		pref.putInteger(KEY_LEVELCOMPLETED, levelsComplete);
		pref.putFloat(KEY_PLAYTIME, playtime);
		pref.putInteger(KEY_STEPCOUNTER, stepCounter);
		pref.flush();
	}

	/**
	 * If a record exists.
	 */
	@Override
	public boolean hasRecord() {
		return false;
	}

	/**
	 * Fetches the statistic.
	 */
	@Override
	public void fetch() {
		levelsComplete = pref.getInteger(KEY_LEVELCOMPLETED,
				DEFAULT_LEVELCOMPLETED);
		playtime = pref.getFloat(KEY_PLAYTIME, DEFAULT_PLAYTIME);
		stepCounter = pref.getInteger(KEY_STEPCOUNTER, DEFAULT_STEPCOUNTER);
	}

	/**
	 * Resets the statistics.
	 */
	@Override
	public void destroy() {
		this.levelsComplete = DEFAULT_LEVELCOMPLETED;
		this.playtime = DEFAULT_PLAYTIME;
		this.stepCounter = DEFAULT_STEPCOUNTER;
		write();
	}

	/*
	 * Getter and Setter
	 */
	
	/**
	 * Get method for the completed levels.
	 * 
	 * @return Number of completed Levels
	 */
	public int getLevelsComplete() {
		return levelsComplete;
	}

	/**
	 * Returns the number of steps the player made with the character.
	 * 
	 * @return Number of steps made by the character.
	 */
	public int getStepCounter() {
		return stepCounter;
	}

	/**
	 * Get method that returns the time the user placed as an integer.
	 * 
	 * @return The time the player has played already.
	 */
	public float getPlaytime() {
		return playtime;
	}
	
	/**
	 * Set method to assign a new amount of levels that were completed by the
	 * user.
	 * 
	 * @param levelsComplete
	 *            The number of completed levels.
	 */
	public void setLevelsComplete(int levelsComplete) {
		this.levelsComplete = levelsComplete;
		write();
	}

	/**
	 * Sets number of steps which the character made.
	 * 
	 * @param stepCounter
	 *            New number of steps made by the character.
	 */
	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
		write();
	}

	/**
	 * Assigns a new play time to this class.
	 * 
	 * @param playtime
	 *            The name play time as an integer.
	 */
	public void setPlaytime(float playtime) {
		this.playtime = playtime;
		write();
	}
}
