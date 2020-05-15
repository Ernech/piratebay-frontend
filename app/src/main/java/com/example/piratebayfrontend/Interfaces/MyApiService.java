package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.CredentialModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiService {
    //Autenticación login
   @POST("/api/v1/security/login")
   Call<ArrayList<CredentialModel>> getLogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
