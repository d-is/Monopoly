package retrofit;

import java.io.IOException;

public class ClintImpl {

	public static void init() {
		Retrofit retro = new Retrofit.Builder().baseUrl("http://localhost:4567")
				.addConverterFactory(GsonConverterFactory.create()).build();
		ClientInterface client = retro.create(ClientInterface.class);
	}
}
