package com.retroMachines.data;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;

/**
 * The database manages the data of the game:
 * The current state of the game, the settings, the accessible levels, profiles etc.
 * @author RetroFactory
 *
 */
public class RetroDatabase implements Database {
	
	/**
	 * The name of the database in the sqlite file
	 */
	private static final String DATABASE_NAME = "retroMachines";
	
	/**
	 * a copy of the database
	 */
	private static RetroDatabase singleton;
	
	/**
	 * private constructor to avoid double creation of the database connection
	 */
	private RetroDatabase(){
		
	}
	
	/**
	 * Instantiates a new singleton
	 */
	private static void createSingleton() {
		singleton = new RetroDatabase();
		singleton.setupDatabase();
	}
	
	/**
	 * Returns the only copy of the database
	 * @return database reference
	 */
	public static RetroDatabase getSingleton() {
		if (singleton == null) {
			createSingleton();
		}
		return singleton;
	}

	/**
	 * closes the existing database
	 * @throws SQLiteGdxException is thrown when the database could not be closed
	 */
	@Override
	public void closeDatabase() throws SQLiteGdxException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * executes a SQL query
	 * @param arg0 the query as a String
	 * @throws SQLiteGdxException is thrown when the query could not be executed
	 */
	@Override
	public void execSQL(String arg0) throws SQLiteGdxException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Opens an existing database or creates a new one
	 * @throws SQLiteGdxException is thrown when the database could not be opened or created
	 */
	@Override
	public void openOrCreateDatabase() throws SQLiteGdxException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * executes a SQL query
	 * @param arg0 the query as a String
	 * @return a database cursor
	 * @throws SQLiteGdxException is thrown when the query could not be executed
	 */
	@Override
	public DatabaseCursor rawQuery(String arg0) throws SQLiteGdxException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLiteGdxException
	 */
	@Override
	public DatabaseCursor rawQuery(DatabaseCursor arg0, String arg1)
			throws SQLiteGdxException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * sets up the database
	 */
	@Override
	public void setupDatabase() {
		// TODO Auto-generated method stub
		
	}

}
