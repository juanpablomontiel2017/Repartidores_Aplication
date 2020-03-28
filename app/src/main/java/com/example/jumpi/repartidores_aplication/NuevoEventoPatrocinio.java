package com.example.jumpi.repartidores_aplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
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


    TextView tv_dni_responsable_nuevo_evento_patrocinio, tv_nombre_responsable_nuevo_evento_patrocinio,
             tv_apellido_responsable_nuevo_evento_patrocinio,tv_codigo_area_responsable_nuevo_evento_patrocinio,
             tv_telefono_responsable_nuevo_evento_patrocinio,tv_direccion_responsable_nuevo_evento_patrocinio,
             tv_barrio_responsable_nuevo_evento_patrocinio,tv_correo_responsable_nuevo_evento_patrocinio,
             tv_referencia_responsable_nuevo_evento_patrocinio;



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


        tv_dni_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.dni_responsable_nuevo_evento_patrocinio);
        tv_nombre_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.nombre_responsable_nuevo_evento_patrocinio);
        tv_apellido_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.apellido_responsable_nuevo_evento_patrocinio);
        tv_codigo_area_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.codigo_area_responsable_nuevo_evento_patrocinio);
        tv_telefono_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.telefono_responsable_nuevo_evento_patrocinio);
        tv_direccion_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.direccion_responsable_nuevo_evento_patrocinio);
        tv_barrio_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.barrio_responsable_nuevo_evento_patrocinio);
        tv_correo_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.email_responsable_nuevo_evento_patrocinio);
        tv_referencia_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.referencia_responsable_nuevo_evento_patrocinio);





        et_nombre_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_nombre_nuevo_evento_patrocinio);
        et_nombre_nuevo_evento_patrocinio.requestFocus();
        et_nombre_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));


        et_direccion_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_direccion_nuevo_evento_patrocinio);
        et_direccion_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));


        et_barrio_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_barrio_nuevo_evento_patrocinio);
        et_barrio_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));


        et_referencia_nuevo_evento_patrocinio = (EditText) findViewById(R.id.et_referencia_nuevo_evento_patrocinio);
        et_referencia_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado_nuevo_evento_supervisor));






        et_fecha_inicio_nuevo_evento_patrocinio = (EditText) findViewById(R.id.nuevo_evento_patrocinio_et_fecha_inicio);


        et_fecha_fin_nuevo_evento_patrocinio = (EditText) findViewById(R.id.nuevo_evento_patrocinio_et_fecha_fin);
        /**Deshabilitar el ET al comienzo solo si la fecha de inicio tiene una fecha seleccionada **/
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


                    et_fecha_fin_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
                    et_fecha_fin_nuevo_evento_patrocinio.setHint("(Obligatorio)");

                    dialog_fecha_fin_nuevo_evento = new DatePickerDialog(NuevoEventoPatrocinio.this,
                            R.style.MyDatePickerStyleRepartidoresNuevoEvento,selector_fecha_fin_nuevo_evento,
                            pYearFI,pMonthFI,pDayFI);


                    dialog_fecha_fin_nuevo_evento.getDatePicker().setMinDate(ConvertirFechaEnMilisegundos(pDayFI,
                            pMonthFI,pYearFI));



                } //FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))

                else{


                    et_fecha_fin_nuevo_evento_patrocinio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
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

                if(!et_nombre_nuevo_evento_patrocinio.getText().toString().isEmpty()
                        || !et_direccion_nuevo_evento_patrocinio.getText().toString().isEmpty()
                        || !et_barrio_nuevo_evento_patrocinio.getText().toString().isEmpty()
                        || !et_referencia_nuevo_evento_patrocinio.getText().toString().isEmpty()
                        || !et_fecha_inicio_nuevo_evento_patrocinio.getText().toString().isEmpty()
                        || !et_fecha_fin_nuevo_evento_patrocinio.getText().toString().isEmpty()){




                    AlertDialog.Builder builder = new AlertDialog.Builder(NuevoEventoPatrocinio.this);
                    builder.setIcon(R.drawable.ic_msj_alerta);
                    builder.setTitle("¿Desea salir?");
                    builder.setMessage("Al parecer algunos campos del formulario no están vacios. ¿Desea cancelar la adición de un nuevo evento?");


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











        btn_confirmar_nuevo_evento_patrocinio = (Button) findViewById(R.id.btn_confirmar_nuevo_evento_patrocinio);

        btn_confirmar_nuevo_evento_patrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable();


            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/







        /** Recibe los datos del responsable de la activity de BuscarResponsableParaPatrocinio **/

        String dni_responsable_recibir = getIntent().getStringExtra("DNI_Responsable_Enviar");
        tv_dni_responsable_nuevo_evento_patrocinio.setText(dni_responsable_recibir);

        String nombre_responsable_recibir = getIntent().getStringExtra("Nombre_Responsable_Enviar");
        tv_nombre_responsable_nuevo_evento_patrocinio.setText(nombre_responsable_recibir);

        String apellido_responsable_recibir = getIntent().getStringExtra("Apellido_Responsable_Enviar");
        tv_apellido_responsable_nuevo_evento_patrocinio.setText(apellido_responsable_recibir);

        String codigo_area_responsable_recibir = getIntent().getStringExtra("Codigo_Area_Responsable_Enviar");
        tv_codigo_area_responsable_nuevo_evento_patrocinio.setText(codigo_area_responsable_recibir);

        String telefono_responsable_recibir = getIntent().getStringExtra("Telefono_Responsable_Enviar");
        tv_telefono_responsable_nuevo_evento_patrocinio.setText(telefono_responsable_recibir);

        String direccion_responsable_recibir = getIntent().getStringExtra("Direccion_Responsable_Enviar");
        tv_direccion_responsable_nuevo_evento_patrocinio.setText(direccion_responsable_recibir);

        String barrio_responsable_recibir = getIntent().getStringExtra("Barrio_Responsable_Enviar");
        tv_barrio_responsable_nuevo_evento_patrocinio.setText(barrio_responsable_recibir);

        String correo_responsable_recibir = getIntent().getStringExtra("Correo_Responsable_Enviar");
        tv_correo_responsable_nuevo_evento_patrocinio.setText(correo_responsable_recibir);

        String referencia_responsable_recibir = getIntent().getStringExtra("Referencia_Responsable_Enviar");
        tv_referencia_responsable_nuevo_evento_patrocinio.setText(referencia_responsable_recibir);






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









    }/*********************FIN DEL onCreate()**************************/





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


            if (!et_nombre_nuevo_evento_patrocinio.getText().toString().isEmpty()
                    && !et_direccion_nuevo_evento_patrocinio.getText().toString().isEmpty()
                    && !et_barrio_nuevo_evento_patrocinio.getText().toString().isEmpty()
                    && !et_referencia_nuevo_evento_patrocinio.getText().toString().isEmpty()
                    && !et_fecha_inicio_nuevo_evento_patrocinio.getText().toString().isEmpty()
                    && !et_fecha_fin_nuevo_evento_patrocinio.getText().toString().isEmpty() ){

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








}/***************************FIN DE LA Activity**********************************/
