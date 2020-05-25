package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class RealizarVentasClientesRepartidor extends AppCompatActivity {





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
    Integer Importe_Articulo;

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
    int importe_anterior = 0;


    Integer importeDespuesDeModificacion = 0;
    Integer importeAntesDeModificacion = 0;

    int contador_de_inicializacion = 0;






    /******************************* COMIENZO DEL onCreate() ******************************/


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_ventas_clientes_repartidor);





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
                ValidarCamposParaAñadirNuevoArticuloVentasRepartidor(LinearLayoutVerticalVentas);



            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/








        spinner_ventas = (Spinner)findViewById(R.id.sp_art_ventas);





        eTCantVentas = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas);

        eTCantVentas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                if (!s.toString().equals("")){

                    Integer cantidadAntesDeModificacion = Integer.parseInt(s.toString());

                    importeAntesDeModificacion = getImporte(spinner_ventas.getSelectedItem().toString(), cantidadAntesDeModificacion);

                }else{

                    importeAntesDeModificacion = 0;

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().equals("")){

                    Integer cantidadDespuesDeModificación = Integer.parseInt(s.toString());

                    importeDespuesDeModificacion = getImporte(spinner_ventas.getSelectedItem().toString(), cantidadDespuesDeModificación);

                    ActualizarImporte();

                }

                else{


                    importeDespuesDeModificacion = 0;

                    ActualizarImporte();

                }


            }

        });







        /*Llamada a la función: */
        setSpinner(spinner_ventas,true, eTCantVentas);











        buttonConfirmarVentas = (ImageButton) findViewById(R.id.img_button_confirmar_ventas);



        buttonConfirmarVentas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ValidacionDeCamposAntesDeConfirmarVenta();

                GuardarVentasRepartidoresEnSharedPreferences();

            }
        });




        eTEntrega = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas);




        Cantidad_Importe_Articulos_Ventas = (TextView) findViewById(R.id.cantidad_importe_articulos_ventas);



        /*Llamada a la función: */
        ActualizarColorDelCheckBoton();







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





    public void ActualizarImporte(){

        String cadena = Cantidad_Importe_Articulos_Ventas.getText().toString();


        if(!cadena.equals("IMPORTE") && !cadena.equals("")){


            Integer importe = Integer.parseInt(Cantidad_Importe_Articulos_Ventas.getText().toString());

            importe = importe - importeAntesDeModificacion + importeDespuesDeModificacion;
            Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(importe));
        }
        else {


            Integer importe = 0;

            importe = importe - importeAntesDeModificacion + importeDespuesDeModificacion;

            Cantidad_Importe_Articulos_Ventas.setText(String.valueOf(importe));


        }









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







    public Integer getImporte(String ArticuloSeleccionadoSpinner, Integer cantidadArticulo){



        if (ArticuloSeleccionadoSpinner == "Bidones") {

            Importe_Articulo = CalcularImporteBidones(precio_bidones,cantidadArticulo);

        }

        else if (ArticuloSeleccionadoSpinner == "Dispenser Plástico") {

            Importe_Articulo = CalcularImporteDispenserPlastico(precio_dispenser_plastico,cantidadArticulo);

        }



        else if (ArticuloSeleccionadoSpinner == "Canillas") {

            Importe_Articulo = CalcularImporteCanillas(precio_canillas,cantidadArticulo);

        }



        else if (ArticuloSeleccionadoSpinner == "Dispenser Eléctrico") {

            Importe_Articulo = CalcularImporteDispenserElectrico(precio_dispenser_electrico,cantidadArticulo);

        }

        else if (ArticuloSeleccionadoSpinner == "Envases vacíos retirados") {

            Importe_Articulo = 0;

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





    public int CalcularImporteBidones(int precio_bidones, Integer cantidadArticulo) {



        int importe_bidones = cantidadArticulo * precio_bidones;

        return importe_bidones;

    }




    public int CalcularImporteDispenserPlastico(int precio_dispenser_plastico,Integer cantidadArticulo) {





        int importe_dispenser_plastico = cantidadArticulo * precio_dispenser_plastico;


        return importe_dispenser_plastico;
    }


    public int CalcularImporteCanillas(int precio_canillas, Integer cantidadArticulo) {





        int importe_canillas = cantidadArticulo * precio_canillas;


        return importe_canillas;

    }




    public int CalcularImporteDispenserElectrico(int precio_dispenser_electrico, Integer cantidadArticulo) {




        int importe_dispenser_electrico = cantidadArticulo * precio_dispenser_electrico;


        return importe_dispenser_electrico;

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





    int ChildNuevoArticuloVentasRepartidores = 0;

    public void ObtenerNuevoArticuloParaVentasRepartidores(String ValorElementoSeleccionadoSpinnerProgramatico,final LinearLayout llv) {

        View NuevoArticuloInfladoVentasRepartidores;

        /*Llamada a la función: */
        NuevoArticuloInfladoVentasRepartidores = AgregarNuevoArticuloParaVentasRepartidores(llv);



        /** Instanciamos las vistas del diseño XML: "nuevo_articulo_ventas.xml" **/

        final LinearLayout LinearLayoutHorizontalContenedorSpinnerProgramatico = (LinearLayout) findViewById(R.id.llh_contenedor_spinner_nuevo_articulo_repartidor);

        final ImageButton btnEliminarArticuloVentas = (ImageButton) NuevoArticuloInfladoVentasRepartidores.findViewById(R.id.delete_art_ventas_repartidor);

        final EditText EditText_Cantidad_Nuevo_Articulo_Ventas = (EditText) NuevoArticuloInfladoVentasRepartidores.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);

        final Spinner spinner_nuevos_articulos_ventas = (Spinner) NuevoArticuloInfladoVentasRepartidores.findViewById(R.id.sp_new_art_ventas_repartidor);

        //spinner_nuevos_articulos_ventas.onTouchEvent()



        EditText_Cantidad_Nuevo_Articulo_Ventas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (!s.toString().equals("")){

                    Integer cantidadAntesDeModificacion = Integer.parseInt(s.toString());


                    importeAntesDeModificacion = getImporte(spinner_nuevos_articulos_ventas.getSelectedItem().toString(), cantidadAntesDeModificacion);

                }else{

                    importeAntesDeModificacion = 0;

                }


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().equals("")){

                    Integer cantidadDespuesDeModificación = Integer.parseInt(s.toString());

                    importeDespuesDeModificacion = getImporte(spinner_nuevos_articulos_ventas.getSelectedItem().toString(), cantidadDespuesDeModificación);

                    ActualizarImporte();

                }else{


                    importeDespuesDeModificacion = 0;
                    ActualizarImporte();
                }









                if(s.toString().length() > 0 && s.toString().charAt(0) != '0'){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);



                } else {


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }




            }

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


                if(!s.toString().isEmpty() && EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }

                if(s.toString().isEmpty() && EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }



            }
        });





        /*Llamada a la función: */
        setSpinner(spinner_nuevos_articulos_ventas,false,EditText_Cantidad_Nuevo_Articulo_Ventas);


        ArticuloSeleccionadoAnterior = spinner_nuevos_articulos_ventas.getSelectedItem().toString();


        spinner_nuevos_articulos_ventas.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerProgramatico,spinner_nuevos_articulos_ventas));





        if (ValorElementoSeleccionadoSpinnerProgramatico != ""){


            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner_nuevos_articulos_ventas,ArticuloSeleccionadoAnterior,ValorElementoSeleccionadoSpinnerProgramatico, this);


        } else{

            Utils_Spinner.contador_de_inicializacion = 0;

            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner_nuevos_articulos_ventas,null,ArticuloSeleccionadoAnterior, this);


        }





        btnEliminarArticuloVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer cantidadAntesDeModificacion = Integer.parseInt(EditText_Cantidad_Nuevo_Articulo_Ventas.getText().toString());
                importeAntesDeModificacion = getImporte(spinner_nuevos_articulos_ventas.getSelectedItem().toString(), cantidadAntesDeModificacion);
                importeDespuesDeModificacion = 0;

                ActualizarImporte();

                EliminarNuevoArticuloVentasRepartidores(v,spinner_nuevos_articulos_ventas);



            }
        });


    } /**************************************FIN DE LA FUNCIÓN ObtenerNuevoArticuloParaVentasRepartidores()*******************************************/






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


    public View AgregarNuevoArticuloParaVentasRepartidores(final LinearLayout LayoutVertical) {





        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedViewVentas = inflater.inflate(R.layout.nuevo_articulo_ventas_repartidor, null, true);

        LayoutVertical.addView(InflatedViewVentas);

        ChildNuevoArticuloVentasRepartidores = LayoutVertical.getChildCount();




        return InflatedViewVentas;




    } /***************************FIN DE LA FUNCIÓN AgregarNuevoArticuloParaVentasRepartidores()************************************/




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




    public void EliminarNuevoArticuloVentasRepartidores(View btnEliminarNuevoArticuloVentasRepartidores,Spinner spinner) {



        String ArticuloSeleccionado = null;



        /*Llamada a la función: */
        ImageButton btnAgregarNuevoArticulo = Utils_Spinner.ObtenerBotonParaAgregarNuevosArticulos(btnEliminarNuevoArticuloVentasRepartidores);

        btnAgregarNuevoArticulo.setEnabled(true);




        /**************************************************************************************************/

        ArticuloSeleccionado = spinner.getSelectedItem().toString();







        /*Llamada a la función: */
        Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner,ArticuloSeleccionado,null, this);







        final LinearLayout HijoLinearLayoutHorizontalNuevosArticulos = (LinearLayout) btnEliminarNuevoArticuloVentasRepartidores.getParent();

        final LinearLayout llv_padre = (LinearLayout) HijoLinearLayoutHorizontalNuevosArticulos.getParent();

        llv_padre.removeView(HijoLinearLayoutHorizontalNuevosArticulos);




        buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);



    } /*************************************FIN DE LA FUNCIÓN EliminarNuevoArticuloVentasRepartidores()***********************************************/







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

    public void ValidarCamposParaAñadirNuevoArticuloVentasRepartidor(LinearLayout LinearLayout){


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


        final LinearLayout llv_ventas_repartidor = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

        for(int j = 1 ; j < llv_ventas_repartidor.getChildCount() ; j++){

            final LinearLayout llh_nuevo_articulo_ventas_repartidor = (LinearLayout) llv_ventas_repartidor.getChildAt(j);

            final EditText et_cantidad_del_nuevo_articulo_ventas_repartidor = (EditText) llh_nuevo_articulo_ventas_repartidor.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);


            /** Primer Validación: Si el campo ya mencionado contiene un valor, entonces se guardarán los cambios. Caso contrario
             * se arrojará un mensaje de error*/

            if (!et_cantidad_del_nuevo_articulo_ventas_repartidor.getText().toString().isEmpty()){

                bandera_boleana = true;


            }


            if (et_cantidad_del_nuevo_articulo_ventas_repartidor.getText().toString().isEmpty()) {


                bandera_boleana = false;

                Toast.makeText(getApplicationContext(), "Error! el campo del nuevo artículo añadido está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


            }

            /** Segunda Validación: Si el campo fijo (eTCantVentas) esta vacío pero el campo programático tiene un valor cargado, entonces deberá arrojar un mensaje
             * de error.
             */


            if (!et_cantidad_del_nuevo_articulo_ventas_repartidor.getText().toString().isEmpty() && eTCantVentas.getText().toString().isEmpty()) {

                bandera_boleana = false;

                Toast.makeText(getApplicationContext(), "Error! el primer campo de los artículos a la venta está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


            }



            /** Tercera Validación: Si el campo contiene en el primer dígito un cero o se trata
             *  de guardar valores nulos se arrojará un mensaje de error */

            if (et_cantidad_del_nuevo_articulo_ventas_repartidor.getText().toString().length() > 0 && et_cantidad_del_nuevo_articulo_ventas_repartidor.getText().toString().charAt(0) == '0'){

                Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                        " dígito comience con cero ", Toast.LENGTH_LONG).show();

                bandera_boleana = false;
            }




        } //Fin del for






        if (bandera_boleana) {



            /*Llamar a la función*/
            ObtenerNuevoArticuloParaVentasRepartidores("",LinearLayout);


            buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

            Toast.makeText(getApplicationContext(), "Nuevo artículo añadido", Toast.LENGTH_LONG).show();

        }


    }/*************************************FIN DE LA FUNCIÓN ValidarCamposParaAñadirNuevoArticuloVentasRepartidor()***********************************************/





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







    public void setSpinner(final Spinner spinner,boolean EsSpinnerFijo, EditText editText) {


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
                    Utils_Spinner.RefrescarOtrosSpinnerConBordes((Spinner)adapterView,ArticuloSeleccionadoAnterior,text, RealizarVentasClientesRepartidor.this);

                    //cuando entra por 2da vez a la activity salta el error debido que a que la cantidad recibe un
                    //valor vacío

                    if (!editText.getText().toString().isEmpty()){

                        Integer cantidadDeArticulo = Utils_Spinner.obtenerCantidadDeArticulo((Spinner) adapterView);
                        importeAntesDeModificacion = getImporte(ArticuloSeleccionadoAnterior, cantidadDeArticulo);
                        importeDespuesDeModificacion = getImporte(text, cantidadDeArticulo);

                        ActualizarImporte();

                    }





                }//Fin del else





            }/******************************FIN DEL EVENTO onItemSelected()*****************************/





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }





        });/******************************FIN DEL EVENTO setOnItemSelectedListener()*****************************/

    } /******************************FIN DE LA FUNCIÓN setSpinner()*****************************/




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

            final LinearLayout llv_ventas_repartidor = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

            for(int l = 1 ; l < llv_ventas_repartidor.getChildCount() ; l++){

                final LinearLayout llh_nuevo_articulo_ventas_repartidor = (LinearLayout) llv_ventas_repartidor.getChildAt(l);

                final EditText et_cantidad_venta_del_nuevo_articulo_ventas_repartidor = (EditText) llh_nuevo_articulo_ventas_repartidor.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);



                /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!et_cantidad_venta_del_nuevo_articulo_ventas_repartidor.getText().toString().isEmpty()){

                    flag_validacion_campos_confirmacion_ventas = true;
                }


                if (et_cantidad_venta_del_nuevo_articulo_ventas_repartidor.getText().toString().isEmpty()) {

                    flag_validacion_campos_confirmacion_ventas = false;

                    Toast.makeText(getApplicationContext(), "Error! el campo del nuevo artículo añadido está vacío. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();


                }



                /** Segunda Validación: Si el campo fijo (eTCantVentas) esta vacío pero el campo programático tiene un valor cargado, entonces deberá arrojar un mensaje
                 * de error.
                 */


                if (!et_cantidad_venta_del_nuevo_articulo_ventas_repartidor.getText().toString().isEmpty() && eTCantVentas.getText().toString().isEmpty()) {

                    flag_validacion_campos_confirmacion_ventas = false;

                    Toast.makeText(getApplicationContext(), "Error! el primer campo de los artículos a la venta está vaciío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


                }





                /** Tercera Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                 *  de guardar valores nulos se arrojará un mensaje de error */


                if (et_cantidad_venta_del_nuevo_articulo_ventas_repartidor.getText().toString().length() > 0 && et_cantidad_venta_del_nuevo_articulo_ventas_repartidor.getText().toString().charAt(0) == '0'){

                    Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                            " dígito comience con cero ", Toast.LENGTH_LONG).show();

                    flag_validacion_campos_confirmacion_ventas = false;


                }






            } //Fin del for del "l" ("Articulos Programáticos")







            if (flag_validacion_campos_confirmacion_ventas) {


                Toast.makeText(getApplicationContext(),"Venta realizada con éxito",Toast.LENGTH_LONG).show();

                // put the String to pass back into an Intent and close this activity
                Intent intent = new Intent();

                setResult(RESULT_OK, intent);

                finish();

            } //Fin del if (flag){}


        } /*Fin del primer 'for'*/

        return flag_validacion_campos_confirmacion_ventas;

    } /*********************************FIN DE LA FUNCIÓN ValidacionDeCamposAntesDeConfirmarVenta() ****************************/






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

    public void GuardarVentasRepartidoresEnSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Ventas_Repartidores", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();





        int tope_de_articulos = 0;

        final Spinner spinner_fijo_ventas_repartidores = (Spinner) findViewById(R.id.sp_art_ventas);

        final EditText editText_cantidad_articulo_fijo_repartidores = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas);

        final EditText editText_entrega_venta_repartidores = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas);




        String ArticuloSeleccionado =  spinner_fijo_ventas_repartidores.getSelectedItem().toString();

        editor.putString("Articulo_Seleccionado", ArticuloSeleccionado);

        editor.putString("Cantidad_Articulo", editText_cantidad_articulo_fijo_repartidores.getText().toString());

        editor.putString("Entrega_Venta_Supervisor", editText_entrega_venta_repartidores.getText().toString());




        final LinearLayout llv_ventas_repartidor = (LinearLayout) findViewById(R.id.layout_vertical_ventas);

        tope_de_articulos = llv_ventas_repartidor.getChildCount()-1;


        for(int j = 1 ; j < llv_ventas_repartidor.getChildCount() ; j++){



            final LinearLayout llh_nuevo_articulo_ventas_repartidor = (LinearLayout) llv_ventas_repartidor.getChildAt(j);

            final Spinner spinner_nuevos_articulos_ventas_repartidor = (Spinner) llh_nuevo_articulo_ventas_repartidor.findViewById(R.id.sp_new_art_ventas_repartidor);

            final EditText et_carga_del_nuevo_articulo_venta_repartidor = (EditText) llh_nuevo_articulo_ventas_repartidor.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);



            String ArticuloSeleccionadoSpinnerProgramatico =  spinner_nuevos_articulos_ventas_repartidor.getSelectedItem().toString();

            editor.putString("Nuevo Articulo Seleccionado - Posicion: " + j, ArticuloSeleccionadoSpinnerProgramatico);

            editor.putString("Cantidad Nuevo Articulo - Posicion: " + j, et_carga_del_nuevo_articulo_venta_repartidor.getText().toString());


        } //Fin del for del "j" ("Articulos Programáticos")




    }/*******************************FIN DE LA FUNCIÓN GuardarVentasRepartidoresEnSharedPreferences()******************************/




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





}/******************************* FIN DE LA ACTIVITY RealizarVentasClientesRepartidor ***********************************/