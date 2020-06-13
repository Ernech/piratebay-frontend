package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.JWTUtils;
import com.example.piratebayfrontend.Controladores.RefreshTokenController;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.CredentialModel;
import com.example.piratebayfrontend.R;

import java.text.Normalizer;

public class MenuActivity extends AppCompatActivity {
    Bundle bundle;
    Object authnToken, refreshToken;
    Button btnRefresh;
    RefreshTokenController refreshTokenController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnRefresh = findViewById(R.id.btnRefresh);
        CardView cvUsuarios = findViewById(R.id.cvUsuarios);
        bundle = getIntent().getExtras();
        authnToken = bundle.get("authnToken");
        refreshToken = bundle.get("refreshToken");
        System.out.println("ATUHN2 "+authnToken);
        System.out.println("REFRESH2 "+refreshToken);
        try{
            JWTUtils.getFeaturesFromJWT(authnToken.toString());
        }catch (Exception e){
            Log.e("Error",e+"");
        }

        cvUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshTokenController = new RefreshTokenController(refreshToken);
                refreshTokenController.sendToPostRefreshToken(new RefreshTokenCallBack() {
                    @Override
                    public void onSuccess(boolean value, Object authnTokenN, Object refreshTokenN) {
                        if(value && authnTokenN!=null && refreshTokenN!=null){
                            System.out.println("ATUHNN "+authnTokenN);
                            System.out.println("REFRESHN "+refreshTokenN);
                        }
                    }
                });
            }
        });

    }
}
