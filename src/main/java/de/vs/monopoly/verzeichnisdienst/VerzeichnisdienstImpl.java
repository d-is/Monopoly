package de.vs.monopoly.verzeichnisdienst;

import java.util.ArrayList;

import controller.GameData;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class VerzeichnisdienstImpl {

	private static VerzeichnisdienstImpl verzeichnisdienstImpl;
	final String url = "https://vs-docker.informatik.haw-hamburg.de/ports/8053/services";
	Retrofit retro;
	VerzeichnisdienstInterface verzeichnisdienst;

	public static VerzeichnisdienstImpl init() {
		if(verzeichnisdienstImpl == null)
			verzeichnisdienstImpl= new VerzeichnisdienstImpl();
		return verzeichnisdienstImpl;

	}
	private VerzeichnisdienstImpl() {
		retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
		verzeichnisdienst = retro.create(VerzeichnisdienstInterface.class);
	}

	public ArrayList<Service> getServiceByName(String name) {
		ArrayList<Service> result = new ArrayList<Service>();
		try {
			 result = (ArrayList<Service>) verzeichnisdienst.holeServiceByName(name);
			//muss wahscheinlich noch per JSON geparst werden!!! Also das er eine Liste mit vielen Services 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
