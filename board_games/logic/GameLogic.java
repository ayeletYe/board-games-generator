package board_games.logic;

import java.io.Serializable;

import board_games.Board;
import board_games.Cell;
import board_games.Board.Square;

/**
 * Class that stores the logic of a game moves on the board,
 * and calculates the status of the board.
 */
public abstract class GameLogic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Checks if a given point is legal on the given board
	 * 
	 * @param board
	 *		Board of the game
	 * @param p
	 *		New point we want to put on the board
	 * @return
	 *		True if legal move, else false.
	 */
	public boolean isLegal(Board board, Cell p)
	{
		return (board.getCell(p) == Square.BLANK);
	}
	
	/**
	 * Updates the board with the new move.
	 * Only if the move is legal.
	 * 
	 * @param board
	 *		Board of the game
	 * @param p
	 *		New point we want to put on the board
	 * @param player
	 *		The player who puts the new point
	 */
	public void updateBoard(Board board, Cell p, Square player)
	{
		board.setCell(p.getRow(), p.getColumn(), player);
	}
	
	/**
	 * Gets board status
	 *
	 * @param board
	 *		Board of the game
	 * @return
	 *		Board status
	 */
	public abstract Status getBoardStatus(Board board);
	
	/**
	 * Contains status of a board
	 */
	public class Status {
		private boolean isEnded;
		private Square winner;
		private int[] playerScore;
		
		Status() {
			isEnded = false;
			winner = Square.BLANK;
			playerScore = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE};
		}

		/**
		 * Returns is the game ended
		 * 
		 * @return
		 * 		True if the game ended, false otherwise.
		 */
		public boolean isEnded()
		{
			return this.isEnded;
		}
		
		/**
		 * Returns the winner of the game if the game ended.
		 * Returns BLANK if the game ended with tie.
		 * 
		 * @return Winner
		 */
		public Square getWinner()
		{
			return winner;
		}
		
		/**
		 * Sets the winner of the game,
		 * the score of this player to maximal,
		 * and the score of the other player to minimal.
		 * 
		 * If game is tie then player is set to BLANK,
		 * and both scores are minimal.
		 * 
		 * @param player
		 *		The player who won
		 *
		 * @return
		 * 		This object
		 */
		public Status setWinner(Square player)
		{
			this.winner = player;
			this.isEnded = true;
			
			switch (player) {
			case OnePlayer:
				setPlayerScore(Square.OnePlayer, Integer.MAX_VALUE);
				setPlayerScore(Square.SecondPlayer, Integer.MIN_VALUE);
				break;
			case SecondPlayer:
				setPlayerScore(Square.OnePlayer, Integer.MIN_VALUE);
				setPlayerScore(Square.SecondPlayer, Integer.MAX_VALUE);
			case BLANK:
				setPlayerScore(Square.OnePlayer, Integer.MIN_VALUE);
				setPlayerScore(Square.SecondPlayer, Integer.MIN_VALUE);
			default:
				break;
			}
			
			return this;
		}
		
		/**
		 * Returns the calculated board score for a given user.
		 * 
		 * @param player
		 * 		Player to get score for
		 * 
		 * @return 
		 * 		Score for given player
		 */
		public int getPlayerScore(Square player)
		{
			switch (player) {
			case OnePlayer:
				return playerScore[0];
			case SecondPlayer:
				return playerScore[1];
			default:
				break;
			}
			
			return Integer.MIN_VALUE;
		}
		
		/**
		 * Sets the calculated board score for a given user.
		 * 
		 * @param player
		 * 		Player to set the score for
		 * @param score
		 * 		The score of the player
		 * 
		 * @return
		 * 		This object
		 */
		public Status setPlayerScore(Square player, int score)
		{
			switch (player) {
			case OnePlayer:
				playerScore[0] = score;
			case SecondPlayer:
				playerScore[1] = score;
			default:
				break;
			}
			
			return this;
		}
	}
}
