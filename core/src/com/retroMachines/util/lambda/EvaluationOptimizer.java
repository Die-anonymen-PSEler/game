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

public class EvaluationOptimizer {

	public final static EvaluationOptimizer OPTIMIZER = new EvaluationOptimizer();

	private static EvaluationController EvaluationController;

	/**
	 * The resultTree represents the result of the Evaluation
	 */
	private static LevelTree ResultTree;

	/**
	 * List of all gameElements in this level
	 */
	private static LinkedList<Vertex> VertexList;

	/**
	 * resultPointer.next is always last element added to resultTree
	 */
	private static Vertex ResultPointer;

	/**
	 * evaluationPointer . next is always the actual worker in evaluation
	 */
	private static Vertex EvalutionPointer;

	private static int OffsetX;

	private static boolean Result;

	private static boolean NextStep;

	private static boolean AutoStep;

	private static int ActStep;
	
	private static LinkedList<ActionListElement> ActionList = new LinkedList<ActionListElement>();
	
	private static LinkedList<EvaluationController> AnimationListner = new LinkedList<EvaluationController>(); 

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
			EvalutionPointer.setnext(EvaluationController.getlambdaTree()
					.getStart());
		}
		ActStep = 0;
		ResultTree = null;
		VertexList = null;	
	}

	// -----Methods-------

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

	public static void runNextStep() {
		runNextEvaluationStep();
	}

	public static void delayAndRunNextStepAnim(GameElement x) {

		Action action = Actions.sequence(Actions.delay(Constants.ACTION_TIME),
				Actions.run(new NextStepWithoutRemove()));
		ActionList.addLast(new ActionListElement(x, action));
	}

	private static void step1AlphaConversion() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 1;
		EvalutionPointer.getnext().alphaConversion();

		Vertex readIn = EvalutionPointer.getnext().getReadIn();
		if (readIn != null) {
			readIn.readInAnimation(EvalutionPointer.getnext().getGameElement()
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
		VertexList = EvalutionPointer.getnext().betaReduction();
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
		EvalutionPointer.getnext()
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
		EvalutionPointer.getnext().deleteAfterBetaReduction();
		//notify Controller
		notifyEvaluationController();
	}

	private static void step5UpdateFamilyPositions() {
		//reset List
		ActionList = new LinkedList<ActionListElement>();
		ActStep = 5;
		EvalutionPointer.getnext().updatePositionsAfterBetaReduction();
		//notify Controller
		notifyEvaluationController();
	}

	private synchronized static void prepareNextEvaluation() {
		ActStep = 0;

		// creating copy of current tree
		LevelTree oldTree = new LevelTree(EvalutionPointer.cloneMe());
		EvalutionPointer.setfamily(EvalutionPointer.getnext());
		Vertex result = EvalutionPointer.getnext().getEvaluationResult();

		if (result != null) {
			// check whether there is an infinite evaluation (same tree after
			// evaluation as before)
			if (result.getnext() != null
					&& !result.getType().equals(
							Constants.RetroStrings.VARIABLE_TYPE)) {
				LevelTree newTree = new LevelTree(result.getnext());
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
				ResultPointer.getnext().setnext(result);
				ResultPointer.setnext(ResultPointer.getnext().getnext());
			} else {
				ResultTree = new LevelTree(result);
				ResultPointer.setnext(ResultTree.getStart());
			}
		}

		EvalutionPointer.setnext(EvalutionPointer.getnext()
				.updatePointerAfterBetaReduction());

		if (EvalutionPointer.getnext() == null) {
			// End of Evaluation
			ResultPointer.getnext().setnext(null);
			checkEvaluation();
		} else {
			if (EvalutionPointer.getnext().getType()
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

	public static void autoStepClicked() {
		if (!AutoStep && !NextStep) {
			AutoStep = true;
			runNextEvaluationStep();
		}

	}

	public static void nextStepClicked() {
		if (!NextStep) {
			NextStep = true;
			runNextEvaluationStep();
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
	
	public static LinkedList<ActionListElement> getActionList() {
		return ActionList;
	}
	
	/**
	 *  called at the end of each step
	 */
	private static void notifyEvaluationController() {
		for (EvaluationController e : AnimationListner) {
			e.startAnimation();
		}
	}
	
	public static void addMeToListnerList(EvaluationController e) {
		if (e != null) {
			if(!AnimationListner.contains(e)) {
				AnimationListner.add(e);
			}
		}
	}
}
