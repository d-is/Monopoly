package de.vs.monopoly.gameservice;

import java.util.ArrayList;


public class Game {
	
	private String gameid;
	private ArrayList<Player> players;
	public String dice; 
	public String board;
	public String bank;
	
	public Game(String gameid){
		this.gameid = gameid;
		this.players = new ArrayList<Player>();
	}
	
	public String getDice() {
		return dice;
	}

	public void setDice(String dice) {
		this.dice = dice;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getGameid() {
		return gameid;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void AddPlayer(Player player){
		this.players.add(player);
	}
}
