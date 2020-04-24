package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class RealizarVentasClientesSupervisor extends AppCompatActivity {


    /*******DECLARACIÓN DE VARIABLES GLOBALES**/


    /**Variables tipo Spinner**/

    Spinner spinner_ventas;




    /** Matríz Clásica tipo Cadena */

    String [] ListaDeArticulosEnVenta =  {"Bidones", "Dispenser Plástico", "Canillas", "Dispenser Eléctrico","Envases vacíos retirados"};




    /** Variables tipo TextView*/

    TextView DNI_Cliente_Ventas;

    TextView Apellido_Cliente_Ventas;

    TextView Nombre_Cliente_Ventas;

    TextView Codigo_Area_Cliente_Ventas;

    TextView Telefono_Cliente_Ventas;

    TextView Direccion_Cliente_Ventas;

    TextView Barrio_Cliente_Ventas;

    TextView Referencia_Cliente_Ventas;

    TextView Correo_Cliente_Ventas;

    TextView Cantidad_Importe_Articulos_Ventas;

    /******Variables tipo String********/

    String ArticuloSeleccionadoAnterior;
    String Importe_Articulo;

    /**Variables tipo EditText*/

    EditText eTCantVentas;

    EditText eTEntrega;


    /** Variables tipo ImageButton*/

    ImageButton btnAgregarNuevoArticuloParaVentas;

    ImageButton buttonConfirmarVentas;



    /** Variables tipo ImageView*/

    ImageView Foto_Cliente_Ventas;

    /** Variables tipo LinearLayout*/
    LinearLayout LinearLayoutVerticalDatosPersonales,LinearLayoutVerticalVentas,LinearLayoutHorizontalContenedorSpinner,
                 LinearLayoutVerticalImporteEntregaVentas, LinearLayoutHorizontalVentas;




    /** Variables enteras*/

    int precio_bidones = 100;
    int precio_dispenser_plastico = 250;
    int precio_canillas = 70;
    int precio_dispenser_electrico = 6000;

    int Importe = 0;

    int coste_articulo = 0;

    int importe_anterior = 0;

    int coste_articulo_anterior = 0;

    /** Variables de tipo ArrayList*/

    ArrayList<LinearLayout> ArrayListArticulos = new ArrayList<LinearLayout>();





    /******************************* COMIENZO DEL onCreate() ******************************/


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_ventas_clientes_supervisor);


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






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ventas);
        toolbar.setTitle("VENTAS");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);






        Foto_Cliente_Ventas = (ImageView) findViewById(R.id.img_cliente) ;

        DNI_Cliente_Ventas  = (TextView) findViewById(R.id.dni_cliente_realizar_ventas);

        Apellido_Cliente_Ventas  = (TextView) findViewById(R.id.apellido_cliente_realizar_ventas);

        Nombre_Cliente_Ventas  = (TextView) findViewById(R.id.nombre_cliente_realizar_ventas);

        Codigo_Area_Cliente_Ventas  = (TextView) findViewById(R.id.codigo_cliente_realizar_ventas);

        Telefono_Cliente_Ventas  = (TextView) findViewById(R.id.telefono_cliente_realizar_ventas);

        Direccion_Cliente_Ventas  = (TextView) findViewById(R.id.direccion_cliente_realizar_ventas);

        Barrio_Cliente_Ventas  = (TextView) findViewById(R.id.barrio_cliente_realizar_ventas);

        Referencia_Cliente_Ventas  = (TextView) findViewById(R.id.referencia_cliente_realizar_ventas);

        Correo_Cliente_Ventas  = (TextView) findViewById(R.id.correo_cliente_realizar_ventas);




        /*Llamada a la función: */
        RecibirParametrosDeLosClientesVentasSupervisores();




        LinearLayoutVerticalDatosPersonales = (LinearLayout) findViewById(R.id.llv_datos_personales);

        LinearLayoutVerticalVentas = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

        LinearLayoutHorizontalContenedorSpinner = (LinearLayout) findViewById(R.id.llh_contenedor_spinner);

        LinearLayoutVerticalImporteEntregaVentas = (LinearLayout) findViewById(R.id.llv_importe_entrega_venta);

        LinearLayoutHorizontalVentas = (LinearLayout) findViewById(R.id.layout_horizontal_ventas);



        btnAgregarNuevoArticuloParaVentas = (ImageButton) findViewById(R.id.add_art_ventas);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticuloParaVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                importe_anterior = Importe;

                /*Llamada a la función:  */
                ValidarCamposParaAñadirNuevoArticuloVentasSupervisor(LinearLayoutVerticalVentas);



            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/








        spinner_ventas = (Spinner)findViewById(R.id.sp_art_ventas);



        eTCantVentas = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas);

        eTCantVentas.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




        /*Llamada a la función: */

        setSpinner(spinner_ventas,true);








        buttonConfirmarVentas = (ImageButton) findViewById(R.id.img_button_confirmar_ventas);



        buttonConfirmarVentas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ValidacionDeCamposAntesDeConfirmarVenta();

                GuardarVentasSupervisoresEnSharedPreferences();

            }
        });




        eTEntrega = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas);

        eTEntrega.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        Cantidad_Importe_Articulos_Ventas = (TextView) findViewById(R.id.cantidad_importe_articulos_ventas);



        /*Llamada a la función: */
        ActualizarColorDelCheckBoton();


        ActualizarImporte(spinner_ventas,eTCantVentas);





        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
            setSupportActionBar(toolbar);

            LinearLayoutVerticalDatosPersonales.setBackgroundColor(Color.parseColor("#283593"));

            LinearLayoutHorizontalContenedorSpinner.setBackgroundDrawable(getDrawable(R.drawable.spinner_style_ventas_repartidor));


            LinearLayoutVerticalImporteEntregaVentas.setBackgroundColor(Color.parseColor("#283593"));



        }//Fin del if



    }/*******************************FIN DEL onCreate()**************************************/



    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/
    /*********************************************************************************************/



    Boolean flag_importe = true;

    public void ActualizarImporte(Spinner spinner, EditText editText){



        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {

/*

                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL) {

                            if (s.toString().length() > 0 && s.toString().length() < 3 && s.toString().charAt(0) != '0') {


                                String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                                String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                                coste_articulo = Integer.parseInt(str_importe);

                                Importe = 0;

                                Importe = Importe + coste_articulo;

                                //Muestra solamente el costo del artículo individual más el importe del otro artículo
                                Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));


                                flag_importe = false;


                            }//FIN DEL PRIMER if

                            else {

                                flag_importe = true;


                            }//FIN DEL else

                        }//FIN DEL if

                        return false;
                    }
                });

*/


                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/



                    if (s.toString().length() > 0 && s.toString().charAt(0) != '0'){


                        String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                        String str_importe = getImporte(nombre_articulo_seleccionado,editText);

                        coste_articulo = Integer.parseInt(str_importe);

                        //Muestra solamente el costo del artículo individual
                        Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(coste_articulo));

                        Importe = 0;

                        Importe = Importe + coste_articulo;

                        importe_anterior = Importe;



                    }

                    else{

                        Importe = 0;

                        Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));


                    }





            }


        });





