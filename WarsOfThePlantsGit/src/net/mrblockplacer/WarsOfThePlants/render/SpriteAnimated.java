package net.mrblockplacer.WarsOfThePlants.render;

public class SpriteAnimated extends Sprite {

	private int frame = 0;;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int length = -1;

	public SpriteAnimated(SpriteSheets sheet, int width, int height, int length) {
		super(width, height, sheet);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length)
			System.err.println("ERROR! Length Is Too Long!");
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= length - 1) {
				frame = 0;
			} else {
				frame++;
			}

			sprite = sheet.getSprites()[frame];
		}
		System.out.println(sprite + " :: " + frame);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}
}
