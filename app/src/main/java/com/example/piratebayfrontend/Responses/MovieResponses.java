package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.MoviesCallBack;
import com.example.piratebayfrontend.Model.MovieModel;

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
   int warehouseId;

    public MovieResponses() {
    }

    public MovieResponses(String authToken,int warehouseId) {
        this.authToken = authToken;
        this.warehouseId = warehouseId;
    }
    public void getMoviesListByWarehouse(final MoviesCallBack callBack){
        Call<ArrayList<MovieModel>> call = MyApiAdapter.getApiService().getMovies("bearer "+authToken,warehouseId);
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
        jsonParams.put("searchParameter",name);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<MovieModel>> call = MyApiAdapter.getApiService().getMoviesByParameter("bearer ",warehouseId,body);
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
    public void sortMoviesListByWarehouseAndParameter(String parameter,final MoviesCallBack callBack){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("sortParameter",parameter);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<MovieModel>> call = MyApiAdapter.getApiService().getMoviesOrderedByParameter("bearer "+authToken,warehouseId,body);
        call.enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<MovieModel> getMoviesResponse = response.body();
                    Log.d("onSortResponseP", getMoviesResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getMoviesResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onSortResponseP", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                Log.d("onSortResponse", "Error "+t);
            }
        });
    }
    public void getMoviesByNameSortedByParameter(String name, String parameter, final MoviesCallBack callBack){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("searchParameter",name);
        jsonParams.put("sortParameter",parameter);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<MovieModel>> call = MyApiAdapter.getApiService().getMoviesByNameSortedByParameter("bearer "+authToken,warehouseId,body);
        call.enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<MovieModel> getMoviesResponse = response.body();
                    Log.d("onSortNameResponseP", getMoviesResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getMoviesResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onSortNameResponseP", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                Log.d("onSortResponse", "Error "+t);
            }
        });
    }
}
