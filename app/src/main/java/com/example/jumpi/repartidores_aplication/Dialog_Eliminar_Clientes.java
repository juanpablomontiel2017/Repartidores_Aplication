package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dialog_Eliminar_Clientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog__eliminar__clientes);

        final Button buttonCancelarEliminarCliente = (Button) findViewById(R.id.btn_cancelar_ElC);

        buttonCancelarEliminarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*
                Intent buttonCancelElC = new Intent(Dialog_Eliminar_Clientes.this, Second_Activity.class);
                startActivity(buttonCancelElC);
*/
                finish();

            }
        });


        final Button buttonAceptarEliminarCliente = (Button) findViewById(R.id.btn_aceptar_ElC);

        buttonAceptarEliminarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*
                Intent buttonAceptarElC = new Intent(Dialog_Eliminar_Clientes.this, Second_Activity.class);
                startActivity(buttonAceptarElC);
*/


                Toast.makeText(getApplicationContext(),"El cliente ha sido eliminado",Toast.LENGTH_LONG).show();

                finish();
            }
        });




    }
}
