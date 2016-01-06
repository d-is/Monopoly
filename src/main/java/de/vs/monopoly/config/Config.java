package de.vs.monopoly.config;

import controller.GameData;
import de.vs.monopoly.client.ClientInterface;
import de.vs.monopoly.client.ClientInterface2;
import de.vs.monopoly.dice.rmi.DiceService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Config {
	public static final String baseURL = "http://localhost";
	public static final String verzeichnisdienst = ":????/services";
	public static final String boards = ":11042/";
	public static final String games = ":11041/";
	public static final String dice = ":11040/";
	

	// USW...

	//Hier werden die einzelnen Retro Interfaces zur direkten nutzung zurückgeggeben
	public static ClientInterface getClientServiceForGame() {
		Retrofit retroClientGameService = new Retrofit.Builder()
				.baseUrl(baseURL + games)
				.addConverterFactory(GsonConverterFactory.create()).build();

		return retroClientGameService.create(ClientInterface.class);
	}
	
	public static ClientInterface2 getClientServiceForDice() {
		Retrofit retroClientDiceService = new Retrofit.Builder()
				.baseUrl(baseURL +dice)
				.addConverterFactory(GsonConverterFactory.create()).build();

		return retroClientDiceService.create(ClientInterface2.class);
	}

}
