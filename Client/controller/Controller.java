package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.jetty.util.PathWatcher.Config;

import de.vs.monopoly.model.Roll;
import de.vs.monopoly.verzeichnisdienst.BlankoRequestsInterface;
import de.vs.monopoly.verzeichnisdienst.ServiceGenerator;
import de.vs.monopoly.verzeichnisdienst.VerzeichnisdienstInterface;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.client.ClientInterface;
import de.vs.monopoly.client.ClientInterface2;
import de.vs.monopoly.model.Game;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import ui.MainWindow;
import de.vs.monopoly.config.*;
import de.vs.monopoly.dice.rmi.DiceService;

public class Controller {

	private static ClientInterface gameService;
	private static ClientInterface2 dice;
	private static GameData gd;
	
	public static void init() {

		gameService = de.vs.monopoly.config.Config.getClientServiceForGame();
		dice = de.vs.monopoly.config.Config.getClientServiceForDice();
		
		
		
		gd = GameData.initGameObject();
	}

	public static Roll dice() throws IOException {
	
		return (Roll) dice.dice().execute().body();
	}

	public static boolean createGame(String gamename, String playername) {
		Random rand = new Random();
		String id = rand.nextInt() + "";
		Response<Game> game = null;
		Player player = new Player(id, playername, null, null, 0);
		try {
			game = gameService.erstelleSpiel().execute();
			String urlToRegister = game.headers().get("Lokationsheader");
			gd.setGameDate(game.body(), player);

			registerPlayer(player, urlToRegister);
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

			gameService.registriereSpieler(game.getGameid(), player.getId()).execute();

			gd.setGameDate(game, player);
			gd.getGame().getPlayers().add(player);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
		//Blanko Register wird ausgeführt, wenn ein neues SPiel erstellt wurde und dieser SPieler sich direkt im Anschluss an das Game anmeldet
	private static void registerPlayer(Player player, String urlToRegister) {
		System.out.println("Versuche Spieler mit neuer URL: " +urlToRegister +"\n zu registieren!");
		Retrofit retro;
		BlankoRequestsInterface blanko;
		blanko = ServiceGenerator.createService(BlankoRequestsInterface.class, urlToRegister);
		try {
			
			
			blanko.registriereSpieler(player.getId().toString()).execute();
			gd.getGame().getPlayers().add(player);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setPlayerReady() throws IOException {
		gameService.meldeSpielerReady(gd.getGame().getGameid().toString(), gd.getPlayer().getId().toString()).execute();
	}

	public static void putDiceThToBoardService(int rollNumber) throws IOException {
		Roll roll = new Roll(rollNumber);
		gameService.uebergebeWurf(gd.getGame().getGameid().toString(), gd.getPlayer().getId().toString(), roll).execute();
	}
	public static void zeigeAnDasSpieleramZugist(){
		MainWindow.lblDiceResult.setText("Sie sind am Zug!");
	}
	public static void zeigeEventan(String text){
		MainWindow.lblDiceResult.setText(text);
	}
}
