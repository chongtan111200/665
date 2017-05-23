package abstractFactory;

/**
 * an interface for the 2 kinds of players
 */
import scrabbleGame.PlayerInterface;
import tile.Tile;

public abstract class AbstractFactory {
	   abstract PlayerInterface getPlayer(String player);
	   abstract Tile getTile(String tile);
}
