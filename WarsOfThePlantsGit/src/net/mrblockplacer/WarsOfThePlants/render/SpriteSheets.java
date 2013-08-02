package net.mrblockplacer.WarsOfThePlants.render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheets {

	private String path;
	public final int SIZE;
	public int[] pixels;

	public static SpriteSheets tiles = new SpriteSheets("/textures/sheets/spritesheet.png", 256);
	public static SpriteSheets spawn_level = new SpriteSheets("/textures/sheets/spawn_level.png", 48);

	public SpriteSheets(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheets.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
