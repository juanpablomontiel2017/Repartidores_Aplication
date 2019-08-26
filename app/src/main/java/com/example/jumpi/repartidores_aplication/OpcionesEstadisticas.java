package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class OpcionesEstadisticas extends AppCompatActivity {

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








        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);




        Button btn_comisiones = (Button) findViewById(R.id.btn_comisiones);

        btn_comisiones.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, EleccionRepartidores.class);

                intent.putExtra("activity","ComisionesEstadisticas");

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/







        Button btn_combustible = (Button) findViewById(R.id.btn_combustible);

        btn_combustible.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent (OpcionesEstadisticas.this, CombustibleEstadisticas.class);

                startActivity(intent);


            }



        });/*********FIN DEL EVENTO setOnClickListener()*************/








    }/***************************FIN DEL onCreate()*************************************/
















}/**********************************************FIN DE LA Activity**********************************/
