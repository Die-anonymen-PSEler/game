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
	public LinkedList<Vertex> betaReduction() {
		
		// Check if next is there
		if(this.getnext() != null) {
			// Check if family is there
			if (this.getfamily() != null) {

				//Start Beta Reduction
				LinkedList<Vertex> returnList = this.getfamily().replaceInFamily(this);
				
				// Replace own family Vertex
				//Replace Family Vertex if Color and Type are ok
				if (this.getfamily().getType().equals("Variable") && this.getfamily().getColor() == this.getColor()) {
					Vertex replace = this.getnext().cloneMe();
					
					//Update listOfNewVertex
					LinkedList<Vertex> cloneList = replace.getVertexList();
					for(Vertex v : cloneList) {
						returnList.add(v);
					}
					
					// Insert clone in Family
					
					Vector2 position = new Vector2(this.getGameElement().getPosition().x, this.getGameElement().getPosition().y);
					position.x += Constants.ABSTRACTION_OUTPUT;
					position.y += Constants.GAMEELEMENT_ANIMATION_WIDTH;

					EvaluationOptimizer.moveAndScaleAnimationWithoutDelay(position, this.getfamily().getGameElement(), false);
					
					if(this.getfamily().getnext() != null) {
						replace.setnext(this.getfamily().getnext());
					}
					this.setfamily(replace);
				}
				
				//Update colorList
				this.updateColorList(this.getnext().getCopyOfFamilyColorList(), this.getColor());
				
				// Update width
				this.updateWidth();
				
				if(this.getnext().getnext() != null) {
					this.setnext(this.getnext().getnext());
				} else {
					this.setnext(null);
				}
				
				EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());
				return returnList;
				
			} else {
				// Error every machine has a family
				return null;
			}
		} else {
			// Error every machine has next
			return null;
		}
		
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
		clone = new Abstraction(null, family,this.getId(), this.getColor(), this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
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
		Vertex clone = new Abstraction(next, family,this.getId(), this.getColor(), this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
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
		int newColor = -1;
		// search unused color ID
		for(int i = 1; i <= Constants.MAX_COLOR_ID; i++) {
			// Search if id "i" is unused in firstList
			if(!actFam.contains(new Integer(i))) {
				if(!nextFam.contains(new Integer(i))) {
					newColor = i;
					break;
				}
			}
		}
		return newColor;
	}

	public Vertex getReadIn() {
		return this.getnext();
	}

	@Override
	public void reorganizePositions(Vector2 start, Vector2 newPos) {
		//Abstraction needs reorganizesation of Element positions
		this.setGameelementPosition(start, newPos);
	}

	@Override
	public void DeleteAfterBetaReduction() {
		// Remove element and Start next Step of BetaReduction
		EvaluationOptimizer.scaleAnimation(this.getGameElement(), true);
		
	}

	@Override
	public Vertex updatePointerAfterBetaReduction() {
		// Update pointer if needed
		if(this.getnext() != null) {
			// Search last Vertex in first Family layer
			Vertex pointer = new Dummy();
			pointer.setnext(this.getfamily());
			if(pointer.getnext() != null) {
				while (pointer.getnext().getnext() != null) {
					pointer.setnext(pointer.getnext().getnext());
				}
			}
			// Set next Vertex of this as Next of Last in First Family layer;
			pointer.getnext().setnext(this.getnext());
		}
		// retrn new Worker
		return this.getfamily();
	}

	@Override
	public Vertex getEvaluationResult() {
		//Returns null because the Abstraction is no Part of Evaluation Result
		return null;
	}

	@Override
	public void updatePositionsAfterBetaReduction() {
		// update Gameelement Postions  after Gameelement of this was deleted 
		this.getfamily().updateGameelementPosition(0, -1);
	}
}
