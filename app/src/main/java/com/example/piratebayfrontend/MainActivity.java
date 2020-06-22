package com.example.piratebayfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piratebayfrontend.Activities.MenuActivity;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Responses.LoginResponse;
import com.example.piratebayfrontend.Interfaces.LoginCallBack;
import com.example.piratebayfrontend.Model.CredentialModel;

public class MainActivity extends AppCompatActivity {

    LoginResponse loginResponse;
    EditText etUserName,etPassword;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etUserName.getText().toString().trim()) || TextUtils.isEmpty(etPassword.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Complete los campos",Toast.LENGTH_SHORT).show();
                }
                else{
                    CredentialModel credentialModel = new CredentialModel();
                    credentialModel.setUsername(etUserName.getText().toString().trim());
                    credentialModel.setPassword(etPassword.getText().toString().trim());
                    loginResponse = new LoginResponse(credentialModel);
                    loginResponse.sendToPostLogin(new LoginCallBack() {
                        @Override
                        public void onSuccess(boolean value, Object authnToken, Object refreshToken) {
                            if(value && authnToken!=null && refreshToken!=null){
                                // Guardar los tokens en Shared Preferences
                                TokensControl.saveTokens(authnToken, refreshToken, getApplicationContext());

                                // Ir al menú
                                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void bindUI(){
        etUserName = findViewById(R.id.etNomUs);
        etPassword = findViewById(R.id.etPass);
        btnEnter = findViewById(R.id.btnEnter);
    }
}
