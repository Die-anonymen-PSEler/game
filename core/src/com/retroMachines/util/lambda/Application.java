package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.LightElement;
import com.retroMachines.util.Constants;

/**
 * 
 * @author RetroFactory
 *
 */
public class Application extends Vertex {

	// --------------------------
	// --------Constructor-------
	// --------------------------
	
	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param id
	 *            ID to set.
	 */
	public Application(int id) {
		super(id, 1);
	}
	
	/**
	 * Creates a clone for beta reduction
	 * 
	 * @param next next Clone
	 * @param family family Clone
	 * @param type type of Clone
	 * @param color color of Clone
	 * @param familyColorlist familyColorList of Clone
	 */
	private Application(Vertex next, Vertex family,int id, LinkedList<Integer> familyColorlist) {
		super(id, 1);
		this.setnext(next);
		this.setfamily(family);
		this.setFamilyColorlist(familyColorlist);
	}
	
	//------------------------------
	//-------- Alpha Conversion and Beta Reduction ------
	//------------------------------
	
	/**
	 * this method does nothing because there is no alpha conversion for applications
	 */
	@Override
	public boolean alphaConversion() {
		// no alpha conversion
		return false;
	}
	
	/**
	 * Fulfills one step of beta-reduction for a Abstraction
	 * 
	 * @return True if this application has changed, false when an error appeared.
	 */
	@Override
	public LinkedList<Vertex> betaReduction(EvaluationController e) {		
				
		// Set Light green
		int offset = (Integer) this.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		this.getGameElement().setTileId(2 + offset);
		
		// no changes
		return new LinkedList<Vertex>();
	}

	
	
	//---------------------------------------------------
	//-------- Beta Reduction and Alpha Conversion ------
	//------------------Help Methods---------------------
	//---------------------------------------------------
	
	/**
	 * Creates a clone of this Vertex without Next and his hole Family
	 * @param next
	 * @return
	 */
	public Vertex cloneMe(){
		// check if next or family is null
		Vertex family;
		if(this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
		} else  {
			family = null;
		}
		Vertex clone;
		clone = new Application(null, family,this.getId(), this.getFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		Vertex next;
		Vertex family;
		if(this.getnext() != null) {
			next = this.getnext().cloneFamily();
		} else  {
			next = null;
		}
		if(this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
		} else  {
			family = null;
		}
		Vertex clone = new Application(next, family,this.getId(), this.getFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new LightElement();
		}
		return gameElement;
	}
	
	public String getType() {
		return "Application";
	}

	@Override
	public Vertex getReadIn() {
		return null;
	}
}
