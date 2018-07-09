package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.Toolbar;

public class AgregarCliente extends AppCompatActivity {

    //Declaración de variables para cada CheckBox correspondiente a los 6 días laborales

    CheckBox c1, c2, c3, c4, c5, c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);



/*

        Bundle bundle = getIntent().getExtras();
        //final String dia = bundle.getString("dia");
*/


        final Button buttonCancelar = (Button) findViewById(R.id.button_cancelar_AC);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonCancelarC = new Intent(AgregarCliente.this, Second_Activity.class);
                startActivity(buttonCancelarC);

            }
        });



        final Button buttonAgregar = (Button) findViewById(R.id.button_agregar_AC);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonAgregarA = new Intent(AgregarCliente.this, Ventas_Activity.class);
                startActivity(buttonAgregarA);

            }
        });


        c1 = (CheckBox) findViewById(R.id.idcheckbox_lunes);
        c2 = (CheckBox) findViewById(R.id.idcheckbox_martes);
        c3 = (CheckBox) findViewById(R.id.idcheckbox_miercoles);
        c4 = (CheckBox) findViewById(R.id.idcheckbox_jueves);
        c5 = (CheckBox) findViewById(R.id.idcheckbox_viernes);
        c6 = (CheckBox) findViewById(R.id.idcheckbox_sabado);


    }

  public void onclick (View view) {

        if (view.getId()==R.id.button_agregar_AC) {

            MethodAgregarC();
        } //Fin de la sentencia 'if'

  }


    private void MethodAgregarC(){




    } //Fin del método






}
