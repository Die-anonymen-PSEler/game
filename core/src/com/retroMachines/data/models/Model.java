package com.retroMachines.data.models;

import com.badlogic.gdx.sql.Database;
import com.retroMachines.data.RetroDatabase;

public abstract class Model {
	
	/**
	 * The database connection that will execute queries to the SQLite file.
	 */
	protected Database db;
	
	/**
	 * The id of the row where the record is stored within the database.
	 */
	protected int rowId;
	
	/**
	 * Creates a new Model instance and retrieves a copy of the database connection
	 * in order to update, insert and select data from it.
	 */
	public Model() {
		db = RetroDatabase.getSingleton();
	}
	
	/**
	 * Checks if the database has a previous copy for the model.
	 * @return true if the background storage has a record for the model; false otherwise.
	 */
	public abstract boolean hasRecordInSQL();
	
	/**
	 * This method saves the model object to the persistent background storage.
	 */
	public abstract void writeToSQL();
	
	/**
	 * This method attempts to retrieve a copy of the model from the persistent 
	 * background storage and saves it to this object.
	 */
	public abstract void fetchFromSQL();
}
