package net.mrblockplacer.WarsOfThePlants.level.tile.spawn_level;

import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;
import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class TileTypeGrass extends Tile {

	public TileTypeGrass(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
