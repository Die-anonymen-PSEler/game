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

	//private boolean nextNull;

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
	 * @param next
	 *            next Clone
	 * @param family
	 *            family Clone
	 * @param type
	 *            type of Clone
	 * @param color
	 *            color of Clone
	 * @param familyColorlist
	 *            familyColorList of Clone
	 */
	private Abstraction(Vertex next, Vertex family, int color,
			LinkedList<Integer> familyColorlist) {
		super(color);
		this.setnext(next);
		this.setfamily(family);
		this.setFamilyColorlist(familyColorlist);
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
		return this.getfamily().SearchEqualAbstractions(this.getColor(), newColor);
	}

	/**
	 * Fulfills one step of beta-reduction for a Abstraction
	 * 
	 * @return True if this abstraction has changed, false when an error
	 *         appeared.
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {
		// Check if next is there
		if (this.getnext() != null) {
			// Check if family is there
			if (this.getfamily() != null) {

				// Start Beta Reduction
				LinkedList<Vertex> returnList = this.getfamily()
						.replaceInFamily(this);

				// Replace own family Vertex
				// Replace Family Vertex if Color and Type are ok
				if (this.getfamily().getType().equals("Variable")
						&& this.getfamily().getColor() == this.getColor()) {
					Vertex replace = this.getnext().cloneMe();

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
					EvaluationOptimizer.moveAndScaleAnimationWithoutDelay(
							position, this.getfamily().getGameElement(), false);

					if (this.getfamily().getnext() != null) {
						replace.setnext(this.getfamily().getnext());
					}
					this.setfamily(replace);
				}

				// Update colorList
				this.updateColorList(this.getnext().getCopyOfFamilyColorList(),
						this.getColor());

				// Update width
				this.updateWidth();

				if (this.getnext().getnext() != null) {
					this.setnext(this.getnext().getnext());
				} else {
					this.setnext(null);
				}
				//nextNull = false;
				EvaluationOptimizer.delayAndRunNextStepAnim(this
						.getGameElement());
				return returnList;

			} else {
				// Error every machine has a family
				return null;
			}
		} else {
			//nextNull = true;
			EvaluationOptimizer.runNextStep();
			return new LinkedList<Vertex>();
		}

	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

	/**
	 * Creates a clone of this Vertex without Next and his whole Family
	 * 
	 * @return clone of this vertex
	 */
	@Override
	public Vertex cloneMe() {
		// check if next or family is null
		Vertex family;
		if (this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
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
	 * Creates a clone of this Vertex and his whole Family
	 * 
	 * @return First Vertex in Tree structure
	 */
	@Override
	public Vertex cloneFamily() {
		// check if next or family is null
		Vertex next;
		Vertex family;
		if (this.getnext() != null) {
			next = this.getnext().cloneFamily();
		} else {
			next = null;
		}
		if (this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
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
	 * returns gameElemet according to this vertex
	 */
	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new MachineElement(getColor());
		}
		return gameElement;
	}

	@Override
	public String getType() {
		return "Abstraction";
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

	@Override
	public Vertex getReadIn() {
		return this.getnext();
	}

	@Override
	public void reorganizePositions(Vector2 start, Vector2 newPos) {
		// Abstraction needs reorganizesation of Element position
		this.setGameelementPosition(start, newPos);
	}

	@Override
	public void deleteAfterBetaReduction() {
		// Remove element and Start next Step of BetaReduction
		if (getnext() == null) {
			EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());
			return;
		}
		EvaluationOptimizer.scaleAnimation(this.getGameElement(), true);

	}

	@Override
	public Vertex updatePointerAfterBetaReduction() {
		if (getnext() == null) {
			return this.getnext();
		}
		
		return super.updatePointerAfterBetaReduction();
	}

	@Override
	public Vertex getEvaluationResult() {
		if (getnext() == null) {
			return this;
		} else {
			// Returns null because the Abstraction is no Part of Evaluation
			// Result
			return null;
		}

	}

	@Override
	public void updatePositionsAfterBetaReduction() {
		if (getnext() == null) {
			EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());
			return;
		} else {
			// update Gameelement Postions after Gameelement of this was deleted
			if(this.getfamily() != null) {
				// In Theorie Abstraction has at all time a family object .....
				this.getfamily().updateGameelementPosition(0, -1);
			}

		}

	}

	@Override
	public Vertex getClone() {
		Vertex clone = new Abstraction(null, null, getColor(), null);
		return clone;
	}
}
