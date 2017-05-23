package tile;

/**
 * This is the class for regular tiles with letters and values
 * 
 * @author chong tian
 *
 */
public class RegularTile implements Tile {
	private char name;
	private int value;

	/**
	 * Constructor creates a new letter tile with name and value
	 * 
	 * @param name name of tile
	 * @param value value of tile
	 * 
	 */
	public RegularTile(char name, int value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @return name
	 */
	public char getName() {
		return name;
	}

	/**
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @param value to be set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "" + name;
	}

}
