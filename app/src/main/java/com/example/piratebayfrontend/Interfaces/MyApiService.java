package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.CredentialModel;
import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.Model.UserModel;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyApiService {
    //Autenticación login
    @Headers({"Content-Type: application/json",
              "Accept: application/json"})
    @POST("security/login")
    Call<Map<String,Object>> getLogin(
            @Body RequestBody requestBody
    );

    //Obtener nuevos tokens
    @Headers({"Content-Type: application/json",
              "Accept: application/json"})
    @POST("security/refresh")
    Call<Map<String,Object>> getNewTokens(
            @Body RequestBody requestBody
    );

    // Mandar el Authentication Token para obtener los usuarios
    @Headers({"Accept: application/json"})
    @GET("user")
    Call<ArrayList<UserModel>> getUsers (@Header("Authorization") String authToken);

    // Obtener lista de películas por almacen
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("movies")
    Call<ArrayList<MovieModel>> getMovies (@Header("Authorization") String authToken,  @Body RequestBody requestBody);
    // Obtener lista de películas por almacen y nombre
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("movies/search")
    Call<ArrayList<MovieModel>> getMoviesByParameter (@Header("Authorization") String authToken,  @Body RequestBody requestBody);
    //Ordenar películas por parámetro
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("movies/order")
    Call<ArrayList<MovieModel>> getMoviesOrderedByParameter (@Header("Authorization") String authToken,  @Body RequestBody requestBody);
}
