package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.UserManager;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.piratebayfrontend.Clases.Tabla;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Controladores.RefreshTokenController;
import com.example.piratebayfrontend.Controladores.UserController;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.Interfaces.UserCallBack;
import com.example.piratebayfrontend.Model.UserModel;
import com.example.piratebayfrontend.R;

import java.util.ArrayList;
import java.util.Map;

public class UsersListActivity extends AppCompatActivity {

    Tabla tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        bindUI();

        tabla.agregarCabecera(R.array.cabecera_tabla);

        Map<String, String> tokens = TokensControl.retrieveTokens(getApplicationContext());
        UserController userController= new UserController(tokens.get("authentication"));
        userController.getUsersList(new UserCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<UserModel> usersList) {
                if(value && usersList!=null){
                    agregarUsuarios(usersList);
                }
            }
        });
    }

    private void bindUI(){
        tabla= new Tabla((TableLayout)findViewById(R.id.layoutTabla), this);
    }

    private void agregarUsuarios(ArrayList<UserModel> usersList){
        for(int i=0; i<usersList.size(); i++){
            ArrayList<String> users = new ArrayList<String>();
            users.add(Integer.toString(usersList.get(i).getUserId()));
            users.add(usersList.get(i).getUsername());
            users.add(usersList.get(i).getEmail());
            users.add(usersList.get(i).getPhoneNumber());
            tabla.agregarFilaTabla(users);
        }
    }
}