package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditarClientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_clientes);


        final Button buttonCancelarEditarCliente = (Button) findViewById(R.id.button_cancelar_EC);

        buttonCancelarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonCancelarEC = new Intent(EditarClientes.this, Second_Activity.class);
                startActivity(buttonCancelarEC);

            }
        });


        final Button buttonAceptarEditarCliente = (Button) findViewById(R.id.button_aceptar_EC);

        buttonAceptarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonAceptarEC = new Intent(EditarClientes.this, Second_Activity.class);
                startActivity(buttonAceptarEC);

            }
        });


    }
}
