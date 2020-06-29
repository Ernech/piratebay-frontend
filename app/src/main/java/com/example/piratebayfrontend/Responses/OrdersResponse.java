package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.OrdersCallBack;
import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.Model.OrderModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersResponse {

    String authToken;
    int warehouseId;
    int productId;

    public OrdersResponse(){

    }

    public OrdersResponse(String authToken, int warehouseId, int productId) {
        this.authToken = authToken;
        this.warehouseId = warehouseId;
        this.productId = productId;
    }

    public void getOrdersByWarehouseAndProduct(final OrdersCallBack callBack){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("warehouseId",warehouseId);
        jsonParams.put("productId",productId);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<OrderModel>> call = MyApiAdapter.getApiService().getOrders("bearer "+authToken,body);
        call.enqueue(new Callback<ArrayList<OrderModel>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderModel>> call, Response<ArrayList<OrderModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<OrderModel> getOrdersResponse = response.body();
                    Log.d("onOrdersResponse", getOrdersResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getOrdersResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onOrdersResponse", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OrderModel>> call, Throwable t) {
                Log.d("onOrdersResponse", "Error "+t);
            }
        });

    }

}
