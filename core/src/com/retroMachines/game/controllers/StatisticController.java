package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Statistic;

/**
 * Controller which controls the Statistic and changes them 
 * 
 * @author RetroFactory
 *
 */
public class StatisticController {
	
	/**
	 * instance of the Game which is needed to get all statistic informations
	 */
	private final RetroMachines game;
	
	/**
	 * Private attribute of the Statistics Database which stores the statistic informations
	 */
	private Statistic statistic;
	
	/**
	 * Constructor which starts an instance of StatisticController  with the game as an attribute
	 * @param game attribute of StatisticController
	 */
	public StatisticController(RetroMachines game) {
		this.game = game;
		statistic = game.getProfileController().getProfile().getStatistic();
	}
	
	
	/**
	 * Increases the StepCounter of the current Statistic
	 * @param amount
	 */
	public void incStepCounter(int amount) {
		int value = amount;
		if (amount == -1) {
			value = 1;
		}
		statistic.setStepCounter(statistic.getStepCounter() + value);
	}
}
