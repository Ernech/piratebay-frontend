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

    public static String WAREHOUSE_LA_PAZ = "La Paz";
    public static String WAREHOUSE_SANTA_CRUZ="Santa Cruz";
    public static String WAREHOUSE_COCHABAMBA = "Cochabamba";
    public static String WAREHOUSE_TARIJA="Tarija";
    public static String WAREHOUSE_SUCRE="Sucre";
}
