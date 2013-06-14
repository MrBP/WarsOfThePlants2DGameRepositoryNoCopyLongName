package net.mrblockplacer.WarsOfThePlants.entity.mob;

import java.util.Random;

import net.mrblockplacer.WarsOfThePlants.entity.projectile.Projectile;
import net.mrblockplacer.WarsOfThePlants.graphics.Screen;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;

public class Jabba extends Mob {
	private int anim = 0;
	private boolean walking = false;
	private int mustWaitTime = 150;
	private int waitTime = mustWaitTime;
	private int curRand = 1;

	public Jabba() {
		sprite = Sprite.player_foward;
		// Level.entities.add(this);

	}

	public Jabba(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.player_foward;
		dir = 2;
		// Level.mobs.add(this);

	}

	Random rand = new Random();

	public void update() {
		int rnd;
		if (waitTime >= mustWaitTime || isIntersection()) {
			rnd = rand.nextInt(4) + 1;
			waitTime = 0;
		} else {
			rnd = curRand;
			waitTime++;
		}
		// System.out.println(rnd);
		int xa = 0, ya = 0;
		if (anim < 7500)
			anim++;
		else
			anim = 0;
		if (rnd == 1)
			ya--;
		if (rnd == 2)
			ya++;
		if (rnd == 3)
			xa--;
		if (rnd == 4)
			xa++;
		// }
		if ((xa != 0 || ya != 0) && !collision(xa, ya)) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
			waitTime = mustWaitTime;
		}
		clear();
		updateShooting();
		curRand = rnd;
	}

	private boolean isIntersection() {
		int dir1 = this.x - 16;
		int dir2 = this.x + 16;
		int dir3 = this.y - 16;
		int dir4 = this.y + 16;
		System.out.println(dir1);
		boolean t1 = level.getTile(dir1, this.y).solid();
		boolean t2 = level.getTile(dir2, this.y).solid();
		boolean t3 = level.getTile(this.x, dir3).solid();
		boolean t4 = level.getTile(this.x, dir4).solid();
		int i = 0;
		if (!t1)
			i++;
		if (!t2)
			i++;
		if (!t3)
			i++;
		if (!t4)
			i++;
		if (i > 3)
			return false;
		else
			return false;
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) {
				level.getProjectiles().remove(i);
			}
		}
	}

	private void updateShooting() {
		// if(fireCounter > BulletProjectile.)
		// if (Mouse.getB() == 1 && fireCounter <= 0) {
		// double dx = (Mouse.getX() - (Game.width * Game.scale) / 2);
		// double dy = (Mouse.getY() - (Game.height * Game.scale) / 2);
		// double dir2 = Math.atan2(dy, dx);
		// shoot(x, y, dir2);
		// fireCounter = BulletProjectile.rateOfFire;
		// } else {
		// fireCounter--;
		// }
	}

	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_foward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_foward_1;
				} else {
					sprite = Sprite.player_foward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}

		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			}

		}
		if (dir == 3) {
			flip = 1;
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
		}

		screen.renderPlayer(x - 16, y - 16, sprite, flip);

	}

}
