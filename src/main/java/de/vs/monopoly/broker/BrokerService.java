package de.vs.monopoly.broker;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;

import de.vs.monopoly.model.Board;
import de.vs.monopoly.model.Broker;
import de.vs.monopoly.model.Components;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Place;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Roll;
import de.vs.monopoly.verzeichnisdienst.VerzeichnisdienstImpl;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public class BrokerService {

	private HashMap<String, Broker> brokers;

	// List <Game> gameListe = new ArrayList<Game>();
	// Gson gson = new Gson();

	/*
	 * @PUT("brokers/{gameid}") Call<Void> createBrokerForGame(@Path("gameid")
	 * String gameid, @Body Object game);
	 * 
	 * @PUT("brokers/{gameid}/places/{placeid}") Call<Void>
	 * registerPlaces(@Path("gameid") String gameid, @Path("placerid") String
	 * placeid,@Body Object place);
	 * 
	 * @POST("brokers/{gameid}/places/{placeid}/visit/{playerid}") Call<Void>
	 * playerLogIn(@Path("gameid") String gameid, @Path("placerid") String
	 * placeid, @Path("playerid") String playerid);
	 * 
	 * @POST("brokers/{gameid}/places/{placeid}/owner") Call<Void>
	 * buyAPlace(@Path("gameid") String gameid, @Path("placerid") String
	 * placeid, @Path("playerid") String playerid);
	 */
	public void start() {

		this.brokers = new HashMap<>();

		Gson gson = new Gson();

		// Board bei Broker -> Besuche durch Spieler anmelden
		post("/broker/:gameid/places/:placeid/visit/:playerid", (request, response) -> {
			String gameid = request.params(":gameid");
			String placeid = request.params(":placeid");
			String player = request.params(":playerid");
			
			Broker broker = this.brokers.get(gameid);
			if(broker != null){
				
				//Vorab sicherstellen, dass der Besuch vom alten Platz gelöscht wird
				Collection<HashSet<String>> values =  broker.getVisits().values();
				for(HashSet<String> elem : values){
					if(elem.contains(player))
						elem.remove(player);
				}
				//Der neue Eintrag wird festgelegt
					broker.getVisits().get(placeid).add(player);						
			}else{
				return false;
			}
			response.status(201);
			return true;

		});

		// Spieler Grundstücke kaufen
		post("/broker/:gameid/places/:placeid/owner", (request, response) -> {
			String gameid = request.params(":gameid");
			String placeid = request.params(":placeid");
			Player player = gson.fromJson(request.body(), Player.class);
			
			Broker broker = this.brokers.get(gameid);
			if(broker != null){
				
				//Vorab sicherstellen, dass die "verkaufte" Starße nicht mehr als freie Straße bzw im Besitz eines anderen ist
				broker.getFreePlaces().remove(placeid);

				Collection<HashSet<String>> values =  broker.getOwners().values();
				for(HashSet<String> elem : values){
					if(elem.contains(placeid))
						elem.remove(placeid);
				}
				//Der neue Eintrag wird festgelegt
				String url = VerzeichnisdienstImpl.init().getServiceByName("Bank").get(0).url;
				//Hier muss der Aufruf bei der Bank gemacht werden Player x kauft entsprechendes Feld
				//per IF die erfolgreiche abbuchung abwarten und dann das Feld umbuchen
				
				if(broker.getOwners().containsKey(player.getId())){
					HashSet<String> ownPlaces = broker.getOwners().get(player.getId());
					ownPlaces.add(placeid);
				}else{
					HashSet<String> newHS = new HashSet<String>();
					newHS.add(placeid);
					HashSet<String> ownPlaces = broker.getOwners().put(player.getId(),newHS);
				}
				
			}else{
				return false;
			}
			response.status(201);
			return true;

		});

		// boards einen Broker pro Spiel erstellt
		put("/broker/:gameid", (request, response) -> {
			Broker newBroker = new Broker(request.params(":gameid"));
			this.brokers.put(request.params(":gameid"), newBroker);
			// String json = gson.toJson(this.broker);
			// response.status(200);
			return true;
		});

		// die verfügbaren Grundstücke Registieren
		put("/games/:gameid/places/:placeid", (request, response) -> {
			String placeid = request.params(":placeid");
			String gameid = request.params(":gameid");

			Broker broker = this.brokers.get(gameid);

			if (broker != null){
				broker.getFreePlaces().add(placeid);
			} else {
				return false;
			}

			response.status(400);
			return true;
		});

	
	}

}
