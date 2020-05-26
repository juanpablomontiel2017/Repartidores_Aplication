package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OpcionesEstadisticas extends AppCompatActivity {


    LinearLayout LinearLayoutVerticalComisiones, LinearLayoutVerticalCombustible,LinearLayoutVerticalVentaBidones,
            LinearLayoutVerticalFrecuenciaConsumo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_estadisticas);



        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));







        LinearLayoutVerticalComisiones = (LinearLayout) findViewById(R.id.llv_uno_punto_uno);

        LinearLayoutVerticalComisiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, EleccionRepartidores.class);

                intent.putExtra("activity","ComisionesEstadisticas");

                startActivity(intent);



            }
        });



        ImageButton img_btn_comisiones = (ImageButton) findViewById(R.id.img_btn_comisiones);

        img_btn_comisiones.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, EleccionRepartidores.class);

                intent.putExtra("activity","ComisionesEstadisticas");

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/










        LinearLayoutVerticalCombustible = (LinearLayout) findViewById(R.id.llv_uno_punto_dos);

        LinearLayoutVerticalCombustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, CombustibleEstadisticas.class);

                startActivity(intent);



            }
        });




        ImageButton img_btn_combustible = (ImageButton) findViewById(R.id.img_btn_combustible);

        img_btn_combustible.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, CombustibleEstadisticas.class);

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/





        LinearLayoutVerticalVentaBidones = (LinearLayout) findViewById(R.id.llv_dos_punto_uno);

        LinearLayoutVerticalVentaBidones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, VentaBidonesEstadisticas.class);

                startActivity(intent);



            }
        });




        ImageButton img_btn_venta_bidones = (ImageButton) findViewById(R.id.img_btn_venta_bidones);

        img_btn_venta_bidones.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, VentaBidonesEstadisticas.class);

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/








        LinearLayoutVerticalFrecuenciaConsumo = (LinearLayout) findViewById(R.id.llv_dos_punto_dos);

        LinearLayoutVerticalFrecuenciaConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, Frecuencia_De_Consumo.class);

                startActivity(intent);



            }
        });




        ImageButton img_btn_frecuencia_consumo = (ImageButton) findViewById(R.id.img_btn_frecuencia_consumo);

        img_btn_frecuencia_consumo.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, Frecuencia_De_Consumo.class);

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/



    }/***************************FIN DEL onCreate()*************************************/
















}/**********************************************FIN DE LA Activity**********************************/
