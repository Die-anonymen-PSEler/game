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
 * 
 */
public class AboutMenuScreen extends MenuScreen {

	private final static float FONTSIZE2_5 = 2.0f;
	private final static float CENTERTITLE = (1f / 4f);

	private float textWidth;
	/**
	 * The developers of RetroMachine. The information about the lambda.
	 */
	public static final String CREDIT = "by RetroMachines: \n \n"
			+ " Luca Becker\n Henrike Hardt\n Larissa Schmid\n Adrian Schulte\n Maik Wiesner";

	public static final String INFO = "Der Lambda-Kalkuel ist ein Element der Informatik. Es ist eine formale "
			+ "Sprache, die im Allgemeinen dazu dient, Funktionen zu definieren beziehungsweise zu beschreiben.";

	public static final String GAMEINFO_1 = "Metallobjekt: \n"
			+ "Entspricht der Variable im Lambda-Kalkül, kann von der Maschine vearbeitet werden";

	public static final String GAMEINFO_2 = "Maschine: \n"
			+ "Entspricht der Abstraktion im Lambda-Kalkül, kann andere Objekte verarbeiten";

	public static final String GAMEINFO_3 = "Ampel: \n"
			+ "Entspricht der Applikation im Lambda-Kalkül, dient zum zusammenfassen (klammern) mehrerer Objekte,"
			+ " soadass sie als ein Objekt verarbeitet werden künnen";

	public static final String GAMEINFO_4 = "Depot: \n"
			+ "Hierin künnen Objekte Platziert werden. die anschlieüend ausgewertet werden sollen. (Dargestellt durch schwarz/gelben Kasten)";

	public static final String GAMEINFO_5 = "Verarbeitungsbereich: \n"
			+ "Der Verarbeitungsbereich eines Objektes sind alle Depots"
			+ " überhalb seines Depots, die durch die nach oben führende Rohre erreicht werden können.";

	public static final String GAMEINFO_6 = "Eingabe / EingabeBereich: \n"
			+ "Der Eingabebereich eines Objektes,"
			+ " ist der Inhalt des Depots rechts davon (verbunden durch eine Rohre) sowie dessen kompleter Verarbeitungsbereich. ";

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
		title1.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title1.setAlignment(Align.center);

		// Make Text
		Label gameInfoText1 = new Label(GAMEINFO_1, skin);
		gameInfoText1.setWrap(true);
		gameInfoText1.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText1.setAlignment(Align.center);

		// Make Text
		Label gameInfoText2 = new Label(GAMEINFO_2, skin);
		gameInfoText2.setWrap(true);
		gameInfoText2.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText2.setAlignment(Align.center);

		// Make Text
		Label gameInfoText3 = new Label(GAMEINFO_3, skin);
		gameInfoText3.setWrap(true);
		gameInfoText3.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText3.setAlignment(Align.center);

		// Make Text
		Label gameInfoText4 = new Label(GAMEINFO_4, skin);
		gameInfoText4.setWrap(true);
		gameInfoText4.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText4.setAlignment(Align.center);

		// Make Text
		Label gameInfoText5 = new Label(GAMEINFO_5, skin);
		gameInfoText5.setWrap(true);
		gameInfoText5.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText5.setAlignment(Align.center);

		// Make Text
		Label gameInfoText6 = new Label(GAMEINFO_6, skin);
		gameInfoText6.setWrap(true);
		gameInfoText6.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		gameInfoText6.setAlignment(Align.center);

		// Make Text
		Label infoText = new Label(INFO, skin);
		infoText.setWrap(true);
		infoText.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		infoText.setAlignment(Align.center);

		// Make Text
		Label aboutText = new Label(CREDIT, skin);
		aboutText.setWrap(true);
		aboutText
				.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
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
		aboutScroll.getStyle().hScrollKnob
				.setMinWidth((DEFAULTBUTTONSIZE * screenWidth)
						/ DIVIDEWIDTHDEFAULT);

		// Make Buttons
		Button buttonReturn = new Button(skin, ButtonStrings.BACK);
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDINGx2)
				.padLeft(screenWidth / DEFAULTPADDINGx4).left();
		table.add(title1).width(screenWidth * HALF).right()
				.padRight((screenWidth * HALF) - (screenWidth * CENTERTITLE))
				.expandX().row();
		table.add(aboutScroll).expandY().colspan(2)
				.padTop(screenHeight / DEFAULTPADDINGx4)
				.padBottom(screenHeight / DEFAULTPADDINGx4).row();

		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
	}
}
