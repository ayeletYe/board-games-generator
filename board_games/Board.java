package board_games;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class represent a board of a game.
 * Stores the values of each cell in the board.
 * 
 * Supports iteration over all cells in the board.
 */
public class Board implements Iterable<Board.CellValue>, Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum Square {
		
		BLANK, OnePlayer, SecondPlayer;
	}
	
	private int rows;
	private int columns;
	private Square[][] board;
	
	/**
	 * Creates a new board with all cells initialized to BLANK
	 * 
	 * @param row
	 * 		Rows in the board
	 * @param column
	 * 		Columns in the board
	 */
	public Board(int row, int column)
	{
		this.rows = row;
		this.columns = column;
		board = new Square[row][column];
		
		for(CellValue cell : this)
		{
			setCell(cell.getCellRow(), cell.getCellColumn(), Square.BLANK);
		}
	}
	
	/**
	 * Creates a deep copy of a given board
	 * 
	 * @param b
	 * 		The board to copy
	 */
	public Board(Board b)
	{
		this.rows = b.rows;
		this.columns = b.columns;
		board = new Square[rows][columns];
		
		for(CellValue cell : this)
		{
			setCell(cell.getCellRow(), cell.getCellColumn(), 
					b.getCell(cell.getCellRow(), cell.getCellColumn()));
		}
	}
	
	/**
	 * Get number of rows in board
	 * 
	 * @return Number of rows in board
	 */
	public int getRows()
	{
		return rows;
	}
	
	
	/**
	 * Get number of columns in board
	 * 
	 * @return Number of columns in board
	 */
	public int getColumns()
	{
		return columns;
	}

	/**
	 * Get the value of a cell in the board.
	 * Who this cell belongs.
	 * 
	 * @param row
	 * 		The row of the cell
	 * @param column
	 * 		The columns of the cell
	 * 
	 * @return
	 * 		The value of the cell
	 */
	public Square getCell(int row, int column)
	{
		if (row >= rows || column >= columns)
			throw new ArrayIndexOutOfBoundsException();
		
		return this.board[row][column];
	}
	
	/**
	 * Get the value of a cell in the board.
	 * Who this cell belongs.
	 * 
	 * @param p
	 * 		The point of the cell (combined row and column)
	 * 
	 * @return
	 * 		The value of the cell
	 */
	public Square getCell(Cell p)
	{
		return getCell(p.getRow(), p.getColumn());
	}
	
	/**
	 * Sets cell value in board
	 * 
	 * @param row
	 * 		The row of the cell
	 * @param column
	 * 		The columns of the cell
	 * @param data
	 * 		The value to set to
	 */
	public void setCell(int row, int column, Square data)
	{
		if (row >= rows || column >= columns)
			throw new ArrayIndexOutOfBoundsException();
		
		this.board[row][column] = data;
	}
	
	/**
	 * An auxiliary class that combines a cell with its value
	 */
	public static class CellValue {
		
		private Cell cell;
		private Square data;
		
		/**
		 * Constructs a cell with a value
		 * 
		 * @param row
		 * 		Row of the cell
		 * @param column
		 * 		Column of the cell
		 * @param data
		 * 		Value of the cell
		 */
		public CellValue(int row, int column, Square data)
		{
			this.cell = new Cell(row, column);
			this.data = data;
		}
		
		/**
		 * Get cell row
		 * 
		 * @return
		 * 		Cell's row
		 */
		public int getCellRow()
		{
			return cell.getRow();
		}
		
		/**
		 * Get cell column
		 * 
		 * @return
		 * 		Cell's column
		 */
		public int getCellColumn()
		{
			return cell.getColumn();
		}
		
		/**
		 * Get cell value
		 * 
		 * @return
		 * 		Cell's value
		 */
		public Square getCellData()
		{
			return data;
		}
		
		/**
		 * Get the cell
		 * 
		 * @return
		 * 		Cell
		 */
		public Cell getCell() {
			return cell;
		}
	}
	

	/**
	 * Board iterator
	 * 
	 * Goes over all cells in the board,
	 * from top left to bottom right.
	 * 
	 * Overrides the standard iterator functions: hasNext and next
	 */
	public class BoardIterator implements Iterator<CellValue> {
		Board gb;
		int row = 0;
		int column = 0;
		
		/**
		 * Constructs a new Board iterator for a given board
		 * 
		 * @param gb
		 * 		Game board to iterate on
		 */
		public BoardIterator(Board gb) 
		{
			this.gb = gb;
		}
		
		@Override
		public boolean hasNext() {
			return (this.row < gb.rows && this.column < gb.columns);
		}
		
		@Override
		public CellValue next() {

			CellValue boardCell;
			
			if(hasNext())
				boardCell = new CellValue(row, column, gb.getCell(row, column));
			
			else
				throw new NoSuchElementException();
			
			column++;
			
			if(column == gb.columns)
			{
				column = 0;
				row++;
			}
			return boardCell;
		}
		
	}
	
	@Override
	public Iterator<CellValue> iterator()
	{
		return new BoardIterator(this);
	}
}
