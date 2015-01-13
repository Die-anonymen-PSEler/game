package com.retroMachines.util.lambda;

/**
 * The different types that a vertex could be.
 * @author RetroFactory
 *
 */
public enum VertexType {
	/**
	 * The type of the vertex is not set yet.
	 */
	NotSetYet ,
	/**
	 * The vertex stands for the abstraction of the lambda-term.
	 * In the game it is represented by a machine.
	 */
	Abstraction, 
	/**
	 * The vertex stands for the application of the lambda-term.
	 * In the game it is represented by a light element.
	 */
	Application, 
	/**
	 * The vertex stands for the variable of the lambda-term.
	 * In the game it is represented by a metal element.
	 */
	Variable
}
