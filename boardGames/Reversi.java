package board_games.boardGames;

import board_games.Board.Square;
import board_games.Player;
import board_games.logic.ReversiLogic;

/**
 * Reversi game
 */
public class Reversi extends BoardGames {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Reversi(Player[] players)
	{
		super(new ReversiLogic(), players, 8, 8);

		gameBoard.setCell(3, 3, Square.OnePlayer);
		gameBoard.setCell(3, 4, Square.SecondPlayer);
		gameBoard.setCell(4, 3, Square.SecondPlayer);
		gameBoard.setCell(4, 4, Square.OnePlayer);
	}
	
	@Override
	public String getPlayerIcon(Square player) {
		if (player == Square.OnePlayer)
			return "images/reversi/wh.jpg";
		else
			return "images/reversi/bl.jpg";
	}
	
}
