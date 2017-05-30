package flyWeight;

import java.util.HashMap;
import java.util.Map;

import tile.RegularTile;
import tile.Tile;

/**
 * A static class to store all tiles in a map.
 * When first needed, the tile is created.
 * When needed again, the tile is got from the map.
 * @author chong tian
 *
 */
public class FlyWeightFactory {
	private static final Map<Character, Tile> tileMap = new HashMap<>();
	
	public static Tile getTile(char tile, int value) {
	      Tile returnTile = tileMap.get(tile);

	      if(returnTile == null) {
	    	  returnTile = new RegularTile(tile,value);
	    	  tileMap.put(tile, returnTile);
	         System.out.println("Creating new Regular tile of : " + tile);
	      }
	      return returnTile;
	   }
}
