package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.MachineElement;
import com.retroMachines.util.Constants;

/**
 * Class for the abstraction of the lambda-term.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Abstraction extends Vertex {

	private boolean nextNull;

	// --------------------------
	// --------Constructor-------
	// --------------------------

	/**
	 * Creates a clone for beta reduction.
	 * 
	 * @param next
	 *            The next Clone.
	 * @param family
	 *            The family Clone.
	 * @param type
	 *            The type of Clone.
	 * @param color
	 *            The color of Clone.
	 * @param familyColorlist
	 *            The familyColorList of Clone.
	 */
	private Abstraction(Vertex next, Vertex family, int color,
			LinkedList<Integer> familyColorlist) {
		super(color);
		this.setNext(next);
		this.setFamily(family);
		this.setFamilyColorlist(familyColorlist);
	}
	
	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param id
	 *            ID to set.
	 */
	public Abstraction(int color) {
		super(color);
	}
	
	private int searchUnusedColorID() {
		LinkedList<Integer> actFam = this.getFamilyColorList();
		int newColor = -1;
		// search unused color ID
		for (int i = 1; i <= Constants.MAX_COLOR_ID; i++) {
			// Search if id "i" is unused in firstList
			if (!actFam.contains(i)) {
				newColor = i;
				break;
			}
		}
		return newColor;
	}

	// ------------------------------
	// --------Alpha Conversion & Beta Reduction ------
	// ------------------------------

	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	@Override
	public boolean alphaConversion() {
		int newColor = searchUnusedColorID();
		return this.getFamily().searchEqualAbstractions(this.getColor(), newColor);
	}

	/**
	 * Fulfills one step of beta-reduction for a Abstraction.
	 * 
	 * @return True if this abstraction has changed, false when an error
	 *         appeared.
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {
		// Check if next is there
		if (this.getNext() != null) {
			// Check if family is there
			if (this.getFamily() != null) {

				// Start Beta Reduction
				LinkedList<Vertex> returnList = this.getFamily()
						.replaceInFamily(this);

				// Replace own family Vertex
				// Replace Family Vertex if Color and Type are ok
				if (this.getFamily().getType().equals("Variable")
						&& this.getFamily().getColor() == this.getColor()) {
					Vertex replace = this.getNext().cloneMe();

					// Update listOfNewVertex
					LinkedList<Vertex> cloneList = replace.getVertexList();
					for (Vertex v : cloneList) {
						returnList.add(v);
					}

					// Insert clone in Family

					Vector2 position = new Vector2(this.getGameElement()
							.getPosition().x, this.getGameElement()
							.getPosition().y);
					position.x += Constants.ABSTRACTION_OUTPUT;
					position.y += Constants.GAMEELEMENT_ANIMATION_WIDTH;
					EvaluationExecutioner.moveAndScaleAnimationWithoutDelay(
							position, this.getFamily().getGameElement(), false);

					if (this.getFamily().getNext() != null) {
						replace.setNext(this.getFamily().getNext());
					}
					this.setFamily(replace);
				}

				// Update colorList
				this.updateColorList(this.getNext().getCopyOfFamilyColorList(),
						this.getColor());

				// Update width
				this.updateWidth();

				if (this.getNext().getNext() != null) {
					this.setNext(this.getNext().getNext());
				} else {
					this.setNext(null);
				}
				nextNull = false;
				EvaluationExecutioner.delayAndRunNextStepAnim(this
						.getGameElement());
				return returnList;

			} else {
				// Error every machine has a family
				return null;
			}
		} else {
			nextNull = true;
			EvaluationExecutioner.runNextStep();
			return new LinkedList<Vertex>();
		}

	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

	/**
	 * Creates a clone of this Vertex without Next and his whole Family.
	 * 
	 * @return The clone of this vertex.
	 */
	@Override
	public Vertex cloneMe() {
		// check if next or family is null
		Vertex family;
		if (this.getFamily() != null) {
			family = this.getFamily().cloneFamily();
		} else {
			family = null;
		}
		Vertex clone;
		clone = new Abstraction(null, family, this.getColor(),
				this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	/**
	 * Creates a clone of this Vertex and his whole Family.
	 * 
	 * @return First Vertex in Tree structure.
	 */
	@Override
	public Vertex cloneFamily() {
		// check if next or family is null
		Vertex next;
		Vertex family;
		if (this.getNext() != null) {
			next = this.getNext().cloneFamily();
		} else {
			next = null;
		}
		if (this.getFamily() != null) {
			family = this.getFamily().cloneFamily();
		} else {
			family = null;
		}
		Vertex clone = new Abstraction(next, family, this.getColor(),
				this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	/**
	 * Method to reorganize the positions.
	 */
	@Override
	public void reorganizePositions(Vector2 start, Vector2 newPos) {
		// Abstraction needs reorganizesation of Element position
		this.setGameelementPosition(start, newPos);
	}

	/**
	 * Method to delete the abstraction after the beta-reduction.
	 */
	@Override
	public void deleteAfterBetaReduction() {
		// Remove element and Start next Step of BetaReduction
		if (nextNull) {
			EvaluationExecutioner.delayAndRunNextStepAnim(this.getGameElement());
			return;
		} else {
			EvaluationExecutioner.scaleAnimation(this.getGameElement(), true);
		}

	}

	/**
	 * Method to update the pointer after the beta-reduction.
	 */
	@Override
	public Vertex updatePointerAfterBetaReduction() {
		if (nextNull) {
			return this.getNext();
		}	
		return super.updatePointerAfterBetaReduction();
	}

	/**
	 * Method to update the positions after the beta-reduction.
	 */
	@Override
	public void updatePositionsAfterBetaReduction() {
		if (nextNull) {
			EvaluationExecutioner.delayAndRunNextStepAnim(this.getGameElement());
			return;
		} else {
			// update Gameelement Postions after Gameelement of this was deleted
			if(this.getFamily() != null) {
				// In Theorie Abstraction has at all time a family object .....
				this.getFamily().updateGameelementPosition(0, -1);
			}
		}
	}

	/*
	 * Getter and Setter
	 */
	/**
	 * Getter for the evaluation result.
	 * 
	 * @return The evaluation result.
	 */
	@Override
	public Vertex getEvaluationResult() {
		if (nextNull) {
			return this;
		} else {
			// Returns null because the Abstraction is no Part of Evaluation
			// Result
			return null;
		}
	}
	
	/**
	 * Getter for the game element according to this vertex.
	 * 
	 * @return The element.
	 */
	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new MachineElement(getColor());
		}
		return gameElement;
	}

	/**
	 * Getter for the type.
	 * 
	 * @return The type.
	 */
	@Override
	public String getType() {
		return "Abstraction";
	}

	/**
	 * Getter for what was read in.
	 * 
	 * @return What was read.
	 */
	@Override
	public Vertex getReadIn() {
		return this.getNext();
	}

	/**
	 * Getter for the clone.
	 * 
	 * @return The clone.
	 */
	@Override
	public Vertex getClone() {
		Vertex clone = new Abstraction(null, null, getColor(), null);
		return clone;
	}
}
