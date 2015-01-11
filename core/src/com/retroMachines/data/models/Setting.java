package com.retroMachines.data.models;

/**
 * Stores the Setting informations of the Game.
 * Every information stored within this class will be backed up to the SQLite database.
 * 
 * @author RetroFactory
 * 
 */
public class Setting extends Model {

	/**
	 * the name of the table where the settings are stored
	 */
	public static final String TABLE_NAME = "settings";

	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "";

	/**
	 * the volume of the game range 0.0f to 1.0f
	 */
	private float volume;

	/**
	 * true if sound is enabled. false otherwise
	 */
	private boolean soundOnOff;

	/**
	 * true if leftControl is enabled
	 */
	private boolean leftControl;

	/**
	 * Constructor which creates an Instance of Settings with all needed
	 * Attributs
	 * 
	 * @param leftControl
	 *            true if in Game the Controlbuttons of RetroMan changed Sites
	 *            (Steering on the right not Left and Jump and Interact Button
	 *            on Left)
	 * @param soundOnOff
	 *            true if sound is enabled. false otherwise
	 * @param volume
	 *            actual volume setting
	 */
	public Setting(boolean leftControl, boolean soundOnOff, float volume) {
		super();
		this.volume = volume;
		this.soundOnOff = soundOnOff;
		this.leftControl = leftControl;
	}
	
	/**
	 * Constructor which creates a new instance of setting and attempts to
	 * find forther attributes with the row id
	 * @param rowId the id of the row where the record regarding this setting is stored.
	 */
	public Setting(int rowId) {
		super();
		this.rowId = rowId;
		fetchFromSQL();	
	}

	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * @return the current volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(float volume) {
		if (volume > 1.0f || volume < 0.0f) {
			throw new IllegalArgumentException("new volume is out of range");
		}
		this.volume = volume;
	}

	/**
	 * @return true, if the sound is enabled; false otherwise
	 */
	public boolean isSoundOnOff() {
		return soundOnOff;
	}

	/**
	 * @param soundOnOff
	 *            the soundOnOff to set. True if the sound should be enabled,
	 *            false otherwise
	 */
	public void setSoundOnOff(boolean soundOnOff) {
		this.soundOnOff = soundOnOff;
	}

	/**
	 * @return the leftControl. True if leftControl is enabled, false otherwise
	 */
	public boolean isLeftControl() {
		return leftControl;
	}

	/**
	 * @param leftControl
	 *            the leftControl to set. True if the leftContol should be
	 *            enabled, false otherwise
	 */
	public void setLeftControl(boolean leftControl) {
		this.leftControl = leftControl;
	}

	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
	}

}
