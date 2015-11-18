package de.vs.monopoly.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Board {

	private ArrayList<Field>fields;
	private HashMap<String, Integer> positions;
	public Board() {
		super();
		fields = new ArrayList<Field>();
		this.positions = new HashMap<String,Integer>();
	}
	
	public ArrayList<Field> getFields() {
		return fields;
	}
	public HashMap<String, Integer> getPositions() {
		return positions;
	}
	
	
	
}
