package board_games.strategy;

import java.util.Random;

import board_games.Board;
import board_games.Cell;
import board_games.logic.GameLogic;

/**
 * Simple computer strategy
 * 
 * Returns a random cell out of all legal points of the game.
 * Uses game logic to find which cells are legal for the given board.
 */
public class SimpleStrategy extends Strategy{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameLogic gameLogic;
	
	public SimpleStrategy(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	@Override
	public Cell getPlayerMove(Board gameBoard)
	{
		Cell[] blanks = new Cell[gameBoard.getRows() * gameBoard.getColumns()];
		
		int blanksCount = 0;
		
		for(Board.CellValue cell : gameBoard)
		{
			if(gameLogic.isLegal(gameBoard, cell.getCell()))
			{
				blanks[blanksCount] = cell.getCell();
				blanksCount++;
			}
			
		}
		Random random = new Random();
		
		int rand = random.nextInt(blanksCount);
		
		return blanks[rand];
	}
	
}


