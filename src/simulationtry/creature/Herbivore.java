package simulationtry.creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.MovementLogger;
import simulationtry.PathFinderBFS;
import simulationtry.entities.Entity;
import simulationtry.entities.Grass;
import simulationtry.entities.Rock;
import simulationtry.entities.Tree;

public class Herbivore extends Creature {
	private boolean hasMoved = false;

	public Herbivore() {
		this.health = 10;
		this.speed = 1; // ходит 1 клетку за ход
	}

	public void makeMove(GameMap map, MovementLogger logger) {
		Optional<Coordinates> optionalFrom = map.getCoordinates(this);
		if (optionalFrom.isEmpty()) {
			return;
		}
		Coordinates from = optionalFrom.get();

		PathFinderBFS pathFinder = new PathFinderBFS();
		Set<Class<?>> obstacles = Set.of(Tree.class, Rock.class, Predator.class);

		// Ищем траву
		List<Coordinates> grassPositions = new ArrayList<>();
		for (Map.Entry<Coordinates, Entity> entry : map.getHashMap().entrySet()) {
			if (entry.getValue() instanceof Grass) {
				grassPositions.add(entry.getKey());
			}
		}

		if (grassPositions.isEmpty()) {
			// Травы нет — ходим рандомно
			moveRandomly(map, logger, from);
			return;
		}

		// Ищем кратчайший путь к траве
		List<Coordinates> shortestPath = Collections.emptyList();
		for (Coordinates grassPos : grassPositions) {
			List<Coordinates> path = pathFinder.findPath(map, from, grassPos, obstacles);
			if (!path.isEmpty() && (shortestPath.isEmpty() || path.size() < shortestPath.size())) {
				shortestPath = path;
			}
		}

		if (shortestPath.isEmpty()) {
			moveRandomly(map, logger, from);
			return;
		}

		// Двигаемся максимум на speed шагов
		int steps = Math.min(this.speed, shortestPath.size());
		Coordinates to = shortestPath.get(steps - 1);

		Entity entityAtTo = map.getEntityAt(to);

		// Если дошли до травы — съедаем
		if (grassPositions.contains(to) && entityAtTo instanceof Grass) {
			map.getHashMap().remove(to);
			this.health += 5; // восстановление здоровья
			logger.logEating(this, "Grass", to);
		}

		if (map.isEmptySquare(to) || grassPositions.contains(to)) {
			map.getHashMap().remove(from);
			map.getHashMap().put(to, this);
			logger.logMovement(this, from, to);
		} else {
			// Клетка занята, не двигаемся
		}
	}

	private void moveRandomly(GameMap map, MovementLogger logger, Coordinates from) {
		Random random = new Random();
		int[] dx = { -1, 0, 1 };
		int[] dy = { -1, 0, 1 };

		List<Coordinates> candidates = new ArrayList<>();
		for (int x : dx) {
			for (int y : dy) {
				if (Math.abs(x) + Math.abs(y) != 1)
					continue;
				int newRow = from.getRow() + y;
				int newCol = from.getCollumn() + x;
				if (newRow >= 0 && newRow < map.getLength() && newCol >= 0 && newCol < map.getWidth()) {
					Coordinates candidate = new Coordinates(newRow, newCol);
					if (map.isEmptySquare(candidate)) {
						candidates.add(candidate);
					}
				}
			}
		}

		if (!candidates.isEmpty()) {
			Coordinates to = candidates.get(random.nextInt(candidates.size()));
			map.getHashMap().remove(from);
			map.getHashMap().put(to, this);
			logger.logMovement(this, from, to);
		}
	}

	@Override
	public String toString() {
		return "\ud83d\udc0f";
	}
}