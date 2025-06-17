package simulationtry.creature;

import java.util.HashMap;
import java.util.Random;

import simulationtry.Coordinates;
import simulationtry.GameMap;
import simulationtry.entities.Entity;

public class Herbivore extends Creature {

	public Herbivore() {

	}

	public void makeMove(GameMap map) {

		Coordinates from = map.getCoordinates(this);

		// Возможные направления движения (+1/-1 по оси X или Y)
		Random random = new Random();
		int dx = random.nextInt(3) - 1; // Смещение по оси X (-1, 0, +1)
		int dy = random.nextInt(3) - 1; // Смещение по оси Y (-1, 0, +1)

		// Исключаем диагональное движение (двигаться только по горизонтали или
		// вертикали)
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

	private boolean isValidPosition(Coordinates coords, GameMap map) {
		return map.isEmptySquare(coords);
	}

	@Override
	public String toString() {
		return "\ud83d\udc0f";
	}

}
