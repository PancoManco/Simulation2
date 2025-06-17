package simulationtry;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import simulationtry.entities.Entity;

public class GameMap {

	private HashMap<Coordinates, Entity> entities = new HashMap<>();
	private final int length;
	private final int width;

	public GameMap() {
		this.length = Constans.ROW;
		this.width = Constans.COLLUMN;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	private Random random = new Random();

	public void setEntity(Coordinates coordinates, Entity entity) {
		entities.put(coordinates, entity);
	}

	public boolean isEmptySquare(Coordinates coordinates) {
		return !entities.containsKey(coordinates);
	}

	public Coordinates getCoordinates(Entity entity) {
		for (Entry<Coordinates, Entity> entry : entities.entrySet()) {
			if (entry.getValue().equals(entity)) {
				return entry.getKey();
			}
		}
		throw new IllegalArgumentException();
	}

	public Entity getEntityAt(Coordinates coordinates) {
		return entities.get(coordinates);
	}

	public HashMap<Coordinates, Entity> getHashMap() {
		return entities;

	}

	public Coordinates generateRandomCoordinates(int size) {
		int maxIndex = size - 1;
		Coordinates coordinates;

		do {
			int rX = random.nextInt(maxIndex + 1);
			int rY = random.nextInt(maxIndex + 1);
			coordinates = new Coordinates(rX, rY);
		} while (!isEmptySquare(coordinates));

		return coordinates;
	}
//	public void removeEntity(Coordinates coordinates) {
//		entities.remove(coordinates);
//	}

}