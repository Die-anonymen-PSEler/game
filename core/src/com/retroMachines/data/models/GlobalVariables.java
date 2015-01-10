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
	 * a pattern (that should be formatted with printf or similar) that updates a
	 * row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that deletes a
	 * row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that inserts a
	 * row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "";
	
	/**
	 * HashMap to store keyValue-pairs for faster access
	 */
	private HashMap<String, String> map;
	
	private static GlobalVariables instance;
	
	private GlobalVariables() {
		super();
		map = new HashMap<String, String>();
	}
	
	private static void instanciate() {
		instance = new GlobalVariables();
	}
	
	public static GlobalVariables getSingleton() {
		if (instance == null) {
			instanciate();
		}
		return instance;
	}
	
	public void put(String key, String value) {
		
	}
	
	public String get(String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		}
		else {
			String result = fetchFromBackgroundStorage(key);
			map.put(key, result);
			writeToSQL();
			return result;
		}
	}
	
	private String fetchFromBackgroundStorage(String key) {
		return null;
	}
	
	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}

}
