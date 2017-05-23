package scrabbleGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import scrabbleGame.Game;
import scrabbleGame.GameChangeMediator;
import scrabbleGame.Player;
import scrabbleGame.Square;
import tile.NegateTile;
import tile.BoomTile;
import tile.ReverseOrderTile;
import tile.ZeroTile;
import tile.RotateTile;

/**
 * A panel that displays the Scrabble board and current player label, score, and
 * tiles
 */
@SuppressWarnings("serial")
public class ScrabblePanel extends JPanel implements GameChangeMediator {

	private final Game game;
	private final JButton[][] squares;
	private final JTextField playerName, challengePlayerName;
	private final JLabel currentPlayerLabel, informationLabel, addPlayerLabel;
	private final JButton[] regularTiles, moveButtons;
	private final JButton specialTiles, addPlayerButton, startGameButton, 
	challengeButton, nextPlayerButton,
	BoomTileButton, NegateTileButtion, ReverseOrderTileButtion, ZeroTileButton,RotateDownButton;
	private static final int MAX_TILE = 7;
	private static final int MOVE_BUTTONS = 4;
	private int specialTileNum;
	private final JFrame chellangeFrame;

	/**
	 * Creates a new panel for displaying the game.
	 * 
	 * @param g
	 *            Scrabble game interface.
	 */
	public ScrabblePanel(final Game g) {
		game = g;
		specialTileNum=0;
		currentPlayerLabel = new JLabel();
		chellangeFrame=new JFrame();
		BoomTileButton=new JButton();
		NegateTileButtion=new JButton();
		ReverseOrderTileButtion=new JButton();
		ZeroTileButton=new JButton();
		informationLabel = new JLabel();
		challengeButton = new JButton();
		nextPlayerButton = new JButton();
		RotateDownButton= new JButton();
		addPlayerLabel = new JLabel("type in more than 2 player names to start game");
		addPlayerButton = new JButton("add player");
		startGameButton = new JButton("start game");

		playerName = new JTextField("to start game, add players here then hit start");
		challengePlayerName = new JTextField("add challenger names after move");
		squares = new JButton[game.getLength()][game.getLength()];

		regularTiles = new JButton[MAX_TILE];
		specialTiles = new JButton();
		moveButtons = new JButton[MOVE_BUTTONS];

		game.addGameChangeListener(this);
		playerName.selectAll();
		setLayout(new BorderLayout());
		add(createBoardPanel(), BorderLayout.CENTER);
		add(createTilePanel(), BorderLayout.SOUTH);
		add(controlPanel(), BorderLayout.WEST);
		adjustMoveButtons(false);
		startGameButton.setEnabled(false);
		adjustSpecialTiles(false);
	}

