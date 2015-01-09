package com.retroMachines.data.models;

public class Setting extends Model {
	
	private static final String TABLE_NAME = "settings";
	
	private static final String CREATE_TABLE_QUERY = "";
	
	private static final String UPDATE_TABLE_QUERY_PATTERN = "";
	
	private static final String DELETE_TABLE_QUERY_PATTERN = "";
	
	private static final String INSERT_TABLE_QUERY_PATTERN = "";
	
	/**
	 * the volume of the game
	 * range 0.0f to 1.0f
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
	
	public Setting(boolean leftControl, boolean soundOnOff, float volume) {
		super();
		this.volume = volume;
		this.soundOnOff = soundOnOff;
		this.leftControl = leftControl;
	}
	
	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(float volume) {
		if (volume > 1.0f || volume < 0.0f) {
			throw new IllegalArgumentException("new volume is out of range");
		}
		this.volume = volume;
	}

	/**
	 * @return the soundOnOff
	 */
	public boolean isSoundOnOff() {
		return soundOnOff;
	}

	/**
	 * @param soundOnOff the soundOnOff to set
	 */
	public void setSoundOnOff(boolean soundOnOff) {
		this.soundOnOff = soundOnOff;
	}

	/**
	 * @return the leftControl
	 */
	public boolean isLeftControl() {
		return leftControl;
	}

	/**
	 * @param leftControl the leftControl to set
	 */
	public void setLeftControl(boolean leftControl) {
		this.leftControl = leftControl;
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
	
	
	
}
