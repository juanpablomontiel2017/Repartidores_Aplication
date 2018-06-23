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


    Spinner lista;
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

                    case 1:
                        Toast to = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        to.show();
                        break;

                    case 2:
                        Toast t = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        t.show();
                        break;


                    case 3:
                        Toast ta = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        ta.show();
                        break;


                    case 4:
                        Toast te = Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG);
                        te.show();
                        break;


                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



                Nombre_Cliente_Ventas  = (TextView) findViewById(R.id.nombre_cliente_realizar_ventas);


        String extras = getIntent().getStringExtra("Nombre");

        Nombre_Cliente_Ventas.setText(extras);



    }




}
