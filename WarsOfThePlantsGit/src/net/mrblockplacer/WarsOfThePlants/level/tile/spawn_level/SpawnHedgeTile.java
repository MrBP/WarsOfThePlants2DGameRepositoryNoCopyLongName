package net.mrblockplacer.WarsOfThePlants.level.tile.spawn_level;

import net.mrblockplacer.WarsOfThePlants.graphics.Screen;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;
import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;

public class SpawnHedgeTile extends Tile {

	public SpawnHedgeTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}
	
	public boolean breakable()
	{
		return true;
	}
}
