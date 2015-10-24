package de.vs.monopoly;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Roll implements Serializable{

	private int number;

	public Roll(int zahl) {
		number = zahl;
	}

	public int getZahl() {
		return number;
	}
	
	
	
}
