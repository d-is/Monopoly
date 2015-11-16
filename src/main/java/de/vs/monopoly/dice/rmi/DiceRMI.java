package de.vs.monopoly.dice.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.vs.monopoly.model.Roll;

public interface DiceRMI extends Remote {
	public Roll roll() throws RemoteException;
}
