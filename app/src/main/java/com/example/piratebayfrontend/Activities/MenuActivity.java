package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.JWTUtils;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Clases.VerifyFeatures;
import com.example.piratebayfrontend.Controladores.RefreshTokenController;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.CredentialModel;
import com.example.piratebayfrontend.R;

import org.json.JSONArray;

import java.text.Normalizer;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    CardView cvUsuarios;
    CardView cvProductos;
    CardView cvSalir;

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

        if(hasFeaturePageProductManagement){
            cvProductos.setVisibility(View.VISIBLE);
        }

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
        cvSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TokensControl.removeTokens(getApplicationContext());
                logOut();
            }
        });
    }

    private void bindUI(){
        cvUsuarios = findViewById(R.id.cvUsuarios);
        cvProductos = findViewById(R.id.cvGestionProductos);
        cvSalir = findViewById(R.id.cvSalir);
    }

    private void logOut(){
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
