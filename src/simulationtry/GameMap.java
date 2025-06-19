package simulationtry;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import simulationtry.entities.Entity;

public class GameMap {

	private final HashMap<Coordinates, Entity> entities = new HashMap<>();
	private final int length;
	private final int width;
	private final Random random = new Random();

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

	public void setEntity(Coordinates coordinates, Entity entity) {
		entities.put(coordinates, entity);
	}

	public boolean isEmptySquare(Coordinates coordinates) {
		return !entities.containsKey(coordinates);
	}

	public Coordinates getCoordinates(Entity entity) {
		for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
			if (entry.getValue().equals(entity)) {
				return entry.getKey();
			}
		}
		throw new IllegalArgumentException("Сущность не найдена на карте.");
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

	// ✅ Метод для безопасного размещения сущности
	public boolean placeEntityRandomly(Entity entity) {
		int attempts = 0;
		while (attempts < 100) {
			Coordinates coords = generateRandomCoordinates(width);
			if (isEmptySquare(coords)) {
				setEntity(coords, entity);
				return true;
			}
			attempts++;
		}
		return false;
	}
}