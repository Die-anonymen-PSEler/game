package com.retroMachines.data.models;

import java.util.HashMap;

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

	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `globalVariables` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`key`\tTEXT NOT NULL,\n" +
            "\t`value`\tTEXT\n" +
            ");";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 * please specify values in the follwing order
	 * key, value, id (where clause)
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `globalVariables` SET `key` = '%s', `value` = '%s' WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `globalVariables` WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `globalVariables` VALUES (null, '%s','%s');";

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
	}

	/**
	 * Creates a new instance of GlobalVariables and saves it to the attribute.
	 */
	private static void instantiate() {
		instance = new GlobalVariables();
	}

	/**
	 * Fetches the value from the persistent background storage.
	 * @param key the key of the value.
	 * @return the value that is associated with the key.
	 */
	private String fetchFromBackgroundStorage(String key) {
		return null;
	}

	/**
	 * This function checks if the persistent background storage has a record.
	 * @return true if a record exists
	 */
	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Stores all keyValue pairs in the database for persistent storage.
	 */
	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
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

	}

	/**
	 * Returns the value according to the key. 
	 * @param key of the key value pair for lookup
	 * @return the value that was found. null if the key does not exist.
	 */
	public String get(String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			String result = fetchFromBackgroundStorage(key);
			map.put(key, result);
			writeToSQL();
			return result;
		}
	}
}
