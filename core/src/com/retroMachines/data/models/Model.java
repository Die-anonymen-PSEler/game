package com.retroMachines.data.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.retroMachines.data.RetroDatabase;

/**
 * This class is part of the model of RetroMachines.
 * This class is abstract and needs to be implemented by all other model classes.
 * It retrieves a copy of the database within it's constructor so children classes can execute their 
 * implemented methods.
 * @author RetroFactory
 */
public abstract class Model {
	
	/**
	 * the timeout for a query to be executed
	 */
	private static final int QUERY_TIMEOUT = 10;

	/**
	 * The database connection that will execute queries to the SQLite file.
	 */
	protected Connection connection;
	
	/**
	 * The id of the row where the record is stored within the database.
	 */
	protected int rowId;
	
	/**
	 * Creates a new Model instance and retrieves a copy of the database connection
	 * in order to update, insert and select data from it.
	 */
	public Model() {
		rowId = -1;
		connection = RetroDatabase.getConnection();
	}
	
	protected Statement getStatement() {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(QUERY_TIMEOUT);  // set timeout to 30 sec.
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks if the database has a previous copy for the model.
	 * @return true if the background storage has a record for the model; false otherwise.
	 */
	public abstract boolean hasRecordInSQL();
	
	/**
	 * This method saves the model and all it's attributes to the persistent background storage.
	 */
	public abstract void writeToSQL();
	
	/**
	 * This method attempts to retrieve a copy of the model from the persistent 
	 * background storage and saves it to this object.
	 */
	public abstract void fetchFromSQL();
	
	/**
	 * Destroys the object and removes it from the persistent background storage
	 */
	public abstract void destroy();
}
