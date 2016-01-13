package de.vs.monopoly.verzeichnisdienst;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface VerzeichnisdienstInterface {

@GET("services")
Call<Object> holeService();

@POST("/services")
Call<Void> registriereService(@Body Object service);

@GET("/services/{id}")
Call<Object> holeServiceById(@Path("id") String id);

@PUT("/services/{id}")
Call<Void> registriereServiceById(@Path("id") String id, @Body Object service);

@DELETE("/services/{id}")
Call<Void> loescheServiceById(@Path("id") String id);

@GET("/services/of/name/{name}")
Call<Object> holeServiceByName(@Path("name") String name);

@GET("/services/of/type/{type}")
Call<Object> holeServiceByType(@Path("type") String type);


}
