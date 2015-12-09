package de.vs.monopoly.rest;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import de.vs.monopoly.model.Board;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Roll;

public class BoardsService {
	public static void main(String[] args) {
		Game game = new Game(null, null, null);

		Board board = new Board();

		Gson gson = new Gson();

		// Zustand des Brettes abfragen
		get("/boards/:gameid", (request, response) -> {

			String json = gson.toJson(board.getFields());
			response.status(200);
			return json;
		});
		// Position des Spielers abfragen
		get("/boards/:gameid/players/:playerid", (request, response) -> {
			String json = null;
			//eventuell an gameservice weiterleiten
			for (Player elem : game.getPlayers()) {
				if (elem.getId().equals(request.params(":playerid"))) {
					json = gson.toJson(elem.getPosition());
					break;
				}
			}
			response.status(200);
			return json;

		});

		//client übergibt würfelwurf
		post("/boards/:gameid/players:playerid/roll", (request, response) -> {
			Roll roll = gson.fromJson(request.body(), Roll.class);
			roll.getNumber();
			for (Player elem : game.getPlayers()) {
				if (elem.getId().equals(request.params(":playerid"))) {
					elem.setPosition(roll.getNumber());
					break;
				}
			}
			return true;
		});

	}

}


