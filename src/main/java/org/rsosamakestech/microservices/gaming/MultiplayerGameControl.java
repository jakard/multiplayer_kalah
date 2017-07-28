package org.rsosamakestech.microservices.gaming;

import java.util.Collection;

import org.rsosamakestech.microservices.gaming.utils.GameStorageServiceImpl;
import org.rsosamakestech.microservices.gaming.utils.HazelcastDistributedStorage;

/**
 * The Class MultiplayerGameControl. This is the main game behavior 
 * manager
 * 
 * @author rsosa
 */
public class MultiplayerGameControl {


	/** The storage. */
	GameStorageServiceImpl storage;

	/**
	 * Instantiates a new multiplayer game control.
	 */
	public MultiplayerGameControl() {

		
		storage = new HazelcastDistributedStorage();
	}

	/**
	 * Creates the new user.
	 *
	 * @return the game user
	 */
	public GameUser createNewUser() {

		

		GameUser newUser = new GameUser();
		storage.createUser(newUser);

		return newUser;

	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public Collection<GameUser> getUsers() {

		return storage.readAllUsers();
	}


	/**
	 * Join open game.
	 *
	 * @param gameId the game id
	 * @param userId the user id
	 * @return the game response
	 */
	public GameResponse joinOpenGame(String gameId, String userId) {

		GameResponse resp = new GameResponse();

		GameUser user = storage.readUser(userId);
		Game openGame = storage.readGame(gameId,true);

		if (openGame.joinGame(user)) {

			if (openGame.isFull()) {
				
				storage.createGame(openGame, false);
				storage.deleteGame(gameId, true);
			}

			resp.result = GameResult.NEW_GAME;

		} else {
			resp.result = GameResult.GAME_ERROR;

		}

		return resp;

	}

	/**
	 * Creates the open game.
	 *
	 * @param userId the user id
	 * @return the game
	 */
	public Game createOpenGame(String userId) {

		GameUser user = storage.readUser(userId);

		Game newGame = new Game();

		newGame.joinGame(user);

		
		storage.createGame(newGame, true);
		

		return newGame;

	}


	/**
	 * Gets the open games list.
	 *
	 * @return the open games list
	 */
	public Collection<Game> getOpenGamesList() {

		return storage.readAllGames(true);
	}

	/**
	 * Gets the started games list.
	 *
	 * @return the started games list
	 */
	public Collection<Game> getStartedGamesList() {

		return storage.readAllGames(false);
	}

	/**
	 * Watch started game.
	 *
	 * @param gameId the game id
	 * @return the game
	 */
	public Game watchStartedGame(String gameId) {

		return storage.readGame(gameId, false);

	}

	/**
	 * Move.
	 *
	 * @param gameId the game id
	 * @param userId the user id
	 * @param pit the pit
	 * @return the game response
	 */
	public GameResponse move(String gameId, String userId, int pit) {

		Game game = storage.readGame(gameId, false);
		return game.move(userId, pit);

	}

	/**
	 * Take.
	 *
	 * @param gameId the game id
	 * @param userId the user id
	 * @param pit the pit
	 * @return the game response
	 */
	public GameResponse take(String gameId, String userId, int pit) {

		Game game = storage.readGame(gameId, false);
		return game.take(userId, pit);

		
	}

	/**
	 * Not take.
	 *
	 * @param gameId the game id
	 * @param userId the user id
	 * @return the game response
	 */
	public GameResponse notTake(String gameId, String userId) {

		Game game = storage.readGame(gameId, false);
		return game.notTake(userId);

	}

}
