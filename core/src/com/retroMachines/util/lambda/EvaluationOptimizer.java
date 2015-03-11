package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;

public class EvaluationOptimizer {

	public final static EvaluationOptimizer optimizer = new EvaluationOptimizer();

	private static EvaluationController evaluationController;

	/**
	 * The resultTree represents the result of the Evaluation
	 */
	private static LevelTree resultTree;

	/**
	 * List of all gameElements in this level
	 */
	private static LinkedList<Vertex> vertexList;

	/**
	 * resultPointer.next is always last element added to resultTree
	 */
	private static Vertex resultPointer;

	/**
	 * evaluationPointer . next is always the actual worker in evaluation
	 */
	private static Vertex evalutionPointer;

	private static int offsetX;

	private static boolean result;

	private static boolean nextStep;

	private static boolean autoStep;

	private static int actStep;

	public static void initialize(EvaluationController e) {
		evaluationController = e;
		resultPointer = new Dummy();
		offsetX = 0;
		nextStep = false;
		autoStep = false;
		evalutionPointer = new Dummy();
		evalutionPointer.setnext(evaluationController.getlambdaTree()
				.getStart());
		actStep = 0;
		resultTree = null;
		vertexList = null;

	}

	// -----Methods-------

	public static void moveAndScaleAnimationWithoutDelay(Vector2 pos,
			GameElement x, boolean nextStep) {
		Action a;

		if (nextStep) {
			a = Actions.sequence(Actions.parallel(Actions.moveTo(pos.x, pos.y,
					Constants.ACTION_TIME), Actions.scaleTo(
					Constants.GAMEELEMENT_SCALING,
					Constants.GAMEELEMENT_SCALING, Constants.ACTION_TIME)),
					Actions.run(new NextStep(x)));
		} else {
			a = Actions.sequence(Actions.parallel(Actions.moveTo(pos.x, pos.y,
					Constants.ACTION_TIME), Actions.scaleTo(
					Constants.GAMEELEMENT_SCALING,
					Constants.GAMEELEMENT_SCALING, Constants.ACTION_TIME)),
					Actions.run(new DestroyElement(x)));
		}
		evaluationController.runAnimation(x, a);
	}

	public static void moveAndScaleAnimation(Vector2 pos, GameElement x,
			boolean nextStep) {
		Action a;

		if (nextStep) {
			a = Actions
					.sequence(Actions.delay(Constants.ACTION_TIME), Actions
							.parallel(Actions.moveTo(pos.x, pos.y,
									Constants.ACTION_TIME), Actions.scaleTo(
									Constants.GAMEELEMENT_SCALING,
									Constants.GAMEELEMENT_SCALING,
									Constants.ACTION_TIME)), Actions
							.run(new NextStep(x)));
		} else {
			a = Actions
					.sequence(Actions.delay(Constants.ACTION_TIME), Actions
							.parallel(Actions.moveTo(pos.x, pos.y,
									Constants.ACTION_TIME), Actions.scaleTo(
									Constants.GAMEELEMENT_SCALING,
									Constants.GAMEELEMENT_SCALING,
									Constants.ACTION_TIME)), Actions
							.run(new DestroyElement(x)));
		}
		evaluationController.runAnimation(x, a);
	}

	public static void scaleAnimation(GameElement x, boolean nextStep) {
		Action a;
		if (nextStep) {
			a = Actions.sequence(Actions.delay(Constants.ACTION_TIME), Actions
					.scaleTo(Constants.GAMEELEMENT_SCALING,
							Constants.GAMEELEMENT_SCALING,
							Constants.ACTION_TIME), Actions
					.run(new NextStep(x)));
		} else {
			a = Actions.sequence(Actions.delay(Constants.ACTION_TIME), Actions
					.scaleTo(Constants.GAMEELEMENT_SCALING,
							Constants.GAMEELEMENT_SCALING,
							Constants.ACTION_TIME), Actions
					.run(new DestroyElement(x)));
		}
		evaluationController.runAnimation(x, a);
	}

	public static void moveAnimation(Vector2 pos, GameElement x,
			boolean nextStep) {
		Action a;

		if (nextStep) {
			a = Actions.sequence(
					Actions.moveTo(pos.x, pos.y, Constants.ACTION_TIME),
					Actions.run(new NextStepWithoutRemove()));

		} else {
			a = Actions.moveTo(pos.x, pos.y, Constants.ACTION_TIME);
		}
		evaluationController.runAnimation(x, a);
	}

