package tile;

import java.util.List;

import scrabbleGame.Player;
import scrabbleGame.Square;


/**
 *special Tile for negation 
 */
public class NegateTile implements SpecialTile{

	private static final int PRICE=20;
	
	/**
	 * explain what's happening for the tile
	 * @return the description of this tile
	 */
	public String getDescription() {
		return "The word that activated this tile is scored negatively for the player who activated the tile; "
				+ "i.e., the player loses (rather than gains) the points for the played word";
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
		player.setScoreModifier(-1);
		
	}

	@Override
	public String toString(){
		return "Negate";
	}
}
