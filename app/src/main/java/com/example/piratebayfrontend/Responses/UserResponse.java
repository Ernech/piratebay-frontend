package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.UserCallBack;
import com.example.piratebayfrontend.Model.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserResponse {

    String authToken;

    public UserResponse(){

    }

    public UserResponse(String authToken){
        this.authToken=authToken;
    }

    public void getUsersList (final UserCallBack callBack){

        Call<ArrayList<UserModel>> call = MyApiAdapter.getApiService().getUsers("bearer "+authToken);
        call.enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<UserModel> getUsersResponse = response.body();
                    Log.d("onResponse", getUsersResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getUsersResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {

            }
        });
    }
}
