package com.example.piratebayfrontend.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Utilities.Utilities;

public class WarehousesActivity extends AppCompatActivity {
    CardView cvWareHouseLaPaz;
    CardView cvWareHouseSantaCruz;
    CardView cvWareHouseCochabamba;
    CardView cvWareHouseTarija;
    CardView cvWareHouseSucre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouses);
        bindUI();
        cvWareHouseLaPaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                intent.putExtra(Utilities.WAREHOUSE,Utilities.WAREHOUSE_LA_PAZ);
                startActivity(intent);
            }
        });
        cvWareHouseSantaCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                intent.putExtra(Utilities.WAREHOUSE,Utilities.WAREHOUSE_SANTA_CRUZ);
                startActivity(intent);
            }
        });
        cvWareHouseCochabamba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                intent.putExtra(Utilities.WAREHOUSE,Utilities.WAREHOUSE_COCHABAMBA);
                startActivity(intent);
            }
        });
        cvWareHouseTarija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                intent.putExtra(Utilities.WAREHOUSE,Utilities.WAREHOUSE_TARIJA);
                startActivity(intent);
            }
        });
        cvWareHouseSucre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                intent.putExtra(Utilities.WAREHOUSE,Utilities.WAREHOUSE_SUCRE);
                startActivity(intent);
            }
        });
    }

    private void bindUI(){
        cvWareHouseLaPaz = findViewById(R.id.cvAlmacenLP);
        cvWareHouseSantaCruz = findViewById(R.id.cvAlmacenSC);
        cvWareHouseCochabamba = findViewById(R.id.cvAlmacenCB);
        cvWareHouseTarija = findViewById(R.id.cvAlmacenTJ);
        cvWareHouseSucre = findViewById(R.id.cvAlmacenSU);
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
        Intent intent = new Intent(WarehousesActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void returnMenu(){
        Intent intent = new Intent(WarehousesActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}