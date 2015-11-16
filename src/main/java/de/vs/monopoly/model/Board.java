package de.vs.monopoly.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Board {

	private ArrayList<Field>fields;
	private HashMap<Integer, Integer> positions;
	public Board() {
		super();
		fields = new ArrayList<Field>();
		this.positions = new HashMap<Integer,Integer>();
	}
	
	public ArrayList<Field> getFields() {
		return fields;
	}
	public HashMap<Integer, Integer> getPositions() {
		return positions;
	}
	
	
	
}
