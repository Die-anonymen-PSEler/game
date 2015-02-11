package com.retroMachines.util.lambda;

import java.util.LinkedList;

/**
 * 
 * @author RetroFactory
 *
 */
public class Abstraction extends Vertex {
	
	// --------------------------
	// --------Constructor-------
	// --------------------------
	
	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param id
	 *            ID to set.
	 */
	public Abstraction(int color) {
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
	private Abstraction(Vertex next, Vertex family, int color, LinkedList<Integer> familyColorlist) {
		this.setnext(next);
		this.setfamily(family);
		this.setColor(color);
		this.setFamilyColorlist(familyColorlist);
	}
	
	//------------------------------
	//--------Alpha Conversion & Beta Reduction ------
	//------------------------------
	
	/**
	 * this method does nothing because there is no alpha conversion for abstractions
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
		// Check if family is there
		if (this.getfamily() != null) {
			LinkedList<Integer> replaced = this.getfamily().replaceInFamily(this);
			
			// Check if there was no error in replaceInFamily
			if (replaced != null) {
				this.mergeMyColorList(replaced);
				
				// Update pointer if needed
				if(this.getnext().getnext() != null) {
					Vertex pointer = this.getfamily();
					while (pointer.getnext() != null) {
						pointer = pointer.getnext();
					}
					pointer.setnext(this.getnext().getnext());
				}
			} else {
				//error in family
				return false;
			}
		} else {
			// Error every machine has a family
			return false;
		}
		return true;
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
	public Vertex cloneMe(Vertex next){
		return new Abstraction(next, this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		return new Abstraction(this.getnext().cloneFamily(), this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
	}
	
}
