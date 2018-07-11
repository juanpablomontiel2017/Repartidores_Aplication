package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditarClientes extends AppCompatActivity {



    TextView Apellido_Cliente_EditarCliente;
    TextView Nombre_Cliente_EditarCliente;
    TextView Direccion_Cliente_EditarCliente;
    TextView Barrio_Cliente_EditarCliente;
    TextView Telefono_Cliente_EditarCliente;
    TextView Correo_Cliente_EditarCliente;
    TextView Referencia_Cliente_EditarCliente;


    CheckBox c1, c2, c3, c4, c5, c6;


    EditText eTApellido;
    EditText eTNombre;
    EditText eTDireccion;
    EditText eTBarrio;
    EditText eTTelefono;
    EditText eTCorreo;
    EditText eTReferencia;




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

                String AuxiliarApellidoE = eTApellido.getText().toString();
                String AuxiliarNombreE = eTNombre.getText().toString();
                String AuxiliarDireccionE = eTDireccion.getText().toString();
                String AuxiliarBarrioE = eTBarrio.getText().toString();
                String AuxiliarTelefonoE = eTTelefono.getText().toString();
                String AuxiliarCorreoE = eTCorreo.getText().toString();
                String AuxiliarReferenciaE = eTReferencia.getText().toString();






                //Estructuras selectivas utilizadas para comprobar que ningún campo importante como el 'Apellido' 'Nombre' 'Dirección' 'Barrio' y 'Referencia' quede vacío.

                if (AuxiliarApellidoE.isEmpty() && AuxiliarNombreE.isEmpty() && AuxiliarDireccionE.isEmpty() && AuxiliarBarrioE.isEmpty() && AuxiliarTelefonoE.isEmpty() && AuxiliarCorreoE.isEmpty() && AuxiliarReferenciaE.isEmpty() ) {

                    Toast.makeText(getApplicationContext(),"Error! Todos los campos estan vacios. Por favor, complete los campos correspondientes con datos válidos",Toast.LENGTH_LONG).show();
                }




                else if (AuxiliarApellidoE.isEmpty() && AuxiliarNombreE.isEmpty() && AuxiliarDireccionE.isEmpty() && AuxiliarBarrioE.isEmpty() && AuxiliarReferenciaE.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Error! Los campos: Apellido, nombre, dirección, barrio y referencia son obligatorios. Por favor, verifíquelos!",Toast.LENGTH_LONG).show();





                } else  if (AuxiliarApellidoE.isEmpty() ) {


                Toast.makeText(getApplicationContext(),"Error! El campo 'apellido' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();


            } else if (AuxiliarNombreE.isEmpty()) {


                    Toast.makeText(getApplicationContext(),"Error! El campo 'nombre' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                } else if (AuxiliarDireccionE.isEmpty()) {


                    Toast.makeText(getApplicationContext(),"Error! El campo 'dirección' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                } else  if (AuxiliarBarrioE.isEmpty()) {

                    Toast.makeText(getApplicationContext(),"Error! El campo 'barrio' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                } else if (AuxiliarReferenciaE.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Error! El campo 'referencia' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                //Para que ningun CheckBox quede sin seleccionar
                } else if (c1.isChecked() == false && c2.isChecked() == false && c3.isChecked()==false && c4.isChecked()==false && c5.isChecked()==false && c6.isChecked()==false ){


                    Toast.makeText(getApplicationContext(),"Error! No ha seleccionado un día de reparto. Por favor, seleccione al menos uno",Toast.LENGTH_LONG).show();

                } else {



    /*
                Intent buttonAceptarEC = new Intent(EditarClientes.this, Second_Activity.class);
                startActivity(buttonAceptarEC);

                    */

                    Toast.makeText(getApplicationContext(),"Edición Exitosa",Toast.LENGTH_LONG).show();

                    finish();
                }





            }
        });




        //Recibir los párametros de los clientes desde el RecyclerViewAdapter


        //Pasar el apellido del cliente a la actividad de "Editar_Cliente"
        Apellido_Cliente_EditarCliente  = (TextView) findViewById(R.id.apellido_cliente_editar);

        String extrasTextos = getIntent().getStringExtra("ApellidoEC");

        Apellido_Cliente_EditarCliente.setText(extrasTextos);





        //Pasar el nombre del cliente a la actividad de "Editar_Cliente"
        Nombre_Cliente_EditarCliente  = (TextView) findViewById(R.id.nombre_cliente_editar);

        extrasTextos = getIntent().getStringExtra("NombreEC");

        Nombre_Cliente_EditarCliente.setText(extrasTextos);




        //Pasar la dirección del cliente a la actividad de "Editar_Cliente"
        Direccion_Cliente_EditarCliente  = (TextView) findViewById(R.id.direccion_cliente_editar);

        extrasTextos = getIntent().getStringExtra("DireccionEC");

        Direccion_Cliente_EditarCliente.setText(extrasTextos);



        //Pasar el barrio del cliente a la actividad de "Editar_Cliente"
        Barrio_Cliente_EditarCliente  = (TextView) findViewById(R.id.barrio_cliente_editar);

        extrasTextos = getIntent().getStringExtra("BarrioEC");

        Barrio_Cliente_EditarCliente.setText(extrasTextos);



        //Pasar el teléfono del cliente a la actividad de "Editar_Cliente"
        Telefono_Cliente_EditarCliente  = (TextView) findViewById(R.id.telefono_cliente_editar);

        extrasTextos = getIntent().getStringExtra("TelefonoEC");

        Telefono_Cliente_EditarCliente.setText(extrasTextos);



        //Pasar el correo del cliente a la actividad de "Editar_Cliente"
        Correo_Cliente_EditarCliente  = (TextView) findViewById(R.id.correo_cliente_editar);

        extrasTextos = getIntent().getStringExtra("CorreoEC");

        Correo_Cliente_EditarCliente.setText(extrasTextos);



        //Pasar la referencia del cliente a la actividad de "Editar_Cliente"
        Referencia_Cliente_EditarCliente  = (TextView) findViewById(R.id.referencia_cliente_editar);

        extrasTextos = getIntent().getStringExtra("ReferenciaEC");

        Referencia_Cliente_EditarCliente.setText(extrasTextos);


        c1 = (CheckBox) findViewById(R.id.idcheckbox_lunes);
        c2 = (CheckBox) findViewById(R.id.idcheckbox_martes);
        c3 = (CheckBox) findViewById(R.id.idcheckbox_miercoles);
        c4 = (CheckBox) findViewById(R.id.idcheckbox_jueves);
        c5 = (CheckBox) findViewById(R.id.idcheckbox_viernes);
        c6 = (CheckBox) findViewById(R.id.idcheckbox_sabado);




        eTApellido = (EditText) findViewById(R.id.apellido_cliente_editar);

        eTNombre = (EditText) findViewById(R.id.nombre_cliente_editar);

        eTDireccion = (EditText) findViewById(R.id.direccion_cliente_editar);

        eTBarrio = (EditText) findViewById(R.id.barrio_cliente_editar);

        eTTelefono = (EditText) findViewById(R.id.telefono_cliente_editar);

        eTCorreo = (EditText) findViewById(R.id.correo_cliente_editar);

        eTReferencia = (EditText) findViewById(R.id.referencia_cliente_editar);




    }



    public void onclick (View view) {

        if (view.getId()==R.id.button_aceptar_EC) {

            MethodEditarC();
        } //Fin de la sentencia 'if'

    }


    private void MethodEditarC(){




    } //Fin del método



}
