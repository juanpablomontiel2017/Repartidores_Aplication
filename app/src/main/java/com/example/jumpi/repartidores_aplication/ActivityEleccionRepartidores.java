package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Date;


public class ActivityEleccionRepartidores extends AppCompatActivity {


    private GridView gridView;
    private AdaptadorDeRepartidores adaptador;

    String ActivityProvenienteDe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eleccion_repartidores);




        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);



        gridView = (GridView) findViewById(R.id.grid);

        adaptador = new AdaptadorDeRepartidores(this);




        ActivityProvenienteDe = getIntent().getStringExtra("activity");



        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (ActivityProvenienteDe){

                    case "Carga_Descarga":


                        /*Llamada a la función: */
                        IniciarActivityCargaDescarga(view);

                        break;

                    case "ComisionesEstadisticas":

                        /*Llamada a la función: */
                        IniciarActivityComisionesEstadisticas(view);

                        Toast.makeText(ActivityEleccionRepartidores.this, "¡Ha ingresado a la activity 'ComisionesEstadisticas'!", Toast.LENGTH_LONG).show();



                    default:

                         Toast.makeText(ActivityEleccionRepartidores.this, "¡No se encontró la activity!", Toast.LENGTH_LONG).show();


                }//Fin del switch






            } /******************FIN DEL EVENTO onItemClick()***************/




        });/**********************FIN DEL EVENTO setOnItemClickListener()***********************/







    }/**************************************FIN DEL onCreate()***************************************/




    public void IniciarActivityCargaDescarga(View view){



        TextView nombre_apellido_repartidor = (TextView) view.findViewById(R.id.nombre_apellido_repartidor);

        String nombre_apellido_repartidor_enviar = nombre_apellido_repartidor.getText().toString();


        Date HoraSistema = UtilsFecha.getHoraActual("HH:mm:ss");

        Date HoraInicio = UtilsFecha.getHoraInicio();






        //Si es mayor a cero significa que está dentro del horario laboral
        if (HoraSistema.compareTo(HoraInicio) > 0){


            Intent intent = new Intent (ActivityEleccionRepartidores.this, ActivityCargasDescargas.class);

            intent.putExtra("nombre_apellido_repartidor",nombre_apellido_repartidor_enviar);

            startActivity(intent);

            //finish();



        } else{

            Toast.makeText(ActivityEleccionRepartidores.this, "¡El horario laboral comienza a las 06:00 AM!", Toast.LENGTH_LONG).show();


        }



    }/*************************************FIN DE LA FUNCION IniciarActivityCargaDescarga() *******************************************/








    public void IniciarActivityComisionesEstadisticas(View view){

        TextView nombre_apellido_repartidor = (TextView) view.findViewById(R.id.nombre_apellido_repartidor);

        String nombre_apellido_repartidor_enviar = nombre_apellido_repartidor.getText().toString();



        Intent intent = new Intent (ActivityEleccionRepartidores.this, ActivityComisionesEstadisticas.class);

        intent.putExtra("nombre_apellido_repartidor",nombre_apellido_repartidor_enviar);

        startActivity(intent);



    }/*************************************FIN DE LA FUNCION IniciarActivityComisionesEstadisticas() *******************************************/




























}/*************************************FIN DE LA Activity*******************************************/

















