package com.example.jumpi.repartidores_aplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class EditarClientes extends AppCompatActivity {


/******************** DECLARACIÓN DE VARIABLES GLOBALES ****************************/


    LinearLayout LinearLayoutContenedorDeEditarCliente;


    TextView Recibir_DNI_Cliente_EditarCliente;
    TextView Recibir_Apellido_Cliente_EditarCliente;
    TextView Recibir_Nombre_Cliente_EditarCliente;
    TextView Recibir_Codigo_Area_Cliente_EditarCliente;
    TextView Recibir_Telefono_Cliente_EditarCliente;
    TextView Recibir_Direccion_Cliente_EditarCliente;
    TextView Recibir_Barrio_Cliente_EditarCliente;
    TextView Recibir_Correo_Cliente_EditarCliente;
    TextView Recibir_Referencia_Cliente_EditarCliente;


    TextView Titulo_EditarCliente;
    TextView Titulo_DNI_Cliente_EditarCliente;
    TextView Titulo_Apellido_Cliente_EditarCliente;
    TextView Titulo_Nombre_Cliente_EditarCliente;
    TextView Titulo_Codigo_Area_Cliente_EditarCliente;
    TextView Titulo_Telefono_Cliente_EditarCliente;
    TextView Titulo_Direccion_Cliente_EditarCliente;
    TextView Titulo_Barrio_Cliente_EditarCliente;
    TextView Titulo_Correo_Cliente_EditarCliente;
    TextView Titulo_Referencia_Cliente_EditarCliente;



    EditText eTDNI;
    EditText eTApellido;
    EditText eTNombre;
    EditText eTDireccion;
    EditText eTBarrio;
    EditText eTCodigoArea;
    EditText eTTelefono;
    EditText eTCorreo;
    EditText eTReferencia;

    ImageView Recibir_Foto_Cliente_EditarCliente;


    Button buttonCancelarEditarCliente, buttonGuardarCambiosEditarCliente;






    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_clientes);


        /**Añadir "manualmente" color al StatusBar **/
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));


        /* Para cambiar el color del puntero o "burbuja" del EditText */
        setTheme(R.style.AppTheme_CursorSupervisor);


        LinearLayoutContenedorDeEditarCliente = (LinearLayout) findViewById(R.id.llh_editar_clientes_id);
        LinearLayoutContenedorDeEditarCliente.setBackground(getDrawable(R.drawable.fondo_editar_cliente_supervisor));


        Titulo_EditarCliente = (TextView) findViewById(R.id.tv_editar_cliente_titulo);
        Typeface face0=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_EditarCliente.setTypeface(face0);


        Titulo_Apellido_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_apellido_ec);
        Typeface face1=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Apellido_Cliente_EditarCliente.setTypeface(face1);


        Titulo_Nombre_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_nombre_ec);
        Typeface face2 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Nombre_Cliente_EditarCliente.setTypeface(face2);


        Titulo_Codigo_Area_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_codigo_area_ec);
        Typeface face3 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Codigo_Area_Cliente_EditarCliente.setTypeface(face3);


        Titulo_Telefono_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_telefono_ec);
        Typeface face4 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Telefono_Cliente_EditarCliente.setTypeface(face4);


        Titulo_Direccion_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_direccion_ec);
        Typeface face5 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Direccion_Cliente_EditarCliente.setTypeface(face5);


        Titulo_Barrio_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_barrio_ec);
        Typeface face6 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Barrio_Cliente_EditarCliente.setTypeface(face6);


        Titulo_Correo_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_correo_ec);
        Typeface face7 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Correo_Cliente_EditarCliente.setTypeface(face7);


        Titulo_DNI_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_dni_ec);
        Typeface face8 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_DNI_Cliente_EditarCliente.setTypeface(face8);


        Titulo_Referencia_Cliente_EditarCliente = (TextView) findViewById(R.id.tv_referencia_ec);
        Typeface face9 = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-BoldItalic.ttf");
        Titulo_Referencia_Cliente_EditarCliente.setTypeface(face9);










        eTDNI = (EditText) findViewById(R.id.et_dni_cliente_editar);
        eTDNI.setTextColor(Color.parseColor("#ffa726"));
        eTDNI.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTApellido = (EditText) findViewById(R.id.et_apellido_cliente_editar);
        eTApellido.setTextColor(Color.parseColor("#ffa726"));
        eTApellido.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTNombre = (EditText) findViewById(R.id.et_nombre_cliente_editar);
        eTNombre.setTextColor(Color.parseColor("#ffa726"));
        eTNombre.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTCodigoArea = (EditText) findViewById(R.id.et_codigo_area_cliente_editar);
        eTCodigoArea.setTextColor(Color.parseColor("#ffa726"));
        eTCodigoArea.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTTelefono = (EditText) findViewById(R.id.et_telefono_cliente_editar);
        eTTelefono.setTextColor(Color.parseColor("#ffa726"));
        eTTelefono.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTDireccion = (EditText) findViewById(R.id.et_direccion_cliente_editar);
        eTDireccion.setTextColor(Color.parseColor("#ffa726"));
        eTDireccion.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTBarrio = (EditText) findViewById(R.id.et_barrio_cliente_editar);
        eTBarrio.setTextColor(Color.parseColor("#ffa726"));
        eTBarrio.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTCorreo = (EditText) findViewById(R.id.et_correo_cliente_editar);
        eTCorreo.setTextColor(Color.parseColor("#ffa726"));
        eTCorreo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTReferencia = (EditText) findViewById(R.id.et_referencia_cliente_editar);
        eTReferencia.setTextColor(Color.parseColor("#ffa726"));
        eTReferencia.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




        /*Llamada a la función: */
        RecibirDatosPersonalesDelClienteEC();



        buttonGuardarCambiosEditarCliente = (Button) findViewById(R.id.button_guardar_cambios_ec);
        buttonGuardarCambiosEditarCliente.setBackground(getDrawable(R.drawable.editar_cliente_btn_guardar_cambios_supervisor));

        buttonGuardarCambiosEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                /*Llamada a la función: */
                ValidarCamposParaGuardarCambiosEditarCliente();



            }
        });






        buttonCancelarEditarCliente = (Button) findViewById(R.id.button_cancelar_ec);

        buttonCancelarEditarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();

            }
        });





        Usuario usuario = new Usuario();
        usuario.LeerUsuarioEnUnSharedPreferences(this);


        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            /* Para cambiar el color del puntero o "burbuja" del EditText */
            setTheme(R.style.AppTheme_CursorRepartidor);

            LinearLayoutContenedorDeEditarCliente.setBackground(getDrawable(R.mipmap.background_editar_cliente_repartidor));


            buttonGuardarCambiosEditarCliente.setBackground(getDrawable(R.drawable.editar_cliente_btn_guardar_cambios_repartidor));


            Field f = null;

            try {

                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                f.setAccessible(true);
                f.set(eTDNI, R.drawable.color_cursor_repartidores);
                f.set(eTApellido, R.drawable.color_cursor_repartidores);
                f.set(eTNombre, R.drawable.color_cursor_repartidores);
                f.set(eTCodigoArea, R.drawable.color_cursor_repartidores);
                f.set(eTTelefono, R.drawable.color_cursor_repartidores);
                f.set(eTDireccion, R.drawable.color_cursor_repartidores);
                f.set(eTBarrio, R.drawable.color_cursor_repartidores);
                f.set(eTCorreo, R.drawable.color_cursor_repartidores);
                f.set(eTReferencia, R.drawable.color_cursor_repartidores);



            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }



            eTDNI.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTApellido.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTNombre.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTCodigoArea.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTTelefono.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTDireccion.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTCorreo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));
            eTReferencia.setBackgroundDrawable(getDrawable(R.drawable.edit_text_underline_color_repartidor));


        }// FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))








    }/***************************** FIN DEL onCreate()*******************************/







    public void RecibirDatosPersonalesDelClienteEC(){


         Recibir_Foto_Cliente_EditarCliente  = (ImageView) findViewById(R.id.img_cliente_editar);

         int recibir_foto = getIntent().getIntExtra("Foto",0);

         Recibir_Foto_Cliente_EditarCliente.setImageResource(recibir_foto);



         Recibir_DNI_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_dni_cliente_editar);

         int extras_dni = getIntent().getIntExtra("DNI",0);

         Recibir_DNI_Cliente_EditarCliente.setText(String.valueOf(extras_dni));




         Recibir_Apellido_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_apellido_cliente_editar);

         String extrasTextos = getIntent().getStringExtra("Apellido");

         Recibir_Apellido_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Nombre_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_nombre_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Nombre");

         Recibir_Nombre_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Codigo_Area_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_codigo_area_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Codigo_Area");

        Recibir_Codigo_Area_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Telefono_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_telefono_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Telefono");

         Recibir_Telefono_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Direccion_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_direccion_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Direccion");

         Recibir_Direccion_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Barrio_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_barrio_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Barrio");

         Recibir_Barrio_Cliente_EditarCliente.setText(extrasTextos);



         Recibir_Correo_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_correo_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Correo");

         Recibir_Correo_Cliente_EditarCliente.setText(extrasTextos);




         Recibir_Referencia_Cliente_EditarCliente  = (TextView) findViewById(R.id.et_referencia_cliente_editar);

         extrasTextos = getIntent().getStringExtra("Referencia");

         Recibir_Referencia_Cliente_EditarCliente.setText(extrasTextos);


    }/**********************FIN DE LA FUNCIÓN RecibirDatosPersonalesDelClienteEC()********************************/










    boolean flag_validacion_campos_editar_cliente = false;

    public boolean ValidarCamposParaGuardarCambiosEditarCliente(){


        String AuxiliarApellidoE = eTApellido.getText().toString();

        String AuxiliarNombreE = eTNombre.getText().toString();

        String AuxiliarDireccionE = eTDireccion.getText().toString();

        String AuxiliarBarrioE = eTBarrio.getText().toString();

        String AuxiliarTelefonoE = eTTelefono.getText().toString();

        String AuxiliarCorreoE = eTCorreo.getText().toString();

        String AuxiliarDNIE = eTDNI.getText().toString();

        String AuxiliarReferenciaE = eTReferencia.getText().toString();




        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {

        /**Primer Validación: todos los campos obligatorios deben estar rellenados**/

            if (!AuxiliarApellidoE.isEmpty() && !AuxiliarNombreE.toString().isEmpty() && !AuxiliarDireccionE.toString().isEmpty()
                && !AuxiliarBarrioE.toString().isEmpty() && !AuxiliarTelefonoE.toString().isEmpty()
                && !AuxiliarDNIE.toString().isEmpty() && !AuxiliarReferenciaE.toString().isEmpty() ){

            flag_validacion_campos_editar_cliente = true;

            }

            else{

                Toast.makeText(getApplicationContext(), "¡Error! Recuerde completar todos los campos que sean obligatorios.", Toast.LENGTH_LONG).show();


                flag_validacion_campos_editar_cliente = false;

            }




        if (flag_validacion_campos_editar_cliente) {

            finish();

            Toast.makeText(getApplicationContext(), "¡Los cambios fueron realizados con éxito!", Toast.LENGTH_LONG).show();


        } //Fin del if (flag_validacion_campos_editar_cliente){}





        } /*Fin del 'for'*/

        return flag_validacion_campos_editar_cliente;





}/********** FIN DE LA FUNCIÓN ValidarCamposParaGuardarCambiosEditarCliente() ***********/














}/******************************** FIN DE LA ACTIVITY EditarClientes *******************************/
