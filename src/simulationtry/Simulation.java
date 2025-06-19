package simulationtry;

import Actions.InitActions;
import Actions.TurnActions;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMap map = new GameMap();
		new InitActions().setupRandomEntityPositions(map);

		MapRenderer renderer = new MapRenderer();
		TurnActions actions = new TurnActions();
		MovementLogger logger = new MovementLogger();

		int iterations = 3;
		boolean isFirstIteration = true;

		for (int i = 1; i <= iterations; i++) {
			System.out.println("\n=== ИТЕРАЦИЯ " + i + " ===");

			if (!isFirstIteration) {
				actions.makeShift(map, logger, false); // перемещения
			}

			renderer.render(map); // карта после перемещений

			if (isFirstIteration) {
				actions.makeShift(map, logger, true); // только начальные позиции
				isFirstIteration = false;
			}

			logger.printLogs(); // логи перемещений
		}
	}
}