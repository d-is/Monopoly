package de.vs.monopoly.gameservice;

import java.util.ArrayList;
import java.util.Collection;
import static spark.Spark.*;
import com.google.gson.Gson;

public class Gameservice {

	public static void main(String[] args) {

		Collection<Game> gameListe = new ArrayList<Game>();
		Gson gson = new Gson();
//		
//		Player test = new Player("test","Tester","", null, 0);
//		ArrayList<Player> plays = new ArrayList<Player>();
//		plays.add(test);
//		Game temp = new Game("SpikesGame", plays);
//		gameListe.add(temp);

// erstellt neues spiel		
		post("/games", (request,response) ->{
//			Game game = gson.fromJson(request.body(), Game.class);
//			gameListe.add(game);
//			response.status(201);
//			return gson.toJson(game);
			return "spiel";
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
					return gson.toJson(player);
				}
			}
			response.status(400);
			return "Das Spiel konnte nicht gefunden werden.";
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
							return "Ready!";
						}
					}
				}
			}
			response.status(400);
			return "Spieler oder Spiel nicht bekannt";
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
