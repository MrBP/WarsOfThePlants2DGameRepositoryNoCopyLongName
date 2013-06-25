package net.mrblockplacer.WarsOfThePlants.entity.mob;

import net.mrblockplacer.WarsOfThePlants.Game;
import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.BulletProjectile;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.Projectile;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;
import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;
import net.mrblockplacer.WarsOfThePlants.network.MainNetwork;
import net.mrblockplacer.WarsOfThePlants.sound.Sound;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected int waterSound = 0;
	public boolean hasPhaseSuit = false;
	protected int fireCounter = 0;
	public int health = 6;
	protected int speed = 1;
	int counter = 10;

	public void move(int xa, int ya, Mob mob) {
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if ((!collision(xa * mob.getSpeed(), ya * mob.getSpeed()) || hasPhaseSuit) && waterSound > 20) {
			if (water(xa * mob.getSpeed(), ya * mob.getSpeed())) {
				Sound.playSound(Sound.SOUND_WATER_1);
				waterSound = 0;
			}
		} else {
			waterSound++;
		}
		if (!collision(0, ya * mob.getSpeed()) || hasPhaseSuit) {
			y += ya * mob.getSpeed();
		}

		if (!collision(xa * mob.getSpeed(), 0) || hasPhaseSuit) {
			x += xa * mob.getSpeed();
		}
		if (mob instanceof Player) {
			if (((Player) mob).id == 1) {
				if (Game.network.socket != null && counter > 100) {
					Game.network.sendText(".modPlayer:" + mob.x + ":" + mob.y);
					counter = 0;
				} else {
					counter++;
				}

			}
		}
	}

	protected int getSpeed() {
		return 1;
	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new BulletProjectile(x, y, dir);
		level.addProjectiles(p);
		Sound.playSound(Sound.SOUND_BOUNCE);
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
			// if (this.x < )
			// for (Mob entity : Level.mobs) {
			// // System.out.println(entity);
			// if (entity != this) {
			// System.out.println(entity);
			//
			// if (this.x > entity.x - 1 && this.x < entity.x + 1) {
			// if (this.y > entity.y - 1 && this.y < entity.y + 1) {
			// test = true;
			// }
			// }
			// }
			// // if(this.x)
			//
			// }
			// System.out.println();
		}
		return test;
	}

	protected boolean water(int xa, int ya) {
		boolean test = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt) == Tile.spawn_water1)
				test = true;
			if (level.getTile(xt, yt) == Tile.spawn_water2)
				test = true;
			if (level.getTile(xt, yt) == Tile.spawn_water3)
				test = true;
			if (level.getTile(xt, yt) == Tile.spawn_water4)
				test = true;
		}
		return test;
	}
}
