package de.vs.monopoly.gameservice;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface retroGameserviceInterface {

@PUT("boards/{gameid}")
Call<Void> uebergebeGameAnBoardservice(@Path("gameid") String gameid, @Body Object game);

@PUT("boards/{gameid}/players/{playerid}")
Call<Void> uebergebeSpielerAnBoardservice(@Path("gameid") String gameid, @Path("playerid") String playerid,@Body Object player);
}
