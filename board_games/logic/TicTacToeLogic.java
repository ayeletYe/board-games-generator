package board_games.logic;

import board_games.Board;
import board_games.Board.Square;
import board_games.boardGames.BoardGamesUtils;


/**
 * Tic Tac Toe logic
 */
public class TicTacToeLogic extends GameLogic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WINNING_LEN = 3; 

	@Override
	public Status getBoardStatus(Board gameBoard) {
		Status status = new Status();
		int len = WINNING_LEN;
		Square win;
		
		win = BoardGamesUtils.hasWinningLen(gameBoard, len);
		if (win != Square.BLANK)
			return status.setWinner(win);
		
		/* No winner if board is full and we didn't find a winner above */
		if (BoardGamesUtils.isBoardFull(gameBoard))
			status.setWinner(Square.BLANK);
		
		return status;
	}

}
