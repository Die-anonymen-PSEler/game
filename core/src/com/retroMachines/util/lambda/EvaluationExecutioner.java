package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.ActionListElement;
import com.retroMachines.util.Constants;

/**
 * Class for the evaluation executioner.
 * @author RetroFactory
 * @version 1.0
 */
public class EvaluationExecutioner {

	/**
	 * The optimizer.
	 */
	public final static EvaluationExecutioner OPTIMIZER = new EvaluationExecutioner();

	private static EvaluationController EvaluationController;

	/**
	 * The resultTree represents the result of the Evaluation.
	 */
	private static LevelTree ResultTree;

	/**
	 * List of all gameElements in this level.
	 */
	private static LinkedList<Vertex> VertexList;

	/**
	 * ResultPointer.next is always last element added to resultTree.
	 */
	private static Vertex ResultPointer;

	/**
	 * EvaluationPointer .next is always the actual worker in evaluation.
	 */
	private static Vertex EvalutionPointer;

	private static int OffsetX;

	private static boolean Result;

	private static boolean NextStep;

	private static boolean AutoStep;

	private static int ActStep;
	
	private static LinkedList<ActionListElement> ActionList = new LinkedList<ActionListElement>();
	
	private static LinkedList<EvaluationController> AnimationListner = new LinkedList<EvaluationController>(); 
	
	private static void step1AlphaConversion() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 1;
		EvalutionPointer.getNext().alphaConversion();

