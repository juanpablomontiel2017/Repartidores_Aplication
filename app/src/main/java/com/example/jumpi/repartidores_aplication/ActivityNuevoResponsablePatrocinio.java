package com.example.jumpi.repartidores_aplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class ActivityNuevoResponsablePatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/

    EditText et_dni_nuevo_responsable, et_nombre_nuevo_responsable, et_apellido_nuevo_responsable,
            et_codigo_area_nuevo_responsable, et_telefono_nuevo_responsable, et_direccion_nuevo_responsable,
            et_barrio_nuevo_responsable, et_correo_nuevo_responsable, et_referencia_nuevo_responsable;


    EditText et_nombre_del_evento_nuevo_responsable, et_direccion_del_evento_nuevo_responsable,et_barrio_del_evento_nuevo_responsable,
             et_referencia_del_evento_nuevo_responsable,et_fecha_inicio_del_evento_nuevo_responsable, et_fecha_fin_del_evento_nuevo_responsable;



    Button btn_confirmar_nuevo_responsable_del_evento_supervisor, btn_cancelar_nuevo_responsable_del_evento_supervisor;



    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();


    private int pYearFI;
    private int pYearFF;
    private int pMonthFI;
    private int pMonthFF;
    private int pDayFI;
    private int pDayFF;

    DatePickerDialog.OnDateSetListener selector_fecha_inicio,selector_fecha_fin;

    DatePickerDialog dialog_fecha_inicio, dialog_fecha_fin;





    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/
    /***************************************************************/




    /**************************** COMIENZO DEL onCreate() ********************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_responsable_patrocinio);



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
        et_telefono_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_colorprimary));



        /**Deshabilitar el ET al comienzo solo si el código de área tiene dígitos válidos **/
        et_telefono_nuevo_responsable.setFocusable(false);
        et_telefono_nuevo_responsable.setCursorVisible(false);
        et_telefono_nuevo_responsable.setHint("");
        et_telefono_nuevo_responsable.setText("");
        et_telefono_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);



        et_codigo_area_nuevo_responsable.addTextChangedListener(new TextWatcher() {

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

                /*Llamada a la función: */
                ValidarEmail(et_correo_nuevo_responsable);

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


        et_fecha_fin_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_fecha_fin_evento_formulario_patrocinio);
        /**Deshabilitar el ET al comienzo solo si la fecha de inicio tiene una fecha seleccionada **/
        et_fecha_fin_del_evento_nuevo_responsable.setEnabled(false);
        et_fecha_fin_del_evento_nuevo_responsable.setFocusable(false);
        et_fecha_fin_del_evento_nuevo_responsable.setCursorVisible(false);
        et_fecha_fin_del_evento_nuevo_responsable.setHint("");
        et_fecha_fin_del_evento_nuevo_responsable.setText("");
        et_fecha_fin_del_evento_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);





        selector_fecha_fin = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {



                pYearFF = year;
                pMonthFF = monthOfYear;
                pDayFF = dayOfMonth;


                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR,pYearFF);
                myCalendar2.set(Calendar.MONTH,pMonthFF);
                myCalendar2.set(Calendar.DAY_OF_MONTH,pDayFF);


                /*Llamada a la función */
                updateLabelFechaFin();



            } /**** Fin del método onDataSet ****/

        }; /*********** Fin del método setOnClickListener ***********/






        selector_fecha_inicio = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view,  int year, int month,
                                   int day) {



                    pYearFI = year;
                    pMonthFI = month;
                    pDayFI = day;



                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, pYearFI);
                    myCalendar.set(Calendar.MONTH,pMonthFI);
                    myCalendar.set(Calendar.DAY_OF_MONTH,pDayFI);


                    /*Llamada a la función */
                    updateLabelFechaInicio();






                    et_fecha_fin_del_evento_nuevo_responsable.setEnabled(true);
                    et_fecha_fin_del_evento_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_colorprimary));
                    et_fecha_fin_del_evento_nuevo_responsable.setHint("(Obligatorio)");

                    dialog_fecha_fin = new DatePickerDialog(ActivityNuevoResponsablePatrocinio.this,
                            R.style.MyDatePickerStyleNuevoResponsable,selector_fecha_fin,
                            pYearFI,pMonthFI,pDayFI);


                    dialog_fecha_fin.getDatePicker().setMinDate(ConvertirFechaEnMilisegundos(pDayFI+1,
                            pMonthFI,pYearFI));




                }/**** Fin del método onDataSet ****/

        }; /*********** Fin del método setOnClickListener ***********/








        dialog_fecha_inicio = new DatePickerDialog(this, R.style.MyDatePickerStyleNuevoResponsable, selector_fecha_inicio, pYearFI, pMonthFI, pDayFI);
        dialog_fecha_inicio.getDatePicker().setMinDate(new Date().getTime());



        et_fecha_inicio_del_evento_nuevo_responsable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                dialog_fecha_inicio.show();


            } /**** Fin del método onClick ****/


        }); /*********** Fin del método setOnClickListener ***********/





        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/
        /*****************************************************************************************************/







        et_fecha_fin_del_evento_nuevo_responsable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                dialog_fecha_fin.show();



            }

        });





        btn_cancelar_nuevo_responsable_del_evento_supervisor = (Button) findViewById(R.id.btn_cancelar_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);
        btn_cancelar_nuevo_responsable_del_evento_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();



            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/







        btn_confirmar_nuevo_responsable_del_evento_supervisor = (Button) findViewById(R.id.btn_confirmar_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);
        btn_confirmar_nuevo_responsable_del_evento_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable()) {


                    int Indice_Responsable = GuardarDatosResponsableEnSharedPreferences();

                    GuardarDatosEventoEnSharedPreferences(Indice_Responsable);

                    Intent intent = new Intent (ActivityNuevoResponsablePatrocinio.this, ActivityBuscarResponsablePatrocinio.class);

                    startActivity(intent);

                    finish();

                }




            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/






    }/******************************************FIN DEL onCreate() ******************************************************/







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


    private void updateLabelFechaInicio() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_inicio_guardada = sdf.format(myCalendar.getTime());

        et_fecha_inicio_del_evento_nuevo_responsable.setText(fecha_inicio_guardada);

    } /******************** FIN DE LA FUNCIÓN updateLabelFechaInicio() *******************/



    private void updateLabelFechaFin() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_fin_guardada = sdf.format(myCalendar2.getTime());

        et_fecha_fin_del_evento_nuevo_responsable.setText(fecha_fin_guardada);

    } /******************** FIN DE LA FUNCIÓN updateLabelFechaFin() *******************/






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





    public static long ConvertirFechaEnMilisegundos(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTimeInMillis();
    }





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



        //Estructura repetitiva para duplicar el tiempo de duración del Toast
            for (int i = 0; i < 2; i++) {

                if(et_correo_nuevo_responsable.getText().toString().isEmpty()){


                    if (!et_dni_nuevo_responsable.getText().toString().isEmpty() && ValidarDocumento()
                            && !et_nombre_nuevo_responsable.getText().toString().isEmpty() && ValidarNombre()
                            && !et_apellido_nuevo_responsable.getText().toString().isEmpty() && ValidarApellido()
                            && !et_codigo_area_nuevo_responsable.getText().toString().isEmpty() && ValidarCodigoArea()
                            && !et_telefono_nuevo_responsable.getText().toString().isEmpty() && ValidarTelefono()
                            && !et_direccion_nuevo_responsable.getText().toString().isEmpty() && ValidarDireccion()
                            && !et_barrio_nuevo_responsable.getText().toString().isEmpty() && ValidarBarrio()
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



                }/** FIN DEL if (Campo de CORREO vacío **/





                /**Campo de correo con valor **/
                else {

                    if (!et_dni_nuevo_responsable.getText().toString().isEmpty() && ValidarDocumento()
                            && !et_nombre_nuevo_responsable.getText().toString().isEmpty() && ValidarNombre()
                            && !et_apellido_nuevo_responsable.getText().toString().isEmpty() && ValidarApellido()
                            && !et_codigo_area_nuevo_responsable.getText().toString().isEmpty() && ValidarCodigoArea()
                            && !et_telefono_nuevo_responsable.getText().toString().isEmpty() && ValidarTelefono()
                            && !et_direccion_nuevo_responsable.getText().toString().isEmpty() && ValidarDireccion()
                            && !et_barrio_nuevo_responsable.getText().toString().isEmpty() && ValidarBarrio()
                            &&  ValidarEmail(et_correo_nuevo_responsable)

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





                }/*** FIN DEL else (CORREO No Vacío) ***/






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




    boolean flag_dni_responsable = false;

    public boolean ValidarDocumento() {



        if(et_dni_nuevo_responsable.getText().toString().length() > 0 && et_dni_nuevo_responsable.getText().toString().length() == 8){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_dni_nuevo_responsable.setError("DNI válido", myIconCheck);

            flag_dni_responsable = true;


        } else {


            et_dni_nuevo_responsable.setError("DNI no válido");

            flag_dni_responsable = false;

        }


        return flag_dni_responsable;



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






    boolean flag_codigo_area_responsable = false;

    public boolean ValidarCodigoArea() {


        if(UtilsCodigoAreaTelefonicoArgentina.Prueba(ActivityNuevoResponsablePatrocinio.this,et_codigo_area_nuevo_responsable.getText().toString())){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_codigo_area_nuevo_responsable.setError("Código de área válido", myIconCheck);


            et_telefono_nuevo_responsable.setFocusableInTouchMode(true);
            et_telefono_nuevo_responsable.setCursorVisible(true);
            et_telefono_nuevo_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            et_telefono_nuevo_responsable.setHint(R.string.edittext_hint);

            flag_codigo_area_responsable = true;


        } else {


            et_codigo_area_nuevo_responsable.setError("Código de área no válido");


            et_telefono_nuevo_responsable.setFocusable(false);
            et_telefono_nuevo_responsable.setCursorVisible(false);
            et_telefono_nuevo_responsable.setHint("");
            et_telefono_nuevo_responsable.setText("");
            et_telefono_nuevo_responsable.setBackgroundColor(Color.TRANSPARENT);

            flag_codigo_area_responsable = false;


        }

        return flag_codigo_area_responsable;


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




    boolean flag_telefono_responsable = false;

    public boolean ValidarTelefono(){



        if (et_codigo_area_nuevo_responsable.getText().toString().length() > 0
                && et_codigo_area_nuevo_responsable.getText().toString().length() < 3
                && et_telefono_nuevo_responsable.getText().toString().length() == 8){

            setEditTextMaxLength(et_telefono_nuevo_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_responsable = true;


        }



        else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 2
                && et_codigo_area_nuevo_responsable.getText().toString().length() < 4
                && et_telefono_nuevo_responsable.getText().toString().length() == 7){

            setEditTextMaxLength(et_telefono_nuevo_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_responsable = true;


        }



        else if (et_codigo_area_nuevo_responsable.getText().toString().length() > 3
                && et_codigo_area_nuevo_responsable.getText().toString().length() < 5
                && et_telefono_nuevo_responsable.getText().toString().length() == 6){

            setEditTextMaxLength(et_telefono_nuevo_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_nuevo_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_responsable = true;

        }





        else{

            et_telefono_nuevo_responsable.setError("¡Número de teléfono no válido!");

            flag_telefono_responsable = false;

        }


        return flag_telefono_responsable;

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



    boolean flag_correo_responsable = false;

    public boolean ValidarEmail(EditText et_correo_nuevo_responsble){



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

        if(et_correo_nuevo_responsable.getText().toString().isEmpty()){

            editor.putString("Correo_Responsable" + indice, "No tiene correo");

        } else {

            editor.putString("Correo_Responsable" + indice, et_correo_nuevo_responsable.getText().toString());

        }

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

        int indice_evento = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionEvento = sharedPreferences.getString("DimensionDeEvento", "");

        if(DimensionEvento == ""){

            indice_evento = 0;


        }//Fin del if


        else {

            indice_evento = Integer.parseInt(DimensionEvento) + 1;

        }//Fin del else



        editor.putString("Nombre_Evento" + indice_evento, et_nombre_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Direccion_Evento" + indice_evento, et_direccion_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Barrio_Evento" + indice_evento, et_barrio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Referencia_Evento" + indice_evento, et_referencia_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Inicio_Evento" + indice_evento, et_fecha_inicio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Fin_Evento" + indice_evento, et_fecha_fin_del_evento_nuevo_responsable.getText().toString());

        editor.putInt("Indice_Responsable" + indice_evento, indice_responsable);

        editor.putBoolean("Estado_Evento" + indice_evento, true);



        editor.putString("DimensionDeEvento", String.valueOf(indice_evento));
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







}/************************************************FIN DE LA Activity***************************************************************/