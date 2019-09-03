package com.example.jumpi.repartidores_aplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarClientes extends AppCompatActivity {


/******************** DECLARACIÓN DE VARIABLES GLOBALES ****************************/



    TextView Recibir_Apellido_Cliente_EditarCliente;
    TextView Recibir_Nombre_Cliente_EditarCliente;
    TextView Recibir_Direccion_Cliente_EditarCliente;
    TextView Recibir_Barrio_Cliente_EditarCliente;
    TextView Recibir_Telefono_Cliente_EditarCliente;
    TextView Recibir_Correo_Cliente_EditarCliente;
    TextView Recibir_DNI_Cliente_EditarCliente;
    TextView Recibir_Referencia_Cliente_EditarCliente;


    TextView Titulo_EditarCliente;
    TextView Titulo_Apellido_Cliente_EditarCliente;
    TextView Titulo_Nombre_Cliente_EditarCliente;
    TextView Titulo_Direccion_Cliente_EditarCliente;
    TextView Titulo_Barrio_Cliente_EditarCliente;
    TextView Titulo_Telefono_Cliente_EditarCliente;
    TextView Titulo_Correo_Cliente_EditarCliente;
    TextView Titulo_DNI_Cliente_EditarCliente;
    TextView Titulo_Referencia_Cliente_EditarCliente;





    EditText eTApellido;
    EditText eTNombre;
    EditText eTDireccion;
    EditText eTBarrio;
    EditText eTTelefono;
    EditText eTCorreo;
    EditText eTDNI;
    EditText eTReferencia;



    Button buttonCancelarEditarCliente, buttonGuardarCambiosEditarCliente;








    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_clientes);






        Titulo_EditarCliente = (TextView) findViewById(R.id.tv_editar_cliente_titulo);
        Typeface face0=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_EditarCliente.setTypeface(face0);


        Titulo_Apellido_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_apellido_ec);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Apellido_Cliente_EditarCliente.setTypeface(face);


        Titulo_Nombre_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_nombre_ec);
        Typeface face1 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Nombre_Cliente_EditarCliente.setTypeface(face1);


        Titulo_Direccion_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_direccion_ec);
        Typeface face2 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Direccion_Cliente_EditarCliente.setTypeface(face2);


        Titulo_Barrio_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_barrio_ec);
        Typeface face3 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Barrio_Cliente_EditarCliente.setTypeface(face3);


        Titulo_Telefono_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_telefono_ec);
        Typeface face4 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Telefono_Cliente_EditarCliente.setTypeface(face4);


        Titulo_Correo_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_correo_ec);
        Typeface face5 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Correo_Cliente_EditarCliente.setTypeface(face5);


        Titulo_DNI_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_dni_ec);
        Typeface face6 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_DNI_Cliente_EditarCliente.setTypeface(face6);


        Titulo_Referencia_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_referencia_ec);
        Typeface face7 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Referencia_Cliente_EditarCliente.setTypeface(face7);











        eTApellido = (EditText) findViewById(R.id.et_apellido_cliente_editar);

        eTNombre = (EditText) findViewById(R.id.et_nombre_cliente_editar);

        eTDireccion = (EditText) findViewById(R.id.et_direccion_cliente_editar);

        eTBarrio = (EditText) findViewById(R.id.et_barrio_cliente_editar);

        eTTelefono = (EditText) findViewById(R.id.et_telefono_cliente_editar);

        eTCorreo = (EditText) findViewById(R.id.et_correo_cliente_editar);

        eTDNI = (EditText) findViewById(R.id.et_dni_cliente_editar);

        eTReferencia = (EditText) findViewById(R.id.et_referencia_cliente_editar);




        /*Llamada a la función: */
        RecibirDatosPersonalesDelClienteEC();



        buttonGuardarCambiosEditarCliente = (Button) findViewById(R.id.button_guardar_cambios_ec);

        buttonGuardarCambiosEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                /*Llamada a la función: */
                ValidarCamposParaGuardarCambiosEditarCliente();



            }
        });






        buttonCancelarEditarCliente = (Button) findViewById(R.id.button_cancelar_ec);

        buttonCancelarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();

            }
        });





    }/***************************** FIN DEL onCreate()*******************************/







    public void RecibirDatosPersonalesDelClienteEC(){


         Recibir_Apellido_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_apellido_cliente_editar);

         String extrasTextos = getIntent().getStringExtra("ApellidoEC");

         Recibir_Apellido_Cliente_EditarCliente.setText(extrasTextos);





         Recibir_Nombre_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_nombre_cliente_editar);

         extrasTextos = getIntent().getStringExtra("NombreEC");

         Recibir_Nombre_Cliente_EditarCliente.setText(extrasTextos);




         Recibir_Direccion_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_direccion_cliente_editar);

         extrasTextos = getIntent().getStringExtra("DireccionEC");

         Recibir_Direccion_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Barrio_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_barrio_cliente_editar);

         extrasTextos = getIntent().getStringExtra("BarrioEC");

         Recibir_Barrio_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Telefono_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_telefono_cliente_editar);

         extrasTextos = getIntent().getStringExtra("TelefonoEC");

         Recibir_Telefono_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Correo_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_correo_cliente_editar);

         extrasTextos = getIntent().getStringExtra("CorreoEC");

         Recibir_Correo_Cliente_EditarCliente.setText(extrasTextos);




         Recibir_DNI_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_dni_cliente_editar);

         extrasTextos = getIntent().getStringExtra("DNIEC");

         Recibir_DNI_Cliente_EditarCliente.setText(extrasTextos);


         Recibir_Referencia_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_referencia_cliente_editar);

         extrasTextos = getIntent().getStringExtra("ReferenciaEC");

         Recibir_Referencia_Cliente_EditarCliente.setText(extrasTextos);


    }/**********************FIN DE LA FUNCIÓN RecibirDatosPersonalesDelClienteEC()********************************/










    boolean flag_validacion_campos_editar_cliente = false;

    public boolean ValidarCamposParaGuardarCambiosEditarCliente(){


        String AuxiliarApellidoE = eTApellido.getText().toString();

        String AuxiliarNombreE = eTNombre.getText().toString();

        String AuxiliarDireccionE = eTDireccion.getText().toString();

        String AuxiliarBarrioE = eTBarrio.getText().toString();

        String AuxiliarTelefonoE = eTTelefono.getText().toString();

        String AuxiliarCorreoE = eTCorreo.getText().toString();

        String AuxiliarDNIE = eTDNI.getText().toString();

        String AuxiliarReferenciaE = eTReferencia.getText().toString();




        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {

        /**Primer Validación: todos los campos obligatorios deben estar rellenados**/

            if (!AuxiliarApellidoE.isEmpty() || !AuxiliarNombreE.toString().isEmpty() || !AuxiliarDireccionE.toString().isEmpty()
                || !AuxiliarBarrioE.toString().isEmpty() || !AuxiliarTelefonoE.toString().isEmpty()
                || !AuxiliarDNIE.toString().isEmpty() || !AuxiliarReferenciaE.toString().isEmpty() ){

            flag_validacion_campos_editar_cliente = true;

            }

            else{

                Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios.", Toast.LENGTH_LONG).show();


                flag_validacion_campos_editar_cliente = false;

            }




        if (flag_validacion_campos_editar_cliente) {

            finish();

            Toast.makeText(getApplicationContext(), "¡Los cambios fueron realizados con éxito!", Toast.LENGTH_LONG).show();


        } //Fin del if (flag_validacion_campos_editar_cliente){}





        } /*Fin del 'for'*/

        return flag_validacion_campos_editar_cliente;





}/********** FIN DE LA FUNCIÓN ValidarCamposParaGuardarCambiosEditarCliente() ***********/














}/******************************** FIN DE LA ACTIVITY EditarClientes *******************************/
