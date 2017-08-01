package org.rsosamakestech.microservices.gaming;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Allow the controller to return a 404 if an account is not found by simply
 * throwing this exception. The @ResponseStatus causes Spring MVC to return a
 * 404 instead of the usual 500.
 * 
 * @author rsosa
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameActionException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new game action exception.
	 *
	 * @param gameId the game id
	 */
	public GameActionException(String gameId) {
		super("No such game here: " + gameId);
	}
}
