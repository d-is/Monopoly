package de.vs.monopoly.game;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import controller.GameData;
import de.vs.monopoly.client.ClientInterface;
import de.vs.monopoly.client.RetrofitRest;
import de.vs.monopoly.config.Config;
import de.vs.monopoly.model.Components;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Place;
import de.vs.monopoly.model.Player;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import spark.Spark;

public class Gameservice {

	static int gameid = 0;

	public static void main(String[] args) {
		Spark.port(11041);
		System.out.println("Game Service gestartet: Port 11041");
		List<Game> gameListe = new ArrayList<Game>();
		Gson gson = new Gson();

		// erstellt neues spiel
		post("/games", (request, response) -> {
			gameid++;
			Game game = new Game(Integer.toString(gameid), new ArrayList<Player>(),
					new Components("", "", "", "", "", "", ""));
			gameListe.add(game);

			// spiel an den boardservice schicken
			String rueck = gson.toJson(game);
			System.out.println("GameServce: Neues Spiel wurde erstellt ID= "+ game.getGameid());
			
			//Nun wird der Lokationheader mit der vollen Adresse zur Registrierung im Header hinzugefügt
			String []adresse = InetAddress.getLocalHost().toString().split("/");
			response.status(201);
			if(adresse.length>0){
			response.header("Lokationsheader", adresse[1]+Config.games+"games/"+game.getGameid()+"/players/");
			}else{
			response.header("Lokationsheader", "ERROR");	
			}
			return rueck;

		});

		// spieler registrieren sich
		put("/games/:gameid/players/:playerid", (request, response) -> {
			Place place = new Place("start");
			Player player = new Player(request.params(":playerid"), request.params(":playerid"), "", place, 0);
			String gameId = request.params(":gameid");

			
			for (Game game : gameListe) {
				if (game.getGameid().equals(gameId)) {
					game.AddPlayer(player);
					response.status(200);
					System.out.println("GameServce: Neuer Spieler wurde registiert ID= "+ player.getId());
					return true;
				}
			}

			// spieler an den Boardservice übergeben
			response.status(400);
			return false;
		});

		// spieler melden sich ready
		put("/games/:gameid/players/:playerid/ready", (request, response) -> {
			String gameId = request.params(":gameid");
			String playerid = request.params(":playerid");

			for (Game game : gameListe) {
				if (game.getGameid().equals(gameId)) {
					ArrayList<Player> players = game.getPlayers();
					for (Player player : players) {
						if (player.getId().equals(playerid)) {
							response.status(200);
							player.setReady(true);
							System.out.println("GameServce: Spieler ID= "+ player.getId()+" ist ready!");
							if (players.size() > 2) {
								int allready = 0;
								for (Player elem : players) {
									if (!elem.isReady())
										allready += 1;

								}
								if (allready == 0) {
									Thread.sleep(5000);
									while (allready == 0) {
										System.out.println("GameServce: Spieler ID= "+ player.getId()+" ist am Zug!");
										Retrofit retroClient = new Retrofit.Builder().baseUrl("http://localhost:4567")
												.addConverterFactory(GsonConverterFactory.create()).build();
										RetroGameserviceInterface sender = retroClient
												.create(RetroGameserviceInterface.class);

										sender.turn();
									}

								}

							}

							return true;
						}
					}
				}
			}
			response.status(400);
			return null;
		});

		// optional !!!!!!!
		// gibt alle spiele zurueck
		get("/games", (request, response) -> {
			String json = gson.toJson(gameListe);
			response.status(200);
			return json;
		});

		// gibt aus welche spieler bereits das spiel betreteten haben
		get("/games/:gameid/players", (request, response) -> {
			String gameId = request.params(":gameid");
			for (Game game : gameListe) {
				if (game.getGameid().equals(gameId)) {
					response.status(200);
					return game.getPlayers();
				}
			}
			response.status(400);
			return "Spiel nicht bekannt.";
		});
	}

}
