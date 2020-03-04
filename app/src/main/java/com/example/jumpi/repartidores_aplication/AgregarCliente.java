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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarCliente extends AppCompatActivity {


    /*************** DECLARACIÓN DE VARIABLES GLOBALES *****************/


    TextView tv_titulo_nuevo_cliente;

    LinearLayout llh_contenedor_apellido, llh_contenedor_nombre, llh_contenedor_direccion,
                 llh_contenedor_barrio, llh_contenedor_telefono, llh_contenedor_correo,
                 llh_contenedor_dni, llh_contenedor_referencia;

    ImageView  img_apellido, img_nombre, img_direccion, img_barrio, img_telefono, img_correo, img_dni,
               img_referencia;

    View view_apellido, view_nombre, view_direccion, view_barrio, view_telefono, view_correo, view_dni,
         view_referencia;


    EditText eTApellidoAC, eTNombreAC, eTDireccionAC, eTBarrioAC, eTTelefonoAC, eTCorreoAC,
             eTdniAC, eTReferenciaAC;

    Button buttonCancelar, buttonAgregar;









    /******************** COMIENZO DEL onCreate() **********************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);


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






        eTApellidoAC = (EditText) findViewById(R.id.apellido_cliente_agregar);
        eTApellidoAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTNombreAC = (EditText) findViewById(R.id.nombre_cliente_agregar);
        eTNombreAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTDireccionAC = (EditText) findViewById(R.id.direccion_cliente_agregar);
        eTDireccionAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTBarrioAC = (EditText) findViewById(R.id.barrio_cliente_agregar);
        eTBarrioAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTTelefonoAC = (EditText) findViewById(R.id.telefono_cliente_agregar);
        eTTelefonoAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTCorreoAC = (EditText) findViewById(R.id.correo_cliente_agregar);
        eTCorreoAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTdniAC = (EditText) findViewById(R.id.dni_cliente_agregar);
        eTdniAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTReferenciaAC = (EditText) findViewById(R.id.referencia_cliente_agregar);
        eTReferenciaAC.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));






        buttonCancelar = (Button) findViewById(R.id.button_cancelar_AC);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if(!eTApellidoAC.getText().toString().isEmpty() || !eTNombreAC.getText().toString().isEmpty()
                        || !eTDireccionAC.getText().toString().isEmpty() || !eTBarrioAC.getText().toString().isEmpty()
                        || !eTTelefonoAC.getText().toString().isEmpty() || !eTCorreoAC.getText().toString().isEmpty()
                        || !eTdniAC.getText().toString().isEmpty() ||!eTReferenciaAC.getText().toString().isEmpty()){




                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarCliente.this);
                    builder.setIcon(R.drawable.ic_msj_alerta);
                    builder.setTitle("¿Desea salir?");
                    builder.setMessage("Al parecer algunos campos del formulario no están vacios. ¿Desea cancelar la adición de un nuevo cliente?");


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







        buttonAgregar = (Button) findViewById(R.id.button_agregar_AC);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                /* Llamada a la función: */
                ValidarCamposDelFormularioAntesDeConfirmarNuevoCliente();




            }
        });




        tv_titulo_nuevo_cliente = (TextView) findViewById(R.id.tv_nuevo_cliente_titulo);


        llh_contenedor_apellido = (LinearLayout) findViewById(R.id.llh_apellido);
        llh_contenedor_nombre = (LinearLayout) findViewById(R.id.llh_nombre);
        llh_contenedor_direccion = (LinearLayout) findViewById(R.id.llh_direccion);
        llh_contenedor_barrio = (LinearLayout) findViewById(R.id.llh_barrio);
        llh_contenedor_telefono = (LinearLayout) findViewById(R.id.llh_telefono);
        llh_contenedor_correo = (LinearLayout) findViewById(R.id.llh_correo);
        llh_contenedor_dni = (LinearLayout) findViewById(R.id.llh_dni);
        llh_contenedor_referencia = (LinearLayout) findViewById(R.id.llh_referencia);


        img_apellido = (ImageView) findViewById(R.id.img_apellido_ac);
        img_nombre = (ImageView) findViewById(R.id.img_nombre_ac);
        img_direccion =(ImageView) findViewById(R.id.img_direccion_ac);
        img_barrio =(ImageView) findViewById(R.id.img_barrio_ac);
        img_telefono =(ImageView) findViewById(R.id.img_telefono_ac);
        img_correo =(ImageView) findViewById(R.id.img_correo_ac);
        img_dni =(ImageView) findViewById(R.id.img_dni_ac);
        img_referencia =(ImageView) findViewById(R.id.img_referencia_ac);


        view_apellido = (View) findViewById(R.id.view_apellido_ac);
        view_nombre = (View) findViewById(R.id.view_nombre_ac);
        view_direccion = (View) findViewById(R.id.view_direccion_ac);
        view_barrio = (View) findViewById(R.id.view_barrio_ac);
        view_telefono = (View) findViewById(R.id.view_telefono_ac);
        view_correo = (View) findViewById(R.id.view_correo_ac);
        view_dni = (View) findViewById(R.id.view_dni_ac);
        view_referencia = (View) findViewById(R.id.view_referencia_ac);







        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));

            tv_titulo_nuevo_cliente.setTextColor(Color.parseColor("#283593"));



            llh_contenedor_apellido.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_nombre.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_direccion.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_barrio.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_telefono.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_correo.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_dni.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));
            llh_contenedor_referencia.setBackground(getDrawable(R.drawable.contenedor_edittext_ac));






            img_apellido.setImageResource(R.drawable.ic_apellido_y_nombre_ac);
            img_nombre.setImageResource(R.drawable.ic_apellido_y_nombre_ac);
            img_direccion.setImageResource(R.drawable.ic_direccion_ac);
            img_barrio.setImageResource(R.drawable.ic_barrio_ac);
            img_telefono.setImageResource(R.drawable.ic_telefono_ac);
            img_correo.setImageResource(R.drawable.ic_correo_ac);
            img_dni.setImageResource(R.drawable.ic_dni_ac);
            img_referencia.setImageResource(R.drawable.ic_referencia_ac);







            view_apellido.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_nombre.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_direccion.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_barrio.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_telefono.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_correo.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_dni.setBackgroundColor(Color.parseColor("#03a9f4"));
            view_referencia.setBackgroundColor(Color.parseColor("#03a9f4"));


            buttonAgregar.setBackground(getDrawable(R.drawable.btn_borde_redondeado));
            buttonAgregar.getBackground().setColorFilter(Color.parseColor("#03a9f4"), PorterDuff.Mode.SRC_ATOP);

            buttonCancelar.setBackground(getDrawable(R.drawable.btn_borde_redondeado));
            buttonCancelar.getBackground().setColorFilter(Color.parseColor("#283593"), PorterDuff.Mode.SRC_ATOP);



        }//Fin del if









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






    boolean flag_nuevo_cliente = false;


    public boolean ValidarCamposDelFormularioAntesDeConfirmarNuevoCliente(){



        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {

            /**Primer Validación: todos los campos obligatorios deben estar rellenados**/

            if (!eTApellidoAC.getText().toString().isEmpty() && !eTNombreAC.getText().toString().isEmpty()
                    && !eTDireccionAC.getText().toString().isEmpty() && !eTBarrioAC.getText().toString().isEmpty()
                    && !eTTelefonoAC.getText().toString().isEmpty() && !eTCorreoAC.getText().toString().isEmpty()
                    && !eTdniAC.getText().toString().isEmpty() && !eTReferenciaAC.getText().toString().isEmpty()){

                flag_nuevo_cliente= true;

            }

            else{

                Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios.", Toast.LENGTH_LONG).show();


                flag_nuevo_cliente = false;

            }






            if (flag_nuevo_cliente) {


                Toast.makeText(getApplicationContext(), "¡Un nuevo cliente se ha añadido con éxito!", Toast.LENGTH_LONG).show();

                finish();

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









}/*************************** FIN DE LA ACTIVITY AgregarCliente *****************************/
