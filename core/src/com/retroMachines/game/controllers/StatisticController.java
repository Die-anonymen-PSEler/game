package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Statistic;

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
		game.getProfileController().addProfileChangedListener(this);
		if (game.getProfileController().getProfile() != null) {
			// no profile available waiting for notification
			statistic = game.getProfileController().getProfile().getStatistic();
		}
	}
	
	
	/**
	 * Increases the StepCounter of the current statistic.
	 * @param amount Adds the amount to the StepCounter.
	 */
	public void incStepCounter(int amount) {
		int value = amount;
		if (amount == -1) {
			value = 1;
		}
		statistic.setStepCounter(statistic.getStepCounter() + value);
	}

	/**
	 * Controls if the profile was changed.
	 */
	@Override
	public void profileChanged() {
		// TODO Auto-generated method stub
		statistic = game.getProfileController().getProfile().getStatistic();
	}
	
	public int getPlaytime() {
		return statistic.getPlaytime();
	}
	
	public int getStepCounter() {
		return statistic.getStepCounter();
	}
	
	public int getLevelsCompleted() {
		return statistic.getLevelsComplete();
	}
}
