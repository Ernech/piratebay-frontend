package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.piratebayfrontend.R;

public class WarehousesActivity extends AppCompatActivity {
    CardView cvWareHouseLaPaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouses);
        bindUI();
        cvWareHouseLaPaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindUI(){
        cvWareHouseLaPaz = findViewById(R.id.cvAlmacenLP);
    }
}