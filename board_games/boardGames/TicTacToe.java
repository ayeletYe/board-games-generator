package board_games.boardGames;

import board_games.Player;
import board_games.Board.Square;
import board_games.logic.TicTacToeLogic;

/**
 * Tic Tac Toe game
 */
public class TicTacToe extends BoardGames {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicTacToe(Player[] players)
	{
		super(new TicTacToeLogic(), players, 3, 3);
	}
	
	@Override
	public String getPlayerIcon(Square player) {
		if (player == Square.OnePlayer)
			return "images/tic_tac_toe/x.jpg";
		else
			return "images/tic_tac_toe/circle.jpg";
	}
}
