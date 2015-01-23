package com.retroMachines.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;
import com.retroMachines.data.RetroDatabase;

/**
 * This class is part of the model of RetroMachines.
 * It has knowledge about all attributes regarding the profile of a user.
 * It saves information on every change that is made to this class.
 * @author RetroFactory
 */
public class Profile extends Model {

	/**
	 * The name of the table where the profiles are stored.
	 */
	public static final String TABLE_NAME = "profiles";
	
	private static final String KEY_ID = "id";
	
	private static final String KEY_PROFILE_NAME = "name";
	
	private static final String KEY_STATISTIC = "statisticId";
	
	private static final String KEY_SETTING = "settingId";

	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT NOT NULL,\n" +
            "\t`statisticId`\tINTEGER NOT NULL,\n" +
            "\t`settingId`\tINTEGER NOT NULL\n" +
            ");";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 * the order is name -> statisticId -> settingId -> rowId
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `" + TABLE_NAME + "` SET `name` = '%s', `statisticId` = '%s', `settingId` = '%s' WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `" + TABLE_NAME + "` WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 * name -> statisticId -> settingId
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `" + TABLE_NAME + "` VALUES (null, '%s', '%s', '%s');";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that selects a row
	 * within the table
	 * 0 -> id, 1 -> name, 2 -> statisticId, 3 -> settingId
	 */
	public static final String SELECT_TABLE_QUERY_PATTERN = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + TABLE_NAME + "`.`id` = %s;";

	/**
	 * the name of the profile
	 */
	private String profileName;

	/**
	 * the settings for the profile
	 */
	private Setting setting;

	/**
	 * the statistics belonging to the profile
	 */
	private Statistic statistic;

	/**
	 * Creates a new profile and 
	 * 
	 * @param name
	 *            Name of the profile
	 * @param profileId
	 *            Id of the profile
	 * @param setting
	 *            settings of the profile
	 * @param statistic
	 *            statistics of the profile
	 */
	public Profile(String name, int profileId, Setting setting,
			Statistic statistic) {
		super();
		this.setProfileName(name);
		this.rowId = profileId;
		this.setSetting(setting);
		this.statistic = statistic;
	}
	
	/**
	 * Creates a new profile and attempts to fetch the further data form the 
	 * persistent background storage.
	 * @param rowId the id of the row where the record is stored
	 */
	public Profile(int rowId) {
		super();
		this.rowId = rowId;
		fetchFromSQL();
	}

	@Override
	public void writeToSQL() {
		Statement statement = getStatement();
		if (hasRecordInSQL()) {
			try {
				statement.executeUpdate(String.format(UPDATE_TABLE_QUERY_PATTERN, profileName, statistic.rowId, setting.rowId, rowId));
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				this.rowId = statement.executeUpdate(String.format(INSERT_TABLE_QUERY_PATTERN, profileName, statistic.rowId, setting.rowId), Statement.RETURN_GENERATED_KEYS);
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		if (rowId != -1) {
			Statement statement = getStatement();
			ResultSet rs;
			try {
				rs = statement.executeQuery(String.format(SELECT_TABLE_QUERY_PATTERN, rowId));
				int size = RetroDatabase.countResultSet(rs);
				statement.close();
				rs.close();
				return size == 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		if (hasRecordInSQL()) {
			Statement statement = getStatement();
			ResultSet rs;
			try {
				rs = statement.executeQuery(String.format(SELECT_TABLE_QUERY_PATTERN, rowId));
				profileName = rs.getString(KEY_PROFILE_NAME);
				setting = new Setting(rs.getInt(KEY_SETTING));
				statistic = new Statistic(rs.getInt(KEY_STATISTIC));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	/*
	 * Getter and Setter
	 */

	public void setStatistic(Statistic statistic2) {
		this.statistic = statistic2;		
	}

	/**
	 * Get method to retrieve the name of the profile.
	 * @return the name of the profile
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Assigns a new profile name to the profile.
	 * @param profileName the new name for the profile.
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * Get method to retrieve the profile's id.
	 * @return the id of the profile
	 */
	public int getProfileId() {
		return rowId;
	}

	/**
	 * Get method to retrieve the profile's settings.
	 * @return the settings of the profile
	 */
	public Setting getSetting() {
		return setting;
	}

	/**
	 * Assigns a new settings object to the profile.
	 * @param setting the setting to assign
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	
	/**
	 * Get method for the statistics of the profile
	 * @return the statistic attribute associated with this profile
	 */
	public Statistic getStatistic() {
		// TODO Auto-generated method stub
		return null;
	}

}
