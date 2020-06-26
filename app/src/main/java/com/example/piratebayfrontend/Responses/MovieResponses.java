package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.MoviesCallBack;
import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.Model.UserModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieResponses {
    String authToken;
    String warehouseName;

    public MovieResponses() {
    }

    public MovieResponses(String authToken,String warehouseName) {
        this.authToken = authToken;
        this.warehouseName = warehouseName;
    }
    public void getMoviesListByWarehouse(final MoviesCallBack callBack){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("warehouse",warehouseName);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<MovieModel>> call = MyApiAdapter.getApiService().getMovies("bearer "+authToken,body);
        call.enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<MovieModel> getMoviesResponse = response.body();
                    Log.d("onMoviesResponse", getMoviesResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getMoviesResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onMoviesResponse", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                Log.d("onMoviesResponse", "Error "+t);
            }
        });
    }
    public void getMoviesListByWarehouseAndName(String name,final MoviesCallBack callBack){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("warehouse",warehouseName);
        jsonParams.put("searchParameter",name);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<MovieModel>> call = MyApiAdapter.getApiService().getMoviesByParameter("bearer "+authToken,body);
        call.enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<MovieModel> getMoviesResponse = response.body();
                    Log.d("onMoviesResponseP", getMoviesResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getMoviesResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onMoviesResponseP", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                Log.d("onMoviesResponse", "Error "+t);
            }
        });
    }
}
