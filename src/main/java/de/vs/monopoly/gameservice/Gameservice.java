package de.vs.monopoly.gameservice;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Place;
import de.vs.monopoly.model.Player;

public class Gameservice {

	public static void main(String[] args) {

		List <Game> gameListe = new ArrayList<Game>();
		Gson gson = new Gson();

// erstellt neues spiel		
		post("/games", (request,response) ->{
			Game game = new Game();
			gameListe.add(game);
			String rueck = gson.toJson(game);
			response.status(201);
			return rueck;
			
		});

// spieler registrieren sich		
		put("/games/:gameid/players/:playerid", (request,response) ->{
			Place place = new Place("start");
			Player player = new Player(request.params(":playerid"), request.params(":playerid"), "", place, 0);
			String gameid = request.params(":gameid");
			
			for(Game game : gameListe){
				if(game.getGameid()==gameid){
					game.AddPlayer(player);
					response.status(200);
					return null;
				}
			}
			response.status(400);
			return null;
		});

// spieler melden sich ready
		put("/games/:gameid/players/:playerid/ready", (request,response) ->{
			String gameid 	= request.params(":gameid");
			String playerid = request.params(":playerid");
			
			for(Game game : gameListe){
				if(game.getGameid()==gameid){
					ArrayList<Player> players = game.getPlayers();
					for(Player player : players){
						if(player.getId()==playerid){
							response.status(200);
							player.setReady(true);
							return null;
						}
					}
				}
			}
			response.status(400);
			return null;
		});
		
		
//optional !!!!!!!		
// gibt alle spiele zurueck
		get("/games", (request,response) ->{
			String json = gson.toJson(gameListe);
			response.status(200);
			return json;
		});

// gibt aus welche spieler bereits das spiel betreteten haben
		get("/games/:gameid/players", (request, response) ->{
			String gameid 	= request.params(":gameid");
			for(Game game : gameListe){
				if(game.getGameid()==gameid){
					response.status(200);
					return game.getPlayers();
				}
			}
			response.status(400);
			return "Spiel nicht bekannt.";
		});
	}

}
