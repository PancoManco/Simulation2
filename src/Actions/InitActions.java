package Actions;

import java.util.Map;
import java.util.Random;

import simulationtry.GameMap;
import simulationtry.creature.Herbivore;
import simulationtry.creature.Predator;
import simulationtry.entities.Entity;
import simulationtry.entities.Grass;
import simulationtry.entities.Rock;
import simulationtry.entities.Tree;

public class InitActions extends Action {

	public final int mapSize = 12;
	Random random = new Random();

	@Override
	public void execute() {

	}

	public void setupRandomEntityPositions(GameMap map) {
		Map<Class<? extends Entity>, Integer> entityCounts = Map.of(Herbivore.class, 3, Predator.class, 3, Grass.class,
				2, Tree.class, 2, Rock.class, 2);

		for (var entry : entityCounts.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				try {
					Entity entity = entry.getKey().getDeclaredConstructor().newInstance();
					boolean placed = map.placeEntityRandomly(entity);
					if (!placed) {
						System.err.println("❌ Не удалось разместить: " + entity.getClass().getSimpleName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}