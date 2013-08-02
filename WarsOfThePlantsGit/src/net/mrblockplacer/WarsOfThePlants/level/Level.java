package net.mrblockplacer.WarsOfThePlants.level;

import java.util.ArrayList;
import java.util.List;

import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.entity.mob.Mob;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.Projectile;
import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;
import net.mrblockplacer.WarsOfThePlants.render.Screen;

public class Level {

	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;
	// public static Level spawn = new SpawnLevel("/textures/sheets/map_1.png");
	public static Level spawn2 = new SpawnLevel("/levels/maze.png");
	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	public static List<Entity> entities = new ArrayList<Entity>();
	public static List<Mob> mobs = new ArrayList<Mob>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
	}

	public void add(Entity e) {
		entities.add(e);
	}

	public void addProjectiles(Projectile e) {
		getProjectiles().add(e);
	}

	protected void loadLevel(String path) {
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < getProjectiles().size(); i++) {
			getProjectiles().get(i).update();
		}
	}

	// private void time() {
	// }

	public void render(int xScroll, int yScroll, Screen screen) {

		screen.setOffset(xScroll, yScroll);

		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
				// 256 = 16 * 16
				// if (x + y * 16 < 0 || x + y * 16 >= 256) {
				// Tile.voidTile.render(x, y, screen);
				// // continue;
				// } else {
				// tiles[x + y * 16].render(x, y, screen);
				// }
			}
		}

		// for (int i = 0; i < entities.size(); i++) {
		// entities.get(i).render(screen);
		// }
		for (int i = 0; i < getProjectiles().size(); i++) {
			getProjectiles().get(i).render(screen);
		}
	}

	// Grass = 0xFF00FF00;
	// Flower = 0xFFFFFF00
	// Rock = 0xFF7F7F00
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		// grass
		if (tiles[x + y * width] == Tile.col_spawn_grass1)
			return Tile.spawn_grass1;
		if (tiles[x + y * width] == Tile.col_spawn_grass2)
			return Tile.spawn_grass2;
		if (tiles[x + y * width] == Tile.col_spawn_grass3)
			return Tile.spawn_grass3;
		if (tiles[x + y * width] == Tile.col_spawn_grass4)
			return Tile.spawn_grass4;
		if (tiles[x + y * width] == Tile.col_spawn_grass5)
			return Tile.spawn_grass5;
		if (tiles[x + y * width] == Tile.col_spawn_grass6)
			return Tile.spawn_grass6;
		// floor
		if (tiles[x + y * width] == Tile.col_spawn_floor1)
			return Tile.spawn_floor1;
		if (tiles[x + y * width] == Tile.col_spawn_floor2)
			return Tile.spawn_floor2;
		if (tiles[x + y * width] == Tile.col_spawn_floor3)
			return Tile.spawn_floor3;
		if (tiles[x + y * width] == Tile.col_spawn_floor4)
			return Tile.spawn_floor4;
		if (tiles[x + y * width] == Tile.col_spawn_floor5)
			return Tile.spawn_floor5;
		if (tiles[x + y * width] == Tile.col_spawn_floor6)
			return Tile.spawn_floor6;

		// wall
		if (tiles[x + y * width] == Tile.col_spawn_wall1)
			return Tile.spawn_wall1;
		if (tiles[x + y * width] == Tile.col_spawn_wall2)
			return Tile.spawn_wall2;
		if (tiles[x + y * width] == Tile.col_spawn_wall3)
			return Tile.spawn_wall3;
		if (tiles[x + y * width] == Tile.col_spawn_wall4)
			return Tile.spawn_wall4;
		if (tiles[x + y * width] == Tile.col_spawn_wall5)
			return Tile.spawn_wall5;
		if (tiles[x + y * width] == Tile.col_spawn_wall6)
			return Tile.spawn_wall6;
		if (tiles[x + y * width] == Tile.col_spawn_wall7)
			return Tile.spawn_wall7;
		// hedge
		if (tiles[x + y * width] == Tile.col_spawn_hedge1)
			return Tile.spawn_hedge1;
		if (tiles[x + y * width] == Tile.col_spawn_hedge2)
			return Tile.spawn_hedge2;
		// water

		if (tiles[x + y * width] == Tile.col_spawn_water1)
			return Tile.spawn_water1;
		if (tiles[x + y * width] == Tile.col_spawn_water2)
			return Tile.spawn_water2;
		if (tiles[x + y * width] == Tile.col_spawn_water3)
			return Tile.spawn_water3;
		if (tiles[x + y * width] == Tile.col_spawn_water4)
			return Tile.spawn_water4;
		// misc
		if (tiles[x + y * width] == Tile.col_spawn_teleporter1)
			return Tile.spawn_teleporter1;
		if (tiles[x + y * width] == Tile.col_spawn_teleporter2)
			return Tile.spawn_teleporter2;

		return Tile.spawn_grass1;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public boolean tileCollision(double x, double y, double nx, double ny, int size) {
		boolean test = false;
		for (int c = 0; c < 4; c++) {
			int xt = (((int) x + (int) nx) + c % 2 * size - 4) / 16;
			int yt = (((int) y + (int) ny) + c / 2 * size) / 16;
			if (getTile(xt, yt).solid())
				test = true;
		}
		return test;
	}

}
