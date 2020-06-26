package com.example.piratebayfrontend.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.MovieListAdapter;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Interfaces.MoviesCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.MovieModel;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.MovieResponses;
import com.example.piratebayfrontend.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Map;

public class MoviesListActivity extends AppCompatActivity {
    EditText etSearch;
    RecyclerView rvMovieList;
    Spinner spSearchOptions;
    MovieListAdapter movieListAdapter;
    ArrayList<MovieModel> movieList;
    String warehouseName;
    Map<String, String> tokens;
    int indexSpinner=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        tokens = TokensControl.retrieveTokens(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        warehouseName = bundle.getString(Utilities.WAREHOUSE);
        bindUI();
        getMovies();
        ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Utilities.searchOptions());
        spSearchOptions.setAdapter(spinnerAdapter);
        spSearchOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(indexSpinner>0){
                    Toast.makeText(getApplicationContext(),"Item "+ Utilities.searchOptions().get(i), Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if("".equals(etSearch.getText().toString().trim())){
                        getMovies();
                    }
                    else{
                       // Toast.makeText(getApplicationContext(),etSearch.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                        getMoviesByParameter(etSearch.getText().toString().trim());
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    private void bindUI(){
        etSearch=findViewById(R.id.etBuscar);
        rvMovieList=findViewById(R.id.rvProd);
        spSearchOptions =findViewById(R.id.spCategoria);
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

    private void getMovies(){
        MovieResponses movieResponses = new MovieResponses(tokens.get(Utilities.AUTHENTICATION_TOKEN),warehouseName);
        movieResponses.getMoviesListByWarehouse(new MoviesCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<MovieModel> moviesList) {
                if(value && moviesList!=null){
                    setMovieListAdapter(moviesList);
                }
                else{
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getMoviesByParameter(String movieName){
        MovieResponses movieResponses = new MovieResponses(tokens.get(Utilities.AUTHENTICATION_TOKEN),warehouseName);
        movieResponses.getMoviesListByWarehouseAndName(movieName,new MoviesCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<MovieModel> moviesList) {
                if(value && moviesList!=null){
                    setMovieListAdapter(moviesList);
                }
                else{
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setMovieListAdapter(ArrayList<MovieModel> moviesList){
        movieList=moviesList;
        rvMovieList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        movieListAdapter = new MovieListAdapter(movieList,getApplicationContext());
        rvMovieList.setAdapter(movieListAdapter);
        movieListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ID movie: "+movieList.get(rvMovieList.getChildAdapterPosition(view)).getProductId(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}