package net.mrblockplacer.WarsOfThePlants.level.tile;

import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}


}
