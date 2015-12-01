package controller;

import entities.Game;
import entities.Player;

public class GameData {
	
	private static GameData gameData;
	private Player ownPlayer;
	private Game game;
	
	private GameData(){
		ownPlayer = new Player("id","Name","uri",null,0);
		game = new Game("Spielname", null, null);
	}
	
	public static GameData initGameObject(){
		if(gameData == null)
			gameData = new GameData();
		return gameData;
	}
	
	public Player getPlayer(){
		return this.ownPlayer;
	}
	public Game getGame(){
		return this.game;
	}

}
