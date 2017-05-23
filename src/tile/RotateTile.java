package tile;

import java.util.List;

import scrabbleGame.Player;
import scrabbleGame.Square;


/**
 * rotate speical effect tile
 * 
 * @author chong tian
 *
 */
public class RotateTile implements SpecialTile {
	private static final int LENGTH = 15;
	private static final int PRICE = 20;

	/**
	 * get description of tile for user
	 * 
	 * @return string
	 */
	public String getDescription() {
		return "When this tile is triggered, each tile on the board shifts down"
				+ " one position and the bottom row of the board becomes the new top row of the board.";
	}

	/**
	 * get price of tile
	 * 
	 * @return price
	 */
	public int getSPTilePrice() {
		return PRICE;
	}

	@Override
	public void applyEffect(int x, int y, Square[][] board, List<Square> list, Player player) {
		// save get the first row, rotate regular tiles
		Tile[] temp = new Tile[LENGTH];
		for (int j = 0; j < LENGTH; j++) {
			if(board[0][j].getTile()!=null)
			temp[j] = board[0][j].getTile();
		}

		for (int i = 1; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				if(board[i][j].getTile()!=null)
				board[i - 1][j].setRegularTile(board[i][j].getTile());
			}
		}
		
		for (int j = 0; j < LENGTH; j++) {
			if(temp[j]!=null)
			board[14][j].setRegularTile(temp[j]) ;
		}
		
		// save get the first row, rotate special tiles
		SpecialTile[] temp2 = new SpecialTile[LENGTH];
		for (int j = 0; j < LENGTH; j++) {
			if(board[0][j].getSPTile()!=null)
			temp2[j] = board[0][j].getSPTile();
		}

		for (int i = 1; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				if(board[i][j].getSPTile()!=null)
				board[i - 1][j].setSPTile(board[i][j].getSPTile());
			}
		}
		
		for (int j = 0; j < LENGTH; j++) {
			if(temp2[j]!=null)
			board[14][j].setSPTile(temp2[j]);
		}
	}

	@Override
	public String toString() {
		return "Rotate";
	}

}
