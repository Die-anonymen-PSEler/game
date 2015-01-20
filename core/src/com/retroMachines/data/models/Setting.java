package com.retroMachines.data.models;

/**
 * This class is part of the model of RetroMachines.
 * It has knowledge about all attributes regarding the settings of a profile.
 * These attributes are saved every time a change to this settings file is made.
 * @author RetroFactory
 */
public class Setting extends Model {

	/**
	 * the name of the table where the settings are stored
	 */
	public static final String TABLE_NAME = "settings";

	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `settings` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`volume`\tREAL NOT NULL DEFAULT 0.5,\n" +
            "\t`soundOnOff`\tINTEGER NOT NULL DEFAULT 1,\n" +
            "\t`leftControl`\tINTEGER NOT NULL DEFAULT 0\n" +
            ");";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `" + TABLE_NAME + "` SET `volume` = '%s', `soundOnOff` = '%s', `leftControl` = '%s' WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `" + TABLE_NAME + "` WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `" + TABLE_NAME + "` VALUES (null, '%s','%s', '%s');";

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
	 * find further attributes with the row id
	 * @param rowId the id of the row where the record regarding this setting is stored.
	 */
	public Setting(int rowId) {
		super();
		this.rowId = rowId;
		fetchFromSQL();	
	}
	
	
	/*
	 * inherited methods
	 */
	
	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return true;
	}
	

	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
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
	 * Assigns a new volume to this object.
	 * @param volume the new volume that is assigned. it's range may go from 0 to 1.0
	 */
	public void setVolume(float volume) {
		if (volume > 1.0f || volume < 0.0f) {
			throw new IllegalArgumentException("new volume is out of range");
		}
		this.volume = volume;
	}

	/**
	 * Get method to check whether sound is enabled or not.
	 * @return true if sound is enabled; false otherwise.
	 */
	public boolean isSoundOnOff() {
		return soundOnOff;
	}

	/**
	 * Enables or disables the sound in this setting.
	 * @param soundOnOff true to enable sound; false to disable sound.
	 */
	public void setSoundOnOff(boolean soundOnOff) {
		this.soundOnOff = soundOnOff;
	}

	/**
	 * Get method to check whether left or right control is enabled in this settings objects.
	 * @return true if the left control; false equals right control.
	 */
	public boolean isLeftControl() {
		return leftControl;
	}

	/**
	 * Set left control to this setting.
	 * @param leftControl true if left control shall be enabled; false otherwise
	 */
	public void setLeftControl(boolean leftControl) {
		this.leftControl = leftControl;
	}
}
