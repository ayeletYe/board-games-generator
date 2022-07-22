package board_games.logic;

import board_games.Board;
import board_games.Board.Square;
import board_games.boardGames.BoardGamesUtils;
import board_games.Cell;

/**
 * Connect 4 logic
 */
public class ConnectFourLogic extends GameLogic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WINNING_LEN = 4; 
	
	@Override
	public boolean isLegal(Board board, Cell p) {
		if (super.isLegal(board, p) == false)
			return false;

		if ((p.getRow() + 1) == board.getRows())
			return true;
		
		Cell downPoint = new Cell(p.getRow() + 1, p.getColumn());

		return (board.getCell(downPoint) != Square.BLANK);
	}

	@Override
	public Status getBoardStatus(Board gameBoard) {
		int len = WINNING_LEN;
		Status status = new Status();
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
