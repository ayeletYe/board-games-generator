package board_games.boardGames;

import board_games.Board;
import board_games.Board.Square;

/**
 * Common tools used by the various games
 */
public class BoardGamesUtils { 
	
	/**
	 * Checks if the board is full and no blank cells are left
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @return
	 * 		True if no blank cells, false otherwise.
	 */
	public static boolean isBoardFull(Board gameBoard)
	{
		for (Board.CellValue cell : gameBoard) {
			if (cell.getCellData() != Square.BLANK)
				return false;
		}

		return true;
	}
	
	
	/**
	 * Checks if a given row and column is valid for a given board
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @param row
	 * 		Row to check
	 * @param column
	 * 		Column to check
	 */
	public static void checkBounds(Board gameBoard, int row, int column)
	{
		if((row >= gameBoard.getRows() || row < 0) || (column >= gameBoard.getColumns() || column < 0))
			throw new ArrayIndexOutOfBoundsException();
	}
	
	/**
	 * Checks if starting from a given cell the next cells in row, 
	 * according to a given length, belongs to the same player.
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @param row
	 * 		Row of the starting cell
	 * @param column
	 * 		Column of the starting cell
	 * @param len
	 * 		Length of the match
	 * 
	 * @return
	 * 		The cell value of the winner if all equals, BLANK if not all equals.
	 */
	public static Square isEqualsRow(Board gameBoard, int row, int column, int len)
	{
		checkBounds(gameBoard, row, column);
		
		boolean equal = true;
		
		if ((column + len - 1) >= gameBoard.getColumns())
			return Square.BLANK;
		
		for(int i = 1; i < len; i++)
		{
			if(gameBoard.getCell(row, column) != gameBoard.getCell(row, (column + i)))
			{
				equal = false;
				break;
			}
		}	
		if(equal) 
			return gameBoard.getCell(row, column);
		
		return Square.BLANK;
	}
	
	/**
	 * Checks if starting from a given cell the next cells in column, 
	 * according to a given length, belongs to the same player.
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @param row
	 * 		Row of the starting cell
	 * @param column
	 * 		Column of the starting cell
	 * @param len
	 * 		Length of the match
	 * 
	 * @return
	 * 		The cell value of the winner if all equals, BLANK if not all equals.
	 */
	public static Square isEqualsColumn(Board gameBoard, int row, int column, int len)
	{
		checkBounds(gameBoard, row, column);
		
		boolean equal = true;
		
		if ((row + len - 1) >= gameBoard.getRows())
			return Square.BLANK;
		
		for(int i = 1; i < len; i++)
		{
			if(gameBoard.getCell(row, column) != gameBoard.getCell((row + i), column))
			{
				equal = false;
				break;
			}
		}	
		if(equal) 
			return gameBoard.getCell(row, column);
		
		return Square.BLANK;
	}

	/**
	 * Checks if starting from a given cell the next cells in a main diagonal, 
	 * according to a given length, belongs to the same player.
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @param row
	 * 		Row of the starting cell
	 * @param column
	 * 		Column of the starting cell
	 * @param len
	 * 		Length of the match
	 * 
	 * @return
	 * 		The cell value of the winner if all equals, BLANK if not all equals.
	 */
	public static Square isEqualsMainDiagonal(Board gameBoard, int row, int column, int len)
	{
		checkBounds(gameBoard, row, column);
		
		boolean equal = true;
		
		if ((column + len - 1) >= gameBoard.getColumns())
			return Square.BLANK;
		
		if ((row + len - 1) >= gameBoard.getRows())
			return Square.BLANK;
		
		for(int i = 1; i < len; i++)
		{
			if(gameBoard.getCell(row, column) != gameBoard.getCell((row + i), (column + i)))
			{
				equal = false;
				break;
			}
		}	
		if(equal) 
			return gameBoard.getCell(row, column);
		
		return Square.BLANK;
	}
	

	/**
	 * Checks if starting from a given cell the next cells in a secondary diagonal, 
	 * according to a given length, belongs to the same player.
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @param row
	 * 		Row of the starting cell
	 * @param column
	 * 		Column of the starting cell
	 * @param len
	 * 		Length of the match
	 * 
	 * @return
	 * 		The cell value of the winner if all equals, BLANK if not all equals.
	 */
	public static Square isEqualsSecondaryDiagonal(Board gameBoard, int row, int column, int len)
	{
		checkBounds(gameBoard, row, column);
		
		boolean equal = true;
		
		if ((column + len - 1) >= gameBoard.getColumns())
			return Square.BLANK;
		
		if ((row - len + 1) < 0)
			return Square.BLANK;
		
		for(int i = 1; i < len; i++)
		{
			if(gameBoard.getCell(row, column) != gameBoard.getCell((row - i), (column + i)))
			{
				equal = false;
				break;
			}
		}	
		if(equal) 
			return gameBoard.getCell(row, column);
		
		return Square.BLANK;
	}
	
	/**
	 * Checks if starting from a given cell the next cells in any of 4 direction, 
	 * according to a given length, belongs to the same player.
	 * 
	 * @param gameBoard
	 * 		Board of a game
	 * @param len
	 * 		Length of the match
	 * 
	 * @return
	 * 		The cell value of the winner if all equals, BLANK if not all equals.
	 */
	public static Square hasWinningLen(Board gameBoard, int len)
	{
		for (Board.CellValue cell : gameBoard) {
			Square win;
			
			win = BoardGamesUtils.isEqualsRow(gameBoard, cell.getCellRow(), cell.getCellColumn(), len);
			if (win != Square.BLANK)
				return win;

			win = BoardGamesUtils.isEqualsColumn(gameBoard, cell.getCellRow(), cell.getCellColumn(), len);
			if (win != Square.BLANK)
				return win;

			win = BoardGamesUtils.isEqualsMainDiagonal(gameBoard, cell.getCellRow(), cell.getCellColumn(), len);
			if (win != Square.BLANK)
				return win;

			win = BoardGamesUtils.isEqualsSecondaryDiagonal(gameBoard, cell.getCellRow(), cell.getCellColumn(), len);
			if (win != Square.BLANK)
				return win;
		}
		
		return Square.BLANK;
	}

}
