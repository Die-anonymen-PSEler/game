package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.AssetManager;

public class DepotElement extends GameElement{

	@Override
	public TiledMapTileSet getTileSet() {
	    return AssetManager.getDepots();
	}

}
