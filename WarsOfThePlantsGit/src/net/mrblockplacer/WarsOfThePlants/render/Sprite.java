package net.mrblockplacer.WarsOfThePlants.render;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheets sheet;

	// Sprite void
	public static Sprite spawn_void = new Sprite(16, 0x000000);
	// public static Sprite spawn_void = new Sprite(16, 0x1B87E0);
	// Sprite color green no blue for example
	// public static Sprite spawn_void = new Sprite(16, 0x12b32d);

	// Sprite for rock, flower, grass, etc
	// public static Sprite grass = new Sprite(16, 0, 0, SpriteSheets.tiles);
	// public static Sprite flower = new Sprite(16, 1, 0, SpriteSheets.tiles);
	// public static Sprite spawn_rock = new Sprite(16, 2, 0,
	// SpriteSheets.tiles);

	public static Sprite bullet1 = new Sprite(16, 3, 0, SpriteSheets.tiles);
	// grass
	public static Sprite spawn_grass1 = new Sprite(16, 0, 1, SpriteSheets.tiles);
	public static Sprite spawn_grass2 = new Sprite(16, 0, 2, SpriteSheets.tiles);
	public static Sprite spawn_grass3 = new Sprite(16, 0, 3, SpriteSheets.tiles);
	public static Sprite spawn_grass4 = new Sprite(16, 0, 4, SpriteSheets.tiles);
	public static Sprite spawn_grass5 = new Sprite(16, 0, 5, SpriteSheets.tiles);
	public static Sprite spawn_grass6 = new Sprite(16, 0, 6, SpriteSheets.tiles);
	// floor
	public static Sprite spawn_floor1 = new Sprite(16, 1, 1, SpriteSheets.tiles);
	public static Sprite spawn_floor2 = new Sprite(16, 1, 2, SpriteSheets.tiles);
	public static Sprite spawn_floor3 = new Sprite(16, 1, 3, SpriteSheets.tiles);
	public static Sprite spawn_floor4 = new Sprite(16, 1, 4, SpriteSheets.tiles);
	public static Sprite spawn_floor5 = new Sprite(16, 1, 5, SpriteSheets.tiles);
	public static Sprite spawn_floor6 = new Sprite(16, 1, 6, SpriteSheets.tiles);
	// wall
	public static Sprite spawn_wall1 = new Sprite(16, 2, 1, SpriteSheets.tiles);
	public static Sprite spawn_wall2 = new Sprite(16, 2, 2, SpriteSheets.tiles);
	public static Sprite spawn_wall3 = new Sprite(16, 2, 3, SpriteSheets.tiles);
	public static Sprite spawn_wall4 = new Sprite(16, 2, 4, SpriteSheets.tiles);
	public static Sprite spawn_wall5 = new Sprite(16, 2, 5, SpriteSheets.tiles);
	public static Sprite spawn_wall6 = new Sprite(16, 2, 6, SpriteSheets.tiles);
	public static Sprite spawn_wall7 = new Sprite(16, 2, 7, SpriteSheets.tiles);
	// hedge
	public static Sprite spawn_hedge1 = new Sprite(16, 3, 1, SpriteSheets.tiles);
	public static Sprite spawn_hedge2 = new Sprite(16, 3, 2, SpriteSheets.tiles);
	// water
	public static Sprite spawn_water1 = new Sprite(16, 5, 1, SpriteSheets.tiles);
	public static Sprite spawn_water2 = new Sprite(16, 5, 2, SpriteSheets.tiles);
	public static Sprite spawn_water3 = new Sprite(16, 5, 3, SpriteSheets.tiles);
	public static Sprite spawn_water4 = new Sprite(16, 5, 4, SpriteSheets.tiles);
	// misc
	public static Sprite spawn_teleporter1 = new Sprite(16, 4, 1, SpriteSheets.tiles);
	public static Sprite spawn_teleporter2 = new Sprite(16, 4, 2, SpriteSheets.tiles);

	// Sprite for player
	// x=0 y=5 because you select a square 32x32
	public static Sprite player_foward = new Sprite(32, 0, 5, SpriteSheets.tiles);
	public static Sprite player_foward_1 = new Sprite(32, 0, 6, SpriteSheets.tiles);
	public static Sprite player_foward_2 = new Sprite(32, 0, 7, SpriteSheets.tiles);

	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheets.tiles);
	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheets.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheets.tiles);

	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheets.tiles);
	public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheets.tiles);
	public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheets.tiles);

	// particles
	public static Sprite particle_n = new Sprite(3, 0xFF0000);

	protected Sprite(int width, int height, SpriteSheets sheet) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
//		load();

	}

	public Sprite(int size, int x, int y, SpriteSheets sheet) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		this.pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();

	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[this.width * this.height];
		setColor(color);
	}

	public Sprite(int size, int color) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		this.pixels = new int[this.SIZE * this.SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void setColor(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				int someone = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
				System.out.println(someone);
				pixels[x + y * width] = someone;
			}
		}
	}

}

/*
 * 
 * public static Sprite spawn_grass = new Sprite(16, 0, 0,
 * SpriteSheets.spawn_level); public static Sprite spawn_hedge = new Sprite(16,
 * 1, 0, SpriteSheets.spawn_level); public static Sprite spawn_water = new
 * Sprite(16, 2, 1, SpriteSheets.spawn_level); public static Sprite spawn_wall1
 * = new Sprite(16, 0, 1, SpriteSheets.spawn_level); public static Sprite
 * spawn_wall2 = new Sprite(16, 0, 2, SpriteSheets.spawn_level); public static
 * Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheets.spawn_level);
 */