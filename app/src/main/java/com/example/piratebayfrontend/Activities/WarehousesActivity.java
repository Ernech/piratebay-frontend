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
import android.widget.Toast;


import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Clases.WarehouseAdapter;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.Interfaces.WarehouseCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.WarehouseModel;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.RefreshTokenResponse;
import com.example.piratebayfrontend.Responses.WarehouseResponse;
import com.example.piratebayfrontend.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Map;

public class WarehousesActivity extends AppCompatActivity {

    RecyclerView rvWarehouses;
    Map<String,String> tokens;
    ArrayList<WarehouseModel> warehouses;
    WarehouseAdapter warehouseAdapter;
    RefreshTokenResponse refreshTokenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouses);
        tokens = TokensControl.retrieveTokens(getApplicationContext());
        bindUI();
        getWarehouses();
    }

    private void bindUI(){
      rvWarehouses = findViewById(R.id.rvWarehouses);
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

    private void getWarehouses(){
        WarehouseResponse warehouseResponse = new WarehouseResponse(tokens.get(Utilities.AUTHENTICATION_TOKEN));
        warehouseResponse.getWarehouses(new WarehouseCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<WarehouseModel> warehouseList) {
                if (value && warehouseList!=null){
                    setWarehouseAdapter(warehouseList);
                }
            }
        });
    }

    private void setWarehouseAdapter(final ArrayList<WarehouseModel> warehouseList){
        warehouses = warehouseList;
        rvWarehouses.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        warehouseAdapter = new WarehouseAdapter(getApplicationContext(),warehouseList);
        rvWarehouses.setAdapter(warehouseAdapter);
        warehouseAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshTokens();
                Intent intent = new Intent(getApplicationContext(),MoviesListActivity.class);
                intent.putExtra(Utilities.WAREHOUSE,warehouseList.get(rvWarehouses.getChildAdapterPosition(view)).getWarehouseId());
                startActivity(intent);
            }
        });
    }

    private void refreshTokens(){
        refreshTokenController = new RefreshTokenResponse(tokens.get("refresh"));
        refreshTokenController.sendToPostRefreshToken(new RefreshTokenCallBack() {
            @Override
            public void onSuccess(boolean value, Object newAuthnToken, Object newRefreshToken) {
                if(value && newAuthnToken!=null && newRefreshToken!=null){
                    TokensControl.saveTokens(newAuthnToken, newRefreshToken, getApplicationContext());
                    tokens.clear();
                    tokens = TokensControl.retrieveTokens(getApplicationContext());
                    //  authnTokenExpired =false;
                } else {
                    TokensControl.removeTokens(getApplicationContext());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });
    }
}