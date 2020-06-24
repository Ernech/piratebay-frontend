package com.example.piratebayfrontend.Interfaces;


import com.example.piratebayfrontend.Model.MovieModel;

import java.util.ArrayList;

public interface MoviesCallBack {
    void onSuccess(boolean value, ArrayList<MovieModel> moviesList);
}
