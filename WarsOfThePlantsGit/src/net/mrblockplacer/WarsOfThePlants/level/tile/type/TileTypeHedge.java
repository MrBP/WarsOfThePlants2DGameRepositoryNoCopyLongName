package net.mrblockplacer.WarsOfThePlants.level.tile.type;

import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;
import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class TileTypeHedge extends Tile {

	public TileTypeHedge(Sprite sprite) {
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
