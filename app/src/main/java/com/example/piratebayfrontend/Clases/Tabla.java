package com.example.piratebayfrontend.Clases;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.piratebayfrontend.R;

import java.util.ArrayList;

public class Tabla {
    private TableLayout tabla;//Lugar donde se generará la tabla
    private ArrayList<TableRow> filas; //Array de las filas de la tabla
    private Activity activity;
    private Resources resources;
    private int FILAS,COLUMNAS;

    public Tabla(TableLayout tabla, Activity activity) {
        this.tabla = tabla;
        this.activity = activity;
        this.resources = activity.getResources();
        FILAS=COLUMNAS=0;
        filas = new ArrayList<TableRow>();
    }
    /**
     * Agregar la cabecera a la tabla
     */
    public void agregarCabecera(int recursoCabecera){
        TableRow.LayoutParams layoutCelda;
        TableRow fila = new TableRow(activity);
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        fila.setLayoutParams(layoutFila);
        String [] arrayCabecera = resources.getStringArray(recursoCabecera);
        COLUMNAS = arrayCabecera.length;
        for(int i=0;i<arrayCabecera.length;i++){
            TextView texto = new TextView(activity);
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(arrayCabecera[i]),TableLayout.LayoutParams.WRAP_CONTENT);
            texto.setText(arrayCabecera[i]);
            texto.setGravity(Gravity.CENTER_HORIZONTAL);
            texto.setTextAppearance(activity, R.style.estilo_celda);
            texto.setBackgroundResource(R.drawable.tabla_celda_cabecera);
            texto.setLayoutParams(layoutCelda);
            fila.addView(texto);
        }
        tabla.addView(fila);
        filas.add(fila);
        FILAS ++;
    }

    /**
     * Agrega una fila a la tabla
     * @param elementos Elementos de la fila
     */
    public void agregarFilaTabla(ArrayList<String> elementos){
        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(activity);
        fila.setLayoutParams(layoutFila);
        for (int i=0 ;i<elementos.size();i++){
            TextView texto = new TextView(activity);
            texto.setText(String.valueOf(elementos.get(i)));
            texto.setGravity(Gravity.CENTER_HORIZONTAL);
            texto.setTextAppearance(activity,R.style.estilo_celda);
            texto.setBackgroundResource(R.drawable.tabla_celda);
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto.getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
            texto.setLayoutParams(layoutCelda);
            fila.addView(texto);
        }
        tabla.addView(fila);
        filas.add(fila);
        FILAS++;
    }


    /**
     * Elimina una fila de la tabla
     * @param indicefilaeliminar Indice de la fila a eliminar
     */
    public void eliminarFila(int indicefilaeliminar) {
        if( indicefilaeliminar > 0 && indicefilaeliminar < FILAS ) {
            tabla.removeViewAt(indicefilaeliminar);
            FILAS--;
        }
    }
    /**
     * Devuelve las columnas de la tabla
     * @return Columnas totales de la tabla
     */
    public int getColumnas()
    {
        return COLUMNAS;
    }
    /**
     * Devuelve las filas de la tabla
     * @return filas totales de la tabla
     */
    public int getFilas()
    {
        return FILAS;
    }

    /**
     * Devuelve el número de celdas de la tabla, la cabecera se cuenta como fila
     * @return Número de celdas totales de la tabla
     */
    public int getCeldasTotales()
    {
        return FILAS * COLUMNAS;
    }

    /**
     * Obtiene el ancho en píxeles de un texto en un String
     * @param texto Texto
     * @return Ancho en píxeles del texto
     */
    private int obtenerAnchoPixelesTexto(String texto) {
        Paint p = new Paint();
        Rect bounds = new Rect();
        p.setTextSize(50);
        p.getTextBounds(texto, 0, texto.length(), bounds);
        return bounds.width();
    }
}
