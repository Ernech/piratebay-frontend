package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.Utilities.Utilities;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefreshTokenResponse implements Callback<Map<String, Object>> {
    Object refreshToken;

    public RefreshTokenResponse() {
    }

    public RefreshTokenResponse(Object refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void sendToPostRefreshToken(final RefreshTokenCallBack callBack){
        //Mapa para pasar los campos del formulario
        Map<String, Object> jsonParams = new HashMap<>();
        //Colocar los datos en el mapa
        jsonParams.put("refreshModel",refreshToken);
        //Pasar los datos del mapa en formato JSON
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<Map<String,Object>> call = MyApiAdapter.getApiService().getNewTokens(body);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.isSuccessful()) {
                    Map<String, Object> postRefreshTokenResponse = response.body();
                    Log.d("onResponse", postRefreshTokenResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true,postRefreshTokenResponse.get(Utilities.AUTHENTICATION_TOKEN),postRefreshTokenResponse.get(Utilities.REFRESH_TOKEN));
                    }
                } else {
                    if (response.code() == 500) {
                        callBack.onSuccess(false,null,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

    }

    @Override
    public void onFailure(Call<Map<String, Object>> call, Throwable t) {

    }
}
