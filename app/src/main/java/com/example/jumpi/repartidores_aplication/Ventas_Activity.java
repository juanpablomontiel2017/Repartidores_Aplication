package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ventas);
        setSupportActionBar(toolbar);

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


        final Button buttonConfirmarVentas = (Button) findViewById(R.id.button_confirmar_ventas);

        buttonConfirmarVentas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonConfirmarV = new Intent(Ventas_Activity.this, Second_Activity.class);
                startActivity(buttonConfirmarV);
                Toast.makeText(getApplicationContext(),"Venta realizada con éxito",Toast.LENGTH_LONG).show();
            }
        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_realizar_ventas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings_ventas) {
            return true;
        }

        if (id == R.id.id_cancelar_venta) {

            Intent IntentCancelarV = new Intent(Ventas_Activity.this, Second_Activity.class);
            startActivity(IntentCancelarV);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
