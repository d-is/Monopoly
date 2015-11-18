package de.vs.monopoly.model;

import java.io.Serializable;

public class Roll implements Serializable{
	private static final long serialVersionUID = 1227L;
	private int number;
	public Roll(int number){
		this.number = number;
	}
	public int getNumber(){
		return this.number;
	}
	public String toString(){
		return ""+number;
	}

}
