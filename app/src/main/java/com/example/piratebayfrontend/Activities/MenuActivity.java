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
import com.example.piratebayfrontend.Controladores.RefreshTokenController;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.CredentialModel;
import com.example.piratebayfrontend.R;

import org.json.JSONArray;

import java.text.Normalizer;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    Map<String, String> tokens;
    RefreshTokenController refreshTokenController;
    Button btnRefresh;
    CardView cvUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bindUI();
        // Se recuperan los tokens generados
        tokens = TokensControl.retrieveTokens(getApplicationContext());

        try{
            JSONArray features = JWTUtils.getFeaturesFromJWT(tokens.get("authentication"));

            System.out.println(features.get(0));
            System.out.println(features.get(1));
            System.out.println(features.get(2));
        }catch (Exception e){
            Log.e("Error",e+"");
        }

        cvUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, UsersListActivity.class);
                startActivity(intent);
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshTokenController = new RefreshTokenController(tokens.get("refresh"));
                refreshTokenController.sendToPostRefreshToken(new RefreshTokenCallBack() {
                    @Override
                    public void onSuccess(boolean value, Object authnTokenN, Object refreshTokenN) {
                        if(value && authnTokenN!=null && refreshTokenN!=null){
                            System.out.println("ATUHNN !!!!!!!!!!!!!!!!1"+authnTokenN);
                            System.out.println("REFRESHN !!!!!!!!!!!!!!!!!!!"+refreshTokenN);
                        }
                    }
                });
            }
        });
    }

    private void bindUI(){
        btnRefresh = findViewById(R.id.btnRefresh);
        cvUsuarios = findViewById(R.id.cvUsuarios);
    }
}
