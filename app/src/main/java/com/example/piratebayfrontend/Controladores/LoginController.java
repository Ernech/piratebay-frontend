package com.example.piratebayfrontend.Controladores;

import android.util.Log;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Interfaces.LoginCallBack;
import com.example.piratebayfrontend.Model.CredentialModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {

    CredentialModel credentialModel;
    public LoginController (CredentialModel credentialModel){
        this.credentialModel=credentialModel;
    }

    public void sendToPostLogin(final LoginCallBack callBack){
        //Mapa para pasar los campos del formulario
        Map<String, Object> jsonParams = new HashMap<>();

        //Colocar los datos en el mapa
        jsonParams.put("username",credentialModel.getUsername());
        jsonParams.put("password",credentialModel.getPassword());

        //Pasar los datos del mapa en formato JSON
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());

        Call<Map<String,Object>> call = MyApiAdapter.getApiService().getLogin(body);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.isSuccessful()) {
                    Map<String, Object> postLoginResponse = response.body();
                    Log.d("onResponse", postLoginResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, postLoginResponse.get("authentication"), postLoginResponse.get("refresh"));
                    }
                } else {
                    if (response.code() == 403) {
                        callBack.onSuccess(false,null,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });
    }
}
