package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Statistic;

/**
 * Controller which controlles the Stitistic and change them 
 * 
 * @author retroFactory
 *
 */
public class StatisticController implements OnProfileChangedListener {
	
	/**
	 * instance of the Game which is needed to get alle stistic informations
	 */
	private final RetroMachines game;
	
	
	private Statistic statistic;
	
	/**
	 * Constructor which starts an instantce of Statistic Controller  with the game as attribut
	 * @param game attribut of Statistic Controller
	 */
	public StatisticController(RetroMachines game) {
		this.game = game;
		game.getProfileController().addProfileChangedListener(this);
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


	@Override
	public void profileChanged() {
		// TODO Auto-generated method stub
		statistic = game.getProfileController().getProfile().getStatistic();
	}
}
