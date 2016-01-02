package de.vs.monopoly.model;

public class Broker {
	// URI to the estates managed by the broker
	private String estates;
	
	public Broker(String estates){
		this.estates = estates;
	}
	public String getEstates(){
		return this.estates;
	}
	
}
