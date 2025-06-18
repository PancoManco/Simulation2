package simulationtry.creature;

import java.util.Random;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.MovementLogger;

public class Predator extends Creature {
	private boolean hasMoved = false;

	public Predator() {
	}

	public void makeMove(GameMap map) {

		Coordinates from = map.getCoordinates(this);
		MovementLogger logger = new MovementLogger();

		Random random = new Random();
		int dx = random.nextInt(3) - 1;
		int dy = random.nextInt(3) - 1;

		if (dx != 0 && dy != 0) {
			// Выбираем случайное направление, оставляя одно нулевым
			if (random.nextBoolean()) {
				dy = 0; // Горизонтальное движение
			} else {
				dx = 0; // Вертикальное движение
			}
		}

		// Рассчитываем потенциальные новые координаты
		int newRow = from.getRow() + dy;
		int newCol = from.getCollumn() + dx;

		// Проверяем границы карты
		if (newRow >= 0 && newRow < map.getLength() && newCol >= 0 && newCol < map.getWidth()) {
			// Новые координаты в границах карты
			Coordinates to = new Coordinates(newRow, newCol);

			// Удаляем существо с предыдущих координат
			map.getHashMap().remove(from);
			// Помещаем существо на новые координаты
			map.getHashMap().put(to, this);
		}
	}

	@Override
	public String toString() {
		return "\ud83d\udc05";
	}
}
