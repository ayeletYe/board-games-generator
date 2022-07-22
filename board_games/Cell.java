package board_games;

/**
 * Represents a position in a game board
 */
public class Cell {
	private int row;
	private int column;
	
	/**
	 * Construct a new cell
	 * 
	 * @param row
	 * 		The row of the cell
	 * @param column
	 * 		The column of the cell
	 */
	public Cell(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Get cell row
	 * 
	 * @return
	 * 		Cell's row
	 */
	public int getRow()
	{
		return this.row;
	}
	
	/**
	 * Get cell column
	 * 
	 * @return
	 * 		Cell's column
	 */
	public int getColumn()
	{
		return this.column;
	}
}
