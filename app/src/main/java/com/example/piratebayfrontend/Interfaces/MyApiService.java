package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.KardexInformationModel;
import com.example.piratebayfrontend.Model.KardexModel;
import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.Model.OrderModel;
import com.example.piratebayfrontend.Model.UserModel;
import com.example.piratebayfrontend.Model.WarehouseModel;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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

    //Obtener Almacenes
    @Headers({"Accept: application/json"})
    @GET("warehouse")
    Call<ArrayList<WarehouseModel>> getAllWareHouses (@Header("Authorization") String authToken);

    // Obtener lista de películas por almacen y nombre
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("movies/search")
    Call<ArrayList<MovieModel>> getMoviesByParameter (@Header("Authorization") String authToken,  @Body RequestBody requestBody);
    //Ordenar películas por parámetro
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("movies/sort")
    Call<ArrayList<MovieModel>> getMoviesOrderedByParameter (@Header("Authorization") String authToken,  @Body RequestBody requestBody);
    //Obtener películas por nombre ordenadas por parámetro
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("movies/search/sort")
    Call<ArrayList<MovieModel>> getMoviesByNameSortedByParameter (@Header("Authorization") String authToken,  @Body RequestBody requestBody);
    //Recibir elementos del kardex
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("kardex")
    Call<ArrayList<KardexModel>> getKardexElements (@Header("Authorization") String authToken, @Body RequestBody requestBody);
    //Recibir elementos del kardex
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("kardex/information")
    Call<KardexInformationModel> getKardexInformation (@Header("Authorization") String authToken, @Body RequestBody requestBody);
    //Recibir Ordenes
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @POST("orders/not_received")
    Call<ArrayList<OrderModel>> getOrders (@Header("Authorization") String authToken, @Body RequestBody requestBody);

    //Recibir Ordenes
    @Headers({"Content-Type: application/json",
            "Accept: application/json"})
    @PUT("kardex/update")
    Call<Map<String,String>> updateKardex (@Header("Authorization") String authToken, @Body RequestBody requestBody);
}
