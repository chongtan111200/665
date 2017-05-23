package tile;

import java.util.List;

import scrabbleGame.Player;
import scrabbleGame.Square;


/**
 * speical tile for reverse order
 * @author chong tian
 *
 */
public class ReverseOrderTile implements SpecialTile{

	private static final int PRICE=10;
	/**
	 * explain what's happening for the tile
	 * @return the description of this tile
	 */
	public String getDescription() {
		return "The turn ends as usual, but after this tile is activated play "
				+ "continues in the reverse of the previous order. ";
	}

	/**
	 * get the price of this tile
	 * @return the price of tile
	 */
	public int getSPTilePrice() {
		return PRICE;
	}

	@Override
	public void applyEffect(int x, int y, Square[][] board, List<Square> list, Player player) {
		player.setPlayOrder(-1*player.getPlayOrder());
	}

	@Override
	public String toString(){
		return "Reverse";
	}


}
