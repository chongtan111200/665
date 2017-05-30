package decoratorPattern;

import tile.RegularTile;
import tile.Tile;

/**
 * 
 * This class implements the tile interface to decorate it.
 * It provides additional functionality to tile.
 * It doubles the value of a regular tile
 * @author chong tian
 *
 */
public abstract class TileDecorator implements Tile{

		   protected RegularTile decoratedTile;

		   public TileDecorator(RegularTile decoratedTile){
		      this.decoratedTile = decoratedTile;
		   }

		   /**
		    * change the value of the regular tile
		    */
		   abstract void cheatDoubleTileValue();

		}

