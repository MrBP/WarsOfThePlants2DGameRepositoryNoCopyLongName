package net.mrblockplacer.WarsOfThePlants.entity.projectile;

import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrg, yOrg;
	protected Sprite sprite;
	protected double nx, ny, speed, range, angle, damageDealt, distance, x, y;
	public static int rateOfFire;

	public Projectile(int x, int y, double dir) {
		xOrg = x;
		yOrg = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	protected void move() {

	}

	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
