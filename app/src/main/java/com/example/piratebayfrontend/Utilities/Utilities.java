package com.example.piratebayfrontend.Utilities;

import java.util.ArrayList;

public class Utilities {
    public static ArrayList<String>searchOptions(){
        ArrayList<String> searchOptionsMovieList=new ArrayList<>();
        searchOptionsMovieList.add("Ordenar por");
        searchOptionsMovieList.add("Título");
        searchOptionsMovieList.add("Código");
        searchOptionsMovieList.add("Fecha de creación");
        searchOptionsMovieList.add("Cantidad");
        return searchOptionsMovieList;
    }
    public static String AUTHENTICATION_TOKEN="authentication";
    public static String REFRESH_TOKEN="refresh";

    public static String WAREHOUSE ="warehouse";



    public static String PRODUCT_NAME="product_name";
    public static String PRODUCT_CODE="product_code";
    public static String CREATION_DATE="creation_date";
    public static String QTTY_RECEIVED = "qtty_received";
}
