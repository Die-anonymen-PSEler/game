package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Statistic;
import com.retroMachines.util.Constants;

/**
 * The StatisticController is part of the controller of RetroMachines. It
 * controls the statistics of the profile and changes them.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class StatisticController implements OnProfileChangedListener {

	/**
	 * Instance of the Game which is needed to get all statistical informations.
	 */
	private final RetroMachines game;

	/**
	 * Private attribute of the statistics database which stores the statistical
	 * informations.
	 */
	private Statistic statistic;

	/**
	 * Constructor which starts an instance of StatisticController with the game
	 * as an attribute.
	 * 
	 * @param game
	 *            Attribute of StatisticController.
	 */
	public StatisticController(RetroMachines game) {
		this.game = game;
		initialize();
	}

	/**
	 * Controls if the profile was changed.
	 */
	@Override
	public void profileChanged() {
		statistic = game.getProfileController().getProfile().getStatistic();
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
	 * 
	 * @param amount
	 *            Adds the amount to the StepCounter. A value smaller than 0
	 *            will result in 1 step added.
	 */
	public void incStepCounter(int amount) {
		int value = amount;
		if (amount < 0) {
			value = 1;
		}
		statistic.setStepCounter(statistic.getStepCounter() + value);
	}

	/**
	 * Increases the play time by a given amount.
	 * 
	 * @param diff
	 *            The amount to add to the play time; A value smaller than 0
	 *            will result in 1 minute added.
	 */
	public void incPlayTime(float diff) {
		statistic.setPlaytime(diff < 0 ? statistic.getPlaytime()
				+ Constants.SECONDS_IN_MINUTE : statistic.getPlaytime() + diff);
	}

	/**
	 * Increases the number of levels that are completed.
	 * @param minimumLevel
	 *            Level 1 will result.
	 */
	public void incLevelCompleted(int minimumLevel) {
		if (Constants.MAX_LEVEL_ID > statistic.getLevelsComplete()
				&& getLevelsCompleted() < minimumLevel) {
			statistic.setLevelsComplete(getLevelsCompleted() + 1);
		}
	}
	
	/*
	 * Getter and Setter
	 */
	
	/**
	 * Get method for the play time.
	 * 
	 * @return The play time.
	 */
	public float getPlaytime() {
		return statistic.getPlaytime();
	}

	/**
	 * Get method for the step counter.
	 * 
	 * @return Number of steps that were made.
	 */
	public int getStepCounter() {
		return statistic.getStepCounter();
	}

	/**
	 * Get method for the number of levels completed.
	 * 
	 * @return The number of levels completed.
	 */
	public int getLevelsCompleted() {
		return statistic.getLevelsComplete();
	}
}
