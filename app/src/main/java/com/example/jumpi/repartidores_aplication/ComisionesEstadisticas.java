package com.example.jumpi.repartidores_aplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ComisionesEstadisticas extends AppCompatActivity {




    String nombre_apellido_recibir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comisiones_estadisticas);



        /**Recibir como parámetro el Nombre&Apellido del repartidor de la activity de EleccionRepartidores**/
        nombre_apellido_recibir = getIntent().getStringExtra("nombre_apellido_repartidor");



        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);

        /*Recibir como parámetro el nombre_&_apellido del repartidor */
        toolbar.setTitle(nombre_apellido_recibir);

        setSupportActionBar(toolbar);








    }/*********************FIN DEL onCreate()***********************/











}/***********************FIN DE LA Activity***************************/


