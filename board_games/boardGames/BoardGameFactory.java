package board_games.boardGames;

import board_games.Player;
import board_games.Board.Square;
import board_games.Player.Type;
import board_games.logic.ConnectFourLogic;
import board_games.logic.GameLogic;
import board_games.logic.ReversiLogic;
import board_games.logic.TicTacToeLogic;
import board_games.strategy.ComplexStrategy;
import board_games.strategy.HumanStrategy;
import board_games.strategy.SimpleStrategy;
import board_games.strategy.Strategy;

/**
 * A singleton game board factory.
 * 
 * Allows to set multiple game parameters from few GUI screens,
 * and later create a board game based on these multiple parameters.
 */
public class BoardGameFactory {
	
	/**
	 * Instance of the singleton
	 */
	private static BoardGameFactory singleBoardGame = null;
	
	private String game;
	private String playerOneType;
	private String playerTwoType;
	private String playerOneDifficulty;
	private String playerTwoDifficulty;
	private String whoStart;
	
	/**
	 * Makes sure this class is created only as a singleton
	 */
	private BoardGameFactory(){
		/* nothing here */
	}

	/**
	 * Returns instance of this class
	 * 
	 * @return
	 * 		The singleton instance of BoardGameFactory
	 */
	public static BoardGameFactory getInstance()
	{
		if(singleBoardGame == null)
			singleBoardGame = new BoardGameFactory();
		
		return singleBoardGame;
	}
	
	/**
	 * Set selected game name
	 * 
	 * @param game
	 * 		Game name
	 */
	public void setGame(String game)
	{
		this.game = game;
	}
	
	/**
	 * Set selected player 1 type
	 * 
	 * @param playerOneType
	 * 		Player 1 type
	 */
	public void setPlayerOneType(String playerOneType)
	{
		this.playerOneType = playerOneType;
	}

	/**
	 * Set selected player 2 type
	 * 
	 * @param playerTwoType
	 * 		Player 2 type
	 */
	public void setPlayerTwoType(String playerTwoType)
	{
		this.playerTwoType = playerTwoType;
	}

	/**
	 * Set selected player 1 difficulty.
	 * Relevant only for computer player types.
	 * 
	 * @param playerOneDifficulty
	 * 		Player 1 difficulty
	 */
	public void setPlayerOneDifficulty(String playerOneDifficulty)
	{
		this.playerOneDifficulty = playerOneDifficulty;
	}
	
	/**
	 * Set selected player 2 difficulty.
	 * Relevant only for computer player types.
	 * 
	 * @param playerTwoDifficulty
	 * 		Player 2 difficulty
	 */
	public void setPlayerTwoDifficulty(String playerTwoDifficulty)
	{
		this.playerTwoDifficulty = playerTwoDifficulty;
	}
	
	/**
	 * Set which player starts, as selected in GUI.
	 * 
	 * @param whoStart
	 * 		The player that starts the game
	 */
	public void setWhoStart(String whoStart)
	{
		this.whoStart = whoStart;
	}
	
	/**
	 * Evaluates all parameters that were set and creates a new board game accordingly.
	 * 
	 * @return
	 * 		New board game
	 */
	public BoardGames createBoardGame()
	{
		GameLogic gameLogic;
		BoardGames boardGame;
		
		Player[] players = new Player[2];
		
		if(game.equals("Connect Four"))
		{
			gameLogic = new ConnectFourLogic();
		}
		else if(game.equals("Tic Tac Toe"))
		{
			gameLogic = new TicTacToeLogic();
		}
		else// if game = "Reversi"
		{
			gameLogic = new ReversiLogic();
		}
		
		Strategy strategy1;
		Player.Type type1;
		
		if(playerOneType.equals("Human")) {
			strategy1 = new HumanStrategy();
			type1 = Type.Human;
		}
		else
		{
			if(playerOneDifficulty.equals("Easy"))
				strategy1 = new SimpleStrategy(gameLogic);
			
			else//if playerOneDifficulty = Difficult
				strategy1 = new ComplexStrategy(gameLogic, Square.OnePlayer);

			type1 = Type.Computer;
		}
		
		Player player1 = new Player(strategy1, Square.OnePlayer, type1);
		
		
		Strategy strategy2;
		Player.Type type2;
		
		if(playerTwoType.equals("Human")) {
			strategy2 = new HumanStrategy();
			type2 = Type.Human;
		}
		else
		{
			if(playerTwoDifficulty.equals("Easy"))
				strategy2 = new SimpleStrategy(gameLogic);
			
			else//if playerTwoDifficulty = "Difficult"
				strategy2 = new ComplexStrategy(gameLogic, Square.SecondPlayer);

			type2 = Type.Computer;
		}
		
		Player player2 = new Player(strategy2, Square.SecondPlayer, type2);
		
		players[0] = player1;
		players[1] = player2;
		
		if(game.equals("Connect Four"))
		{
			boardGame = new ConnectFour(players);
		}
		else if(game.equals("Tic Tac Toe"))
		{
			boardGame = new TicTacToe(players);
		}
		else// if game = "Reversi"
		{
			boardGame = new Reversi(players);
		}
		if(whoStart.equals("Player 1"))
			boardGame.setWhoseTurn(0);
		
		else//if whoseTurn = "Player 2"
			boardGame.setWhoseTurn(1);
		
		return boardGame;
	}
}
