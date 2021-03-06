package de.vs.monopoly.model;

public class Event {
	
	private String type;
	private String name;
	private String reason;
	private String resource;
	private Player player;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Event(String type, String name, String reason, String resource, Player player){
		
		this.type = type;
		this.name = name;
		this.reason = reason;
		this.resource = resource;
		this.player = player;
		
	}
}
