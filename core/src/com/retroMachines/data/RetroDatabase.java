package com.retroMachines.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Database is part of the controller of MetroMachines.
 * It manages the data of the game:
 * The current state of the game, the settings, the accessible levels, profiles etc.
 * As a singleton only one instance can be created.
 * @author RetroFactory
 *
 */
public class RetroDatabase {
	
	/**
	 * A copy of the database.
	 */
	private static Connection connection;
	
	/**
	 * The name of the database in the sqlite file.
	 */
	public static final String DATABASE_NAME = "assets/db/testcase.db";
	
	/**
	 * Private constructor to avoid double creation of the database connection.
	 */
	private RetroDatabase(){
		
	}
	
	/**
	 * Instantiates a new singleton.
	 */
	private static void createSingleton() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
			connection.setAutoCommit(true);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the only copy of the database.
	 * @return The database reference.
	 */
	public static Connection getConnection() {
		if (connection == null) {
			createSingleton();
		}
		return connection;
	}
	
	public static void closeDatabase() {
		if (connection != null) {
			// check if connection was initialized
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void reopenDatabase() {
		createSingleton();
	}
	
	public static int countResultSet(ResultSet set) {
		int counter = 0;
		try {
			while(set.next()) {
				counter++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return counter;
	}
}
