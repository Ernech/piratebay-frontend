package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.CredentialModel;
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
}
