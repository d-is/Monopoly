package de.vs.monopoly.eventService;

import com.google.gson.Gson;
import de.vs.monopoly.model.Board;
import de.vs.monopoly.model.Event;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Roll;
import de.vs.monopoly.verzeichnisdienst.VerzeichnisdienstInterface;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Path;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;

public class EventService {

	final String url = "https://vs-docker.informatik.haw-hamburg.de/ports/11042/boards";
	Retrofit retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
	IRetroEventService boardinterface = retro.create(IRetroEventService.class);
	private Board board;

	public void start() {
		
		Gson gson = new Gson();
		ArrayList<Event> eventListe = new ArrayList<Event>();
		ArrayList<Player> playerListe = new ArrayList<Player>();

		
		
		post("/events",(request, response) -> {					 //neue Events erstellt werden können mit
			Event event = gson.fromJson(request.body(), Event.class);
			eventListe.add(event);
			return true;		
		});
		post("/events/subscriptions",(request, response) -> {	//Interessenten sich Einschreiben können mittels
			Player p = gson.fromJson(request.body(), Player.class);
			playerListe.add(p);
			return true;				
		});
		
		//unklar was mitgegeben wird post {uri of the subscription} //alle Interessenten benachrichtigt werden über neue Events per
	}
}
