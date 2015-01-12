package com.retroMachines.data;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

/**
 * The database manages the data of the game:
 * The current state of the game, the settings, the accessible levels, profiles etc.
 * @author RetroFactory
 *
 */
public class RetroDatabase {
	
	/**
	 * a copy of the database
	 */
	private static Database dbHandler;
	
	/**
	 * The name of the database in the sqlite file
	 */
	public static final String DATABASE_NAME = "retroMachines";
	
	/**
	 * the version of the database
	 */
	public static final int DATABASE_VERSION = 1;
	
	/**
	 * The query that should be executed when the database needs to be created
	 */
	public static final String DATABASE_CREATE = "";
	
	/**
	 * private constructor to avoid double creation of the database connection
	 */
	private RetroDatabase(){
		
	}
	
	/**
	 * Instantiates a new singleton
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
	 * Returns the only copy of the database
	 * @return database reference
	 */
	public static Database getSingleton() {
		if (dbHandler == null) {
			createSingleton();
		}
		return dbHandler;
	}
}
