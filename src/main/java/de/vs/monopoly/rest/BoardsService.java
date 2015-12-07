package de.vs.monopoly.rest;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;

import de.vs.monopoly.model.Board;
import de.vs.monopoly.model.Dice;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;

public class BoardsService {
	public static void main(String[] args) {
		Game game = new Game();

		Dice dice = new Dice();
		Board board = new Board();
		Player player;

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
			for (Player elem : game.getPlayers()) {
				if (elem.getId().equals(request.params(":playerid"))) {
					json = gson.toJson(elem.getPosition());
					break;
				}
			}
			response.status(200);
			return json;

		});
		// Position des Spielers ändern
		put("/boards/:gameid/players/:playerid/:position", (request, response) -> {
			String json = null;
			for (Player elem : game.getPlayers()) {
				if (elem.getId().equals(request.params(":playerid"))) {
					elem.setPosition(Integer.valueOf(request.params(":position")));
					break;
				}
			}

			return null;
		});

		//
		post("/boards/:gameid/players:playerid/roll", (request, response) -> {
			dice.wuerfeln();
			String json = gson.toJson(dice.getRoll());
			response.status(200);
			return json;
		});

	}

}

// get("/dice", (request, response) -> {
// dice.wuerfeln();
// String json = gson.toJson(dice.getRoll());
// response.status(200);
// return json;
// });
// get("/dice", (request, response) -> {
// dice.wuerfeln();
// String json = gson.toJson(dice.getRoll());
// response.status(200);
// return json;
// });
// ///{gameid}/players/{playerid}/roll
//
//
//
//
// get("/games/:gameid/players", (request, response) ->{
// for(Game game : gameListe){
// if(game.equals(request.params(":gameid"))){
// response.status(200);
// return game.getPlayers();
// }}response.status(400);return"Gameid wrong.";});