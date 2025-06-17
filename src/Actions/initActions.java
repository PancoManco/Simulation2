package Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.creature.Herbivore;
import simulationtry.creature.Predator;
import simulationtry.entities.Entity;
import simulationtry.entities.Grass;
import simulationtry.entities.Rock;
import simulationtry.entities.Tree;

public class initActions extends Action {

	public final int mapSize = 12;
	Random random = new Random();

	@Override
	public void execute() {
	}

	public void setupRandomEntityPositions(GameMap map) {

		map.getHashMap().put(map.generateRandomCoordinates(12), new Herbivore());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Herbivore());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Herbivore());

		map.getHashMap().put(map.generateRandomCoordinates(12), new Predator());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Predator());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Predator());

		map.getHashMap().put(map.generateRandomCoordinates(12), new Grass());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Grass());

		map.getHashMap().put(map.generateRandomCoordinates(12), new Tree());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Tree());

		map.getHashMap().put(map.generateRandomCoordinates(12), new Rock());
		map.getHashMap().put(map.generateRandomCoordinates(12), new Rock());
	}
}
