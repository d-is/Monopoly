package de.vs.monopoly.model;

import java.util.Random;

public class Dice {
	
	private Random zufall = new Random();
	private Roll roll;
	
	public void wuerfeln(){
		roll = new Roll(zufall.nextInt(6)+1);
	}

	public Roll getRoll() {
		return roll;
	}
	
	public int getZahl(){
		return roll.getNumber();
	}
	
	
	
	

}
