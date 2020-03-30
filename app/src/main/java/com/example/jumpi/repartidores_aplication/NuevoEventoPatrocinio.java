package com.example.jumpi.repartidores_aplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NuevoEventoPatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/


    LinearLayout linearlayout_vertical_datos_responsable_nuevo_evento_patrocinio, linearlayout_horizontal_titulo_nuevo_evento_patrocinio,
                 linearlayout_horizontal_nombre_nuevo_evento_patrocinio, linearlayout_horizontal_direccion_nuevo_evento_patrocinio,
                 linearlayout_horizontal_barrio_nuevo_evento_patrocinio,linearlayout_horizontal_referencia_nuevo_evento_patrocinio,
                 linearlayout_horizontal_fecha_inicio_nuevo_evento_patrocinio,linearlayout_horizontal_fecha_fin_nuevo_evento_patrocinio;


    EditText et_dni_responsable_nuevo_evento_patrocinio, et_nombre_responsable_nuevo_evento_patrocinio,
             et_apellido_responsable_nuevo_evento_patrocinio,et_codigo_area_responsable_nuevo_evento_patrocinio,
             et_telefono_responsable_nuevo_evento_patrocinio,et_direccion_responsable_nuevo_evento_patrocinio,
             et_barrio_responsable_nuevo_evento_patrocinio,et_correo_responsable_nuevo_evento_patrocinio,
             et_referencia_responsable_nuevo_evento_patrocinio;



    EditText et_nombre_nuevo_evento_patrocinio, et_direccion_nuevo_evento_patrocinio,
            et_barrio_nuevo_evento_patrocinio, et_referencia_nuevo_evento_patrocinio,
            et_fecha_inicio_nuevo_evento_patrocinio, et_fecha_fin_nuevo_evento_patrocinio;



    Button btn_confirmar_nuevo_evento_patrocinio, btn_cancelar_nuevo_evento_patrocinio;



    final Calendar myCalendarNewEvent = Calendar.getInstance();
    final Calendar myCalendarNewEvent2 = Calendar.getInstance();

    private int pYearFI;
    private int pYearFF;
    private int pMonthFI;
    private int pMonthFF;
    private int pDayFI;
    private int pDayFF;



    private DatePickerDialog.OnDateSetListener selector_fecha_inicio_nuevo_evento,selector_fecha_fin_nuevo_evento;


    DatePickerDialog dialog_fecha_inicio_nuevo_evento, dialog_fecha_fin_nuevo_evento;

    int Indice_Item;



    /*********************** COMIENZO DEL onCreate() *************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_evento_patrocinio);





        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));


        /* Para cambiar el color del puntero o "burbuja" del EditText */
        setTheme(R.style.AppTheme_CursorNuevoEvento);



        Indice_Item = getIntent().getIntExtra("Indice_Item", 0);



        Usuario usuario = new Usuario();
        usuario.LeerUsuarioEnUnSharedPreferences(this);


        linearlayout_vertical_datos_responsable_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llv_datos_nuevo_evento_patrocinio);
        linearlayout_horizontal_titulo_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_titulo_nuevo_evento_patrocinio);
        linearlayout_horizontal_nombre_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_nombre_nuevo_evento_patrocinio);
        linearlayout_horizontal_direccion_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_direccion_nuevo_evento_patrocinio);
        linearlayout_horizontal_barrio_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_barrio_nuevo_evento_patrocinio);
        linearlayout_horizontal_referencia_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_referencia_responsable_nuevo_evento_patrocinio);
        linearlayout_horizontal_fecha_inicio_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_fecha_inicio_nuevo_evento_patrocinio);
        linearlayout_horizontal_fecha_fin_nuevo_evento_patrocinio = (LinearLayout) findViewById(R.id.llh_fecha_fin_nuevo_evento_patrocinio);


        /*Instanciamos los datos del responsable */
        et_dni_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.dni_responsable_nuevo_evento_patrocinio);
        et_nombre_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.nombre_responsable_nuevo_evento_patrocinio);
        et_apellido_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.apellido_responsable_nuevo_evento_patrocinio);
        et_codigo_area_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.codigo_area_responsable_nuevo_evento_patrocinio);
        et_telefono_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.telefono_responsable_nuevo_evento_patrocinio);
        et_direccion_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.direccion_responsable_nuevo_evento_patrocinio);
        et_barrio_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.barrio_responsable_nuevo_evento_patrocinio);
        et_correo_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.email_responsable_nuevo_evento_patrocinio);
        et_referencia_responsable_nuevo_evento_patrocinio = (EditText) findViewById(R.id.referencia_responsable_nuevo_evento_patrocinio);



        /*Deshabilitamos sus campos */
        et_dni_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_dni_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_dni_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_nombre_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_nombre_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_nombre_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_apellido_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_apellido_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_apellido_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_codigo_area_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_codigo_area_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_codigo_area_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_telefono_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_telefono_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_telefono_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_direccion_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_direccion_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_direccion_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_barrio_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_barrio_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_barrio_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_correo_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_correo_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_correo_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);


        et_referencia_responsable_nuevo_evento_patrocinio.setFocusable(false);
        et_referencia_responsable_nuevo_evento_patrocinio.setCursorVisible(false);
        et_referencia_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);






        et_nombre_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_nombre_nuevo_evento_patrocinio);
        et_nombre_nuevo_evento_patrocinio.requestFocus();
        et_nombre_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));


        et_nombre_nuevo_evento_patrocinio.addTextChangedListener(new TextWatcher() {
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




        et_direccion_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_direccion_nuevo_evento_patrocinio);
        et_direccion_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));

        et_direccion_nuevo_evento_patrocinio.addTextChangedListener(new TextWatcher() {
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



        et_barrio_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_barrio_nuevo_evento_patrocinio);
        et_barrio_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));

        et_barrio_nuevo_evento_patrocinio.addTextChangedListener(new TextWatcher() {
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



        et_referencia_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_referencia_nuevo_evento_patrocinio);
        et_referencia_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));

        et_referencia_nuevo_evento_patrocinio.addTextChangedListener(new TextWatcher() {
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





        et_fecha_inicio_nuevo_evento_patrocinio = (EditText) findViewById(R.id.nuevo_evento_patrocinio_et_fecha_inicio);
        et_fecha_inicio_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));


        et_fecha_fin_nuevo_evento_patrocinio = (EditText) findViewById(R.id.nuevo_evento_patrocinio_et_fecha_fin);
        /**Deshabilitar el ET al comienzo solo si la fecha de inicio tiene una fecha seleccionada **/
        et_fecha_fin_nuevo_evento_patrocinio.setEnabled(false);
        et_fecha_fin_nuevo_evento_patrocinio.setFocusable(false);
        et_fecha_fin_nuevo_evento_patrocinio.setCursorVisible(false);
        et_fecha_fin_nuevo_evento_patrocinio.setHint("");
        et_fecha_fin_nuevo_evento_patrocinio.setText("");
        et_fecha_fin_nuevo_evento_patrocinio.setBackgroundColor(Color.TRANSPARENT);




        selector_fecha_fin_nuevo_evento = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {


                pYearFF = year;
                pMonthFF = monthOfYear;
                pDayFF = dayOfMonth;


                // TODO Auto-generated method stub
                myCalendarNewEvent2.set(Calendar.YEAR, pYearFF);
                myCalendarNewEvent2.set(Calendar.MONTH,pMonthFF);
                myCalendarNewEvent2.set(Calendar.DAY_OF_MONTH,pDayFF);

                /*Llamada a la función */
                updateLabelFechaFinNuevoEvento();


            } /**** Fin del método onDataSet ****/

        }; /*********** Fin del método OnDateSetListener ***********/




        selector_fecha_inicio_nuevo_evento = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                pYearFI = year;
                pMonthFI = monthOfYear;
                pDayFI = dayOfMonth;



                // TODO Auto-generated method stub
                myCalendarNewEvent.set(Calendar.YEAR, pYearFI);
                myCalendarNewEvent.set(Calendar.MONTH,pMonthFI);
                myCalendarNewEvent.set(Calendar.DAY_OF_MONTH,pDayFI);

                /*Llamada a la función */
                updateLabelFechaInicioNuevoEvento();




                if(usuario.getTipo_de_Usuario().equals("repartidor")) {


                    et_fecha_fin_nuevo_evento_patrocinio.setEnabled(true);
                    et_fecha_fin_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));
                    et_fecha_fin_nuevo_evento_patrocinio.setHint("(Obligatorio)");

                    dialog_fecha_fin_nuevo_evento = new DatePickerDialog(NuevoEventoPatrocinio.this,
                            R.style.MyDatePickerStyleRepartidoresNuevoEvento,selector_fecha_fin_nuevo_evento,
                            pYearFI,pMonthFI,pDayFI);


                    dialog_fecha_fin_nuevo_evento.getDatePicker().setMinDate(ConvertirFechaEnMilisegundos(pDayFI,
                            pMonthFI,pYearFI));



                } //FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))

                else{

                    et_fecha_fin_nuevo_evento_patrocinio.setEnabled(true);
                    et_fecha_fin_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));
                    et_fecha_fin_nuevo_evento_patrocinio.setHint("(Obligatorio)");


                    dialog_fecha_fin_nuevo_evento = new DatePickerDialog(NuevoEventoPatrocinio.this,
                            R.style.MyDatePickerStyleSupervisoresNuevoEvento,
                            selector_fecha_fin_nuevo_evento, pYearFI,pMonthFI,pDayFI);


                    dialog_fecha_fin_nuevo_evento.getDatePicker().setMinDate(ConvertirFechaEnMilisegundos(pDayFI,
                            pMonthFI,pYearFI));



                }//Fin del else



            }/**** Fin del método onDataSet ****/

        };/*********** Fin del método setOnClickListener ***********/



        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            dialog_fecha_inicio_nuevo_evento = new DatePickerDialog(this,
                    R.style.MyDatePickerStyleRepartidoresNuevoEvento, selector_fecha_inicio_nuevo_evento,
                    pYearFI, pMonthFI, pDayFI);

            dialog_fecha_inicio_nuevo_evento.getDatePicker().setMinDate(new Date().getTime());

            et_fecha_inicio_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    dialog_fecha_inicio_nuevo_evento.show();


                }

            });


        }//FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))


        //Usuario logueado como SUPERVISOR
        else {

            dialog_fecha_inicio_nuevo_evento = new DatePickerDialog(this,
                    R.style.MyDatePickerStyleSupervisoresNuevoEvento, selector_fecha_inicio_nuevo_evento,
                    pYearFI, pMonthFI, pDayFI);

            dialog_fecha_inicio_nuevo_evento.getDatePicker().setMinDate(new Date().getTime());

            et_fecha_inicio_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    dialog_fecha_inicio_nuevo_evento.show();


                }

            });

        }//Fin del else





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





        if(usuario.getTipo_de_Usuario().equals("repartidor")){


            et_fecha_fin_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    dialog_fecha_fin_nuevo_evento.show();



                }
            });

        }//FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))

        else {


            et_fecha_fin_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    dialog_fecha_fin_nuevo_evento.show();

                }
            });



        }//Fin del else












        btn_cancelar_nuevo_evento_patrocinio = (Button) findViewById(R.id.btn_cancelar_nuevo_evento_patrocinio);

        btn_cancelar_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(usuario.getTipo_de_Usuario().equals("repartidor")){


                    AlertDialog.Builder builder = new AlertDialog.Builder(NuevoEventoPatrocinio.this, R.style.AlertDialogStyleRepartidores);


                    builder.setIcon(R.drawable.ic_msj_alerta);
                    builder.setTitle("¿Desea salir?");
                    builder.setMessage("Al cancelar la adición de un nuevo evento se perderán los cambios realizados ¿Desea continuar?");



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



                }//FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))




                else{


                        AlertDialog.Builder builder = new AlertDialog.Builder(NuevoEventoPatrocinio.this, R.style.AlertDialogStyleSupervisores);


                        builder.setIcon(R.drawable.ic_msj_alerta);
                        builder.setTitle("¿Desea salir?");
                        builder.setMessage("Al cancelar la adición de un nuevo evento se perderán los cambios realizados ¿Desea continuar?");



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



                }//FIN DEL else (usuario = SUPERVISOR)



           }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/










        btn_confirmar_nuevo_evento_patrocinio = (Button) findViewById(R.id.btn_confirmar_nuevo_evento_patrocinio);

        btn_confirmar_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable()) {


                    int Indice_Responsable = GuardarDatosResponsableEnSharedPreferences();

                    GuardarDatosEventoEnSharedPreferences(Indice_Responsable);

                    Intent intent = new Intent (NuevoEventoPatrocinio.this, BuscarResponsableParaPatrocinio.class);

                    startActivity(intent);

                    finish();

                }

            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/







        /** Recibe los datos del responsable de la activity de BuscarResponsableParaPatrocinio **/
        RecibirDatosPersonalesResponsable();






        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/
        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));






            linearlayout_vertical_datos_responsable_nuevo_evento_patrocinio.setBackgroundColor(Color.parseColor("#0277bd"));

            linearlayout_horizontal_nombre_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_direccion_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_barrio_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_referencia_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_fecha_inicio_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_fecha_fin_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));


            btn_confirmar_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.btn_borde_redondeado));
            btn_confirmar_nuevo_evento_patrocinio.getBackground().setColorFilter(Color.parseColor("#00bcd4"), PorterDuff.Mode.SRC_ATOP);


            btn_cancelar_nuevo_evento_patrocinio.setBackground(getDrawable(R.drawable.btn_borde_redondeado));
            btn_cancelar_nuevo_evento_patrocinio.getBackground().setColorFilter(Color.parseColor("#283593"), PorterDuff.Mode.SRC_ATOP);



        }//Fin del if









    }/************************* FIN DEL onCreate() ******************************/






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




    public void RecibirDatosPersonalesResponsable() {


        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        String DimensionEvento = preferences.getString("DimensionDeEvento", "");

        if (DimensionEvento != "") {

            LeerDatosPersonalesResponsable(Indice_Item);

        }//Fin del if


    }/******************************* FIN DE LA FUNCIÓN RecibirDatosPersonalesResponsable() ***********************************/



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






    public void LeerDatosPersonalesResponsable(int indice_evento){




        final EditText ET_DNI_Responsable = (EditText) findViewById(R.id.dni_responsable_nuevo_evento_patrocinio);


        final EditText ET_Nombre_Responsable = (EditText) findViewById(R.id.nombre_responsable_nuevo_evento_patrocinio);


        final EditText ET_Apellido_Responsable = (EditText) findViewById(R.id.apellido_responsable_nuevo_evento_patrocinio);


        final EditText ET_Codigo_Area_Responsable = (EditText) findViewById(R.id.codigo_area_responsable_nuevo_evento_patrocinio);


        final EditText ET_Telefono_Responsable = (EditText) findViewById(R.id.telefono_responsable_nuevo_evento_patrocinio);


        final EditText ET_Direccion_Responsable = (EditText) findViewById(R.id.direccion_responsable_nuevo_evento_patrocinio);


        final EditText ET_Barrio_Responsable = (EditText) findViewById(R.id.barrio_responsable_nuevo_evento_patrocinio);


        final EditText ET_Correo_Responsable = (EditText) findViewById(R.id.email_responsable_nuevo_evento_patrocinio);


        final EditText ET_Referencia_Responsable = (EditText) findViewById(R.id.referencia_responsable_nuevo_evento_patrocinio);





        SharedPreferences preferences_evento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);


        int Indice_Responsable = preferences_evento.getInt("Indice_Responsable" + indice_evento, 0);






        SharedPreferences preferences = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);

        String DNI_Responsable = preferences.getString("DNI_Responsable" + Indice_Responsable, "");
        String Nombre_Responsable = preferences.getString("Nombre_Responsable" + Indice_Responsable, "");
        String Apellido_Responsable = preferences.getString("Apellido_Responsable" + Indice_Responsable, "");
        String Codigo_Area_Responsable = preferences.getString("Codigo_Area_Responsable" + Indice_Responsable, "");
        String Telefono_Responsable = preferences.getString("Telefono_Responsable" + Indice_Responsable, "");
        String Direccion_Responsable = preferences.getString("Direccion_Responsable" + Indice_Responsable, "");
        String Barrio_Responsable = preferences.getString("Barrio_Responsable" + Indice_Responsable, "");
        String Correo_Responsable = preferences.getString("Correo_Responsable" + Indice_Responsable, "");
        String Referencia_Responsable = preferences.getString("Referencia_Responsable" + Indice_Responsable, "");




        ET_DNI_Responsable.setText(DNI_Responsable);
        ET_Nombre_Responsable.setText(Nombre_Responsable);
        ET_Apellido_Responsable.setText(Apellido_Responsable);
        ET_Codigo_Area_Responsable.setText(Codigo_Area_Responsable);
        ET_Telefono_Responsable.setText(Telefono_Responsable);
        ET_Direccion_Responsable.setText(Direccion_Responsable);
        ET_Barrio_Responsable.setText(Barrio_Responsable);
        ET_Correo_Responsable.setText(Correo_Responsable);
        ET_Referencia_Responsable.setText(Referencia_Responsable);





    }/**************** FIN DE LA FUNCIÓN LeerDatosPersonalesResponsable() **************/



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

        editor.putString("DNI_Responsable" + indice, et_dni_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Nombre_Responsable" + indice, et_nombre_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Apellido_Responsable" + indice, et_apellido_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Codigo_Area_Responsable" + indice, et_codigo_area_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Telefono_Responsable" + indice, et_telefono_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Direccion_Responsable" + indice, et_direccion_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Barrio_Responsable" + indice, et_barrio_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Correo_Responsable" + indice, et_correo_responsable_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Referencia_Responsable" + indice, et_referencia_responsable_nuevo_evento_patrocinio.getText().toString());




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



        editor.putString("Nombre_Evento" + indice_evento, et_nombre_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Direccion_Evento" + indice_evento, et_direccion_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Barrio_Evento" + indice_evento, et_barrio_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Referencia_Evento" + indice_evento, et_referencia_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Fecha_Inicio_Evento" + indice_evento, et_fecha_inicio_nuevo_evento_patrocinio.getText().toString());

        editor.putString("Fecha_Fin_Evento" + indice_evento, et_fecha_fin_nuevo_evento_patrocinio.getText().toString());

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









    private void updateLabelFechaInicioNuevoEvento() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_inicio_guardada = sdf.format(myCalendarNewEvent.getTime());

        et_fecha_inicio_nuevo_evento_patrocinio.setText(fecha_inicio_guardada);

    } /******************** FIN DE LA FUNCIÓN updateLabelFechaInicioNuevoEvento() *******************/




    private void updateLabelFechaFinNuevoEvento() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_fin_guardada = sdf.format(myCalendarNewEvent2.getTime());

        et_fecha_fin_nuevo_evento_patrocinio.setText(fecha_fin_guardada);

    } /******************** FIN DE LA FUNCIÓN updateLabelFechaFinNuevoEvento() *******************/




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





    boolean flag_nuevo_evento = false;


    public boolean ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable(){



        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {


            if (!et_nombre_nuevo_evento_patrocinio.getText().toString().isEmpty() && ValidarNombreEvento()
                    && !et_direccion_nuevo_evento_patrocinio.getText().toString().isEmpty() && ValidarDireccionEvento()
                    && !et_barrio_nuevo_evento_patrocinio.getText().toString().isEmpty() && ValidarBarrioEvento()
                    && !et_referencia_nuevo_evento_patrocinio.getText().toString().isEmpty() && ValidarReferenciaEvento()
                    && !et_fecha_inicio_nuevo_evento_patrocinio.getText().toString().isEmpty() && ValidarFechaInicioEvento()
                    && !et_fecha_fin_nuevo_evento_patrocinio.getText().toString().isEmpty() && ValidarFechaFinEvento()){

                flag_nuevo_evento = true;

            }

            else{

                Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios.", Toast.LENGTH_LONG).show();


                flag_nuevo_evento = false;

            }






            if (flag_nuevo_evento) {

                finish();

                Toast.makeText(getApplicationContext(), "¡Un nuevo evento se ha añadido con éxito!", Toast.LENGTH_LONG).show();


            } //Fin del if (flag_nuevo_evento){}


        } /*Fin del 'for'*/

        return flag_nuevo_evento;


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



    boolean flag_nombre_evento = false;

    public boolean ValidarNombreEvento() {


        if(et_nombre_nuevo_evento_patrocinio.getText().toString().length() > 14){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_nuevo_evento_patrocinio.setError("Nombre de evento válido", myIconCheck);

            flag_nombre_evento = true;

        } else {

            Drawable myIconError = getResources().getDrawable(R.drawable.campo_error_32px);
            myIconError.setBounds(0, 0, myIconError.getIntrinsicWidth(), myIconError.getIntrinsicHeight());
            et_nombre_nuevo_evento_patrocinio.setError("Nombre de evento no válido", myIconError);

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


        if(et_direccion_nuevo_evento_patrocinio.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_nuevo_evento_patrocinio.setError("Dirección de evento válida", myIconCheck);

            flag_direccion_evento = true;

        } else {


            Drawable myIconError = getResources().getDrawable(R.drawable.campo_error_32px);
            myIconError.setBounds(0, 0, myIconError.getIntrinsicWidth(), myIconError.getIntrinsicHeight());
            et_direccion_nuevo_evento_patrocinio.setError("Dirección de evento no válida", myIconError);

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



        if(et_barrio_nuevo_evento_patrocinio.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_nuevo_evento_patrocinio.setError("Barrio de evento válido", myIconCheck);

            flag_barrio_evento = true;

        } else {


            Drawable myIconError = getResources().getDrawable(R.drawable.campo_error_32px);
            myIconError.setBounds(0, 0, myIconError.getIntrinsicWidth(), myIconError.getIntrinsicHeight());
            et_barrio_nuevo_evento_patrocinio.setError("Barrio de evento no válido", myIconError);

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


        if(et_referencia_nuevo_evento_patrocinio.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_nuevo_evento_patrocinio.setError("Referencia de evento válida", myIconCheck);

            flag_referencia_evento = true;

        } else {


            Drawable myIconError = getResources().getDrawable(R.drawable.campo_error_32px);
            myIconError.setBounds(0, 0, myIconError.getIntrinsicWidth(), myIconError.getIntrinsicHeight());
            et_referencia_nuevo_evento_patrocinio.setError("Referencia de evento no válida", myIconError);

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



        if(et_fecha_inicio_nuevo_evento_patrocinio.getText().toString().length() > 0
                && et_fecha_inicio_nuevo_evento_patrocinio.getText().toString().length() == 10){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_fecha_inicio_nuevo_evento_patrocinio.setError("Fecha de inicio válida", myIconCheck);

            flag_fecha_inicio = true;




        } else {


            Drawable myIconError = getResources().getDrawable(R.drawable.campo_error_32px);
            myIconError.setBounds(0, 0, myIconError.getIntrinsicWidth(), myIconError.getIntrinsicHeight());
            et_fecha_inicio_nuevo_evento_patrocinio.setError("Fecha de inicio de evento no válida", myIconError);

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



        if(et_fecha_fin_nuevo_evento_patrocinio.getText().toString().length() > 0
                && et_fecha_fin_nuevo_evento_patrocinio.getText().toString().length() == 10){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_fecha_fin_nuevo_evento_patrocinio.setError("Fecha de fin válida", myIconCheck);

            flag_fecha_fin = true;




        } else {



            Drawable myIconError = getResources().getDrawable(R.drawable.campo_error_32px);
            myIconError.setBounds(0, 0, myIconError.getIntrinsicWidth(), myIconError.getIntrinsicHeight());
            et_fecha_fin_nuevo_evento_patrocinio.setError("Fecha de fin de evento no válida", myIconError);

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




}/***************************FIN DE LA Activity**********************************/
