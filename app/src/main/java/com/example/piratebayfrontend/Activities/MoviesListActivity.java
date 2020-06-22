package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.MovieListAdapter;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Utilities.Utilities;

public class MoviesListActivity extends AppCompatActivity {
    EditText etSearch;
    RecyclerView rvMovieList;
    Spinner spSearchOptions;
    MovieListAdapter movieListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        bindUI();
        movieListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"ID: "+Utilities.movies().get(rvMovieList.getChildAdapterPosition(view)).getIdMovie(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void bindUI(){
        etSearch=findViewById(R.id.etBuscar);
        rvMovieList=findViewById(R.id.rvProd);
        spSearchOptions =findViewById(R.id.spCategoria);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));
        movieListAdapter = new MovieListAdapter(Utilities.movies(),this);
        rvMovieList.setAdapter(movieListAdapter);
        ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Utilities.searchOptions());
        spSearchOptions.setAdapter(spinnerAdapter);

    }
}