/*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                if(editText.getText().toString().length() > 0 && editText.getText().toString().charAt(0) != '0' ){


                    String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                    String str_importe = getImporte(nombre_articulo_seleccionado,editText);

                    coste_articulo = Integer.parseInt(str_importe);

                    //Muestra solamente el costo del artículo individual
                    Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(coste_articulo));

                    int Importe_Actual = coste_articulo - Importe;

                    Importe = Importe + Importe_Actual;

                }

                else {



                }




            }






            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }




        });

*/

        /******************************FIN DEL EVENTO onItemSelected()*****************************/

        /******************************FIN DEL EVENTO setOnItemSelectedListener()*****************************/

    }/****************** FIN DE LA FUNCIÓN ActualizarImporte() ************************/



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/



    Boolean flag_importe_programatico = true;

    public void ActualizarImporteArticulosProgramaticos(Spinner spinner, EditText editText){



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {


                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL) {

                           if (s.toString().length() > 1 && s.toString().length() < 3 && s.toString().charAt(0) != '0') {

                               if (!eTCantVentas.getText().toString().isEmpty()) {

/*
                                   String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                                   String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                                   coste_articulo = Integer.parseInt(str_importe);

                                   Importe = Importe - coste_articulo;


 */
                                   Importe = importe_anterior;

                                   flag_importe_programatico = false;


                               }//FIN DEL SEGUNDO if


                               //Si el articulo fijo no tiene valor o está vacío
                               else {

                                   Importe = 0;

                                   flag_importe_programatico = false;

                               }



                           }//FIN DEL PRIMER if


                           else {

                               flag_importe_programatico = true;

                           }//FIN DEL else


                        }//FIN DEL if

                        return false;

                    }

                });


                /************ Cuando la longitud del editText sea == '1' podrá hacer lo siguiente: **********/


                if (s.toString().length() > 0 && s.toString().length() < 2 && s.toString().charAt(0) != '0') {


                    String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                    String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                    coste_articulo = Integer.parseInt(str_importe);

                    coste_articulo_anterior = coste_articulo;

                    Importe = Importe + coste_articulo;

                    //Muestra solamente el costo del artículo individual más el importe del otro artículo
                    Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));

                    flag_importe_programatico = false;

                }





                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/


                if (flag_importe_programatico) {


                        if (s.toString().length() > 0 && s.toString().length() < 2 && s.toString().charAt(0) != '0') {


                            if (!eTCantVentas.getText().toString().isEmpty()) {


                                String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                                String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                                coste_articulo = Integer.parseInt(str_importe);

                                //Importe = coste_articulo + coste_anterior;

                                Importe = Importe + coste_articulo;

                                //Muestra solamente el costo del artículo individual más el importe del otro artículo
                                Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));


                            } else {


                                String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                                String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                                coste_articulo = Integer.parseInt(str_importe);

                                //Muestra solamente el costo del artículo individual
                                Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(coste_articulo));

                                Importe = Importe + coste_articulo;


                            }//FIN DEL else


                        }


                }//FIN DEL if (flag_importe_programatico)



                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/




                if (s.toString().isEmpty()) {


                    //Importe = 0;

                    //Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));

                }


                if (s.toString().length() > 1 && s.toString().charAt(0) != '0') {

                    //SI 's' es igual a 3



                   if (!eTCantVentas.getText().toString().isEmpty()) {


                      String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                      String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                      coste_articulo = Integer.parseInt(str_importe);

                      Importe = Importe - coste_articulo_anterior;

                      Importe = Importe + coste_articulo; //Importe por unidad

                      //Muestra solamente el costo del artículo individual más el importe del otro artículo
                      Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));

                      coste_articulo_anterior = coste_articulo;


                   }

                   //Si el campo del artículo fijo está vacío
                   else{


                       String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                       String str_importe = getImporte(nombre_articulo_seleccionado, editText);

                       coste_articulo = Integer.parseInt(str_importe);

                       Importe = coste_articulo;

                       //Muestra solamente el costo del artículo individual más el importe del otro artículo
                       Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));

                   }

                }




                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/
                /**************************************************/



            }

        });









