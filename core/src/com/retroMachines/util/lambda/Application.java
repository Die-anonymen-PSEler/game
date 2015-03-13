package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.LightElement;

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
	 */
	public Application() {
		super(1);
	}

	/**
	 * Creates a clone for beta reduction
	 * 
	 * @param next
	 *            next Clone
	 * @param family
	 *            family Clone
	 * @param familyColorlist
	 *            familyColorList of Clone
	 */
	private Application(Vertex next, Vertex family,
			LinkedList<Integer> familyColorlist) {
		super(1);
		this.setnext(next);
		this.canSetfamily(family);
		this.setFamilyColorlist(familyColorlist);
	}

	// ------------------------------
	// -------- Alpha Conversion and Beta Reduction ------
	// ------------------------------

	/**
	 * this method does nothing because there is no alpha conversion for
	 * applications
	 * @return false
	 */
	@Override
	public boolean canAlphaConversion() {
		// no alpha conversion
		return false;
	}

	/**
	 * Fulfills one step of beta-reduction for a Abstraction
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

		EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());

		// no changes
		return new LinkedList<Vertex>();
	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

	/**
	 * Creates a clone of this Vertex without Next, but with his whole Family
	 * 
	 * @return a deep copy of this vertex except the next attribute in vertex representation
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
		clone = new Application(null, family, this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	/**
	 * Creates a clone of this Vertex and his whole Family
	 * 
	 * @return a deep copy of this vertex including next and family attribute
	 */
	@Override
	public Vertex cloneFamily() {
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
		Vertex clone = new Application(next, family,
				this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}

	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new LightElement();
		}
		return gameElement;
	}

	@Override
	public String getType() {
		return "Application";
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
		// Remove element and Start next Step of BetaReduction
		EvaluationOptimizer.scaleAnimation(this.getGameElement(), true);

	}

	@Override
	public Vertex getEvaluationResult() {
		// Returns null because the Application is no Part of Evaluation Result
		return null;
	}

	@Override
	public void updatePositionsAfterBetaReduction() {
		// update Gameelement Postions after Gameelement of this was deleted
		this.getfamily().updateGameelementPosition(0, -1);

	}

	@Override
	public Vertex getClone() {
		Vertex clone = new Application(null, null, null);
		return clone;
	}
}
