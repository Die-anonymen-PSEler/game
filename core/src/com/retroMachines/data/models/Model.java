package com.retroMachines.data.models;

import com.badlogic.gdx.sql.Database;
import com.retroMachines.data.RetroDatabase;

public abstract class Model {
	
	/**
	 * The database connection that will execute queries to the SQLite file
	 */
	protected Database db;
	
	/**
	 * the id of the row where the record is stored within the database
	 */
	protected int rowId;
	
	/**
	 * Makes a single instance of RetroDatabase
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
	
	/**
	 * attempts to fetch records from the background storage and saves them within the model
	 */
	public abstract void fetchFromSQL();
}
