package net.mrblockplacer.WarsOfThePlants.effects;

import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class Particle extends Entity {

	// private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;

	private int life;
	private int oldlife = 0;
	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_n;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = 20.0;
	}

	// public Particle(int x, int y, int life, int amount) {
	// this(x, y, life);
	// for (int i = 0; i < amount - 1; i++) {
	// particles.add(new Particle(x, y, life));
	// }
	// particles.add(this);
	// }
	//
	
	public void update() {
		oldlife++;
		if (oldlife >= life) {
			remove();
		}

		za -= 0.1;

		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.4;
			ya *= 0.4;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
}
