package de.vs.monopoly.board;

import com.google.gson.Gson;
import de.vs.monopoly.model.Board;
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

public class BoardsService {

	final String url = "https://vs-docker.informatik.haw-hamburg.de/ports/11042/boards";
	Retrofit retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
	IRetroBoardService boardinterface = retro.create(IRetroBoardService.class);
	private Board board;

	public void start() {
				
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
		
		//Games bei Erstellung eines neuen Spiels ein neues Spielbrett erzeugen durch
		put("/boards/{gameid}", (request, response) -> {
			
			this.board = new Board();
			String json = gson.toJson(this.board);
			response.status(200);
			return json;

		});
		//Games bei Registierung von Spielern diese gleich auf das Board setzt durch
		put("/boards/{gameid}/players/{playerid}", (request, response) -> {
			Player player = gson.fromJson(request.body(), Player.class);
			this.board.getPositions().put(player.getId(), 0);
			
			response.status(200);
			return true;
		});
	
	}

}
