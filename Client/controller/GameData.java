package controller;

import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;

public class GameData {
	
	private static GameData gameData;
	private Player ownPlayer;
	private Game game;
	
	private GameData(){
		ownPlayer = null; //new Player("id","Name","uri",null,0);
		game = null; // new Game("Spielname", null, null);
		
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
	public void setGameDate(Game game, Player pl){
		this.ownPlayer = pl;
		this.game = game;
	}

}
