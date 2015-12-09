package de.vs.monopoly.client;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitRest {

	public static void main(String[] args){
		Retrofit retro = new Retrofit.Builder().baseUrl("http://localhost:4567").addConverterFactory(GsonConverterFactory.create()).build();
		ClientInterface client = retro.create(ClientInterface.class);
		try {
			System.out.println(client.erstelleSpiel().execute().body());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
