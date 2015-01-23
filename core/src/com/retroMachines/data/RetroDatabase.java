package com.retroMachines.data;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

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
	private static Database dbHandler;
	
	/**
	 * The name of the database in the sqlite file.
	 */
	public static final String DATABASE_NAME = "retroMachines.db";
	
	/**
	 * The version of the database.
	 */
	public static final int DATABASE_VERSION = 1;
	
	/**
	 * The query that should be executed when the database needs to be created.
	 */
	public static final String DATABASE_CREATE = "";
	
	/**
	 * Private constructor to avoid double creation of the database connection.
	 */
	private RetroDatabase(){
		
	}
	
	/**
	 * Instantiates a new singleton.
	 */
	private static void createSingleton() {
		dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, DATABASE_CREATE, null);
		dbHandler.setupDatabase();
		
		try {
			dbHandler.openOrCreateDatabase();
			dbHandler.setupDatabase();
		}
		catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the only copy of the database.
	 * @return The database reference.
	 */
	public static Database getSingleton() {
		if (dbHandler == null) {
			createSingleton();
		}
		return dbHandler;
	}
	
	public static void closeDatabase() {
		try {
			dbHandler.closeDatabase();
		} catch (SQLiteGdxException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	public static void reopenDatabase() {
		try {
			dbHandler.openOrCreateDatabase();
		} catch (SQLiteGdxException e) {
			// TODO Auto-generated catch block
			
		}
	}
}
