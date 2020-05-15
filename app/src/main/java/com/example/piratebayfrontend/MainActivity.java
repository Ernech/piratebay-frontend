package com.example.piratebayfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.MyApiAdapter;
import com.example.piratebayfrontend.Model.CredentialModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Map<String, Object>> {
    EditText etUserName,etPassword;
    Button btnenter;
    Call<Map<String,Object>> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etUserName.getText().toString().trim()) || TextUtils.isEmpty(etPassword.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Complete los campos",Toast.LENGTH_SHORT).show();
                }
                else{
                    CredentialModel credentialModel = new CredentialModel();
                    credentialModel.setUsername(etUserName.getText().toString().trim());
                    credentialModel.setPassword(etPassword.getText().toString().trim());
                    sendToPostLogin(credentialModel);

                }
            }
        });



    }
    private void bindUI(){
        etUserName=findViewById(R.id.etNomUs);
        etPassword = findViewById(R.id.etPass);
        btnenter = findViewById(R.id.btnEnter);
    }
    private void sendToPostLogin(CredentialModel credentialModel){
        call= MyApiAdapter.getApiService().getLogin(credentialModel.getUsername(),credentialModel.getPassword());
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
       if(response.isSuccessful()){
           Map<String,Object> postLoginResponse=response.body();
           Log.d("onResponse:",postLoginResponse+"");
       }

    }

    @Override
    public void onFailure(Call<Map<String, Object>> call, Throwable t) {
        Log.d("Error:",t+"");
    }
}
