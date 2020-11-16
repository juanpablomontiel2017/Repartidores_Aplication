package com.example.jumpi.repartidores_aplication;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class ActivityDatosPersonalesPatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/


    EditText et_dni_responsable, et_nombre_responsable, et_apellido_responsable,
            et_codigo_area_responsable, et_telefono_responsable, et_direccion_responsable,
            et_barrio_responsable, et_correo_responsable, et_referencia_responsable;


    EditText et_nombre_del_evento_responsable, et_direccion_del_evento_responsable,
            et_barrio_del_evento_responsable, et_referencia_del_evento_responsable,
            et_fecha_inicio_del_evento_responsable, et_fecha_fin_del_evento_responsable;


    Button btn_guardar_nuevo_evento_patrocinio_supervisor, btn_salir_nuevo_evento_patrocinio_supervisor;

    ImageButton imgbtn_editar_datos_personales_responsable;

    TextView Titulo_Datos_Personales, Titulo_Datos_Evento;

    View Separador;

    int Indice_Item;


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








    /************************* COMIENZO DEL onCreate() ********************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales_responsable);



        Indice_Item = getIntent().getIntExtra("Indice_Item", 0);




        /*Instanciamos los campos de los DATOS PERSONALES del responsable */

        Titulo_Datos_Personales = (TextView) findViewById(R.id.datos_personales_responsable);

        Titulo_Datos_Evento = (TextView) findViewById(R.id.tv_evento_formulario_patrocinio);

        Separador = (View) findViewById(R.id.separador);

        et_dni_responsable = (EditText) findViewById(R.id.et_dni_responsable);
        et_dni_responsable.setFocusable(false);
        et_dni_responsable.setCursorVisible(false);
        et_dni_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_nombre_responsable = (EditText) findViewById(R.id.et_nombre_responsable);
        et_nombre_responsable.setFocusable(false);
        et_nombre_responsable.setCursorVisible(false);
        et_nombre_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_apellido_responsable = (EditText) findViewById(R.id.et_apellido_responsable);
        et_apellido_responsable.setFocusable(false);
        et_apellido_responsable.setCursorVisible(false);
        et_apellido_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_codigo_area_responsable = (EditText) findViewById(R.id.et_codigo_area_responsable);
        et_codigo_area_responsable.setFocusable(false);
        et_codigo_area_responsable.setCursorVisible(false);
        et_codigo_area_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_telefono_responsable = (EditText) findViewById(R.id.et_telefono_responsable);
        et_telefono_responsable.setFocusable(false);
        et_telefono_responsable.setCursorVisible(false);
        et_telefono_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_direccion_responsable = (EditText) findViewById(R.id.et_direccion_responsable);
        et_direccion_responsable.setFocusable(false);
        et_direccion_responsable.setCursorVisible(false);
        et_direccion_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_barrio_responsable = (EditText) findViewById(R.id.et_barrio_responsable);
        et_barrio_responsable.setFocusable(false);
        et_barrio_responsable.setCursorVisible(false);
        et_barrio_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_correo_responsable = (EditText) findViewById(R.id.et_email_responsable);
        et_correo_responsable.setFocusable(false);
        et_correo_responsable.setCursorVisible(false);
        et_correo_responsable.setBackgroundColor(Color.TRANSPARENT);




        et_referencia_responsable = (EditText) findViewById(R.id.et_referencia_responsable);
        et_referencia_responsable.setFocusable(false);
        et_referencia_responsable.setCursorVisible(false);
        et_referencia_responsable.setBackgroundColor(Color.TRANSPARENT);






        /*Instanciamos los campos de los datos del evento */

        et_nombre_del_evento_responsable = (EditText) findViewById(R.id.et_nombre_evento);
        et_nombre_del_evento_responsable.setFocusable(false);
        et_nombre_del_evento_responsable.setCursorVisible(false);
        et_nombre_del_evento_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_direccion_del_evento_responsable = (EditText) findViewById(R.id.et_direccion_evento);
        et_direccion_del_evento_responsable.setFocusable(false);
        et_direccion_del_evento_responsable.setCursorVisible(false);
        et_direccion_del_evento_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_barrio_del_evento_responsable = (EditText) findViewById(R.id.et_barrio_evento);
        et_barrio_del_evento_responsable.setFocusable(false);
        et_barrio_del_evento_responsable.setCursorVisible(false);
        et_barrio_del_evento_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_referencia_del_evento_responsable = (EditText) findViewById(R.id.et_referencia_evento);
        et_referencia_del_evento_responsable.setFocusable(false);
        et_referencia_del_evento_responsable.setCursorVisible(false);
        et_referencia_del_evento_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_fecha_inicio_del_evento_responsable = (EditText) findViewById(R.id.et_fecha_inicio_evento);
        et_fecha_inicio_del_evento_responsable.setFocusable(false);
        et_fecha_inicio_del_evento_responsable.setCursorVisible(false);
        et_fecha_inicio_del_evento_responsable.setBackgroundColor(Color.TRANSPARENT);


        et_fecha_fin_del_evento_responsable = (EditText) findViewById(R.id.et_fecha_fin_evento);
        et_fecha_fin_del_evento_responsable.setFocusable(false);
        et_fecha_fin_del_evento_responsable.setCursorVisible(false);
        et_fecha_fin_del_evento_responsable.setBackgroundColor(Color.TRANSPARENT);




        /** Recibe los datos del responsable de la activity de NuevoResponsable, con la siguiente función: **/
        MostrarDatosPersonalesResponsable();




        imgbtn_editar_datos_personales_responsable = (ImageButton) findViewById(R.id.img_button_editar_datos_responsable);

        imgbtn_editar_datos_personales_responsable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditarDatosResponsable(true);


            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/


        btn_guardar_nuevo_evento_patrocinio_supervisor = (Button) findViewById(R.id.btn_guardar_cambios_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);


        btn_salir_nuevo_evento_patrocinio_supervisor = (Button) findViewById(R.id.btn_salir_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);

        btn_salir_nuevo_evento_patrocinio_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });







    }/*************************** FIN DEL onCreate() *******************************/






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


    public void MostrarDatosPersonalesResponsable() {


        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        String DimensionEvento = preferences.getString("DimensionDeEvento", "");

        if (DimensionEvento != "") {

           LeerDatosPersonalesResponsable(Indice_Item);

        }//Fin del if


    }/******************************* FIN DE LA FUNCIÓN MostrarDatosPersonalesResponsable() ***********************************/


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


    public void LeerDatosPersonalesResponsable(int indice_evento) {


        final EditText ET_DNI_Responsable = (EditText) findViewById(R.id.et_dni_responsable);


        final EditText ET_Nombre_Responsable = (EditText) findViewById(R.id.et_nombre_responsable);


        final EditText ET_Apellido_Responsable = (EditText) findViewById(R.id.et_apellido_responsable);


        final EditText ET_Codigo_Area_Responsable = (EditText) findViewById(R.id.et_codigo_area_responsable);


        final EditText ET_Telefono_Responsable = (EditText) findViewById(R.id.et_telefono_responsable);


        final EditText ET_Direccion_Responsable = (EditText) findViewById(R.id.et_direccion_responsable);


        final EditText ET_Barrio_Responsable = (EditText) findViewById(R.id.et_barrio_responsable);


        final EditText ET_Correo_Responsable = (EditText) findViewById(R.id.et_email_responsable);


        if(ET_Correo_Responsable.equals("No tiene correo")){

            ET_Correo_Responsable.setTextColor(Color.parseColor("#d50000"));

        }


        final EditText ET_Referencia_Responsable = (EditText) findViewById(R.id.et_referencia_responsable);


        final EditText ET_Nombre_Evento = (EditText) findViewById(R.id.et_nombre_evento);


        final EditText ET_Direccion_Evento = (EditText) findViewById(R.id.et_direccion_evento);


        final EditText ET_Barrio_Evento = (EditText) findViewById(R.id.et_barrio_evento);


        final EditText ET_Referencia_Evento = (EditText) findViewById(R.id.et_referencia_evento);


        final EditText ET_Fecha_Inicio_Evento = (EditText) findViewById(R.id.et_fecha_inicio_evento);


        final EditText ET_Fecha_Fin_Evento = (EditText) findViewById(R.id.et_fecha_fin_evento);



        SharedPreferences preferences_evento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        String Nombre_Evento = preferences_evento.getString("Nombre_Evento" + indice_evento, "");
        String Direccion_Evento = preferences_evento.getString("Direccion_Evento" + indice_evento, "");
        String Barrio_Evento = preferences_evento.getString("Barrio_Evento" + indice_evento, "");
        String Referencia_Evento = preferences_evento.getString("Referencia_Evento" + indice_evento, "");
        String Fecha_Inicio_Evento = preferences_evento.getString("Fecha_Inicio_Evento" + indice_evento, "");
        String Fecha_Fin_Evento = preferences_evento.getString("Fecha_Fin_Evento" + indice_evento, "");

        int Indice_Responsable = preferences_evento.getInt("Indice_Responsable" + indice_evento, 0);





        /*Aca leemos los valores que nos interesan y que están guardados en el SharedPreferences de la otra Activity */

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

        ET_Nombre_Evento.setText(Nombre_Evento);
        ET_Direccion_Evento.setText(Direccion_Evento);
        ET_Barrio_Evento.setText(Barrio_Evento);
        ET_Referencia_Evento.setText(Referencia_Evento);
        ET_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
        ET_Fecha_Fin_Evento.setText(Fecha_Fin_Evento);


    }/***************************FIN DE LA FUNCIÓN LeerDatosPersonalesResponsable()*****************************/



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





    public boolean DeshabilitarVistasAlGuardarCambios(boolean flag_enabled){

        if (flag_enabled) {


            final EditText editText_dni_responsable = (EditText) findViewById(R.id.et_dni_responsable);

            final EditText editText_nombre_responsable = (EditText) findViewById(R.id.et_nombre_responsable);

            final EditText editText_apellido_responsable = (EditText) findViewById(R.id.et_apellido_responsable);

            final EditText editText_codigo_area_responsable = (EditText) findViewById(R.id.et_codigo_area_responsable);

            final EditText editText_telefono_responsable = (EditText) findViewById(R.id.et_telefono_responsable);

            final EditText editText_direccion_responsable = (EditText) findViewById(R.id.et_direccion_responsable);

            final EditText editText_barrio_responsable = (EditText) findViewById(R.id.et_barrio_responsable);

            final EditText editText_correo_responsable = (EditText) findViewById(R.id.et_email_responsable);

            final EditText editText_referencia_responsable = (EditText) findViewById(R.id.et_referencia_responsable);

            final EditText editText_nombre_evento = (EditText) findViewById(R.id.et_nombre_evento);

            final EditText editText_direccion_evento = (EditText) findViewById(R.id.et_direccion_evento);

            final EditText editText_barrio_evento = (EditText) findViewById(R.id.et_barrio_evento);

            final EditText editText_referencia_evento = (EditText) findViewById(R.id.et_referencia_evento);

            final EditText editText_fecha_inicio_evento = (EditText) findViewById(R.id.et_fecha_inicio_evento);

            final EditText editText_fecha_fin_evento = (EditText) findViewById(R.id.et_fecha_fin_evento);

            final Button btn_guardar_cambios = (Button) findViewById(R.id.btn_guardar_cambios_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);



            /** Deshabilitar todas las vistas **/

            editText_dni_responsable.setFocusable(false);
            editText_dni_responsable.setBackgroundColor(Color.TRANSPARENT);


            editText_nombre_responsable.setFocusable(false);
            editText_nombre_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_apellido_responsable.setFocusable(false);
            editText_apellido_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_codigo_area_responsable.setFocusable(false);
            editText_codigo_area_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_telefono_responsable.setFocusable(false);
            editText_telefono_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_direccion_responsable.setFocusable(false);
            editText_direccion_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_barrio_responsable.setFocusable(false);
            editText_barrio_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_correo_responsable.setFocusable(false);
            editText_correo_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_referencia_responsable.setFocusable(false);
            editText_referencia_responsable.setBackgroundColor(Color.TRANSPARENT);

            editText_nombre_evento.setFocusable(false);
            editText_nombre_evento.setBackgroundColor(Color.TRANSPARENT);

            editText_direccion_evento.setFocusable(false);
            editText_direccion_evento.setBackgroundColor(Color.TRANSPARENT);

            editText_barrio_evento.setFocusable(false);
            editText_barrio_evento.setBackgroundColor(Color.TRANSPARENT);

            editText_referencia_evento.setFocusable(false);
            editText_referencia_evento.setBackgroundColor(Color.TRANSPARENT);

            editText_fecha_inicio_evento.setFocusable(false);
            editText_fecha_inicio_evento.setBackgroundColor(Color.TRANSPARENT);

            editText_fecha_fin_evento.setFocusable(false);
            editText_fecha_fin_evento.setBackgroundColor(Color.TRANSPARENT);


            btn_guardar_cambios.setBackground(getDrawable(R.drawable.btn_confirmar_dos));
            btn_guardar_cambios.setEnabled(false);


        }  /*Fin del primer if (flag_enabled) {}*/



        return flag_enabled;



    }/*******FIN DE LA FUNCIÓN DeshabilitarVistasAlGuardarCambios() ********/



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




    public void EditarDatosResponsable(boolean flag_edit){

        if (flag_edit) {


            final EditText editText_dni_responsable = (EditText) findViewById(R.id.et_dni_responsable);

            final EditText editText_nombre_responsable = (EditText) findViewById(R.id.et_nombre_responsable);

            final EditText editText_apellido_responsable = (EditText) findViewById(R.id.et_apellido_responsable);

            final EditText editText_codigo_area_responsable = (EditText) findViewById(R.id.et_codigo_area_responsable);

            final EditText editText_telefono_responsable = (EditText) findViewById(R.id.et_telefono_responsable);

            final EditText editText_direccion_responsable = (EditText) findViewById(R.id.et_direccion_responsable);

            final EditText editText_barrio_responsable = (EditText) findViewById(R.id.et_barrio_responsable);

            final EditText editText_correo_responsable = (EditText) findViewById(R.id.et_email_responsable);

            final EditText editText_referencia_responsable = (EditText) findViewById(R.id.et_referencia_responsable);

            final EditText editText_nombre_evento = (EditText) findViewById(R.id.et_nombre_evento);

            final EditText editText_direccion_evento = (EditText) findViewById(R.id.et_direccion_evento);

            final EditText editText_barrio_evento = (EditText) findViewById(R.id.et_barrio_evento);

            final EditText editText_referencia_evento = (EditText) findViewById(R.id.et_referencia_evento);

            final EditText editText_fecha_inicio_evento = (EditText) findViewById(R.id.et_fecha_inicio_evento);

            final EditText editText_fecha_fin_evento = (EditText) findViewById(R.id.et_fecha_fin_evento);

            final Button btn_guardar_cambios = (Button) findViewById(R.id.btn_guardar_cambios_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);
            btn_guardar_cambios.setBackground(getDrawable(R.drawable.btn_confirmar_uno));




            /** Habilitar todas las vistas **/

            editText_dni_responsable.setFocusableInTouchMode(true);
            editText_dni_responsable.setCursorVisible(true);
            editText_dni_responsable.setTextColor(Color.parseColor("#263238"));
            editText_dni_responsable.getBackground().setColorFilter(Color.parseColor("#1a237e"), PorterDuff.Mode.SRC_IN);


            editText_nombre_responsable.setFocusableInTouchMode(true);
            editText_nombre_responsable.setCursorVisible(true);
            editText_nombre_responsable.setTextColor(Color.parseColor("#263238"));
            editText_nombre_responsable.getBackground().setColorFilter(Color.parseColor("#1a237e"), PorterDuff.Mode.SRC_IN);


            editText_apellido_responsable.setFocusableInTouchMode(true);
            editText_apellido_responsable.setCursorVisible(true);
            editText_apellido_responsable.setTextColor(Color.parseColor("#263238"));
            editText_apellido_responsable.getBackground().setColorFilter(Color.parseColor("#1a237e"), PorterDuff.Mode.SRC_IN);


            editText_codigo_area_responsable.setFocusableInTouchMode(true);
            editText_codigo_area_responsable.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_codigo_area_responsable.setTextColor(Color.parseColor("#263238"));


            editText_telefono_responsable.setFocusableInTouchMode(true);
            editText_telefono_responsable.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_telefono_responsable.setTextColor(Color.parseColor("#263238"));


            editText_direccion_responsable.setFocusableInTouchMode(true);
            editText_direccion_responsable.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_direccion_responsable.setTextColor(Color.parseColor("#263238"));


            editText_barrio_responsable.setFocusableInTouchMode(true);
            editText_barrio_responsable.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_barrio_responsable.setTextColor(Color.parseColor("#263238"));


            editText_correo_responsable.setFocusableInTouchMode(true);
            editText_correo_responsable.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_correo_responsable.setTextColor(Color.parseColor("#263238"));


            editText_referencia_responsable.setFocusableInTouchMode(true);
            editText_referencia_responsable.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_referencia_responsable.setTextColor(Color.parseColor("#263238"));


            editText_nombre_evento.setFocusableInTouchMode(true);
            editText_nombre_evento.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_nombre_evento.setTextColor(Color.parseColor("#263238"));


            editText_direccion_evento.setFocusableInTouchMode(true);
            editText_direccion_evento.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_direccion_evento.setTextColor(Color.parseColor("#263238"));


            editText_barrio_evento.setFocusableInTouchMode(true);
            editText_barrio_evento.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_barrio_evento.setTextColor(Color.parseColor("#263238"));


            editText_referencia_evento.setFocusableInTouchMode(true);
            editText_referencia_evento.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_referencia_evento.setTextColor(Color.parseColor("#263238"));


            editText_fecha_inicio_evento.setFocusableInTouchMode(true);
            editText_fecha_inicio_evento.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_fecha_inicio_evento.setTextColor(Color.parseColor("#263238"));


            editText_fecha_fin_evento.setFocusableInTouchMode(true);
            editText_fecha_fin_evento.setCursorVisible(true);
            editText_dni_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            editText_fecha_fin_evento.setTextColor(Color.parseColor("#263238"));


            /**** Comprobar si cada campo de texto es valido (arrojando un check o un msj de error) ****/

            editText_dni_responsable.addTextChangedListener(new TextWatcher() {
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



            editText_nombre_responsable.addTextChangedListener(new TextWatcher() {
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



            editText_apellido_responsable.addTextChangedListener(new TextWatcher() {
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






            editText_codigo_area_responsable.addTextChangedListener(new TextWatcher() {

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



            editText_telefono_responsable.addTextChangedListener(new TextWatcher() {

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










            editText_telefono_responsable.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View view, boolean hasFocus) {

                    if(!hasFocus){


                        editText_codigo_area_responsable.addTextChangedListener(new TextWatcher() {
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


                                if (editText_codigo_area_responsable.getText().toString().length() > 2
                                        && editText_codigo_area_responsable.getText().toString().length() < 4
                                        && editText_telefono_responsable.getText().toString().length() == 8){



                                    editText_telefono_responsable.setText(BorrarUltimoCaracter(editText_telefono_responsable.getText().toString()));

                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_responsable.setError("Número de teléfono valido", myIconCheck);



                                }



                                else if (editText_codigo_area_responsable.getText().toString().length() > 3
                                        && editText_codigo_area_responsable.getText().toString().length() < 5
                                        && editText_telefono_responsable.getText().toString().length() == 7){



                                    editText_telefono_responsable.setText(BorrarUltimoCaracter(editText_telefono_responsable.getText().toString()));

                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_responsable.setError("Número de teléfono valido", myIconCheck);


                                }





                                /******** CASO N°2: Se establece un relación incorrecta entre la cantidad
                                 * de dígitos del código de área con la cantidad de dígitos del número
                                 * de télefono, arrojando un msj de error.********/


                                else if (editText_codigo_area_responsable.getText().toString().length() > 2
                                        && editText_codigo_area_responsable.getText().toString().length() < 4
                                        && editText_telefono_responsable.getText().toString().length() == 6){


                                    editText_telefono_responsable.setError("¡Número de teléfono no válido!");

                                }



                                else if (editText_codigo_area_responsable.getText().toString().length() > 0
                                        && editText_codigo_area_responsable.getText().toString().length() < 3
                                        && editText_telefono_responsable.getText().toString().length() == 7){

                                    editText_telefono_responsable.setError("¡Número de teléfono no válido!");

                                }






                                /******** CASO N°3: Relación adecuada o correcta entre la cantidad de
                                 * dígitos del código de área con la cantidad de dígitos del número de
                                 * teléfono.********/


                                else if (editText_codigo_area_responsable.getText().toString().length() > 2
                                        && editText_codigo_area_responsable.getText().toString().length() < 4
                                        && editText_telefono_responsable.getText().toString().length() == 7){





                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_responsable.setError("Número de teléfono valido", myIconCheck);


                                }



                                else if (editText_codigo_area_responsable.getText().toString().length() > 3
                                        && editText_codigo_area_responsable.getText().toString().length() < 5
                                        && editText_telefono_responsable.getText().toString().length() == 6){




                                    Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
                                    myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
                                    editText_telefono_responsable.setError("Número de teléfono valido", myIconCheck);


                                }


                            }
                        });




                    }
                }
            });






            editText_direccion_responsable.addTextChangedListener(new TextWatcher() {
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








            editText_barrio_responsable.addTextChangedListener(new TextWatcher() {
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








            editText_correo_responsable.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                    /*Llamada a la función: */
                    ValidarEmail(editText_correo_responsable);


                }
            });






            editText_referencia_responsable.addTextChangedListener(new TextWatcher() {
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







            editText_nombre_evento.addTextChangedListener(new TextWatcher() {
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







            editText_direccion_evento.addTextChangedListener(new TextWatcher() {
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







            editText_barrio_evento.addTextChangedListener(new TextWatcher() {
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









            editText_referencia_evento.addTextChangedListener(new TextWatcher() {
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




            editText_fecha_inicio_evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));


            /**Deshabilitar el ET al comienzo solo si la fecha de inicio tiene una fecha seleccionada **/
            editText_fecha_fin_evento.setEnabled(false);
            editText_fecha_fin_evento.setFocusable(false);
            editText_fecha_fin_evento.setCursorVisible(false);


            //Estructura repetitiva para duplicar el tiempo de duración del Toast
            for (int i = 0; i < 2; i++) {

                Toast toast = Toast.makeText(ActivityDatosPersonalesPatrocinio.this, R.string.mensaje_datos_personales_fecha_fin, Toast.LENGTH_LONG);
                LinearLayout toastLayout = (LinearLayout) toast.getView();
                TextView toastTV = (TextView) toastLayout.getChildAt(0);
                toastTV.setTextSize(18);
                toast.show();

            }//FIN DEL for




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






                    editText_fecha_fin_evento.setEnabled(true);
                    editText_fecha_fin_evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
                    editText_fecha_fin_evento.setHint("(Obligatorio)");

                    dialog_fecha_fin = new DatePickerDialog(ActivityDatosPersonalesPatrocinio.this,
                            R.style.MyDatePickerStyleNuevoResponsable,selector_fecha_fin,
                            pYearFI,pMonthFI,pDayFI);


                    dialog_fecha_fin.getDatePicker().setMinDate(ConvertirFechaEnMilisegundos(pDayFI+1,
                            pMonthFI,pYearFI));


                }/**** Fin del método onDataSet ****/

            }; /*********** Fin del método setOnClickListener ***********/








            dialog_fecha_inicio = new DatePickerDialog(this, R.style.MyDatePickerStyleNuevoResponsable, selector_fecha_inicio, pYearFI, pMonthFI, pDayFI);
            dialog_fecha_inicio.getDatePicker().setMinDate(new Date().getTime());



            editText_fecha_inicio_evento.setOnClickListener(new View.OnClickListener() {

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



            editText_fecha_fin_evento.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    dialog_fecha_fin.show();



                }

            });



            btn_guardar_cambios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    if(DeshabilitarVistasAlGuardarCambios(ValidarCamposDelResponsable())){

                        btn_guardar_cambios.setEnabled(true);


                        GuardarCambiosDeEdicionDeEventoEnUnSharedPreferences(Indice_Item);

                        SharedPreferences preferences_evento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

                        int Indice_Responsable = preferences_evento.getInt("Indice_Responsable" + Indice_Item, 0);

                        GuardarCambiosDeEdicionDeResponsableEnUnSharedPreferences(Indice_Responsable);




                    }


                }
            });




        }  /*Fin del primer if (flag_edit) {}*/




    }/***************************FIN DE LA FUNCIÓN EditarDatosResponsable() **********************************/




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




    public void GuardarCambiosDeEdicionDeEventoEnUnSharedPreferences(int indice_evento){


        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("Nombre_Evento" + indice_evento, et_nombre_del_evento_responsable.getText().toString());

        editor.putString("Direccion_Evento" + indice_evento, et_direccion_del_evento_responsable.getText().toString());

        editor.putString("Barrio_Evento" + indice_evento, et_barrio_del_evento_responsable.getText().toString());

        editor.putString("Referencia_Evento" + indice_evento, et_referencia_del_evento_responsable.getText().toString());

        editor.putString("Fecha_Inicio_Evento" + indice_evento, et_fecha_inicio_del_evento_responsable.getText().toString());

        editor.putString("Fecha_Fin_Evento" + indice_evento, et_fecha_fin_del_evento_responsable.getText().toString());


        editor.commit();




    }/******************** FIN DE LA FUNCIÓN GuardarCambiosDeEdicionDeEventoEnUnSharedPreferences() *******************/




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




    public void GuardarCambiosDeEdicionDeResponsableEnUnSharedPreferences(int indice_responsable){


        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("DNI_Responsable" + indice_responsable, et_dni_responsable.getText().toString());

        editor.putString("Nombre_Responsable" + indice_responsable, et_nombre_responsable.getText().toString());

        editor.putString("Apellido_Responsable" + indice_responsable, et_apellido_responsable.getText().toString());

        editor.putString("Codigo_Area_Responsable" + indice_responsable, et_codigo_area_responsable.getText().toString());

        editor.putString("Telefono_Responsable" + indice_responsable, et_telefono_responsable.getText().toString());

        editor.putString("Direccion_Responsable" + indice_responsable, et_direccion_responsable.getText().toString());

        editor.putString("Barrio_Responsable" + indice_responsable, et_barrio_responsable.getText().toString());

        editor.putString("Correo_Responsable" + indice_responsable, et_correo_responsable.getText().toString());

        editor.putString("Referencia_Responsable" + indice_responsable, et_referencia_responsable.getText().toString());


        editor.commit();

    }/******************** FIN DE LA FUNCIÓN GuardarCambiosDeEdicionDeResponsableEnUnSharedPreferences() *******************/






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







    boolean flag_responsable = false;

    public boolean ValidarCamposDelResponsable(){


        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {



            if(et_correo_responsable.getText().toString().isEmpty() || et_correo_responsable.getText().toString().equals("No tiene correo")){


                if (!et_dni_responsable.getText().toString().isEmpty() && ValidarDocumento()
                        && !et_nombre_responsable.getText().toString().isEmpty() && ValidarNombre()
                        && !et_apellido_responsable.getText().toString().isEmpty() && ValidarApellido()
                        && !et_codigo_area_responsable.getText().toString().isEmpty() && ValidarCodigoArea()
                        && !et_telefono_responsable.getText().toString().isEmpty() && ValidarTelefono()
                        && !et_direccion_responsable.getText().toString().isEmpty() && ValidarDireccion()
                        && !et_barrio_responsable.getText().toString().isEmpty() && ValidarBarrio()
                        && !et_referencia_responsable.getText().toString().isEmpty() && ValidarReferencia()
                        && !et_nombre_del_evento_responsable.getText().toString().isEmpty() && ValidarNombreEvento()
                        && !et_direccion_del_evento_responsable.getText().toString().isEmpty() && ValidarDireccionEvento()
                        && !et_barrio_del_evento_responsable.getText().toString().isEmpty() && ValidarBarrioEvento()
                        && !et_referencia_del_evento_responsable.getText().toString().isEmpty() && ValidarReferenciaEvento()
                        && !et_fecha_inicio_del_evento_responsable.getText().toString().isEmpty() && ValidarFechaInicioEvento()
                        && !et_fecha_fin_del_evento_responsable.getText().toString().isEmpty() && ValidarFechaFinEvento()){





                    flag_responsable = true;

                }



                else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_responsable = false;

                }



            }/** FIN DEL if (Campo de CORREO vacío **/





            /**Campo de correo con valor **/
            else {

                if (!et_dni_responsable.getText().toString().isEmpty() && ValidarDocumento()
                        && !et_nombre_responsable.getText().toString().isEmpty() && ValidarNombre()
                        && !et_apellido_responsable.getText().toString().isEmpty() && ValidarApellido()
                        && !et_codigo_area_responsable.getText().toString().isEmpty() && ValidarCodigoArea()
                        && !et_telefono_responsable.getText().toString().isEmpty() && ValidarTelefono()
                        && !et_direccion_responsable.getText().toString().isEmpty() && ValidarDireccion()
                        && !et_barrio_responsable.getText().toString().isEmpty() && ValidarBarrio()
                        &&  ValidarEmail(et_correo_responsable)

                        && !et_referencia_responsable.getText().toString().isEmpty() && ValidarReferencia()
                        && !et_nombre_del_evento_responsable.getText().toString().isEmpty() && ValidarNombreEvento()
                        && !et_direccion_del_evento_responsable.getText().toString().isEmpty() && ValidarDireccionEvento()
                        && !et_barrio_del_evento_responsable.getText().toString().isEmpty() && ValidarBarrioEvento()
                        && !et_referencia_del_evento_responsable.getText().toString().isEmpty() && ValidarReferenciaEvento()
                        && !et_fecha_inicio_del_evento_responsable.getText().toString().isEmpty() && ValidarFechaInicioEvento()
                        && !et_fecha_fin_del_evento_responsable.getText().toString().isEmpty() && ValidarFechaFinEvento()){



                    flag_responsable = true;

                }



                else {

                    Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios y con datos válidos.", Toast.LENGTH_LONG).show();

                    flag_responsable = false;

                }





            }/*** FIN DEL else (CORREO No Vacío) ***/






            if (flag_responsable) {


                Toast.makeText(getApplicationContext(), "¡Los cambios de edición fueron guardados con éxito!", Toast.LENGTH_LONG).show();


            } //Fin del if (flag_nuevo_responsable){}


        } /*Fin del 'for'*/

        return flag_responsable;


    }/************************************FIN DE LA FUNCIÓN ValidarCamposDelResponsable()****************************************/




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



        if(et_dni_responsable.getText().toString().length() > 0 && et_dni_responsable.getText().toString().length() == 8){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_dni_responsable.setError("DNI válido", myIconCheck);

            flag_dni_ec = true;


        } else {


            et_dni_responsable.setError("DNI no válido");

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



    boolean flag_nombre_responsable = false;

    public boolean ValidarNombre() {


        if(et_nombre_responsable.getText().toString().length() > 2){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_responsable.setError("Nombre válido", myIconCheck);


            flag_nombre_responsable = true;

        } else {

            et_nombre_responsable.setError("Nombre no válido");

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


        if(et_apellido_responsable.getText().toString().length() > 3){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_apellido_responsable.setError("Apellido válido", myIconCheck);


            flag_apellido_responsable = true;

        } else {

            et_apellido_responsable.setError("Apellido no válido");

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


        if(UtilsCodigoAreaTelefonicoArgentina.Prueba(ActivityDatosPersonalesPatrocinio.this,et_codigo_area_responsable.getText().toString())){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_codigo_area_responsable.setError("Código de área válido", myIconCheck);


            et_telefono_responsable.setFocusableInTouchMode(true);
            et_telefono_responsable.setCursorVisible(true);
            et_telefono_responsable.setHint("Obligatorio");
            et_telefono_responsable.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_coloraccent));
            et_telefono_responsable.setHint(R.string.edittext_hint);

            flag_codigo_area_responsable = true;


        } else {


            et_codigo_area_responsable.setError("Código de área no válido");


            et_telefono_responsable.setFocusable(false);
            et_telefono_responsable.setCursorVisible(false);
            et_telefono_responsable.setHint("");
            et_telefono_responsable.setText("");
            et_telefono_responsable.setBackgroundColor(Color.TRANSPARENT);

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



        if (et_codigo_area_responsable.getText().toString().length() > 0
                && et_codigo_area_responsable.getText().toString().length() < 3
                && et_telefono_responsable.getText().toString().length() == 8){

            setEditTextMaxLength(et_telefono_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_responsable = true;


        }



        else if (et_codigo_area_responsable.getText().toString().length() > 2
                && et_codigo_area_responsable.getText().toString().length() < 4
                && et_telefono_responsable.getText().toString().length() == 7){

            setEditTextMaxLength(et_telefono_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_responsable = true;


        }



        else if (et_codigo_area_responsable.getText().toString().length() > 3
                && et_codigo_area_responsable.getText().toString().length() < 5
                && et_telefono_responsable.getText().toString().length() == 6){

            setEditTextMaxLength(et_telefono_responsable,8);


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_telefono_responsable.setError("Número de teléfono valido", myIconCheck);


            flag_telefono_responsable = true;

        }





        else{

            et_telefono_responsable.setError("¡Número de teléfono no válido!");

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


        if(et_direccion_responsable.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_responsable.setError("Dirección válida", myIconCheck);


            flag_direccion_responsable = true;

        } else {

            et_direccion_responsable.setError("Dirección no válida");

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


        if(et_barrio_responsable.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_responsable.setError("Barrio válido", myIconCheck);

            flag_barrio_responsable = true;

        } else {

            et_barrio_responsable.setError("Barrio no válido");

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


        if(et_referencia_responsable.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_responsable.setError("Referencia válida", myIconCheck);

            flag_referencia_responsable = true;


        } else {

            et_referencia_responsable.setError("Referencia no válida");

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


        if(et_nombre_del_evento_responsable.getText().toString().length() > 14){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_nombre_del_evento_responsable.setError("Nombre de evento válido", myIconCheck);

            flag_nombre_evento = true;

        } else {

            et_nombre_del_evento_responsable.setError("Nombre de evento no válido");

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


        if(et_direccion_del_evento_responsable.getText().toString().length() > 9 ){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_direccion_del_evento_responsable.setError("Dirección de evento válida", myIconCheck);

            flag_direccion_evento = true;

        } else {

            et_direccion_del_evento_responsable.setError("Dirección de evento no válida");

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



        if(et_barrio_del_evento_responsable.getText().toString().length() > 4){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_barrio_del_evento_responsable.setError("Barrio de evento válido", myIconCheck);

            flag_barrio_evento = true;

        } else {

            et_barrio_del_evento_responsable.setError("Barrio de evento no válido");

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


        if(et_referencia_del_evento_responsable.getText().toString().length() > 13){



            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_referencia_del_evento_responsable.setError("Referencia de evento válida", myIconCheck);

            flag_referencia_evento = true;

        } else {

            et_referencia_del_evento_responsable.setError("Referencia de evento no válida");

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



        if(et_fecha_inicio_del_evento_responsable.getText().toString().length() > 0
                && et_fecha_inicio_del_evento_responsable.getText().toString().length() == 10){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_fecha_inicio_del_evento_responsable.setError("Fecha de inicio válida", myIconCheck);

            flag_fecha_inicio = true;




        } else {


            et_fecha_inicio_del_evento_responsable.setError("Fecha de inicio no válida");

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



        if(et_fecha_fin_del_evento_responsable.getText().toString().length() > 0
                && et_fecha_fin_del_evento_responsable.getText().toString().length() == 10){


            Drawable myIconCheck = getResources().getDrawable(R.drawable.et_checkgood);
            myIconCheck.setBounds(0, 0, myIconCheck.getIntrinsicWidth(), myIconCheck.getIntrinsicHeight());
            et_fecha_fin_del_evento_responsable.setError("Fecha de fin válida", myIconCheck);

            flag_fecha_fin = true;




        } else {


            et_fecha_fin_del_evento_responsable.setError("Fecha de fin no válida");

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




    private void updateLabelFechaInicio() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_inicio_guardada = sdf.format(myCalendar.getTime());

        et_fecha_inicio_del_evento_responsable.setText(fecha_inicio_guardada);

    } /******************** FIN DE LA FUNCIÓN updateLabelFechaInicio() *******************/



    private void updateLabelFechaFin() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String fecha_fin_guardada = sdf.format(myCalendar2.getTime());

        et_fecha_fin_del_evento_responsable.setText(fecha_fin_guardada);

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


}/**************************** FIN DE LA Activity DatosPersonalesResponsable *********************/