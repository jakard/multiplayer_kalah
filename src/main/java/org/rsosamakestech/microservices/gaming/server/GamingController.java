package org.rsosamakestech.microservices.gaming.server;


import java.util.Collection;
import java.util.logging.Logger;

import org.rsosamakestech.microservices.gaming.Game;
import org.rsosamakestech.microservices.gaming.GameActionException;
import org.rsosamakestech.microservices.gaming.GameResponse;
import org.rsosamakestech.microservices.gaming.GameUser;
import org.rsosamakestech.microservices.gaming.MultiplayerGameControl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author rsosa
 */
@RestController
public class GamingController {

	protected Logger logger = Logger.getLogger(GamingController.class.getName());

	private MultiplayerGameControl gameControl = new MultiplayerGameControl();

	public GamingController() {

		logger.info("Gaming for Backbase says system has started Gaming Rest Controller");
	}


	@RequestMapping("/games/open")
	public Collection<Game> getAllOpenGames() {

		Collection<Game> games = gameControl.getOpenGamesList();

		if (games == null)
			throw new GameActionException(null);
		else {
			return games;
		}
	}

	@RequestMapping("/games/{gameId}")
	public Game byId(@PathVariable("gameId") String gameId) {

		Game game = gameControl.watchStartedGame(gameId);

		if (game == null)
			throw new GameActionException(gameId);
		else {
			return game;
		}
	}
	
	@RequestMapping("/users/create")
	public GameUser createUser() {

		GameUser game = gameControl.createNewUser();

		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/users")
	public Collection<GameUser> getAllUsers() {

		Collection<GameUser> game = gameControl.getUsers();

		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/games/create/{userId}")
	public Game createGameChallenge(@PathVariable("userId") String userId) {

		Game game = gameControl.createOpenGame(userId);
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/games/join/{gameId}/{userId}")
	public GameResponse joinGameChallenge(@PathVariable("userId") String userId, @PathVariable("gameId") String gameId) {

		GameResponse game = gameControl.joinOpenGame(gameId, userId);
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/games/move/{gameId}/{userId}/{pit}")
	public GameResponse move(@PathVariable("userId") String userId, @PathVariable("gameId") String gameId, @PathVariable("pit") Integer pit) {

		GameResponse game = gameControl.move(gameId, userId, pit);
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/games/take/{gameId}/{userId}/{pit}")
	public GameResponse take(@PathVariable("userId") String userId, @PathVariable("gameId") String gameId, @PathVariable("pit") Integer pit) {

		GameResponse game = gameControl.take(gameId, userId, pit);
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/games/notake/{gameId}/{userId}")
	public GameResponse notTake(@PathVariable("userId") String userId, @PathVariable("gameId") String gameId) {

		GameResponse game = gameControl.notTake(gameId, userId);
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	
	@RequestMapping("/games/started")
	public Collection<Game> getStartedGames() {

		Collection<Game> game = gameControl.getStartedGamesList();
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}
	@RequestMapping("/games/watch/{gameId}")
	public Game watchGame(@PathVariable("gameId") String gameId) {

		Game game = gameControl.watchStartedGame(gameId);
		if (game == null)
			throw new GameActionException(null);
		else {
			return game;
		}
	}

}
