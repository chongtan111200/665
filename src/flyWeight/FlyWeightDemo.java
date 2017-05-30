package flyWeight;

import tile.Tile;
/**
 * This class demonstrates that only 1 tile is created even 20 are needed.
 * @author chong tian
 *
 */
public class FlyWeightDemo {

	public static void main(String[] args) {
		for(int i=0; i < 5; i++) {
			Tile demoTile = FlyWeightFactory.getTile('t', 1);
			System.out.println(demoTile.getName());
		}
		for(int i=0; i < 5; i++) {
			Tile demoTile2 = FlyWeightFactory.getTile('s', 1);
			System.out.println(demoTile2.getName());
		}
	}
}
