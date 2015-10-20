package de.vs.monopoly.dice;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Naming;

import de.vs.monopoly.dice.DiceRMI;


public class RollImpl extends UnicastRemoteObject 
                       implements DiceRMI {

  public RollImpl() throws RemoteException {
  }

  public Roll roll() throws RemoteException {
		// TODO Auto-generated method stub
		return new Roll(5);
	}

}