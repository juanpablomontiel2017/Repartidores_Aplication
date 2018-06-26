package com.example.jumpi.repartidores_aplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ventas_Activity extends AppCompatActivity {


    Spinner lista, lista2, lista3, lista4;
    String [] datos =  {"Canillas","Dispenser Eléctrico","Dispenser Plástico","Envases"};

    TextView Nombre_Cliente_Ventas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_);

        lista = (Spinner)findViewById(R.id.lista_productos_ventas);


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        lista.setAdapter(adaptador);

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){

                    case 0:
                        Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        to.show();
                        break;

                    case 1:
                        Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        t.show();
                        break;


                    case 2:
                        Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        ta.show();
                        break;


                    case 3:
                        Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        te.show();
                        break;


                }




            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





         //PRUEBA para el SEGUNDO SPINNER
        lista2 = (Spinner)findViewById(R.id.lista2_productos_ventas);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        lista2.setAdapter(adaptador2);

        lista2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i){

                    case 0:
                        Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        to.show();
                        break;

                    case 1:
                        Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        t.show();
                        break;


                    case 2:
                        Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        ta.show();
                        break;


                    case 3:
                        Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        te.show();
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //PRUEBA para el TERCER SPINNER
        lista3 = (Spinner)findViewById(R.id.lista3_productos_ventas);
        ArrayAdapter<String> adaptador3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        lista3.setAdapter(adaptador3);

        lista3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i){

                    case 0:
                        Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        to.show();
                        break;

                    case 1:
                        Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        t.show();
                        break;


                    case 2:
                        Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        ta.show();
                        break;


                    case 3:
                        Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        te.show();
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        //PRUEBA para el CUARTO SPINNER
        lista4 = (Spinner)findViewById(R.id.lista4_productos_ventas);
        ArrayAdapter<String> adaptador4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        lista4.setAdapter(adaptador4);

        lista4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i){

                    case 0:
                        Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        to.show();
                        break;

                    case 1:
                        Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        t.show();
                        break;


                    case 2:
                        Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        ta.show();
                        break;


                    case 3:
                        Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        te.show();
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        //Recibir los párametros de los clientes desde el RecyclerViewAdapter
        Nombre_Cliente_Ventas  = (TextView) findViewById(R.id.nombre_cliente_realizar_ventas);

        String extras = getIntent().getStringExtra("Nombre");

        Nombre_Cliente_Ventas.setText(extras);



    }




}
