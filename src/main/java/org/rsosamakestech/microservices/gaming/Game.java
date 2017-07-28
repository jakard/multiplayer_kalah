package org.rsosamakestech.microservices.gaming;

import java.io.Serializable;

/**
 * The Class Game, this is a simple game class only with code 
 * related to game rules.
 * 
 * @author rsosa
 */
public class Game implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8863708893572850843L;
	
	/** The Constant _MAX_STONES. */
	private static final int MAX = 6;
	
	/** The game id. */
	String gameId;
	
	/** The user A. */
	GameUser userA;
	
	/** The user B. */
	GameUser userB;

	/** The board. */
	CircularKalahBoard board;

	/**
	 * Instantiates a new game.
	 */
	public Game() {

		initBoard();

	}

	/**
	 * Inits the board.
	 */
	private void initBoard() {

		board = new CircularKalahBoard();
		board.initBoard(MAX);

	}

	/**
	 * Join game.
	 *
	 * @param newUser the new user
	 * @return true, if successful
	 */
	public boolean joinGame(GameUser newUser) {

		boolean isOk = true;

		if (null == userA) {
			userA = newUser;
		} else if (null == userB) {
			userB = newUser;
		} else {
			isOk = false;
		}

		return isOk;
	}

	/**
	 * Checks if is full.
	 *
	 * @return true, if is full
	 */
	public boolean isFull() {

		return (null != userA && null != userB);
	}

	/**
	 * Move.
	 *
	 * @param userId the user id
	 * @param pit the pit
	 * @return the game response
	 */
	public GameResponse move(String userId, int pit) {

		GameResponse response;

		if (userA.getId().equals(userId)) {
			response = board.counterClockwise("A", pit);

		} else {
			response = board.counterClockwise("B", pit);
		}

		return response;

	}

	/**
	 * Take.
	 *
	 * @param userId the user id
	 * @param pit the pit
	 * @return the game response
	 */
	public GameResponse take(String userId, int pit) {

		GameResponse response;

		if (userA.getId().equals(userId)) {
			response = board.take("A", pit);

		} else {
			response = board.take("B", pit);
		}

		return response;

	}

	/**
	 * Not take.
	 *
	 * @param userId the user id
	 * @return the game response
	 */
	public GameResponse notTake(String userId) {

		GameResponse response;

		if (userA.getId().equals(userId)) {
			response = board.notTake("A");

		} else {
			response = board.notTake("B");
		}

		return response;

	}

	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * Sets the game id.
	 *
	 * @param gameId the new game id
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * Gets the user A.
	 *
	 * @return the user A
	 */
	public GameUser getUserA() {
		return userA;
	}

	/**
	 * Sets the user A.
	 *
	 * @param userA the new user A
	 */
	public void setUserA(GameUser userA) {
		this.userA = userA;
	}

	/**
	 * Gets the user B.
	 *
	 * @return the user B
	 */
	public GameUser getUserB() {
		return userB;
	}

	/**
	 * Sets the user B.
	 *
	 * @param userB the new user B
	 */
	public void setUserB(GameUser userB) {
		this.userB = userB;
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public CircularKalahBoard getBoard() {
		return board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	public void setBoard(CircularKalahBoard board) {
		this.board = board;
	}

	/**
	 * Gets the max stones.
	 *
	 * @return the max stones
	 */
	public static int getMaxStones() {
		return MAX;
	}

}
