package iteratordemo;

import tile.SpecialTile;

/**
 * shows the use of the iterator for the special tiles
 * @author chong tian
 *
 */
public class IteratorDemo {

	public static void main(String[] args) {
		SpecialTileIterator spTileCollection = new SpecialTileIterator();

		for (Iterator iter = spTileCollection.getIterator(); iter.hasNext();) {
			SpecialTile tile = (SpecialTile) iter.next();
			System.out.println("tile : " + tile.getDescription());
		}
	}
}