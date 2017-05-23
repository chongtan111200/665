package scrabbleGame;

import tile.SpecialTile;
import tile.Tile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the class for players
 * @author chong tian
 *
 */
public class Player implements PlayerInterface {
	private static final int LENGTH = 15;
	private static final int MAX_TILES = 7;
	private String name;
	private int score;
	private int scoreModifier;
	private int playOrder;
	private Tile movedTile;
	private SpecialTile TempSPTile;
	
	/**
	 * 0= never challenge anyone 
	 * 1= challenger but challenge invalid, need to be punished 
	 * 2= challenger but challenge valid 
	 * 3= challenged challenge is valid need to be punished 
	 * 4= challenged but challenge invalid, fine
	 */
	private int challengedStatus;
	private boolean played;
	private List<Tile> regularTiles;
	private Map<String,SpecialTile> specialTiles;
	private SpecialTile bourghtSPTile;
	private List<Square> lastMovedTiles;
	private List<String> formedStrings;
	private List<Square> formedSquares;

	/**
	 * Constructor creates a new letter tile with name and value
	 * 
	 * @param name is the name of the player
	 */
	public Player(String name) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
		this.score = 0;
		this.regularTiles = new ArrayList<Tile>();
		specialTiles = new HashMap<String,SpecialTile>();
		scoreModifier = 1;
		lastMovedTiles = new ArrayList<Square>();
		challengedStatus = 0;

