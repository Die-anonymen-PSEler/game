package com.retroMachines.util;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.retroMachines.game.gameelements.GameElement;

public class GameelementActionListElement {
	
	private GameElement element;
	private Action act;
	
	public GameelementActionListElement(GameElement g, Action a) {
		element = g;
		act = a;
	}
	
	public GameElement getGameElement() {
		return element;
	}
	
	public Action getAction() {
		return act;
	}
}
