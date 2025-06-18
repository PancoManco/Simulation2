package simulationtry;

import java.util.HashMap;

import simulationtry.entities.Entity;

public class MovementLogger {

	private boolean isFirstIteration = true; // Признак первой итерации

	public void logMovement(Entity entity, Coordinates from, Coordinates to) {

		System.out.printf("[%s]: Переместилось с [%d:%d] на [%d:%d]\n", entity.getClass().getSimpleName(),
				from.getRow(), from.getCollumn(), to.getRow(), to.getCollumn());

	}

	public void resetLog() {
		isFirstIteration = true; // сброс лога для следующей сессии
	}

//	public void logMovementFirstIteration(GameMap map) {
//
//	
//			System.out.printf("[%s] Начальная позиция: [%d:%d]\n", entity.getClass().getSimpleName(), from.getRow(),
//					from.getCollumn());
//			isFirstIteration = false; // Далее перейдем на режим отображения перемещений
//
//		
//	}

//	///
//	public void setEntityNewCoordinates(Coordinates coordinates, Entity entity) {
//		entities1.put(coordinates, entity);
//
//	}
//
//	public HashMap<Coordinates, Entity> getHashMapNewCoordinates() {
//		return entities;
//	}
//	///

}