		formedStrings = new ArrayList<String>();
		formedSquares = new ArrayList<Square>();
	}

	/**
	 * set the order the player is going to play
	 * @param order the order of player
	 */
	public void setPlayOrder(int order) {
		this.playOrder = order;
	}

	/**
	 * 
	 * @return formed squares of last move
	 */
	public List<Square> getFromedSquare(){
		return formedSquares;
	}
	
	/**
	 * 
	 * @return order of players
	 */
	public int getPlayOrder() {
		return playOrder;
	}

	/**
	 * 
	 * @return name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return score of player
	 * @override
	 */
	public int getScore() {
		return score;
	}

	/**
	 * set the status of the player
	 * @param status if the player has moved or not
	 */
	public void setPlayed(boolean status) {
		played = status;
	}

	/**
	 * 
	 * @return if the player has played
	 */
	public boolean getPlayed() {
		return played;
	}

	/**
	 * set the score of the player
	 * 
	 * @param score is the tile to be set
	 */
	public void setScore(int score) {
		if (score < 0)
			throw new IllegalArgumentException();
		this.score = score;
	}

	/**
	 * 
	 * @return list of regular tiles
	 */
	public List<Tile> getRegularTiles() {
		return regularTiles;
	}

	/**
	 * 
	 * @return temporary moved tile to board
	 */
	public Tile getTempTile(){
		return movedTile;
	}
	
	/**
	 * 
	 * @param tile player moved tile
	 */
	public void setTempTile(Tile tile){
		this.movedTile=tile;
	}
	
	/**
	 * 
	 * @return temporary moved special tile to board
	 */
	public SpecialTile getTempSPTile(){
		return TempSPTile;
	}
	
	/**
	 * 
	 * @param spTile player moved special tile
	 */
	public void setTempSPTile(SpecialTile spTile){
		this.TempSPTile=spTile;
	}
	
	/**
	 * 
	 * @return score modifier
	 */
	public int getScoreModifier() {
		return scoreModifier;
	}

	/**
	 * 
	 * @return list of special tiles
	 */
	public Map<String,SpecialTile> getSpecialTiles() {
		return specialTiles;
	}

	/**
	 * set the special tiles as the list tile
	 * 
	 * @param tile is the tile to be set
	 */
	public void setSpecialTiles(Map<String,SpecialTile> tile) {
		if (tile == null)
			throw new NullPointerException();
		specialTiles = tile;
	}
	
	/**
	 * 
	 * @return bought special tile
	 */
	public SpecialTile getBourghtSPTile(){
		return bourghtSPTile;
	}
	
	/**
	 * 
	 * @param bourghtSPTile special tile bought
	 */
	public void setBroughtSPTile(SpecialTile bourghtSPTile){
		this.bourghtSPTile=bourghtSPTile;
	}

	/**
	 * set the regular tiles as the list tile
	 * 
	 * @param tile is the tile to be set
	 */
	public void setRegularTiles(List<Tile> tile) {
		if (tile == null)
			throw new NullPointerException();
		regularTiles = tile;
	}

	/**
	 * add regular tile to list
	 * @param tile add regular tile
	 */
	public void addRegularTile(Tile tile) {
		regularTiles.add(tile);
	}

	/**
	 * addTile to tile list
	 * 
	 * @param t is the tile to be add
	 * 
	 * @param lst is the list to be added
	 */
	public void addTile(Tile t, List<Tile> lst) {
		if (t == null)
			throw new NullPointerException();
		else if (lst.size() >= MAX_TILES)
			throw new IllegalArgumentException();
		lst.add(t);
	}

	/**
	 * add square to square list
	 * 
	 * @param s
	 *            is the square to be added
	 * 
	 * @param list
	 *            is the list to be added
	 */
	public void addSquare(Square s, List<Square> list) {
		list.add(s);
	}

	/**
	 * get the number of current tiles of tile list
	 * 
	 * @param list
	 *            is the list to be find
	 * @return the number of tiles
	 */
	public int getTileNum(List<Tile> list) {
		if (list == null)
			throw new NullPointerException();
		return list.size();
	}

	/**
	 * 
	 * @return list of last moved tiles
	 */
	public List<Square> getLastMovedTile() {
		return lastMovedTiles;
	}

	/**
	 * reset the record of the last moved tiles
	 */
	public void resetLastMovedTile() {
		lastMovedTiles.clear();
	}

	/**
	 * remove the last placed tiles if challenge is acceptable
	 */
	public void removeLastMove() {
		for (Square s : lastMovedTiles) {
			s.setRegularTile(null);
		}
	}

	/**
	 * check the orientation of the moves,either along x or y
	 * 
	 * @return the orientation of the moves
	 */
	public int moveOrientation() {

		/*
		 * stores the valid new tiles orientation, either along x or y it's
		 * along x when it is 0 it's along y when it is 1 it's invalid if it is
		 * otherwise
		 */
		List<Integer> newTileOrientation = new ArrayList<Integer>();
		for (int i = 0; i < lastMovedTiles.size() - 1; i++) {
			Square firstTile = lastMovedTiles.get(i);
			Square secondTile = lastMovedTiles.get(i + 1);

			if (firstTile.getTileX() == secondTile.getTileX() && firstTile.getTileY() != secondTile.getTileY()) {
				newTileOrientation.add(0);
			} else if (firstTile.getTileX() != secondTile.getTileX() && firstTile.getTileY() == secondTile.getTileY()) {
				newTileOrientation.add(1);
			} else {
				newTileOrientation.add(-1);
			}
		}

		int orientationFlag = newTileOrientation.get(0);
		for (int j : newTileOrientation) {
			if (j != orientationFlag) {
				return -1;
			}
		}
		return orientationFlag;
	}

	/**
	 * check if the moves are valid
	 * 
	 * @return return true if valid
	 * @param board 
	 */
	public boolean validMove(Square[][] board) {
		// if there is only one new tile added, it is almost always valid
		if (lastMovedTiles.size() == 1) {
			return true;
		} else {
			int orientation = moveOrientation();
			if(orientation==-1){
				return false;
			}
			else if (orientation == 1) {

				// find out the range between new tiles in direction x
				int minX = lastMovedTiles.get(0).getTileX();
				int maxX = 0;
				for (Square s : lastMovedTiles) {
					if (s.getTileX() < minX)
						minX = s.getTileX();
					if (s.getTileX() > maxX)
						maxX = s.getTileX();
				}
				int currentY = lastMovedTiles.get(0).getTileY();
				// make sure there are no holes between xmin to xmax, if so
				// return false
				while (minX < maxX) {
					if (!board[minX][currentY].hasRegularTile())
						return false;
					minX++;
				}
				return true;
			} else if (orientation == 0) {

				// do the same thing for direction y
				int minY = lastMovedTiles.get(0).getTileY();
				int maxY = 0;

				for (Square s : lastMovedTiles) {
					if (s.getTileY() < minY)
						minY = s.getTileY();
					if (s.getTileY() > maxY)
						maxY = s.getTileY();
				}

				int currentX = lastMovedTiles.get(0).getTileX();
				while (minY < maxY) {
					if (!board[currentX][minY].hasRegularTile())
						return false;
					minY++;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * calculate score form the newly formedStrings
	 * 
	 * @return score of all words formed
	 */
	public int findScore() {
		int score = 0;
		int wordScale = 1;
		for (Square s : formedSquares) {
			if (s.getName().length() == 2 && s.getName().charAt(1) == 'W') {
				// use the largest word scale to scale the whole word
				int currentScale = Character.getNumericValue(s.getName().charAt(0));
				if (currentScale > wordScale)
					wordScale = currentScale;
			}

			// get the score of each square multiplying the value of tiles
			score += s.getValue() * s.getTile().getValue();
		}
		return score * wordScale * scoreModifier;
	}

	/**
	 * trigger special effect of each square
	 * 
	 * @param board
	 *            takes the board to trigger the effect
	 */
	public void triggerSPEffect(Square[][] board) {
		for (Square s : lastMovedTiles) {
			int currentX = s.getTileX();
			int currentY = s.getTileY();
			board[currentX][currentY].getSPTile().applyEffect(currentX, currentY, board, formedSquares, this);

			// after the special effect, remove the special tile of these moves
			board[currentX][currentY].setSPTile(null);
		}
	}

	/**
	 * find out all the possible words based on newly moved tiles
	 * 
	 * @param board
	 *            takes the board to trigger the effect
	 */
	public void findAllWords(Square[][] board) {
		formedStrings.clear();
		// check through all the newly placed tiles
		for (Square s : lastMovedTiles) {
			int i = s.getTileX();
			int j = s.getTileY();
			// find the horizontal beginning of a possible word
			while (i >= 0 && board[i][j].hasRegularTile()) {
				i--;
			}
			i++;

			String formedStringX = "";
			while (i < LENGTH && board[i][j].hasRegularTile()) {

				// add the string to the arraylist formedStrings
				formedStringX+=board[i][j].getTile().getName();

				// add the squares to the arraylist formedSquares
				if (!formedSquares.contains(board[i][j])) {
					formedSquares.add(board[i][j]);
				}
				i++;
			}
			if (formedStringX.length() > 1 && !formedStrings.contains(formedStringX))
				formedStrings.add(formedStringX);
			i = s.getTileX();
			// find the vertical beginning of a possible word
			while (j < LENGTH && board[i][j].hasRegularTile()) {
				j++;
			}

			j--;
			String formedStringY = "";
			while (j >= 0 && board[i][j].hasRegularTile()) {
				// add the string to the arraylist formedStrings
				formedStringY += board[i][j].getTile().getName();
				// add the squares to the arraylist formedSquares
				if (!formedSquares.contains(board[i][j])) {
					formedSquares.add(board[i][j]);
				}
				j--;
			}
			if (formedStringY.length() > 1 && !formedStrings.contains(formedStringY))
				formedStrings.add(formedStringY);
		}
	}
	

	/**
	 * 
	 * @return list of fromed Strings
	 */
	public List<String> getFormedStrings() {
		return formedStrings;
	}

	/**
	 * judge if words are valid by checking with dictionary
	 * 
	 * @param challengedPlayer
	 *            the player that receives the challenge
	 * @throws FileNotFoundException
	 * 
	 */
	public void challenge(Player challengedPlayer) throws FileNotFoundException {
		try{
		Scanner input = new Scanner(new File("words.txt"));
		int hasWordCount = 0;
		while (input.hasNext()) {
			String compareString=input.next();
			for (String s : challengedPlayer.getFormedStrings()) {
				if (compareString.equals(s))
					hasWordCount++;
			}
		}
		input.close();
		if (hasWordCount == challengedPlayer.getFormedStrings().size()) {
			challengedPlayer.setChallengeResult(4);// challenge invalid
			this.challengedStatus = 1;
		} else {
			challengedPlayer.setChallengeResult(3);//challenge valid, punish challenged player
			this.challengedStatus = 2;
		}
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}

	/**
	 * set the result as indicated
	 * @param result set play challenge result
	 */
	public void setChallengeResult(int result) {
		this.challengedStatus = result;
	}

	/**
	 * 
	 * @return challenged status
	 */
	public int getChallengeResult() {
		return challengedStatus;
	}

	/**
	 * change the score modifier, which could change the overall score
	 * 
	 * @param scoreModifier
	 *            takes the change of modifier
	 */
	public void setScoreModifier(int scoreModifier) {
		this.scoreModifier = scoreModifier;
	}

	@Override
	public String toString() {
		return name;
	}

}
