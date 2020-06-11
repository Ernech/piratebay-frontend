package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.CredentialModel;
import com.example.piratebayfrontend.R;

import java.text.Normalizer;

public class MenuActivity extends AppCompatActivity {
    Bundle bundle;
    Object authnToken, refreshToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CardView cvUsuarios = findViewById(R.id.cvUsuarios);
        bundle = getIntent().getExtras();
        authnToken = bundle.get("authnToken");
        refreshToken = bundle.get("refreshToken");
        System.out.println("ATUHN2 "+authnToken);
        System.out.println("REFRESH2 "+refreshToken);
        cvUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

    }
}
