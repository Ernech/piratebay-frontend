package com.example.piratebayfrontend.Utilities;

import com.example.piratebayfrontend.Model.MovieModel;

import java.util.ArrayList;

public class Utilities {
    public static ArrayList<String>searchOptions(){
        ArrayList<String> searchOptionsMovieList=new ArrayList<>();
        searchOptionsMovieList.add("Ordenar por");
        searchOptionsMovieList.add("Título");
        searchOptionsMovieList.add("Formato");
        searchOptionsMovieList.add("Fecha de creación");
        searchOptionsMovieList.add("Proveedor");
        searchOptionsMovieList.add("Cantidad");
        return searchOptionsMovieList;
    }
    public static String AUTHENTICATION_TOKEN="authentication";
    public static String REFRESH_TOKEN="refresh";

    public static String WAREHOUSE ="warehouse";



    public static String PRODUCT_NAME="product_name";
    public static String PROVIDER_NAME="provider_name";
    public static String FORMAT = "FORMAT";
    public static String CREATION_DATE="created_date";
    public static String QTYY_RECEIVED = "qtty_received";
}
