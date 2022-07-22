package board_games.logic;

import board_games.Board;
import board_games.Board.Square;
import board_games.Cell;

public class ReversiLogic extends GameLogic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Updates a row starting from a given cell,
	 * if there are cells that needs to change color to current player color.
	 * According to reversi rules.
	 * 
	 * @param gameBoard
	 * 		Board of the game
	 * @param p
	 * 		Cell to begin from
	 * @param color
	 * 		Color of current player
	 */
	private void updateRow(Board gameBoard, Cell p, Square color)
	{
		int row = p.getRow();
		
		int column = p.getColumn();
		
		int numOfColumns = gameBoard.getColumns();
		Square rNeighbor = null;
		Square lNeighbor = null;
		
		int index, index2;

		if ((column + 1) < gameBoard.getColumns())
			rNeighbor = gameBoard.getCell(row, (column + 1));
		
		if ((column - 1) >= 0)
			lNeighbor = gameBoard.getCell(row, (column - 1));
		
		if((column < numOfColumns - 2) && (rNeighbor != null && rNeighbor != color && rNeighbor != Square.BLANK))
		{
			for(index = column + 2; index < numOfColumns; index++)
			{
				if(gameBoard.getCell(row, index) == Square.BLANK)
					break;
				
				if(gameBoard.getCell(row, index) == color)
				{
					for(index2 = column + 1; index2 < index; index2++)
						gameBoard.setCell(row, index2, color);
					
					break;
				}
			}
		}
		if((column > 1) && (lNeighbor != null && lNeighbor != color && lNeighbor != Square.BLANK))
		{
			for(index = column - 2; index >= 0; index--)
			{
				if(gameBoard.getCell(row, index) == Square.BLANK)
					break;
				
				if(gameBoard.getCell(row, index) == color)
				{
					for(index2 = column - 1; index2 > index; index2--)
					
						gameBoard.setCell(row, index2, color);
					
					break;
				}
			}
		}
	}
	
	/**
	 * Updates a column starting from a given cell,
	 * if there are cells that needs to change color to current player color.
	 * According to reversi rules.
	 * 
	 * @param gameBoard
	 * 		Board of the game
	 * @param p
	 * 		Cell to begin from
	 * @param color
	 * 		Color of current player
	 */
	private void updateColumn(Board gameBoard, Cell p, Square color)
	{
		int row = p.getRow();
		
		int column = p.getColumn();
		
		int numOfRows = gameBoard.getRows();
		
		Square uNeighbor = null;
		
		Square dNeighbor = null;
		
		int index, index2;
		
		if ((row + 1) < gameBoard.getRows())
			dNeighbor = gameBoard.getCell(row + 1, column);
		
		if ((row - 1) >= 0)
			uNeighbor = gameBoard.getCell(row - 1, column);
		
		if((row < numOfRows - 2) && (dNeighbor != null && dNeighbor != color && dNeighbor != Square.BLANK))
		{
			for(index = row + 2; index < numOfRows; index++)
			{
				if(gameBoard.getCell(index, column) == Square.BLANK)
					break;
				
				if(gameBoard.getCell(index, column) == color)
				{
					for(index2 = row + 1; index2 < index; index2++)
						gameBoard.setCell(index2, column, color);
					
					break;
				}
			}
		}
		if((row > 1) && (uNeighbor != null && uNeighbor != color && uNeighbor != Square.BLANK))
		{
			for(index = row - 2; index >= 0; index--)
			{
				if(gameBoard.getCell(index, column) == Square.BLANK)
					break;
				
				if(gameBoard.getCell(index, column) == color)
				{
					for(index2 = row - 1; index2 > index; index2--)
						gameBoard.setCell(index2, column, color);

					
					break;
				}
			}
		}
	}
	
	/**
	 * Updates a main diagonal starting from a given cell,
	 * if there are cells that needs to change color to current player color.
	 * According to reversi rules.
	 * 
	 * @param gameBoard
	 * 		Board of the game
	 * @param p
	 * 		Cell to begin from
	 * @param color
	 * 		Color of current player
	 */
	private void updateMainDiagonal(Board gameBoard, Cell p, Square color)
	{
		int row = p.getRow();
		
		int column = p.getColumn();
		
		int numOfColumns = gameBoard.getColumns();
		
		int numOfRows = gameBoard.getRows();
		
		Square ulNeighbor = null;
		Square drNeighbor = null;
		
		int roIndex, coIndex, roIndex2, coIndex2;
		
		if ((row + 1) < gameBoard.getRows() && (column + 1) < gameBoard.getColumns())
			drNeighbor = gameBoard.getCell(row + 1, column + 1);
		
		if ((row - 1) >= 0 && (column - 1) >= 0)
			ulNeighbor = gameBoard.getCell(row - 1, column - 1);
		
		if((row < numOfRows - 2 && column < numOfColumns - 2) && (drNeighbor != null && drNeighbor != color && drNeighbor != Square.BLANK))
		{
			for(roIndex = row + 2, coIndex = column + 2; roIndex < numOfRows && coIndex < numOfColumns; roIndex++, coIndex++)
			{
				if(gameBoard.getCell(roIndex, coIndex) == Square.BLANK)
					break;
				
				if(gameBoard.getCell(roIndex, coIndex) == color)
				{
					for(roIndex2 = row + 1, coIndex2 = column + 1; roIndex2 < roIndex && coIndex2 < coIndex; roIndex2++, coIndex2++)
						gameBoard.setCell(roIndex2, coIndex2, color);
					
					break;
				}
			}
		}
		if((row > 1 && column > 1) && (ulNeighbor != null && ulNeighbor != color && ulNeighbor != Square.BLANK))
		{
			for(roIndex = row - 2, coIndex = column - 2; roIndex >= 0 && coIndex >= 0; roIndex--, coIndex--)
			{
				if(gameBoard.getCell(roIndex, coIndex) == Square.BLANK)
					break;
				
				if(gameBoard.getCell(roIndex, coIndex) == color)
				{
					for(roIndex2 = row - 1, coIndex2 = column - 1; roIndex2 > roIndex && coIndex2 > coIndex; roIndex2--, coIndex2--)
						gameBoard.setCell(roIndex2, coIndex2, color);
					
					break;
				}
			}
		}
	}
		
	/**
	 * Updates a secondary diagonal starting from a given cell,
	 * if there are cells that needs to change color to current player color.
	 * According to reversi rules.
	 * 
	 * @param gameBoard
	 * 		Board of the game
	 * @param p
	 * 		Cell to begin from
	 * @param color
	 * 		Color of current player
	 */
	private void updateSecondaryDiagonal(Board gameBoard, Cell p, Square color)
	{
		int row = p.getRow();
		
		int column = p.getColumn();
		
		int numOfColumns = gameBoard.getColumns();
		
		int numOfRows = gameBoard.getRows();
		
		Square urNeighbor = null;
		
		Square dlNeighbor = null;
		
		int roIndex, coIndex, roIndex2, coIndex2;
	
		if ((row + 1) < gameBoard.getRows() && (column - 1) >= 0)
			dlNeighbor = gameBoard.getCell(row + 1, column - 1);
		
		if ((row - 1) >= 0 && (column + 1) < gameBoard.getColumns())
			urNeighbor = gameBoard.getCell(row - 1, column + 1);
		
		if((row > 1 && column < numOfColumns - 2) && (urNeighbor != null && urNeighbor != color && urNeighbor != Square.BLANK))
		{
			for(roIndex = row - 2, coIndex = column + 2; roIndex >= 0 && coIndex < numOfColumns; roIndex--, coIndex++)
			{
				if(gameBoard.getCell(roIndex, coIndex) == Square.BLANK)

					break;
				
				if(gameBoard.getCell(roIndex, coIndex) == color)

				{
					for(roIndex2 = row - 1, coIndex2 = column + 1; roIndex2 > roIndex && coIndex2 < coIndex; roIndex2--, coIndex2++)
						gameBoard.setCell(roIndex2, coIndex2, color);

					
					break;
				}
			}
		}
		if((row < numOfRows - 2 && column > 1) && (dlNeighbor != null && dlNeighbor != color && dlNeighbor != Square.BLANK))
		{
			for(roIndex = row + 2, coIndex = column - 2; roIndex < numOfRows && coIndex >= 0; roIndex++, coIndex--)
			{
				if(gameBoard.getCell(roIndex, coIndex) == Square.BLANK)

					break;
				
				if(gameBoard.getCell(roIndex, coIndex) == color)

				{
					for(roIndex2 = row + 1, coIndex2 = column - 1; roIndex2 < roIndex && coIndex2 > coIndex; roIndex2++, coIndex2--)
						gameBoard.setCell(roIndex2, coIndex2, color);

					
					break;
				}
			}
		}
	}
	
	@Override
	public void updateBoard(Board board, Cell p, Square player)
	{
		/** Put the cell on the board, as for all games */
		super.updateBoard(board, p, player);
		
		/** Update all direction according to reversi rules */
		updateRow(board, p, player);
		updateColumn(board, p, player);
		updateMainDiagonal(board, p, player);
		updateSecondaryDiagonal(board, p, player);
	}

	@Override
	public Status getBoardStatus(Board gameBoard) {
		int scoreOnePlayer = 0;
		int scoreSecondPlayer = 0;
		int totalCells = 0;
		Status status = new Status();
		
		for (Board.CellValue cell : gameBoard) {
			if (cell.getCellData() == Square.OnePlayer)
				scoreOnePlayer++;
			else if (cell.getCellData() == Square.SecondPlayer)
				scoreSecondPlayer++;
			
			totalCells++;
		}

		status.setPlayerScore(Square.OnePlayer, scoreOnePlayer);
		status.setPlayerScore(Square.SecondPlayer, scoreSecondPlayer);
		
		/**
		 * If the other player has 0 stones and it's not the first turn,
		 * then all the board is ours and we're the winner.
		 * Set to max score.
		 */
		if (scoreOnePlayer == 0 && scoreSecondPlayer > 0)
			status.setWinner(Square.SecondPlayer);
		else if (scoreSecondPlayer == 0 && scoreOnePlayer > 0)
			status.setWinner(Square.OnePlayer);
		
		/**
		 * If no empty cells then the player with more cells wins.
		 */
		if ((scoreOnePlayer + scoreSecondPlayer) == totalCells)
		{
			if (scoreOnePlayer > scoreSecondPlayer)
				status.setWinner(Square.OnePlayer);
			else if (scoreOnePlayer < scoreSecondPlayer)
				status.setWinner(Square.SecondPlayer);
			else
				status.setWinner(Square.BLANK);
		}
				
		return status;
	}

}
