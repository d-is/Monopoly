package de.vs.monopoly.game;

import static spark.Spark.post;
import static spark.Spark.put;

import controller.Controller;
import de.vs.monopoly.model.Board;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface RetroGameserviceInterface {

	@PUT("/boards/{gameid}")
	Call<Object> holeBoard(@Path("gameid") String gameid);

	@PUT("/boards/{gameid}/players/{playerid}")
	Call<Void> uebergebeSpielerAnBoardservice(@Path("gameid") String gameid, @Path("playerid") String playerid,@Body Object player);

	@POST("/player/turn")
	Call<Void> turn();


}
