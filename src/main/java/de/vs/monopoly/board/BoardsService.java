package de.vs.monopoly.board;

import com.google.gson.Gson;
import de.vs.monopoly.model.Board;
import de.vs.monopoly.model.Broker;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Roll;
import de.vs.monopoly.verzeichnisdienst.VerzeichnisdienstInterface;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Path;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.HashMap;

public class BoardsService {

	final String url = "https://vs-docker.informatik.haw-hamburg.de/ports/11042/boards";
	Retrofit retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
	IRetroBoardService boardinterface = retro.create(IRetroBoardService.class);
	// private static Board board;
	private static HashMap<String, Board> boards;

	public static void main(String[] Args) {

		boards = new HashMap<>();

		Board test = new Board();
		test.getPositions().put("11", 3);
		boards.put("10", test);
		Gson gson = new Gson();

		// Zustand des Brettes abfragen
		get("/boards/:gameid", (request, response) -> {
			System.out.println("Zustand des Brettes abgefragt!");
			String gameid = request.params(":gameid");
			String json;
			try {
				json = gson.toJson(boards.get(gameid).getFields());
			} catch (Exception e) {
				json = "Game (ID:" + gameid+ ") ist nicht registiert";
			}
			response.status(200);
			return json;
		});
		// Position des Spielers abfragen
		get("/boards/:gameid/players/:playerid", (request, response) -> {
			System.out.println("Zustand eines Spielers abgefragt!");
			String gameid = request.params(":gameid");
			String player = request.params(":playerid");
			// eventuell an gameservice weiterleiten
			String json;
			response.status(200);
			try {
				json = gson.toJson(boards.get(gameid).getPositions().get(player));
			} catch (Exception e) {
				json = "Spieler (ID:" + player + ") ist nicht registiert";
			}

			return json;

		});

		// client übergibt würfelwurf
		post("/boards/:gameid/players:playerid/roll", (request, response) -> {
			System.out.println("Roll wird übergeben");
			Roll roll = gson.fromJson(request.body(), Roll.class);
			roll.getNumber();

			return true;
		});

		// Games bei Erstellung eines neuen Spiels ein neues Spielbrett erzeugen
		// durch
		put("/boards/{gameid}", (request, response) -> {
			System.out.println("Neues Brett wird erstellt!");
			String gameid = request.params(":gameid");
			boards.put(gameid, new Board());

			response.status(200);
			return true;

		});
		// Games bei Registierung von Spielern diese gleich auf das Board setzt
		// durch
		put("/boards/{gameid}/players/{playerid}", (request, response) -> {
			System.out.println("Spieler wurde registiert!");
			String gameid = request.params(":gameid");
			String player = request.params(":playerid");

			boards.get(gameid).getPositions().put(player, 0);
			response.status(200);
			return true;

		});

	}

}
