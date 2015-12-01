package controller;

import java.util.ArrayList;

public class Spielfeld {

	private int x;
	private int y;
	private ArrayList<Feld> spielfelder;
	
	public Spielfeld(int x, int y){
		this.x = x;
		this.y = y;
		
		int breite = x/13;
		spielfelder = new ArrayList<Feld>();
		
		int p1x = 0;
		int p1y = 0;
		
		
		
		for(int i = 1; i<= 40; i++){
			
			
		//	spielfelder.add(new Feld("Feld"+i, punkt1x, punkt1y, punkt2x, punkt2y, punkt3x, punkt3y, punkt4x, punkt4y))
			
			
		}
		
		
	}

	public ArrayList<Feld> getSpielfelder() {
		return spielfelder;
	}
	
}
