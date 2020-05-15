package com.example.piratebayfrontend.Interfaces;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyApiService {
    //Autenticación login
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
   @POST("security/login")
   Call<Map<String,Object>> getLogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
