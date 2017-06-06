package iteratordemo;

import tile.BoomTile;
import tile.NegateTile;
import tile.SpecialTile;
import tile.ZeroTile;

/**
 * shows the implementation of the iterator pattern
 * it loops through the special tiles
 * @author chong tian
 *
 */
public class SpecialTileIterator implements Container{
	public SpecialTile tiles[] = {new BoomTile() , new NegateTile() ,new ZeroTile() };
	
	   @Override
	   public Iterator getIterator() {
	      return new SpTileIterator();
	   }
	   
	   private class SpTileIterator implements Iterator {

		      int index;

		      @Override
		      public boolean hasNext() {
		      
		         if(index < tiles.length){
		            return true;
		         }
		         return false;
		      }

		      @Override
		      public Object next() {
		      
		         if(this.hasNext()){
		            return tiles[index++];
		         }
		         return null;
		      }		
		   }
}
