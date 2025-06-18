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

	public void makeShift(GameMap map) {
		int i = 0;
		for (Creature creature : getAllCreature(map)) {
			Coordinates from = map.getCoordinates(creature);

			if (i == 0) {
				i++;
				System.out.printf("[%s] Начальная позиция: [%d:%d]\n", creature.getClass().getSimpleName(),
						from.getRow(), from.getCollumn());

			} else {
				creature.makeMove(map);
				logger.logMovement(creature, from, map.getCoordinates(creature));
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
