package de.vs.monopoly.client;



import static spark.Spark.post;
import com.google.gson.Gson;
import controller.Controller;
import de.vs.monopoly.model.Event;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
public class RetrofitRest {

	public static void main(String[] args){
		
		Retrofit retro = new Retrofit.Builder().baseUrl("http://localhost:4567").addConverterFactory(GsonConverterFactory.create()).build();
		ClientInterface client = retro.create(ClientInterface.class);
		Gson gson = new Gson();
		
		
		post("/player/turn", (request, response) -> {			// in der gui anzeigen das spieler dran ist	
			Controller.zeigeAnDasSpieleramZugist();
			return true;
		}); 
		
		
		
		post("/player/event",(request, response) ->{			//event in der gui anzeigen
			Event event = gson.fromJson(request.body(), Event.class);
			Controller.zeigeEventan();
			return true;
			
			
		});   
		
		
	}

}
/*post("/boards/:gameid/players:playerid/roll", (request, response) -> {
	Roll roll = gson.fromJson(request.body(), Roll.class);
	roll.getNumber();
	for (Player elem : game.getPlayers()) {
		if (elem.getId().equals(request.params(":playerid"))) {
			elem.setPosition(roll.getNumber());
			break;
		}
	}
	return true;
});*/