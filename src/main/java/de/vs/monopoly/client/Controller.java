package de.vs.monopoly.client;

public class Controller {
	
	    private Rest rest;

	    public void setHost(String host) {
	        rest = Rest.newInstance(host);
	    }

	    public String createGame(String gameId, String playerId, String playerName) {
	        try {
	            return rest.post("games").body("{\"gameid\":\"" + gameId + "\",\"players\":[{\"id\":\"" + playerId + "\",\"name\":\" " + playerName + "\",\"uri\":\"\",\"position\":0,\"ready\":false}]}").asJson().getBody().toString();
	        } catch (UnirestException e) {
	            e.printStackTrace();
	        }
	        return "Fehler";
	    }

	    public String registerPlayer(String gameId, String playerId) {
	        try {
	            return rest.put("games", gameId, "players", playerId).asJson().getBody().toString();
	        } catch (UnirestException e) {
	            e.printStackTrace();
	        }
	        return "Fehler";
	    }

	    public String playerReady(String gameId, String playerId) {

	        try {
	            return rest.put("games", gameId, "players", playerId, "ready").asJson().getBody().toString();
	        } catch (UnirestException e) {
	            e.printStackTrace();
	        }

	        return "Fehler";
	    }

	    public String allPlayerReady(String gameId, String playerId) {
	        try {
	            return rest.get("games", gameId, "players", playerId, "ready").asJson().getBody().toString();
	        } catch (UnirestException e) {
	            e.printStackTrace();
	        }

	        return "Fehler";
	    }

	    public String dice() {
	        try {

	            return rest.get("dice").asJson().getBody().toString();
	        } catch (UnirestException e) {
	            e.printStackTrace();
	        }

	        return "Fehler";
	    }

}
