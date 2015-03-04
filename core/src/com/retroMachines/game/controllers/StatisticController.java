package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Statistic;
import com.retroMachines.util.Constants;

/**
 * The StatisticController is part of the controller of RetroMachines.
 * It controls the statistics of the profile and changes them.
 * 
 * @author RetroFactory
 *
 */
public class StatisticController implements OnProfileChangedListener {
	
	/**
	 * Instance of the Game which is needed to get all statistical informations.
	 */
	private final RetroMachines game;
	
	/**
	 * Private attribute of the statistics database which stores the statistical informations.
	 */
	private Statistic statistic;
	
	/**
	 * Constructor which starts an instance of StatisticController with the game as an attribute.
	 * @param game Attribute of StatisticController.
	 */
	public StatisticController(RetroMachines game) {
		this.game = game;
		initialize();
	}
	
	/**
	 * Completes the setup of the controller.
	 */
	public void initialize() {
		game.getProfileController().addProfileChangedListener(this);
		if (game.getProfileController().getProfile() != null) {
			// no profile available waiting for notification
			statistic = game.getProfileController().getProfile().getStatistic();
		}
	}
	
	/**
	 * Increases the StepCounter of the current statistic.
	 * @param amount Adds the amount to the StepCounter. a value smaller than 0 will result in 1 step added
	 */
	public void incStepCounter(int amount) {
		int value = amount;
		if (amount < 0) {
			value = 1;
		}
		statistic.setStepCounter(statistic.getStepCounter() + value);
	}
	
	/**
	 * Increases the playtime by a given amount
	 * @param diff the amount to add to the play time; a value smaller than 0 will result in 1 minute added 
	 */
	public void incPlayTime(float diff) {
		statistic.setPlaytime(statistic.getPlaytime() + diff);
	}
	
	/**
	 * @param minimumLevel the level to which you wanna increase the level atleast
	 */
	public void incLevelCompleted(int minimumLevel) {
		if (Constants.MAX_LEVEL_ID > statistic.getLevelsComplete() && getLevelsCompleted() < minimumLevel) {
			statistic.setLevelsComplete(getLevelsCompleted() + 1);
		}
	}

	/**
	 * Controls if the profile was changed.
	 */
	@Override
	public void profileChanged() {
		statistic = game.getProfileController().getProfile().getStatistic();
	}
	
	public float getPlaytime() {
		return statistic.getPlaytime();
	}
	
	public int getStepCounter() {
		return statistic.getStepCounter();
	}
	
	public int getLevelsCompleted() {
		return statistic.getLevelsComplete();
	}
}
