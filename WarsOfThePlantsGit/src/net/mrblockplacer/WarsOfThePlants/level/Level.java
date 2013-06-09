package net.mrblockplacer.WarsOfThePlants.level;

import java.util.ArrayList;
import java.util.List;

import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.Projectile;
import net.mrblockplacer.WarsOfThePlants.graphics.Screen;
import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;

public class Level {

	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/levels/spawn3.png");
	public static Level spawn2 = new SpawnLevel("/levels/spawn.png");

	private List<Entity> entities = new ArrayList<Entity>();
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

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
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
		if (tiles[x + y * width] == Tile.col_spawn_floor)
			return Tile.spawn_floor;
		if (tiles[x + y * width] == Tile.col_spawn_grass)
			return Tile.spawn_grass;
		if (tiles[x + y * width] == Tile.col_spawn_hedge)
			return Tile.spawn_hedge;
		if (tiles[x + y * width] == Tile.col_spawn_wall1)
			return Tile.spawn_wall1;
		if (tiles[x + y * width] == Tile.col_spawn_wall2)
			return Tile.spawn_wall2;
		if (tiles[x + y * width] == Tile.col_spawn_water)
			return Tile.spawn_water;

		return Tile.spawn_water;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

}
