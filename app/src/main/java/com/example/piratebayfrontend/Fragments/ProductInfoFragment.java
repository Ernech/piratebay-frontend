package com.example.piratebayfrontend.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Interfaces.KardexInformationCallBack;
import com.example.piratebayfrontend.Model.KardexInformationModel;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.KardexResponse;
import com.example.piratebayfrontend.Utilities.Utilities;

import java.util.Map;


public class ProductInfoFragment extends Fragment {
    int warehouseId;
    int idMovie;
    Map<String, String> tokens;
    TextView txProductCode;
    TextView txProductName;
    TextView txFormat;
    TextView txWarehouseAddress;
    TextView txProviderName;
    String productName;
    public ProductInfoFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idMovie = (int) getArguments().getInt("movieID");
            warehouseId=(int) getArguments().getInt("wh");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tokens =  TokensControl.retrieveTokens(getContext());
        txProductCode = getActivity().findViewById(R.id.tvCodigo);
        txProductName = getActivity().findViewById(R.id.tvNombreArticulo);
        txFormat = getActivity().findViewById(R.id.tvNombreFormato);
        txWarehouseAddress = getActivity().findViewById(R.id.tvLocalizacion);
        txProviderName = getActivity().findViewById(R.id.tvProveedor);
        KardexResponse kardexResponse = new KardexResponse(tokens.get(Utilities.AUTHENTICATION_TOKEN),warehouseId,idMovie);
        kardexResponse.getKardexInformation(new KardexInformationCallBack() {
            @Override
            public void onSuccess(boolean value, KardexInformationModel kardexInformationModel) {
                if (value && kardexInformationModel!=null){
                    txProductCode.setText(kardexInformationModel.getProductCode());
                    txProductName.setText(kardexInformationModel.getProductName());
                    txFormat.setText(kardexInformationModel.getFormat());
                    txWarehouseAddress.setText(kardexInformationModel.getWarehouseAddress());
                    txProviderName.setText(kardexInformationModel.getProviderName());
                }
                else{
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
