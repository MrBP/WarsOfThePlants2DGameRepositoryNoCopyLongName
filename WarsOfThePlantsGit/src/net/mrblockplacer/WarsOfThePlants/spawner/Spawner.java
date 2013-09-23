package net.mrblockplacer.WarsOfThePlants.spawner;

import java.util.Random;

import net.mrblockplacer.WarsOfThePlants.effects.Particle;
import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.level.Level;

public class Spawner extends Entity {

	private SpawnerType type;

	public Spawner(int x, int y, SpawnerType type, int amount, Level level, boolean rand) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		Random rand2 = new Random();
		for (int i = 0; i < amount; i++) {
			if (type == SpawnerType.PARTICLE) {
				level.add(new Particle(x, y, rand ? rand2.nextInt(50) + 100 : 100));
			}
		}
	}
}
