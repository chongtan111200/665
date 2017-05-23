package scrabbleGame;

/**
 * an interface for call back
 * @author chong tian
 *
 */
public interface GameChangeMediator {
	void squareChanged(int row, int col);
	void regularTileChanged(int tilePosition);
}
