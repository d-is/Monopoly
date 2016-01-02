package de.vs.monopoly.dice.rmi;

import static spark.Spark.*;
import com.google.gson.*;

import de.vs.monopoly.model.Dice;

public class DiceService 
{
    public static void main( String[] args )
    {
    	Dice dice = new Dice();
    	Gson gson = new Gson();
    	
    	 get("/dice", (request, response) -> {
             dice.wuerfeln();
             String json = gson.toJson(dice.getRoll());
             response.status(200);
             return json;
    	});
    }
}
