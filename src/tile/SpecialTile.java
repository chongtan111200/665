package tile;

import java.util.List;

import scrabbleGame.Player;
import scrabbleGame.Square;


/**
 * interface for special tiles
 * @author chong tian
 *
 */
public interface SpecialTile {
	/**
	 * explain what's happening for the tile
	 * @return the description of this tile
	 */
	String getDescription();
	
	/**
	 * get the price of this tile
	 * @return the price of tile
	 */
	int getSPTilePrice();
	
	/**
	 * apply special effect of this special effect tile
	 * @param x coordinate
	 * @param y coordinate
	 * @param board board of the game
	 * @param list list to be modified
	 * @param player player triggered the effect
	 */
	void applyEffect(int x, int y, Square[][] board,List<Square> list, Player player);
}
