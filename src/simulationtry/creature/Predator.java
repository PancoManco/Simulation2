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

public class Predator extends Creature {
	private boolean hasMoved = false;

	public Predator() {
		this.health = 20;
		this.speed = 2; // ходит 2 клетки за ход
	}

	public void makeMove(GameMap map, MovementLogger logger) {
		Optional<Coordinates> optionalFrom = map.getCoordinates(this);
		if (optionalFrom.isEmpty()) {
			return;
		}
		Coordinates from = optionalFrom.get();

		PathFinderBFS pathFinder = new PathFinderBFS();

		// Хищник сам — препятствие для движения
		Set<Class<?>> obstacles = Set.of(Tree.class, Rock.class, Predator.class);

		// Сначала ищем травоядных
		List<Coordinates> herbivorePositions = new ArrayList<>();
		for (Map.Entry<Coordinates, Entity> entry : map.getHashMap().entrySet()) {
			Entity entity = entry.getValue();
			if (entity instanceof Herbivore) {
				herbivorePositions.add(entry.getKey());
			}
		}

		List<Coordinates> targetPositions;
		boolean huntingHerbivore = !herbivorePositions.isEmpty();

		if (huntingHerbivore) {
			targetPositions = herbivorePositions;
		} else {
			// Если травоядных нет, ищем траву
			List<Coordinates> grassPositions = new ArrayList<>();
			for (Map.Entry<Coordinates, Entity> entry : map.getHashMap().entrySet()) {
				Entity entity = entry.getValue();
				if (entity instanceof Grass) {
					grassPositions.add(entry.getKey());
				}
			}
			targetPositions = grassPositions;
		}

		if (targetPositions.isEmpty()) {
			// Нет целей — ходим рандомно
			moveRandomly(map, logger, from);
			return;
		}

		// Ищем кратчайший путь к любой из целей
		List<Coordinates> shortestPath = Collections.emptyList();
		for (Coordinates targetPos : targetPositions) {
			List<Coordinates> path = pathFinder.findPath(map, from, targetPos, obstacles);
			if (!path.isEmpty() && (shortestPath.isEmpty() || path.size() < shortestPath.size())) {
				shortestPath = path;
			}
		}

		if (shortestPath.isEmpty()) {
			// Не можем пройти к цели — ходим рандомно
			moveRandomly(map, logger, from);
			return;
		}

		// Двигаемся на speed шагов по пути (или меньше, если путь короче)
		int steps = Math.min(this.speed, shortestPath.size());
		Coordinates to = shortestPath.get(steps - 1);

		Entity entityAtTo = map.getEntityAt(to);

		boolean ate = false;

		// Съедаем только если это целевой тип сущности и цель именно та, которую ищем
		if (huntingHerbivore && entityAtTo instanceof Herbivore) {
			map.getHashMap().remove(to);
			this.health += 10;
			logger.logEating(this, "Herbivore", to);
			ate = true;
		} else if (!huntingHerbivore && entityAtTo instanceof Grass) {
			map.getHashMap().remove(to);
			this.health += 3;
			logger.logEating(this, "Grass", to);
			ate = true;
		}

		// Перемещаемся, если клетка свободна или съели цель на этой клетке
		if (ate || map.isEmptySquare(to)) {
			map.getHashMap().remove(from);
			map.getHashMap().put(to, this);
			logger.logMovement(this, from, to);
		}
		// Иначе хищник не двигается, чтобы не заходить на занятое место
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
		return "\ud83d\udc05";
	}
}