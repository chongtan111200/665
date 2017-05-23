package abstractFactory;


import scrabbleGame.PlayerInterface;
import tile.RegularTile;
import tile.Tile;

public class TileFactory extends AbstractFactory {

	@Override
	public PlayerInterface getPlayer(String player) {
		return null;
	}

	@Override
	Tile getTile(String tile) {
		if (tile == null) {
			return null;
		}

		if (tile.equalsIgnoreCase("English")) {
			return new RegularTile('E', 1);

		} else if (tile.equalsIgnoreCase("Dutch")) {
			return new RegularTile('D', 1);
		}
		return null;
	}
}
