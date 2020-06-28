package com.example.piratebayfrontend.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piratebayfrontend.Clases.MinMaxFilter;
import com.example.piratebayfrontend.Clases.Tabla;
import com.example.piratebayfrontend.Clases.TokensControl;
import com.example.piratebayfrontend.Interfaces.KardexCallBack;
import com.example.piratebayfrontend.Model.KardexModel;
import com.example.piratebayfrontend.R;
import com.example.piratebayfrontend.Responses.KardexResponse;
import com.example.piratebayfrontend.Responses.RefreshTokenResponse;
import com.example.piratebayfrontend.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Map;


public class KardexFragment extends Fragment {

    Tabla tablaKardex;
    Button btnEntry;
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
        btnEntry = getActivity().findViewById(R.id.btnEntry);
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
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    alertEntry("Registrar entrada","Ingrese la cantidad para regisrrar la entrada");
            }
        });

    }
    private void generateTableHeader(){
       tablaKardex.agregarCabecera(R.array.cabecera_kardex);

    }
    private void alertEntry(String title,String msg){
        AlertDialog.Builder entryAlert = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.entry_layout,null);
        TextView tvQttyAlertRequested = view.findViewById(R.id.tvAlertQttyRequested);
        TextView tvQttyAlertCommited = view.findViewById(R.id.tvAlertQttyCommited);
        EditText etAlertQtyyReceived = view.findViewById(R.id.etQttyReceived);
       // etAlertQtyyReceived.setFilters(new InputFilter[]{(InputFilter) new MinMaxFilter( 1, 15)});
        entryAlert.setView(view);
        entryAlert.setTitle(title);
        entryAlert.setMessage(msg);
        entryAlert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        entryAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        entryAlert.show();
    }

}
