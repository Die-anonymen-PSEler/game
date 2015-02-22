package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.MetalElement;

/**
 * 
 * @author RetroFactory
 *
 */
public class Variable extends Vertex {
	
	// --------------------------
	// --------Constructor-------
	// --------------------------
	
	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param id
	 *            ID to set.
	 */
	public Variable(int id, int color) {
		super(id, color);
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
	private Variable(Vertex next, Vertex family,int id, int color, LinkedList<Integer> familyColorlist) {
		super(id,color);
		this.setnext(next);
		this.setfamily(family);
		this.setFamilyColorlist(familyColorlist);
	}
	
	//------------------------------
	//--------Alpha Conversion & Beta Reduction ------
	//------------------------------
	
	/**
	 * this method does nothing because there is no alpha conversion for variables
	 */
	@Override
	public boolean alphaConversion() {
		// no alpha conversion
		return false;
	}
	
	/**
	 * Fulfills one step of beta-reduction for a Abstraction
	 * 
	 * @return True if this abstraction has changed, false when an error appeared.
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {
		//Variable doesnt do betaReduction
		return new LinkedList<Vertex>();
	}

	
	
	//---------------------------------------------------
	//-------- Beta Reduction and Alpha Conversion ------
	//------------------Help Methods---------------------
	//---------------------------------------------------
	
	@Override
	public Vertex cloneMe() {
		// check if next or family is null
		Vertex family;
		if(this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
		} else  {
			family = null;
		}
		Vertex clone;
		clone = new Variable( null, family, this.getId(), this.getColor(), this.getFamilyColorList());
		return clone;
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		// check if next or family is null
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
		Vertex clone = new Variable( next, family, this.getId(), this.getColor(), this.getFamilyColorList());
		return clone;
	}



	@Override
	public GameElement getGameElement() {
		//only one instance of gameElement allowed
		if (gameElement == null) {
			gameElement = new MetalElement(getColor());	
		}
		return gameElement;
	}
	
	public String getType() {
		return "Variable";
	}

	public Vertex getReadIn() {
		return null;
	}

}
