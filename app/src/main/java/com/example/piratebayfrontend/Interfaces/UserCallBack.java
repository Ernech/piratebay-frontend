package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.UserModel;

import java.util.ArrayList;

public interface UserCallBack {
    void onSuccess(boolean value, ArrayList<UserModel> usersList);
}
