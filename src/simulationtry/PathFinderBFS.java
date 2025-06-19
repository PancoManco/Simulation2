package simulationtry;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import simulationtry.entities.Entity;

public class PathFinderBFS {

	public List<Coordinates> findPath(GameMap map, Coordinates start, Coordinates goal, Set<Class<?>> obstacles) {
		int rows = map.getLength();
		int cols = map.getWidth();

		boolean[][] visited = new boolean[rows][cols];
		Map<Coordinates, Coordinates> parentMap = new HashMap<>();
		Queue<Coordinates> queue = new LinkedList<>();

		queue.add(start);
		visited[start.getRow()][start.getCollumn()] = true;

		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		while (!queue.isEmpty()) {
			Coordinates current = queue.poll();

			if (current.equals(goal)) {
				// восстанавливаем путь от goal к start
				return buildPath(parentMap, start, goal);
			}

			for (int[] dir : directions) {
				int newRow = current.getRow() + dir[0];
				int newCol = current.getCollumn() + dir[1];

				if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
					Coordinates next = new Coordinates(newRow, newCol);
					if (!visited[newRow][newCol]) {
						Entity entity = map.getEntityAt(next);
						if (entity == null || !obstacles.contains(entity.getClass())) {
							visited[newRow][newCol] = true;
							parentMap.put(next, current);
							queue.add(next);
						}
					}
				}
			}
		}
		// путь не найден
		return Collections.emptyList();
	}

	private List<Coordinates> buildPath(Map<Coordinates, Coordinates> parentMap, Coordinates start, Coordinates goal) {
		LinkedList<Coordinates> path = new LinkedList<>();
		Coordinates current = goal;
		while (!current.equals(start)) {
			path.addFirst(current);
			current = parentMap.get(current);
			if (current == null) {
				// Ошибка, путь не полон — вернем пустой список
				return Collections.emptyList();
			}
		}
		return path;
	}
}