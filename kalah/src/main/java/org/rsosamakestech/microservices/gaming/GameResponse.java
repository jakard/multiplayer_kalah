package org.rsosamakestech.microservices.gaming;

/**
 * The Class GameResponse, just a way to respond the result for the
 * game sequence
 * 
 * @author rsosa
 */
public class GameResponse {

	/** The result. */
	public GameResult result;
	
	/** The last pit. */
	private int lastPit;
	
	/** The score. */
	private int score;

	/**
	 * Gets the last pit.
	 *
	 * @return the last pit
	 */
	public int getLastPit() {
		return lastPit;
	}

	/**
	 * Sets the last pit.
	 *
	 * @param lastPit the new last pit
	 */
	public void setLastPit(int lastPit) {
		this.lastPit = lastPit;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
