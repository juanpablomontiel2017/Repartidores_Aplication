package com.example.jumpi.repartidores_aplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class ActivityEditarClientes extends AppCompatActivity {


/******************** DECLARACIÓN DE VARIABLES GLOBALES ****************************/



    TextView Recibir_DNI_Cliente_EditarCliente;
    TextView Recibir_Apellido_Cliente_EditarCliente;
    TextView Recibir_Nombre_Cliente_EditarCliente;
    TextView Recibir_Codigo_Area_Cliente_EditarCliente;
    TextView Recibir_Telefono_Cliente_EditarCliente;
    TextView Recibir_Direccion_Cliente_EditarCliente;
    TextView Recibir_Barrio_Cliente_EditarCliente;
    TextView Recibir_Correo_Cliente_EditarCliente;
    TextView Recibir_Referencia_Cliente_EditarCliente;


    TextView Titulo_EditarCliente;
    TextView Titulo_DNI_Cliente_EditarCliente;
    TextView Titulo_Apellido_Cliente_EditarCliente;
    TextView Titulo_Nombre_Cliente_EditarCliente;
    TextView Titulo_Codigo_Area_Cliente_EditarCliente;
    TextView Titulo_Telefono_Cliente_EditarCliente;
    TextView Titulo_Direccion_Cliente_EditarCliente;
    TextView Titulo_Barrio_Cliente_EditarCliente;
    TextView Titulo_Correo_Cliente_EditarCliente;
    TextView Titulo_Referencia_Cliente_EditarCliente;



    EditText eTDNI;
    EditText eTApellido;
    EditText eTNombre;
    EditText eTDireccion;
    EditText eTBarrio;
    EditText eTCodigoArea;
    EditText eTTelefono;
    EditText eTCorreo;
    EditText eTReferencia;

    ImageView Recibir_Foto_Cliente_EditarCliente, ButtonEditar;


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
        Typeface face1=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Apellido_Cliente_EditarCliente.setTypeface(face1);


        Titulo_Nombre_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_nombre_ec);
        Typeface face2 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Nombre_Cliente_EditarCliente.setTypeface(face2);


        Titulo_Codigo_Area_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_codigo_area_ec);
        Typeface face3 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Codigo_Area_Cliente_EditarCliente.setTypeface(face3);


        Titulo_Telefono_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_telefono_ec);
        Typeface face4 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Telefono_Cliente_EditarCliente.setTypeface(face4);


        Titulo_Direccion_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_direccion_ec);
        Typeface face5 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Direccion_Cliente_EditarCliente.setTypeface(face5);


        Titulo_Barrio_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_barrio_ec);
        Typeface face6 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Barrio_Cliente_EditarCliente.setTypeface(face6);


        Titulo_Correo_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_correo_ec);
        Typeface face7 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Correo_Cliente_EditarCliente.setTypeface(face7);


        Titulo_DNI_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_dni_ec);
        Typeface face8 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_DNI_Cliente_EditarCliente.setTypeface(face8);


        Titulo_Referencia_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_referencia_ec);
        Typeface face9 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Referencia_Cliente_EditarCliente.setTypeface(face9);










        eTDNI = (EditText) findViewById(R.id.et_dni_cliente_editar);
        eTDNI.setFocusable(false);
        eTDNI.setCursorVisible(false);
        eTDNI.setBackgroundColor(Color.TRANSPARENT);



        eTApellido = (EditText) findViewById(R.id.et_apellido_cliente_editar);
        eTApellido.setFocusable(false);
        eTApellido.setCursorVisible(false);
        eTApellido.setBackgroundColor(Color.TRANSPARENT);




        eTNombre = (EditText) findViewById(R.id.et_nombre_cliente_editar);
        eTNombre.setFocusable(false);
        eTNombre.setCursorVisible(false);
        eTNombre.setBackgroundColor(Color.TRANSPARENT);




        eTCodigoArea = (EditText) findViewById(R.id.et_codigo_area_cliente_editar);
        eTCodigoArea.setFocusable(false);
        eTCodigoArea.setCursorVisible(false);
        eTCodigoArea.setBackgroundColor(Color.TRANSPARENT);


        eTTelefono = (EditText) findViewById(R.id.et_telefono_cliente_editar);
        eTTelefono.setFocusable(false);
        eTTelefono.setCursorVisible(false);
        eTTelefono.setBackgroundColor(Color.TRANSPARENT);




        eTDireccion = (EditText) findViewById(R.id.et_direccion_cliente_editar);
        eTDireccion.setFocusable(false);
        eTDireccion.setCursorVisible(false);
        eTDireccion.setBackgroundColor(Color.TRANSPARENT);



        eTBarrio = (EditText) findViewById(R.id.et_barrio_cliente_editar);
        eTBarrio.setFocusable(false);
        eTBarrio.setCursorVisible(false);
        eTBarrio.setBackgroundColor(Color.TRANSPARENT);




        eTCorreo = (EditText) findViewById(R.id.et_correo_cliente_editar);
        eTCorreo.setFocusable(false);
        eTCorreo.setCursorVisible(false);
        eTCorreo.setBackgroundColor(Color.TRANSPARENT);



        eTReferencia = (EditText) findViewById(R.id.et_referencia_cliente_editar);
        eTReferencia.setFocusable(false);
        eTReferencia.setCursorVisible(false);
        eTReferencia.setBackgroundColor(Color.TRANSPARENT);





        /*Llamada a la función: */
        RecibirDatosPersonalesDelClienteEC();



        ButtonEditar = (ImageView) findViewById(R.id.img_boton_editar);
        ButtonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditarDatosCliente(true);

            }
        });



        buttonGuardarCambiosEditarCliente = (Button) findViewById(R.id.button_guardar_cambios_ec);
        buttonGuardarCambiosEditarCliente.setBackground(getDrawable(R.drawable.btn_deshabilitado));




        buttonCancelarEditarCliente = (Button) findViewById(R.id.button_cancelar_ec);
        buttonCancelarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();

            }
        });




    }/***************************** FIN DEL onCreate()*******************************/


    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/




    public void RecibirDatosPersonalesDelClienteEC(){


         Recibir_Foto_Cliente_EditarCliente  = (ImageView) findViewById(R.id.img_cliente_editar);

         int recibir_foto = getIntent().getIntExtra("Foto",0);

         Recibir_Foto_Cliente_EditarCliente.setImageResource(recibir_foto);



         Recibir_DNI_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_dni_cliente_editar);

         int extras_dni = getIntent().getIntExtra("DNI",0);

         Recibir_DNI_Cliente_EditarCliente.setText(String.valueOf(extras_dni));




         Recibir_Apellido_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_apellido_cliente_editar);

         String extrasTextos = getIntent().getStringExtra("Apellido");

         Recibir_Apellido_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Nombre_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_nombre_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Nombre");

         Recibir_Nombre_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Codigo_Area_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_codigo_area_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Codigo_Area");

        Recibir_Codigo_Area_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Telefono_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_telefono_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Telefono");

         Recibir_Telefono_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Direccion_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_direccion_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Direccion");

         Recibir_Direccion_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Barrio_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_barrio_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Barrio");

         Recibir_Barrio_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Correo_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_correo_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Correo");

         Recibir_Correo_Cliente_EditarCliente.setText(extrasTextos);




         Recibir_Referencia_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_referencia_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Referencia");

         Recibir_Referencia_Cliente_EditarCliente.setText(extrasTextos);


    }/**********************FIN DE LA FUNCIÓN RecibirDatosPersonalesDelClienteEC()********************************/



    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/



    public void EditarDatosCliente(boolean flag_edit){

        if (flag_edit) {


            final EditText editText_dni_cliente = (EditText) findViewById(R.id.et_dni_cliente_editar);

            final EditText editText_apellido_cliente= (EditText) findViewById(R.id.et_apellido_cliente_editar);

            final EditText editText_nombre_cliente = (EditText) findViewById(R.id.et_nombre_cliente_editar);

            final EditText editText_codigo_area_cliente = (EditText) findViewById(R.id.et_codigo_area_cliente_editar);

            final EditText editText_telefono_cliente = (EditText) findViewById(R.id.et_telefono_cliente_editar);

            final EditText editText_direccion_cliente = (EditText) findViewById(R.id.et_direccion_cliente_editar);

            final EditText editText_barrio_cliente = (EditText) findViewById(R.id.et_barrio_cliente_editar);

            final EditText editText_correo_cliente = (EditText) findViewById(R.id.et_correo_cliente_editar);

            final EditText editText_referencia_cliente = (EditText) findViewById(R.id.et_referencia_cliente_editar);



            final Button btn_guardar_cambios_ec = (Button) findViewById(R.id.button_guardar_cambios_ec);



            /** Habilitar todas las vistas **/

            editText_dni_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_dni_cliente.setFocusableInTouchMode(true);
            editText_dni_cliente.setCursorVisible(true);
            editText_dni_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_apellido_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_apellido_cliente.setFocusableInTouchMode(true);
            editText_apellido_cliente.setCursorVisible(true);
            editText_apellido_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_nombre_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_nombre_cliente.setFocusableInTouchMode(true);
            editText_nombre_cliente.setCursorVisible(true);
            editText_nombre_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_codigo_area_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_codigo_area_cliente.setFocusableInTouchMode(true);
            editText_codigo_area_cliente.setCursorVisible(true);
            editText_codigo_area_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_telefono_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_telefono_cliente.setFocusableInTouchMode(true);
            editText_telefono_cliente.setCursorVisible(true);
            editText_telefono_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_direccion_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_direccion_cliente.setFocusableInTouchMode(true);
            editText_direccion_cliente.setCursorVisible(true);
            editText_direccion_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_barrio_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_barrio_cliente.setFocusableInTouchMode(true);
            editText_barrio_cliente.setCursorVisible(true);
            editText_barrio_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_correo_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_correo_cliente.setFocusableInTouchMode(true);
            editText_correo_cliente.setCursorVisible(true);
            editText_correo_cliente.setTextColor(Color.parseColor("#64c1ff"));


            editText_referencia_cliente.setFocusableInTouchMode(true);
            editText_referencia_cliente.setCursorVisible(true);
            editText_referencia_cliente.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_referencia_cliente.setTextColor(Color.parseColor("#64c1ff"));



            /**** Comprobar si cada campo de texto es valido (arrojando un check o un msj de error) ****/

            editText_dni_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {



                    /*Llamada a la función: */
                    ValidarDocumento();


                }
            });





            editText_apellido_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    /*Llamada a la función: */
                    ValidarApellido();

                }
            });





            editText_nombre_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    /*Llamada a la función: */
                    ValidarNombre();

                }
            });








            editText_codigo_area_cliente.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {


                    /*Llamada a la función: */
                    ValidarCodigoArea();



                }
            });



            editText_telefono_cliente.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    /*Llamada a la función: */
                    ValidarTelefono();

                }
            });










            editText_telefono_cliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View view, boolean hasFocus) {

                    if(!hasFocus){


                        editText_codigo_area_cliente.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {





                                /******** CASO N°1: Relación adecuada o correcta entre la cantidad de
                                 * dígitos del código de área con la cantidad de dígitos del número de
                                 * teléfono. Pero si se escribe o añade otro dígito en el código de área, entonces
                                 * debería borrarse el último dígito del campo de número de teléfono.********/


                                if (editText_codigo_area_cliente.getText().toString().length() > 2
                                        && editText_codigo_area_cliente.getText().toString().length() < 4
                                        && editText_telefono_cliente.getText().toString().length() == 8){



                                    editText_telefono_cliente.setText(BorrarUltimoCaracter(editText_telefono_cliente.getText().toString()));

                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_cliente.setError("Número de teléfono valido", myIconCheck);



                                }



                                else if (editText_codigo_area_cliente.getText().toString().length() > 3
                                        && editText_codigo_area_cliente.getText().toString().length() < 5
                                        && editText_telefono_cliente.getText().toString().length() == 7){



                                    editText_telefono_cliente.setText(BorrarUltimoCaracter(editText_telefono_cliente.getText().toString()));

                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_cliente.setError("Número de teléfono valido", myIconCheck);


                                }





                                /******** CASO N°2: Se establece un relación incorrecta entre la cantidad
                                 * de dígitos del código de área con la cantidad de dígitos del número
                                 * de télefono, arrojando un msj de error.********/


                                else if (editText_codigo_area_cliente.getText().toString().length() > 2
                                        && editText_codigo_area_cliente.getText().toString().length() < 4
                                        && editText_telefono_cliente.getText().toString().length() == 6){


                                    editText_telefono_cliente.setError("¡Número de teléfono no válido!");

                                }



                                else if (editText_codigo_area_cliente.getText().toString().length() > 0
                                        && editText_codigo_area_cliente.getText().toString().length() < 3
                                        && editText_telefono_cliente.getText().toString().length() == 7){

                                    editText_telefono_cliente.setError("¡Número de teléfono no válido!");

                                }






                                /******** CASO N°3: Relación adecuada o correcta entre la cantidad de
                                 * dígitos del código de área con la cantidad de dígitos del número de
                                 * teléfono.********/


                                else if (editText_codigo_area_cliente.getText().toString().length() > 2
                                        && editText_codigo_area_cliente.getText().toString().length() < 4
                                        && editText_telefono_cliente.getText().toString().length() == 7){





                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_cliente.setError("Número de teléfono valido", myIconCheck);


                                }



                                else if (editText_codigo_area_cliente.getText().toString().length() > 3
                                        && editText_codigo_area_cliente.getText().toString().length() < 5
                                        && editText_telefono_cliente.getText().toString().length() == 6){




                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_cliente.setError("Número de teléfono valido", myIconCheck);


                                }


                            }
                        });




                    }
                }
            });






            editText_direccion_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    /*Llamada a la función: */
                    ValidarDireccion();

                }
            });








            editText_barrio_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    /*Llamada a la función: */
                    ValidarBarrio();

                }
            });








            editText_correo_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                    /*Llamada a la función: */
                    ValidarEmail(editText_correo_cliente);


                }
            });






            editText_correo_cliente.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    /*Llamada a la función: */
                    ValidarReferencia();

                }
            });





            btn_guardar_cambios_ec.setBackground(getDrawable(R.drawable.btn_confirmar_uno));
            btn_guardar_cambios_ec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    if(DeshabilitarVistasAlGuardarCambios(ValidarCamposParaGuardarCambiosEditarCliente())){

                        btn_guardar_cambios_ec.setEnabled(true);


                        //GuardarCambiosDeEdicionClienteEnUnSharedPreferences();


                    }


                }
            });





        }  /*Fin del primer if (flag_edit) {}*/







    }/***************************FIN DE LA FUNCIÓN EditarDatosCliente() **********************************/




    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/




    public boolean DeshabilitarVistasAlGuardarCambios(boolean flag_enabled){

        if (flag_enabled) {


            final EditText editText_dni_cliente = (EditText) findViewById(R.id.et_dni_cliente_editar);

            final EditText editText_apellido_cliente= (EditText) findViewById(R.id.et_apellido_cliente_editar);

            final EditText editText_nombre_cliente = (EditText) findViewById(R.id.et_nombre_cliente_editar);

            final EditText editText_codigo_area_cliente = (EditText) findViewById(R.id.et_codigo_area_cliente_editar);

            final EditText editText_telefono_cliente = (EditText) findViewById(R.id.et_telefono_cliente_editar);

            final EditText editText_direccion_cliente = (EditText) findViewById(R.id.et_direccion_cliente_editar);

            final EditText editText_barrio_cliente = (EditText) findViewById(R.id.et_barrio_cliente_editar);

            final EditText editText_correo_cliente = (EditText) findViewById(R.id.et_correo_cliente_editar);

            final EditText editText_referencia_cliente = (EditText) findViewById(R.id.et_referencia_cliente_editar);



            final Button btn_guardar_cambios_ec = (Button) findViewById(R.id.button_guardar_cambios_ec);




            /** Deshabilitar todas las vistas **/

            editText_dni_cliente.setFocusable(false);
            editText_dni_cliente.setBackgroundColor(Color.TRANSPARENT);


            editText_nombre_cliente.setFocusable(false);
            editText_nombre_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_apellido_cliente.setFocusable(false);
            editText_apellido_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_codigo_area_cliente.setFocusable(false);
            editText_codigo_area_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_telefono_cliente.setFocusable(false);
            editText_telefono_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_direccion_cliente.setFocusable(false);
            editText_direccion_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_barrio_cliente.setFocusable(false);
            editText_barrio_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_correo_cliente.setFocusable(false);
            editText_correo_cliente.setBackgroundColor(Color.TRANSPARENT);

            editText_referencia_cliente.setFocusable(false);
            editText_referencia_cliente.setBackgroundColor(Color.TRANSPARENT);


            btn_guardar_cambios_ec.setBackground(getDrawable(R.drawable.btn_deshabilitado));
            btn_guardar_cambios_ec.setEnabled(false);


        }  /*Fin del primer if (flag_enabled) {}*/



        return flag_enabled;



    }/*******FIN DE LA FUNCIÓN DeshabilitarVistasAlGuardarCambios() ********/




    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/




    public void GuardarCambiosDeEdicionClienteEnUnSharedPreferences(){


        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Cliente", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("DNI_Cliente", eTDNI.getText().toString());

        editor.putString("Apellido_Cliente", eTApellido.getText().toString());

        editor.putString("Nombre_Cliente", eTNombre.getText().toString());

        editor.putString("Codigo_Area_Cliente", eTCodigoArea.getText().toString());

        editor.putString("Telefono_Cliente", eTTelefono.getText().toString());

        editor.putString("Direccion_Cliente", eTDireccion.getText().toString());

        editor.putString("Barrio_Cliente", eTBarrio.getText().toString());

        editor.putString("Correo_Cliente", eTCorreo.getText().toString());

        editor.putString("Referencia_Cliente", eTReferencia.getText().toString());


        editor.commit();




    }/******************** FIN DE LA FUNCIÓN GuardarCambiosDeEdicionClienteEnUnSharedPreferences() *******************/




    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/




    public void LeerDatosPersonalesCliente() {


        final EditText ET_DNI_Cliente = (EditText) findViewById(R.id.et_dni_cliente_editar);


        final EditText ET_Nombre_Cliente = (EditText) findViewById(R.id.et_nombre_cliente_editar);


        final EditText ET_Apellido_Cliente = (EditText) findViewById(R.id.et_apellido_cliente_editar);


        final EditText ET_Codigo_Area_Cliente = (EditText) findViewById(R.id.et_codigo_area_cliente_editar);


        final EditText ET_Telefono_Cliente = (EditText) findViewById(R.id.et_telefono_cliente_editar);


        final EditText ET_Direccion_Cliente = (EditText) findViewById(R.id.et_direccion_cliente_editar);


        final EditText ET_Barrio_Cliente = (EditText) findViewById(R.id.et_barrio_cliente_editar);


        final EditText ET_Correo_Cliente = (EditText) findViewById(R.id.et_correo_cliente_editar);


        final EditText ET_Referencia_Cliente = (EditText) findViewById(R.id.et_dni_cliente_editar);




        SharedPreferences preferences = getSharedPreferences("Datos_Cliente", MODE_PRIVATE);


        String DNI_Cliente = preferences.getString("DNI_Cliente", "");
        String Nombre_Cliente = preferences.getString("Nombre_Cliente", "");
        String Apellido_Cliente = preferences.getString("Apellido_Cliente", "");
        String Codigo_Area_Cliente = preferences.getString("Codigo_Area_Cliente", "");
        String Telefono_Cliente = preferences.getString("Telefono_Cliente", "");
        String Direccion_Cliente = preferences.getString("Direccion_Cliente", "");
        String Barrio_Cliente = preferences.getString("Barrio_Cliente", "");
        String Correo_Cliente = preferences.getString("Correo_Cliente", "");
        String Referencia_Cliente = preferences.getString("Referencia_Cliente", "");




        ET_DNI_Cliente.setText(DNI_Cliente);
        ET_Nombre_Cliente.setText(Nombre_Cliente);
        ET_Apellido_Cliente.setText(Apellido_Cliente);
        ET_Codigo_Area_Cliente.setText(Codigo_Area_Cliente);
        ET_Telefono_Cliente.setText(Telefono_Cliente);
        ET_Direccion_Cliente.setText(Direccion_Cliente);
        ET_Barrio_Cliente.setText(Barrio_Cliente);
        ET_Correo_Cliente.setText(Correo_Cliente);
        ET_Referencia_Cliente.setText(Referencia_Cliente);


    }/***************************FIN DE LA FUNCIÓN LeerDatosPersonalesCliente()*****************************/






    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/




    boolean flag_validacion_campos_ec = false;

    public boolean ValidarCamposParaGuardarCambiosEditarCliente(){



        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {



            if(eTCorreo.getText().toString().isEmpty() || eTCorreo.getText().toString().equals("No tiene correo")){


                if (!eTDNI.getText().toString().isEmpty() && ValidarDocumento()
                        && !eTNombre.getText().toString().isEmpty() && ValidarNombre()
                        && !eTApellido.getText().toString().isEmpty() && ValidarApellido()
                        && !eTCodigoArea.getText().toString().isEmpty() && ValidarCodigoArea()
                        && !eTTelefono.getText().toString().isEmpty() && ValidarTelefono()
                        && !eTDireccion.getText().toString().isEmpty() && ValidarDireccion()
                        && !eTBarrio.getText().toString().isEmpty() && ValidarBarrio()
                        && !eTReferencia.getText().toString().isEmpty() && ValidarReferencia()){


                    flag_validacion_campos_ec = true;

                }



                else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_validacion_campos_ec = false;

                }



            }/** FIN DEL if (Campo de CORREO vacío **/





            /**Campo de correo con valor **/
            else {

                if (!eTDNI.getText().toString().isEmpty() && ValidarDocumento()
                        && !eTNombre.getText().toString().isEmpty() && ValidarNombre()
                        && !eTApellido.getText().toString().isEmpty() && ValidarApellido()
                        && !eTCodigoArea.getText().toString().isEmpty() && ValidarCodigoArea()
                        && !eTTelefono.getText().toString().isEmpty() && ValidarTelefono()
                        && !eTDireccion.getText().toString().isEmpty() && ValidarDireccion()
                        && !eTBarrio.getText().toString().isEmpty() && ValidarBarrio()
                        &&  ValidarEmail(eTCorreo)){




                    flag_validacion_campos_ec = true;

                }



                else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_validacion_campos_ec = false;

                }





            }/*** FIN DEL else (CORREO No Vacío) ***/






            if (flag_validacion_campos_ec) {


                Toast.makeText(getApplicationContext(), "¡Los cambios de edición fueron guardados con éxito!", Toast.LENGTH_LONG).show();


            } //Fin del if (flag_validacion_campos_ec){}


        } /*Fin del 'for'*/

        return flag_validacion_campos_ec;




    }/********** FIN DE LA FUNCIÓN ValidarCamposParaGuardarCambiosEditarCliente() ***********/





    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/
    /**********************************************************************************/



    public void setEditTextMaxLength(EditText editText, int length) {

        InputFilter[] FilterArray = new InputFilter[1];

        FilterArray[0] = new InputFilter.LengthFilter(length);

        editText.setFilters(FilterArray);


    }/************** FIN DE LA FUNCIÓN setEditTextMaxLength() *******************/







    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/





    public String BorrarUltimoCaracter(String str) {

        if (str != null && str.length() > 0) {

            str = str.substring(0, str.length()-1);

        }

        return str;

    }/******************** FIN DE LA FUNCIÓN BorrarUltimoCaracter() ******************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    boolean flag_dni_ec = false;

    public boolean ValidarDocumento() {



        if(eTDNI.getText().toString().length() > 0 && eTDNI.getText().toString().length() == 8){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTDNI.setError("DNI válido", myIconCheck);

            flag_dni_ec = true;


        } else {


            eTDNI.setError("DNI no válido");

            flag_dni_ec = false;

        }


        return flag_dni_ec;


    }   /**********************FIN DE LA FUNCIÓN ValidarDocumento()*********************/










    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/



    boolean flag_nombre_ec = false;

    public boolean ValidarNombre() {


        if(eTNombre.getText().toString().length() > 2){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTNombre.setError("Nombre válido", myIconCheck);


            flag_nombre_ec = true;

        } else {

            eTNombre.setError("Nombre no válido");

            flag_nombre_ec = false;

        }


        return flag_nombre_ec;


    }   /**********************FIN DE LA FUNCIÓN ValidarNombre()*********************/




    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/



    boolean flag_apellido_ec = false;

    public boolean ValidarApellido() {


        if(eTApellido.getText().toString().length() > 3){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTApellido.setError("Apellido válido", myIconCheck);


            flag_apellido_ec = true;

        } else {

            eTApellido.setError("Apellido no válido");

            flag_apellido_ec = false;

        }



        return flag_apellido_ec;

    }   /**********************FIN DE LA FUNCIÓN ValidarApellido()*********************/




    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/






    boolean flag_codigo_area_ec = false;

    public boolean ValidarCodigoArea() {


        if(UtilsCodigoAreaTelefonicoArgentina.Prueba(ActivityEditarClientes.this,eTCodigoArea.getText().toString())){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTCodigoArea.setError("Código de área válido", myIconCheck);


            eTTelefono.setFocusableInTouchMode(true);
            eTTelefono.setCursorVisible(true);
            eTTelefono.setHint("(Obligatorio)");
            eTTelefono.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));

            flag_codigo_area_ec = true;


        } else {


            eTCodigoArea.setError("Código de área no válido");


            eTTelefono.setFocusable(false);
            eTTelefono.setCursorVisible(false);
            eTTelefono.setHint("");
            eTTelefono.setText("");
            eTTelefono.setBackgroundColor(Color.TRANSPARENT);

            flag_codigo_area_ec = false;


        }

        return flag_codigo_area_ec;


    }   /**********************FIN DE LA FUNCIÓN ValidarCodigoArea()*********************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    boolean flag_telefono_ec = false;

    public boolean ValidarTelefono(){



        if (eTCodigoArea.getText().toString().length() > 0
                && eTCodigoArea.getText().toString().length() < 3
                && eTTelefono.getText().toString().length() == 8){

            setEditTextMaxLength(eTTelefono,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTTelefono.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_ec = true;


        }



        else if (eTCodigoArea.getText().toString().length() > 2
                && eTCodigoArea.getText().toString().length() < 4
                && eTTelefono.getText().toString().length() == 7){

            setEditTextMaxLength(eTTelefono,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTTelefono.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_ec = true;


        }



        else if (eTCodigoArea.getText().toString().length() > 3
                && eTCodigoArea.getText().toString().length() < 5
                && eTTelefono.getText().toString().length() == 6){

            setEditTextMaxLength(eTTelefono,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTTelefono.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_ec = true;

        }





        else{

            eTTelefono.setError("¡Número de teléfono no válido!");

            flag_telefono_ec = false;

        }


        return flag_telefono_ec;

    }/**********************FIN DE LA FUNCIÓN ValidarTelefono()*********************/



    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/



    boolean flag_direccion_ec = false;

    public boolean ValidarDireccion() {


        if(eTDireccion.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTDireccion.setError("Dirección válida", myIconCheck);


            flag_direccion_ec = true;

        } else {

            eTDireccion.setError("Dirección no válida");

            flag_direccion_ec = false;

        }


        return flag_direccion_ec;


    }   /**********************FIN DE LA FUNCIÓN ValidarDireccionResponsable()*********************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    boolean flag_barrio_ec = false;

    public boolean ValidarBarrio() {


        if(eTBarrio.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTBarrio.setError("Barrio válido", myIconCheck);

            flag_barrio_ec = true;

        } else {

            eTBarrio.setError("Barrio no válido");

            flag_barrio_ec = false;

        }


        return flag_barrio_ec;

    }   /**********************FIN DE LA FUNCIÓN ValidarBarrioResponsable()*********************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/



    private boolean ObtenerEmailValido(String email) {


        if (email.endsWith(".com")
                || email.endsWith(".com.ar")
                || email.endsWith(".net") ) {


            Pattern pattern = Patterns.EMAIL_ADDRESS;


            return pattern.matcher(email).matches();


        } else {


            return false;

        }



    }  /**********************FIN DE LA FUNCIÓN ObtenerEmailValido()*********************/



    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/



    boolean flag_correo_ec = false;

    public boolean ValidarEmail(EditText eTCorreo){


        if (ObtenerEmailValido(eTCorreo.getText().toString())){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTCorreo.setError("¡Correo válido!", myIconCheck);

            flag_correo_ec = true;

        }



        else {


            eTCorreo.setError("¡Correo no válido!");

            flag_correo_ec = false;

        }

        return flag_correo_ec;

    }/**********************FIN DE LA FUNCIÓN ValidarEmail()*********************/


    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/


    boolean flag_referencia_ec = false;

    public boolean ValidarReferencia() {


        if(eTReferencia.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTReferencia.setError("Referencia válida", myIconCheck);

            flag_referencia_ec = true;


        } else {

            eTReferencia.setError("Referencia no válida");

            flag_referencia_ec = false;

        }

        return flag_referencia_ec;


    }   /**********************FIN DE LA FUNCIÓN ValidarReferencia()*********************/




    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/






}/******************************** FIN DE LA ACTIVITY EditarClientes *******************************/
