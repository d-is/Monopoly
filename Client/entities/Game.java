package entities;

import java.util.ArrayList;

public class Game {
	
	private String gameid;
	private ArrayList<Player> players;
	private Components components;
	
	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Components getComponents() {
		return components;
	}

	public void setComponents(Components components) {
		this.components = components;
	}
	
	public Game(String gameid, ArrayList<Player> players, Components components){
		this.gameid = gameid;
		this.players = players;
		this.components = components;
	}
	
	public void AddPlayer(Player player){
		this.players.add(player);
	}
}