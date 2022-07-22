package board_games;

import java.io.Serializable;

import board_games.Board.Square;
import board_games.strategy.Strategy;

/**
 * Represents a player in a game.
 * 
 * Stores player type, his strategy and the cell value he places on the board.
 */
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Type of the player
	 */
	public enum Type {
		Human, Computer
	};
	
	private Strategy strategy;
	private Square squareType;
	private Type type;
	
	/**
	 * Constructs a new player
	 * 
	 * @param strategy
	 * 		Strategy of the player
	 * @param squareType
	 * 		Square type used by this player
	 * @param type
	 * 		Type of the player
	 */
	public Player(Strategy strategy, Square squareType, Type type) {
		this.strategy = strategy;
		this.squareType = squareType;
		this.type = type;
	}
	
	/**
	 * Returns player's strategy
	 * 
	 * @return
	 * 		Strategy of the player
	 */
	public Strategy getStrategy()
	{
		return strategy;
	}
	
	/**
	 * Sets players strategy
	 * 
	 * @param strategy
	 * 		Strategy of the player
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}

	/**
	 * Returns the square type this player places on the board
	 * 
	 * @return
	 * 		Player square type
	 */
	public Square getSquareType() {
		return squareType;
	}

	/**
	 * Sets the square type this player places on the board
	 * 
	 * @param squareType
	 * 		Player square type
	 */
	public void setSquareType(Square squareType) {
		this.squareType = squareType;
	}

	/**
	 * Get the player type
	 * 
	 * @return
	 * 		Player type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Set the player type
	 * 
	 * @param type
	 * 		Player type
	 */
	public void setType(Type type) {
		this.type = type;
	}
}
