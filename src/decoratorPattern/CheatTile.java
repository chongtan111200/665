package decoratorPattern;

import tile.RegularTile;

/**
 * Change the value of the tile to 2 times the value of normal value.
 * It gets the regular tile from decorator class 
 * and multiply the value of tile by 2. 
 * Then return the tile to the game class to replace the regular tiles.
 * @author chong tian
 *
 */
public class CheatTile extends TileDecorator{
	CheatTile(RegularTile decoratedTile){
		super(decoratedTile);
	}
	
	/**
	 * 
	 * @return the revised tile
	 */
	public RegularTile getTile(){
		return decoratedTile;
	}
	
	@Override
	public void cheatDoubleTileValue(){
		decoratedTile.setValue(2 * decoratedTile.getValue());
	}

	@Override
	public char getName() {
		return 0;
	}

	@Override
	public int getValue() {
		return 0;
	}
}
