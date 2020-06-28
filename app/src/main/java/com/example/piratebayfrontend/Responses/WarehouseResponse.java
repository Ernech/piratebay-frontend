package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.WarehouseCallBack;
import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.Model.WarehouseModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WarehouseResponse {

    String authToken;

    public WarehouseResponse(){

    }
    public WarehouseResponse(String authToken){
        this.authToken = authToken;
    }

    public void getWarehouses(final WarehouseCallBack callBack){
        Call<ArrayList<WarehouseModel>> call = MyApiAdapter.getApiService().getAllWareHouses("bearer "+authToken);
        call.enqueue(new Callback<ArrayList<WarehouseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<WarehouseModel>> call, Response<ArrayList<WarehouseModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<WarehouseModel> getWarehouseResponse = response.body();
                    Log.d("onWarehouseResponse", getWarehouseResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getWarehouseResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onWarehouseResponse", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WarehouseModel>> call, Throwable t) {
                Log.d("onWarehouseResponse", "Error "+t);
            }
        });
    }

}
