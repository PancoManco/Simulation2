package simulationtry;

import java.util.ArrayList;
import java.util.List;

import simulationtry.entities.Entity;

public class MovementLogger {
	private final List<String> logEntries = new ArrayList<>();

	public void logMovement(Entity entity, Coordinates from, Coordinates to) {
		String log = String.format("[%s]: Переместилось с [%d:%d] на [%d:%d]", entity.getClass().getSimpleName(),
				from.getRow(), from.getCollumn(), to.getRow(), to.getCollumn());
		logEntries.add(log);
	}

	public void logCollision(Entity entity, Coordinates attemptedPosition) {
		String log = String.format("[%s]: Столкновение на [%d:%d] — клетка занята.", entity.getClass().getSimpleName(),
				attemptedPosition.getRow(), attemptedPosition.getCollumn());
		logEntries.add(log);
	}

	public void printLogs() {
		for (String log : logEntries) {
			System.out.println(log);
		}
		logEntries.clear();
	}
}