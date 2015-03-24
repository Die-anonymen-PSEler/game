package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.MetalElement;

/**
 * Class for the variable of the lambda-term.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Variable extends Vertex {

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
	 * @param color
	 *            The color of Clone.
	 * @param familyColorlist
	 *            The familyColorList of Clone.
	 */
	private Variable(Vertex next, Vertex family, int color,
			LinkedList<Integer> familyColorlist) {
		super(color);
		this.setNext(next);
		this.setFamily(family);
		this.setFamilyColorlist(familyColorlist);
	}
	
	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param color
	 *            The color to set.
	 */
	public Variable(int color) {
		super(color);
	}

	// ------------------------------
	// --------Alpha Conversion & Beta Reduction ------
	// ------------------------------

	/**
	 * This method does nothing because there is no alpha conversion for
	 * variables.
	 * 
	 * @return false
	 */
	@Override
	public boolean alphaConversion() {
		// no alpha conversion
		return false;
	}

	/**
	 * Fulfills one step of beta-reduction for an abstraction.
	 * 
	 * @return empty LinkedList
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {
		// Variable doesnt do betaReduction
		EvaluationExecutioner.runNextStep();
		return new LinkedList<Vertex>();
	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

	/**
	 * Method to clone the variable.
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
		clone = new Variable(null, family, this.getColor(),
				this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	/**
	 * Creates a clone of this Vertex and his hole family.
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
		Vertex clone = new Variable(next, family, this.getColor(),
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
		// Start next Step no reorganization is needed
		EvaluationExecutioner.delayAndRunNextStepAnim(this.getGameElement());

	}

	/**
	 * Method to delete the variable after the beta-reduction.
	 */
	@Override
	public void deleteAfterBetaReduction() {
		// start next evaluationStep
		EvaluationExecutioner.runNextStep();

	}

	/**
	 * Method to update the pointer after the beta-reduction.
	 */
	@Override
	public Vertex updatePointerAfterBetaReduction() {
		return this.getNext();
	}

	/**
	 * Method to update the positions after the beta-reduction.
	 */
	@Override
	public void updatePositionsAfterBetaReduction() {
		// Do nothing , you Have No Family ! :D
		// Start next evaluation Step
		EvaluationExecutioner.runNextStep();
	}
	
	/*
	 * Getter and Setter
	 */
	
	/**
	 * Getter for the game element according to this vertex.
	 * 
	 * @return The element.
	 */
	@Override
	public GameElement getGameElement() {
		// only one instance of gameElement allowed
		if (gameElement == null) {
			gameElement = new MetalElement(getColor());
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
		return "Variable";
	}

	/**
	 * Getter for what was read in.
	 * 
	 * @return What was read.
	 */
	@Override
	public Vertex getReadIn() {
		return null;
	}

	/**
	 * Getter for the evaluation result.
	 * 
	 * @return The evaluation result.
	 */
	@Override
	public Vertex getEvaluationResult() {
		return this;
	}
	
	/**
	 * Getter for the clone.
	 * 
	 * @return The clone.
	 */
	@Override
	public Vertex getClone() {
		Vertex clone = new Variable(null, null, getColor(), null);
		return clone;
	}

}
