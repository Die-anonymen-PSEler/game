package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.LightElement;
import com.retroMachines.game.gameelements.MachineElement;
import com.retroMachines.util.Constants;

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
	public Abstraction(int id, int color) {
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
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	public boolean alphaConversion() {
		LinkedList<Integer> nextFam = this.getnext().getFamilyColorList();
		boolean returnValue = false;
		int sA = getFamilyColorList().size();
		int sN = nextFam.size();
		int newColor;
		// Searched for double used colors
		for (int i = 0; i < sA; i++) {
			for (int j = 0; j < sN; j++) {
				if (getFamilyColorList().get(i) == nextFam.get(j)) {
					//Replace color in next family
					newColor = this.searchUnusedColorID();
					if (newColor == - 1) {
						Gdx.app.log(Constants.LOG_TAG, "out of ColorID Range");
						// what should happen when there are more than 11 different Colors used ?
						return true;
					}
					updateMap(nextFam.get(j), newColor); //updating mapped color of vertex
					if (!this.getnext().renameFamily(nextFam.get(j), newColor)) {
						// Error
						System.out.println();
						Gdx.app.log(Constants.LOG_TAG, "AlphaConversionError: " + this.getColor());
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
		return new Abstraction(next, this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		return new Abstraction(this.getnext().cloneFamily(), this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
	}
	
	/**
	 * returns gameElemet according to this vertex
	 */
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new MachineElement(getColor());
		}
		return gameElement;
	}
	
	public String getType() {
		return "Abstraction";
	}
	
	private int searchUnusedColorID() {
		LinkedList<Integer> nextFam = this.getnext().getFamilyColorList();
		LinkedList<Integer> actFam = this.getFamilyColorList();
		int sA = actFam.size();
		int sN = nextFam.size();
		int newColor = -1;
		boolean idIsFree = true;
		// search unused color ID
		for(int i = 0; i <= Constants.MAX_COLOR_ID; i++) {
			// Search if id "i" is unused in firstList
			for(int j = 0; j < sA; j++) {
				if(actFam.get(j) == i) {
					idIsFree = false;
					break;
				}
			}
			// if id "i" is unused in first list search in next List
			if(idIsFree) {
				for(int j = 0; j < sN; j++) {
					if(nextFam.get(j) == i) {
						idIsFree = false;
						break;
					}
				}
				// if id "i" is unused in both Lists set as return value
				if(idIsFree) {
					newColor = i;
					break;
				}
			}
		}
		return newColor;
	}
}
