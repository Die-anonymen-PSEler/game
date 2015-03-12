package com.retroMachines.util;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.retroMachines.game.gameelements.GameElement;

public class ActionListElement {
	
	private GameElement element;
	private Action act;
	
	public ActionListElement(GameElement g, Action a) {
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
