package de.vs.monopoly.verzeichnisdienst;

import java.util.ArrayList;

import controller.GameData;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Player;
import de.vs.monopoly.model.Service;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public class VerzeichnisdienstImpl {

	private static VerzeichnisdienstImpl verzeichnisdienstImpl;
	final String url = "https://vs-docker.informatik.haw-hamburg.de/ports/8053/services";
	Retrofit retro;
	VerzeichnisdienstInterface verzeichnisdienst;

	public static VerzeichnisdienstImpl init() {
		if (verzeichnisdienstImpl == null)
			verzeichnisdienstImpl = new VerzeichnisdienstImpl();
		return verzeichnisdienstImpl;

	}

	private VerzeichnisdienstImpl() {

		retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
		verzeichnisdienst = retro.create(VerzeichnisdienstInterface.class);

	}

	public VerzeichnisdienstInterface getVerzeichnisdienstInterface() {
		return this.verzeichnisdienst;
	}

	public Service holeServiceById(String name) {
		Service result = null;
		try {
			result = (Service) verzeichnisdienst.holeServiceByName(name);
			// muss wahscheinlich noch per JSON geparst werden!!! Also das er
			// eine Liste mit vielen Services
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Service> getServiceByName(String name) {
		ArrayList<Service> result = new ArrayList<Service>();
		try {
			result = (ArrayList<Service>) verzeichnisdienst.holeServiceByName(name);
			// muss wahscheinlich noch per JSON geparst werden!!! Also das er
			// eine Liste mit vielen Services
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Service> getServiceByType(String type) {
		ArrayList<Service> result = new ArrayList<Service>();
		try {
			result = (ArrayList<Service>) verzeichnisdienst.holeServiceByName(type);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void registerService(Service service) {

		try {
			verzeichnisdienst.registriereService(service);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loeschen(String id) {

		try {
			verzeichnisdienst.holeServiceByName(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
