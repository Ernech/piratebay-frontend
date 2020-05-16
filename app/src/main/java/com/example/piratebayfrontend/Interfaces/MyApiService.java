package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.CredentialModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
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

}
