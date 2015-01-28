package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is part of the model of RetroMachines. 
 * Vertices of the Graph of Gameelements extends this class
 * 
 * @author RetroFactory
 * 
 */
public abstract class Vertex {
	
	/**
	 * Reference to next input.
	 */
	private Vertex next;
	
	/**
	 * Reference to the output tree.
	 */
	private Vertex family;
	
	/**
	 * Unique identifier of Variable.
	 */
	private int color;
	
	/**
	 * List of all color's of vertices corresponding to this abstraction.
	 * color's are sorted after their size small to big 
	 */
	private LinkedList<Integer> familyColorList;
	
	/**
	 * Position of vertex
	 */
	private Vector2 pos;
	
	/**
	 * True if this vertex is placed in a depot, false otherwise
	 */
	private boolean isInDepot;
	
	// --------------------------
	// --------Constructor-------
	// --------------------------
	
	/**
	 * Default Public Constructor for Dummy Element
	 */
	public Vertex() {
		
	}
	
	/**
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param color
	 *            color to set.
	 */
	public Vertex(int color) {
		this.color = color;
		this.familyColorList = new LinkedList<Integer>();
		this.familyColorList.add(color);
	}	
	
	// --------------------------
	// ---------Methods----------
	// --------------------------
	
	/**
	 * Replaces the oldId with the newId in the IdList.
	 * It  is needed when a parent Abstraction performs a Beta-reduction or an Alpha-conversion.
	 * @param oldId ID which should be replaced.
	 * @param newId ID which should take place of the former ID.
	 * @return true if the IdList contained the oldId and it was changed successfully.
	 */
	public boolean updateIdList(int oldId, int newId) {
		boolean updated = false;
		for(Integer id : familyColorList) {
			if (id == oldId) {
				id = newId;
				updated = true;
				//break;
			}
		}
		return updated;
	}
	
	/**
	 * Add element to IdList
	 * @param addId id which should be added
	 */
	public void addFamilyIdList(int addId){
		if (familyColorList.contains(addId)) {
			return; //list already contains id
		}
		int index = 0;
		for (int id : familyColorList) {
			if (id > addId) {
				index = familyColorList.indexOf(id);
				break;
			}
		}
		if (index == 0) {
			familyColorList.addFirst(addId);
		} else {
			familyColorList.add(index - 1, addId);
		}
		
	}
	
	//---------------------------------------------------
	//-------- Beta Reduction and Alpha Conversion ------
	//---------------------------------------------------
	
	/**
	 * Fulfills one step of beta-reduction.
	 * 
	 * @return True if this abstraction has changed, false when an error appeared.
	 */
	abstract public boolean betaReduction();
	
	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	abstract public boolean alphaConversion();
	//---------------------------------------------------
	//-------- Beta Reduction and Alpha Conversion ------
	//------------------Help Methods---------------------
	//---------------------------------------------------
	
	/**
	 * replace OldColor with newColor in Hole Family
	 * @param oldColor Color which should be replaced
	 * @param newColor Color which should take place of OldColor
	 * @return true if renamed family successful, false otherwise
	 */
	protected boolean renameFamily(int oldColor, int newColor) {
		int index = 0;
		// Get Index of oldColor 
		while(this.familyColorList.get(index) < oldColor) {
			index++;
		}
		
		// Replace Color 
		if (this.familyColorList.get(index) == oldColor) {
			
			// Replace Color in family Color List
			this.familyColorList.remove(index);
			if (familyColorList.getLast() >= newColor) {
				/* Falsche Sortierung/ newColor
				 * New color ist immer gr��tes Element in gesamter Liste
				 */
				return false;
			}
			this.familyColorList.addLast(newColor);
			
			// Replace own Color if needed
			if (this.color == oldColor){
				this.color = newColor;
			}
			
			// Rename Family
			if (this.family != null) {
				
				// Rename First in Family
				if (this.family.renameFamily(oldColor, newColor)) {
					//Error
					return false;
				}
				
				// Rename the Others if they are no imaginary Friends  
				Vertex renamePointer = this.family;
				while(renamePointer.next != null) {
					if (!renamePointer.next.renameFamily(oldColor, newColor)) {
						//Error
						return false;
					}
					// Set 
					renamePointer = renamePointer.next;
				}
			}
		}
		return true;
	}
	
	/**
	 * Creates a clone of this Vertex without Next and his hole Family
	 * @param next vertex to clone
	 * @return deep copy of next
	 */
	abstract public Vertex cloneMe(Vertex next);
	
	/**
	 * Creates a clone of this Vertex and his hole Family
	 * @return clone of Vertex with hole family and next coned
	 */
	abstract public Vertex cloneFamily();
	
