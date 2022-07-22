package board_games.strategy;

import board_games.Board;
import board_games.Cell;
import board_games.gui.Game;

/**
 * Human strategy which is simply get the cell that was clicked in GUI.
 */
public class HumanStrategy extends Strategy{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Cell getPlayerMove(Board gameBoard) {
		
		return Game.getNextStep();
	}
}