	public static void runNextStep() {
		runNextEvaluationStep();
	}

	public static void delayAndRunNextStepAnim(GameElement g) {

		Action a = Actions.sequence(Actions.delay(Constants.ACTION_TIME),
				Actions.run(new NextStepWithoutRemove()));
		evaluationController.runAnimation(g, a);
	}

	private static void step1AlphaConversion() {
		actStep = 1;
		evalutionPointer.getnext().alphaConversion();

		Vertex readIn = evalutionPointer.getnext().getReadIn();
		if (readIn != null) {
			readIn.readInAnimation(evalutionPointer.getnext().getGameElement()
					.getPosition());
			// Next Step by Animation
		} else {
			runNextEvaluationStep();
		}

	}

	private static void step2BetaReduction() {
		actStep = 2;
		vertexList = evalutionPointer.getnext().betaReduction();
		// Next Step by Animation
	}

	private static void step3UpdatePositions() {
		actStep = 3;
		Vector2 start = evaluationController.getEvaluationscreenPadding();
		start.x += offsetX;
		evalutionPointer.getnext()
				.reorganizePositions(start, new Vector2(0, 0));
		// Next Step by Animation
	}

	private static void step4InsertReadIn() {
		actStep = 4;
		// only Abstraction returns a list with elements else it is empty
		for (Vertex v : vertexList) {
			evaluationController.setOnStage(v.getGameElement());
		}

		// At the end worker disappears when its an Abstraction or Application
		evalutionPointer.getnext().deleteAfterBetaReduction();
	}

	private static void step5UpdateFamilyPositions() {
		actStep = 5;
		evalutionPointer.getnext().updatePositionsAfterBetaReduction();
	}

	private synchronized static void prepareNextEvaluation() {
		actStep = 0;

		// creating copy of current tree
		LevelTree oldTree = new LevelTree(evalutionPointer.cloneMe());
		evalutionPointer.setfamily(evalutionPointer.getnext());
		Vertex result = evalutionPointer.getnext().getEvaluationResult();

		if (result != null) {
			// check whether there is an infinite evaluation (same tree after
			// evaluation as before)
			if (result.getnext() != null
					&& !result.getType().equals(
							Constants.RetroStrings.VARIABLE_TYPE)) {
				LevelTree newTree = new LevelTree(result.getnext());
				if (oldTree.treeEquals(newTree)) {
					// we can stop evaluation at this point
					resultTree = oldTree;
					checkEvaluation();
					return;
				}
			}
			// make resultTree if needed
			offsetX += result.getWidth() * Constants.GAMEELEMENT_WIDTH;
			if (resultTree != null) {
				resultPointer.getnext().setnext(result);
				resultPointer.setnext(resultPointer.getnext().getnext());
			} else {
				resultTree = new LevelTree(result);
				resultPointer.setnext(resultTree.getStart());
			}
		}

		evalutionPointer.setnext(evalutionPointer.getnext()
				.updatePointerAfterBetaReduction());

		if (evalutionPointer.getnext() == null) {
			// End of Evaluation
			resultPointer.getnext().setnext(null);
			checkEvaluation();
		} else {
			if (evalutionPointer.getnext().getType()
					.equals(Constants.RetroStrings.VARIABLE_TYPE)) {
				runNextEvaluationStep();
				return;
			}
			// Wenn alle steps starten geklickt starte nächsten schritt sonst
			// wart auf eingabe
			if (autoStep) {
				runNextEvaluationStep();
			} else {
				nextStep = false;
			}
		}
	}

	/**
	 * This method is called after the last evaluation step , evaluation result
	 * is saved in resultTree
	 */
	private static void checkEvaluation() {
		result = resultTree.treeEquals(evaluationController.getLevel()
				.getLambdaUtil().getTargetTree());
		if (result) {
			evaluationController.getGameController().evaluationComplete();
		} else {
			evaluationController.getGameController().evaluationInComplete();
		}
	}

	public static void autoStepClicked() {
		if (!autoStep && !nextStep) {
			autoStep = true;
			runNextEvaluationStep();
		}

	}

	public static void nextStepClicked() {
		if (!nextStep) {
			nextStep = true;
			runNextEvaluationStep();
		}

	}

	private static void runNextEvaluationStep() {

		switch (actStep) {
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
}
