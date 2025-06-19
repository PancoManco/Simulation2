package simulationtry.creature;

import java.util.Random;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.MovementLogger;

public class Predator extends Creature {
	private boolean hasMoved = false;

	public Predator() {
		this.health = 20;
		this.speed = 2; // ходит 2 клетки за ход
	}

	public void makeMove(GameMap map, MovementLogger logger) {
		Coordinates from = map.getCoordinates(this);

		Random random = new Random();
		int dx = random.nextInt(3) - 1;
		int dy = random.nextInt(3) - 1;

		if (dx != 0 && dy != 0) {
			if (random.nextBoolean()) {
				dy = 0;
			} else {
				dx = 0;
			}
		}

		int newRow = from.getRow() + dy;
		int newCol = from.getCollumn() + dx;

		if (newRow >= 0 && newRow < map.getLength() && newCol >= 0 && newCol < map.getWidth()) {
			Coordinates to = new Coordinates(newRow, newCol);

			if (map.isEmptySquare(to)) {
				map.getHashMap().remove(from);
				map.getHashMap().put(to, this);
				logger.logMovement(this, from, to);
			} else {
				// Столкновение — логируем
				logger.logCollision(this, to);
			}
		}
	}

	@Override
	public String toString() {
		return "\ud83d\udc05";
	}
}
