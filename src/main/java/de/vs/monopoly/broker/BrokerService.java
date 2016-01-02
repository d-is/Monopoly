package de.vs.monopoly.broker;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import de.vs.monopoly.model.Components;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Place;
import de.vs.monopoly.model.Player;

public class BrokerService {

	static int gameid = 0;
//	List <Game> gameListe = new ArrayList<Game>();
//	Gson gson = new Gson();
	
		
	
	public static void main(String[] args) {
		
//		int gameid = 0;
		List <Game> gameListe = new ArrayList<Game>();
		Gson gson = new Gson();

		// Board bei Broker -> Besuche durch Spieler anmelden
		post("/broker/:gameid/places/:placeid/visit/:playerid", (request,response) ->{
			
			Game game = new Game(Integer.toString(gameid),new ArrayList<Player>(),new Components("", "", "", "", "", "", ""));
			gameListe.add(game);
	
			String rueck = gson.toJson(game);
			response.status(201);
			return rueck;
			
		});
		
		// Spieler Grundstücke kaufen
		post("/broker/:gameid/places/:placeid/owner", (request,response) ->{
			
			Game game = new Game(Integer.toString(gameid),new ArrayList<Player>(),new Components("", "", "", "", "", "", ""));
			gameListe.add(game);
	
			String rueck = gson.toJson(game);
			response.status(201);
			return rueck;
			
		});

		// boards einen Broker pro Spiel erstellt		
		put("/broker/:gameid", (request,response) ->{
			Place place = new Place("start");
			Player player = new Player(request.params(":playerid"), request.params(":playerid"), "", place, 0);
			String gameId = request.params(":gameid");
			
			for(Game game : gameListe){
				if(game.getGameid()==gameId){
					game.AddPlayer(player);
					response.status(200);
					return null;
				}
			}
			
		//	spieler an den Boardservice übergeben
			response.status(400);
			return null;
		});

// spieler melden sich ready
		put("/games/:gameid/players/:playerid/ready", (request,response) ->{
			String gameId 	= request.params(":gameid");
			String playerid = request.params(":playerid");
			
			for(Game game : gameListe){
				if(game.getGameid()==gameId){
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
			String gameId 	= request.params(":gameid");
			for(Game game : gameListe){
				if(game.getGameid()==gameId){
					response.status(200);
					return game.getPlayers();
				}
			}
			response.status(400);
			return "Spiel nicht bekannt.";
		});
	}

}
