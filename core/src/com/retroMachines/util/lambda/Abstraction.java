package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;
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
	private Abstraction(Vertex next, Vertex family,int id, int color, LinkedList<Integer> familyColorlist) {
		super(id, color);
		this.setnext(next);
		this.setfamily(family);
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
		
		// Check if next is there
		if(this.getnext() != null) {
			// Check if family is there
			if (this.getfamily() != null) {
				
				//Delete Next Vertex and family
				this.getnext().removeGameelemens();
				
				//Start Beta Reduction
				LinkedList<Integer> replaced = this.getfamily().replaceInFamily(this);
				
				// Replace own family Vertex
				//Replace Family Vertex if Color and Type are ok
				if (this.getfamily().getType().equals("Variable") && this.getfamily().getColor() == this.getColor()) {
					Vertex replace = this.getnext().cloneMe(this.getfamily().getnext());
					int oldWidth = this.getfamily().getWidth();
					int newWidth = replace.getWidth();
					
					// update Position
					if (oldWidth != newWidth) {
						int dif = newWidth - oldWidth;
						// Move next Families from Start
						if (this.getnext().getnext() != null) {
							this.getnext().getnext().updateGameelementPosition(dif, 0);
						}
						// Move next Vertex from Family
						if(this.getfamily().getnext() != null) {
							this.getfamily().getnext().updateGameelementPosition(dif, 0);
						}
					}
					this.setfamily(replace);
				}
				
				
				// Check if there was no error in replaceInFamily
				if (replaced != null) {
					this.mergeMyColorList(replaced);
					
					// Update pointer if needed
					if(this.getnext().getnext() != null) {
						Vertex pointer = new Dummy();
						pointer.setnext(this.getfamily().getnext());
						if(pointer.getnext() != null) {
							while (pointer.getnext().getnext() != null) {
								pointer.setnext(pointer.getnext().getnext());
							}
						}
						
						pointer.getnext().setnext(this.getnext().getnext());
					}
					
					// remove Actors
					this.getGameElement().remove();
					// Family takes place of this element
					this.getfamily().updateGameelementPosition(0, -1);
					
					//Evaluation will choose next Vertex for next eavluation step
					this.setnext(this.getfamily());
				} else {
					//error in family
					return false;
				}
			} else {
				// Error every machine has a family
				return false;
			}
		} else {
			// Error every machine has next
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
		// check if next or family is null
		Vertex family;
		if(this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
		} else  {
			family = null;
		}
		Vertex clone;
		if(next != null) {
			clone = new Abstraction(next, family,this.getId(), this.getColor(), this.getFamilyColorList());
		} else  {
			clone = new Abstraction(null, family,this.getId(), this.getColor(), this.getFamilyColorList());
		}
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
		Vertex clone = new Abstraction(next, family,this.getId(), this.getColor(), this.getFamilyColorList());
		return clone;
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
