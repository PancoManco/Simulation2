package simulationtry;

public class MapRenderer {

	public void render(GameMap map) {
		int rows = map.getLength();
		int cols = map.getWidth();
		Coordinates coord = new Coordinates();

		System.out.print("  ");
		for (int col = 0; col < cols; col++) {
			System.out.printf("%2d", col);
		}
		System.out.println();
		for (int row = 0; row < rows; row++) {
			System.out.printf("%2d ", row);
			String line = "";
			for (int column = 0; column < cols; column++) {
				Coordinates coordinates = new Coordinates(row, column);
				if (map.isEmptySquare(coordinates)) {
					line += getSpringForEmptySquare(coordinates);

				} else {
					line += map.getEntityAt(coordinates);

				}
			}
			System.out.println(line);
		}

	}

	private String getSpringForEmptySquare(Coordinates coordinates) {

		return "_ ";
	}

}
