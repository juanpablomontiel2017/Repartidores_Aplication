package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityEstadisticas extends AppCompatActivity {


    LinearLayout LinearLayoutVerticalComisiones, LinearLayoutVerticalCombustible,LinearLayoutVerticalVentaBidones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_estadisticas);


        LinearLayoutVerticalComisiones = (LinearLayout) findViewById(R.id.llv_uno_punto_uno);
        LinearLayoutVerticalComisiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (ActivityEstadisticas.this, ActivityEleccionRepartidores.class);

                intent.putExtra("activity","ComisionesEstadisticas");

                startActivity(intent);



            }
        });



        ImageButton img_btn_comisiones = (ImageButton) findViewById(R.id.img_btn_comisiones);
        img_btn_comisiones.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (ActivityEstadisticas.this, ActivityEleccionRepartidores.class);

                intent.putExtra("activity","ComisionesEstadisticas");

                startActivity(intent);


            }



        });/********* FIN DEL EVENTO setOnClickListener()*************/










        LinearLayoutVerticalCombustible = (LinearLayout) findViewById(R.id.llv_uno_punto_dos);
        LinearLayoutVerticalCombustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (ActivityEstadisticas.this, ActivityCombustibleEstadisticas.class);

                startActivity(intent);



            }
        });




        ImageButton img_btn_combustible = (ImageButton) findViewById(R.id.img_btn_gasto_combustible);
        img_btn_combustible.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (ActivityEstadisticas.this, ActivityCombustibleEstadisticas.class);

                startActivity(intent);


            }



        });/********* FIN DEL EVENTO setOnClickListener()*************/





        LinearLayoutVerticalVentaBidones = (LinearLayout) findViewById(R.id.llv_dos_punto_uno);
        LinearLayoutVerticalVentaBidones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (ActivityEstadisticas.this, ActivityVentaBidonesEstadisticas.class);

                startActivity(intent);



            }
        });




        ImageButton img_btn_venta_bidones = (ImageButton) findViewById(R.id.img_btn_venta_bidones);
        img_btn_venta_bidones.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (ActivityEstadisticas.this, ActivityVentaBidonesEstadisticas.class);

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/




    }/***************************FIN DEL onCreate()*************************************/
















}/**********************************************FIN DE LA Activity**********************************/
