package com.retroMachines;

/**
 * @opt views
 * @hidden
 * @texignore
 */
class UMLOptions {

}

/**
 * @view
 * 
 * @match class .*
 * @opt !all
 * @opt nodefillcolor 1
 * @opt nodefontname Verdana
 * @opt edgefontname Verdana
 * @opt edgecolor #565656
 * @opt edgefontsize 10
 * 
 * @match class com.badlogic.gdx.*
 * @opt qualify
 * @opt postfixpackage
 * 
 * @match class com.retroMachines.*
 * @opt all
 * @opt nodefillcolor 2
 * 
 * @match class com.retroMachines.game.controller.*
 * @opt nodefillcolor 3
 * 
 * @match class com.retroMachines.game.gameelements.*
 * @opt nodefillcolor 4
 * 
 * @match class com.retroMachines.ui.screens.game.*
 * @opt nodefillcolor 5
 * 
 * @match class com.retroMachines.ui.screens.menus.*
 * @opt nodefillcolor 11
 * 
 * @match class com.retroMachines.util.*
 * @opt nodefillcolor 12
 * 
 * @match class com.retroMachines.data.*
 * @opt nodefillcolor #ee7600
 * 
 * @match class com.retroMachines.data.models.*
 * @opt nodefillcolor #458b74
 * 
 * @match class com.retroMachines\..*Exception
 * @opt nodefillcolor #b22222
 * @opt nodefontcolor white
 * 
 * @match class java.*
 * @opt hide
 * 
 * @match class com.badlogic.gdx.scenes.scene2d.utils.ClickListener
 * @opt hide
 * 
 * @match class com.retroMachines.ui.screens.menus.*.*ClickListener
 * @opt hide
 * 
 * @match class com.retroMachines.ui.screens.game.*.*ClickListener
 * @opt hide
 * 
 * @match class com.retroMachines.ui.screens.menus.*.*ButtonListener
 * @opt hide
 * 
 * @match class com.retroMachines.ui.screens.game.*.*ButtonListener
 * @opt hide
 * 
 * @texignore
 */
public class Uml {
}
