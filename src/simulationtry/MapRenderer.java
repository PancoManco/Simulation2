package simulationtry;

import simulationtry.entities.Entity;

public class MapRenderer {

	private final EntityRenderer entityRenderer = new EntityRenderer();

	public void render(GameMap map) {
		int rows = map.getLength();
		int cols = map.getWidth();

		System.out.print(" "); // 3 пробела для отступа под номер строки
		for (int col = 0; col < cols; col++) {
			// Выводим номер колонки, выровненный по центру шириной 3 символа
			System.out.printf("%3d", col);
		}
		System.out.println();

		for (int row = 0; row < rows; row++) {
			System.out.printf("%-3d", row);

			for (int col = 0; col < cols; col++) {
				Coordinates coord = new Coordinates(row, col);
				Entity entity = map.getEntityAt(coord);
				if (entity == null) {
					System.out.printf("%-3s", "_");
				} else {
					System.out.printf("%-3s", entityRenderer.getSymbol(entity));
				}
			}
			System.out.println();
		}
	}
}