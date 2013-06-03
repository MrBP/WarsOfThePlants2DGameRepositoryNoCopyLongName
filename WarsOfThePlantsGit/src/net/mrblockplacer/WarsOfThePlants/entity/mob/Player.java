package net.mrblockplacer.WarsOfThePlants.entity.mob;

import net.mrblockplacer.WarsOfThePlants.Game;
import net.mrblockplacer.WarsOfThePlants.entity.projectile.Projectile;
import net.mrblockplacer.WarsOfThePlants.graphics.Screen;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;
import net.mrblockplacer.WarsOfThePlants.input.Keyboard;
import net.mrblockplacer.WarsOfThePlants.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_foward;
//		sprite = Sprite.player_back;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_foward;
//		sprite = Sprite.player_back;
		dir = 2;
	}

	public void update() {
		if (Game.canPlayerMove) {
			int xa = 0, ya = 0;
			if (anim < 7500)
				anim++;
			else
				anim = 0;
			if (input.up)
				ya--;
			if (input.down)
				ya++;
			if (input.left)
				xa--;
			if (input.right)
				xa++;

			if (xa != 0 || ya != 0) {
				move(xa, ya);
				walking = true;
			} else {
				walking = false;
			}
			clear();
			updateShooting();
		}
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
		if (Mouse.getB() == 1) {
			double dx = (Mouse.getX() - (Game.width * Game.scale) / 2);
			double dy = (Mouse.getY() - (Game.height * Game.scale) / 2);
			double dir2 = Math.atan2(dy, dx);
			shoot(x, y, dir2);
		}
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
