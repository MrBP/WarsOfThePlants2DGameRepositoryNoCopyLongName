package net.mrblockplacer.WarsOfThePlants.level.tile;

import net.mrblockplacer.WarsOfThePlants.level.tile.type.TileTypeFloor;
import net.mrblockplacer.WarsOfThePlants.level.tile.type.TileTypeGrass;
import net.mrblockplacer.WarsOfThePlants.level.tile.type.TileTypeHedge;
import net.mrblockplacer.WarsOfThePlants.level.tile.type.TileTypeWall;
import net.mrblockplacer.WarsOfThePlants.level.tile.type.TileTypeWater;
import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;

//	public static Tile grass = new GrassTile(Sprite.grass);
//	public static Tile flower = new FlowerTile(Sprite.flower);
//	public static Tile rock = new RockTile(Sprite.spawn_rock);
	public static Tile spawn_void = new TileTypeVoid(Sprite.spawn_void);
	// grass
	public static Tile spawn_grass1 = new TileTypeGrass(Sprite.spawn_grass1);
	public static Tile spawn_grass2 = new TileTypeGrass(Sprite.spawn_grass2);
	public static Tile spawn_grass3 = new TileTypeGrass(Sprite.spawn_grass3);
	public static Tile spawn_grass4 = new TileTypeGrass(Sprite.spawn_grass4);
	public static Tile spawn_grass5 = new TileTypeGrass(Sprite.spawn_grass5);
	public static Tile spawn_grass6 = new TileTypeGrass(Sprite.spawn_grass6);
	// floor
	public static Tile spawn_floor1 = new TileTypeFloor(Sprite.spawn_floor1);
	public static Tile spawn_floor2 = new TileTypeFloor(Sprite.spawn_floor2);
	public static Tile spawn_floor3 = new TileTypeFloor(Sprite.spawn_floor3);
	public static Tile spawn_floor4 = new TileTypeFloor(Sprite.spawn_floor4);
	public static Tile spawn_floor5 = new TileTypeFloor(Sprite.spawn_floor5);
	public static Tile spawn_floor6 = new TileTypeFloor(Sprite.spawn_floor6);
	// wall
	public static Tile spawn_wall1 = new TileTypeWall(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new TileTypeWall(Sprite.spawn_wall2);
	public static Tile spawn_wall3 = new TileTypeWall(Sprite.spawn_wall3);
	public static Tile spawn_wall4 = new TileTypeWall(Sprite.spawn_wall4);
	public static Tile spawn_wall5 = new TileTypeWall(Sprite.spawn_wall5);
	public static Tile spawn_wall6 = new TileTypeWall(Sprite.spawn_wall6);
	public static Tile spawn_wall7 = new TileTypeWall(Sprite.spawn_wall7);
	// hedge
	public static Tile spawn_hedge1 = new TileTypeHedge(Sprite.spawn_hedge1);
	public static Tile spawn_hedge2 = new TileTypeHedge(Sprite.spawn_hedge2);
	// water
	public static Tile spawn_water1 = new TileTypeWater(Sprite.spawn_water1);
	public static Tile spawn_water2 = new TileTypeWater(Sprite.spawn_water2);
	public static Tile spawn_water3 = new TileTypeWater(Sprite.spawn_water3);
	public static Tile spawn_water4 = new TileTypeWater(Sprite.spawn_water4);
	// misc
	public static Tile spawn_teleporter1 = new TileTypeWater(Sprite.spawn_teleporter1);
	public static Tile spawn_teleporter2 = new TileTypeWater(Sprite.spawn_teleporter2);

	// grass
	public static final int col_spawn_grass1 = 0xff404040;
	public static final int col_spawn_grass2 = 0xff404050;
	public static final int col_spawn_grass3 = 0xff404060;
	public static final int col_spawn_grass4 = 0xff404070;
	public static final int col_spawn_grass5 = 0xff404080;
	public static final int col_spawn_grass6 = 0xff404090;
	// floor
	public static final int col_spawn_floor1 = 0xff4CFF00;
	public static final int col_spawn_floor2 = 0xff4C9F00;
	public static final int col_spawn_floor3 = 0xff4C8F00;
	public static final int col_spawn_floor4 = 0xff4C7F00;
	public static final int col_spawn_floor5 = 0xff4C6F00;
	public static final int col_spawn_floor6 = 0xff4C5F00;
	// wall
	public static final int col_spawn_wall1 = 0xffFFD800;// 808080;
	public static final int col_spawn_wall2 = 0xffFFC800;// 808080;
	public static final int col_spawn_wall3 = 0xffFFB800;// 808080;
	public static final int col_spawn_wall4 = 0xffFFA800;// 808080;
	public static final int col_spawn_wall5 = 0xffFF9800;// 808080;
	public static final int col_spawn_wall6 = 0xffFF8800;// 808080;
	public static final int col_spawn_wall7 = 0xffFF7800;// 808080;
	//hedge
	public static final int col_spawn_hedge1 = 0xffFF0000; // unused
	public static final int col_spawn_hedge2 = 0xffEF0000; // unused
	//water
	public static final int col_spawn_water1 = 0xffFF6A00; // unused
	public static final int col_spawn_water2 = 0xffFF5A00; // unused
	public static final int col_spawn_water3 = 0xffFF4A00; // unused
	public static final int col_spawn_water4 = 0xffFF3A00; // unused
	//misc
	public static final int col_spawn_teleporter1 = 0xff0026FF; // unused
	public static final int col_spawn_teleporter2 = 0xff00269F; // unused
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
	}

	public boolean solid() {
		return false;
	}
}
