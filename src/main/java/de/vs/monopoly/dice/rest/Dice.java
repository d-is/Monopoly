package de.vs.monopoly.dice.rest;

import java.util.Random;

import de.vs.monopoly.dice.Roll;

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
