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
    public static ArrayList<MovieModel>movies(){
        ArrayList<MovieModel> moviesList=new ArrayList<>();
       moviesList.add(new MovieModel(1,"A la hora señalada","DVD","20/04/2020","Piratas Perú",80) );
        moviesList.add(new MovieModel(2,"África Mia","DVD","21/04/2020","Colopelículas",100) );
        moviesList.add(new MovieModel(3,"Al otro lado del viento","Blu ray","21/04/2020","Piratas Perú",90) );
        moviesList.add(new MovieModel(4,"Al otro lado del viento","Blu ray","23/04/2020","Piratas Perú",120) );
        moviesList.add(new MovieModel(5,"Blade runner: The final cut","DVD","24/04/2020","Colopelículas",30) );
        moviesList.add(new MovieModel(6,"Avengers Endgame","DVD","24/04/2020","Colopelículas",30) );


        return moviesList;
    }
}
