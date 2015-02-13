package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.MachineElement;

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
	public Application(int color) {
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
	private Application(Vertex next, Vertex family, int color, LinkedList<Integer> familyColorlist) {
		this.setnext(next);
		this.setfamily(family);
		this.setColor(color);
		this.setFamilyColorlist(familyColorlist);
	}
	
	//------------------------------
	//-------- Alpha Conversion and Beta Reduction ------
	//------------------------------
	
	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	public boolean alphaConversion() {
		LinkedList<Integer> nextFam = this.getnext().getFamilyColorList();
		boolean returnValue = false;
		int sA = getFamilyColorList().size();
		int sN = nextFam.size();
		int newColor = this.getFamilyColorList().getLast() + 1;
		// Searched for double used colors
		for (int i = 0; i < sA; i++) {
			for (int j = 0; j < sN; j++) {
				if (getFamilyColorList().get(i) == nextFam.get(j)) {
					//Replace color in next family
					if (!this.getnext().renameFamily(nextFam.get(j), newColor)) {
						// Error
						System.out.println("AlphaConversionError: " + this.getColor());
						//TODO: kein System.out, Exception oder Logcat
					}
					returnValue = true;
					newColor++;
				}
			}
		}
		return returnValue;
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
		Vertex clone = new Application(next, this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
		return clone;
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		Vertex clone = new Application(this.getnext().cloneFamily(), this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
		return clone;
	}

	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			return new MachineElement(getColor());
		} else {
			return gameElement;
		}
		
	}
}
