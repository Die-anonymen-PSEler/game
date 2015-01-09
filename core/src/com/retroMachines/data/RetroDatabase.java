package com.retroMachines.data;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;

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
	
	public static void createSingleton() {
		singleton = new RetroDatabase();
		singleton.setupDatabase();
	}
	
	public static RetroDatabase getSingleton() {
		if (singleton == null) {
			createSingleton();
		}
		return singleton;
	}
	

	@Override
	public void closeDatabase() throws SQLiteGdxException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execSQL(String arg0) throws SQLiteGdxException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openOrCreateDatabase() throws SQLiteGdxException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DatabaseCursor rawQuery(String arg0) throws SQLiteGdxException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatabaseCursor rawQuery(DatabaseCursor arg0, String arg1)
			throws SQLiteGdxException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setupDatabase() {
		// TODO Auto-generated method stub
		
	}

}
