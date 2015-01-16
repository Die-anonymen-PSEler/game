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
	private char id;
	
	/**
	 * List of all ID's of vertices corresponding to this abstraction.
	 */
	private LinkedList<Character> familyIdlist;
	
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
	public Vertex(char id,VertexType type) {
		this.type = type;
		this.id = id;
		this.familyIdlist = new LinkedList<Character>();
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
	private boolean betaReduction() {
		// TODO: beta reduction
		return false;
	}

	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique ID's.
	 * 
	 * @return True if at least one ID has changed, false if no ID has changed.
	 */
	private boolean alphaConversion() {
		// TODO: implement alpha conversion
		return false;
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
	public int getId() {
		return id;
	}

	/**
	 * Setter for the ID.
	 * 
	 * @param id
	 *            ID that is to set.
	 */
	public void setId(char id) {
		this.id = id;
	}
	
	public LinkedList<Character> getFamilyIdList(){
		return familyIdlist;
	}
}
