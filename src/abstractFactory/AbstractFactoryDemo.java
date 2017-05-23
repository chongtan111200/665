package abstractFactory;

import scrabbleGame.PlayerInterface;
import tile.Tile;

public class AbstractFactoryDemo {
	  public static void main(String[] args) {

	      //show player factory
	      AbstractFactory playerFactory = FactoryProducer.getFactory("PLAYER");
	      PlayerInterface player1=playerFactory.getPlayer("REGULAR");
	      PlayerInterface player2=playerFactory.getPlayer("VIP");
	      
	      System.out.print(player1.getName());
	      System.out.print(player2.getName());
	      
	      //show tile factory
	      AbstractFactory tileFactory = FactoryProducer.getFactory("TILE");
	      Tile tile1=tileFactory.getTile("English");
	      Tile tile2=tileFactory.getTile("Dutch");
	      
	      System.out.print(tile1.getName());
	      System.out.print(tile2.getName());
	  }
}
