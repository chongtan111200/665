package scrabbleGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scrabbleGame.Game;
import scrabbleGame.GameChangeMediator;

/**
 * tile listener for changing the tiles
 * @author chong tian
 *
 */
public class TileListener implements ActionListener{
	private int tilePosition;
	private Game game;

	
	public TileListener(int tilePosition,Game game){
		this.tilePosition=tilePosition;
		this.game=game;

	}
	
	@Override
	public void actionPerformed(final ActionEvent arg0) {
		if(tilePosition!=7){
		game.getCurrentPlayer().setTempTile(game.getCurrentPlayer().getRegularTiles().get(tilePosition));
		for(GameChangeMediator g:game.getGameChangeListener()){
		g.regularTileChanged(tilePosition);
		}
		}else{

			game.getCurrentPlayer().setTempSPTile(game.getCurrentPlayer().getBourghtSPTile());
			for(GameChangeMediator g:game.getGameChangeListener()){
				g.regularTileChanged(tilePosition);
				}
		}
	}
}
