package com.retroMachines.util;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.retroMachines.game.gameelements.GameElement;

/**
 * ActionListElement stores a GameElement with its action
 * @author RetroFactory
 * @version 1.0
 */
public class ActionListElement {
	
	private GameElement element;
	
	private Action act;
	
	/**
	 * Creates a new instance of this class.
	 * @param g The game element for this action.
	 * @param a The action that is to be done.
	 */
	public ActionListElement(GameElement g, Action a) {
		element = g;
		act = a;
	}
	
	/*
	 * Getter and Setter
	 */
	
	/**
	 * Getter for the game element of this class.
	 * @return The element.
	 */
	public GameElement getGameElement() {
		return element;
	}
	
	/**
	 * Getter for the action of this class.
	 * @return The action.
	 */
	public Action getAction() {
		return act;
	}
}
