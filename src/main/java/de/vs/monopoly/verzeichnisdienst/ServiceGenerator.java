package de.vs.monopoly.verzeichnisdienst;

import java.io.IOException;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

	public static final String API_BASE_URL = "https://vs-docker.informatik.haw-hamburg.de/ports/8053/";

	private static OkHttpClient httpClient = Util.getUnsafeOkHttpClient();
	private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create());

	public static <S> S createService(Class<S> serviceClass) {

		final String basic = "Basic " + "YWJvNDc2OkRFMTAwNnN1ODc=";

		httpClient.interceptors().clear();
		httpClient.interceptors().add(new Interceptor() {
			@Override
			public Response intercept(Interceptor.Chain chain) throws IOException {
				Request original = chain.request();

				Request.Builder requestBuilder = original.newBuilder().header("Authorization", basic);
				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});

		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}

	public static <S> S createService(Class<S> serviceClass, String urlToRegister) {
		
		 OkHttpClient httpClient = Util.getUnsafeOkHttpClient();
		Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://"+urlToRegister)
				.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}
}