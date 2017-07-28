package org.rsosamakestech.microservices.gaming;

import java.io.Serializable;


/**
 * The Class GameUser.
 * 
 * @author rsosa
 */
public class GameUser implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6761641600896866864L;
	
	/** The id. */
	String id;
	
	/** The side. */
	int side;
	
	/** The score. */
	int score;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the side.
	 *
	 * @return the side
	 */
	public int getSide() {
		return side;
	}

	/**
	 * Sets the side.
	 *
	 * @param side the new side
	 */
	public void setSide(int side) {
		this.side = side;
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
