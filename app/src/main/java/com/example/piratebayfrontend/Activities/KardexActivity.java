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
    String warehouse;
    int idMovie;
    Bundle bundle;
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kardex);
        bindUI();
        bundle = getIntent().getExtras();
        idMovie = bundle.getInt("idMovie");
        warehouse = bundle.getString("warehouseName");
        args = new Bundle();
        args.putInt("movieID",idMovie);
        args.putString("wh",warehouse);
        productInfoFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,productInfoFragment).commit();

    }
    private void bindUI(){
        productInfoFragment=new ProductInfoFragment();
        kardexFragment = new KardexFragment();
    }


    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btnInfoProd: transaction.replace(R.id.fragmentContainer,productInfoFragment);
                productInfoFragment.setArguments(args);
                break;
            case R.id.btnKardex: transaction.replace(R.id.fragmentContainer,kardexFragment);
                kardexFragment.setArguments(args);
                break;
        }
        transaction.commit();
    }
}
