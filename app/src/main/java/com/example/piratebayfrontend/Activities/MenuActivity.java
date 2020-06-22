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
import com.example.piratebayfrontend.Clases.VerifyFeatures;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.R;

public class MenuActivity extends AppCompatActivity {

    CardView cvKardex;
    CardView cvUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bindUI();

        // Verificar las features de un usuario
        boolean hasFeaturePageProductManagement = VerifyFeatures.hasFeature(getApplicationContext(), "PAGE_PRODUCT_MANAGEMENT");
        boolean hasFeaturePageUserManagement = VerifyFeatures.hasFeature(getApplicationContext(), "PAGE_USER_MANAGEMENT");

        // Admin tiene disponibles las tres features.
        // Warehouse Supervisor no tiene disponible el botón para eliminar usuarios.
        // Warehouse Employee sólo tiene disponible la página de productos.


        if(hasFeaturePageUserManagement){
            cvUsuarios.setVisibility(View.VISIBLE);
        }

        cvUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, UsersListActivity.class);
                startActivity(intent);
            }
        });

        cvKardex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, WarehousesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindUI(){
        cvUsuarios = findViewById(R.id.cvUsuarios);
        cvKardex = findViewById(R.id.cvKardex);
    }

    private void logOut(){
        TokensControl.removeTokens(getApplicationContext());
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cmLogOut:logOut();
                return true;
            default:
                return false;
        }
    }
}
