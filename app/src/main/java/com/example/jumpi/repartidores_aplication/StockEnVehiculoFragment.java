package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;


public class StockEnVehiculoFragment extends Fragment {


    /********************* DECLARACIÓN DE VARIABLES GLOBALES ************************/

    ScrollView scrollView;

    Button btn_detalle_envases, btn_detalle_dinero, btn_detalle_articulos, btn_detalle_faltante_sobrante;




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


    public StockEnVehiculoFragment() {


        // Required empty public constructor


    }/************* FIN DEL CONSTRUCTOR StockEnVehiculoFragment() *****************/





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






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock_en_vehiculo, container, false);

        scrollView = (ScrollView) view.findViewById(R.id.scrll);



        btn_detalle_envases= (Button) view.findViewById(R.id.button_detalle_envases);

        btn_detalle_envases.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Detalle_Envases.class);
                startActivity(intent);

            }
        });


        btn_detalle_dinero = (Button) view.findViewById(R.id.button_detalle_dinero);

        btn_detalle_dinero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Detalle_Dinero.class);
                startActivity(intent);

            }
        });


        btn_detalle_articulos = (Button) view.findViewById(R.id.button_detalle_articulos);

        btn_detalle_articulos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Detalle_Articulos.class);
                startActivity(intent);

            }
        });




        return view;


    } /************************FIN DEL OnCreateView ()************************************/






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




}/************ FIN DEL Fragment StockEnVehiculoFragment **************/