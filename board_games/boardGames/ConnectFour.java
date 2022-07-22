package board_games.boardGames;

import board_games.Board.Square;
import board_games.Player;
import board_games.logic.ConnectFourLogic;

/**
 * Connect 4 game
 */
public class ConnectFour extends BoardGames {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectFour(Player[] players) 
	{
		super(new ConnectFourLogic(), players, 6, 7);
	}

	@Override
	public String getPlayerIcon(Square player) {
		if (player == Square.OnePlayer)
			return "images/connect_four/or.jpg";
		else
			return "images/connect_four/gr.jpg";
	}

}