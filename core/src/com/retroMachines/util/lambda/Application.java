package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.LightElement;

/**
 * 
 * Class for the application of the lambda-term.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Application extends Vertex {

	// --------------------------
	// --------Constructor-------
	// --------------------------

	/**
	 * Creates a clone for beta reduction.
	 * 
	 * @param next
	 *            The next clone.
	 * @param family
	 *            The family clone.
	 * @param familyColorlist
	 *            The familyColorList of clone.
	 */
	private Application(Vertex next, Vertex family,
			LinkedList<Integer> familyColorlist) {
		super(1);
		this.setNext(next);
		this.setFamily(family);
		this.setFamilyColorlist(familyColorlist);
	}

	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 */
	public Application() {
		super(1);
	}

	// ------------------------------
	// -------- Alpha Conversion and Beta Reduction ------
	// ------------------------------

	/**
	 * This method does nothing because there is no alpha conversion for
	 * applications.
	 * @return false
	 */
	@Override
	public boolean alphaConversion() {
		// no alpha conversion
		return false;
	}

	/**
	 * Fulfills one step of beta-reduction for a Abstraction.
	 * 
	 * @return True if this application has changed, false when an error
	 *         appeared.
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {

		// Set Light green
		int offset = (Integer) this.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		this.getGameElement().setTileId(2 + offset);

		EvaluationExecutioner.delayAndRunNextStepAnim(this.getGameElement());

		// no changes
		return new LinkedList<Vertex>();
	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

	/**
	 * Creates a clone of this Vertex without Next, but with his whole Family.
	 * 
	 * @return A deep copy of this vertex except the next attribute in vertex representation.
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
		clone = new Application(null, family, this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	/**
	 * Creates a clone of this Vertex and his whole Family.
	 * 
	 * @return A deep copy of this vertex including next and family attribute.
	 */
	@Override
	public Vertex cloneFamily() {
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
		Vertex clone = new Application(next, family,
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
	 * Method to delete the application after the beta-reduction.
	 */
	@Override
	public void deleteAfterBetaReduction() {
		// Remove element and Start next Step of BetaReduction
		EvaluationExecutioner.scaleAnimation(this.getGameElement(), true);

	}

	/**
	 * Method to update the positions after the beta-reduction.
	 */
	@Override
	public void updatePositionsAfterBetaReduction() {
		// update Gameelement Postions after Gameelement of this was deleted
		this.getFamily().updateGameelementPosition(0, -1);

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
		// Returns null because the Application is no Part of Evaluation Result
		return null;
	}
	
	/**
	 * Getter for the game element according to this vertex.
	 * 
	 * @return The element.
	 */
	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new LightElement();
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
		return "Application";
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
	 * Getter for the clone.
	 * 
	 * @return The clone.
	 */
	@Override
	public Vertex getClone() {
		Vertex clone = new Application(null, null, null);
		return clone;
	}
}
