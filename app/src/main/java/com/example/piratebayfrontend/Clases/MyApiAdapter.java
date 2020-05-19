package com.example.piratebayfrontend.Clases;

import com.example.piratebayfrontend.Interfaces.MyApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyApiAdapter {
    private static MyApiService API_SERVICE;
    public static MyApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrlMariana =  "http://192.168.0.104:8008/api/v1/";
        String baseUrlErnesto =  "http://192.168.0.112:8008/api/v1/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrlErnesto)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(MyApiService.class);
        }

        return API_SERVICE;
    }

}
