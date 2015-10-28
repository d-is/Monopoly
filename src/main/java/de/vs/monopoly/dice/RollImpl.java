package de.vs.monopoly.dice;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.MalformedURLException;
import java.rmi.Naming;

import de.vs.monopoly.dice.DiceRMI;


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
			Naming.rebind("Roll2", new RollImpl());
		} catch(MalformedURLException ex){
			System.out.println(ex.getMessage());
		} catch(RemoteException ex){
			System.out.println(ex.getMessage());
		}
	}
	
  public Roll roll() throws RemoteException {
		// TODO Auto-generated method stub
		return new Roll(5);
	}

}