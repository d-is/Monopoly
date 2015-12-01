package de.vs.monopoly.model;

import java.util.ArrayList;

public class Field {
	private Place place;
	private ArrayList<Player> players;
	
	public Field(Place place) {
		super();
		this.place = place;
		players = new ArrayList<Player>();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	
	

}
