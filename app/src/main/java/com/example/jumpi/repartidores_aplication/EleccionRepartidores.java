package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class EleccionRepartidores extends AppCompatActivity {


    private GridView gridView;
    private AdaptadorDeRepartidores adaptador;

    String ActivityProvenienteDe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eleccion_repartidores);




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
                        IniciarActivityCargaDescarga(view, position);

                        Toast.makeText(EleccionRepartidores.this, "¡Ha ingresado a la activity 'Carga y Descarga'!", Toast.LENGTH_LONG).show();

                        break;

                    case "ComisionesEstadisticas":

                        /*Llamada a la función: */
                        IniciarActivityComisionesEstadisticas(view);

                        Toast.makeText(EleccionRepartidores.this, "¡Ha ingresado a la activity 'ComisionesEstadisticas'!", Toast.LENGTH_LONG).show();



                    default:

                         Toast.makeText(EleccionRepartidores.this, "¡No se encontró la activity!", Toast.LENGTH_LONG).show();


                }//Fin del switch






            } /******************FIN DEL EVENTO onItemClick()***************/




        });/**********************FIN DEL EVENTO setOnItemClickListener()***********************/







    }/**************************************FIN DEL onCreate()***************************************/




    public void IniciarActivityCargaDescarga(View view, int position){

        int idRepartidor = adaptador.getItem(position).getIdRepartidor();
        TextView nombre_apellido_repartidor = (TextView) view.findViewById(R.id.nombre_apellido_repartidor);

        String nombre_apellido_repartidor_enviar = nombre_apellido_repartidor.getText().toString();


        Date HoraSistema = UtilidadFecha.getHoraActual("HH:mm:ss");

        Date HoraInicio = UtilidadFecha.getHoraInicio();






        //Si es mayor a cero significa que está dentro del horario laboral
        if (HoraSistema.compareTo(HoraInicio) > 0){


            Intent intent = new Intent (EleccionRepartidores.this, Cargas_Descargas.class);

            intent.putExtra("nombre_apellido_repartidor",nombre_apellido_repartidor_enviar);

            intent.putExtra("idRepartidor",idRepartidor);

            startActivity(intent);



        } else{

            Toast.makeText(EleccionRepartidores.this, "¡El horario laboral comienza a las 06:00 AM!", Toast.LENGTH_LONG).show();


        }



    }/*************************************FIN DE LA FUNCION IniciarActivityCargaDescarga() *******************************************/








    public void IniciarActivityComisionesEstadisticas(View view){

        TextView nombre_apellido_repartidor = (TextView) view.findViewById(R.id.nombre_apellido_repartidor);

        String nombre_apellido_repartidor_enviar = nombre_apellido_repartidor.getText().toString();



        Intent intent = new Intent (EleccionRepartidores.this, ComisionesEstadisticas.class);

        intent.putExtra("nombre_apellido_repartidor",nombre_apellido_repartidor_enviar);

        startActivity(intent);



    }/*************************************FIN DE LA FUNCION IniciarActivityComisionesEstadisticas() *******************************************/




























}/*************************************FIN DE LA Activity*******************************************/

















