package org.rsosamakestech.microservices.gaming;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class CircularKalahBoard is intented for make a simple implementation
 * of circular list customized for this business case (kalah Game)
 * 
 * @author rsosa
 */
public class CircularKalahBoard implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4694990291946307936L;

	/** The board map. */
	private Map<Integer, Integer> board;

	/** The tail intented for jumps on homes and circular behavior. */
	private int tail = 0;

	/** The take tatus due to the control needen for take on empty pits */
	private String takeStatus = "";

	/** The take all to home status. */
	private String takeAllToHomeStatus = "";

	/** The game status. */
	private String gameStatus = "";

	/**
	 * Take.
	 *
	 * @param userType
	 *            the user type
	 * @param startingPoint
	 *            the starting point
	 * @return the game response
	 */
	public GameResponse take(String userType, int startingPoint) {

		GameResponse response = new GameResponse();

		if (board.get(startingPoint) == 1 && takeStatus.equals(userType)) {

			tail = startingPoint;

			// calculate the opposite
			tail = 12 - startingPoint;

			// set to 0

			board.put(startingPoint, 0);
			board.put(tail, 0);

			int taked = 1 + board.get(tail);

			addToHome(userType, taked);

			this.takeStatus = "";

			response.result = GameResult.END_TURN;

		} else {
			response.result = GameResult.GAME_ERROR;
		}

		return response;
	}

	/**
	 * Not take if you, dont wanna take the stones
	 *
	 * @param userType
	 *            the user type
	 * @return the game response
	 */
	public GameResponse notTake(String userType) {

		GameResponse response = new GameResponse();

		if (takeStatus.equals(userType)) {

			this.takeStatus = "";

			response.result = GameResult.END_TURN;

		} else {
			response.result = GameResult.GAME_ERROR;
		}

		return response;
	}

	/**
	 * Gets the home score.
	 *
	 * @param userType
	 *            the user type
	 * @return the home score
	 */
	private int getHomeScore(String userType) {
		// Add to my home score
		int homeScore = 0;

		if (userType.equals("A")) {
			homeScore = board.get(6);

		} else {
			homeScore = board.get(13);

		}

		return homeScore;
	}

	/**
	 * Adds the to home.
	 *
	 * @param userType
	 *            the user type
	 * @param value
	 *            the value
	 */
	private void addToHome(String userType, int value) {

		int homeScore = 0;

		if (userType.equals("A")) {
			homeScore = board.get(6);
			homeScore += value;
			board.put(6, homeScore);
		} else {
			homeScore = board.get(13);
			homeScore += value;
			board.put(6, homeScore);

		}

	}

	/**
	 * Insert around
	 *
	 * @param userType
	 *            the user type
	 * @param startingPoint
	 *            the starting point
	 * @return the game response
	 */
	public GameResponse counterClockwise(String userType, int startingPoint) {

		GameResponse response = new GameResponse();

		int selectedPitValue = board.get(startingPoint);

		board.put(startingPoint, 0);

		tail = startingPoint;
		incrementTail(userType);

		for (int i = 1; i <= selectedPitValue; i++) {

			int workingOnPit = tail;

			int actualPitValue = board.get(workingOnPit);

			board.put(tail, actualPitValue + 1);
			incrementTail(userType);

			// last stone
			if (i == selectedPitValue) {

				if (gameEnds()) {

					resumeGame();
					response.result = GameResult.GAME_END;
					response.setScore(getHomeScore(userType));

				}

				if (isHome(userType, workingOnPit)) {
					// last stone on my home
					response.setScore(getHomeScore(userType));
					response.result = GameResult.NEW_TURN;

				} else {
					if (actualPitValue != 0) {

						response.setScore(getHomeScore(userType));
						response.result = GameResult.END_TURN;

					} else {

						this.takeStatus = userType;
						response.result = GameResult.CAN_TAKE;
						response.setLastPit(workingOnPit);

					}
				}

				return response;

			}

		}

		response.result = GameResult.END_TURN;
		response.setScore(getHomeScore(userType));

		return response;

	}

	/**
	 * Resume game.
	 */
	private void resumeGame() {

		if (!takeAllToHomeStatus.isEmpty()) {

			if ("A".equals(takeAllToHomeStatus)) {
				for (int i = 0; i < 6; i++) {

					addToHome("A", board.get(i));

				}
			} else {

				for (int i = 7; i < 13; i++) {

					addToHome("B", board.get(i));

				}

			}

			this.gameStatus = "END";

		}

	}

	/**
	 * Game ends.
	 *
	 * @return true, if successful
	 */
	private boolean gameEnds() {

		boolean end = true;

		for (int i = 0; i < 6; i++) {
			if (board.get(i) != 0) {
				end = false;
				break;
			}

			this.takeAllToHomeStatus = "B";
		}

		for (int i = 7; i < 13; i++) {
			if (board.get(i) != 0) {
				end = false;
				break;
			}
			this.takeAllToHomeStatus = "A";
		}

		return end;
	}

	/**
	 * Increment tail.
	 *
	 * @param userId
	 *            the user id
	 */
	private void incrementTail(String userId) {

		if (tail < 13)
			tail++;
		else
			tail = 0;

		if (isOpponentHome(userId, tail)) {
			incrementTail(userId);
		}

	}

	/**
	 * Checks if is home.
	 *
	 * @param userId
	 *            the user id
	 * @param pit
	 *            the pit
	 * @return true, if is home
	 */
	private boolean isHome(String userId, int pit) {
		return ((userId.equals("A") && pit == 6) || (userId.equals("B") && pit == 13));

	}

	/**
	 * Checks if is opponent home.
	 *
	 * @param userId
	 *            the user id
	 * @param pit
	 *            the pit
	 * @return true, if is opponent home
	 */
	private boolean isOpponentHome(String userId, int pit) {

		boolean isOpponentHome = false;

		if ((pit == 6 || pit == 13) && !isHome(userId, pit)) {

			isOpponentHome = true;

		}
		return isOpponentHome;
	}

	/**
	 * Inits the board.
	 *
	 * @param maxStones
	 *            the max stones
	 */
	public void initBoard(int maxStones) {

		board = new HashMap<>();

		for (int i = 0; i < 14; i++) {

			if (i != 6 && i != 13) {

				board.put(i, maxStones);

			} else {
				board.put(i, 0);
			}

		}

	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Map<Integer, Integer> getBoard() {
		return board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board
	 *            the board
	 */
	public void setBoard(Map<Integer, Integer> board) {
		this.board = board;
	}

	/**
	 * Gets the tail.
	 *
	 * @return the tail
	 */
	public int getTail() {
		return tail;
	}

	/**
	 * Sets the tail.
	 *
	 * @param tail
	 *            the new tail
	 */
	public void setTail(int tail) {
		this.tail = tail;
	}

	/**
	 * Gets the take status.
	 *
	 * @return the take status
	 */
	public String getTakeStatus() {
		return takeStatus;
	}

	/**
	 * Sets the take status.
	 *
	 * @param takeStatus
	 *            the new take status
	 */
	public void setTakeStatus(String takeStatus) {
		this.takeStatus = takeStatus;
	}

	/**
	 * Gets the take all to home status.
	 *
	 * @return the take all to home status
	 */
	public String getTakeAllToHomeStatus() {
		return takeAllToHomeStatus;
	}

	/**
	 * Sets the take all to home status.
	 *
	 * @param takeAllToHomeStatus
	 *            the new take all to home status
	 */
	public void setTakeAllToHomeStatus(String takeAllToHomeStatus) {
		this.takeAllToHomeStatus = takeAllToHomeStatus;
	}

	/**
	 * Gets the game status.
	 *
	 * @return the game status
	 */
	public String getGameStatus() {
		return gameStatus;
	}

	/**
	 * Sets the game status.
	 *
	 * @param gameStatus
	 *            the new game status
	 */
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

}
