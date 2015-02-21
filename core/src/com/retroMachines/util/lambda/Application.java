package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.LightElement;
import com.retroMachines.util.Constants;

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
	private Application(Vertex next, Vertex family, int color, LinkedList<Integer> familyColorlist) {
		this.setnext(next);
		this.setfamily(family);
		this.setColor(color);
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
	public boolean betaReduction() {
		// Check if family is there
		if (this.getfamily() != null) {
			Vertex actWorker = new Dummy();
			actWorker.setnext(this.getfamily());
			while(actWorker.getnext() != null) {
				actWorker.getnext().alphaConversion();
				
				if(!actWorker.getnext().betaReduction()) {
					Gdx.app.log(Constants.LOG_TAG, "Application Beta-Reduction error");
				}
				actWorker.setnext(actWorker.getnext().getnext());
				
			}
			
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
			
			//Evaluation will choose next Vertex for next eavluation step
			this.setnext(this.getfamily());
		} else {
			// Error every Light has a family
			return false;
		}
		// Set Light green
		int offset = (Integer) this.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
		this.getGameElement().setTileId(2 + offset);
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
		Vertex clone = new Application(next, this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
		return clone;
	}
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return First Vertex in Tree structure
	 */
	public Vertex cloneFamily(){
		Vertex clone = new Application(this.getnext().cloneFamily(), this.getfamily().cloneFamily(), this.getColor(), this.getFamilyColorList());
		return clone;
	}

	@Override
	public GameElement getGameElement() {
		if (gameElement == null) {
			gameElement = new LightElement();
		}
		return gameElement;
	}
	
	public String getType() {
		return "Application";
	}
}
