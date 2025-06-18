package Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.MovementLogger;
import simulationtry.creature.Creature;
import simulationtry.entities.Entity;

public class turnActions extends Action {

	MovementLogger logger = new MovementLogger();

	@Override
	public void execute() {

	}

	public void makeShift(GameMap map, boolean isFirstIteration) {
		MovementLogger logger = new MovementLogger();

		for (Creature creature : getAllCreature(map)) {
			Coordinates from = map.getCoordinates(creature);

			if (isFirstIteration) {
				// При первой итерации выводим начальные позиции
				System.out.printf("[%s] Начальная позиция: [%d:%d]\n", creature.getClass().getSimpleName(),
						from.getRow(), from.getCollumn());
			} else {
				// Начиная со второй итерации выполняем перемещение и логируем его
				creature.makeMove(map);
				Coordinates to = map.getCoordinates(creature);
				logger.logMovement(creature, from, to);
			}
		}
	}

	private Set<Creature> getAllCreature(GameMap map) {
		Set<Creature> allCreatures = new HashSet<>();
		for (Map.Entry<Coordinates, Entity> entry : map.getHashMap().entrySet()) {
			Entity entity = entry.getValue();
			if (entity instanceof Creature) {
				allCreatures.add((Creature) entity);
			}
		}
		return allCreatures;
	}

}
