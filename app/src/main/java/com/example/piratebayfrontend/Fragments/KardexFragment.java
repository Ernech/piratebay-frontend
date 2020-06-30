package com.example.piratebayfrontend.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.piratebayfrontend.Activities.OrderActivity;
import com.example.piratebayfrontend.Clases.Tabla;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Interfaces.KardexCallBack;
import com.example.piratebayfrontend.Interfaces.RefreshTokenCallBack;
import com.example.piratebayfrontend.MainActivity;
import com.example.piratebayfrontend.Model.KardexModel;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.KardexResponse;
import com.example.piratebayfrontend.Responses.RefreshTokenResponse;
import com.example.piratebayfrontend.Utilities.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;


public class KardexFragment extends Fragment {

    Tabla tablaKardex;
    FloatingActionButton fabEntry;
    RefreshTokenResponse refreshTokenController;
    Map<String, String> tokens;
    ArrayList<KardexModel> kardex;
    int warehouseId;
    int idMovie;
    public KardexFragment() {
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
        return inflater.inflate(R.layout.fragment_kardex, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabEntry = getActivity().findViewById(R.id.fabEntry);
        tablaKardex=new Tabla((TableLayout)getActivity().findViewById(R.id.layoutTablaKardex),getActivity());
        tokens =  TokensControl.retrieveTokens(getContext());
        generateTableHeader();
        final KardexResponse kardexResponse = new KardexResponse(tokens.get(Utilities.AUTHENTICATION_TOKEN),warehouseId,idMovie);
        kardexResponse.getKardexElements(new KardexCallBack() {
            @Override
            public void onSuccess(boolean value, ArrayList<KardexModel> kardexElements) {
                if(value && kardexElements!=null){
                    kardex=kardexElements;
                    kardexResponse.fillKardex(kardexElements,tablaKardex);
                }else {
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        fabEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshTokens();
                Intent intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra("whId",warehouseId);
                intent.putExtra("prodId",idMovie);
                startActivity(intent);
            }
        });


    }
    private void generateTableHeader(){
       tablaKardex.agregarCabecera(R.array.cabecera_kardex);

    }

    private void refreshTokens(){
        refreshTokenController = new RefreshTokenResponse(tokens.get("refresh"));
        refreshTokenController.sendToPostRefreshToken(new RefreshTokenCallBack() {
            @Override
            public void onSuccess(boolean value, Object newAuthnToken, Object newRefreshToken) {
                if(value && newAuthnToken!=null && newRefreshToken!=null){
                    TokensControl.saveTokens(newAuthnToken, newRefreshToken, getContext());
                    tokens.clear();
                    tokens = TokensControl.retrieveTokens(getContext());
                    //  authnTokenExpired =false;
                } else {
                    TokensControl.removeTokens(getContext());
                    Intent i = new Intent(getContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });
    }

}
