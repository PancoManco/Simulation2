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
		int i = 1;

		while (i < 4) {
			System.out.println("day" + i);
			renderer.render(map);
			new turnActions().makeShift(map);
			i++;

			System.out.println();
		}

	}

}
