package de.vs.monopoly.client;

import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Roll;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;


public interface ClientInterface {
	@GET("dice")
	Call<Roll> dice();
	
	@POST("games")
	Call<Game> erstelleSpiel();
	
	@PUT ("games/{gameid}/players/{playerid}")
	Call<Void> registriereSpieler(@Path("gameid") String gamid,@Path("playerid") String playerid);
	
	
	@PUT ("games/{gameid}/players/{playerid}/ready")
	Call<Void> meldeSpielerReady(@Path("gameid") String gamid,@Path("playerid") String playerid);
	
	@POST("boards/{gameid}/players/{playerid}/roll")
	Call<Void> uebergebeWurf(@Path ("gameid") String gamid,@Path("playerid") String playerid,@Body Roll roll);	
}