	/**
	 * replaces all Elements of a specific color in family of start Vertex
	 * @param start vertex which is parent of this Vertex and starts the beta Reduction
	 * @return
	 */
	protected LinkedList<Integer> replaceInFamily(Vertex start) {
		
		LinkedList<Integer> listOfNewColors = new LinkedList<Integer>();
		
		// if family contains color, search and replace it
		if (this.getFamilyColorList().contains(start.getColor())) {
			if (this.getfamily() != null) {
				
				// Check Family Vertexes before you check to replace the first in Family
				listOfNewColors = this.getfamily().replaceInFamily(start);
				
				// Stop replacing of vertexes! betaReduction will know what to do
				if (listOfNewColors == null) {
					return null;
				}
				
				//When there is a change in Family merge it with your list
				if (!listOfNewColors.isEmpty()) {
					//Remove the searched color from your list if it is replaced in your family
					this.getFamilyColorList().remove(start.getColor());
					this.mergeMyColorList(listOfNewColors);
				}
				
				//Replace Family Vertex if Color is ok
				if (this.getfamily().getColor() == start.getColor()) {
					Vertex replaced = start.getnext().cloneMe(this.getfamily().getnext());
					this.setfamily(replaced);
				}
			} else {
				// Error each Abstraction has family
				return null;
			}
		}
		
		// if next Vertex contains color, search and replace it 
		if (this.getnext() != null) {
			
			// Check all Vertexes next to you, before you check to replace the Next Vertex
			listOfNewColors = this.getnext().replaceInFamily(start);
			
			// Stop replacing of vertexes! betaReduction will know what to do
			if (listOfNewColors == null) {
				return null;
			}
			
			//Replace Next Vertex if Color and Type are Ok
			if (this.getnext().getColor() == start.getColor()) {
				Vertex replaced = start.getnext().cloneMe(this.getfamily().getnext());
				this.setnext(replaced);
			}
		}
		// At the End return the ColorList, if something is replaced;
		return listOfNewColors;
	}
	
	/**
	 * Merges the given List with the color List of this Vertex
	 * @param listToAdd List wich should be merged with this color List
	 */
	protected void mergeMyColorList(LinkedList<Integer> listToAdd) {
		int lengthBoth = this.getFamilyColorList().size();
		lengthBoth =+ listToAdd.size();
		LinkedList<Integer> newColorList = new LinkedList<Integer>();
		for (int i = 0; i < lengthBoth; i++) {
			
			// if one list is empty add the rest of the other one to the end
			if(this.getFamilyColorList().isEmpty()) {
				int l = listToAdd.size();
				for (int j = 0; j < l; j++) {
					newColorList.addLast(listToAdd.pollFirst());
				}
				break;
			}
			if(listToAdd.isEmpty()) {
				int l = this.getFamilyColorList().size();
				for (int j = 0; j < l; j++) {
					newColorList.addLast(this.getFamilyColorList().pollFirst());
				}
				break;
			}
			
			// if there are still elements in one List search the smallest one and add it to the new List
			if (this.getFamilyColorList().getFirst() < listToAdd.getFirst()) {
				newColorList.addLast(this.getFamilyColorList().pollFirst());
			} else {
				newColorList.addLast(listToAdd.pollFirst());
			}
			
		}
		this.setFamilyColorlist(newColorList);
	}
	
	// --------------------------
	// ------Getter/Setter-------
	// --------------------------
	
	/**
	 * Getter for next Vertex in lambda-tree.
	 * 
	 * @return The next Vertex in the lambda-tree.
	 */
	public Vertex getnext() {
		return next;
	}
	
	/**
	 * Setter for next Vertex in the lambda-tree.
	 * 
	 * @param next
	 * 				Next vertex that is to set.
	 */
	public void setnext(Vertex next) {
		this.next = next;
	}
	
	/**
	 * Getter for the family tree of this vertex.
	 * 
	 * @return The family tree of this vertex.
	 */
	public Vertex getfamily() {
		return family;
	}
	
	/**
	 * Setter for the family tree of this vertex.
	 * 
	 * @param family 
	 * 				The start vertex for the family that is to set.
	 * @return
	 * 				false if type of Vertex is Variable , true otherwise
	 */
	public boolean setfamily(Vertex family) {
		this.family = family;
		return true;
	}
	
	/**
	 * Getter for the Color.
	 * 
	 * @return The Color of the vertex.
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Setter for the Color.
	 * 
	 * @param color
	 *          	Color that is to set.
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Getter for the familyColorList
	 * 
	 * @return The familyColorList of this Vertex
	 */
	public LinkedList<Integer> getFamilyColorList(){
		return familyColorList;
	}
	
	/**
	 * Setter for the famiylColorList
	 * 
	 * @param familyColorList
	 * 				FamilyColorList that is to set
	 */
	protected void setFamilyColorlist(LinkedList<Integer> familyColorList) {
		this.familyColorList = familyColorList;
	}
	
	/**
	 * updates start vertex. This vertex if family is null.
	 * @return
	 */
	public Vertex updateStart() {
		if (family == null) {
			return this;
		} else {
			return family;
		}
	}
}

