package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.OrderListAdapter;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Interfaces.KardexUpdateCallBack;
import com.example.piratebayfrontend.Interfaces.OrdersCallBack;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.OrderModel;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.KardexResponse;
import com.example.piratebayfrontend.Responses.OrdersResponse;
import com.example.piratebayfrontend.Responses.RefreshTokenResponse;
import com.example.piratebayfrontend.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    RecyclerView rvOrders;
    TextView tvProduct;
    Map<String, String> tokens;
    RefreshTokenResponse refreshTokenController;
    OrderListAdapter orderListAdapter;
    int warehouseId;
    int productId;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tokens = TokensControl.retrieveTokens(getApplicationContext());
        bundle= getIntent().getExtras();
        warehouseId = bundle.getInt("whId");
        productId = bundle.getInt("prodId");
        bindUI();
        getOrders();
    }
    private void bindUI(){
        rvOrders = findViewById(R.id.rvProd);
        tvProduct = findViewById(R.id.tvProductOrders);
    }
    private void getOrders(){
        OrdersResponse ordersResponse = new OrdersResponse(tokens.get(Utilities.AUTHENTICATION_TOKEN),warehouseId,productId);
        ordersResponse.getOrdersByWarehouseAndProduct(new OrdersCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<OrderModel> orderList) {
                if (value && orderList!=null){
                    setOrdersListAdapter(orderList);
                }
            }
        });

    }
    private void setOrdersListAdapter(final ArrayList<OrderModel> orderList){
        rvOrders.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        orderListAdapter = new OrderListAdapter(getApplicationContext(),orderList);
        rvOrders.setAdapter(orderListAdapter);
        orderListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshTokens();
                alertEntry("Registrar entrada","Ingrese la cantidad que desea registrar",
                        orderList.get(rvOrders.getChildAdapterPosition(view)).getQttyOrdered(),
                        orderList.get(rvOrders.getChildAdapterPosition(view)).getQttyCommit(),
                        orderList.get(rvOrders.getChildAdapterPosition(view)).getQttyReceived(),
                        orderList.get(rvOrders.getChildAdapterPosition(view)).getProviderProductId());
                Toast.makeText(getApplicationContext(),""+ orderList.get(rvOrders.getChildAdapterPosition(view)).getQttyReceived()+" "+
                        orderList.get(rvOrders.getChildAdapterPosition(view)).getProviderProductId() ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void alertEntry(String title, String msg, int qttyRequested, final int qttyCommited, final Integer qttyReceived, final Integer producOrderId){
        AlertDialog.Builder entryAlert = new AlertDialog.Builder(OrderActivity.this);
        View view = getLayoutInflater().inflate(R.layout.entry_layout,null);
        TextView tvQttyAlertRequested = view.findViewById(R.id.tvAlertQttyRequested);
        TextView tvQttyAlertCommited = view.findViewById(R.id.tvAlertQttyCommited);
        final EditText etAlertQtyyReceived = view.findViewById(R.id.etQttyReceived);
        tvQttyAlertRequested.setText("Cantidad Ordenada: "+qttyRequested);
        tvQttyAlertCommited.setText("Cantidad Confirmada: "+qttyCommited);
        if (qttyReceived!=null){
            etAlertQtyyReceived.setText(qttyReceived+"");
        }
        else{
            etAlertQtyyReceived.setText("");
        }
        entryAlert.setView(view);
        entryAlert.setTitle(title);
        entryAlert.setMessage(msg);
        entryAlert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(Integer.parseInt(etAlertQtyyReceived.getText().toString().trim())>qttyCommited){
                    Toast.makeText(getApplicationContext(),"La cantidad recibida no puede ser mayor a la cantidad confirmada",
                            Toast.LENGTH_SHORT).show();
                }
                else if((Integer.parseInt(etAlertQtyyReceived.getText().toString().trim())==0) || TextUtils.isEmpty(etAlertQtyyReceived.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"La cantidad recibida no puede igual a 0",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    refreshTokens();
                    KardexResponse kardexResponse = new KardexResponse(tokens.get(Utilities.AUTHENTICATION_TOKEN));
                    kardexResponse.updateKardex(Integer.parseInt(etAlertQtyyReceived.getText().toString().trim()), producOrderId, new KardexUpdateCallBack() {
                        @Override
                        public void onSuccess(boolean value) {
                            if (value){
                                Toast.makeText(getApplicationContext(),"Entrada registrada",
                                        Toast.LENGTH_SHORT).show();
                                getOrders();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        entryAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        entryAlert.show();
    }

    private void refreshTokens(){
        refreshTokenController = new RefreshTokenResponse(tokens.get("refresh"));
        refreshTokenController.sendToPostRefreshToken(new RefreshTokenCallBack() {
            @Override
            public void onSuccess(boolean value, Object newAuthnToken, Object newRefreshToken) {
                if(value && newAuthnToken!=null && newRefreshToken!=null){
                    TokensControl.saveTokens(newAuthnToken, newRefreshToken, getApplicationContext());
                    tokens.clear();
                    tokens = TokensControl.retrieveTokens(getApplicationContext());
                    //  authnTokenExpired =false;
                } else {
                    TokensControl.removeTokens(getApplicationContext());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });
    }
}
