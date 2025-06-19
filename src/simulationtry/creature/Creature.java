package simulationtry.creature;

import simulationtry.GameMap;
import simulationtry.MovementLogger;
import simulationtry.entities.Entity;

public abstract class Creature extends Entity {
	protected int health;
	protected int speed;

	public int getHealth() {
		return health;
	}

	public void reduceHealth(int amount) {
		health -= amount;
	}

	public int getSpeed() {
		return speed;
	}

	public abstract void makeMove(GameMap map, MovementLogger logger);
}