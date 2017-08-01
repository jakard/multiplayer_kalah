package org.rsosamakestech.microservices.gaming.utils;

import java.util.Collection;

import org.rsosamakestech.microservices.gaming.Game;
import org.rsosamakestech.microservices.gaming.GameUser;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;

public class HazelcastDistributedStorage implements GameStorageServiceImpl {

	HazelcastInstance hz;
	IdGenerator usersIdGenerator;
	IdGenerator gamesIdGenerator;



	public HazelcastDistributedStorage() {
		Config cfg = new Config();
		hz = Hazelcast.newHazelcastInstance(cfg);
		usersIdGenerator = hz.getIdGenerator("users-ids");
		gamesIdGenerator = hz.getIdGenerator("games-ids");

	}

	@Override
	public void createUser(GameUser user) {

		IMap<String, GameUser> users = getUsersMap();
		
		if(null==user.getId()) {
			user.setId(String.valueOf(usersIdGenerator.newId()));
		}

		users.put(user.getId(), user);

	}

	@Override
	public boolean updateUser(GameUser user) {
		IMap<String, GameUser> map = getUsersMap();
		return (map.replace(user.getId(), user) != null);

	}

	@Override
	public GameUser readUser(String userId) {
		IMap<String, GameUser> map = getUsersMap();
		return map.get(userId);
	}

	@Override
	public void deleteUser(String userId) {
		IMap<String, GameUser> map = getUsersMap();
		map.remove(userId);

	}

	@Override
	public Collection<GameUser> readAllUsers() {
		IMap<String, GameUser> map = getUsersMap();

		return map.values();
	}



	@Override
	public void createGame(Game game, boolean open) {
		IMap<String, Game> map;

		map = chooseGameMap(open);
		
		if(null==game.getGameId()) {
			game.setGameId(String.valueOf(gamesIdGenerator.newId()));
		}

		map.put(game.getGameId(), game);

	}

	@Override
	public boolean updateGame(Game game, boolean open) {
		IMap<String, Game> map = chooseGameMap(open);
		return (map.replace(game.getGameId(), game) != null);

	}

	@Override
	public Game readGame(String gameId, boolean open) {
		IMap<String, Game> map = chooseGameMap(open);
		return map.get(gameId);
	}

	@Override
	public void deleteGame(String gameId, boolean open) {
		IMap<String, Game> map = chooseGameMap(open);
map.remove(gameId);
	}

	@Override
	public Collection<Game> readAllGames(boolean open) {
		IMap<String, Game> map = chooseGameMap(open);

		return map.values();
	}

	private IMap<String, Game> chooseGameMap(boolean open) {
		IMap<String, Game> map;
		if (open)
			map = hz.getMap("openGames");
		else
			map = hz.getMap("startedGames");
		return map;
	}
	
	private IMap<String, GameUser> getUsersMap() {
		return hz.getMap("onlineUsers");
	}

}