	private JPanel createBoardPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(game.getLength(), game.getLength()));

		// Create all of the squares and display them.
		for (int row = 0; row < game.getLength(); row++) {
			for (int col = 0; col < game.getLength(); col++) {
				squares[row][col] = new JButton();
				if (!game.getBoard()[row][col].getName().equals("regular")) {
					squares[row][col].setText(game.getBoard()[row][col].getName());
					squares[row][col].setBackground(Color.yellow);
				}
				squares[row][col].addActionListener(new SquareListener(row, col, game));
				panel.add(squares[row][col]);
			}
		}
		return panel;
	}

	private JPanel createTilePanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		for (int i = 0; i < MAX_TILE; i++) {
			regularTiles[i] = new JButton();
			panel.add(regularTiles[i]);
			regularTiles[i].addActionListener(new TileListener(i, game));
		}
		panel.add(specialTiles);
		specialTiles.addActionListener(new TileListener(7,game));
		return panel;
	}

	private JPanel controlPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(gameInformationPanel(), BorderLayout.SOUTH);
		panel.add(moveButtonsPanel(), BorderLayout.CENTER);
		panel.add(addPlayerPanel(), BorderLayout.NORTH);
		return panel;
	}

	private JPanel moveButtonsPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		moveButtons[0] = new JButton();
		moveButtons[0].setText("Confirm Move");
		moveButtons[0].addActionListener(a3 -> {
			// Confirm Move Button is activated
			if(currentPlayer().getLastMovedTile().size()>0){
			if (!currentPlayer().validMove(game.getBoard()) || !checkTilesConnected()) {
				informationLabel.setText("Move invalid" + " do it again");
				cancelMove();
			} else {
				informationLabel.setText(currentPlayer().getName() + " confirmed the move");
				adjustChallengeButtons(true);
				adjustMoveButtons(false);
				adjustSPTile(false);
			}
			}else{
				informationLabel.setText("Place tiles on board please");
			}
		});
		panel.add(moveButtons[0], BorderLayout.WEST);

		moveButtons[1] = new JButton();
		moveButtons[1].setText("Cancel Move");
		moveButtons[1].addActionListener(a3 -> {
			// cancel move is activated
			informationLabel.setText(currentPlayer().getName() + "cancelled the move");
			cancelMove();
		});
		panel.add(moveButtons[1], BorderLayout.CENTER);

		JPanel specialPanel=new JPanel();
		specialPanel.setLayout(new BorderLayout());
		moveButtons[2] = new JButton();
		moveButtons[2].setText("Confirm buying a Special Tile");
		specialPanel.add(moveButtons[2], BorderLayout.NORTH);
		specialPanel.add(specialTilesPanel(), BorderLayout.CENTER);
		panel.add(specialPanel, BorderLayout.EAST);
		moveButtons[2].addActionListener(a3 -> {
			// show special tiles
			if(specialTileNum==0 && currentPlayer().getScore()-30>=0){
				if(currentPlayer().getSpecialTiles().containsValue(new BoomTile())){
					informationLabel.setText("You had the same special tile already!");
					return;
				}
				currentPlayer().setBroughtSPTile(new BoomTile());
				currentPlayer().setScore(currentPlayer().getScore()-30);
				updateScore();
			}else if(specialTileNum==1 && currentPlayer().getScore()-20>=0){
				if(currentPlayer().getSpecialTiles().containsValue(new NegateTile())){
					informationLabel.setText("You had the same special tile already!");
					return;
				}
				currentPlayer().setBroughtSPTile(new NegateTile());
				currentPlayer().setScore(currentPlayer().getScore()-20);
				updateScore();
			}else if(specialTileNum==2 && currentPlayer().getScore()-10>=0){
				if(currentPlayer().getSpecialTiles().containsValue(new ReverseOrderTile())){
					informationLabel.setText("You had the same special tile already!");
					return;
				}
				currentPlayer().setBroughtSPTile(new ReverseOrderTile());
				currentPlayer().setScore(currentPlayer().getScore()-10);
				updateScore();
			}else if(specialTileNum==3 && currentPlayer().getScore()-40>=0){
				if(currentPlayer().getSpecialTiles().containsValue(new ZeroTile())){
					informationLabel.setText("You had the same special tile already!");
					return;
				}
				currentPlayer().setBroughtSPTile(new ZeroTile());
				currentPlayer().setScore(currentPlayer().getScore()-40);
				updateScore();
			}else if(specialTileNum==4 && currentPlayer().getScore()-20>=0){
				if(currentPlayer().getSpecialTiles().containsValue(new RotateTile())){
					informationLabel.setText("You had the same special tile already!");
					return;
				}
				currentPlayer().setBroughtSPTile(new RotateTile()); 
				currentPlayer().setScore(currentPlayer().getScore()-20);
				updateScore();
				
			}else{
				informationLabel.setText("Not enough score or "
						+ "select a special tile then confirm");
				specialTileNum=0;
			}
			displaySPTile();
		});
		
		moveButtons[3] = new JButton();
		moveButtons[3].setText("Shuffle tiles/Pass ");
		panel.add(moveButtons[3], BorderLayout.NORTH);
		moveButtons[3].addActionListener(a3 -> {
			// randomly give the player new tiles and pass the current player
			informationLabel.setText(currentPlayer().getName() + "passed the round, " + "tiles are switched");
			game.getAllRegularTiles().addAll(currentPlayer().getRegularTiles());
			currentPlayer().getRegularTiles().clear();
			if(game.fillRegularTile(currentPlayer(), game.getMaxRegularTile())){
				endGame();
			}
			moveToNextPlayer();
			renewPlayerTiles();
			clearMovedTiles();
			showRegularTile();
			adjustSPTile(true);
		});

		final JPanel panelChallenge = new JPanel();
		panelChallenge.setLayout(new BorderLayout());
		panelChallenge.add(challengePlayerName, BorderLayout.NORTH);
		challengePlayerName.selectAll();

		challengeButton.setText("Challenge!");
		challengeButton.addActionListener(l -> {
			// add challenge function

			informationLabel.setText(currentPlayer().getFormedStrings().toString());
			Player challenger=game.getPlayer(challengePlayerName.getText());
			if(challenger==null){
				informationLabel.setText("Please refer to information on top and add a valid player name");
				challengePlayerName.setText("Wrong name!");
			}else if(challenger.getName().equals(currentPlayer().getName())){
				informationLabel.setText("OMG don't challenge yourself!");
				challengePlayerName.setText("Wrong name!");
			}else{

				currentPlayer().findAllWords(game.getBoard());

				try{
				challenger.challenge(currentPlayer());
				}catch (FileNotFoundException e){
					informationLabel.setText("Error checking the dictionary, please use human power to figure it out");
				}
			
			if(currentPlayer().getChallengeResult()==4){
				JOptionPane.showMessageDialog(chellangeFrame, "challenge invalid, challenger "+ challenger.getName()+ " punished");
				if(triggerEffect()){
				updateBoardAferEffect();
			
				}
				moveToNextPlayer();
				clearMovedTiles();
				adjustChallengeButtons(false);
				adjustSPTile(true);
			}else if(currentPlayer().getChallengeResult()==3){
				JOptionPane.showMessageDialog(chellangeFrame, "challenge valid, challenged player "+ currentPlayer().getName()+ " punished");
				cancelMove();
				moveToNextPlayer();
				renewPlayerTiles();
				clearMovedTiles();
				showRegularTile();
				adjustSPTile(true);
				adjustChallengeButtons(false);
			}
			}
		});

		panelChallenge.add(challengeButton, BorderLayout.CENTER);
		nextPlayerButton.setText("Next Player");
		nextPlayerButton.addActionListener(a5 -> {
			// move to next player
			if(triggerEffect()){
			updateBoardAferEffect();
			
			}
			moveToNextPlayer();
			clearMovedTiles();
			renewPlayerTiles();
			showRegularTile();
			adjustSPTile(true);
			adjustChallengeButtons(false);
		});

		panelChallenge.add(nextPlayerButton, BorderLayout.SOUTH);
		nextPlayerButton.setEnabled(false);
		challengePlayerName.setEnabled(false);
		challengeButton.setEnabled(false);
		panel.add(panelChallenge, BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel specialTilesPanel(){
		JPanel panel= new JPanel();
		panel.setLayout(new BorderLayout());
		BoomTileButton.setText("Boom, 30");//specialTileNum =0
		NegateTileButtion.setText("Negate, 20");//specialTileNum =1
		ReverseOrderTileButtion.setText("Reverse, 10");//specialTileNum =2
		ZeroTileButton.setText("Zero, 40");//specialTileNum =3
		RotateDownButton.setText("Rotate, 20");//specialTileNum =4
		
		BoomTileButton.addActionListener(a->
		{
			specialTileNum=0;
			informationLabel.setText("Boom tile selected");
		}
				);
		NegateTileButtion.addActionListener(a->
		{
			specialTileNum=1;		
			informationLabel.setText("Negate tile selected");
		}
				);
		ReverseOrderTileButtion.addActionListener(a->
		{
			specialTileNum=2;	
			informationLabel.setText("Reverse tile selected");
		}		
				);
		ZeroTileButton.addActionListener(a->
		{
			specialTileNum=3;	
			informationLabel.setText("Zero tile selected");
		}		
				);
		RotateDownButton.addActionListener(a->
		{
			specialTileNum=4;	
			informationLabel.setText("Rotate tile selected");
		}		
				);
		
		final JPanel insidePanel2=new JPanel();
		final JPanel insidePanel=new JPanel();
		insidePanel.setLayout(new BorderLayout());
		insidePanel2.setLayout(new BorderLayout());
		
		insidePanel.add(BoomTileButton, BorderLayout.NORTH);
		insidePanel.add(NegateTileButtion, BorderLayout.SOUTH);
		insidePanel.add(insidePanel2,BorderLayout.CENTER);
		
		insidePanel2.add(ReverseOrderTileButtion,BorderLayout.NORTH);
		insidePanel2.add(ZeroTileButton, BorderLayout.CENTER);
		insidePanel2.add(RotateDownButton, BorderLayout.SOUTH);
		panel.add(insidePanel,BorderLayout.CENTER);
		return panel;
	}

	private JPanel addPlayerPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel.add(playerName, BorderLayout.NORTH);
		panel.add(addPlayerLabel, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);
		panel2.add(addPlayerButton, BorderLayout.NORTH);
		panel2.add(startGameButton, BorderLayout.SOUTH);

		addPlayerButton.addActionListener(a -> {
			playerNameActionPerformed();
		});

		playerName.addActionListener(a -> {
			playerNameActionPerformed();
		});

		startGameButton.addActionListener(e -> {
			// game start
			adjustSpecialTiles(true);
			game.setPassedRounds(0);
			game.setPlayerNum(0);
			game.iniPlayerTile();
			showRegularTile();
			addPlayerLabel.setText("Players are " + game.getAllPlayers().toString() + " Scores are " +"[0, 0]");
			playerName.setEnabled(false);
			addPlayerButton.setEnabled(false);
			startGameButton.setEnabled(false);
			adjustMoveButtons(true);
			currentPlayerLabel.setText("now player " + currentPlayer().getName() + " is playing, ");
		});
		return panel;
	}

	private JPanel gameInformationPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(currentPlayerLabel, BorderLayout.SOUTH);
		panel.add(informationLabel, BorderLayout.NORTH);
		currentPlayerLabel.setText("Player is");
		informationLabel.setText("Confirm_Move after move, Cancel_Move to replace tiles");
		return panel;
	}

	/**
	 * 
	 * @return current player
	 */
	private Player currentPlayer() {
		return game.getCurrentPlayer();
	}

	private void showRegularTile() {
		for (int i = 0; i < MAX_TILE; i++) {
			regularTiles[i].setText(currentPlayer().getRegularTiles().get(i).getName() + ", "
					+ currentPlayer().getRegularTiles().get(i).getValue());
		}
	}

	/**
	 * trigger special effect
	 * @return true if effect is triggered
	 */
	public boolean triggerEffect(){
		for(Square s: currentPlayer().getLastMovedTile()){
			if(s.getSPTile()!=null){
				s.getSPTile().applyEffect(s.getTileX(), s.getTileY(), game.getBoard(), currentPlayer().getLastMovedTile(), currentPlayer());
				informationLabel.setText("BANG!BANG!BANG! " + s.getSPTile().toString()+ " triggered");
				s.setSPTile(null);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * adjustMoveButtons enable or disable
	 * 
	 * @param s
	 */
	private void adjustMoveButtons(boolean s) {
		for (int i = 0; i < MOVE_BUTTONS; i++) {
			moveButtons[i].setEnabled(s);
		}
		BoomTileButton.setEnabled(s);
		NegateTileButtion.setEnabled(s);
		ReverseOrderTileButtion.setEnabled(s); 
		ZeroTileButton.setEnabled(s);
		RotateDownButton.setEnabled(s);
	}

	/**
	 * show special tile for player
	 */
	public void displaySPTile(){
		if(currentPlayer().getBourghtSPTile()!=null)
		specialTiles.setText(currentPlayer().getBourghtSPTile().toString());
	}
	
	/**
	 * adjustChallengeButtons enable or disable
	 * 
	 * @param s
	 */
	private void adjustChallengeButtons(boolean s) {
		challengePlayerName.setEnabled(s);
		challengeButton.setEnabled(s);
		nextPlayerButton.setEnabled(s);
	}

	/**
	 * adjust special tiles display status
	 * @param s
	 */
	private void adjustSpecialTiles(boolean s){
		BoomTileButton.setEnabled(s);
		NegateTileButtion.setEnabled(s);
		ReverseOrderTileButtion.setEnabled(s);
		ZeroTileButton.setEnabled(s);
		RotateDownButton.setEnabled(s);
	}
	
	/**
	 * give the game to next player
	 */
	private void moveToNextPlayer() {
		game.setPassedRounds(game.getPassedRounds()+1);
		//set temp tiles as null
		currentPlayer().setTempSPTile(null);
		currentPlayer().setTempTile(null);
		//find score
		if(currentPlayer().getChallengeResult()!=3){
		int score = currentPlayer().findScore();
		currentPlayer().setScore(currentPlayer().getScore() + score);
		informationLabel.setText(currentPlayer().getName() + " score + " + score + ", moved to next player");
		String allScores = "";
		for (Player p : game.getAllPlayers()) {
			allScores += p.getScore() + ", ";
		}
		String allScoreCorrectFormat = allScores.substring(0, allScores.length() - 2);
		addPlayerLabel.setText(
				"Players are " + game.getAllPlayers().toString() + " Scores are [" + allScoreCorrectFormat + "]");
		}
		
		//move to next player
		int next = (game.getPlayerNum() + 1) % game.getAllPlayers().size();
		game.setPlayerNum(next);
		if(currentPlayer().getChallengeResult()==1){
			//challenger, invalid move to next one
			currentPlayer().setChallengeResult(0);
			next=(next+1) % game.getAllPlayers().size();
			game.setPlayerNum(next);
			
		} else if(currentPlayer().getChallengeResult() ==4){
			//being challenged, challenge is valid, needs to be punished
			currentPlayer().setChallengeResult(0);
			cancelMove();
			next=(next+1) % game.getAllPlayers().size();
			game.setPlayerNum(next);
		}
		
		currentPlayerLabel.setText("now " + currentPlayer().getName() + " is playing, currnet round is " + game.getPassedRounds());

		//clear tiles
		showRegularTile();
		adjustMoveButtons(true);
		
		for (int row = 0; row < game.getLength(); row++) {
			for (int col = 0; col < game.getLength(); col++) {
				squares[row][col].setForeground(Color.black);
			}
		}
	}

	/**
	 * update the board after the rotate or boom effect
	 */
	private void updateBoardAferEffect(){
		
		for (int row = 0; row < game.getLength(); row++) {
			for (int col = 0; col < game.getLength(); col++) {
				if(game.getSquare(row, col).getTile()!=null && game.getSquare(row, col).getSPTile()==null ){
					squares[row][col].setText(""+game.getBoard()[row][col].getTile().getName());
				}else if(game.getSquare(row, col).getTile()!=null && game.getSquare(row, col).getSPTile()!=null){
					squares[row][col].setText(game.getSquare(row, col).getSPTile()+", "+game.getBoard()[row][col].getTile().getName());
				}else if (!game.getBoard()[row][col].getName().equals("regular")) {
					squares[row][col].setText(game.getSquare(row,col).getName());
					squares[row][col].setBackground(Color.yellow);
				}
			}
		}
	}
	

	
	/**
	 * update score
	 */
	private void updateScore(){
		String allScores = "";
		for (Player p : game.getAllPlayers()) {
			allScores += p.getScore() + ", ";
		}
		String allScoreCorrectFormat = allScores.substring(0, allScores.length() - 2);
		addPlayerLabel.setText(
				"Players are " + game.getAllPlayers().toString() + " Scores are [" + allScoreCorrectFormat + "]");
		}
	
	/**
	 * cancel the current move
	 */
	private void cancelMove() {

		showRegularTile();
		for (int i = 0; i < currentPlayer().getLastMovedTile().size(); i++) {
			int x = currentPlayer().getLastMovedTile().get(i).getTileX();
			int y = currentPlayer().getLastMovedTile().get(i).getTileY();
			squares[x][y].setForeground(Color.black);
			game.getSquare(x, y).setRegularTile(null);
			game.getSquare(x, y).setSPTile(null);
			if (!game.getBoard()[x][y].getName().equals("regular")) {
				squares[x][y].setText(game.getBoard()[x][y].getName());
			} else {
				squares[x][y].setText("");
			}
		}
		clearMovedTiles();
	}

	/**
	 * dispaly special tiles on board for current player
	 * @param s true display current player's special tile, false, don't display
	 */
	private void adjustSPTile(boolean s){
		for (int row = 0; row < game.getLength(); row++) {
			for (int col = 0; col < game.getLength(); col++) {
				if(currentPlayer().getSpecialTiles().containsKey(currentPlayer().getName())&&
						currentPlayer().getSpecialTiles().containsValue(game.getSquare(row, col).getSPTile())){
					if(s){
						if(game.getSquare(row, col).getTile()!=null){
					squares[row][col].setText(game.getSquare(row, col).getSPTile().toString().substring(0, 2)+", "+
							game.getSquare(row, col).getTile().getName());
						}else{
							squares[row][col].setText(game.getSquare(row, col).getSPTile().toString().substring(0, 2));
						}
					}else if(!s){
						if(game.getSquare(row, col).getTile()!=null)
						squares[row][col].setText(""+game.getSquare(row, col).getTile().getName());
						if(game.getSquare(row, col).getTile()==null)
							squares[row][col].setText("");	
					}
				}
			
			}
		}
	}
	
	/**
	 * clear moved tiles
	 */
	private void renewPlayerTiles(){
		for (Square s : currentPlayer().getLastMovedTile()) {
			currentPlayer().getRegularTiles().remove(s.getTile());
		}
		if(game.fillRegularTile(currentPlayer(), game.getMaxRegularTile() - currentPlayer().getLastMovedTile().size()))
		{
		//game ends
			endGame();

		}

	}
	
	/**
	 * 
	 */
	private void endGame(){
		this.setEnabled(false);
		String winnerName="";
		int tempScore=0;
		for(Player p: game.getAllPlayers()){
			if(p.getScore()>tempScore){
				tempScore=p.getScore();
				winnerName=p.getName();
			}
		}
		JOptionPane.showMessageDialog(chellangeFrame,"game ended winner is " +winnerName);
		
	}
	
	/**
	 * adding players listener
	 */
	private void playerNameActionPerformed() {
		game.getAllPlayers().add(new Player(playerName.getText()));
		addPlayerLabel
				.setText(playerName.getText() + " added, there are " + game.getAllPlayers().size() + " players now");
		if(game.getAllPlayers().size()>1){
			startGameButton.setEnabled(true);
		}
	}

	/**
	 * check if newly put tiles are connected to old tiles
	 * @return true if it is connect, move is valid in this case
	 */
	private boolean checkTilesConnected(){
		boolean firstMove=false;
		if(game.getPassedRounds()==0){
				for(Square s: currentPlayer().getLastMovedTile()){
				if(s.getTileX()==7 && s.getTileX()==7)
					firstMove=true;
			}
		}else{
				for (int row = 0; row < game.getLength(); row++) {
					for (int col = 0; col < game.getLength(); col++) {
						if(!game.getSquare(row, col).hasRegularTile()){
							firstMove=true;
						}
					}
					}
				}
		boolean move=false;
		for(Square s: currentPlayer().getLastMovedTile()){
			int currentX=s.getTileX();
			int currentY=s.getTileY();

			boolean validMove1=false;
			boolean validMove11=false;
			boolean validMove2=false;
			boolean validMove22=false;
			boolean validMove3=false;
			boolean validMove4=false;
			boolean validMove33=false;
			boolean validMove44=false;
			
			if(currentX+1>14){
				validMove1=true;
				validMove11=true;
			}else{
			 validMove1=game.getSquare(currentX+1, currentY).hasRegularTile(); 
			 validMove11=!currentPlayer().getLastMovedTile().contains(game.getSquare(currentX+1, currentY));
			}
			
			if(currentX-1<=0){
				validMove2=true;
				validMove22=true;
			}else{
			 validMove2=game.getSquare(currentX-1, currentY).hasRegularTile(); 
			 validMove22=!currentPlayer().getLastMovedTile().contains(game.getSquare(currentX-1, currentY));
			}
			if(currentY+1>14){
				validMove3=true;
				validMove33=true;
			}else{
			 validMove3=game.getSquare(currentX, currentY+1).hasRegularTile(); 
			 validMove33=!currentPlayer().getLastMovedTile().contains(game.getSquare(currentX, currentY+1));
			}
			if(currentY-1<=0){
				validMove4=true;
				validMove44=true;
			}else{
			 validMove4=game.getSquare(currentX, currentY-1).hasRegularTile(); 
			 validMove44=!currentPlayer().getLastMovedTile().contains(game.getSquare(currentX, currentY-1));
			}

			//if one of the new tile is connected to old tile, valid, or else invalid
			if((validMove1 && validMove11) ||(validMove2 && validMove22) || (validMove3 && validMove33) || (validMove4 && validMove44)){
				move=true;
			}
		}
		return move || firstMove;
	}
	
	/**
	 * clear moved tiles and squares
	 * 
	 * @param p
	 *            player
	 */
	private void clearMovedTiles() {
		currentPlayer().getFormedStrings().clear();
		currentPlayer().getLastMovedTile().clear();
		currentPlayer().getFromedSquare().clear();
	}

	@Override
	public void squareChanged(int row, int col) {
		if( currentPlayer().getTempTile()!=null){
		squares[row][col].setText("" + currentPlayer().getTempTile().getName());
		squares[row][col].setForeground(Color.red);

		}else if(currentPlayer().getTempSPTile()!=null) {
			squares[row][col].setText(""+currentPlayer().getTempSPTile().toString().substring(0, 2));
			squares[row][col].setForeground(Color.red);
		}
	}

	@Override
	public void regularTileChanged(int tilePosition) {
		if(tilePosition!=7){
		regularTiles[tilePosition].setText("");
		}else{
			specialTiles.setText("");
		}
			
	}
}
