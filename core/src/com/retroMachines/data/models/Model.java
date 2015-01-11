package com.retroMachines.data.models;

import com.badlogic.gdx.sql.Database;
import com.retroMachines.data.RetroDatabase;

public abstract class Model {
	
	/**
	 * The database connection that will execute queries to the sqlite file
	 */
	protected Database db;
	
	/**
	 * Makes an singele instantce of RetroDatabase
	 */
	public Model() {
		db = RetroDatabase.getSingleton();
	}
	
	/**
	 * Checks if the database has a possibly previous copy for the model
	 * @return true if a records exists; false otherwise
	 */
	public abstract boolean hasRecordInSQL();
	
	/**
	 * saves the model to the persistence background database
	 */
	public abstract void writeToSQL();
}
