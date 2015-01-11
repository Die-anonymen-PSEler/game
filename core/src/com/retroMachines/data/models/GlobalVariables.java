package com.retroMachines.data.models;

import java.util.HashMap;

public class GlobalVariables extends Model {

	/**
	 * the name of the table where the globalVariables are stored
	 */
	public static final String TABLE_NAME = "globalVariables";

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
	 * HashMap to store keyValue-pairs for faster access
	 */
	private HashMap<String, String> map;

	/**
	 * Private Instance of it selfe
	 */
	private static GlobalVariables instance;

	/**
	 * Private Constructor which sets new empty map
	 */
	private GlobalVariables() {
		super();
		map = new HashMap<String, String>();
	}

	/**
	 * instantiate a global variable
	 */
	private static void instantiate() {
		instance = new GlobalVariables();
	}

	/**
	 * Fetches the variable of the background storage
	 * 
	 * @param key
	 *            the key of the variable
	 * @return the variable
	 */
	private String fetchFromBackgroundStorage(String key) {
		return null;
	}

	/**
	 * this function controls if the variable has an record in SQL
	 * 
	 * @return true if a record exists
	 */
	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * writes the variable to the SQL
	 */
	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}

	/*
	 * Getter and Setter of this class
	 */

	/**
	 * getter for this class
	 * 
	 * @return this class
	 */
	public static GlobalVariables getSingleton() {
		if (instance == null) {
			instantiate();
		}
		return instance;
	}

	/**
	 * places the variable
	 * 
	 * @param key
	 *            the key of the variable
	 * @param value
	 *            the value of the variable
	 */
	public void put(String key, String value) {

	}

	/**
	 * gets the variable
	 * 
	 * @param key
	 *            the key of the variable
	 * @return the variable
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

	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
	}

}
