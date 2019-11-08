package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NuevoEventoPatrocinio extends AppCompatActivity {


    /**********************DECLARACIÓN DE VARIABLES GLOBALES***********************/


    LinearLayout linearlayout_vertical_datos_nuevo_evento_patrocinio_supervisor, linearlayout_horizontal_titulo_nuevo_evento_patrocinio_supervisor,
                 linearlayout_horizontal_nombre_nuevo_evento_patrocinio_supervisor, linearlayout_horizontal_direccion_nuevo_evento_patrocinio_supervisor,
                 linearlayout_horizontal_barrio_nuevo_evento_patrocinio_supervisor,linearlayout_horizontal_referencia_nuevo_evento_patrocinio_supervisor,
                 linearlayout_horizontal_fecha_inicio_nuevo_evento_patrocinio_supervisor,linearlayout_horizontal_fecha_fin_nuevo_evento_patrocinio_supervisor;


    TextView tv_dni_responsable_nuevo_evento_patrocinio, tv_nombre_responsable_nuevo_evento_patrocinio,
             tv_apellido_responsable_nuevo_evento_patrocinio,tv_codigo_area_responsable_nuevo_evento_patrocinio,
             tv_telefono_responsable_nuevo_evento_patrocinio,tv_direccion_responsable_nuevo_evento_patrocinio,
             tv_barrio_responsable_nuevo_evento_patrocinio,tv_correo_responsable_nuevo_evento_patrocinio,
             tv_referencia_responsable_nuevo_evento_patrocinio;



    EditText et_nombre_nuevo_evento_patrocinio_supervisor, et_direccion_nuevo_evento_patrocinio_supervisor,
            et_barrio_nuevo_evento_patrocinio_supervisor, et_referencia_nuevo_evento_patrocinio_supervisor,
            et_fecha_inicio_nuevo_evento_patrocinio_supervisor, et_fecha_fin_nuevo_evento_patrocinio_supervisor;



    Button btn_confirmar_nuevo_evento_patrocinio_supervisor, btn_cancelar_nuevo_evento_patrocinio_supervisor;





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








        linearlayout_vertical_datos_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llv_datos_nuevo_evento_patrocinio);
        linearlayout_horizontal_titulo_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_titulo_nuevo_evento_patrocinio);
        linearlayout_horizontal_nombre_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_nombre_nuevo_evento_patrocinio);
        linearlayout_horizontal_direccion_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_direccion_nuevo_evento_patrocinio);
        linearlayout_horizontal_barrio_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_barrio_nuevo_evento_patrocinio);
        linearlayout_horizontal_referencia_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_referencia_nuevo_evento_patrocinio_supervisor);
        linearlayout_horizontal_fecha_inicio_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_fecha_inicio_nuevo_evento_patrocinio);
        linearlayout_horizontal_fecha_fin_nuevo_evento_patrocinio_supervisor = (LinearLayout) findViewById(R.id.llh_fecha_fin_nuevo_evento_patrocinio);


        tv_dni_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.dni_responsable_nuevo_evento_patrocinio);
        tv_nombre_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.nombre_responsable_nuevo_evento_patrocinio);
        tv_apellido_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.apellido_responsable_nuevo_evento_patrocinio);
        tv_codigo_area_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.codigo_area_responsable_nuevo_evento_patrocinio);
        tv_telefono_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.telefono_responsable_nuevo_evento_patrocinio);
        tv_direccion_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.direccion_responsable_nuevo_evento_patrocinio);
        tv_barrio_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.barrio_responsable_nuevo_evento_patrocinio);
        tv_correo_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.email_responsable_nuevo_evento_patrocinio);
        tv_referencia_responsable_nuevo_evento_patrocinio = (TextView) findViewById(R.id.referencia_responsable_nuevo_evento_patrocinio);





        et_nombre_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_nombre_nuevo_evento_patrocinio);
        et_direccion_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_direccion_nuevo_evento_patrocinio);
        et_barrio_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_barrio_nuevo_evento_patrocinio);
        et_referencia_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_referencia_nuevo_evento_patrocinio);
        et_fecha_inicio_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_fecha_inicio_nuevo_evento_patrocinio);
        et_fecha_fin_nuevo_evento_patrocinio_supervisor = (EditText) findViewById(R.id.et_fecha_fin_nuevo_evento_patrocinio);







        btn_cancelar_nuevo_evento_patrocinio_supervisor = (Button) findViewById(R.id.btn_cancelar_nuevo_evento_patrocinio);


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











        btn_confirmar_nuevo_evento_patrocinio_supervisor = (Button) findViewById(R.id.btn_confirmar_nuevo_evento_patrocinio);

        btn_confirmar_nuevo_evento_patrocinio_supervisor.setOnClickListener(new View.OnClickListener() {
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

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));






            linearlayout_vertical_datos_nuevo_evento_patrocinio_supervisor.setBackgroundColor(Color.parseColor("#0277bd"));

            linearlayout_horizontal_nombre_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_direccion_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_barrio_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_referencia_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_fecha_inicio_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));
            linearlayout_horizontal_fecha_fin_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.contenedor_et_nuevo_evento_patrocinio));


            btn_confirmar_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.btn_borde_redondeado));
            btn_confirmar_nuevo_evento_patrocinio_supervisor.getBackground().setColorFilter(Color.parseColor("#00bcd4"), PorterDuff.Mode.SRC_ATOP);


            btn_cancelar_nuevo_evento_patrocinio_supervisor.setBackground(getDrawable(R.drawable.btn_borde_redondeado));
            btn_cancelar_nuevo_evento_patrocinio_supervisor.getBackground().setColorFilter(Color.parseColor("#283593"), PorterDuff.Mode.SRC_ATOP);



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
