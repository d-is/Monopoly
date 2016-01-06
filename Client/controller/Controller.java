package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import de.vs.monopoly.model.Roll;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.client.ClientInterface;
import de.vs.monopoly.model.Game;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import ui.MainWindow;


public class Controller {
	private static ClientInterface client;
	private static GameData gd;
	private static Retrofit retroClient;
	
	public static void init() {

		retroClient = new Retrofit.Builder().baseUrl("http://localhost:4567")
				.addConverterFactory(GsonConverterFactory.create()).build();
		client = retroClient.create(ClientInterface.class);

		gd = GameData.initGameObject();
	}

	public static Roll dice() throws IOException {
	
		return (Roll) client.dice().execute().body();
	}

	public static boolean createGame(String gamename, String playername) {
		Random rand = new Random();
		String id = rand.nextInt() + "";
		Game game = null;
		Player player = new Player(id, playername, null, null, 0);
		try {
			game = client.erstelleSpiel().execute().body();

			gd.setGameDate(game, player);

			registerPlayer(player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean registerPlayer(String gameid, String playername) {
		try {
			Random rand = new Random();
			String id = rand.nextInt() + "";
			Game game = new Game(gameid, new ArrayList<Player>(), null);

			Player player = new Player(id, playername, null, null, 0);

			client.registriereSpieler(game.getGameid(), player.getId()).execute();

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
			client.registriereSpieler(gd.getGame().getGameid().toString(), player.getId().toString()).execute();
			gd.getGame().getPlayers().add(player);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setPlayerReady() throws IOException {
		client.meldeSpielerReady(gd.getGame().getGameid().toString(), gd.getPlayer().getId().toString()).execute();
	}

	public static void putDiceThToBoardService(int rollNumber) throws IOException {
		Roll roll = new Roll(rollNumber);
		client.uebergebeWurf(gd.getGame().getGameid().toString(), gd.getPlayer().getId().toString(), roll).execute();
	}
	public static void zeigeAnDasSpieleramZugist(){
		MainWindow.lblDiceResult.setText("Sie sind am Zug!");
	}
	public static void zeigeEventan(String text){
		MainWindow.lblDiceResult.setText(text);
	}
}
