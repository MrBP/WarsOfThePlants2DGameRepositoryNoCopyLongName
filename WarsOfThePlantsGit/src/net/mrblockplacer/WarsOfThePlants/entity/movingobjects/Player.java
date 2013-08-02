package net.mrblockplacer.WarsOfThePlants.entity.movingobjects;

import net.mrblockplacer.WarsOfThePlants.MainClass;
import net.mrblockplacer.WarsOfThePlants.entity.projectiles.BulletProjectile;
import net.mrblockplacer.WarsOfThePlants.entity.projectiles.Projectile;
import net.mrblockplacer.WarsOfThePlants.inputs.Keyboard;
import net.mrblockplacer.WarsOfThePlants.inputs.Mouse;
import net.mrblockplacer.WarsOfThePlants.level.TileCoordinate;
import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private int speed = 2;
	public int id;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_foward;
	}

	public Player(int x, int y, int id) {
		this.x = x;
		this.y = y;
		sprite = Sprite.player_foward;
		this.id = id;
		dir = 2;

	}

	public Player(int x, int y, Keyboard input, int id) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_foward;
		dir = 2;
		this.id = id;
	}

	int lastTime1 = 0;
	int lastTime2 = 0;
	int lastSpeed = 1;

	public void update() {
//		MainClass.network.sendText("HI");

		if (MainClass.canPlayerMove) {
			int xa = 0, ya = 0;
			if (anim < 7500)
				anim++;
			else
				anim = 0;
			if (input != null) {
				if (input.up)
					ya--;
				if (input.down)
					ya++;
				if (input.left)
					xa--;
				if (input.right)
					xa++;
				if (input.exit)
					System.exit(0);
				if (input.boss)
					MainClass.bossTime = !MainClass.bossTime;
				if (input.speedUp && lastTime1 <= 0 && speed < 10) {
					speed++;
					System.out.println(speed);
					lastTime1 = 100;
				} else {
					lastTime1--;
				}

				if (input.speedDown && lastTime2 <= 0 && speed > 1) {
					speed--;
					System.out.println(speed);
					lastTime2 = 100;
				} else {
					lastTime2--;
				}
			}

			// if (input.zoomOut) {
			// MainClass.scale+=0.1;
			// System.out.println("zoomOut");
			// }
			// if (input.zoomIn) {
			// MainClass.scale-=0.1;
			// System.out.println("zoomIn");
			// }
			if (xa != 0 || ya != 0) {
				move(xa, ya, this);
				walking = true;
			} else {
				walking = false;
			}
			clear();
			updateShooting();

		}
	}

	protected int getSpeed() {
		return speed;
	}

	private void clear() {
		if (level != null) {
			for (int i = 0; i < level.getProjectiles().size(); i++) {
				Projectile p = level.getProjectiles().get(i);
				if (p.isRemoved()) {
					level.getProjectiles().remove(i);
				}
			}
		}
	}

	private void updateShooting() {
		// if(fireCounter > BulletProjectile.)
		if (Mouse.getB() == 1 && fireCounter <= 0 && input != null) {
			double dx = (Mouse.getX() - (MainClass.width * MainClass.scale) / 2);
			double dy = (Mouse.getY() - (MainClass.height * MainClass.scale) / 2);
			double dir2 = Math.atan2(dy, dx);
			shoot(x, y, dir2);
			fireCounter = BulletProjectile.rateOfFire;
		} else {
			fireCounter--;
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

	public void setXY(TileCoordinate newPos) {
		this.x = newPos.getX();
		this.y = newPos.getY();
	}

}
