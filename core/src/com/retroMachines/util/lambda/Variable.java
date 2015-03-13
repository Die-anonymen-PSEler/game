package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
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
	public Variable(int color) {
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
	private Variable(Vertex next, Vertex family, int color,
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
	 * this method does nothing because there is no alpha conversion for
	 * variables
	 */
	@Override
	public boolean canAlphaConversion() {
		// no alpha conversion
		return false;
	}

	/**
	 * Fulfills one step of beta-reduction for a Abstraction
	 * 
	 * @return empty LinkedList
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {
		// Variable doesnt do betaReduction
		EvaluationOptimizer.runNextStep();
		return new LinkedList<Vertex>();
	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

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
		clone = new Variable(null, family, this.getColor(),
				this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	/**
	 * Creates a clone of this Vertex and his hole Family
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
		Vertex clone = new Variable(next, family, this.getColor(),
				this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	@Override
	public GameElement getGameElement() {
		// only one instance of gameElement allowed
		if (gameElement == null) {
			gameElement = new MetalElement(getColor());
		}
		return gameElement;
	}

	@Override
	public String getType() {
		return "Variable";
	}

	@Override
	public Vertex getReadIn() {
		return null;
	}

	@Override
	public void reorganizePositions(Vector2 start, Vector2 newPos) {
		// Start next Step no reorganization is needed
		EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());

	}

	@Override
	public void deleteAfterBetaReduction() {
		// start next evaluationStep
		EvaluationOptimizer.runNextStep();

	}

	@Override
	public Vertex updatePointerAfterBetaReduction() {
		return this.getnext();
	}

	@Override
	public Vertex getEvaluationResult() {
		return this;
	}

	@Override
	public void updatePositionsAfterBetaReduction() {
		// Do nothing , you Have No Family ! :D
		// Start next evaluation Step
		EvaluationOptimizer.runNextStep();
	}

	@Override
	public Vertex getClone() {
		Vertex clone = new Variable(null, null, getColor(), null);
		return clone;
	}

}
