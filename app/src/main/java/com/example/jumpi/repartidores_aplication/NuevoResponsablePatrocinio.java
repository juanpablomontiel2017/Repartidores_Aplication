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







        /*Instanciamos los campos de los DATOS PERSONALES del nuevo responsable */

        et_dni_nuevo_responsable = (EditText) findViewById(R.id.et_dni_nuevo_responsable_formulario_patrocinio_supervisor);
        et_dni_nuevo_responsable.requestFocus();

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

        et_telefono_nuevo_responsable = (EditText) findViewById(R.id.et_telefono_nuevo_responsable_formulario_patrocinio_supervisor);

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

        et_correo_nuevo_responsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {



                if (s.toString().length() > 0
                        && ValidarEmail(et_correo_nuevo_responsable.getText().toString())){



                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                    et_correo_nuevo_responsable.setError("¡Correo válido!", myIconCheck);


                }


                else {


                    et_correo_nuevo_responsable.setError("¡Correo no válido!");


                }




            }
        });







        et_referencia_nuevo_responsable = (EditText) findViewById(R.id.et_referencia_nuevo_responsable_formulario_patrocinio_supervisor);

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

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");

        MaskTextWatcher mtw_fecha_inicio_evento = new MaskTextWatcher(et_fecha_inicio_del_evento_nuevo_responsable, smf);

        et_fecha_inicio_del_evento_nuevo_responsable.addTextChangedListener(mtw_fecha_inicio_evento);








        et_fecha_fin_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_fecha_fin_evento_formulario_patrocinio);

        MaskTextWatcher mtw_fecha_fin_estimada_evento = new MaskTextWatcher(et_fecha_fin_del_evento_nuevo_responsable, smf);

        et_fecha_fin_del_evento_nuevo_responsable.addTextChangedListener(mtw_fecha_fin_estimada_evento);









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


                ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable();

                GuardarEventoEnSharedPreferences();



                Intent intent = new Intent (NuevoResponsablePatrocinio.this, BuscarResponsableParaPatrocinio.class);

                startActivity(intent);



                finish();




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
                            && !et_direccion_nuevo_responsable.getText().toString().isEmpty() && ValidarDireccion()
                            && !et_barrio_nuevo_responsable.getText().toString().isEmpty() && ValidarBarrio()
                            && !et_referencia_nuevo_responsable.getText().toString().isEmpty() && ValidarReferencia()
                            && !et_nombre_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarNombreEvento()
                            && !et_direccion_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarDireccionEvento()
                            && !et_barrio_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarBarrioEvento()
                            && !et_referencia_del_evento_nuevo_responsable.getText().toString().isEmpty() && ValidarReferenciaEvento()){

                        flag_nuevo_responsable = true;

                    }


                    else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios.", Toast.LENGTH_LONG).show();

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




    public boolean ValidarDocumento() {



        if(et_dni_nuevo_responsable.getText().toString().length() > 0 && et_dni_nuevo_responsable.getText().toString().length() == 8){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_dni_nuevo_responsable.setError("DNI válido", myIconCheck);


        } else {

            et_dni_nuevo_responsable.setError("DNI no válido");

        }


        return true;
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




    public boolean ValidarNombre() {


        if(et_nombre_nuevo_responsable.getText().toString().length() > 2){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_nuevo_responsable.setError("Nombre válido", myIconCheck);


        } else {

            et_nombre_nuevo_responsable.setError("Nombre no válido");

        }


        return  true;


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


    public boolean ValidarApellido() {


        if(et_apellido_nuevo_responsable.getText().toString().length() > 3){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_apellido_nuevo_responsable.setError("Apellido válido", myIconCheck);


        } else {

            et_apellido_nuevo_responsable.setError("Apellido no válido");

        }



        return true;

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





    public boolean ValidarDireccion() {


        if(et_direccion_nuevo_responsable.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_nuevo_responsable.setError("Dirección válida", myIconCheck);


        } else {

            et_direccion_nuevo_responsable.setError("Dirección no válida");

        }


        return true;


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






    public boolean ValidarBarrio() {


        if(et_barrio_nuevo_responsable.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_nuevo_responsable.setError("Barrio válido", myIconCheck);


        } else {

            et_barrio_nuevo_responsable.setError("Barrio no válido");

        }


        return true;

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



    private boolean ValidarEmail(String email) {


        if (email.endsWith(".com")
                || email.endsWith(".com.ar")
                || email.endsWith(".net") ) {


            Pattern pattern = Patterns.EMAIL_ADDRESS;

            return pattern.matcher(email).matches();


        } else {


            return false;

        }



     }  /**********************FIN DE LA FUNCIÓN ValidarEmail()*********************/






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




    public boolean ValidarReferencia() {


        if(et_referencia_nuevo_responsable.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_nuevo_responsable.setError("Referencia válida", myIconCheck);


        } else {

            et_referencia_nuevo_responsable.setError("Referencia no válida");

        }

        return true;


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





    public boolean ValidarNombreEvento() {


        if(et_nombre_del_evento_nuevo_responsable.getText().toString().length() > 14){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_del_evento_nuevo_responsable.setError("Nombre de evento válido", myIconCheck);


        } else {

            et_nombre_del_evento_nuevo_responsable.setError("Nombre de evento no válido");

        }


        return true;

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




    public boolean ValidarDireccionEvento() {


        if(et_direccion_del_evento_nuevo_responsable.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_del_evento_nuevo_responsable.setError("Dirección de evento válida", myIconCheck);


        } else {

            et_direccion_del_evento_nuevo_responsable.setError("Dirección de evento no válida");

        }


        return true;


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



    public boolean ValidarBarrioEvento() {


        if(et_barrio_del_evento_nuevo_responsable.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_del_evento_nuevo_responsable.setError("Barrio de evento válido", myIconCheck);


        } else {

            et_barrio_del_evento_nuevo_responsable.setError("Barrio de evento no válido");

        }


        return true;

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


    public boolean ValidarReferenciaEvento() {


        if(et_referencia_del_evento_nuevo_responsable.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_del_evento_nuevo_responsable.setError("Referencia de evento válida", myIconCheck);


        } else {

            et_referencia_del_evento_nuevo_responsable.setError("Referencia de evento no válida");

        }

        return true;


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


    public void GuardarEventoEnSharedPreferences(){

        int indice = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionEvento = sharedPreferences.getString("DimensionDeEvento", "");

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




        editor.putString("Nombre_Evento" + indice, et_nombre_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Direccion_Evento" + indice, et_direccion_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Barrio_Evento" + indice, et_barrio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Referencia_Evento" + indice, et_referencia_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Inicio_Evento" + indice, et_fecha_inicio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Fin_Evento" + indice, et_fecha_fin_del_evento_nuevo_responsable.getText().toString());


        editor.putString("DimensionDeEvento", String.valueOf(indice));

        editor.commit();



    }/******************** FIN DE LA FUNCIÓN GuardarEventoEnSharedPreferences() *******************/





    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    public void LeerEventoEnSharedPreferences(){


        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);



        String DNI_Responsable = preferences.getString("DNI_Responsable", "");

        String Nombre_Responsable =  preferences.getString("Nombre_Responsable", "");

        String Apellido_Responsable =  preferences.getString("Apellido_Responsable", "");

        String Codigo_Area_Responsable = preferences.getString("Codigo_Area_Responsable", "");

        String Telefono_Responsable = preferences.getString("Telefono_Responsable", "");

        String Direccion_Responsable = preferences.getString("Direccion_Responsable", "");

        String Barrio_Responsable = preferences.getString("Barrio_Responsable", "");

        String Correo_Responsable = preferences.getString("Correo_Responsable", "");

        String Referencia_Responsable = preferences.getString("Referencia_Responsable", "");




        String Nombre_Evento = preferences.getString("Nombre_Evento", "");

        String Direccion_Evento = preferences.getString("Direccion_Evento", "");

        String Barrio_Evento = preferences.getString("Barrio_Evento", "");

        String Referencia_Evento = preferences.getString("Referencia_Evento", "");

        String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento", "");

        String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento", "");






    }/******************** FIN DE LA FUNCIÓN LeerEventoEnSharedPreferences() *******************/








}/************************************************FIN DE LA Activity***************************************************************/
