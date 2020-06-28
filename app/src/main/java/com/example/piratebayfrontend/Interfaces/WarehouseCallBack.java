package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.WarehouseModel;

import java.util.ArrayList;

public interface WarehouseCallBack {

    void onSuccess(boolean value, ArrayList<WarehouseModel> warehouseList);
}