		Vertex readIn = EvalutionPointer.getNext().getReadIn();
		if (readIn != null) {
			readIn.readInAnimation(EvalutionPointer.getNext().getGameElement()
					.getPosition());
			// Next Step by Animation
			//notify Controller
			notifyEvaluationController();
		} else {
			runNextEvaluationStep();
		}
		

	}

	private static void step2BetaReduction() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 2;
		VertexList = EvalutionPointer.getNext().betaReduction();
		// Next Step by Animation
		//notify Controller
		notifyEvaluationController();
	}

	private static void step3UpdatePositions() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 3;
		Vector2 start = EvaluationController.getEvaluationscreenPadding();
		start.x += OffsetX;
		EvalutionPointer.getNext()
				.reorganizePositions(start, new Vector2(0, 0));
		// Next Step by Animation
		//notify Controller
		notifyEvaluationController();
	}

	private static void step4InsertReadIn() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 4;
		// only Abstraction returns a list with elements else it is empty
		for (Vertex v : VertexList) {
			EvaluationController.setOnStage(v.getGameElement());
		}

		// At the end worker disappears when its an Abstraction or Application
		EvalutionPointer.getNext().deleteAfterBetaReduction();
		//notify Controller
		notifyEvaluationController();
	}

	private static void step5UpdateFamilyPositions() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 5;
		EvalutionPointer.getNext().updatePositionsAfterBetaReduction();
		//notify Controller
		notifyEvaluationController();
	}

	private synchronized static void prepareNextEvaluation() {
		ActStep = 0;

		// creating copy of current tree
		LevelTree oldTree = new LevelTree(EvalutionPointer.cloneMe());
		Vertex result = EvalutionPointer.getNext().getEvaluationResult();
		if (result != null) {
			// check whether there is an infinite evaluation (same tree after
			// evaluation as before)
			if (result.getNext() != null
					&& !result.getType().equals(
						Constants.RetroStrings.VARIABLE_TYPE)
					&& EvalutionPointer.getFamily().getType().equals(EvalutionPointer.getNext().getType())
					&& EvalutionPointer.getFamily().getColor() == EvalutionPointer.getNext().getColor()) {
				
				LevelTree newTree = new LevelTree(result.getNext());
				if (oldTree.equalsTree(newTree)) {
					// we can stop evaluation at this point
					ResultTree = oldTree;
					checkEvaluation();
					return;
				}
			}
			// make resultTree if needed
			OffsetX += result.getWidth() * Constants.GAMEELEMENT_WIDTH;
			if (ResultTree != null) {
				if(ResultPointer.getNext().getType().equals(Constants.RetroStrings.ABSTRACTION_TYPE)) {
					ResultPointer.getNext().setFamily(result);
					ResultPointer.setNext(ResultPointer.getNext().getFamily());
				} else {
					ResultPointer.getNext().setNext(result);
					ResultPointer.setNext(ResultPointer.getNext().getNext());
				}

			} else {
				ResultTree = new LevelTree(result);
				ResultPointer.setNext(ResultTree.getStart());
			}
		}
		
		// Stor last worker
		Vertex v = EvalutionPointer.getNext().cloneMe();
		EvalutionPointer.setFamily(v);
		EvalutionPointer.setNext(EvalutionPointer.getNext()
				.updatePointerAfterBetaReduction());
		
		if (EvalutionPointer.getNext() == null) {
			// End of Evaluation
			ResultPointer.getNext().setNext(null);
			checkEvaluation();
		} else {
			if (EvalutionPointer.getNext().getType()
					.equals(Constants.RetroStrings.VARIABLE_TYPE)) {
				runNextEvaluationStep();
				return;
			}
			// Wenn alle steps starten geklickt starte nächsten schritt sonst
			// wart auf eingabe
			if (AutoStep) {
				runNextEvaluationStep();
			} else {
				NextStep = false;
			}
		}
	}

	/**
	 * This method is called after the last evaluation step , evaluation result
	 * is saved in resultTree
	 */
	private static void checkEvaluation() {
		Result = ResultTree.equalsTree(EvaluationController.getLevel()
				.getLambdaUtil().getTargetTree());
		if (Result) {
			EvaluationController.getGameController().evaluationComplete();
		} else {
			EvaluationController.getGameController().evaluationInComplete();
		}
	}

	private static void runNextEvaluationStep() {
		if(EvaluationController == null) {
			Gdx.app.log(Constants.LOG_TAG, "EvaluationOptimizer not initialized");
			return;
		}
		switch (ActStep) {
		case 0:
			step1AlphaConversion();
			break;
		case 1:
			step2BetaReduction();
			break;
		case 2:
			step3UpdatePositions();
			break;
		case 3:
			step4InsertReadIn();
			break;
		case 4:
			step5UpdateFamilyPositions();
			break;
		case 5:
			prepareNextEvaluation();
			break;
		default:
			Gdx.app.log(Constants.LOG_TAG, "act Step error");
		}
	}
	
	/**
	 *  called at the end of each step
	 */
	private static void notifyEvaluationController() {
		for (EvaluationController e : AnimationListner) {
			e.startAnimation();
		}
	}

	/**
	 * Method to initialize the executioner.
	 * @param e The evaluation controller.
	 */
	public static void initialize(EvaluationController e) {
		AnimationListner = new LinkedList<EvaluationController>();
		ActionList = new LinkedList<ActionListElement>();
		EvaluationController = e;
		ResultPointer = new Dummy();
		OffsetX = 0;
		NextStep = false;
		AutoStep = false;
		EvalutionPointer = new Dummy();
		if(EvaluationController != null) {
			EvalutionPointer.setNext(EvaluationController.getlambdaTree()
					.getStart());
		}
		ActStep = 0;
		ResultTree = null;
		VertexList = null;	
	}

	// -----Methods-------

	/**
	 * Method for the animation immediately. 
	 * @param pos The position.
	 * @param x The game element.
	 * @param nextStep The next step.
	 */
	public static void moveAndScaleAnimationWithoutDelay(Vector2 pos,
			GameElement x, boolean nextStep) {
		Action action;

		if (nextStep) {
			action = Actions.sequence(Actions.parallel(Actions.moveTo(pos.x, pos.y,
					Constants.ACTION_TIME), Actions.scaleTo(
					Constants.GAMEELEMENT_SCALING,
					Constants.GAMEELEMENT_SCALING, Constants.ACTION_TIME)),
					Actions.run(new NextStep(x)));
		} else {
			action = Actions.sequence(Actions.parallel(Actions.moveTo(pos.x, pos.y,
					Constants.ACTION_TIME), Actions.scaleTo(
					Constants.GAMEELEMENT_SCALING,
					Constants.GAMEELEMENT_SCALING, Constants.ACTION_TIME)),
					Actions.run(new DestroyElement(x)));
		}
		ActionList.addLast(new ActionListElement(x, action));
	}

	/**
	 * Method for the animation.
	 * @param pos The position.
	 * @param x The game element.
	 * @param nextStep The next step.
	 */
	public static void moveAndScaleAnimation(Vector2 pos, GameElement x,
			boolean nextStep) {
		Action action;

		if (nextStep) {
			action = Actions
					.sequence(Actions.delay(Constants.ACTION_TIME), Actions
							.parallel(Actions.moveTo(pos.x, pos.y,
									Constants.ACTION_TIME), Actions.scaleTo(
									Constants.GAMEELEMENT_SCALING,
									Constants.GAMEELEMENT_SCALING,
									Constants.ACTION_TIME)), Actions
							.run(new NextStep(x)));
		} else {
			action = Actions
					.sequence(Actions.delay(Constants.ACTION_TIME), Actions
							.parallel(Actions.moveTo(pos.x, pos.y,
									Constants.ACTION_TIME), Actions.scaleTo(
									Constants.GAMEELEMENT_SCALING,
									Constants.GAMEELEMENT_SCALING,
									Constants.ACTION_TIME)), Actions
							.run(new DestroyElement(x)));
		}
		ActionList.addLast(new ActionListElement(x, action));
	}

	/**
	 * Method to scale the animation.
	 * @param x The game element.
	 * @param nextStep The next step.
	 */
	public static void scaleAnimation(GameElement x, boolean nextStep) {
		Action action;
		if (nextStep) {
			action = Actions.sequence(Actions.delay(Constants.ACTION_TIME), Actions
					.scaleTo(Constants.GAMEELEMENT_SCALING,
							Constants.GAMEELEMENT_SCALING,
							Constants.ACTION_TIME), Actions
					.run(new NextStep(x)));
		} else {
			action = Actions.sequence(Actions.delay(Constants.ACTION_TIME), Actions
					.scaleTo(Constants.GAMEELEMENT_SCALING,
							Constants.GAMEELEMENT_SCALING,
							Constants.ACTION_TIME), Actions
					.run(new DestroyElement(x)));
		}
		ActionList.addLast(new ActionListElement(x, action));
	}

	/**
	 * Method for the moving animation.
	 * @param pos The position.
	 * @param x The game element.
	 * @param nextStep The next step.
	 */
	public static void moveAnimation(Vector2 pos, GameElement x,
			boolean nextStep) {
		Action action;

		if (nextStep) {
			action = Actions.sequence(
					Actions.moveTo(pos.x, pos.y, Constants.ACTION_TIME),
					Actions.run(new NextStepWithoutRemove()));

		} else {
			action = Actions.moveTo(pos.x, pos.y, Constants.ACTION_TIME);
		}
		ActionList.addLast(new ActionListElement(x, action));
	}

	/**
	 * Method to execute the next step.
	 */
	public static void runNextStep() {
		runNextEvaluationStep();
	}

	/**
	 * Method to delay and run the animation of the next step.
	 * @param x The game element.
	 */
	public static void delayAndRunNextStepAnim(GameElement x) {

		Action action = Actions.sequence(Actions.delay(Constants.ACTION_TIME),
				Actions.run(new NextStepWithoutRemove()));
		ActionList.addLast(new ActionListElement(x, action));
	}

	/**
	 * The autoStep was clicked.
	 */
	public static void autoStepClicked() {
		if (!AutoStep && !NextStep) {
			AutoStep = true;
			runNextEvaluationStep();
		}

	}

	/**
	 * The nextStep was clicked.
	 */
	public static void nextStepClicked() {
		if (!AutoStep && !NextStep) {
			NextStep = true;
			runNextEvaluationStep();
		}
	}
	
	/**
	 * Getter for the action list.
	 * @return The action list.
	 */
	public static LinkedList<ActionListElement> getActionList() {
		return ActionList;
	}
	
	/**
	 * Add the caller to the listener list.
	 * @param e The controller.
	 */
	public static void addMeToListnerList(EvaluationController e) {
		if (e != null) {
			if(!AnimationListner.contains(e)) {
				AnimationListner.add(e);
			}
		}
	}

	private static class DestroyElement implements Runnable {

		private GameElement element;

		public DestroyElement(GameElement g) {
			this.element = g;
		}

		@Override
		public void run() {
			element.remove();
		}

	}

	private static class NextStep implements Runnable {
		private GameElement element;

		public NextStep(GameElement g) {
			this.element = g;
		}

		@Override
		public void run() {
			element.remove();
			runNextEvaluationStep();
		}

	}

	private static class NextStepWithoutRemove implements Runnable {

		public NextStepWithoutRemove() {
		}

		@Override
		public void run() {
			runNextEvaluationStep();
		}
	}
	
}
