package de.vs.monopoly.model;

import java.util.HashMap;
import java.util.HashSet;

public class Broker {
	// URI to the estates managed by the broker
	private String estates;
	private HashSet<String> freePlaces;
	private HashMap<String, HashSet<String>> owners;
	private HashMap<String, HashSet<String>> visits;
	
	
	public Broker(String estates){
		this.estates = estates;
		this.freePlaces = new HashSet<String>();
		this.owners = new HashMap<String, HashSet<String>>();
		this.visits = new HashMap<String, HashSet<String>>();
	}
	public String getEstates(){
		return this.estates;
	}
	public HashSet<String> getFreePlaces() {
		return freePlaces;
	}
	public HashMap<String, HashSet<String>> getOwners() {
		return owners;
	}
	public HashMap<String, HashSet<String>> getVisits() {
		return visits;
	}

	
	
}
