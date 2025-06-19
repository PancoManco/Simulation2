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

	public void logEating(Entity eater, String eatenType, Coordinates position) {
		String log = String.format("[%s] съел %s на [%d:%d]", eater.getClass().getSimpleName(), eatenType,
				position.getRow(), position.getCollumn());
		logEntries.add(log);
	}

	public void printLogs() {
		for (String log : logEntries) {
			System.out.println(log);
		}
		logEntries.clear();
	}

	public void clearLogs() {
		logEntries.clear();
	}
}
