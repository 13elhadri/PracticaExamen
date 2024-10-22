package org.example.api;

import org.example.api.createupdatedelete.RequestUCD;
import org.example.api.createupdatedelete.ResponseUCD;
import org.example.api.getAll.ResponseGetAll;
import org.example.api.getById.ResponseGetById;
import org.example.models.Tenista;
import retrofit2.http.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TenistaApiRest {

    String API_URL = "https://my-json-server.typicode.com/joseluisgs/KotlinLocalAndRemote/tenistas";


    @GET("tenistas")
    CompletableFuture<List<ResponseGetAll>> getAll();


    @GET("tenistas/{id}")
    CompletableFuture<ResponseGetById> getById(@Path("id") int id);

    @POST("tenistas")
    CompletableFuture<ResponseUCD> create(@Body RequestUCD tenista);

    @PUT("tenistas/{id}")
    CompletableFuture<ResponseUCD> update(@Path("id") int id, @Body RequestUCD tenista);

    @DELETE("users/{id}")
    CompletableFuture<ResponseUCD>delete(@Path("id") int id);
}
