package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Detalle_Articulos extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_articulos);




    }/********************************FIN DEL onCreate ()************************************/



    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/







    int ChildNuevoArticulo = 0;

    public View AgregarItemTandas(final LinearLayout LayoutVerticalTercerTupla) {


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflater.inflate(R.layout.item_cantidad_stock_vehiculo, null, true);

        LayoutVerticalTercerTupla.addView(InflatedView);

        ChildNuevoArticulo = LayoutVerticalTercerTupla.getChildCount();


        return InflatedView;


    } /***************************FIN DE LA FUNCIÓN AgregarItemTandas()************************************/




    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/






}/**********************************FIN DE LA Activity Detalle_Articulos************************************/
