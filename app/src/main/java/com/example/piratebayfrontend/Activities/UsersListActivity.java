package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.piratebayfrontend.Clases.Tabla;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Clases.VerifyFeatures;
import com.example.piratebayfrontend.Controladores.RefreshTokenController;
import com.example.piratebayfrontend.Controladores.UserController;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.Interfaces.UserCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.UserModel;
import com.example.piratebayfrontend.R;

import java.util.ArrayList;
import java.util.Map;

public class UsersListActivity extends AppCompatActivity {

    Tabla tabla;
    Button btnRefresh;
    RefreshTokenController refreshTokenController;
    Map<String, String> tokens;
    boolean authnTokenExpired;
    boolean hasFeatureButtonDeleteUSer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        bindUI();
        hasFeatureButtonDeleteUSer = VerifyFeatures.hasFeature(getApplicationContext(), "BUTTON_DELETE_USER");
       generateTableHeader();
        tokens = TokensControl.retrieveTokens(getApplicationContext());
        listUsers();


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!authnTokenExpired){
                    tabla.clearRows();
                    generateTableHeader();
                    refreshListUsers();
                }
                else{
                    refreshTokens();
                }

            }
        });
    }

    private void bindUI(){
        tabla= new Tabla((TableLayout)findViewById(R.id.layoutTabla), this);
        btnRefresh = findViewById(R.id.btnRefresh);
    }

    private void agregarUsuarios(ArrayList<UserModel> usersList){
        for(int i=0; i<usersList.size(); i++){
            ArrayList<String> users = new ArrayList<String>();
            users.add(Integer.toString(usersList.get(i).getUserId()));
            users.add(usersList.get(i).getUsername());
            users.add(usersList.get(i).getEmail());
            users.add(usersList.get(i).getPhoneNumber());
            users.add("Editar");
            if (hasFeatureButtonDeleteUSer){
                users.add("Eliminar");
            }
            tabla.agregarFilaTabla(users);
        }
    }
    private void listUsers(){
        UserController userController= new UserController(tokens.get("authentication"));
        userController.getUsersList(new UserCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<UserModel> usersList) {
                if(value && usersList!=null){
                    agregarUsuarios(usersList);
                }else{
                    btnRefresh.setText("Refrescar tokens");
                }
            }
        });
    }
    private void refreshListUsers(){
        UserController userController= new UserController(tokens.get("authentication"));
        userController.getUsersList(new UserCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<UserModel> usersList) {
                if(value && usersList!=null){
                    agregarUsuarios(usersList);
                    authnTokenExpired=false;
                }else{
                    authnTokenExpired = true;
                    btnRefresh.setText("Refrescar tokens");
                }
            }
        });
    }
    private void refreshTokens(){
        refreshTokenController = new RefreshTokenController(tokens.get("refresh"));
        refreshTokenController.sendToPostRefreshToken(new RefreshTokenCallBack() {
            @Override
            public void onSuccess(boolean value, Object newAuthnToken, Object newRefreshToken) {
                if(value && newAuthnToken!=null && newRefreshToken!=null){
                    TokensControl.saveTokens(newAuthnToken, newRefreshToken, getApplicationContext());
                    tokens.clear();
                    tokens = TokensControl.retrieveTokens(getApplicationContext());
                    // tokens.put("authentication", (String) newAuthnToken);
                    //tokens.put("refresh", (String) newRefreshToken);
                    btnRefresh.setText("Actualizar Datos");
                    authnTokenExpired =false;
                } else {
                    TokensControl.removeTokens(getApplicationContext());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });
    }
    private void generateTableHeader(){
        if(hasFeatureButtonDeleteUSer){
            tabla.agregarCabecera(R.array.cabecera_tabla_administrador);
        }
        else{
            tabla.agregarCabecera(R.array.cabecera_tabla__warehouse_employee);
        }
    }
}