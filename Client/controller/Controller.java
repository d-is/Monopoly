package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import de.vs.monopoly.model.Roll;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Game;
import retrofit.ClientInterface;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Controller {
	private static ClientInterface client;
	private static GameData gd;

	public static void init() {
		
		//Verzeichnisdienst implementieren
		
		
		Retrofit retro = new Retrofit.Builder().baseUrl("http://localhost:4567")
				.addConverterFactory(GsonConverterFactory.create()).build();
		client = retro.create(ClientInterface.class);

		gd = GameData.initGameObject();
	}

	public static void dice() {
		client.dice();
	}

	public static boolean createGame(String gamename, String playername) {
		Random rand = new Random();
		String id = rand.nextInt() + "";
		Game game = null;
		Player player = new Player(id, playername, null, null, 0);
		try {
			game = client.erstelleSpiel().execute().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		gd.setGameDate(game, player);

		registerPlayer(player);

		return true;
	}

	public static boolean registerPlayer(String gameid, String playerid) {
		try {

			Game game = new Game(gameid, new ArrayList<Player>(), null);

			Player player = new Player(playerid, "", null, null, 0);

			client.registriereSpieler(game.getGameid().toString(), player.getId().toString());

			gd.setGameDate(game, player);
			gd.getGame().getPlayers().add(player);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	private static void registerPlayer(Player player) {
		try {

			client.registriereSpieler(gd.getGame().getGameid().toString(), player.getId().toString());

			gd.getGame().getPlayers().add(player);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setPlayerReady() {
		client.meldeSpielerReady(gd.getGame().getGameid().toString(), gd.getPlayer().getId().toString());
	}

	public static void putDiceThToBoardService(int rollNumber) {
		Roll roll = new Roll(rollNumber);
		client.uebergebeWurf(gd.getGame().getGameid().toString(), gd.getPlayer().getId().toString(), roll);
	}

}
