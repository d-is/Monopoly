package retrofit;

import de.vs.monopoly.model.Roll;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;


public interface ClientInterface {
	@GET("dice")
	  Call<Roll> dice();
	
	@POST("games")
	Call<Object> erstelleSpiel();
	
	@PUT ("games/{gameid}/players/{playerid}")
	Call<Object> registriereSpieler(@Path("gameid") String gamid,@Path("playerid") String playerid);
	
	
	@PUT ("games/{gameid}/players/{playerid}/ready")
	Call<Object> meldeSpielerReady(@Path("gameid") String gamid,@Path("playerid") String playerid);
}
