package de.vs.monopoly.dice.rmi;

import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import de.vs.monopoly.dice.rmi.DiceRMI;
import de.vs.monopoly.model.Roll;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.MalformedURLException;
import java.rmi.Naming;


public class RollImpl extends UnicastRemoteObject 
                       implements DiceRMI {

public RollImpl() throws RemoteException {
	  super();
  }
  
  //Zum Testen
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		} catch(RemoteException ex){
			System.out.println(ex.getMessage());
		}
		try{
			Naming.rebind("Roll", new RollImpl());
		} catch(MalformedURLException ex){
			System.out.println(ex.getMessage());
		} catch(RemoteException ex){
			System.out.println(ex.getMessage());
		}
	}
	
  public Roll roll() throws RemoteException {
		// TODO Auto-generated method stub
	  Random rand = new Random();
		return new Roll(rand.nextInt(6)+1);
		
	}

}