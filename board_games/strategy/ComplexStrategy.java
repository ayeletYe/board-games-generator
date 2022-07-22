package board_games.strategy;

import board_games.Board;
import board_games.Board.Square;
import board_games.Cell;
import board_games.logic.GameLogic;
import board_games.logic.GameLogic.Status;

/**
 * A complex strategy as played by a computer.
 * 
 * The computer tries to go over all possible moves in current board and 
 * to find the move that gives the best score.
 * This is a generic function for all games, as it's based on game logic API
 * that is same for all games.
 */
public class ComplexStrategy extends Strategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameLogic gameLogic;
	private Square player;
	
	public ComplexStrategy(GameLogic gameLogic, Square player) {
		this.gameLogic = gameLogic;
		this.player = player;
	}
	
	@Override
	public Cell getPlayerMove(Board gameBoard)
	{
		Cell bestPoint = null;
		int bestScore = Integer.MIN_VALUE;
		
		for (Board.CellValue cell : gameBoard) {
			Cell p = cell.getCell();
			Status status;
			Board boardCopy;
			
			if (!gameLogic.isLegal(gameBoard, p))
				continue;
			
			boardCopy = new Board(gameBoard);
			
			gameLogic.updateBoard(boardCopy, p, player);
			
			status = gameLogic.getBoardStatus(boardCopy);
			if (status.getPlayerScore(player) > bestScore || bestPoint == null) {
				bestPoint = p;
				bestScore = status.getPlayerScore(player);
			}
		}

		return bestPoint;
	}

}
