package scrabbleGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scrabbleGame.Game;
import scrabbleGame.GameChangeMediator;



/**
 * listener of each square on the board, takes the moved tile and save it
 * @author chong tian
 *
 */
public class SquareListener implements ActionListener{

	private final int row;
	private final int col;
	private final Game game;

	/**
	 * constructor
	 * @param row row
	 * @param col column
	 * @param game game passed
	 */
	public SquareListener(int row,int col, Game game){
		this.row=row;
		this.col=col;
		this.game=game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(game.getCurrentPlayer().getTempTile()!=null){
		game.addRegularTile(row,col,game.getCurrentPlayer().getTempTile(),game.getCurrentPlayer().getLastMovedTile());
		game.getSquare(row, col).setRegularTile(game.getCurrentPlayer().getTempTile());
		game.getCurrentPlayer().findAllWords(game.getBoard());
		for(GameChangeMediator g:game.getGameChangeListener()){
		g.squareChanged(row,col);
		}
		}else if(game.getCurrentPlayer().getTempSPTile()!=null){
			game.getCurrentPlayer().getSpecialTiles().put(game.getCurrentPlayer().getName(),game.getCurrentPlayer().getTempSPTile());
			game.addSPTile(row, col, game.getCurrentPlayer().getTempSPTile());
			game.getSquare(row, col).setSPTile(game.getCurrentPlayer().getTempSPTile());
			for(GameChangeMediator g:game.getGameChangeListener()){
				g.squareChanged(row,col);
				}
		}
		game.getCurrentPlayer().setTempTile(null);
	}

}
