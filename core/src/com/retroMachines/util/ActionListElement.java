package com.retroMachines.util;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.retroMachines.game.gameelements.GameElement;

/**
 * ActionListElement stores a GameElement with its action
 * @author RetroFactory
 *
 */
public class ActionListElement {
	
	private GameElement element;
	
	private Action act;
	
	/**
	 * creates a new instance of this class.
	 * @param g GameElement for this action
	 * @param a action to be done
	 */
	public ActionListElement(GameElement g, Action a) {
		element = g;
		act = a;
	}
	
	/*
	 * Getter and Setter
	 */
	
	/**
	 * returns the gameElement of this class
	 * @return element
	 */
	public GameElement getGameElement() {
		return element;
	}
	
	/**
	 * returns the action of this class
	 * @return action
	 */
	public Action getAction() {
		return act;
	}
}
