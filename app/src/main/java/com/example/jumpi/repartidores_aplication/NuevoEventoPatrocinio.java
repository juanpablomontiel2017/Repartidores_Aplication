package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoEventoPatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/

    EditText et_nombre_nuevo_evento_patrocinio_supervisor, et_direccion_nuevo_evento_patrocinio_supervisor,
            et_barrio_nuevo_evento_patrocinio_supervisor, et_referencia_nuevo_evento_patrocinio_supervisor,
            et_fecha_inicio_nuevo_evento_patrocinio_supervisor, et_fecha_fin_nuevo_evento_patrocinio_supervisor;



    Button btn_confirmar_nuevo_evento_patrocinio_supervisor, btn_cancelar_nuevo_evento_patrocinio_supervisor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_evento_patrocinio);



        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);






        et_nombre_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_nombre_nuevo_evento_patrocinio_supervisor);
        et_direccion_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_direccion_nuevo_evento_patrocinio_supervisor);
        et_barrio_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_barrio_nuevo_evento_patrocinio_supervisor);
        et_referencia_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_referencia_nuevo_evento_patrocinio_supervisor);
        et_fecha_inicio_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_fecha_inicio_nuevo_evento_patrocinio_supervisor);
        et_fecha_fin_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_fecha_fin_nuevo_evento_patrocinio_supervisor);







        btn_cancelar_nuevo_evento_patrocinio_supervisor = (Button) findViewById(R.id.btn_cancelar_nuevo_evento_patrocinio_supervisor);


        btn_cancelar_nuevo_evento_patrocinio_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!et_nombre_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                        || !et_direccion_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                        || !et_barrio_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                        || !et_referencia_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                        || !et_fecha_inicio_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                        || !et_fecha_fin_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()){




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











        btn_confirmar_nuevo_evento_patrocinio_supervisor = (Button) findViewById(R.id.btn_confirmar_nuevo_evento_patrocinio_supervisor);

        btn_confirmar_nuevo_evento_patrocinio_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable();


            }/*******************FIN DEL EVENTO onClick()**************************/


        });/*******************FIN DEL EVENTO setOnClickListener()**************************/










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




    boolean flag_nuevo_evento = false;


    public boolean ValidarCamposDelFormularioAntesDeConfirmarNuevoResponsable(){



        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {


            if (!et_nombre_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                    && !et_direccion_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                    && !et_barrio_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                    && !et_referencia_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                    && !et_fecha_inicio_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty()
                    && !et_fecha_fin_nuevo_evento_patrocinio_supervisor.getText().toString().isEmpty() ){

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








}/***************************FIN DE LA Activity**********************************/
