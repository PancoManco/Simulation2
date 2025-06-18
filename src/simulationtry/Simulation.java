package simulationtry;

import Actions.initActions;
import Actions.turnActions;
import simulationtry.entities.Entity;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMap map = new GameMap();
		new initActions().setupRandomEntityPositions(map);
		MapRenderer renderer = new MapRenderer();
		int iterations = 3;
		boolean isFirstIteration = true;

		for (int i = 1; i <= iterations; i++) {
			System.out.println("\nИтерация " + i);
			renderer.render(map);
			new turnActions().makeShift(map, isFirstIteration);
			isFirstIteration = false; // после первой итерации флаг сбрасывается
		}
	}

}
