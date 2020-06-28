package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.KardexModel;

import java.util.ArrayList;

public interface KardexCallBack {
    void onSuccess(boolean value, ArrayList<KardexModel> kardexElements);
}
