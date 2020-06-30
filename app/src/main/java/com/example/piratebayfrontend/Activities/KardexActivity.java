package com.example.piratebayfrontend.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Fragments.KardexFragment;
import com.example.piratebayfrontend.Fragments.ProductInfoFragment;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.RefreshTokenResponse;

import java.util.Map;

public class KardexActivity extends AppCompatActivity {

    ProductInfoFragment productInfoFragment;
    KardexFragment kardexFragment;
    int warehouse;
    int idMovie;
    Bundle bundle;
    Bundle args;
    Map<String, String> tokens;
    RefreshTokenResponse refreshTokenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kardex);
        bindUI();
        bundle = getIntent().getExtras();
        idMovie = bundle.getInt("idMovie");
        warehouse = bundle.getInt("warehouseId");
        args = new Bundle();
        args.putInt("movieID",idMovie);
        args.putInt("wh",warehouse);
        productInfoFragment.setArguments(args);
        tokens = TokensControl.retrieveTokens(getApplicationContext());
        refreshTokens();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,productInfoFragment).commit();

    }
    private void bindUI(){
        productInfoFragment=new ProductInfoFragment();
        kardexFragment = new KardexFragment();
    }


    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btnInfoProd:
                refreshTokens();
                transaction.replace(R.id.fragmentContainer,productInfoFragment);
                productInfoFragment.setArguments(args);
                break;
            case R.id.btnKardex:
                refreshTokens();
                transaction.replace(R.id.fragmentContainer,kardexFragment);
                kardexFragment.setArguments(args);
                break;
        }
        transaction.commit();
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
                    logOut();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cmLogOut2:logOut();
                return true;
            case R.id.cmReturnMenu: returnMenu();
                return true;
            default:
                return false;
        }
    }
    private void logOut(){
        TokensControl.removeTokens(getApplicationContext());
        Intent intent = new Intent(KardexActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void returnMenu(){
        Intent intent = new Intent(KardexActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
