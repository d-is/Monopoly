package de.vs.monopoly.verzeichnisdienst;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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
	final String URL = "https://vs-docker.informatik.haw-hamburg.de/ports/8053";
	Retrofit retro;
	VerzeichnisdienstInterface verzeichnisdienst;

	public static VerzeichnisdienstImpl init() {
		if (verzeichnisdienstImpl == null)
			verzeichnisdienstImpl = new VerzeichnisdienstImpl();
		return verzeichnisdienstImpl;

	}

	private VerzeichnisdienstImpl() {
		OkHttpClient httpClient = Util.getUnsafeOkHttpClient();

		retro = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
				.client(httpClient).build();
		verzeichnisdienst = retro.create(VerzeichnisdienstInterface.class);
	}

	public VerzeichnisdienstInterface getVerzeichnisdienstInterface() {
		return this.verzeichnisdienst;
	}

	public String getServices() {
		Gson result = new Gson();
		String json = "";
		try {
			verzeichnisdienst.registriereService("blub").execute();
			
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


