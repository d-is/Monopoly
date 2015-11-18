package de.vs.monopoly.rest;

import static spark.Spark.*;

import java.io.IOException;

import com.google.gson.*;

import de.vs.monopoly.model.*;
import retrofit.ClientInterface;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import spark.Route;



public class BoardsService {
	public static void main(String[] args) {

		Components compo = new Components("", "http://localhost", "http://localhost", "", "", "", "");
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
	
			json = gson.toJson(board.getPositions().get(request.params(":playerid")));

			response.status(200);
			return json;
		});
	
		post("/boards/:gameid/players/:playerid/roll", (request, response) -> {
			
		String json ;
			try {
				json = gson.toJson("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			//String json = gson.toJson(client.dice().execute().body());
			//response.status(200);
			//return json;
		});
		
		
		
		// in Arbeit
		// Position des Spielers ändern
		put("/boards/:gameid/players/:playerid/:position", (request, response) -> {
			String json = null;
			for (Player elem : game.getPlayers()) {
				if (elem.getId().equals(request.params(":playerid"))) {
					elem.setPosition(Integer.valueOf(request.params(":position")));
					break;
				}
			}

		});

	}

}