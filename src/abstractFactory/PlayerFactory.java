package abstractFactory;

import scrabbleGame.Player;
import scrabbleGame.PlayerInterface;
import scrabbleGame.PlayerVip;
import tile.Tile;

/**
 * the player factory
 * produces the player in the run time
 * @author chong tian
 *
 */
public class PlayerFactory extends AbstractFactory{
	
	   @Override
	   public PlayerInterface getPlayer(String player){
	   
	      if(player == null){
	         return null;
	      }		
	      
	      if(player.equalsIgnoreCase("REGULAR")){
	         return new Player("Regular");
	         
	      }else if(player.equalsIgnoreCase("VIP")){
	         return new PlayerVip("Vip");
	      }
	         
	      return null;
	   }
	   
	   @Override
	   Tile getTile(String tile) {
	      return null;
	   }
}
