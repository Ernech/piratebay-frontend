package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        Map<String, String> tokens = TokensControl.retrieveTokens(getApplicationContext());
        UserController userController= new UserController(tokens.get("authentication"));
        userController.getUsersList(new UserCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<UserModel> usersList) {
                if(value && usersList!=null){
                    System.out.println(usersList);
                }
            }
        });
    }
}