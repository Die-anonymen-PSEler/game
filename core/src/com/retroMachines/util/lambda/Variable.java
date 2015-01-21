package com.retroMachines.util.lambda;

import java.util.LinkedList;

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
	//-------- Beta Reduction ------
	//------------------------------
	
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
	protected Vertex cloneFamily(){
		Vertex clone = new Variable(this.getnext().cloneFamily(), this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorlist());
		return clone;
	}
	
	/**
	 * replaces all Elements of a specific color in family of start Vertex
	 * @param start vertex which is parent of this Vertex and starts the beta Reduction
	 * @return
	 */
	protected LinkedList<Integer> replaceInFamily(Vertex start) {

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
}