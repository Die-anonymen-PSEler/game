package com.retroMachines.data.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
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
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 * the order is name -> statisticId -> settingId -> rowId
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `" + TABLE_NAME + "` SET `name` = ?, `statisticId` = ?, `settingId` = ? WHERE id = ?;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `" + TABLE_NAME + "` WHERE id = ?;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 * name -> statisticId -> settingId
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `" + TABLE_NAME + "`(name, statisticId, settingId) VALUES (?, ?, ?);";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that selects a row
	 * within the table
	 * 0 -> id, 1 -> name, 2 -> statisticId, 3 -> settingId
	 */
	public static final String SELECT_TABLE_QUERY_PATTERN = "SELECT * FROM " + TABLE_NAME + " WHERE profiles.id LIKE ?;";

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
	public Profile(String name, Setting setting,
			Statistic statistic) {
		super();
		this.profileName = name;
		this.setting = setting;
		this.statistic = statistic;
		writeToSQL();
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
		if (hasRecordInSQL()) {
			try {
				PreparedStatement ps = connection.prepareStatement(UPDATE_TABLE_QUERY_PATTERN);
				ps.setString(1, profileName);
				ps.setInt(2, statistic.rowId);
				ps.setInt(3, setting.rowId);
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
				
				ps.setString(1, profileName);
				ps.setInt(2, statistic.rowId);
				ps.setInt(3, setting.rowId);
				ps.executeUpdate();
				ps.close();
				Statement st = getStatement();
				ResultSet generatedKeys = st.executeQuery("SELECT last_insert_rowid()");
				if (generatedKeys.next()) {
					this.rowId = generatedKeys.getInt(1);
				}
				generatedKeys.close();
				st.close();
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "UPDATE query failed | write2sql");
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
				Gdx.app.error("SQLException", "UPDATE query failed | hasrecordinsql");
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
				int settingId = rs.getInt(KEY_SETTING);
				int statisticId = rs.getInt(KEY_STATISTIC);
				profileName = rs.getString(KEY_PROFILE_NAME);
				rs.close();
				ps.close();
				setting = new Setting(settingId);
				statistic = new Statistic(statisticId);
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "SELECT query failed | fetchfromsql");
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
				ps.close();
			} catch (SQLException e) {
				Gdx.app.error("SQLException", "DELETE query failed | destroy");
			}
		}
	}
	
	public static String[] getAllProfiles() {
		if (connection == null) {
			connection = RetroDatabase.getConnection();
		}
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_QUERY_PATTERN);
			ps.setString(1, "%");
			ResultSet rs = ps.executeQuery();
			int size = RetroDatabase.countResultSet(rs);
			rs.close();
			rs = ps.executeQuery();
			String[] result = new String[size];
			int counter = 0;
			while(rs.next()) {
				result[counter] = rs.getString(KEY_PROFILE_NAME);
				counter++;
			}
			rs.close();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HashMap<String, Integer> getProfileNameIdMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (connection == null) {
			connection = RetroDatabase.getConnection();
		}
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_QUERY_PATTERN);
			ps.setString(1, "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(KEY_PROFILE_NAME), rs.getInt(KEY_ID));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/*
	 * Getter and Setter
	 */

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
		writeToSQL();
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
		writeToSQL();
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
		writeToSQL();
	}
	
	/**
	 * Get method for the statistics of the profile
	 * @return the statistic attribute associated with this profile
	 */
	public Statistic getStatistic() {
		return this.statistic;
	}

}
