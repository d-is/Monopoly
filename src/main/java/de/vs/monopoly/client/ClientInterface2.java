package de.vs.monopoly.client;

import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Roll;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;


public interface ClientInterface2 {
	@GET("/dice")
	Call<Roll> dice();

}
