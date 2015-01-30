package com.retroMachines.data.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.badlogic.gdx.Gdx;
import com.retroMachines.data.RetroDatabase;

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
	
	private static final String KEY_ID = "id";
	
	private static final String KEY_PLAYTIME = "playtime";
	
	private static final String KEY_LEVELCOMPLETED = "levelCompleted";
	
	private static final String KEY_STEPCOUNTER = "stepCounter";

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
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `" + TABLE_NAME + "` SET `playtime` = ?, `levelCompleted` =?, `stepCounter` = ? WHERE id = ?;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `" + TABLE_NAME + "` WHERE id = ?;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `" + TABLE_NAME + "` VALUES (null, ?, ?, ?);";
	
	/**
	 * select query to fetch data from the sqlite database
	 */
	public static final String SELECT_TABLE_QUERY_PATTERN = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + TABLE_NAME + "`.`id` = ?;";
	
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
		writeToSQL();
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
		if (hasRecordInSQL()) {
			try {
				PreparedStatement ps = connection.prepareStatement(UPDATE_TABLE_QUERY_PATTERN);
				ps.setInt(1, playtime);
				ps.setInt(2, levelsComplete);
				ps.setInt(3, stepCounter);
				ps.setInt(4, rowId);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "UPDATE query failed | write2sql");
			}
		}
		else {
			try {
				PreparedStatement ps = connection.prepareStatement(INSERT_TABLE_QUERY_PATTERN);
				ps.setInt(1, playtime);
				ps.setInt(2, levelsComplete);
				ps.setInt(3, stepCounter);
				ps.executeUpdate();
				Statement st = getStatement();
				ResultSet generatedKeys = st.executeQuery(SELECT_LASTINSERTEDID);
				if (generatedKeys.next()) {
					this.rowId = generatedKeys.getInt(1);
				}
				generatedKeys.close();
				st.close();
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "INSERT / SELECT query failed | write2sql");
			}
		}
	}

	@Override
	public boolean hasRecordInSQL() {
		if (rowId != -1) {
			ResultSet rs;
			try {
				PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_QUERY_PATTERN);
				ps.setInt(1, rowId);
				rs = ps.executeQuery();
				int size = RetroDatabase.countResultSet(rs);
				ps.close();
				rs.close();
				return size == 1;
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "SELECT query failed | hasRecord method");
			}
		}
		return false;
	}
	
	@Override
	public void fetchFromSQL() {
		if (hasRecordInSQL()) {
			ResultSet rs;
			try {
				PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_QUERY_PATTERN);
				ps.setInt(1, rowId);
				rs = ps.executeQuery();
				playtime = rs.getInt(KEY_PLAYTIME);
				levelsComplete = rs.getInt(KEY_LEVELCOMPLETED);
				stepCounter = rs.getInt(KEY_STEPCOUNTER);
				rs.close();
				ps.close();
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "SELECT query failed | fetch method");
			}
		}
	}
	
	@Override
	public void destroy() {
		if (hasRecordInSQL()) {
			try {
				PreparedStatement ps = connection.prepareStatement(DELETE_TABLE_QUERY_PATTERN);
				ps.setInt(1, rowId);
				ps.executeUpdate();
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "DELETE query failed | destroy method");
			}
		}
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
		writeToSQL();
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
		writeToSQL();
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
		writeToSQL();
	}
}
