package scrabbleGame;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import tile.RegularTile;
import tile.SpecialTile;
import tile.Tile;



/**
 * This is the class for board,15*15 squares
 * @author chong tian
 *
 */
public class Game {

	private static final int LENGTH = 15;
	private static final int PLAYER_TILE_NUM =7;
	private int tileNum;
	
	private int currentPlayer;
	private int passedRounds;
	private Square[][] board;

	private List<Player> allPlayers;
	private List<Tile> allRegularTiles;
	private final List<GameChangeMediator> gameChangeListeners;
	
	/**
	 * Construct the board using 2D array
	 * 	 * 
	 */
	public Game() {
		board = new Square[LENGTH][LENGTH];
		tileNum = 0;
		passedRounds=0;
		currentPlayer=0;
		allPlayers = new ArrayList<Player>();
		allRegularTiles = new ArrayList<Tile>();
		gameChangeListeners = new ArrayList<>();
		iniBoard();
		iniRegularTile();
	}

	private void iniBoard() {
		/**
		 * set up regular tiles and 2W tiles means the score of the word is
		 * doubled
		 */
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				if (i == j || i == 14 - j) {
					board[i][j] = new Square(1, "2W");
				} else {
					board[i][j] = new Square(1, "regular");
				}
			}
		}

		/**
		 * set up at specific locations 3W tiles means the score of the word is
		 * tripled 2L tiles means the score of the letter is doubled 3L tiles
		 * means the score of the letter is tripled
		 */
		board[0][0] = board[0][7] = board[0][14] = board[7][0] = board[7][14] = board[14][0] = board[14][7] = board[14][14] = new Square(
				1, "3W");

		board[0][3] = board[0][11] = board[2][6] = board[2][8] = board[3][0] = board[3][7] = board[3][14] = board[6][2] = board[6][6] = board[6][8] = board[6][12] = board[7][3] = board[7][11] = board[8][2] = board[8][6] = board[8][8] = board[8][12] = board[11][0] = board[11][7] = board[11][14] = board[12][6] = board[12][8] = board[14][3] = board[14][11] = new Square(
				2, "2L");

		board[1][5] = board[1][9] = board[5][0] = board[5][5] = board[5][9] = board[5][14] = board[9][0] = board[9][5] = board[9][9] = board[9][14] = board[13][5] = board[13][9] = new Square(
				3, "3L");
	}

	/**
	 * create the total regular tiles for the game
	 */
	private void iniRegularTile() {
		List<String> letters=new ArrayList<String>();
		List<int[]> number=new ArrayList<int[]>();
		
		letters.add("EAOTINRSLU");
		letters.add("DG");
		letters.add("CMBP");
		letters.add("HFWYV");
		letters.add("K");
		letters.add("JX");
		letters.add("QZ");
		letters.add("HFWYV");
		int[] number1PL = { 24, 16, 15, 15, 13, 13,13, 10, 7, 7 };
		int[] number2PL = { 8, 5 };
		int[] number3PL = { 6, 6, 4, 4 };
		int[] number4PL = { 5, 4, 4, 4, 3 };
		int[] number5PL = { 2 };
		int[] number8PL = { 2,2 };
		int[] number10PL = { 2,2 };
		number.add(number1PL);
		number.add(number2PL);
		number.add(number3PL);
		number.add(number4PL);
		number.add(number5PL);
		number.add(number8PL);
		number.add(number10PL);
		for(int i=0; i<5;i++){
			addRegularTileToBag(letters.get(i), number.get(i), i+1);
		}
		addRegularTileToBag(letters.get(5), number.get(5), 8);
		addRegularTileToBag(letters.get(6), number.get(6), 10);
	}

	/**
	 * add single regular tile
	 * @param letters the string
	 * @param number the number of such tile
	 * @param value the value of such tile
	 */
	public void addRegularTileToBag(String letters, int[] number, int value) {
		assert letters.length()==number.length;
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i]; j++) {
				allRegularTiles.add(new RegularTile(letters.charAt(i), value));
			}
		}
	}

	/**
	 * 
	 * @return all regular tiles
	 */
	public List<Tile> getAllRegularTiles(){
		return allRegularTiles;
	}
	
	/**
	 * initialize the players
	 */
	public void iniPlayerTile() {
		for (Player p : allPlayers) {
			fillRegularTile(p,PLAYER_TILE_NUM);
		}
	}

	/**
	 * 
	 * @return player's maximum tile number
	 */
	public int getMaxRegularTile(){
		return PLAYER_TILE_NUM;
	}

	/**
	 * 
	 * @return game change listener
	 */
	public List<GameChangeMediator> getGameChangeListener(){
		return gameChangeListeners;
	}
	/**
	 * 
	 * @param lisenter to the game change
	 */
	public void addGameChangeListener(GameChangeMediator lisenter){
		gameChangeListeners.add(lisenter);
	}
	
	/**
	 * get current player by index
	 * @return current player
	 */
	public Player getCurrentPlayer(){
		return allPlayers.get(currentPlayer);
	}
	
	/**
	 * 
	 * @return number of current players
	 */
	public int getPlayerNum(){
		return currentPlayer;
	}
	
	/**
	 * 
	 * @return passed rounds
	 */
	public int getPassedRounds(){
		return passedRounds;
	}
	
	/**
	 * 
	 * @param rounds set rounds
	 */
	public void setPassedRounds(int rounds){
		passedRounds=rounds;
	}
	
	/**
	 * 
	 * @param num set how many players game wants to add
	 */
	public void setPlayerNum(int num){
		currentPlayer=num;
	}
	
	/**
	 * 
	 * @return true if game ends
	 */
	public boolean checkGameEnd(){
		if(allRegularTiles.size()==0){
		return true;
		}
		return false;
	}
	
	/**
	 * move to the next player
	 * @return should go to next player or not
	 * @param player next player
	 */
	public boolean nextPlayer(Player player){
		if(player.getPlayed()){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * deal with challenge result
	 * @param player the player you want to check result with
	 */
	public void dealWithChallange(Player player){
		if(player.getChallengeResult()==1){
			//challenger needed to be punished
			player.setPlayed(true);
		}else if(player.getChallengeResult()==3){
			//challenged needed to be punished
			player.resetLastMovedTile();
		}
	}
	
	@Override
	public String toString() {
		return "" + tileNum;
	}

	/**
	 * add regular English letter tile to the board
	 * 
	 * @param x x coordinate
	 * 
	 * @param y y coordinate
	 * 
	 * @param tile type of tile placed
	 * @param lastMovedTiles the list contains the last moved tiles
	 */
	public void addRegularTile(int x, int y, Tile tile, List<Square> lastMovedTiles) {
		if (!board[x][y].hasRegularTile()) {
			board[x][y].setRegularTile(tile);
		}
		lastMovedTiles.add(board[x][y]);
		board[x][y].setTileX(x);
		board[x][y].setTileY(y);
	}

	/**
	 * 
	 * @return length of board
	 */
	public int getLength(){
		return LENGTH;
	}
	
	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return a square
	 */
	public Square getSquare(int x, int y){
		return board[x][y];
	}
	
	/**
	 * 
	 * @return board of the game
	 */
	public Square[][] getBoard(){
		return board;
	}
	
	/**
	 * add special effect tiles
	 * 
	 * @param x x coordinate
	 * 
	 * @param y y coordinate
	 * 
	 * @param spTile type of special tile placed
	 */
	public void addSPTile(int x, int y, SpecialTile spTile) {
		if (!board[x][y].hasSPTile()) {
			board[x][y].setSPTile(spTile);
		}
	}

	/**
	 * Return a player with the given name.
	 * @param name 
	 * @return same player
	 */
	public Player getPlayer(String name){
		if(name == null) return null;
		for(Player p : allPlayers){
			if(p.getName().equals(name)){
				return p;
			}
		}
		return null;
	}

	/**
	 * game is over if player doesn't have any regular tiles and no more tiles available
	 * @return true if it is over
	 */
	public boolean isOver() {
		for(Player s: allPlayers){
			if(allRegularTiles.size()==0 && s.getRegularTiles().size()==0)
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return list of all players
	 */
	public List<Player> getAllPlayers(){
		return allPlayers;
	}
	

	/**
	 * get the winners
	 * @return the list of winners
	 */
	public List<Player> getWinner(){
		List<Player> winners = new ArrayList<Player>();
		winners.add(allPlayers.get(0));
		int max = allPlayers.get(0).getScore();
		for(int i=1; i<allPlayers.size(); i++){
			Player player  = allPlayers.get(i);
			if(player.getScore()>max){
				winners.clear();
				winners.add(player);
				max = player.getScore();
			}
			else if(player.getScore()==max){
				winners.add(player);
			}
		}
		return winners;
	}
	
	/**
	 * give player certain regular tiles
	 * @param player current player
	 * @param fillNum the number of tiles that you want to give
	 * @return true if game ends
	 */
	public boolean fillRegularTile(Player player,int fillNum) {
		if(checkGameEnd()){
			return true;
		}
		Collections.shuffle(allRegularTiles);
		Iterator<Tile> iter = allRegularTiles.iterator();

		for(int i=0;i<fillNum;i++){
			if(iter.hasNext()){
				Tile tile = iter.next();
				iter.remove();
				player.addRegularTile(tile);
			}
		}
		return false;
	}


}
