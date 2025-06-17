package simulationtry.creature;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.entities.Entity;

public abstract class Creature extends Entity {

	public Creature() {

	}

	public abstract void makeMove(GameMap map);

}
