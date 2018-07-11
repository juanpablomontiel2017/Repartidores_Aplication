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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ventas_Activity extends AppCompatActivity {


    Spinner lista, lista2, lista3, lista4;
    String [] datos =  {"Árticulo","Canillas","Dispenser Eléctrico","Dispenser Plástico","Envases"};

    TextView Nombre_Cliente_Ventas;
    TextView Apellido_Cliente_Ventas;
    TextView Direccion_Cliente_Ventas;
    TextView Barrio_Cliente_Ventas;

    EditText eTcant1;
    EditText eTcant2;
    EditText eTcant3;
    EditText eTcant4;
    EditText eTVacios;
    EditText eTLlenos;
    EditText eEntrega;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ventas);
        setSupportActionBar(toolbar);

        eTcant1 = (EditText) findViewById(R.id.cantidad_productos_ventas);
        eTcant1.setEnabled(false);



        eTcant2 = (EditText) findViewById(R.id.cantidad2_productos_ventas);
        eTcant2.setEnabled(false);



        eTcant3 = (EditText) findViewById(R.id.cantidad3_productos_ventas);
        eTcant3.setEnabled(false);



        eTcant4 = (EditText) findViewById(R.id.cantidad4_productos_ventas);
        eTcant4.setEnabled(false);



        eTVacios = (EditText) findViewById(R.id.cantiad_vacios_ventas);
        eTLlenos = (EditText) findViewById(R.id.cantiad_llenos_ventas);
        eEntrega = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas);


        lista = (Spinner)findViewById(R.id.lista_productos_ventas);


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        lista.setAdapter(adaptador);

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){

                    case 0:
                        eTcant1.setEnabled(false);

                       // Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                       // to.show();
                        break;

                    case 1:

                        eTcant1.setEnabled(true);
                        // Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //t.show();
                        break;


                    case 2:

                        eTcant1.setEnabled(true);
                        //Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //ta.show();
                        break;


                    case 3:

                        eTcant1.setEnabled(true);

                        //Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
                        break;

                    case 4:

                        eTcant1.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
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
                        eTcant2.setEnabled(false);

                        // Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        // to.show();
                        break;

                    case 1:

                        eTcant2.setEnabled(true);


                        // Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //t.show();
                        break;


                    case 2:

                        eTcant2.setEnabled(true);

                        //Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //ta.show();
                        break;


                    case 3:

                        eTcant2.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
                        break;

                    case 4:

                        eTcant2.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //PRUEBA para el TERCER SPINNER
        lista3 = (Spinner)findViewById(R.id.lista3_productos_ventas);
        ArrayAdapter<String> adaptador3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
        lista3.setAdapter(adaptador3);

        lista3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i){

                    case 0:
                        eTcant3.setEnabled(false);

                        // Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        // to.show();
                        break;

                    case 1:

                        eTcant3.setEnabled(true);

                        // Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //t.show();
                        break;


                    case 2:

                        eTcant3.setEnabled(true);

                        //Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //ta.show();
                        break;


                    case 3:

                        eTcant3.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
                        break;

                    case 4:

                        eTcant3.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
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
                        eTcant4.setEnabled(false);

                        // Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        // to.show();
                        break;

                    case 1:

                        eTcant4.setEnabled(true);


                        // Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //t.show();
                        break;


                    case 2:

                        eTcant4.setEnabled(true);

                        //Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //ta.show();
                        break;


                    case 3:

                        eTcant4.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
                        break;

                    case 4:

                        eTcant4.setEnabled(true);

                        //  Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        //te.show();
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        //Recibir los párametros de los clientes desde el RecyclerViewAdapter

        //Pasar el apellido del cliente a la actividad de "Realizar_Ventas"
        Apellido_Cliente_Ventas  = (TextView) findViewById(R.id.apellido_cliente_realizar_ventas);

        String extras = getIntent().getStringExtra("Apellido");

        Apellido_Cliente_Ventas.setText(extras);





        //Pasar el nombre del cliente a la actividad de "Realizar_Ventas"
        Nombre_Cliente_Ventas  = (TextView) findViewById(R.id.nombre_cliente_realizar_ventas);

         extras = getIntent().getStringExtra("Nombre");

        Nombre_Cliente_Ventas.setText(extras);




        //Pasar la dirección del cliente a la actividad de "Realizar_Ventas"
        Direccion_Cliente_Ventas  = (TextView) findViewById(R.id.direccion_cliente_realizar_ventas);

        extras = getIntent().getStringExtra("Direccion");

        Direccion_Cliente_Ventas.setText(extras);


        //Pasar el barrio del cliente a la actividad de "Realizar_Ventas"
        Barrio_Cliente_Ventas  = (TextView) findViewById(R.id.barrio_cliente_realizar_ventas);

        extras = getIntent().getStringExtra("Barrio");

        Barrio_Cliente_Ventas.setText(extras);








        final Button buttonConfirmarVentas = (Button) findViewById(R.id.button_confirmar_ventas);

        buttonConfirmarVentas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String AuxiliarLlenos = eTLlenos.getText().toString();
                String AuxiliarVacios = eTVacios.getText().toString();
                String AuxiliarCant1  = eTcant1.getText().toString();
                String AuxiliarCant2  = eTcant2.getText().toString();
                String AuxiliarCant3  = eTcant3.getText().toString();
                String AuxiliarCant4  = eTcant4.getText().toString();
                String AuxiliarEntrega = eEntrega.getText().toString();


                if (AuxiliarLlenos.isEmpty() && AuxiliarVacios.isEmpty() && AuxiliarCant1.isEmpty()&& AuxiliarCant2.isEmpty() && AuxiliarCant3.isEmpty() && AuxiliarCant4.isEmpty() && AuxiliarEntrega.isEmpty() ){

                    Toast.makeText(getApplicationContext(),"Error! Los campos estan vacios. Por favor, complete al menos un campo",Toast.LENGTH_LONG).show();


                }

                else {

                    //Intent buttonConfirmarV = new Intent(Ventas_Activity.this, Second_Activity.class);
                    //startActivity(buttonConfirmarV);
                    Toast.makeText(getApplicationContext(),"Venta realizada con éxito",Toast.LENGTH_LONG).show();
                    finish();

                }


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

            //Intent IntentCancelarV = new Intent(Ventas_Activity.this, Second_Activity.class);
            //startActivity(IntentCancelarV);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
