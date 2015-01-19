package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is part of the model of RetroMachines. It models the vertices
 * of the graph of the lambda term and represents the 3 different game elements.
 * 
 * @author RetroFactory
 * 
 */
public class Vertex {

	/**
	 * Reference to next input.
	 */
	private Vertex next;
	
	/**
	 * Reference to the output tree.
	 */
	private Vertex family;
	
	/**
	 * The type of the game element of the vertex.
	 */
	private VertexType type;
	
	/**
	 * Unique identifier of Variable.
	 */
	private int color;
	
	/**
	 * List of all color's of vertices corresponding to this abstraction.
	 * color's are sorted after their size small to big 
	 */
	private LinkedList<Integer> familyColorlist;
	
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
	 * Creates a new instance of the Vertex class.
	 * 
	 * @param id
	 *            ID to set.
	 */
	public Vertex(int color,VertexType type) {
		this.type = type;
		this.color = color;
		this.familyColorlist = new LinkedList<Integer>();
		this.familyColorlist.add(color);
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
		return false;
	}
	
	/**
	 * Add element to IdList
	 * @param addId id which should be added
	 */
	public void addFamilyIdList(char addId){
		
	}
	
	/**
	 * Fulfills one step of beta-reduction.
	 * 
	 * @return True if this abstraction has changed, false otherwise.
	 */
	public boolean betaReduction() {
		return false;
	}

	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	public boolean alphaConversion() {
		LinkedList<Integer> nextFam = this.next.getFamilyColorList();
		int sA = familyColorlist.size();
		int sN = nextFam.size();
		int newColor = this.familyColorlist.getLast() + 1;
		// Dursucche Liste nach Doppelten farben
		for (int i = 0; i < sA; i++) {
			for (int j = 0; j < sN; j++) {
				if (familyColorlist.get(i) == nextFam.get(j)) {
					//Ersetze Farbe in eingelesener Familie
					if (!this.next.renameFamily(nextFam.get(j), newColor)) {
						// Fehler 
						System.out.println("AlphaConversionError: " + this.color + ", " + this.type.toString());
					}
					newColor++;
				}
			}
		}
		return false;
	}
	
	/**
	 * replace OldColor with newColor in Hole Family
	 * @param oldColor Color which should be replaced
	 * @param newColor Color which should take place of OldColor
	 * @return true if renamed family successful, false otherwise
	 */
	public boolean renameFamily(int oldColor, int newColor) {
		int index = 0;
		// Get Index of oldColor 
		while(this.familyColorlist.get(index) < oldColor) {
			index++;
		}
		
		// Replace Color if
		if (this.familyColorlist.get(index) == oldColor) {
			
			// Replace Color in family Color List
			this.familyColorlist.remove(index);
			if (familyColorlist.getLast() >= newColor) {
				/* Falsche Sortierung/ newColor
				 * New color ist immer größtes Element in gesamter Liste
				 */
				return false;
			}
			this.familyColorlist.addLast(newColor);
			
			// Replace own Color if needed
			if (this.color == oldColor){
				this.color = newColor;
			}
			
			// Rename Family
			if (this.family != null) {
				
				// Rename First in Family
				if (this.family.renameFamily(oldColor, newColor)) {
					//Irgendwo lief was schief
					return false;
				}
				
				// Rename the Others if they are no imaginary Friends  
				Vertex renamePointer = this.family;
				while(renamePointer.next != null) {
					if (!renamePointer.next.renameFamily(oldColor, newColor)) {
						//Irgendwo lief was schief
						return false;
					}
					// Setze Pointer auf nächstes Family Vertex
					renamePointer = renamePointer.next;
				}
			}
		}
		return true;
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
	 * Getter for the ID.
	 * 
	 * @param family 
	 * 				The start vertex for the family that is to set.
	 */
	public void setfamily(Vertex family) {
		this.family = family;
	}

	
	/**
	 * Getter for the ID.
	 * 
	 * @return The ID of the vertex.
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Setter for the ID.
	 * 
	 * @param id
	 *            ID that is to set.
	 */
	public void setId(int color) {
		this.color = color;
	}
	
	public LinkedList<Integer> getFamilyColorList(){
		return familyColorlist;
	}
}
