package com.example.piratebayfrontend.Responses;

import android.util.Log;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Clases.Tabla;
import com.example.piratebayfrontend.Interfaces.KardexCallBack;
import com.example.piratebayfrontend.Model.KardexModel;
import com.example.piratebayfrontend.Model.MovieModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KardexResponse {

    String authToken;
    String warehouseName;
    int idMovie;

    public KardexResponse(){

    }

    public KardexResponse(String authToken, String warehouseName, int idMovie) {
        this.authToken = authToken;
        this.warehouseName = warehouseName;
        this.idMovie = idMovie;
    }

    public void getKardexElements(final KardexCallBack callBack){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("warehouse",warehouseName);
        jsonParams.put("parameter",idMovie);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ArrayList<KardexModel>> call = MyApiAdapter.getApiService().getKardexElements("bearer "+authToken,body);
        call.enqueue(new Callback<ArrayList<KardexModel>>() {
            @Override
            public void onResponse(Call<ArrayList<KardexModel>> call, Response<ArrayList<KardexModel>> response) {
                if(response.isSuccessful()) {
                    ArrayList<KardexModel> getKardexElementsResponse = response.body();
                    Log.d("onKardexResponse", getKardexElementsResponse + "");
                    if (response.code() == 200) {
                        callBack.onSuccess(true, getKardexElementsResponse);
                    }
                } else {
                    if (response.code() == 500) {
                        Log.d("onMKardexResponse", "Error");
                        callBack.onSuccess(false,null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<KardexModel>> call, Throwable t) {
                Log.d("onKardexResponse", "Error "+t);
            }
        });
    }
    public void fillKardex(ArrayList<KardexModel> kardexElements, Tabla tabla){
        for(int i=0; i<kardexElements.size(); i++){
            ArrayList<String> kardexElement = new ArrayList<String>();
            kardexElement.add(kardexElements.get(i).getDateReceived());
            kardexElement.add(kardexElements.get(i).getConcept());
            kardexElement.add(kardexElements.get(i).getReceipt());
            kardexElement.add(kardexElements.get(i).getUnitValue().toString());
            kardexElement.add(kardexElements.get(i).getInputQuantity().toString());
            kardexElement.add(kardexElements.get(i).getInputValue().toString());
            kardexElement.add(kardexElements.get(i).getBalanceQuantity().toString());
            kardexElement.add(kardexElements.get(i).getBalanceValue().toString());
            tabla.agregarFilaTabla(kardexElement);
        }
    }
}