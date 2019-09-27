package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class StockEnVehiculoFragment extends Fragment {


    /********************* DECLARACIÓN DE VARIABLES GLOBALES ************************/

    ScrollView scrollView;

    Button btn_detalle_envases, btn_detalle_dinero, btn_detalle_articulos, btn_detalle_faltante_sobrante;

    TextView TextView_Titulo_Faltante_Sobrante;




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


        btn_detalle_faltante_sobrante = (Button) view.findViewById(R.id.button_detalle_faltante_sobrante);

        btn_detalle_faltante_sobrante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Detalle_Faltante_Sobrante_Envases.class);
                startActivity(intent);

            }
        });





        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1= new SpannableString("FALTANTE \n");
        str1.setSpan(new ForegroundColorSpan(Color.RED), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2= new SpannableString("Y/O ");
        str2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str2.length(), 0);
        builder.append(str2);


        SpannableString str3= new SpannableString("SOBRANTE");
        str3.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str3.length(), 0);
        builder.append(str3);

        TextView tv = (TextView) view.findViewById(R.id.tv_faltante_sobrante_titulo);
        tv.setText( builder, TextView.BufferType.SPANNABLE);


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