package de.vs.monopoly.dice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DiceRMI extends Remote {
	public Roll roll() throws RemoteException;
}
