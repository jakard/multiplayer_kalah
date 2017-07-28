package org.rsosamakestech.microservices.gaming.test;

import org.rsosamakestech.microservices.gaming.GameResponse;
import org.rsosamakestech.microservices.gaming.GameResult;
import org.rsosamakestech.microservices.gaming.MultiplayerGameControl;

public class TestGame {

	public static void main(String[] args) {
		MultiplayerGameControl controller = new MultiplayerGameControl();

		String idUserA = controller.createNewUser().getId();
		String idUserB = controller.createNewUser().getId();

		String gameId = controller.createOpenGame(idUserA).getGameId();
		controller.joinOpenGame(gameId, idUserB);

		
		GameResponse resp = controller.move(gameId, idUserA, 0);

		while (resp.result.equals(GameResult.NEW_TURN)) {


			resp = controller.move(gameId, idUserA, 5);

		}

		if (resp.result.equals(GameResult.CAN_TAKE)) {

			controller.take(gameId, idUserA, resp.getLastPit());
		}

		if (resp.result.equals(GameResult.END_TURN)) {
			

			resp = controller.move(gameId, idUserB, 8);

			while (resp.result.equals(GameResult.NEW_TURN)) {
				
				resp = controller.move(gameId, idUserA, 5);

			}
			if (resp.result.equals(GameResult.CAN_TAKE)) {
				
				controller.take(gameId, idUserB, resp.getLastPit());
			}

		}

	}

}
