package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

public class NuevoResponsablePatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/

    EditText et_dni_nuevo_responsable, et_nombre_nuevo_responsable, et_apellido_nuevo_responsable,
            et_codigo_area_nuevo_responsable, et_telefono_nuevo_responsable, et_direccion_nuevo_responsable,
            et_barrio_nuevo_responsable, et_correo_nuevo_responsable, et_referencia_nuevo_responsable;


    EditText et_nombre_del_evento_nuevo_responsable, et_direccion_del_evento_nuevo_responsable,et_barrio_del_evento_nuevo_responsable,
             et_referencia_del_evento_nuevo_responsable,et_fecha_inicio_del_evento_nuevo_responsable, et_fecha_fin_del_evento_nuevo_responsable;



    Button btn_confirmar_nuevo_responsable_del_evento_supervisor, btn_cancelar_nuevo_responsable_del_evento_supervisor;


    TextView tv_datos_responsable, tv_datos_evento;


    LinearLayout LinearLayout_Horizontal_DNI_Responsable, LinearLayout_Horizontal_Nombre_Responsable,
                 LinearLayout_Horizontal_Apellido_Responsable, LinearLayout_Horizontal_Codigo_Area_Responsable,
                 LinearLayout_Horizontal_Telefono_Responsable, LinearLayout_Horizontal_Direccion_Responsable,
                 LinearLayout_Horizontal_Barrio_Responsable, LinearLayout_Horizontal_Correo_Responsable,
                 LinearLayout_Horizontal_Referencia_Responsable,

                 LinearLayout_Horizontal_Nombre_Evento_Responsable, LinearLayout_Horizontal_Direccion_Evento_Responsable,
                 LinearLayout_Horizontal_Barrio_Evento_Responsable, LinearLayout_Horizontal_Referencia_Evento_Responsable,
                 LinearLayout_Horizontal_Fecha_Inicio_Evento_Responsable, LinearLayout_Horizontal_Fecha_Fin_Evento_Responsable;


    ImageView img_ic_dni_nrp_repartidor, img_ic_nombre_nrp_repartidor, img_ic_apellido_nrp_repartidor,
              img_ic_codigo_area_nrp_repartidor,img_ic_telefono_nrp_repartidor, img_ic_direccion_nrp_repartidor, img_ic_barrio_nrp_repartidor,
              img_ic_correo_nrp_repartidor, img_ic_referencia_nrp_repartidor,

              img_ic_nombre_evento_nrp_repartidor, img_ic_direccion_evento_nrp_repartidor, img_ic_barrio_evento_nrp_repartidor,
              img_ic_referencia_evento_nrp_repartidor, img_ic_fecha_inicio_evento_nrp_repartidor,img_ic_fecha_fin_evento_nrp_repartidor;


    View view_separador_dni_repartidor, view_separador_nombre_repartidor, view_separador_apellido_repartidor,
         view_separador_codigo_area_repartidor,view_separador_telefono_repartidor, view_separador_direccion_repartidor,
         view_separador_barrio_repartidor, view_separador_correo_repartidor, view_separador_referencia_repartidor,

         view_separador_nombre_evento_repartidor, view_separador_direccion_evento_repartidor, view_separador_barrio_evento_repartidor,
         view_separador_referencia_evento_repartidor, view_separador_fecha_inicio_evento_repartidor, view_separador_fecha_fin_evento_repartidor;








    /**************************** COMIENZO DEL onCreate() ********************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_responsable_patrocinio);





        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));


        /* Para cambiar el color del puntero o "burbuja" del EditText */
        setTheme(R.style.AppTheme_Cursor);





        /*Instanciamos los campos de los DATOS PERSONALES del nuevo responsable */

        et_dni_nuevo_responsable = (EditText) findViewById(R.id.et_dni_nuevo_responsable_formulario_patrocinio_supervisor);
        et_dni_nuevo_responsable.requestFocus();
        et_dni_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_dni_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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





        et_nombre_nuevo_responsable = (EditText) findViewById(R.id.et_nombre_nuevo_responsable_formulario_patrocinio_supervisor);

        et_nombre_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_nombre_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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







        et_apellido_nuevo_responsable =  (EditText) findViewById(R.id.et_apellido_nuevo_responsable_formulario_patrocinio_supervisor);

        et_apellido_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_apellido_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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










        et_codigo_area_nuevo_responsable = (EditText) findViewById(R.id.et_codigo_area_nuevo_responsable_formulario_patrocinio_supervisor);

        et_codigo_area_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        et_telefono_nuevo_responsable = (EditText) findViewById(R.id.et_telefono_nuevo_responsable_formulario_patrocinio_supervisor);

        et_telefono_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        /**Deshabilitar el ET al comienzo solo si el código de área tiene dígitos válidos **/
        et_telefono_nuevo_responsable.setFocusable(false);
        et_telefono_nuevo_responsable.setCursorVisible(false);
        et_telefono_nuevo_responsable.setHint("");
        et_telefono_nuevo_responsable.setText("");
        et_telefono_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);







        /**Sólo cuando se pierda el foco del ET_CODIGO_AREA, comenzará a comparar si
         * cada código de area del archivo de texto es correcto **/

        /*

        et_codigo_area_nuevo_responsable.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(!hasFocus){

                    if(Utils_Codigo_Area_Telefonico_Argentina.Prueba(NuevoResponsablePatrocinio.this,et_codigo_area_nuevo_responsable.getText().toString())){


                        Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                        myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                        et_codigo_area_nuevo_responsable.setError("Código de área válido", myIconCheck);


                        et_telefono_nuevo_responsable.setFocusableInTouchMode(true);
                        et_telefono_nuevo_responsable.setCursorVisible(true);
                        et_telefono_nuevo_responsable.setHint("Obligatorio");
                        et_telefono_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
                        et_telefono_nuevo_responsable.setHint(R.string.edittext_hint);
                        //et_telefono_nuevo_responsable.setError("¡Escriba el número de teléfono!");



                    } else {


                        et_codigo_area_nuevo_responsable.setError("Código de área no válido");


                        et_telefono_nuevo_responsable.setFocusable(false);
                        et_telefono_nuevo_responsable.setCursorVisible(false);
                        et_telefono_nuevo_responsable.setHint("");
                        et_telefono_nuevo_responsable.setText("");
                        et_telefono_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);


                        //et_telefono_nuevo_responsable.setError("¡Escriba un código de área válido!");


                    }

                }
            }
        });


*/




        et_codigo_area_nuevo_responsable.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {



                ValidarCodigoArea();

                /*

                if(Utils_Codigo_Area_Telefonico_Argentina.Prueba(NuevoResponsablePatrocinio.this,et_codigo_area_nuevo_responsable.getText().toString())){


                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                    et_codigo_area_nuevo_responsable.setError("Código de área válido", myIconCheck);


                    et_telefono_nuevo_responsable.setFocusableInTouchMode(true);
                    et_telefono_nuevo_responsable.setCursorVisible(true);
                    et_telefono_nuevo_responsable.setHint("Obligatorio");
                    et_telefono_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
                    et_telefono_nuevo_responsable.setHint(R.string.edittext_hint);



                } else {


                    et_codigo_area_nuevo_responsable.setError("Código de área no válido");


                    et_telefono_nuevo_responsable.setFocusable(false);
                    et_telefono_nuevo_responsable.setCursorVisible(false);
                    et_telefono_nuevo_responsable.setHint("");
                    et_telefono_nuevo_responsable.setText("");
                    et_telefono_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);




                }

*/



            }
        });










        et_telefono_nuevo_responsable.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {


                    ValidarTelefono();


                    /*


                    if (et_codigo_area_nuevo_responsable.getText().toString().length() > 0
                        && et_codigo_area_nuevo_responsable.getText().toString().length() < 3
                        && et_telefono_nuevo_responsable.getText().toString().length() == 8){

                        setEditTextMaxLength(et_telefono_nuevo_responsable,8);


                        Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                        myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                        et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


                    }



                    else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 2
                            && et_codigo_area_nuevo_responsable.getText().toString().length() < 4
                            && et_telefono_nuevo_responsable.getText().toString().length() == 7){

                        setEditTextMaxLength(et_telefono_nuevo_responsable,8);


                        Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                        myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                        et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


                    }



                    else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 3
                            && et_codigo_area_nuevo_responsable.getText().toString().length() < 5
                            && et_telefono_nuevo_responsable.getText().toString().length() == 6){

                        setEditTextMaxLength(et_telefono_nuevo_responsable,8);


                        Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                        myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                        et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


                    }





                    else{

                        et_telefono_nuevo_responsable.setError("¡Número de teléfono no válido!");

                    }

                */

                }
            });










        et_telefono_nuevo_responsable.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(!hasFocus){


                    et_codigo_area_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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


                            if (et_codigo_area_nuevo_responsable.getText().toString().length() > 2
                                    && et_codigo_area_nuevo_responsable.getText().toString().length() < 4
                                    && et_telefono_nuevo_responsable.getText().toString().length() == 8){



                                et_telefono_nuevo_responsable.setText(BorrarUltimoCaracter(et_telefono_nuevo_responsable.getText().toString()));

                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);



                            }



                            else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 3
                                    && et_codigo_area_nuevo_responsable.getText().toString().length() < 5
                                    && et_telefono_nuevo_responsable.getText().toString().length() == 7){



                                et_telefono_nuevo_responsable.setText(BorrarUltimoCaracter(et_telefono_nuevo_responsable.getText().toString()));

                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


                            }





                            /******** CASO N°2: Se establece un relación incorrecta entre la cantidad
                             * de dígitos del código de área con la cantidad de dígitos del número
                             * de télefono, arrojando un msj de error.********/


                            else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 2
                                    && et_codigo_area_nuevo_responsable.getText().toString().length() < 4
                                    && et_telefono_nuevo_responsable.getText().toString().length() == 6){


                                et_telefono_nuevo_responsable.setError("¡Número de teléfono no válido!");

                            }



                            else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 0
                                    && et_codigo_area_nuevo_responsable.getText().toString().length() < 3
                                    && et_telefono_nuevo_responsable.getText().toString().length() == 7){

                                et_telefono_nuevo_responsable.setError("¡Número de teléfono no válido!");

                            }






                            /******** CASO N°3: Relación adecuada o correcta entre la cantidad de
                             * dígitos del código de área con la cantidad de dígitos del número de
                             * teléfono.********/


                            else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 2
                                    && et_codigo_area_nuevo_responsable.getText().toString().length() < 4
                                    && et_telefono_nuevo_responsable.getText().toString().length() == 7){




                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


                            }



                            else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 3
                                    && et_codigo_area_nuevo_responsable.getText().toString().length() < 5
                                    && et_telefono_nuevo_responsable.getText().toString().length() == 6){




                                Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


                            }


                        }
                    });




                }
            }
        });










        et_direccion_nuevo_responsable = (EditText) findViewById(R.id.et_direccion_nuevo_responsable_formulario_patrocinio_supervisor);

        et_direccion_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_direccion_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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








        et_barrio_nuevo_responsable = (EditText) findViewById(R.id.et_barrio_nuevo_responsable_formulario_patrocinio_supervisor);

        et_barrio_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_barrio_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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









        et_correo_nuevo_responsable = (EditText) findViewById(R.id.et_email_nuevo_responsable_formulario_patrocinio_supervisor);

        et_correo_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_correo_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {



                ValidarEmail(et_correo_nuevo_responsable);

                /*

                if (s.toString().length() > 0
                        && ObtenerEmailValido(et_correo_nuevo_responsable.getText().toString())){



                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                    et_correo_nuevo_responsable.setError("¡Correo válido!", myIconCheck);


                }


                else {


                    et_correo_nuevo_responsable.setError("¡Correo no válido!");


                }



                 */



            }
        });







        et_referencia_nuevo_responsable = (EditText) findViewById(R.id.et_referencia_nuevo_responsable_formulario_patrocinio_supervisor);

        et_referencia_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_referencia_nuevo_responsable.addTextChangedListener(new TextWatcher() {
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









        /*Instanciamos los campos de los DATOS DEL EVENTO del nuevo responsable */

        et_nombre_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_nombre_evento_formulario_patrocinio_supervisor);

        et_nombre_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_nombre_del_evento_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                /*Llamada a la función: */
                ValidarNombreEvento();

            }
        });







        et_direccion_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_direccion_evento_formulario_patrocinio_supervisor);

        et_direccion_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_direccion_del_evento_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                /*Llamada a la función: */
                ValidarDireccionEvento();

            }
        });







        et_barrio_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_barrio_evento_formulario_patrocinio_supervisor);

        et_barrio_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_barrio_del_evento_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                /*Llamada a la función: */
                ValidarBarrioEvento();

            }
        });








        et_referencia_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_referencia_evento_formulario_patrocinio_supervisor);

        et_referencia_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        et_referencia_del_evento_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                /*Llamada a la función: */
                ValidarReferenciaEvento();

            }
        });







        et_fecha_inicio_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_fecha_inicio_evento_formulario_patrocinio_supervisor);

        et_fecha_inicio_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");

        MaskTextWatcher mtw_fecha_inicio_evento = new MaskTextWatcher(et_fecha_inicio_del_evento_nuevo_responsable, smf);

        et_fecha_inicio_del_evento_nuevo_responsable.addTextChangedListener(mtw_fecha_inicio_evento);

        et_fecha_inicio_del_evento_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                ValidarFechaInicioEvento();

            }
        });












        et_fecha_fin_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_fecha_fin_evento_formulario_patrocinio);

        et_fecha_fin_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        MaskTextWatcher mtw_fecha_fin_estimada_evento = new MaskTextWatcher(et_fecha_fin_del_evento_nuevo_responsable, smf);

        et_fecha_fin_del_evento_nuevo_responsable.addTextChangedListener(mtw_fecha_fin_estimada_evento);

        et_fecha_fin_del_evento_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                ValidarFechaFinEvento();

            }
        });






        btn_cancelar_nuevo_responsable_del_evento_supervisor = (Button) findViewById(R.id.btn_cancelar_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);

        btn_cancelar_nuevo_responsable_del_evento_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!et_dni_nuevo_responsable.getText().toString().isEmpty() || !et_nombre_nuevo_responsable.getText().toString().isEmpty()
                        || !et_apellido_nuevo_responsable.getText().toString().isEmpty() ||!et_codigo_area_nuevo_responsable.getText().toString().isEmpty()
                        || !et_telefono_nuevo_responsable.getText().toString().isEmpty() || !et_direccion_nuevo_responsable.getText().toString().isEmpty()
                        || !et_barrio_nuevo_responsable.getText().toString().isEmpty() || !et_correo_nuevo_responsable.getText().toString().isEmpty()
                        || !et_referencia_nuevo_responsable.getText().toString().isEmpty() ||!et_nombre_del_evento_nuevo_responsable.getText().toString().isEmpty()
                        || !et_direccion_del_evento_nuevo_responsable.getText().toString().isEmpty() || !et_barrio_del_evento_nuevo_responsable.getText().toString().isEmpty()
                        || !et_fecha_inicio_del_evento_nuevo_responsable.getText().toString().isEmpty() || !et_fecha_fin_del_evento_nuevo_responsable.getText().toString().isEmpty()){




                    AlertDialog.Builder builder = new AlertDialog.Builder(NuevoResponsablePatrocinio.this);
                    builder.setIcon(R.drawable.ic_msj_alerta);
                    builder.setTitle("¿Desea salir?");
                    builder.setMessage("Al parecer algunos campos del formulario no están vacios. ¿Desea cancelar la adición de un nuevo responsable del evento?");


                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                            finish();


                        }
                    });






                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialog, int id) {


                            dialog.dismiss();


                        }
                    });



                    AlertDialog dialog = builder.create();
                    dialog.show();







                }//Fin del if


                else {


                    finish();


                }//Fin del else


            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/











        btn_confirmar_nuevo_responsable_del_evento_supervisor = (Button) findViewById(R.id.btn_confirmar_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);

        btn_confirmar_nuevo_responsable_del_evento_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable()) {


                    int Indice_Responsable = GuardarDatosResponsableEnSharedPreferences();

                    GuardarDatosEventoEnSharedPreferences(Indice_Responsable);

                    Intent intent = new Intent (NuevoResponsablePatrocinio.this, BuscarResponsableParaPatrocinio.class);

                    startActivity(intent);

                    finish();

                }




            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/


        tv_datos_responsable = (TextView) findViewById(R.id.datos_personales_responsable);

        tv_datos_evento = (TextView) findViewById(R.id.tv_evento_formulario_patrocinio);



        LinearLayout_Horizontal_DNI_Responsable = (LinearLayout) findViewById(R.id.llh_dni_formulario_patrocinio);
        LinearLayout_Horizontal_Nombre_Responsable = (LinearLayout) findViewById(R.id.llh_nombre_formulario_patrocinio);
        LinearLayout_Horizontal_Apellido_Responsable = (LinearLayout) findViewById(R.id.llh_apellido_formulario_patrocinio);
        LinearLayout_Horizontal_Codigo_Area_Responsable = (LinearLayout) findViewById(R.id.llh_codigo_area_formulario_patrocinio);
        LinearLayout_Horizontal_Telefono_Responsable = (LinearLayout) findViewById(R.id.llh_telefono_formulario_patrocinio);
        LinearLayout_Horizontal_Direccion_Responsable = (LinearLayout) findViewById(R.id.llh_direccion_formulario_patrocinio);
        LinearLayout_Horizontal_Barrio_Responsable = (LinearLayout) findViewById(R.id.llh_barrio_formulario_patrocinio);
        LinearLayout_Horizontal_Correo_Responsable = (LinearLayout) findViewById(R.id.llh_email_formulario_patrocinio);
        LinearLayout_Horizontal_Referencia_Responsable = (LinearLayout) findViewById(R.id.llh_referencia_formulario_patrocinio);


        LinearLayout_Horizontal_Nombre_Evento_Responsable = (LinearLayout) findViewById(R.id.llh_nombre_evento_formulario_patrocinio);
        LinearLayout_Horizontal_Direccion_Evento_Responsable = (LinearLayout) findViewById(R.id.llh_direccion_evento_formulario_patrocinio);
        LinearLayout_Horizontal_Barrio_Evento_Responsable = (LinearLayout) findViewById(R.id.llh_barrio_evento_formulario_patrocinio);
        LinearLayout_Horizontal_Referencia_Evento_Responsable = (LinearLayout) findViewById(R.id.llh_referencia_evento_formulario_patrocinio);
        LinearLayout_Horizontal_Fecha_Inicio_Evento_Responsable = (LinearLayout) findViewById(R.id.llh_fecha_inicio_formulario_patrocinio);
        LinearLayout_Horizontal_Fecha_Fin_Evento_Responsable = (LinearLayout) findViewById(R.id.llh_fecha_fin_formulario_patrocinio);








        img_ic_dni_nrp_repartidor = (ImageView) findViewById(R.id.img_dni_nrp);
        img_ic_nombre_nrp_repartidor = (ImageView) findViewById(R.id.img_nombre_nrp);
        img_ic_apellido_nrp_repartidor = (ImageView) findViewById(R.id.img_apellido_nrp);
        img_ic_codigo_area_nrp_repartidor = (ImageView) findViewById(R.id.img_codigo_area_nrp);
        img_ic_telefono_nrp_repartidor = (ImageView) findViewById(R.id.img_telefono_nrp);
        img_ic_direccion_nrp_repartidor = (ImageView) findViewById(R.id.img_direccion_nrp);
        img_ic_barrio_nrp_repartidor = (ImageView) findViewById(R.id.img_barrio_nrp);
        img_ic_correo_nrp_repartidor = (ImageView) findViewById(R.id.img_correo_nrp);
        img_ic_referencia_nrp_repartidor = (ImageView) findViewById(R.id.img_referencia_nrp);


        img_ic_nombre_evento_nrp_repartidor = (ImageView) findViewById(R.id.img_nombre_evento_nrp);
        img_ic_direccion_evento_nrp_repartidor = (ImageView) findViewById(R.id.img_direccion_evento_nrp);
        img_ic_barrio_evento_nrp_repartidor = (ImageView) findViewById(R.id.img_barrio_evento_nrp);
        img_ic_referencia_evento_nrp_repartidor = (ImageView) findViewById(R.id.img_referencia_evento_nrp);
        img_ic_fecha_inicio_evento_nrp_repartidor = (ImageView) findViewById(R.id.img_fecha_inicio_evento_nrp);
        img_ic_fecha_fin_evento_nrp_repartidor = (ImageView) findViewById(R.id.img_fecha_fin_evento_nrp);









        view_separador_dni_repartidor = (View) findViewById(R.id.separador_dni);
        view_separador_nombre_repartidor = (View) findViewById(R.id.separador_nombre);
        view_separador_apellido_repartidor = (View) findViewById(R.id.separador_apellido);
        view_separador_codigo_area_repartidor = (View) findViewById(R.id.separador_codigo_area);
        view_separador_telefono_repartidor = (View) findViewById(R.id.separador_telefono);
        view_separador_direccion_repartidor = (View) findViewById(R.id.separador_direccion);
        view_separador_barrio_repartidor = (View) findViewById(R.id.separador_barrio);
        view_separador_correo_repartidor = (View) findViewById(R.id.separador_correo);
        view_separador_referencia_repartidor = (View) findViewById(R.id.separador_referencia);


        view_separador_nombre_evento_repartidor = (View) findViewById(R.id.separador_nombre_evento_nrp);
        view_separador_direccion_evento_repartidor = (View) findViewById(R.id.separador_direccion_evento_nrp);
        view_separador_barrio_evento_repartidor = (View) findViewById(R.id.separador_barrio_evento_nrp);
        view_separador_referencia_evento_repartidor = (View) findViewById(R.id.separador_referencia_evento_nrp);
        view_separador_fecha_inicio_evento_repartidor = (View) findViewById(R.id.separador_fecha_inicio_evento_nrp);
        view_separador_fecha_fin_evento_repartidor = (View) findViewById(R.id.separador_fecha_fin_evento_nrp);













        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));

            tv_datos_responsable.setBackgroundColor(Color.parseColor("#283593"));
            tv_datos_evento.setBackgroundColor(Color.parseColor("#283593"));



            LinearLayout_Horizontal_DNI_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Nombre_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Apellido_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Codigo_Area_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Telefono_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Direccion_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Barrio_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Correo_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Referencia_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));


            LinearLayout_Horizontal_Nombre_Evento_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Direccion_Evento_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Barrio_Evento_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Referencia_Evento_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Fecha_Inicio_Evento_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));
            LinearLayout_Horizontal_Fecha_Fin_Evento_Responsable.setBackground(getDrawable(R.drawable.contenedor_llh_nuevo_responsable_repartidor));





            img_ic_dni_nrp_repartidor.setImageResource(R.drawable.ic_dni_nrp);
            img_ic_nombre_nrp_repartidor.setImageResource(R.drawable.ic_nombre_apellido_nrp);
            img_ic_apellido_nrp_repartidor.setImageResource(R.drawable.ic_nombre_apellido_nrp);
            img_ic_codigo_area_nrp_repartidor.setImageResource(R.drawable.ic_cod_area_nrp_repartidor);
            img_ic_telefono_nrp_repartidor.setImageResource(R.drawable.ic_telefono_nrp);
            img_ic_direccion_nrp_repartidor.setImageResource(R.drawable.ic_direccion_nrp);
            img_ic_barrio_nrp_repartidor.setImageResource(R.drawable.ic_barrio_nrp);
            img_ic_correo_nrp_repartidor.setImageResource(R.drawable.ic_correo_nrp);
            img_ic_referencia_nrp_repartidor.setImageResource(R.drawable.ic_referencia_nrp);


            img_ic_nombre_evento_nrp_repartidor.setImageResource(R.drawable.ic_nombre_evento_nrp);
            img_ic_direccion_evento_nrp_repartidor.setImageResource(R.drawable.ic_direccion_nrp);
            img_ic_barrio_evento_nrp_repartidor.setImageResource(R.drawable.ic_barrio_nrp);
            img_ic_referencia_evento_nrp_repartidor.setImageResource(R.drawable.ic_referencia_nrp);
            img_ic_fecha_inicio_evento_nrp_repartidor.setImageResource(R.drawable.ic_fecha_inicio_nrp);
            img_ic_fecha_fin_evento_nrp_repartidor.setImageResource(R.drawable.ic_fecha_fin_nrp);





            view_separador_dni_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_nombre_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_apellido_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_codigo_area_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_telefono_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_direccion_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_barrio_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_correo_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_referencia_repartidor.setBackgroundColor(Color.parseColor("#283593"));


            view_separador_nombre_evento_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_direccion_evento_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_barrio_evento_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_referencia_evento_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_fecha_inicio_evento_repartidor.setBackgroundColor(Color.parseColor("#283593"));
            view_separador_fecha_fin_evento_repartidor.setBackgroundColor(Color.parseColor("#283593"));


            btn_confirmar_nuevo_responsable_del_evento_supervisor.setBackground(getDrawable(R.drawable.btn_confirmar_nuevo_responsable_esquinas_con_bordes_repartidor));



        }//Fin del if










    }/******************************************FIN DEL onCreate()******************************************************/







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







    boolean flag_nuevo_responsable = false;

    public boolean ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable(){


        //Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
        //myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());

        //et_nombre_nuevo_responsable.setError("Nombre válido", myIconCheck);



        //Estructura repetitiva para duplicar el tiempo de duración del Toast
            for (int i = 0; i < 2; i++) {


                    /**Primer Validación: todos los campos obligatorios deben estar rellenados**/

                    if (!et_dni_nuevo_responsable.getText().toString().isEmpty() && ValidarDocumento()
                            && !et_nombre_nuevo_responsable.getText().toString().isEmpty() && ValidarNombre()
                            && !et_apellido_nuevo_responsable.getText().toString().isEmpty() && ValidarApellido()
                            && !et_codigo_area_nuevo_responsable.getText().toString().isEmpty() && ValidarCodigoArea()
                            && !et_telefono_nuevo_responsable.getText().toString().isEmpty() && ValidarTelefono()
                            && !et_direccion_nuevo_responsable.getText().toString().isEmpty() && ValidarDireccion()
                            && !et_barrio_nuevo_responsable.getText().toString().isEmpty() && ValidarBarrio()
                            && et_correo_nuevo_responsable.getText().toString().isEmpty() && !ValidarEmail(et_correo_nuevo_responsable)
                            || ValidarEmail(et_correo_nuevo_responsable)
                            && !et_referencia_nuevo_responsable.getText().toString().isEmpty() && ValidarReferencia()
                            && !et_nombre_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarNombreEvento()
                            && !et_direccion_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarDireccionEvento()
                            && !et_barrio_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarBarrioEvento()
                            && !et_referencia_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarReferenciaEvento()
                            && !et_fecha_inicio_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarFechaInicioEvento()
                            && !et_fecha_fin_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarFechaFinEvento()){


                        flag_nuevo_responsable = true;

                    }



                    else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_nuevo_responsable = false;

                    }






                if (flag_nuevo_responsable) {


                    Toast.makeText(getApplicationContext(), "¡Un nuevo responsable se ha añadido con éxito!", Toast.LENGTH_LONG).show();


                } //Fin del if (flag_nuevo_responsable){}


            } /*Fin del 'for'*/

            return flag_nuevo_responsable;


    }/************************************FIN DE LA FUNCIÓN ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable()****************************************/




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




    boolean flag_dni = false;

    public boolean ValidarDocumento() {



        if(et_dni_nuevo_responsable.getText().toString().length() > 0 && et_dni_nuevo_responsable.getText().toString().length() == 8){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_dni_nuevo_responsable.setError("DNI válido", myIconCheck);

            flag_dni = true;


        } else {


            et_dni_nuevo_responsable.setError("DNI no válido");

            flag_dni = false;

        }


        return flag_dni;
 /*


        int posicion_dos = et_dni_nuevo_responsable.getText().toString().indexOf(".");

        int posicion_seis = et_dni_nuevo_responsable.getText().toString().lastIndexOf(".");




        if(et_dni_nuevo_responsable.getText().toString().length() > 0 && et_dni_nuevo_responsable.getText().toString().length() == 10 && posicion_dos == 2 && posicion_seis == 6 ){


            Toast.makeText(getApplicationContext(), "¡DNI válido!", Toast.LENGTH_LONG).show();



        } else {

            et_dni_nuevo_responsable.setError("DNI no válido");

        }



*/


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



    boolean flag_nombre_responsable = false;

    public boolean ValidarNombre() {


        if(et_nombre_nuevo_responsable.getText().toString().length() > 2){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_nuevo_responsable.setError("Nombre válido", myIconCheck);


            flag_nombre_responsable = true;

        } else {

            et_nombre_nuevo_responsable.setError("Nombre no válido");

            flag_nombre_responsable = false;

        }


        return flag_nombre_responsable;


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



    boolean flag_apellido_responsable = false;

    public boolean ValidarApellido() {


        if(et_apellido_nuevo_responsable.getText().toString().length() > 3){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_apellido_nuevo_responsable.setError("Apellido válido", myIconCheck);


            flag_apellido_responsable = true;

        } else {

            et_apellido_nuevo_responsable.setError("Apellido no válido");

            flag_apellido_responsable = false;

        }



        return flag_apellido_responsable;

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






    boolean flag_codigo_area = false;

    public boolean ValidarCodigoArea() {


        if(Utils_Codigo_Area_Telefonico_Argentina.Prueba(NuevoResponsablePatrocinio.this,et_codigo_area_nuevo_responsable.getText().toString())){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_codigo_area_nuevo_responsable.setError("Código de área válido", myIconCheck);


            et_telefono_nuevo_responsable.setFocusableInTouchMode(true);
            et_telefono_nuevo_responsable.setCursorVisible(true);
            et_telefono_nuevo_responsable.setHint("Obligatorio");
            et_telefono_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
            et_telefono_nuevo_responsable.setHint(R.string.edittext_hint);

            flag_codigo_area = true;


        } else {


            et_codigo_area_nuevo_responsable.setError("Código de área no válido");


            et_telefono_nuevo_responsable.setFocusable(false);
            et_telefono_nuevo_responsable.setCursorVisible(false);
            et_telefono_nuevo_responsable.setHint("");
            et_telefono_nuevo_responsable.setText("");
            et_telefono_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);

            flag_codigo_area = false;


        }

        return flag_codigo_area;


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




    boolean flag_telefono = false;

    public boolean ValidarTelefono(){



        if (et_codigo_area_nuevo_responsable.getText().toString().length() > 0
                && et_codigo_area_nuevo_responsable.getText().toString().length() < 3
                && et_telefono_nuevo_responsable.getText().toString().length() == 8){

            setEditTextMaxLength(et_telefono_nuevo_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono = true;


        }



        else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 2
                && et_codigo_area_nuevo_responsable.getText().toString().length() < 4
                && et_telefono_nuevo_responsable.getText().toString().length() == 7){

            setEditTextMaxLength(et_telefono_nuevo_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono = true;


        }



        else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 3
                && et_codigo_area_nuevo_responsable.getText().toString().length() < 5
                && et_telefono_nuevo_responsable.getText().toString().length() == 6){

            setEditTextMaxLength(et_telefono_nuevo_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono = true;

        }





        else{

            et_telefono_nuevo_responsable.setError("¡Número de teléfono no válido!");

            flag_telefono = false;

        }


        return flag_telefono;

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



    boolean flag_direccion_responsable = false;

    public boolean ValidarDireccion() {


        if(et_direccion_nuevo_responsable.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_nuevo_responsable.setError("Dirección válida", myIconCheck);


            flag_direccion_responsable = true;

        } else {

            et_direccion_nuevo_responsable.setError("Dirección no válida");

            flag_direccion_responsable = false;

        }


        return flag_direccion_responsable;


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




    boolean flag_barrio_responsable = false;

    public boolean ValidarBarrio() {


        if(et_barrio_nuevo_responsable.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_nuevo_responsable.setError("Barrio válido", myIconCheck);

            flag_barrio_responsable = true;

        } else {

            et_barrio_nuevo_responsable.setError("Barrio no válido");

            flag_barrio_responsable = false;

        }


        return flag_barrio_responsable;

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



    boolean flag_correo_responsable = false;

    public boolean ValidarEmail(EditText et_correo_nuevo_responsable){


        //et_correo_nuevo_responsable.toString().length() > 0

         if (ObtenerEmailValido(et_correo_nuevo_responsable.getText().toString())){



             Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
             myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
             et_correo_nuevo_responsable.setError("¡Correo válido!", myIconCheck);

             flag_correo_responsable = true;

         }



         else {


             et_correo_nuevo_responsable.setError("¡Correo no válido!");

             flag_correo_responsable = false;

         }

         return flag_correo_responsable;

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


    boolean flag_referencia_responsable = false;

    public boolean ValidarReferencia() {


        if(et_referencia_nuevo_responsable.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_nuevo_responsable.setError("Referencia válida", myIconCheck);

            flag_referencia_responsable = true;


        } else {

            et_referencia_nuevo_responsable.setError("Referencia no válida");

            flag_referencia_responsable = false;

        }

        return flag_referencia_responsable;


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



    boolean flag_nombre_evento = false;

    public boolean ValidarNombreEvento() {


        if(et_nombre_del_evento_nuevo_responsable.getText().toString().length() > 14){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_del_evento_nuevo_responsable.setError("Nombre de evento válido", myIconCheck);

            flag_nombre_evento = true;

        } else {

            et_nombre_del_evento_nuevo_responsable.setError("Nombre de evento no válido");

            flag_nombre_evento = false;

        }


        return flag_nombre_evento;

    }   /**********************FIN DE LA FUNCIÓN ValidarNombreEvento()*********************/




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



    boolean flag_direccion_evento = false;

    public boolean ValidarDireccionEvento() {


        if(et_direccion_del_evento_nuevo_responsable.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_del_evento_nuevo_responsable.setError("Dirección de evento válida", myIconCheck);

            flag_direccion_evento = true;

        } else {

            et_direccion_del_evento_nuevo_responsable.setError("Dirección de evento no válida");

            flag_direccion_evento = false;


        }


        return flag_direccion_evento;


    }   /**********************FIN DE LA FUNCIÓN ValidarDireccionEvento()*********************/





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



    boolean flag_barrio_evento = false;

    public boolean ValidarBarrioEvento() {



        if(et_barrio_del_evento_nuevo_responsable.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_del_evento_nuevo_responsable.setError("Barrio de evento válido", myIconCheck);

            flag_barrio_evento = true;

        } else {

            et_barrio_del_evento_nuevo_responsable.setError("Barrio de evento no válido");

            flag_barrio_evento = false;
        }


        return flag_barrio_evento;

    }   /**********************FIN DE LA FUNCIÓN ValidarBarrioEvento()*********************/




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


    boolean flag_referencia_evento = false;

    public boolean ValidarReferenciaEvento() {


        if(et_referencia_del_evento_nuevo_responsable.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_del_evento_nuevo_responsable.setError("Referencia de evento válida", myIconCheck);

            flag_referencia_evento = true;

        } else {

            et_referencia_del_evento_nuevo_responsable.setError("Referencia de evento no válida");

            flag_referencia_evento = false;
        }

        return flag_referencia_evento;


    }   /**********************FIN DE LA FUNCIÓN ValidarReferenciaEvento()*********************/



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



    boolean flag_fecha_inicio = false;

    public boolean ValidarFechaInicioEvento() {



        if(et_fecha_inicio_del_evento_nuevo_responsable.getText().toString().length() > 0
                && et_fecha_inicio_del_evento_nuevo_responsable.getText().toString().length() == 10){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_fecha_inicio_del_evento_nuevo_responsable.setError("Fecha de inicio válida", myIconCheck);

            flag_fecha_inicio = true;




        } else {


            et_fecha_inicio_del_evento_nuevo_responsable.setError("Fecha de inicio no válida");

            flag_fecha_inicio = false;

        }



        return flag_fecha_inicio;


    }   /**********************FIN DE LA FUNCIÓN ValidarFechaInicioEvento()*********************/






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







    boolean flag_fecha_fin = false;

    public boolean ValidarFechaFinEvento() {



        if(et_fecha_fin_del_evento_nuevo_responsable.getText().toString().length() > 0
                && et_fecha_fin_del_evento_nuevo_responsable.getText().toString().length() == 10){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_fecha_fin_del_evento_nuevo_responsable.setError("Fecha de fin válida", myIconCheck);

            flag_fecha_fin = true;




        } else {


            et_fecha_fin_del_evento_nuevo_responsable.setError("Fecha de fin no válida");

            flag_fecha_fin = false;

        }



        return flag_fecha_fin;


    }   /**********************FIN DE LA FUNCIÓN ValidarFechaFinEvento()*********************/




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




    public int GuardarDatosResponsableEnSharedPreferences(){

        int indice = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionEvento = sharedPreferences.getString("DimensionDeResponsable", "");

        if(DimensionEvento == ""){

            indice = 0;


        }//Fin del if


        else {

            indice = Integer.parseInt(DimensionEvento) + 1;

        }//Fin del else

        editor.putString("DNI_Responsable" + indice, et_dni_nuevo_responsable.getText().toString());

        editor.putString("Nombre_Responsable" + indice, et_nombre_nuevo_responsable.getText().toString());

        editor.putString("Apellido_Responsable" + indice, et_apellido_nuevo_responsable.getText().toString());

        editor.putString("Codigo_Area_Responsable" + indice, et_codigo_area_nuevo_responsable.getText().toString());

        editor.putString("Telefono_Responsable" + indice, et_telefono_nuevo_responsable.getText().toString());

        editor.putString("Direccion_Responsable" + indice, et_direccion_nuevo_responsable.getText().toString());

        editor.putString("Barrio_Responsable" + indice, et_barrio_nuevo_responsable.getText().toString());

        editor.putString("Correo_Responsable" + indice, et_correo_nuevo_responsable.getText().toString());

        editor.putString("Referencia_Responsable" + indice, et_referencia_nuevo_responsable.getText().toString());




        editor.putString("DimensionDeResponsable", String.valueOf(indice));

        editor.commit();

        return indice;

    }/******************** FIN DE LA FUNCIÓN GuardarDatosResponsableEnSharedPreferences() *******************/







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



    public void GuardarDatosEventoEnSharedPreferences(int indice_responsable){

        int indice = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionEvento = sharedPreferences.getString("DimensionDeEvento", "");

        if(DimensionEvento == ""){

            indice = 0;


        }//Fin del if


        else {

            indice = Integer.parseInt(DimensionEvento) + 1;

        }//Fin del else



        editor.putString("Nombre_Evento" + indice, et_nombre_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Direccion_Evento" + indice, et_direccion_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Barrio_Evento" + indice, et_barrio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Referencia_Evento" + indice, et_referencia_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Inicio_Evento" + indice, et_fecha_inicio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Fin_Evento" + indice, et_fecha_fin_del_evento_nuevo_responsable.getText().toString());

        editor.putInt("Indice_Responsable", indice_responsable);



        editor.putString("DimensionDeEvento", String.valueOf(indice));

        editor.commit();





    }/******************** FIN DE LA FUNCIÓN GuardarDatosEventoEnSharedPreferences() *******************/




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




    public void LeerDatosResponsableEnSharedPreferences(){


        SharedPreferences preferences = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);



        String DNI_Responsable = preferences.getString("DNI_Responsable", "");

        String Nombre_Responsable =  preferences.getString("Nombre_Responsable", "");

        String Apellido_Responsable =  preferences.getString("Apellido_Responsable", "");

        String Codigo_Area_Responsable = preferences.getString("Codigo_Area_Responsable", "");

        String Telefono_Responsable = preferences.getString("Telefono_Responsable", "");

        String Direccion_Responsable = preferences.getString("Direccion_Responsable", "");

        String Barrio_Responsable = preferences.getString("Barrio_Responsable", "");

        String Correo_Responsable = preferences.getString("Correo_Responsable", "");

        String Referencia_Responsable = preferences.getString("Referencia_Responsable", "");





    }/******************** FIN DE LA FUNCIÓN LeerDatosResponsableEnSharedPreferences() *******************/





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


    public void LeerDatosEventoEnSharedPreferences(){


        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);



        String Nombre_Evento = preferences.getString("Nombre_Evento", "");

        String Direccion_Evento = preferences.getString("Direccion_Evento", "");

        String Barrio_Evento = preferences.getString("Barrio_Evento", "");

        String Referencia_Evento = preferences.getString("Referencia_Evento", "");

        String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento", "");

        String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento", "");






    }/******************** FIN DE LA FUNCIÓN LeerDatosEventoEnSharedPreferences() *******************/






}/************************************************FIN DE LA Activity***************************************************************/
