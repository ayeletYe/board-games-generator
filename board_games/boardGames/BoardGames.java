package board_games.boardGames;

import java.io.Serializable;
import board_games.Board;
import board_games.Board.Square;
import board_games.Player;
import board_games.Cell;
import board_games.logic.GameLogic;

/**
 * Represents an abstract board game,
 * played by 2 players.
 * 
 * Multiple games which uses board can use this class,
 * with multiple common properties.
 */
public abstract class BoardGames implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	public static final int NUM_PLAYERS = 2;

	protected GameLogic gameLogic;
	Player[] players = new Player[NUM_PLAYERS];
	protected Board gameBoard;
	protected int whoseTurn = 0;
	
	/**
	 * Constructs a new board game
	 * 
	 * @param gameLogic
	 * 		The logic and the rule-set of the game
	 * @param players
	 * 		The 2 players of the game.
	 * @param row
	 * 		Number of rows in this board game
	 * @param column
	 * 		Number of column in this board game
	 */
	public BoardGames(GameLogic gameLogic, Player[] players, int row, int column)
	{
		this.gameLogic = gameLogic;
		this.players = players;
		
		this.gameBoard = new Board(row, column);
	}
	
	/**
	 * Returns the current player which should play now
	 * 
	 * @return
	 * 		Current player
	 */
	public Player getCurrentPlayer()
	{
		return players[whoseTurn];
	}
	
	/**
	 * Get a player
	 * 
	 * @param playerIndex
	 * 		Index of the player
	 * @return
	 * 		Selected player
	 */
	public Player getPlayer(int playerIndex)
	{
		return players[playerIndex];
	}
	
	/**
	 * Returns the board of the game
	 * 
	 * @return
	 * 		Board of the game
	 */
	public Board getGameBoard()
	{
		return gameBoard;
	}
	
	/**
	 * Returns the index of the player that should play now
	 * 
	 * @return
	 * 		Index of the player that should play
	 */
	public int getWhoseTurn()
	{
		return whoseTurn;
	}
	
	/**
	 * Sets the index of the player that should play now
	 * 
	 * @param whoseTurn
	 * 		Index of the player that should play
	 */
	public void setWhoseTurn(int whoseTurn)
	{
		this.whoseTurn = whoseTurn;
	}
	
	/**
	 * Performs a single step of the player that should play now.
	 * 
	 * If current player gives a valid move then we update the board
	 * with the new point, and set the next turn to the other player.
	 * If the move is not valid - we will not apply the move on the board,
	 * and we will not set the next turn to the other player.
	 *
	 * @return
	 * 		True if the move was legal, false otherwise.
	 */
	public boolean performsGameStep()
	{
		Cell p = getCurrentPlayer().getStrategy().getPlayerMove(gameBoard);

		if (gameLogic.isLegal(gameBoard, p) == false)
			return false;
		
		gameLogic.updateBoard(gameBoard, p, getCurrentPlayer().getSquareType());
		
		if(whoseTurn == 0)
			whoseTurn = 1;
		else
			whoseTurn = 0;
		
		return true;
	}

	/**
	 * Returns current game status
	 * 
	 * @return
	 * 		Game status
	 */
	public GameLogic.Status getStatus()
	{
		return gameLogic.getBoardStatus(gameBoard);
	}
	
	/**
	 * Returns the path to the icon of a player.
	 * 
	 * @param player
	 * 		The player we want his icon
	 * @return
	 * 		Path to the icon
	 */
	public abstract String getPlayerIcon(Square player);
}
