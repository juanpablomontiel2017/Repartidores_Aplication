package com.example.jumpi.repartidores_aplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ActivityAgregarCliente extends AppCompatActivity {


    /*************** DECLARACIÓN DE VARIABLES GLOBALES *****************/

    ImageView ImageView_Boton_AddCliente;


    EditText eTdniAC, eTApellidoAC, eTNombreAC, eTCodigo_AreaAC, eTTelefonoAC, eTDireccionAC, eTBarrioAC, eTCorreoAC,
             eTReferenciaAC;


    Button buttonCancelar, buttonAgregar;





    /******************** COMIENZO DEL onCreate() **********************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);






        eTdniAC = (EditText) findViewById(R.id.dni_cliente_agregar);
        eTdniAC.requestFocus();
        eTdniAC.addTextChangedListener(new TextWatcher() {
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








        eTApellidoAC = (EditText) findViewById(R.id.apellido_cliente_agregar);
        eTApellidoAC.addTextChangedListener(new TextWatcher() {
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









        eTNombreAC = (EditText) findViewById(R.id.nombre_cliente_agregar);
        eTNombreAC.addTextChangedListener(new TextWatcher() {
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





        eTCodigo_AreaAC = (EditText) findViewById(R.id.codigo_area_cliente_agregar);

        eTTelefonoAC = (EditText) findViewById(R.id.telefono_cliente_agregar);
        eTTelefonoAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));



        /**Deshabilitar el ET al comienzo solo si el código de área tiene dígitos válidos **/
        eTTelefonoAC.setFocusable(false);
        eTTelefonoAC.setCursorVisible(false);
        eTTelefonoAC.setHint("");
        eTTelefonoAC.setText("");
        eTTelefonoAC.setBackgroundColor(Color.TRANSPARENT);





        eTCodigo_AreaAC.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                /*Llamada a la siguiente función: */
                ValidarCodigoArea();



                eTTelefonoAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_colorprimary));


            }
        });










        eTTelefonoAC.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                ValidarTelefono();

            }
        });










        eTTelefonoAC.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(!hasFocus){


                    eTCodigo_AreaAC.addTextChangedListener(new TextWatcher() {
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


                            if (eTCodigo_AreaAC.getText().toString().length() > 2
                                    && eTCodigo_AreaAC.getText().toString().length() < 4
                                    && eTTelefonoAC.getText().toString().length() == 8){



                                eTTelefonoAC.setText(BorrarUltimoCaracter(eTTelefonoAC.getText().toString()));

                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);



                            }



                            else if (eTCodigo_AreaAC.getText().toString().length() > 3
                                    && eTCodigo_AreaAC.getText().toString().length() < 5
                                    && eTTelefonoAC.getText().toString().length() == 7){



                                eTTelefonoAC.setText(BorrarUltimoCaracter(eTTelefonoAC.getText().toString()));

                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);


                            }





                            /******** CASO N°2: Se establece un relación incorrecta entre la cantidad
                             * de dígitos del código de área con la cantidad de dígitos del número
                             * de télefono, arrojando un msj de error.********/


                            else if (eTCodigo_AreaAC.getText().toString().length() > 2
                                    && eTCodigo_AreaAC.getText().toString().length() < 4
                                    && eTTelefonoAC.getText().toString().length() == 6){


                                eTTelefonoAC.setError("¡Número de teléfono no válido!");

                            }



                            else if (eTCodigo_AreaAC.getText().toString().length() > 0
                                    && eTCodigo_AreaAC.getText().toString().length() < 3
                                    && eTTelefonoAC.getText().toString().length() == 7){

                                eTTelefonoAC.setError("¡Número de teléfono no válido!");

                            }






                            /******** CASO N°3: Relación adecuada o correcta entre la cantidad de
                             * dígitos del código de área con la cantidad de dígitos del número de
                             * teléfono.********/


                            else if (eTCodigo_AreaAC.getText().toString().length() > 2
                                    && eTCodigo_AreaAC.getText().toString().length() < 4
                                    && eTTelefonoAC.getText().toString().length() == 7){




                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);


                            }



                            else if (eTCodigo_AreaAC.getText().toString().length() > 3
                                    && eTCodigo_AreaAC.getText().toString().length() < 5
                                    && eTTelefonoAC.getText().toString().length() == 6){




                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);


                            }


                        }
                    });




                }
            }
        });







        eTDireccionAC = (EditText) findViewById(R.id.direccion_cliente_agregar);
        eTDireccionAC.addTextChangedListener(new TextWatcher() {
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






        eTBarrioAC = (EditText) findViewById(R.id.barrio_cliente_agregar);

        eTBarrioAC.addTextChangedListener(new TextWatcher() {
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








        eTCorreoAC = (EditText) findViewById(R.id.correo_cliente_agregar);
        eTCorreoAC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                ValidarEmail(eTCorreoAC);

            }
        });







        eTReferenciaAC = (EditText) findViewById(R.id.referencia_cliente_agregar);
        eTReferenciaAC.addTextChangedListener(new TextWatcher() {
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






        buttonCancelar = (Button) findViewById(R.id.button_cancelar_AC);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                finish();


            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/







        buttonAgregar = (Button) findViewById(R.id.button_agregar_AC);
        buttonAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if(ValidarCamposDelFormularioAntesDeConfirmarNuevoCliente()) {

                    GuardarDatosClienteEnUnSharedPreferences();

                    Intent intent = new Intent (ActivityAgregarCliente.this, ActivityBuscarClienteVentas.class);

                    startActivity(intent);

                    finish();

                }


            }
        });




        ImageView_Boton_AddCliente = (ImageView) findViewById(R.id.img_cliente_agregar);
        ImageView_Boton_AddCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(ActivityAgregarCliente.this);
                dialog.setContentView(R.layout.dialog_seleccion_imagen_cliente);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));





                ImageView img_cliente_uno = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_uno);

                img_cliente_uno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img1_hombrebarbudo_512px);

                    }
                });


                ImageView img_cliente_dos = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_dos);

                img_cliente_dos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img2_hombrerulos_512px);

                    }
                });





                ImageView img_cliente_tres = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_tres);
                img_cliente_tres.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img3_profesor_512px);

                    }
                });







                ImageView img_cliente_cuatro = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_cuatro);
                img_cliente_cuatro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img4_policia_512px);

                    }
                });





                ImageView img_cliente_cinco = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_cinco);
                img_cliente_cinco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img5_sra_512px);

                    }
                });






                ImageView img_cliente_seis = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_seis);
                img_cliente_seis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img6_profesor_512px);

                    }
                });




                ImageView img_cliente_siete = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_siete);
                img_cliente_siete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img7_abuelo_512px);

                    }
                });




                ImageView img_cliente_ocho = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_ocho);
                img_cliente_ocho.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img8_chica_512px);

                    }
                });



                ImageView img_cliente_nueve = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_nueve);
                img_cliente_nueve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img9_dentista_512px);

                    }
                });


                ImageView img_cliente_diez = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_diez);
                img_cliente_diez.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img10_doctor_512px);

                    }
                });



                ImageView img_cliente_once = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_once);
                img_cliente_once.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img11_woman);

                    }
                });




                ImageView img_cliente_doce = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_doce);
                img_cliente_doce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img12_abuela_512px);

                    }
                });



                ImageView img_cliente_trece = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_trece);
                img_cliente_trece.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img13_bombero_512px);

                    }
                });




                ImageView img_cliente_catorce = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_catorce);
                img_cliente_catorce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img14_man_512px);

                    }
                });




                ImageView img_cliente_quince = (ImageView) dialog.findViewById(R.id.dialog_img_cliente_quince);
                img_cliente_quince.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                        ImageView_Boton_AddCliente.setImageResource(R.mipmap.cliente_img15_jardinero_512px);

                    }
                });



                dialog.show();


            }
        });





    }/****************************** FIN DEL onCreate() *********************************/



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





    public void GuardarDatosClienteEnUnSharedPreferences(){


        int indice_cliente = 0;


        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Cliente", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionClientes = sharedPreferences.getString("DimensionClientes", "");

        if(DimensionClientes == ""){

            indice_cliente = 0;


        }//Fin del if


        else {

            indice_cliente = Integer.parseInt(DimensionClientes) + 1;

        }//Fin del else


        editor.putString("DNI_Cliente", eTdniAC.getText().toString());

        editor.putString("Apellido_Cliente", eTApellidoAC.getText().toString());

        editor.putString("Nombre_Cliente", eTNombreAC.getText().toString());

        editor.putString("Codigo_Area_Cliente", eTCodigo_AreaAC.getText().toString());

        editor.putString("Telefono_Cliente", eTTelefonoAC.getText().toString());

        editor.putString("Direccion_Cliente", eTDireccionAC.getText().toString());

        editor.putString("Barrio_Cliente", eTBarrioAC.getText().toString());

        editor.putString("Correo_Cliente", eTCorreoAC.getText().toString());

        editor.putString("Referencia_Cliente", eTReferenciaAC.getText().toString());

        editor.putString("DimensionClientes", String.valueOf(indice_cliente));


        editor.commit();




    }/******************** FIN DE LA FUNCIÓN GuardarDatosClienteEnUnSharedPreferences() *******************/




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


        final EditText ET_DNI_Cliente = (EditText) findViewById(R.id.dni_cliente_agregar);


        final EditText ET_Nombre_Cliente = (EditText) findViewById(R.id.nombre_cliente_agregar);


        final EditText ET_Apellido_Cliente = (EditText) findViewById(R.id.apellido_cliente_agregar);


        final EditText ET_Codigo_Area_Cliente = (EditText) findViewById(R.id.codigo_area_cliente_agregar);


        final EditText ET_Telefono_Cliente = (EditText) findViewById(R.id.telefono_cliente_agregar);


        final EditText ET_Direccion_Cliente = (EditText) findViewById(R.id.direccion_cliente_agregar);


        final EditText ET_Barrio_Cliente = (EditText) findViewById(R.id.barrio_cliente_agregar);


        final EditText ET_Correo_Cliente = (EditText) findViewById(R.id.correo_cliente_agregar);


        final EditText ET_Referencia_Cliente = (EditText) findViewById(R.id.referencia_cliente_agregar);




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






    boolean flag_nuevo_cliente = false;


    public boolean ValidarCamposDelFormularioAntesDeConfirmarNuevoCliente(){


        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {

            if(eTCorreoAC.getText().toString().isEmpty()){


                if (!eTdniAC.getText().toString().isEmpty() && ValidarDocumento()
                        && !eTNombreAC.getText().toString().isEmpty() && ValidarNombre()
                        && !eTApellidoAC.getText().toString().isEmpty() && ValidarApellido()
                        && !eTCodigo_AreaAC.getText().toString().isEmpty() && ValidarCodigoArea()
                        && !eTTelefonoAC.getText().toString().isEmpty() && ValidarTelefono()
                        && !eTDireccionAC.getText().toString().isEmpty() && ValidarDireccion()
                        && !eTBarrioAC.getText().toString().isEmpty() && ValidarBarrio()
                        && !eTReferenciaAC.getText().toString().isEmpty() && ValidarReferencia()){



                    flag_nuevo_cliente = true;

                }



                else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_nuevo_cliente = false;

                }



            }/** FIN DEL if (Campo de CORREO vacío **/





            /**Campo de correo con valor **/
            else {

                if (!eTdniAC.getText().toString().isEmpty() && ValidarDocumento()
                        && !eTNombreAC.getText().toString().isEmpty() && ValidarNombre()
                        && !eTApellidoAC.getText().toString().isEmpty() && ValidarApellido()
                        && !eTCodigo_AreaAC.getText().toString().isEmpty() && ValidarCodigoArea()
                        && !eTTelefonoAC.getText().toString().isEmpty() && ValidarTelefono()
                        && !eTDireccionAC.getText().toString().isEmpty() && ValidarDireccion()
                        && !eTBarrioAC.getText().toString().isEmpty() && ValidarBarrio()
                        &&  ValidarEmail(eTCorreoAC)){


                    flag_nuevo_cliente = true;

                }



                else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_nuevo_cliente = false;

                }





            }/*** FIN DEL else (CORREO No Vacío) ***/






            if (flag_nuevo_cliente) {


                Toast.makeText(getApplicationContext(), "¡Un nuevo cliente se ha añadido con éxito!", Toast.LENGTH_LONG).show();


            } //Fin del if (flag_nuevo_cliente){}


        } /*Fin del 'for'*/



        return flag_nuevo_cliente;


    }/************************************FIN DE LA FUNCIÓN ValidarCamposDelFormularioAntesDeConfirmarNuevoCliente()****************************************/





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





    boolean flag_dni_ac = false;

    public boolean ValidarDocumento() {



        if(eTdniAC.getText().toString().length() > 0 && eTdniAC.getText().toString().length() == 8){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTdniAC.setError("DNI válido", myIconCheck);

            flag_dni_ac = true;


        } else {


            eTdniAC.setError("DNI no válido");

            flag_dni_ac = false;

        }


        return flag_dni_ac;



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



    boolean flag_nombre_ac = false;

    public boolean ValidarNombre() {


        if(eTNombreAC.getText().toString().length() > 2){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTNombreAC.setError("Nombre válido", myIconCheck);


            flag_nombre_ac = true;

        } else {

            eTNombreAC.setError("Nombre no válido");

            flag_nombre_ac = false;

        }


        return flag_nombre_ac;


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

    boolean flag_apellido_ac = false;

    public boolean ValidarApellido() {


        if(eTApellidoAC.getText().toString().length() > 3){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTApellidoAC.setError("Apellido válido", myIconCheck);


            flag_apellido_ac = true;

        } else {

            eTApellidoAC.setError("Apellido no válido");

            flag_apellido_ac = false;

        }



        return flag_apellido_ac;

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






    boolean flag_codigo_area_ac = false;

    public boolean ValidarCodigoArea() {


        if(UtilsCodigoAreaTelefonicoArgentina.Prueba(ActivityAgregarCliente.this,eTCodigo_AreaAC.getText().toString())){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTCodigo_AreaAC.setError("Código de área válido", myIconCheck);


            eTTelefonoAC.setFocusableInTouchMode(true);
            eTTelefonoAC.setCursorVisible(true);
            eTTelefonoAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            eTTelefonoAC.setHint("(Obligatorio)");

            flag_codigo_area_ac = true;


        } else {


            eTCodigo_AreaAC.setError("Código de área no válido");


            eTTelefonoAC.setFocusable(false);
            eTTelefonoAC.setCursorVisible(false);
            eTTelefonoAC.setHint("");
            eTTelefonoAC.setText("");
            eTTelefonoAC.setBackgroundColor(Color.TRANSPARENT);

            flag_codigo_area_ac = false;


        }

        return flag_codigo_area_ac;


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




    boolean flag_telefono_ac = false;

    public boolean ValidarTelefono(){



        if (eTCodigo_AreaAC.getText().toString().length() > 0
                && eTCodigo_AreaAC.getText().toString().length() < 3
                && eTTelefonoAC.getText().toString().length() == 8){

            setEditTextMaxLength(eTTelefonoAC,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_ac = true;


        }



        else if (eTCodigo_AreaAC.getText().toString().length() > 2
                && eTCodigo_AreaAC.getText().toString().length() < 4
                && eTTelefonoAC.getText().toString().length() == 7){

            setEditTextMaxLength(eTTelefonoAC,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_ac = true;


        }



        else if (eTCodigo_AreaAC.getText().toString().length() > 3
                && eTCodigo_AreaAC.getText().toString().length() < 5
                && eTTelefonoAC.getText().toString().length() == 6){

            setEditTextMaxLength(eTTelefonoAC,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTTelefonoAC.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_ac = true;

        }





        else{

            eTTelefonoAC.setError("¡Número de teléfono no válido!");

            flag_telefono_ac = false;

        }


        return flag_telefono_ac;

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



    boolean flag_direccion_ac = false;

    public boolean ValidarDireccion() {


        if(eTDireccionAC.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTDireccionAC.setError("Dirección válida", myIconCheck);


            flag_direccion_ac = true;

        } else {

            eTDireccionAC.setError("Dirección no válida");

            flag_direccion_ac = false;

        }


        return flag_direccion_ac;


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




    boolean flag_barrio_ac = false;

    public boolean ValidarBarrio() {


        if(eTBarrioAC.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTBarrioAC.setError("Barrio válido", myIconCheck);

            flag_barrio_ac = true;

        } else {

            eTBarrioAC.setError("Barrio no válido");

            flag_barrio_ac = false;

        }


        return flag_barrio_ac;

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
                || email.endsWith(".net")) {


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



    boolean flag_correo_ac = false;

    public boolean ValidarEmail(EditText eTCorreoAC){



        if (ObtenerEmailValido(eTCorreoAC.getText().toString())){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTCorreoAC.setError("¡Correo válido!", myIconCheck);

            flag_correo_ac = true;

        }



        else {


            eTCorreoAC.setError("¡Correo no válido!");

            flag_correo_ac = false;

        }

        return flag_correo_ac;

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


    boolean flag_referencia_ac = false;

    public boolean ValidarReferencia() {


        if(eTReferenciaAC.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            eTReferenciaAC.setError("Referencia válida", myIconCheck);

            flag_referencia_ac = true;


        } else {

            eTReferenciaAC.setError("Referencia no válida");

            flag_referencia_ac = false;

        }

        return flag_referencia_ac;


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






}/*************************** FIN DE LA ACTIVITY AgregarCliente *****************************/
