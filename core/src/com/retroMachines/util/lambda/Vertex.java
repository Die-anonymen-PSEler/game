package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is part of the model of RetroMachines. It models the vertices
 * of the graph of the lambda term and represents the 3 gameelements.
 * 
 * @author RetroFactory
 * 
 */
public class Vertex {

	/**
	 * Reference to next input
	 */
	private Vertex next;
	
	/**
	 * Reference to the output tree
	 */
	private Vertex family;
	
	/**
	 * The Gameelement type of each Vertex
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
	 * Creates a new instance of Vertex class.
	 * 
	 * @param id
	 *            id to set
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
	 * replace the oldId with the newId in IdList
	 * needed when a parent Abstraction makes Betareduction or Alphaconversion
	 * @param oldId id which should replaced
	 * @param newId id which should take place of oldId
	 * @return true if IdList contained the oldId
	 */
	public boolean updateIdList(int oldId, int newId) {
		return false;
	}
	
	public void addFamilyIdList(char addId){
		
	}
	
	// --------------------------
	// ------Getter/Setter-------
	// --------------------------
	
	/**
	 * Getter for next Vertex in lambdatree.
	 * 
	 * @return the next Vertex in the lambdatree
	 */
	public Vertex getnext() {
		return next;
	}
	
	/**
	 * Setter for next Vertex in the lambdatree
	 * 
	 * @param next
	 * 				next vertex to set
	 */
	public void setnext(Vertex next) {
		this.next = next;
	}
	
	/**
	 * Getter for the family tree of this vertex
	 * 
	 * @return the family tree of this vertex
	 */
	public Vertex getfamily() {
		return family;
	}
	
	/**
	 * Getter for the ID.
	 * 
	 * @param family 
	 * 				family start vertex to set
	 */
	public void setfamily(Vertex family) {
		this.family = family;
	}

	
	/**
	 * Getter for the ID.
	 * 
	 * @return the id of the variable
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the ID.
	 * 
	 * @param id
	 *            id to set
	 */
	public void setId(char id) {
		this.id = id;
	}
	
	public LinkedList<Character> getFamilyIdList(){
		return familyIdlist;
	}
}
