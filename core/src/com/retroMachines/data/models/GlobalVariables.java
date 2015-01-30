package com.retroMachines.data.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.retroMachines.data.RetroDatabase;

/**
 * This class is part of the model of RetroMachines.
 * It has knowledge about all attributes regarding the game itself.
 * These attributes are for instance the last used profile.
 * @author RetroFactory
 */
public class GlobalVariables extends Model {

	/**
	 * the name of the table where the globalVariables are stored
	 */
	public static final String TABLE_NAME = "globalVariables";
	
	private static final String KEY_KEY = "key";
	
	private static final String KEY_VALUE = "value";
	
	/**
	 * the key for the lastusedprofile id
	 */
	public static final String KEY_LAST_USED_PROFILE = "lastUsedProfile";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 * please specify values in the follwing order
	 * key, value, id (where clause)
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `globalVariables` SET `key` = ?, `value` = ? WHERE key LIKE ?;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `globalVariables` WHERE id = ?;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `globalVariables` VALUES (null, ?, ?);";
	
	/**
	 * a pattern to select data from the database
	 */
	public static final String SELECT_TABLE_QUERY_PATTERN = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + TABLE_NAME + "`.`key` LIKE ?;";

	/**
	 * HashMap to store keyValue-pairs for faster access
	 */
	private HashMap<String, String> map;

	/**
	 * Private Instance of itself to implement the singleton pattern.
	 */
	private static GlobalVariables instance;

	/**
	 * Private constructor so this class can not be instantiated from the outside.
	 * Please use getSingleton to retrieve a copy of this class.
	 */
	private GlobalVariables() {
		super();
		map = new HashMap<String, String>();
		fetchFromSQL();
	}

	/**
	 * Creates a new instance of GlobalVariables and saves it to the attribute.
	 */
	private static void instantiate() {
		instance = new GlobalVariables();
	}


	/**
	 * This function checks if the persistent background storage has a record.
	 * @return true if a record exists
	 */
	@Override
	public boolean hasRecordInSQL() {
		for (String key : map.keySet()) {
			ResultSet rs;
			try {
				PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_QUERY_PATTERN);
				ps.setString(1, key);
				rs = ps.executeQuery();
				if (RetroDatabase.countResultSet(rs) == 0) {
					// key does not exist. write it back
					ps = connection.prepareStatement(INSERT_TABLE_QUERY_PATTERN);
					ps.setString(1, key);
					ps.setString(2, map.get(key).toString());
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		return true;
	}

	/**
	 * Stores all keyValue pairs in the database for persistent storage.
	 */
	@Override
	public void writeToSQL() {
		hasRecordInSQL();
		for (String key : map.keySet()) {
			try {
				PreparedStatement ps = connection.prepareStatement(UPDATE_TABLE_QUERY_PATTERN);
				ps.setString(1, key);
				ps.setString(2, map.get(key).toString());
				ps.setString(3, key);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void fetchFromSQL() {
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_QUERY_PATTERN);
			ps.setString(1, "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(KEY_KEY), rs.getString(KEY_VALUE));
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		// nothing to do. just override the value
	}

	
	
	/*
	 * Getter and Setter of this class
	 */

	/**
	 * Returns the only instance of this class. If none has been 
	 * created yet one will be created first.
	 * @return the singleton.
	 */
	public static GlobalVariables getSingleton() {
		if (instance == null) {
			instantiate();
		}
		return instance;
	}

	/**
	 * Stores a keyValue pair within this class.
	 * If the key already exists it will override the previous value. 
	 * @param key the key under which the value should be stored.
	 * @param value the value that wil be stored.
	 */
	public void put(String key, String value) {
		map.put(key, value);
		writeToSQL();
	}

	/**
	 * Returns the value according to the key. 
	 * @param key of the key value pair for lookup
	 * @return the value that was found. null if the key does not exist.
	 */
	public String get(String key) {
		return map.get(key);
	}
}
