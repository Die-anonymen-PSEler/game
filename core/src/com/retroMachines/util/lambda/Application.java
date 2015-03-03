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
	 * @param id
	 *            ID to set.
	 */
	public Application(int id) {
		super(id, 1);
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
	private Application(Vertex next, Vertex family,int id, LinkedList<Integer> familyColorlist) {
		super(id, 1);
		this.setnext(next);
		this.setfamily(family);
		this.setFamilyColorlist(familyColorlist);
	}
	
	//------------------------------
	//-------- Alpha Conversion and Beta Reduction ------
	//------------------------------
	
	/**
	 * this method does nothing because there is no alpha conversion for applications
	 */
	@Override
	public boolean alphaConversion() {
		// no alpha conversion
		return false;
	}
	
	/**
	 * Fulfills one step of beta-reduction for a Abstraction
	 * 
	 * @return True if this application has changed, false when an error appeared.
	 */
	@Override
	public LinkedList<Vertex> betaReduction() {		
				
		// Set Light green
		int offset = (Integer) this.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		this.getGameElement().setTileId(2 + offset);
		
		EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());
		
		// no changes
		return new LinkedList<Vertex>();
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
	@Override
	public Vertex cloneMe(){
		// check if next or family is null
		Vertex family;
		if(this.getfamily() != null) {
			family = this.getfamily().cloneFamily();
		} else  {
			family = null;
		}
		Vertex clone;
		clone = new Application(null, family,this.getId(), this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		clone.getGameElement().setTileId(this.getColor() + offset);
		return clone;
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	@Override
	public Vertex cloneFamily(){
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
		Vertex clone = new Application(next, family,this.getId(), this.getCopyOfFamilyColorList());
		int offset = (Integer) clone.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
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
		//Start next Step no reorganization is needed
		EvaluationOptimizer.delayAndRunNextStepAnim(this.getGameElement());
		
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
			// Set next Vertex of this as Next of Last in First Famiyl layer;
			pointer.getnext().setnext(this.getnext());
		}
		// return new Worker
		return this.getfamily();
	}

	@Override
	public Vertex getEvaluationResult() {
		//Returns null because the Application is no Part of Evaluation Result
		return null;
	}

	@Override
	public void updatePositionsAfterBetaReduction() {
		// update Gameelement Postions  after Gameelement of this was deleted 
		this.getfamily().updateGameelementPosition(0, -1);
		
	}
}
