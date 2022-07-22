package board_games.strategy;

import java.io.Serializable;

import board_games.Board;
import board_games.Cell;

/**
 * Strategy of the player in a game
 */
public abstract class Strategy implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Get the move of the player according to his strategy
	 *  
	 * @param gameBoard
	 * 		Current game board
	 * @return
	 * 		The move that the player wants to put on the board
	 */
	abstract public Cell getPlayerMove(Board gameBoard);
}
