package tile;

import java.util.List;

import scrabbleGame.Player;
import scrabbleGame.Square;



/**
 * zero the player's total score
 * @author chong tian
 *
 */
public class ZeroTile implements SpecialTile{

	private static final int PRICE=40;
	/**
	 * explain what's happening for the tile
	 * @return the description of this tile
	 */
	public String getDescription() {
		return " The score of player would be zero once triggered ";
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
		player.setScoreModifier(0);
	}

	@Override
	public String toString(){
		return "Zero";
	}
}
