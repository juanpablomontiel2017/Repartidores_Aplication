package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class AgregarCliente extends AppCompatActivity {

    //Declaración de variables para cada CheckBox correspondiente a los 6 días laborales

    CheckBox c1, c2, c3, c4, c5, c6;


    EditText eTApellidoAC;
    EditText eTNombreAC;
    EditText eTDireccionAC;
    EditText eTBarrioAC;
    EditText eTTelefonoAC;
    EditText eTCorreoAC;
    EditText eTdniAC;
    EditText eTReferenciaAC;


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
/*
                Intent buttonCancelarC = new Intent(AgregarCliente.this, Second_Activity.class);
                startActivity(buttonCancelarC);
*/
                finish();

            }
        });



        final Button buttonAgregar = (Button) findViewById(R.id.button_agregar_AC);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                String AuxiliarApellidoAC = eTApellidoAC.getText().toString();
                String AuxiliarNombreAC = eTNombreAC.getText().toString();
                String AuxiliarDireccionAC = eTDireccionAC.getText().toString();
                String AuxiliarBarrioAC = eTBarrioAC.getText().toString();
                String AuxiliarTelefonoAC = eTTelefonoAC.getText().toString();
                String AuxiliarCorreoAC = eTCorreoAC.getText().toString();
                String AuxiliarDNIAC = eTdniAC.getText().toString();
                String AuxiliarReferenciaAC = eTReferenciaAC.getText().toString();






                //Estructuras selectivas utilizadas para comprobar que ningún campo importante como el 'Apellido' 'Nombre' 'Dirección' 'Barrio' y 'Referencia' quede vacío.

                if (AuxiliarApellidoAC.isEmpty() && AuxiliarNombreAC.isEmpty() && AuxiliarDireccionAC.isEmpty() && AuxiliarBarrioAC.isEmpty() && AuxiliarTelefonoAC.isEmpty() && AuxiliarCorreoAC.isEmpty() && AuxiliarDNIAC.isEmpty() && AuxiliarReferenciaAC.isEmpty() ) {

                    Toast.makeText(getApplicationContext(),"Error! Todos los campos estan vacios. Por favor, complete los campos correspondientes con datos válidos",Toast.LENGTH_LONG).show();
                }




                else if (AuxiliarApellidoAC.isEmpty() && AuxiliarNombreAC.isEmpty() && AuxiliarDireccionAC.isEmpty() && AuxiliarBarrioAC.isEmpty()&& AuxiliarDNIAC.isEmpty() && AuxiliarReferenciaAC.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Error! Los campos: Apellido, nombre, dirección, barrio, DNI y referencia son obligatorios. Por favor, verifíquelos!",Toast.LENGTH_LONG).show();





                } else  if (AuxiliarApellidoAC.isEmpty() ) {


                    Toast.makeText(getApplicationContext(),"Error! El campo 'apellido' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();


                } else if (AuxiliarNombreAC.isEmpty()) {


                    Toast.makeText(getApplicationContext(),"Error! El campo 'nombre' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                } else if (AuxiliarDireccionAC.isEmpty()) {


                    Toast.makeText(getApplicationContext(),"Error! El campo 'dirección' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                } else  if (AuxiliarBarrioAC.isEmpty()) {

                    Toast.makeText(getApplicationContext(),"Error! El campo 'barrio' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();


                } else if(AuxiliarDNIAC.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Error! El campo 'DNI' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();
                }


                else if (AuxiliarReferenciaAC.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Error! El campo 'referencia' está vacío. Por favor complete el campo correspondiente con datos válidos !",Toast.LENGTH_LONG).show();

                    //Para que ningun CheckBox quede sin seleccionar
                } else if (c1.isChecked() == false && c2.isChecked() == false && c3.isChecked()==false && c4.isChecked()==false && c5.isChecked()==false && c6.isChecked()==false ){


                    Toast.makeText(getApplicationContext(),"Error! No ha seleccionado un día de reparto. Por favor, seleccione al menos uno",Toast.LENGTH_LONG).show();

                } else {

                    /*
                Intent buttonAgregarA = new Intent(AgregarCliente.this, Ventas_Activity.class);
                startActivity(buttonAgregarA);
*/


                    Toast.makeText(getApplicationContext(),"El cliente fue añadido al sistema con éxito",Toast.LENGTH_LONG).show();

                    finish();
                }






            }
        });


        c1 = (CheckBox) findViewById(R.id.idcheckbox_lunes);
        c2 = (CheckBox) findViewById(R.id.idcheckbox_martes);
        c3 = (CheckBox) findViewById(R.id.idcheckbox_miercoles);
        c4 = (CheckBox) findViewById(R.id.idcheckbox_jueves);
        c5 = (CheckBox) findViewById(R.id.idcheckbox_viernes);
        c6 = (CheckBox) findViewById(R.id.idcheckbox_sabado);


        eTApellidoAC = (EditText) findViewById(R.id.apellido_cliente_agregar);

        eTNombreAC = (EditText) findViewById(R.id.nombre_cliente_agregar);

        eTDireccionAC = (EditText) findViewById(R.id.direccion_cliente_agregar);

        eTBarrioAC = (EditText) findViewById(R.id.barrio_cliente_agregar);

        eTTelefonoAC = (EditText) findViewById(R.id.telefono_cliente_agregar);

        eTCorreoAC = (EditText) findViewById(R.id.correo_cliente_agregar);

        eTdniAC = (EditText) findViewById(R.id.dni_cliente_agregar);

        eTReferenciaAC = (EditText) findViewById(R.id.referencia_cliente_agregar);


    }

  public void onclick (View view) {

        if (view.getId()==R.id.button_agregar_AC) {

            MethodAgregarC();
        } //Fin de la sentencia 'if'

  }


    private void MethodAgregarC(){




    } //Fin del método






}
