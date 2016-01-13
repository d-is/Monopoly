package de.vs.monopoly.dice.rmi;

import static spark.Spark.*;
import com.google.gson.*;

import de.vs.monopoly.model.Dice;
import spark.Spark;

public class DiceService 
{
    public static void main( String[] args )
    {
    	Spark.port(11040);
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
