package simulationtry;

import java.util.Objects;

public class Coordinates {

	private int row, collumn;

	public Coordinates(int row, int collumn) {
		this.row = row;
		this.collumn = collumn;
	}

	public Coordinates() {
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCollumn() {
		return collumn;
	}

	public void setCollumn(int collumn) {
		this.collumn = collumn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Coordinates that = (Coordinates) o;

		if (row != that.row)
			return false;
		return collumn == that.collumn;
	}

	@Override
	public int hashCode() {
		int result = row;
		result = 31 * result + collumn;
		return result;
	}

}
