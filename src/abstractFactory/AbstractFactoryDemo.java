package abstractFactory;

import scrabbleGame.PlayerInterface;
import tile.Tile;

/**
 * a short demo for the abstract factory
 * it shows how the players and tiles are produced
 * @author chong tian
 *
 */
public class AbstractFactoryDemo {
	  public static void main(String[] args) {

	      //show player factory
	      AbstractFactory playerFactory = FactoryProducer.getFactory("PLAYER");
	      PlayerInterface player1=playerFactory.getPlayer("REGULAR");
	      PlayerInterface player2=playerFactory.getPlayer("VIP");
	      
	      System.out.println(player1.getName());
	      System.out.println(player2.getName());
	      
	      //show tile factory
	      AbstractFactory tileFactory = FactoryProducer.getFactory("TILE");
	      Tile tile1=tileFactory.getTile("English");
	      Tile tile2=tileFactory.getTile("Dutch");
	      
	      System.out.println(tile1.getName());
	      System.out.println(tile2.getName());
	  }
}