/*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                if(editText.getText().toString().length() > 0 && editText.getText().toString().charAt(0) != '0'){


                    String nombre_articulo_seleccionado = spinner.getSelectedItem().toString();

                    String str_importe = getImporte(nombre_articulo_seleccionado,editText);

                    coste_articulo = Integer.parseInt(str_importe);

                    //Muestra solamente el costo del artículo individual
                    Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(coste_articulo));

                    int Importe_Actual = coste_articulo - Importe;

                    Importe = Importe + Importe_Actual;


                }


            }






            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }




        });
*/



    }/****************** FIN DE LA FUNCIÓN ActualizarImporteArticulosProgramaticos() ************************/




    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/



    public String getImporte(String ArticuloSeleccionadoSpinner, EditText editText){



        if (ArticuloSeleccionadoSpinner == "Bidones") {

            Importe_Articulo = String.valueOf (CalcularImporteBidones(precio_bidones,editText));

        }

        else if (ArticuloSeleccionadoSpinner == "Dispenser Plástico") {

            Importe_Articulo = String.valueOf (CalcularImporteDispenserPlastico(precio_dispenser_plastico,editText));

        }



        else if (ArticuloSeleccionadoSpinner == "Canillas") {

            Importe_Articulo = String.valueOf (CalcularImporteCanillas(precio_canillas,editText));

        }



        else if (ArticuloSeleccionadoSpinner == "Dispenser Eléctrico") {

            Importe_Articulo = String.valueOf (CalcularImporteDispenserElectrico(precio_dispenser_electrico,editText));

        }

        else if (ArticuloSeleccionadoSpinner == "Envases vacíos retirados") {

            Importe_Articulo = String.valueOf(0);

        }

        return Importe_Articulo;


    } /*************************** FIN DE LA FUNCIÓN getImporte() ******************************/




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






    public void ActualizarColorDelCheckBoton(){


        eTCantVentas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                /** Todos los CASOS posibles para los CAMPOS FIJOS del Diseño XML, haciendo referencia a la vista: eTCantVentas **/



                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);


                }

                else{


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                    Cantidad_Importe_Articulos_Ventas.setText("");



                }



                if (s.toString().isEmpty() && eTEntrega.getText().toString().length() > 0 && eTEntrega.getText().toString().charAt(0) != '0'){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);


                }


                if (s.toString().isEmpty() && eTEntrega.getText().toString().length() > 0 && eTEntrega.getText().toString().charAt(0) == '0') {

                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);


                }



                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'   && eTEntrega.getText().toString().length() > 0 && eTEntrega.getText().toString().charAt(0) == '0') {

                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }







            }/*******************************FIN DEL EVENTO afterTextChanged()******************************************/


        });/*******************************FIN DEL EVENTO addTextChangedListener()**************************************/






        eTEntrega.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                /** Todos los CASOS posibles para los CAMPOS FIJOS del Diseño XML, haciendo referencia a la vista: eTEntrega **/


                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'){



                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);

                }


                else{

                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);


                }



                if(s.toString().isEmpty() && eTCantVentas.getText().toString().length() > 0 && eTCantVentas.getText().toString().charAt(0) != 0){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);

                }


                if (s.toString().isEmpty() && eTCantVentas.getText().toString().length() > 0 && eTCantVentas.getText().toString().charAt(0) == '0'){

                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);


                }



                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'   && eTCantVentas.getText().toString().length() > 0 && eTCantVentas.getText().toString().charAt(0) == '0') {

                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }






            }/*******************************FIN DEL EVENTO afterTextChanged()******************************************/

        });/*******************************FIN DEL EVENTO addTextChangedListener()**************************************/






    }/*****************************FIN DE LA FUNCIÓN ActualizarColorDelCheckBoton()************************************/




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







    public void RecibirParametrosDeLosClientesVentasSupervisores(){


        int recibir_foto = getIntent().getIntExtra("Foto",0);
        Foto_Cliente_Ventas.setImageResource(recibir_foto);



        int recibir_dni = getIntent().getIntExtra("DNI",0);
        DNI_Cliente_Ventas.setText(String.valueOf(recibir_dni));


        String extras = getIntent().getStringExtra("Apellido");
        Apellido_Cliente_Ventas.setText(extras);


        extras = getIntent().getStringExtra("Nombre");
        Nombre_Cliente_Ventas.setText(extras);


        extras = getIntent().getStringExtra("Codigo_Area");
        Codigo_Area_Cliente_Ventas.setText(extras);

        extras = getIntent().getStringExtra("Telefono");
        Telefono_Cliente_Ventas.setText(extras);

        extras = getIntent().getStringExtra("Direccion");
        Direccion_Cliente_Ventas.setText(extras);


        extras = getIntent().getStringExtra("Barrio");
        Barrio_Cliente_Ventas.setText(extras);

        extras = getIntent().getStringExtra("Referencia");
        Referencia_Cliente_Ventas.setText(extras);


        extras = getIntent().getStringExtra("Correo");
        Correo_Cliente_Ventas.setText(extras);

        if(extras.equals("No tiene correo")){

            Correo_Cliente_Ventas.setTextColor(Color.parseColor("#263238"));

        }

    }/**********************FIN DE LA FUNCIÓN RecibirParametrosDeLosClientesVentasSupervisores()********************************/






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





    int ChildNuevoArticuloVentasSupervisores = 0;

    public void ObtenerNuevoArticuloParaVentasSupervisores(String ValorElementoSeleccionadoSpinnerProgramatico,final LinearLayout llv) {

        View NuevoArticuloInfladoVentasSupervisores;

        /*Llamada a la función: */
        NuevoArticuloInfladoVentasSupervisores = AgregarNuevoArticuloParaVentasSupervisores(llv);



        /** Instanciamos las vistas del diseño XML: "nuevo_articulo_ventas.xml" **/

        final LinearLayout LinearLayoutHorizontalContenedorSpinnerProgramatico = (LinearLayout) findViewById(R.id.llh_contenedor_spinner_nuevo_articulo_supervisor);


        final ImageButton btnEliminarArticuloVentas = (ImageButton) NuevoArticuloInfladoVentasSupervisores.findViewById(R.id.delete_art_ventas_supervisor);


        final EditText EditText_Cantidad_Nuevo_Articulo_Ventas = (EditText) NuevoArticuloInfladoVentasSupervisores.findViewById(R.id.edtx_cantidad_ventas_new_art_supervisor);
        EditText_Cantidad_Nuevo_Articulo_Ventas.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        final Spinner spinner_nuevos_articulos_ventas_supervisores = (Spinner) NuevoArticuloInfladoVentasSupervisores.findViewById(R.id.sp_new_art_ventas_supervisor);


        /* Llamada a la función: */
        ActualizarImporteArticulosProgramaticos(spinner_nuevos_articulos_ventas_supervisores,EditText_Cantidad_Nuevo_Articulo_Ventas);


        EditText_Cantidad_Nuevo_Articulo_Ventas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.toString().isEmpty()){

                    if(!eTCantVentas.getText().toString().isEmpty()){

                                            //en este caso la variable "coste_articulo" conserva su ultimo valor
                        Importe = Importe - coste_articulo;

                        //Muestra solamente el costo del artículo individual más el importe del otro artículo
                        Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));

                        /*
                        String nombre_articulo_seleccionado = spinner_ventas.getSelectedItem().toString();

                        String str_importe = getImporte(nombre_articulo_seleccionado, eTCantVentas);

                        coste_articulo = Integer.parseInt(str_importe);

                        Importe = Importe + coste_articulo;

                        //Muestra solamente el costo del artículo individual más el importe del otro artículo
                        Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));


                         */

                    }




                }//FIN DEL PRIMER if



                /***************************************************************************/
                /***************************************************************************/
                /***************************************************************************/
                /***************************************************************************/
                /***************************************************************************/





                if(s.toString().length() > 0 && s.toString().charAt(0) != '0'){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);



                } else {


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }





            }/*******************************FIN DEL EVENTO afterTextChanged()******************************************/


        });/*******************************FIN DEL EVENTO addTextChangedListener()**************************************/






        eTCantVentas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



                if(s.toString().isEmpty()){


                    if(!EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty()){

                        String nombre_articulo_seleccionado = spinner_nuevos_articulos_ventas_supervisores.getSelectedItem().toString();

                        String str_importe = getImporte(nombre_articulo_seleccionado, EditText_Cantidad_Nuevo_Articulo_Ventas);

                        coste_articulo = Integer.parseInt(str_importe);

                        Importe = Importe + coste_articulo;

                        Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));


                    }


                }//FIN DEL PRIMER if

                else {

                    //Aquí recibo si o si el importe del artículo fijo

                    if(!EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty()){

                        String nombre_articulo_seleccionado = spinner_nuevos_articulos_ventas_supervisores.getSelectedItem().toString();

                        String str_importe = getImporte(nombre_articulo_seleccionado, EditText_Cantidad_Nuevo_Articulo_Ventas);

                        coste_articulo = Integer.parseInt(str_importe);

                        Importe = Importe + coste_articulo;

                        Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(Importe));

                    }




                }







                /***************************************************************************/
                /***************************************************************************/
                /***************************************************************************/
                /***************************************************************************/
                /***************************************************************************/



                if(!s.toString().isEmpty() && EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }

                if(s.toString().isEmpty() && EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }



            }
        });









        eTEntrega.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(!s.toString().isEmpty() && EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }

                if(s.toString().isEmpty() && EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }



            }
        });





        /*Llamada a la función: */
        setSpinner(spinner_nuevos_articulos_ventas_supervisores,false);


        ArticuloSeleccionadoAnterior = spinner_nuevos_articulos_ventas_supervisores.getSelectedItem().toString();


        spinner_nuevos_articulos_ventas_supervisores.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerProgramatico,spinner_nuevos_articulos_ventas_supervisores));





        if (ValorElementoSeleccionadoSpinnerProgramatico != ""){


            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner_nuevos_articulos_ventas_supervisores,ArticuloSeleccionadoAnterior,ValorElementoSeleccionadoSpinnerProgramatico, this);


        } else{

            Utils_Spinner.contador_de_inicializacion = 0;

            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner_nuevos_articulos_ventas_supervisores,null,ArticuloSeleccionadoAnterior, this);


        }





        btnEliminarArticuloVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EliminarNuevoArticuloVentasSupervisores(v,spinner_nuevos_articulos_ventas_supervisores);



            }
        });






        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){


            LinearLayoutHorizontalContenedorSpinnerProgramatico.setBackgroundDrawable(getDrawable(R.drawable.spinner_style_ventas_repartidor));



        }//Fin del if





    } /**************************************FIN DE LA FUNCIÓN ObtenerNuevoArticuloParaVentasSupervisores()*******************************************/






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


    public View AgregarNuevoArticuloParaVentasSupervisores(final LinearLayout LayoutVertical) {





        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedViewVentas = inflater.inflate(R.layout.nuevo_articulo_ventas_supervisor, null, true);

        LayoutVertical.addView(InflatedViewVentas);

        ChildNuevoArticuloVentasSupervisores = LayoutVertical.getChildCount();




        return InflatedViewVentas;




    } /***************************FIN DE LA FUNCIÓN AgregarNuevoArticuloParaVentasSupervisores()************************************/




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




    public void EliminarNuevoArticuloVentasSupervisores(View btnEliminarNuevoArticuloVentasSupervisores,Spinner spinner) {



        String ArticuloSeleccionado = null;



        /*Llamada a la función: */
        ImageButton btnAgregarNuevoArticulo = Utils_Spinner.ObtenerBotonParaAgregarNuevosArticulos(btnEliminarNuevoArticuloVentasSupervisores);

        btnAgregarNuevoArticulo.setEnabled(true);




        /**************************************************************************************************/

        ArticuloSeleccionado = spinner.getSelectedItem().toString();







        /*Llamada a la función: */
        Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner,ArticuloSeleccionado,null, this);







        final LinearLayout HijoLinearLayoutHorizontalNuevosArticulos = (LinearLayout) btnEliminarNuevoArticuloVentasSupervisores.getParent();

        final LinearLayout llv_padre = (LinearLayout) HijoLinearLayoutHorizontalNuevosArticulos.getParent();

        llv_padre.removeView(HijoLinearLayoutHorizontalNuevosArticulos);




        buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);



    } /*************************************FIN DE LA FUNCIÓN EliminarNuevoArticuloVentasSupervisores()***********************************************/







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




    Boolean bandera_boleana = false;

    public void ValidarCamposParaAñadirNuevoArticuloVentasSupervisor(LinearLayout LinearLayout){


            if (!eTCantVentas.getText().toString().isEmpty()){

                bandera_boleana = true;

            }



            if(eTCantVentas.getText().toString().isEmpty()){

                Toast.makeText(getApplicationContext(), "Recuerde! Para añadir un nuevo artículo debe completar el campo 'Cantidad' con valores correctos.", Toast.LENGTH_LONG).show();

                bandera_boleana = false;



            }



            /** Segunda Validación: Si el campo contiene en el primer dígito  un cero o se trata
             *  de guardar valores nulos se arrojará un mensaje de error */

            if (eTCantVentas.getText().toString().length() > 0 && eTCantVentas.getText().toString().charAt(0) == '0'){

                Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                        " dígito comience con cero ", Toast.LENGTH_LONG).show();

                bandera_boleana = false;
            }




            /**VALIDACIÓN DEL CAMPO DE CANTIDAD PARA EL NUEVO ARTICULO**/


            final LinearLayout llv_ventas_supervisor = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

            for(int j = 1 ; j < llv_ventas_supervisor.getChildCount() ; j++){

                final LinearLayout llh_nuevo_articulo_ventas_supervisor = (LinearLayout) llv_ventas_supervisor.getChildAt(j);

                final EditText et_cantidad_del_nuevo_articulo_ventas_supervisor = (EditText) llh_nuevo_articulo_ventas_supervisor.findViewById(R.id.edtx_cantidad_ventas_new_art_supervisor);


                /** Primer Validación: Si el campo ya mencionado contiene un valor, entonces se guardarán los cambios. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!et_cantidad_del_nuevo_articulo_ventas_supervisor.getText().toString().isEmpty()){

                    bandera_boleana = true;


                }


                if (et_cantidad_del_nuevo_articulo_ventas_supervisor.getText().toString().isEmpty()) {


                    bandera_boleana = false;

                    Toast.makeText(getApplicationContext(), "Error! el campo del nuevo artículo añadido está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


                }

                /** Segunda Validación: Si el campo fijo (eTCantVentas) esta vacío pero el campo programático tiene un valor cargado, entonces deberá arrojar un mensaje
                 * de error.
                 */


                if (!et_cantidad_del_nuevo_articulo_ventas_supervisor.getText().toString().isEmpty() && eTCantVentas.getText().toString().isEmpty()) {

                    bandera_boleana = false;

                    Toast.makeText(getApplicationContext(), "Error! el primer campo de los artículos a la venta está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


                }



                /** Tercera Validación: Si el campo contiene en el primer dígito un cero o se trata
                 *  de guardar valores nulos se arrojará un mensaje de error */

                if (et_cantidad_del_nuevo_articulo_ventas_supervisor.getText().toString().length() > 0 && et_cantidad_del_nuevo_articulo_ventas_supervisor.getText().toString().charAt(0) == '0'){

                    Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                            " dígito comience con cero ", Toast.LENGTH_LONG).show();

                    bandera_boleana = false;
                }




            } //Fin del for






        if (bandera_boleana) {


            flag_importe_programatico = true;

            /*Llamar a la función*/
            ObtenerNuevoArticuloParaVentasSupervisores("",LinearLayout);


            buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

            Toast.makeText(getApplicationContext(), "Nuevo artículo añadido", Toast.LENGTH_LONG).show();

        }


    }/*************************************FIN DE LA FUNCIÓN ValidarCamposParaAñadirNuevoArticuloVentasSupervisor()***********************************************/





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







    public void setSpinner(final Spinner spinner,boolean EsSpinnerFijo) {


        /*********************************************************************************************************/
        ArrayAdapter<String> adaptador;


        if(EsSpinnerFijo){



            adaptador = new ArrayAdapter<String>(this, R.layout.spinner, ListaDeArticulosEnVenta);




        }

        else{



            ArrayList<String> ArticulosReducidos = new ArrayList<String>(Arrays.asList(ListaDeArticulosEnVenta));


            /*Llamada a la función: */
            ArticulosReducidos = Utils_Spinner.ReducirListaDeArticulosDeSpinnerConBordes(spinner, ArticulosReducidos);


            adaptador = new ArrayAdapter<String>(this, R.layout.spinner, ArticulosReducidos);







            /** Si el último artículo programático contiene en su lista un único elemento, entonces se llama a la función:  */
            if(ArticulosReducidos.size() == 1){


                ImageButton btnAgregarNuevoArticulo = Utils_Spinner.ObtenerBotonParaAgregarNuevosArticulos_SpinnerConBordes(spinner);

                btnAgregarNuevoArticulo.setEnabled(false);

            } //Fin del if




        } //Fin de else





        /******************************************************************************************/


        spinner.setAdapter(adaptador);



        final String ArticuloSeleccionado = spinner.getSelectedItem().toString();




        /*Llamada a la función: */
        Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner ,null, ArticuloSeleccionado,this);




        /*************************************************************************/



        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                ArticuloSeleccionadoAnterior = spinner.getSelectedItem().toString();



                return false;

            } /**Fin del evento onTouch() */

        }); /**Fin del evento setOnTouchListener() */


        /****************************************************************************/



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(Utils_Spinner.contador_de_inicializacion < Utils_Spinner.contador_spinner){

                    Utils_Spinner.contador_de_inicializacion++;

                } //Fin del if


                else {

                    String text = spinner.getSelectedItem().toString();

                    Toast to = Toast.makeText(getApplicationContext(), "Ha seleccionado " + text, Toast.LENGTH_LONG);
                    to.show();



                    /*Llamada a la función: */
                    Utils_Spinner.RefrescarOtrosSpinnerConBordes((Spinner)adapterView,ArticuloSeleccionadoAnterior,text, RealizarVentasClientesSupervisor.this);




                }//Fin del else





            }/******************************FIN DEL EVENTO onItemSelected()*****************************/





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }





        });/******************************FIN DEL EVENTO setOnItemSelectedListener()*****************************/

    }   /******************************FIN DE LA FUNCIÓN setSpinner()*****************************/




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


    boolean flag_validacion_campos_confirmacion_ventas = false;

    /**FUNCION PARA VALIDAR SI LOS CAMPOS DE TEXTOS CONTIENEN ALGÚN VALOR**/

    @SuppressLint("RestrictedApi")
    public boolean ValidacionDeCamposAntesDeConfirmarVenta() {


        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {




                /** Primer Validación: Si al menos uno de los campos contiene un valor se confirmará la venta. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!eTCantVentas.getText().toString().isEmpty() || !eTEntrega.getText().toString().isEmpty()){

                    flag_validacion_campos_confirmacion_ventas = true;

                }



                if (eTCantVentas.getText().toString().isEmpty() && eTEntrega.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Error! Los campos estan vacios. Por favor, complete al menos un campo",Toast.LENGTH_LONG).show();


                    flag_validacion_campos_confirmacion_ventas = false;


                }






                /** Segunda Validación: Si uno de los campos contienen en el primer dígito  un cero o se trata
                 *  de guardar valores nulos, entonces se arrojará un mensaje de error */

                if (eTCantVentas.getText().toString().length() > 0 && eTCantVentas.getText().toString().charAt(0) == '0'
                        || eTEntrega.getText().toString().length() > 0 && eTEntrega.getText().toString().charAt(0) == '0'){

                    Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos" +
                            " o que el primer dígito comience con cero ", Toast.LENGTH_LONG).show();

                    flag_validacion_campos_confirmacion_ventas = false;


                }







                /**VALIDACIÓN DE CAMPOS DE CANTIDAD DE ARTÍCULO Y LA ENTREGA PARA LOS NUEVOS ARTICULOS**/

                final LinearLayout llv_ventas_supervisor = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

                for(int l = 1 ; l < llv_ventas_supervisor.getChildCount() ; l++){

                    final LinearLayout llh_nuevo_articulo_ventas_supervisores = (LinearLayout) llv_ventas_supervisor.getChildAt(l);

                    final EditText et_cantidad_venta_del_nuevo_articulo_ventas_supervisor = (EditText) llh_nuevo_articulo_ventas_supervisores.findViewById(R.id.edtx_cantidad_ventas_new_art_supervisor);



                    /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                     * se arrojará un mensaje de error*/

                    if (!et_cantidad_venta_del_nuevo_articulo_ventas_supervisor.getText().toString().isEmpty()){

                        flag_validacion_campos_confirmacion_ventas = true;
                    }


                    if (et_cantidad_venta_del_nuevo_articulo_ventas_supervisor.getText().toString().isEmpty()) {

                        flag_validacion_campos_confirmacion_ventas = false;

                        Toast.makeText(getApplicationContext(), "Error! el campo del nuevo artículo añadido está vacío. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();


                    }



                    /** Segunda Validación: Si el campo fijo (eTCantVentas) esta vacío pero el campo programático tiene un valor cargado, entonces deberá arrojar un mensaje
                     * de error.
                     */


                    if (!et_cantidad_venta_del_nuevo_articulo_ventas_supervisor.getText().toString().isEmpty() && eTCantVentas.getText().toString().isEmpty()) {

                        flag_validacion_campos_confirmacion_ventas = false;

                        Toast.makeText(getApplicationContext(), "Error! el primer campo de los artículos a la venta está vaciío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


                    }





                    /** Tercera Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                     *  de guardar valores nulos se arrojará un mensaje de error */


                    if (et_cantidad_venta_del_nuevo_articulo_ventas_supervisor.getText().toString().length() > 0 && et_cantidad_venta_del_nuevo_articulo_ventas_supervisor.getText().toString().charAt(0) == '0'){

                        Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                                " dígito comience con cero ", Toast.LENGTH_LONG).show();

                        flag_validacion_campos_confirmacion_ventas = false;


                    }






                } //Fin del for del "l" ("Articulos Programáticos")







            if (flag_validacion_campos_confirmacion_ventas) {


                Toast.makeText(getApplicationContext(),"Venta realizada con éxito",Toast.LENGTH_LONG).show();

                finish();

            } //Fin del if (flag){}


        } /*Fin del primer 'for'*/

        return flag_validacion_campos_confirmacion_ventas;

    } /*********************************FIN DE LA FUNCION ValidacionDeCamposAntesDeConfirmarVenta() ****************************/






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



    /****FUNCION PARA GUARDAR LOS CAMBIOS MEDIANTE UN SharedPreferences***/


    //AGREGAR/GUARDAR FECHA EN EL MOMENTO DE LA VENTA
    //ID DEL SUPERVISOR

    public void GuardarVentasSupervisoresEnSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences("Datos", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();





            int tope_de_articulos = 0;

            final Spinner spinner_fijo_ventas_supervisores = (Spinner) findViewById(R.id.sp_art_ventas);

            final EditText editText_cantidad_articulo_fijo_supervisores = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas);

            final EditText editText_entrega_venta_supervisores = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas);




            String ArticuloSeleccionado =  spinner_fijo_ventas_supervisores.getSelectedItem().toString();

            editor.putString("Articulo_Seleccionado", ArticuloSeleccionado);

            editor.putString("Cantidad_Articulo", editText_cantidad_articulo_fijo_supervisores.getText().toString());

            editor.putString("Entrega_Venta_Supervisor", editText_entrega_venta_supervisores.getText().toString());




            final LinearLayout llv_ventas_supervisores = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

            tope_de_articulos = llv_ventas_supervisores.getChildCount()-1;




            for(int j = 1 ; j < llv_ventas_supervisores.getChildCount() ; j++){



                final LinearLayout llh_nuevo_articulo_ventas_supervisor = (LinearLayout) llv_ventas_supervisores.getChildAt(j);

                final Spinner spinner_nuevos_articulos_ventas_supervisor = (Spinner) llh_nuevo_articulo_ventas_supervisor.findViewById(R.id.sp_new_art_ventas_supervisor);

                final EditText et_carga_del_nuevo_articulo_venta_supervisor = (EditText) llh_nuevo_articulo_ventas_supervisor.findViewById(R.id.edtx_cantidad_ventas_new_art_supervisor);



                String ArticuloSeleccionadoSpinnerProgramatico =  spinner_nuevos_articulos_ventas_supervisor.getSelectedItem().toString();

                editor.putString("Nuevo Articulo Seleccionado - Posicion: " + j, ArticuloSeleccionadoSpinnerProgramatico);

                editor.putString("Cantidad Nuevo Articulo - Posicion: " + j, et_carga_del_nuevo_articulo_venta_supervisor.getText().toString());


            } //Fin del for del "j" ("Articulos Programáticos")







    }/*******************************FIN DE LA FUNCION GuardarVentasSupervisoresEnSharedPreferences()******************************/




    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/




    public int CalcularImporteBidones(int precio_bidones, EditText editText) {

        int cantidad_bidones =Integer.parseInt(editText.getText().toString());

        int importe_bidones = cantidad_bidones * precio_bidones;

        return importe_bidones;

    }




    public int CalcularImporteDispenserPlastico(int precio_dispenser_plastico,EditText editText) {


        int cantidad_dispenser_plastico = Integer.parseInt(editText.getText().toString());


        int importe_dispenser_plastico = cantidad_dispenser_plastico * precio_dispenser_plastico;


        return importe_dispenser_plastico;
    }


    public int CalcularImporteCanillas(int precio_canillas, EditText editText) {


        int cantidad_canillas = Integer.parseInt(editText.getText().toString());


        int importe_canillas = cantidad_canillas * precio_canillas;


        return importe_canillas;

    }




    public int CalcularImporteDispenserElectrico(int precio_dispenser_electrico, EditText editText) {

        int cantidad_dispenser_electrico = Integer.parseInt(editText.getText().toString());


        int importe_dispenser_electrico = cantidad_dispenser_electrico * precio_dispenser_electrico;


        return importe_dispenser_electrico;

    }




    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/
    /**************************************************************************************************/



}/******************************* FIN DE LA ACTIVITY RealizarVentasCientes ***********************************/
