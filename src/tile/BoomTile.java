package tile;

import java.util.List;

import scrabbleGame.Player;
import scrabbleGame.Square;


/**
 * boom speical effect tile
 * @author chong tian
 *
 */
public class BoomTile implements SpecialTile{
	private static final int LENGTH=15;
	private static final int PRICE=30;

	/**
	 * get description of tile for user
	 * @return string 
	 */
	public String getDescription() {
		return " All tiles in a 3-tile radius on the board are removed from the board. "
				+ "Only surviving tiles are scored for this round. ";
	}

	/**
	 * get price of tile
	 * @return price
	 */
	public int getSPTilePrice() {
		return PRICE;
	}

	@Override
	public void applyEffect(int x, int y, Square[][] board, List<Square> list, Player player) {
		for(int i=x-3; i<=x+3&& i<LENGTH && i>=0; i++){
			for(int j=y-3; j<y+3&& j<LENGTH && j>=0; j++){

				if(player.getLastMovedTile().contains(board[i][j].getTile())){
					player.getLastMovedTile().remove(board[i][j].getTile());
				}
				if(player.getFromedSquare().contains(board[i][j].getTile())){
					player.getFromedSquare().remove(board[i][j].getTile());
				}
				player.getFormedStrings().clear();
				board[i][j].setRegularTile(null);
				player.findAllWords(board);
			}
		}
		
	}
	
	@Override
	public String toString(){
		return "Boom";
	}


}
