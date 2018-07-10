package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditarClientes extends AppCompatActivity {


    TextView Nombre_Cliente_EditarCliente;
    TextView Apellido_Cliente_EditarCliente;
    TextView Direccion_Cliente_EditarCliente;
    TextView Barrio_Cliente_EditarCliente;
    TextView Telefono_Cliente_EditarCliente;
    TextView Correo_Cliente_EditarCliente;
    TextView Referencia_Cliente_EditarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_clientes);


        final Button buttonCancelarEditarCliente = (Button) findViewById(R.id.button_cancelar_EC);

        buttonCancelarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*
                Intent buttonCancelarEC = new Intent(EditarClientes.this, Second_Activity.class);
                startActivity(buttonCancelarEC);
*/
        finish();

            }
        });


        final Button buttonAceptarEditarCliente = (Button) findViewById(R.id.button_aceptar_EC);

        buttonAceptarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*
                Intent buttonAceptarEC = new Intent(EditarClientes.this, Second_Activity.class);
                startActivity(buttonAceptarEC);
*/

                Toast.makeText(getApplicationContext(),"Edición Exitosa",Toast.LENGTH_LONG).show();

                finish();
            }
        });




        //Recibir los párametros de los clientes desde el RecyclerViewAdapter

        //Pasar el apellido del cliente a la actividad de "Editar_Cliente"
        Apellido_Cliente_EditarCliente  = (TextView) findViewById(R.id.apellido_cliente_editar);

        String extras = getIntent().getStringExtra("ApellidoEC");

        Apellido_Cliente_EditarCliente.setText(extras);





        //Pasar el nombre del cliente a la actividad de "Editar_Cliente"
        Nombre_Cliente_EditarCliente  = (TextView) findViewById(R.id.nombre_cliente_editar);

        extras = getIntent().getStringExtra("NombreEC");

        Nombre_Cliente_EditarCliente.setText(extras);




        //Pasar la dirección del cliente a la actividad de "Editar_Cliente"
        Direccion_Cliente_EditarCliente  = (TextView) findViewById(R.id.direccion_cliente_editar);

        extras = getIntent().getStringExtra("DireccionEC");

        Direccion_Cliente_EditarCliente.setText(extras);



        //Pasar el barrio del cliente a la actividad de "Editar_Cliente"
        Barrio_Cliente_EditarCliente  = (TextView) findViewById(R.id.barrio_cliente_editar);

        extras = getIntent().getStringExtra("BarrioEC");

        Barrio_Cliente_EditarCliente.setText(extras);



        //Pasar el teléfono del cliente a la actividad de "Editar_Cliente"
        Telefono_Cliente_EditarCliente  = (TextView) findViewById(R.id.telefono_cliente_editar);

        extras = getIntent().getStringExtra("TelefonoEC");

        Telefono_Cliente_EditarCliente.setText(extras);



        //Pasar el correo del cliente a la actividad de "Editar_Cliente"
        Correo_Cliente_EditarCliente  = (TextView) findViewById(R.id.correo_cliente_editar);

        extras = getIntent().getStringExtra("CorreoEC");

        Correo_Cliente_EditarCliente.setText(extras);



        //Pasar la referencia del cliente a la actividad de "Editar_Cliente"
        Referencia_Cliente_EditarCliente  = (TextView) findViewById(R.id.referencia_cliente_editar);

        extras = getIntent().getStringExtra("ReferenciaEC");

        Referencia_Cliente_EditarCliente.setText(extras);



    }
}
