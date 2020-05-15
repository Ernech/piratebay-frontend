package com.example.piratebayfrontend.Clases;

import com.example.piratebayfrontend.Interfaces.MyApiService;

import okhttp3.logging.HttpLoggingInterceptor;

public class MyApiAdapter {

    private MyApiService myApiService;
    // Creamos un interceptor y le indicamos el log level a usar
    HttpLoggingInterceptor login = new HttpLoggingInterceptor();
       // login.setLevel(HttpLoggingInterceptor.)
}
