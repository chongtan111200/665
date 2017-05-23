package scrabbleGUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import scrabbleGame.Game;


/**
 * Starts and displays the Scrabble GUI. 
 */
public class ScrabbleMain {

  private static final String GAME_NAME = "Scrabble";

  /**
   * Main method that creates and displays the Scrabble GUI.
   *
   * @param args
   *          Command line arguments (ignored).
   */
  public static void main(final String[] args) {
    SwingUtilities.invokeLater(ScrabbleMain::createAndShowGameBoard);
  }

  private static void createAndShowGameBoard() {
    // Create and set-up the window.
    final JFrame frame = new JFrame(GAME_NAME);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    final Game game = new Game();

    // Create and set up the content pane.
    final ScrabblePanel gamePanel = new ScrabblePanel(game);
    gamePanel.setOpaque(true);
    frame.setContentPane(gamePanel);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }
}

