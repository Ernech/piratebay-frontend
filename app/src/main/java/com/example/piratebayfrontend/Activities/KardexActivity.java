package com.example.piratebayfrontend.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.piratebayfrontend.Fragments.KardexFragment;
import com.example.piratebayfrontend.Fragments.ProductInfoFragment;
import com.example.piratebayfrontend.R;

public class KardexActivity extends AppCompatActivity {

    ProductInfoFragment productInfoFragment;
    KardexFragment kardexFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kardex);
        bindUI();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,productInfoFragment);

    }
    private void bindUI(){
        productInfoFragment=new ProductInfoFragment();
        kardexFragment = new KardexFragment();
    }


    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btnInfoProd: transaction.replace(R.id.fragmentContainer,productInfoFragment);
                break;
            case R.id.btnKardex: transaction.replace(R.id.fragmentContainer,kardexFragment);
                break;
        }
        transaction.commit();
    }
}
