package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The AboutMenuScreen is part of the view of RetroMachines. In it the
 * informations about RetroFactory and the authors of this game are given.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class AboutMenuScreen extends MenuScreen {

	private final static float FONTSIZE_TWO_FIVE = 2.0f;
	private final static float CENTERTITLE = (1f / 4f);

	/**
	 * The developers of RetroMachine. The information about the lambda.
	 */
	public static final String CREDIT = String.format("by RetroMachines: \n \n"
			+ " Luca Becker\n Henrike Hardt\n Larissa Schmid\n Adrian Schulte\n Maik Wiesner");

	/**
	 * The information about the lambda-calculus.
	 */
	public static final String INFO = "Der Lambda-Kalkuel ist ein Element der Informatik. Es ist eine formale "
			+ "Sprache, die im Allgemeinen dazu dient, Funktionen zu definieren beziehungsweise zu beschreiben.";

	/**
	 * Information about the role of the metal objects.
	 */
	public static final String GAMEINFO_ONE = "Metallobjekt: \n"
			+ "Entspricht der Variable im Lambda-Kalkuel, kann von der Maschine vearbeitet werden";

	/**
	 * Information about the role of the machines.
	 */
	public static final String GAMEINFO_TWO = "Maschine: \n"
			+ "Entspricht der Abstraktion im Lambda-Kalkuel, kann andere Objekte verarbeiten";

	/**
	 * Information about the role of the lights.
	 */
	public static final String GAMEINFO_THREE = "Ampel: \n"
			+ "Entspricht der Applikation im Lambda-Kalkuel, dient zum zusammenfassen (klammern) mehrerer Objekte,"
			+ " so dass sie als ein Objekt verarbeitet werden koennen";

	/**
	 * Information about the role of the depots.
	 */
	public static final String GAMEINFO_FOUR = "Depot: \n"
			+ "Hierin koennen Objekte Platziert werden. die anschliessend ausgewertet werden sollen. (Dargestellt durch schwarz/gelben Kasten)";

	/**
	 * Information about the processing area of an object.
	 */
	public static final String GAMEINFO_FIVE = "Verarbeitungsbereich: \n"
			+ "Der Verarbeitungsbereich eines Objektes sind alle Depots"
			+ " ueberhalb seines Depots, die durch die nach oben fuehrende Rohre erreicht werden koennen.";

	/**
	 * Information about the input and the input area of an object.
	 */
	public static final String GAMEINFO_SIX = String.format("Eingabe / EingabeBereich: \n"
			+ "Der Eingabebereich eines Objektes,"
			+ " ist der Inhalt des Depots rechts davon (verbunden durch eine Rohre) sowie dessen kompleter Verarbeitungsbereich. ");

	private float textWidth;

	/**
	 * The constructor to create a new instance of the AboutMenuScreen.
	 * 
	 * @param game
	 *            The game which uses the screen.
	 */
	public AboutMenuScreen(RetroMachines game) {
		super(game);
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		skin = RetroAssetManager.getMenuSkin();
		textWidth = screenWidth - (2 * (screenWidth / DEFAULTPADDING));

		// Make Title
		Label title1 = new Label("Information", skin);
		title1.setWrap(true);
		title1.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		title1.setAlignment(Align.center);

		// Make Text
		Label gameInfoText1 = new Label(GAMEINFO_ONE, skin);
		gameInfoText1.setWrap(true);
		gameInfoText1.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText1.setAlignment(Align.center);

		// Make Text
		Label gameInfoText2 = new Label(GAMEINFO_TWO, skin);
		gameInfoText2.setWrap(true);
		gameInfoText2.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText2.setAlignment(Align.center);

		// Make Text
		Label gameInfoText3 = new Label(GAMEINFO_THREE, skin);
		gameInfoText3.setWrap(true);
		gameInfoText3.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText3.setAlignment(Align.center);

		// Make Text
		Label gameInfoText4 = new Label(GAMEINFO_FOUR, skin);
		gameInfoText4.setWrap(true);
		gameInfoText4.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText4.setAlignment(Align.center);

		// Make Text
		Label gameInfoText5 = new Label(GAMEINFO_FIVE, skin);
		gameInfoText5.setWrap(true);
		gameInfoText5.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText5.setAlignment(Align.center);

		// Make Text
		Label gameInfoText6 = new Label(GAMEINFO_SIX, skin);
		gameInfoText6.setWrap(true);
		gameInfoText6.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText6.setAlignment(Align.center);

		// Make Text
		Label infoText = new Label(INFO, skin);
		infoText.setWrap(true);
		infoText.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		infoText.setAlignment(Align.center);

		// Make Text
		Label aboutText = new Label(CREDIT, skin);
		aboutText.setWrap(true);
		aboutText.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		aboutText.setAlignment(Align.center);

		// Make ScrollPane
		Table scrollTable = new Table(skin);
		scrollTable.add(infoText).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(gameInfoText1).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(gameInfoText2).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(gameInfoText3).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(gameInfoText4).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(gameInfoText5).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(gameInfoText6).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(aboutText).width(textWidth)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		ScrollPane aboutScroll = new ScrollPane(scrollTable, skin);
		aboutScroll.setScrollingDisabled(true, false);
		aboutScroll.getStyle().hScrollKnob
				.setMinWidth((DEFAULTBUTTONSIZE * screenWidth)
						/ DIVIDEWIDTHDEFAULT);

		// Make Buttons
		Button buttonReturn = new Button(skin, ButtonStrings.BACK);
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR).left();
		table.add(title1).width(screenWidth * HALF).right()
				.padRight((screenWidth * HALF) - (screenWidth * CENTERTITLE))
				.expandX().row();
		table.add(aboutScroll).expandY().colspan(2)
				.padTop(screenHeight / DEFAULTPADDING_X_FOUR)
				.padBottom(screenHeight / DEFAULTPADDING_X_FOUR).row();

		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
	}
}
