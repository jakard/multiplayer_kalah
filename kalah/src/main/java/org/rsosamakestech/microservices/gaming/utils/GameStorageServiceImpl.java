package org.rsosamakestech.microservices.gaming.utils;

import java.util.Collection;

import org.rsosamakestech.microservices.gaming.Game;
import org.rsosamakestech.microservices.gaming.GameUser;

public interface GameStorageServiceImpl {

//CRUD
	
	public void createUser(GameUser user);
	public boolean updateUser(GameUser user);
	public GameUser readUser(String userId);
	public void deleteUser(String userId);
	
	public Collection<GameUser> readAllUsers();
	
	public void createGame(Game game, boolean open);
	public boolean updateGame(Game game, boolean open);
	public Game readGame(String gameId, boolean open);
	public void deleteGame(String gameId, boolean open);

	public Collection<Game> readAllGames(boolean open);

}