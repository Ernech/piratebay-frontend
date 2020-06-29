package com.example.piratebayfrontend.Interfaces;

import com.example.piratebayfrontend.Model.OrderModel;

import java.util.ArrayList;

public interface OrdersCallBack {

    void onSuccess(boolean value, ArrayList<OrderModel> orderList);
}
