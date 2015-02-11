package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.MetalElement;

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
	public Variable(int color) {
		super(color);
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
	private Variable(Vertex next, Vertex family, int color, LinkedList<Integer> familyColorlist) {
		this.setnext(next);
		this.setfamily(family);
		this.setColor(color);
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
		return true;
	}
	
	/**
	 * Fulfills one step of beta-reduction for a Abstraction
	 * 
	 * @return True if this abstraction has changed, false when an error appeared.
	 */
	@Override
	public boolean betaReduction() {
		if (this.getfamily() == null) {
			// variable has no Family
			return false;
		}
		return true;
	}

	
	
	//---------------------------------------------------
	//-------- Beta Reduction and Alpha Conversion ------
	//------------------Help Methods---------------------
	//---------------------------------------------------
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		Vertex clone = new Variable(this.getnext().cloneFamily(), this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
		return clone;
	}
	
	/**
	 * replaces all Elements of a specific color in family of start Vertex
	 * @param start vertex which is parent of this Vertex and starts the beta Reduction
	 * @return
	 */
	public LinkedList<Integer> replaceInFamily(Vertex start) {

		LinkedList<Integer> listOfNewColors = new LinkedList<Integer>();
		
		// if family contains color, search and replace it
		if (this.getColor() == start.getColor()) {
			if (this.getfamily() != null) {
				// Variable has no Family
				return null;
			}
		}
		
		// if next Vertex contains color, search and replace it 
		if (this.getnext() != null) {
			
			// Check all Vertexes next to you, before you check to replace the Next Vertex
			listOfNewColors = this.getnext().replaceInFamily(start);
			
			// Stop replacing of vertexes! betaReduction will know what to do
			if (listOfNewColors == null) {
				return null;
			}
			
			//Replace Next Vertex if Color and Type are Ok
			if (this.getnext().getColor() == start.getColor()) {
				Vertex replaced = start.getnext().cloneMe(this.getfamily().getnext());
				this.setnext(replaced);
			}
		}
		// At the End return the ColorList, if something is replaced;
		return listOfNewColors;
	}

	@Override
	public Vertex cloneMe(Vertex next) {
		return new Variable(next.getColor());
	}

	@Override
	public GameElement getGameElementFromVertex() {
		//only one instance of gameElement allowed
		if (gameElement == null) {
		return new MetalElement(getColor());
		} else {
			return gameElement;
		}
	}

}
