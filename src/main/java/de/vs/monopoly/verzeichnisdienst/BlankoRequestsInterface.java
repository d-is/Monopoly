package de.vs.monopoly.verzeichnisdienst;

import retrofit.Call;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface BlankoRequestsInterface {

	@PUT ("{playerid}")
	Call<Void> registriereSpieler(@Path("playerid") String playerid);

}
