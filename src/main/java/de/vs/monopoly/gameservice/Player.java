package de.vs.monopoly.gameservice;

public class Player {
	
	private String id;
	private String name;
	private String uri;
	private Place place;
	private boolean ready;
	
	public Player(String id, String name, String uri, Place place, int position){
		
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.place = place;
		this.ready = false;
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}
