package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;

/**
 * Controller which controlles the Stitistic and change them 
 * 
 * @author retroFactory
 *
 */
public class StatisticController {
	
	/**
	 * instance of the Game which is needed to get alle stistic informations
	 */
	private final RetroMachines game;
	
	/**
	 * Constructor which starts an instantce of Statistic Controller  with the game as attribut
	 * @param game attribut of Statistic Controller
	 */
	public StatisticController(RetroMachines game) {
		this.game = game;
	}
}
