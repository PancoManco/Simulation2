package Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.creature.Creature;
import simulationtry.entities.Entity;

public class turnActions extends Action {

	@Override
	public void execute() {

	}

	public void makeShift(GameMap map) {

		for (Creature creature : getAllCreature(map)) {

			creature.makeMove(map);
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
