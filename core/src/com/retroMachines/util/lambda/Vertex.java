package com.retroMachines.util.lambda;

import java.util.HashMap;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;

/**
 * This class is part of the model of RetroMachines. Vertices of the Graph of
 * GameElements extends this class.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public abstract class Vertex {

	private static HashMap<Integer, Integer> ColorMap = new HashMap<Integer, Integer>();

	/**
	 * Reference to next input.
	 */
	private Vertex next;

	/**
	 * Reference to the output tree.
	 */
	private Vertex family;

	/**
	 * Unique ID of the color of Variable.
	 */
	private int color;

	private Vector2 pos;

	private boolean isInDepot;
	
	private Vertex replaced;
	
	private LinkedList<Vertex> cloneList;
	
	private Vector2 position;

	/**
	 * The width of this vertex as number of Vertex in his family.
	 */
	private int width;

	/**
	 * Width of all Vertex next to this.
	 */
	private int nextWidth;

	/**
	 * List of all color's of vertices corresponding to this abstraction.
	 */
	private LinkedList<Integer> familyColorList;

	/**
	 * List of all color's of vertices corresponding to this abstraction.
	 */
	private LinkedList<Integer> nextColorList;

	/**
	 * The game element.
	 */
	protected GameElement gameElement;

	// --------------------------
	// --------Constructor-------
	// --------------------------

	/**
	 * Default public constructor for a dummy element.
	 */
	protected Vertex() {
		isInDepot = false;
	}

	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param color
	 *            The color to set.
	 */
	protected Vertex(int color) {
		this.color = color;
		this.familyColorList = new LinkedList<Integer>();
		this.nextColorList = new LinkedList<Integer>();
		this.familyColorList.add(color);
		this.width = 1;
		this.nextWidth = 0;
		updateMap(color, color); // vertex is not mapped yet
	}
	

	// --------------------------
	// ---------Methods----------
	// --------------------------
	
	/**
	 * Getter for the game element according to this vertex.
	 * 
	 * @return The game element.
	 */
	abstract public GameElement getGameElement();
	
	/**
	 * Getter for the clone of this vertex without family and next.
	 * 
	 * @return The clone of this.
	 */
	abstract public Vertex getClone();

	/**
	 * Creates a clone of this Vertex without Next and his hole Family.
	 * 
	 * @return The deep copy of the next.
	 */
	abstract public Vertex cloneMe();

	/**
	 * Creates a clone of this Vertex and his hole Family.
	 * 
	 * @return Clone of Vertex with hole family and next coned.
	 */
	abstract public Vertex cloneFamily();

	/**
	 * Getter for the type of the element.
	 * 
	 * @return The type.
	 */
	abstract public String getType();

	/**
	 * Returns Vertex with should be added to the ResultTree.
	 * 
	 * @return Returns null if there is no Vertex which should be added.
	 */
	abstract public Vertex getEvaluationResult();

	/**
	 * Update Position of Family if Worker was deleted in BetaReduction.
	 */
	abstract public void updatePositionsAfterBetaReduction();

	/**
	 * Removes the GameElement from screen if this type of Vertex needs it.
	 * 
	 * @param e
	 *            Instance of Evaluation Controller for next Steps.
	 */
	abstract public void deleteAfterBetaReduction();

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ---------------------------------------------------

	/**
	 * Fulfills one step of beta-reduction.
	 * 
	 * @return True if this abstraction has changed, false when an error
	 *         appeared.
	 */
	abstract public LinkedList<Vertex> betaReduction();

	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	abstract public boolean alphaConversion();

	// ----------------------------------------
	// -------- Animation Helper Methods ------
	// ----------------------------------------

	/**
	 * Returns the vertex which will be replaced in the beta-reduction.
	 * 
	 * @return Null if no vertex should be read in.
	 */
	abstract public Vertex getReadIn();

	/**
	 * Reorganizes Position of Vertex if needed.
	 * 
	 * @param start
	 *            Offset of position perhaps Padding to border.
	 * @param newPos
	 *            The new Position of this Vertex in number of the GameElementWidths.
	 * @param e
	 *            Instance of evaluationController for next steps.
	 */
	abstract public void reorganizePositions(Vector2 start, Vector2 newPos);
	
	private void cloned(Vertex start, LinkedList<Vertex> listOfNewVertex) {
		replaced = start.getNext().cloneMe();

		// Update listOfNewVertex
		cloneList = replaced.getVertexList();
		for (Vertex v : cloneList) {
			listOfNewVertex.add(v);
		}

		// Insert clone in Family
		position = new Vector2(start.getGameElement()
			.getPosition().x, start.getGameElement()
			.getPosition().y);
		position.x += Constants.ABSTRACTION_OUTPUT;
		position.y += Constants.GAMEELEMENT_ANIMATION_WIDTH;
	}
	
	private LinkedList<Vertex> getFamilyVertexList() {
		LinkedList<Vertex> returnList = new LinkedList<Vertex>();
		if (this.getNext() != null) {
			returnList = this.getNext().getFamilyVertexList();
		}

		// Add family
		if (this.getFamily() != null) {
			if (returnList.isEmpty()) {
				returnList = this.getFamily().getFamilyVertexList();
			} else {
				LinkedList<Vertex> familyList = this.getFamily()
						.getFamilyVertexList();
				for (Vertex v : familyList) {
					returnList.add(v);
				}
			}

		}
		returnList.add(this);
		return returnList;
	}
	
	private void updateFamilyColorList(LinkedList<Integer> clonedList, int color) {
		// Update Color List in vertex
		updateColorList(clonedList, color);

		// Update Color List in next
		if (this.getNextColorList().contains(color)) {
			if (this.getNext() != null) {
				this.getNext().updateFamilyColorList(clonedList, color);
			}
			this.setNextColorlist(this.getNext().getCopyOfNextColorList());
			if (!this.getNextColorList().contains(
					this.getNext().getColor())) {
				this.getNextColorList().add(this.getNext().getColor());
			}
		}
	}
	
	private void updateFamilyWidth() {
		// Update width
		if (this.getFamily() != null) {
			this.getFamily().updateFamilyWidth();
			this.setWidth(this.getFamily().getWidth()
					+ this.getFamily().getNextWidth());
		} else {
			this.setWidth(1);
		}

		// Update next width
		if (this.getNext() != null) {
			this.getNext().updateFamilyWidth();
			this.setNextWidth(this.getNext().getWidth()
					+ this.getNext().getNextWidth());
		} else {
			this.setNextWidth(0);
		}

		// Update self
	}
	
	private void updateOtherGameelementPosition(int difX, int difY) {
		if (this.getNext() != null) {
			this.getNext().updateOtherGameelementPosition(difX, difY);
		}

		// Move
		Vector2 actPosition = this.getGameElement().getPosition();
		actPosition.x += (Constants.GAMEELEMENT_WIDTH * difX);
		actPosition.y += (Constants.GAMEELEMENT_WIDTH * difY);

		EvaluationExecutioner.moveAnimation(actPosition, this.getGameElement(),
				false);

		if (this.getFamily() != null) {
			this.getFamily().updateOtherGameelementPosition(difX, difY);
		}
	}
	
	/**
	 * Set Game element and family to given.
	 * 
	 * @param newPos
	 *            As number of the GameElementWidths.
	 * @param startpos
	 *            StartPosition of actual worker for the dummies.
	 */
	private void setOtherGameelementPosition(Vector2 newPos, Vector2 startPos,
			Vector2 paddingScreen) {

		if (this.getNext() != null) {
			this.getNext().setOtherGameelementPosition(
					new Vector2(newPos.x + this.getWidth(), newPos.y),
					startPos, paddingScreen);
		}

		// Clone Pre Set
		if (this.getGameElement().getPosition().equals(new Vector2(0, 0))) {
			this.getGameElement().setPosition(startPos);
		}

		// Move
		int centerVertex = (Constants.GAMEELEMENT_WIDTH * (this.getWidth() - 1)) / 2;
		int x = Constants.GAMEELEMENT_WIDTH * (int) newPos.x;
		int y = Constants.GAMEELEMENT_WIDTH * (int) newPos.y;

		// Move
		EvaluationExecutioner.moveAnimation(new Vector2(x + centerVertex
				+ paddingScreen.x, y + paddingScreen.y), this.getGameElement(),
				false);

		if (this.getFamily() != null) {
			this.getFamily().setOtherGameelementPosition(
					new Vector2(newPos.x, newPos.y
							+ Constants.EVALUATION_DEFALT_LAYER_DIF), startPos,
					paddingScreen);
		}
	}
	
	/**
	 * Method to set new color for an vertex (e.g after alphaConversion).
	 * 
	 * @param vertexColor
	 *            The vertex, which color has changed.
	 * @param mappedColor
	 *            The new color of the vertex.
	 */
	protected static void updateMap(int vertexColor, int mappedColor) {
		ColorMap.put(vertexColor, mappedColor);
	}

	/**
	 * Returns the mapped color of the vertex.
	 * 
	 * @param vertexColor
	 *            Original color of the vertex.
	 * @return The mapped color of the vertex.
	 */
	protected static int getMappedColor(int vertexColor) {
		return ColorMap.get(vertexColor);
	}

	// ---------------------------------------------------
	// -------- Beta Reduction and Alpha Conversion ------
	// ------------------Help Methods---------------------
	// ---------------------------------------------------

	/**
	 * Replace all Abstractions with OldColor next to this vertex and in his family.
	 * 
	 * @param oldColor
	 *            Color which should be replaced.
	 * @return True if something is replaced. False otherwise.
	 */
	protected boolean searchEqualAbstractions(int oldColor, int newColor) {
		boolean retValue = false;
		if(this.getColor() == oldColor && this.getType().equals(Constants.RetroStrings.ABSTRACTION_TYPE)) {
			// Change Color of Family
			if (this.getFamily() != null) {
				this.getFamily().recolorFamily(newColor, oldColor);
			}
			// change own Color
			color = newColor;
			updateMap(newColor, newColor);
			int offset = (Integer) this.getGameElement().getTileSet()
					.getProperties().get("firstgid") - 1;
			this.getGameElement().setTileId(newColor + offset);
			
			// Update family Color List
			for (int i = 0; i < this.getFamilyColorList().size(); i++) {
				if (this.getFamilyColorList().get(i) == oldColor) {
					this.getFamilyColorList().remove(i);
					break;
				}
			}	
			this.getFamilyColorList().add(newColor);
			retValue = true;
		} else if( this.getColor() == oldColor) {
			if (this.getFamily() != null) {
				if(this.getFamily().searchEqualAbstractions(oldColor , newColor)) {
					// Update family Color List
					this.getFamilyColorList().add(newColor);
				}
				retValue = true;
			}
			
		} else {
			if (this.getFamily() != null) {
				if (this.getFamilyColorList().contains(oldColor)) {
					if(this.getFamily().searchEqualAbstractions(oldColor, newColor)) {
						// Update family Color List
						for (int i = 0; i < this.getFamilyColorList().size(); i++) {
							if (this.getFamilyColorList().get(i) == oldColor) {
								this.getFamilyColorList().remove(i);
								break;
							}
						}	
						this.getFamilyColorList().add(newColor);
						retValue = true;
					}
				}
			}	
		}
		
		if (this.getNext() != null) {
			if(this.getNext().searchEqualAbstractions(oldColor, newColor)) {
				// Update family Color List
				for (int i = 0; i < this.getNextColorList().size(); i++) {
					if (this.getNextColorList().get(i) == oldColor) {
						this.getNextColorList().remove(i);
						break;
					}
				}	
				this.getNextColorList().add(newColor);
				retValue = true;
			}
		}
		
		return retValue;
	}
	
	/**
	 * Replace all Color of Vertices with oldColor with  given new Color in whole family and next.
	 * @param newColor The new color of the vertex.
	 * @param oldColor The old color of the vertex.
	 */
	protected void recolorFamily(int newColor, int oldColor) {
		// Update family Color List
		if (this.getFamilyColorList().contains(oldColor)) {
			for (int i = 0; i < this.getFamilyColorList().size(); i++) {
				if (this.getFamilyColorList().get(i) == oldColor) {
					this.getFamilyColorList().remove(i);
					break;
				}
			}
		}
		this.getFamilyColorList().add(newColor);
		
		if(!this.getType().equals(Constants.RetroStrings.APPLICATION_TYPE)) {
			if(this.getColor() == oldColor) {
				this.color = newColor;
				updateMap(newColor, newColor);
				int offset = (Integer) this.getGameElement().getTileSet()
						.getProperties().get("firstgid") - 1;
				this.getGameElement().setTileId(newColor + offset);
			}
		}
		if (this.getFamily() != null) {
			this.getFamily().recolorFamily(newColor, oldColor);
		}
		
		if (this.getNext() != null) {
			this.getNext().recolorFamily(newColor, oldColor);
		}
	}	

	/**
	 * Replaces all Elements of a specific color in family of start Vertex.
	 * 
	 * @param start
	 *            Vertex which is parent of this Vertex and starts the beta-reduction.
	 * @return The new family.
	 */
	protected LinkedList<Vertex> replaceInFamily(Vertex start) {

		LinkedList<Vertex> listOfNewVertex = new LinkedList<Vertex>();

		// if family contains color, search and replace it
		if (this.getFamilyColorList().contains(start.getColor())) {

			if (this.getFamily() != null) {
				// Check Family Vertexes before you check to replace the first
				// in Family
				listOfNewVertex = this.getFamily().replaceInFamily(start);

				// Replace Family Vertex if Color and Type are ok
				if (this.getFamily().getType().equals("Variable")
						&& this.getFamily().getColor() == start.getColor()) {
					cloned(start, listOfNewVertex);

					// Animation
					EvaluationExecutioner.moveAndScaleAnimationWithoutDelay(
							position, this.getFamily().getGameElement(), false);

					if (this.getFamily().getNext() != null) {
						replaced.setNext(this.getFamily().getNext());
					}
					this.setFamily(replaced);
				}
			}
		}

		// if next Vertex contains color, search and replace it
		if (this.getNext() != null) {

			// Check all Vertexes next to you, before you check to replace the
			// Next Vertex
			LinkedList<Vertex> listOfNextVertex = this.getNext()
					.replaceInFamily(start);
			for (Vertex v : listOfNextVertex) {
				listOfNewVertex.add(v);
			}

			// Replace Next Vertex if Color and Type are Ok
			if (this.getNext().getType().equals("Variable")
					&& this.getNext().getColor() == start.getColor()) {
				cloned(start, listOfNewVertex);

				// Animation
				EvaluationExecutioner.moveAndScaleAnimationWithoutDelay(position,
						this.getNext().getGameElement(), false);

				if (this.getNext().getNext() != null) {
					replaced.setNext(this.getNext().getNext());
				}

				this.setNext(replaced);
			}
		}
		// At the End return the ColorList, if something is replaced;
		return listOfNewVertex;
	}

	/**
	 * Returns the list of the vertex and his whole family vertex.
	 * 
	 * @return List of Vertex.
	 */
	protected LinkedList<Vertex> getVertexList() {
		LinkedList<Vertex> returnList = new LinkedList<Vertex>();

		// Add family
		if (this.getFamily() != null) {
			returnList = this.getFamily().getFamilyVertexList();
		}
		returnList.add(this);
		return returnList;
	}

	/**
	 * Updates ColorList of this vertex and family.
	 */
	protected void updateColorList(LinkedList<Integer> clonedList, int color) {
		// Update Color List in vertex
		if (this.getFamilyColorList().contains(color)) {
			this.getFamilyColorList().remove((Object) color);
			for (Integer i : clonedList) {
				this.getFamilyColorList().add(i);
			}
			if (this.getFamily() != null) {
				this.getFamily().updateFamilyColorList(clonedList, color);
			}
		}
	}

	/**
	 * Method to read in the family animation.
	 * @param pos The position.
	 */
	protected void readInFamilyAnimation(Vector2 pos) {
		if (this.getNext() != null) {
			this.getNext().readInFamilyAnimation(pos);
		}
		if (this.getFamily() != null) {
			this.getFamily().readInFamilyAnimation(pos);
		}

		EvaluationExecutioner.moveAndScaleAnimation(pos, this.getGameElement(),
				false);
	}
	
	/**
	 * Set the game element and family to given position.
	 * 
	 * @param newPos
	 *            As number of the gameElementWidths.
	 */
	protected void setGameelementPosition(Vector2 start, Vector2 newPos) {

		int centerVertex = (Constants.GAMEELEMENT_WIDTH * (this.getWidth() - 1)) / 2;
		int x = Constants.GAMEELEMENT_WIDTH * (int) newPos.x + (int) start.x;
		int y = Constants.GAMEELEMENT_WIDTH * (int) newPos.y + (int) start.y;
		Vector2 paddingScreen = new Vector2(x, y);
		Vector2 startPos = new Vector2(x + centerVertex, y);

		if (this.getNext() != null) {
			this.getNext().setOtherGameelementPosition(
					new Vector2(newPos.x + this.getWidth(), newPos.y),
					startPos, paddingScreen);
		}

		if (this.getFamily() != null) {
			this.getFamily().setOtherGameelementPosition(
					new Vector2(newPos.x, newPos.y
							+ Constants.EVALUATION_DEFALT_LAYER_DIF), startPos,
					paddingScreen);
		}

		// Move
		EvaluationExecutioner
				.moveAnimation(startPos, this.getGameElement(), true);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cloneList == null) ? 0 : cloneList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		
		// Compare next
		// veritices equal only if this.next and v.next are both null or both !=
		// null.
		// For this we use xor. True if, and only if, one statement is true,
		// while the other is false.
		if (this.getNext() != null ^ other.getNext() != null) {
			return false;
		} else if (this.getNext() != null) { // in case both are not null, need
												// to compare them.
			if (!this.getNext().equals(other.getNext())) { // if they do not equal
														// we can return false
				return false;
			}
		}

		// Compare Family
		if (this.getFamily() != null ^ other.getFamily() != null) {
			return false;
		} else if (this.getFamily() != null) { // in case both are not null,
												// need to compare them.
			if (!this.getFamily().equals(other.getFamily())) { // if they do not
															// equal we can
															// return false
				return false;
			}
		}
		// If family and next is equals compare color and type if equals they are equals
		return (this.getType().equals(other.getType()) && this.getColor() == other.getColor());
	}

	/**
	 * Method to update the width.
	 */
	public void updateWidth() {
		// Update width
		if (this.getFamily() != null) {
			this.getFamily().updateFamilyWidth();
			this.setWidth(this.getFamily().getWidth()
					+ this.getFamily().getNextWidth());
		} else {
			this.setWidth(1);
		}
	}

	/**
	 * Updates pointer after beta-reduction and returns new worker.
	 * 
	 * @return The new worker.
	 */
	public Vertex updatePointerAfterBetaReduction() {

		// Update pointer if needed
		if (this.getNext() != null) {
			// Search last Vertex in first Family layer
			if (this.getFamily() != null) {
				Vertex pointer = new Dummy();
				pointer.setNext(this.getFamily());
				while (pointer.getNext().getNext() != null) {
					pointer.setNext(pointer.getNext().getNext());
				}
				// Set next Vertex of this as Next of Last in First Family layer;
				pointer.getNext().setNext(this.getNext());
			}
		}
		// return new Worker
		return this.getFamily();
	}

	/**
	 * Read in the animation for the vertex and his family.
	 * 
	 * @param pos
	 *            Position of the worker.
	 * @param e
	 *            EvaluationController where next step should be called.
	 */
	public void readInAnimation(Vector2 pos) {
		//check if pos is null
		//otherwise there would be a NullPointerException in following instruction
		if (pos == null) {
			return;
		}
		// Update Position Input
		pos.x += Constants.GAMEELEMENT_ANIMATION_WIDTH;
		pos.y += Constants.ABSTRACTION_INPUT;

		if (this.getFamily() != null) {
			this.getFamily().readInFamilyAnimation(pos);
		}
		// Animation
		EvaluationExecutioner.moveAndScaleAnimation(pos, this.getGameElement(),
				true);

	}

	/**
	 * Update coordinate of the game element with given difference(number of
	 * game elements).
	 * 
	 * @param difX
	 *            The difference on the x axis.
	 * @param difY
	 *            The difference on the y axis.
	 */
	public void updateGameelementPosition(int difX, int difY) {
		if (this.getNext() != null) {
			this.getNext().updateOtherGameelementPosition(difX, difY);
		}

		if (this.getFamily() != null) {
			this.getFamily().updateOtherGameelementPosition(difX, difY);
		}

		// Move
		Vector2 actPosition = this.getGameElement().getPosition();
		actPosition.x += (Constants.GAMEELEMENT_WIDTH * difX);
		actPosition.y += (Constants.GAMEELEMENT_WIDTH * difY);
		// Start next evaluationStep

		EvaluationExecutioner.moveAnimation(actPosition, this.getGameElement(),
				true);
	}
	
	/**
	 * Method to control if the vertex is in a depot.
	 * 
	 * @return True if it is in a depot. False otherwise.
	 */
	public boolean isInDepot() {
		return isInDepot;
	}

	/*
	 * Getter and Setter
	 */
	
	/**
	 * Getter for the familyColorList.
	 * 
	 * @return The familyColorList of this Vertex.
	 */
	public LinkedList<Integer> getFamilyColorList() {
		return familyColorList;
	}

	/**
	 * Getter for a copy of the familyColorList.
	 * 
	 * @return The familyColorList of this Vertex.
	 */
	public LinkedList<Integer> getCopyOfFamilyColorList() {
		LinkedList<Integer> copyList = new LinkedList<Integer>();
		for (Integer i : familyColorList) {
			copyList.add(i);
		}
		return copyList;
	}

	/**
	 * Getter for the nextColorList.
	 * 
	 * @return The nextColorList of this Vertex.
	 */
	public LinkedList<Integer> getNextColorList() {
		return nextColorList;
	}

	/**
	 * Getter for a copy the nextColorList.
	 * 
	 * @return The nextColorList of this Vertex.
	 */
	public LinkedList<Integer> getCopyOfNextColorList() {
		LinkedList<Integer> copyList = new LinkedList<Integer>();
		for (Integer i : nextColorList) {
			copyList.add(i);
		}
		return copyList;
	}

	/**
	 * Getter for the Color.
	 * 
	 * @return The current Color of the vertex.
	 */
	public int getColor() {
		return getMappedColor(color);
	}

	/**
	 * Getter for the family tree of this vertex.
	 * 
	 * @return The family tree of this vertex.
	 */
	public Vertex getFamily() {
		return family;
	}

	/**
	 * Getter for next Vertex in lambda-tree.
	 * 
	 * @return The next Vertex in the lambda-tree.
	 */
	public Vertex getNext() {
		return next;
	}

	/**
	 * Getter for the position.
	 * 
	 * @return The position.
	 */
	public Vector2 getPosition() {
		return pos;
	}

	/**
	 * Getter for the width.
	 * @return The width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter for the width of the next vertex.
	 * @return The width of the next vertex.
	 */
	public int getNextWidth() {
		return nextWidth;
	}

	/**
	 * Setter for the width of the next vertex.
	 * @param w The new width.
	 */
	public void setNextWidth(int w) {
		nextWidth = w;
	}	
	
	/**
	 * Setter for the width of this vertex.
	 * @param w The new width.
	 */
	public void setWidth(int w) {
		width = w;
	}

	
	/**
	 * Setter for next Vertex in the lambda-tree.
	 * 
	 * @param next
	 *            Next vertex that is to be set.
	 */
	public void setNext(Vertex next) {
		this.next = next;
	}

	/**
	 * Setter for the family tree of this vertex.
	 * 
	 * @param family
	 *            The start vertex for the family that is to be set.
	 * @return False if type of Vertex is Variable. True otherwise.
	 */
	public void setFamily(Vertex family) {
		this.family = family;
	}

	/**
	 * Setter for the familyColorList.
	 * 
	 * @param familyColorList
	 *            FamilyColorList that is to be set.
	 */
	public void setFamilyColorlist(LinkedList<Integer> familyColorList) {
		this.familyColorList = familyColorList;
	}

	/**
	 * Setter for the nextColorList.
	 * 
	 * @param nextColorList
	 *            NextColorList that is to be set.
	 */
	public void setNextColorlist(LinkedList<Integer> nextColorList) {
		this.nextColorList = nextColorList;
	}

	/**
	 * Setter for the position of the vertex.
	 * @param p The new position.
	 */
	public void setPosition(Vector2 p) {
		pos = p;
	}

	/**
	 * Setter for setting if the vertex is in a depot.
	 * 
	 * @param b If the vertex is in a depot.
	 */
	public void setIsInDepot(boolean b) {
		isInDepot = b;
	}
}
