package de.vs.monopoly.verzeichnisdienst;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import de.vs.monopoly.model.Service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Verzeichnisdienst {

	private static Verzeichnisdienst verzeichnisdienstImpl;
	final String URL = "https://vs-docker.informatik.haw-hamburg.de/ports/8053";
	Retrofit retro;
	VerzeichnisdienstInterface verzeichnisdienst;

	public static Verzeichnisdienst init() {
		if (verzeichnisdienstImpl == null)
			verzeichnisdienstImpl = new Verzeichnisdienst();
		return verzeichnisdienstImpl;
	}
	private Verzeichnisdienst() {
		this.verzeichnisdienst = ServiceGenerator.createService(VerzeichnisdienstInterface.class);
	}

	public VerzeichnisdienstInterface getVerzeichnisdienstInterface() {
		return this.verzeichnisdienst;
	}

	public String getServices() {
		Gson result = new Gson();
		String json = "";
		try {
			json = result.toJson(verzeichnisdienst.holeService().execute().body());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;

	}

	public String holeServiceById(String name) {
		Gson result = new Gson();
		String json = "";
		try {
			json = result.toJson(verzeichnisdienst.holeServiceByName(name).execute().body());
			// result = GsonBuilder verzeichnisdienst.holeServiceByName(name);
			// muss wahscheinlich noch per JSON geparst werden!!! Also das er
			// eine Liste mit vielen Services
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
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
