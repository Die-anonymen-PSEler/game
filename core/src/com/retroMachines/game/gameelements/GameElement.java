package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.retroMachines.util.Constants;

/**
 * This class is part of the model of RetroMachines. It is abstract and it's
 * children describe the Elements that can be moved within the game. They have
 * to implement a render method so they can be displayed to the user.
 * 
 * @author RetroFactory
 */
public abstract class GameElement extends Actor {

	/**
	 * A texture where the image of the GameElement can be stored.
	 */
	protected TextureRegion textureRegion;

	/**
	 * the id of the tileset that represents this element
	 */
	protected int tileId;

	public GameElement() {
		setX(0f);
		setY(0f);
		setBounds(getX(), getY(), Constants.GAMEELEMENT_WIDTH,
				Constants.GAMEELEMENT_WIDTH);
	}

	/**
	 * getter for TileSet belonging to GameElement
	 * 
	 * @return TiledMapTileSet belonging to GameElement
	 */
	public abstract TiledMapTileSet getTileSet();

	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	public void setTextureRegion(TextureRegion region) {
		this.textureRegion = region;
	}

	public void setTileId(int i) {
		this.tileId = i;
		textureRegion = getTileSet().getTile(i).getTextureRegion();
	}

	public int getTileId() {
		return tileId;
	}

	/**
	 * Set Position (evaluation Screen)
	 * 
	 * @param pos
	 */
	public void setPosition(Vector2 pos) {
		setX(pos.x);
		setY(pos.y);
	}

	/**
	 * get Position (evaluation Screen)
	 * 
	 * @return position of Gameelement
	 */
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	@Override
	public void draw(Batch batch, float alpha) {

		batch.draw(textureRegion, getX(), getY(), this.getOriginX(),
				this.getOriginY(), this.getWidth(), this.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation());
	}

}
