package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class NuevoResponsablePatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/

    EditText et_dni_nuevo_responsable,et_nombre_apellido_nuevo_responsable,et_telefono_nuevo_responsable,
             et_direccion_nuevo_responsable,et_barrio_nuevo_responsable,et_correo_nuevo_responsable,
             et_referencia_nuevo_responsable;


    EditText et_nombre_del_evento_nuevo_responsable, et_direccion_del_evento_nuevo_responsable,et_barrio_del_evento_nuevo_responsable,
             et_referencia_del_evento_nuevo_responsable,et_fecha_inicio_del_evento_nuevo_responsable, et_fecha_fin_del_evento_nuevo_responsable;



    Button btn_confirmar_nuevo_responsable_del_evento_supervisor, btn_cancelar_nuevo_responsable_del_evento_supervisor;








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





        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);





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

        et_nombre_apellido_nuevo_responsable = (EditText) findViewById(R.id.et_nombre_apellido_nuevo_responsable_formulario_patrocinio_supervisor);


        et_telefono_nuevo_responsable = (EditText) findViewById(R.id.et_telefono_nuevo_responsable_formulario_patrocinio_supervisor);


        et_direccion_nuevo_responsable = (EditText) findViewById(R.id.et_direccion_nuevo_responsable_formulario_patrocinio_supervisor);


        et_barrio_nuevo_responsable = (EditText) findViewById(R.id.et_barrio_nuevo_responsable_formulario_patrocinio_supervisor);


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



                if (s.toString().length() > 0 &&!ValidarEmail(et_correo_nuevo_responsable.getText().toString())){


                    et_correo_nuevo_responsable.setError("¡Correo no válido!");


                } else {


                    Toast.makeText(getApplicationContext(), "¡Email válido!", Toast.LENGTH_LONG).show();


                }


            }
        });



        et_referencia_nuevo_responsable = (EditText) findViewById(R.id.et_referencia_nuevo_responsable_formulario_patrocinio_supervisor);


        /*Instanciamos los campos de los DATOS PERSONALES del nuevo responsable */

        et_nombre_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_nombre_evento_formulario_patrocinio_supervisor);


        et_direccion_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_direccion_evento_formulario_patrocinio_supervisor);


        et_barrio_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_barrio_evento_formulario_patrocinio_supervisor);


        et_referencia_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_referencia_evento_formulario_patrocinio_supervisor);


        et_fecha_inicio_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_fecha_inicio_evento_formulario_patrocinio_supervisor);


        et_fecha_fin_del_evento_nuevo_responsable = (EditText) findViewById(R.id.et_nuevo_responsable_fecha_fin_evento_formulario_patrocinio);











        btn_cancelar_nuevo_responsable_del_evento_supervisor = (Button) findViewById(R.id.btn_cancelar_nuevo_responsable_del_evento_formulario_patrocinio_supervisor);

        btn_cancelar_nuevo_responsable_del_evento_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!et_dni_nuevo_responsable.getText().toString().isEmpty() || !et_nombre_apellido_nuevo_responsable.getText().toString().isEmpty()
                        || !et_telefono_nuevo_responsable.getText().toString().isEmpty() || !et_direccion_nuevo_responsable.getText().toString().isEmpty()
                        || !et_barrio_nuevo_responsable.getText().toString().isEmpty() || !et_correo_nuevo_responsable.getText().toString().isEmpty()
                        || !et_referencia_nuevo_responsable.getText().toString().isEmpty() ||!et_nombre_del_evento_nuevo_responsable.getText().toString().isEmpty()
                        || !et_direccion_del_evento_nuevo_responsable.getText().toString().isEmpty() || !et_barrio_del_evento_nuevo_responsable.getText().toString().isEmpty()
                        || !et_fecha_inicio_del_evento_nuevo_responsable.getText().toString().isEmpty()|| !et_fecha_fin_del_evento_nuevo_responsable.getText().toString().isEmpty()){




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


            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/














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






    boolean flag_nuevo_responsable = false;


    public boolean ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable(){



            //Estructura repetitiva para duplicar el tiempo de duración del Toast
            for (int i = 0; i < 2; i++) {

                /**Primer Validación: todos los campos obligatorios deben estar rellenados**/

                    if (!et_dni_nuevo_responsable.getText().toString().isEmpty() && !et_nombre_apellido_nuevo_responsable.getText().toString().isEmpty()
                            && !et_telefono_nuevo_responsable.getText().toString().isEmpty() && !et_direccion_nuevo_responsable.getText().toString().isEmpty()
                            && !et_barrio_nuevo_responsable.getText().toString().isEmpty() && !et_nombre_del_evento_nuevo_responsable.getText().toString().isEmpty()
                            && !et_direccion_del_evento_nuevo_responsable.getText().toString().isEmpty() && !et_barrio_del_evento_nuevo_responsable.getText().toString().isEmpty()
                            && !et_referencia_del_evento_nuevo_responsable.getText().toString().isEmpty()&& !et_fecha_inicio_del_evento_nuevo_responsable.getText().toString().isEmpty()
                            && !et_fecha_fin_del_evento_nuevo_responsable.getText().toString().isEmpty()){

                        flag_nuevo_responsable = true;

                    }

                    else{

                        Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios.", Toast.LENGTH_LONG).show();


                        flag_nuevo_responsable = false;

                    }






                if (flag_nuevo_responsable) {

                    finish();

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



    public void ValidarDocumento() {


        if(et_dni_nuevo_responsable.getText().toString().length() > 0 && et_dni_nuevo_responsable.getText().toString().length() == 8 ){

            Toast.makeText(getApplicationContext(), "¡DNI válido!", Toast.LENGTH_LONG).show();


        } else {

            et_dni_nuevo_responsable.setError("DNI no válido");

        }


    } /**********************FIN DE LA FUNCIÓN ValidarEmail()*********************/



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


        Pattern pattern = Patterns.EMAIL_ADDRESS;

        return pattern.matcher(email).matches();

     } /**********************FIN DE LA FUNCIÓN ValidarEmail()*********************/






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
