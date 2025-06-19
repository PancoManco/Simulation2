package simulationtry;

import java.util.Scanner;

import Actions.InitActions;
import Actions.TurnActions;

public class Simulation {

	public static void main(String[] args) {
		GameMap map = new GameMap();
		new InitActions().setupRandomEntityPositions(map);

		MapRenderer renderer = new MapRenderer();
		TurnActions actions = new TurnActions();
		MovementLogger logger = new MovementLogger();

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		boolean isFirstIteration = true;

		int iteration = 0;

		while (running) {
			iteration++;
			System.out.println("\n=== ИТЕРАЦИЯ " + iteration + " ===");

			// Очистим логи перед ходом, чтобы не скапливались старые записи
			logger.clearLogs();

			if (isFirstIteration) {
				actions.makeShift(map, logger, true);
				isFirstIteration = false;
			} else {
				actions.makeShift(map, logger, false);
			}

			renderer.render(map);

			logger.printLogs();

			System.out.println("Введите 'q' для остановки симуляции, Enter для продолжения:");
			String input = scanner.nextLine();
			if ("q".equalsIgnoreCase(input)) {
				running = false;
				System.out.println("Симуляция остановлена.");
				// Можно добавить здесь сохранение состояния в файл, если надо
			}
		}

		scanner.close();
	}
}