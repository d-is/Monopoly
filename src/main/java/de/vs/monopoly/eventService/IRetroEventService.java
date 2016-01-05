package de.vs.monopoly.eventService;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface IRetroEventService {

@GET("boards/{gameid}")
Call<Void> getBoardStatus(@Path("gameid") String gameid, @Body Object game);	

@GET("boards/{gameid}/players/{playerid}")
Call<Void> getPlayerPosition(@Path("gameid") String gameid, @Path("playerid") String playerid, @Body Object game);	

@POST("/boards/{gameid}/players/{playerid}/roll")
Call<Void> uebergabeWurf(@Path("gameid") String gameid, @Path("playerid") String playerid, @Body Object roll);

@PUT("boards/{gameid}")
Call<Void> uebergebeGameAnBoardservice(@Path("gameid") String gameid, @Body Object game);

@PUT("boards/{gameid}/players/{playerid}")
Call<Void> uebergebeSpielerAnBoardservice(@Path("gameid") String gameid, @Path("playerid") String playerid,@Body Object player);
}
