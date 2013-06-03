package net.mrblockplacer.WarsOfThePlants.entity.mob;

import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.BulletProjectile;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.Projectile;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	// public List<Projectile> projectiles = new ArrayList<Projectile>();

	public void move(int xa, int ya) {
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if (!collision(0, ya)) {
			y += ya;
		}

		if (!collision(xa, 0)) {
			x += xa;
		}
	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new BulletProjectile(x, y, dir);
		// projectiles.add(p);
		level.addProjectiles(p);
	}

	public void update() {

	}

	public void render() {
	}

	protected boolean collision(int xa, int ya) {
		boolean test = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid())
				test = true;
		}
		return test;
	}
}
