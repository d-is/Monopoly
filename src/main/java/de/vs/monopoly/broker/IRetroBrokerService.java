package de.vs.monopoly.broker;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface IRetroBrokerService {

@PUT("brokers/{gameid}")
Call<Void> createBrokerForGame(@Path("gameid") String gameid, @Body Object game);

@PUT("brokers/{gameid}/places/{placeid}")
Call<Void> registerPlaces(@Path("gameid") String gameid, @Path("placerid") String placeid,@Body Object place);

@POST("brokers/{gameid}/places/{placeid}/visit/{playerid}")
Call<Void> playerLogIn(@Path("gameid") String gameid, @Path("placerid") String placeid,  @Path("playerid") String playerid);

@POST("brokers/{gameid}/places/{placeid}/owner")
Call<Void> buyAPlace(@Path("gameid") String gameid, @Path("placerid") String placeid,  @Path("playerid") String playerid);
}
