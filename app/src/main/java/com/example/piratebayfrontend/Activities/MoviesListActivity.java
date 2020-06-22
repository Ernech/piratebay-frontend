package com.example.piratebayfrontend.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.MovieListAdapter;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.MainActivity;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cmLogOut2:logOut();
                return true;
            case R.id.cmReturnMenu: returnMenu();
                return true;
            default:
                return false;
        }
    }
    private void logOut(){
        TokensControl.removeTokens(getApplicationContext());
        Intent intent = new Intent(MoviesListActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void returnMenu(){
        Intent intent = new Intent(MoviesListActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}