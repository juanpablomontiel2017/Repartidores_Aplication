package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Ventas_Activity extends AppCompatActivity {



    /*******DECLARACIÓN DE VARIABLES GLOBALES**/

    /**Variables tipo Spinner**/

    Spinner spinner_ventas_repartidor;




    /** Matríz Clásica tipo Cadena */

    String [] ListaDeArticulosEnVentaRepartidor =  {"Bidones", "Dispenser Plástico", "Canillas", "Dispenser Eléctrico","Envases vacíos retirados"};




    /** Variables tipo TextView*/

    TextView Apellido_Cliente_Ventas_Repartidor;

    TextView Nombre_Cliente_Ventas_Repartidor;

    TextView Direccion_Cliente_Ventas_Repartidor;

    TextView Barrio_Cliente_Ventas_Repartidor;


    /******Variables tipo String********/

    String ArticuloSeleccionadoAnterior;






    /**Variables tipo EditText*/

    EditText eTCantVentasRepartidor;

    EditText eTEntregaRepartidor;




    /** Variables tipo ImageButton*/

    ImageButton btnAgregarNuevoArticuloParaVentasRepartidor;

    ImageButton buttonConfirmarVentasRepartidor;




    /** Variables tipo LinearLayout*/
    LinearLayout LinearLayoutVerticalVentasRepartidor;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ventas_por_cliente);





        Apellido_Cliente_Ventas_Repartidor  = (TextView) findViewById(R.id.apellido_cliente_realizar_ventas_repartidores);

        Nombre_Cliente_Ventas_Repartidor  = (TextView) findViewById(R.id.nombre_cliente_realizar_ventas_repartidores);

        Direccion_Cliente_Ventas_Repartidor  = (TextView) findViewById(R.id.direccion_cliente_realizar_ventas_repartidores);

        Barrio_Cliente_Ventas_Repartidor = (TextView) findViewById(R.id.barrio_cliente_realizar_ventas_repartidores);




        /*Llamada a la función: */
        RecibirParametrosDeLosClientesVentasRepartidores();





        LinearLayoutVerticalVentasRepartidor = (LinearLayout) findViewById(R.id.layout_vertical_ventas_repartidores);





        btnAgregarNuevoArticuloParaVentasRepartidor = (ImageButton) findViewById(R.id.add_art_ventas_repartidores);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticuloParaVentasRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Llamada a la función:  */
                ValidarCamposParaAñadirNuevoArticuloVentasRepartidores(LinearLayoutVerticalVentasRepartidor);



            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/




        spinner_ventas_repartidor = (Spinner)findViewById(R.id.sp_art_ventas_repartidores);



        eTCantVentasRepartidor = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas_repartidores);




        /*Llamada a la función: */

        setSpinner(spinner_ventas_repartidor,true);








        buttonConfirmarVentasRepartidor = (ImageButton) findViewById(R.id.img_button_confirmar_ventas_repartidores);



        buttonConfirmarVentasRepartidor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ValidacionDeCamposAntesDeConfirmarVentaRepartidores();

                GuardarVentasRepartidoresEnSharedPreferences();

            }
        });




        eTEntregaRepartidor = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas_repartidores);


        /*Llamada a la función: */
        ActualizarColorDelCheckBoton();









    } /**********************************FIN DEL OnCreate()***********************************/





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



        eTCantVentasRepartidor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                /** Todos los CASOS posibles para los CAMPOS FIJOS del Diseño XML, haciendo referencia a la vista: eTCantVentasRepartidor **/



                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'){


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_verde);


                }

                else{


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);



                }



                if (s.toString().isEmpty() && eTEntregaRepartidor.getText().toString().length() > 0 && eTEntregaRepartidor.getText().toString().charAt(0) != '0'){


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_verde);


                }


                if (s.toString().isEmpty() && eTEntregaRepartidor.getText().toString().length() > 0 && eTEntregaRepartidor.getText().toString().charAt(0) == '0') {

                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);


                }



                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'   && eTEntregaRepartidor.getText().toString().length() > 0 && eTEntregaRepartidor.getText().toString().charAt(0) == '0') {

                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);

                }







            }/*******************************FIN DEL EVENTO afterTextChanged()******************************************/


        });/*******************************FIN DEL EVENTO addTextChangedListener()**************************************/






        eTEntregaRepartidor.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                /** Todos los CASOS posibles para los CAMPOS FIJOS del Diseño XML, haciendo referencia a la vista: eTEntregaRepartidor **/


                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'){



                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_verde);

                }


                else{

                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);


                }



                if(s.toString().isEmpty() && eTCantVentasRepartidor.getText().toString().length() > 0 && eTCantVentasRepartidor.getText().toString().charAt(0) != 0){


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_verde);

                }


                if (s.toString().isEmpty() && eTCantVentasRepartidor.getText().toString().length() > 0 && eTCantVentasRepartidor.getText().toString().charAt(0) == '0'){

                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);


                }



                if (s.toString().length() > 0 && s.toString().charAt(0) != '0'   && eTCantVentasRepartidor.getText().toString().length() > 0 && eTCantVentasRepartidor.getText().toString().charAt(0) == '0') {

                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);

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







    public void RecibirParametrosDeLosClientesVentasRepartidores(){



        String extras = getIntent().getStringExtra("Apellido");

        Apellido_Cliente_Ventas_Repartidor.setText(extras);




        extras = getIntent().getStringExtra("Nombre");

        Nombre_Cliente_Ventas_Repartidor.setText(extras);




        extras = getIntent().getStringExtra("Direccion");

        Direccion_Cliente_Ventas_Repartidor.setText(extras);




        extras = getIntent().getStringExtra("Barrio");

        Barrio_Cliente_Ventas_Repartidor.setText(extras);



    }/**********************FIN DE LA FUNCIÓN RecibirParametrosDeLosClientesVentasRepartidores()********************************/






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


        /** Instanciamos las vistas del diseño XML: "nuevo_articulo.xml" **/

        final ImageButton btnEliminarArticuloVentasRepartidores = (ImageButton) NuevoArticuloInfladoVentasRepartidores.findViewById(R.id.delete_art_ventas_repartidor);







        final EditText EditText_Carga_Nuevo_Articulo_Ventas_Repartidores = (EditText) NuevoArticuloInfladoVentasRepartidores.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);

        EditText_Carga_Nuevo_Articulo_Ventas_Repartidores.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



                if(s.toString().length() > 0 && s.toString().charAt(0) != '0' ){


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_verde);


                } else {


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);

                }





            }/*******************************FIN DEL EVENTO afterTextChanged()******************************************/


        });/*******************************FIN DEL EVENTO addTextChangedListener()**************************************/


        eTEntregaRepartidor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(!s.toString().isEmpty() && EditText_Carga_Nuevo_Articulo_Ventas_Repartidores.getText().toString().isEmpty() ){


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);

                }

                if(s.toString().isEmpty() && EditText_Carga_Nuevo_Articulo_Ventas_Repartidores.getText().toString().isEmpty() ){


                    buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);

                }



            }
        });



        final Spinner spinner_nuevos_articulos_ventas_repartidores = (Spinner) NuevoArticuloInfladoVentasRepartidores.findViewById(R.id.sp_new_art_ventas_repartidor);


        /*Llamada a la función: */
        setSpinner(spinner_nuevos_articulos_ventas_repartidores,false);


        ArticuloSeleccionadoAnterior = spinner_nuevos_articulos_ventas_repartidores.getSelectedItem().toString();


        spinner_nuevos_articulos_ventas_repartidores.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerProgramatico,spinner_nuevos_articulos_ventas_repartidores));





        if (ValorElementoSeleccionadoSpinnerProgramatico != ""){


            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner_nuevos_articulos_ventas_repartidores,ArticuloSeleccionadoAnterior,ValorElementoSeleccionadoSpinnerProgramatico, this);


        } else{

            Utils_Spinner.contador_de_inicializacion = 0;

            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinnerConBordes(spinner_nuevos_articulos_ventas_repartidores,null,ArticuloSeleccionadoAnterior, this);


        }





        btnEliminarArticuloVentasRepartidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EliminarNuevoArticuloVentasRepartidores(v,spinner_nuevos_articulos_ventas_repartidores);



            }
        });




    } /**************************************FIN DE LA FUNCION ObtenerNuevoArticuloParaVentasRepartidores()*******************************************/






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

        final View InflatedViewVentasRepartidores = inflater.inflate(R.layout.nuevo_articulo_ventas_repartidor, null, true);

        LayoutVertical.addView(InflatedViewVentasRepartidores);

        ChildNuevoArticuloVentasRepartidores= LayoutVertical.getChildCount();


        return InflatedViewVentasRepartidores;




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




        buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_verde);



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

    public void ValidarCamposParaAñadirNuevoArticuloVentasRepartidores(LinearLayout LinearLayout){


        if (!eTCantVentasRepartidor.getText().toString().isEmpty()){

            bandera_boleana = true;

        }



        if(eTCantVentasRepartidor.getText().toString().isEmpty()){

            Toast.makeText(getApplicationContext(), "Recuerde! Para añadir un nuevo artículo debe completar el campo 'Cantidad' con valores correctos.", Toast.LENGTH_LONG).show();

            bandera_boleana = false;



        }



        /** Segunda Validación: Si el campo contiene en el primer dígito  un cero o se trata
         *  de guardar valores nulos se arrojará un mensaje de error */

        if (eTCantVentasRepartidor.getText().toString().length() > 0 && eTCantVentasRepartidor.getText().toString().charAt(0) == '0'){

            Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                    " dígito comience con cero ", Toast.LENGTH_LONG).show();

            bandera_boleana = false;
        }




        /**VALIDACIÓN DEL CAMPO DE CANTIDAD PARA EL NUEVO ARTICULO**/


        final LinearLayout llv_ventas_repartidores = (LinearLayout) findViewById(R.id.layout_vertical_ventas_repartidores);

        for(int j = 1 ; j < llv_ventas_repartidores.getChildCount() ; j++){

            final LinearLayout llh_nuevo_articulo_ventas_repartidores = (LinearLayout) llv_ventas_repartidores.getChildAt(j);

            final EditText et_cantidad_del_nuevo_articulo_ventas_repartidores = (EditText) llh_nuevo_articulo_ventas_repartidores.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);


            /** Primer Validación: Si el campo ya mencionado contiene un valor, entonces se guardarán los cambios. Caso contrario
             * se arrojará un mensaje de error*/

            if (!et_cantidad_del_nuevo_articulo_ventas_repartidores.getText().toString().isEmpty()){

                bandera_boleana = true;


            }


            if (et_cantidad_del_nuevo_articulo_ventas_repartidores.getText().toString().isEmpty()) {


                bandera_boleana = false;

                Toast.makeText(getApplicationContext(), "Error! el campo del nuevo artículo añadido está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


            }

            /** Segunda Validación: Si el campo fijo (eTCantVentasRepartidor) esta vacío pero el campo programático tiene un valor cargado, entonces deberá arrojar un mensaje
             * de error.
             */


            if (!et_cantidad_del_nuevo_articulo_ventas_repartidores.getText().toString().isEmpty() && eTCantVentasRepartidor.getText().toString().isEmpty()) {

                bandera_boleana = false;

                Toast.makeText(getApplicationContext(), "Error! el primer campo de los artículos a la venta está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


            }



            /** Tercera Validación: Si el campo contiene en el primer dígito un cero o se trata
             *  de guardar valores nulos se arrojará un mensaje de error */

            if (et_cantidad_del_nuevo_articulo_ventas_repartidores.getText().toString().length() > 0 && et_cantidad_del_nuevo_articulo_ventas_repartidores.getText().toString().charAt(0) == '0'){

                Toast.makeText(getApplicationContext(), "Error! No está permitido completar el campo con valores nulos o que el primer" +
                        " dígito comience con cero ", Toast.LENGTH_LONG).show();

                bandera_boleana = false;
            }




        } //Fin del for






        if (bandera_boleana) {



            /*Llamar a la función*/

            ObtenerNuevoArticuloParaVentasRepartidores("",LinearLayout);

            buttonConfirmarVentasRepartidor.setImageResource(R.drawable.ic_check_rojo);

            Toast.makeText(getApplicationContext(), "Nuevo artículo añadido", Toast.LENGTH_LONG).show();

        }


    }/*************************************FIN DE LA FUNCIÓN ValidarCamposParaAñadirNuevoArticuloVentasRepartidores()***********************************************/





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



            adaptador = new ArrayAdapter<String>(this, R.layout.spinner, ListaDeArticulosEnVentaRepartidor);




        }

        else{



            ArrayList<String> ArticulosReducidos = new ArrayList<String>(Arrays.asList(ListaDeArticulosEnVentaRepartidor));


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
                    Utils_Spinner.RefrescarOtrosSpinnerConBordes((Spinner)adapterView,ArticuloSeleccionadoAnterior,text, Ventas_Activity.this);




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
    public boolean ValidacionDeCamposAntesDeConfirmarVentaRepartidores() {


        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {




            /** Primer Validación: Si al menos uno de los campos contiene un valor se confirmará la venta. Caso contrario
             * se arrojará un mensaje de error*/

            if (!eTCantVentasRepartidor.getText().toString().isEmpty() || !eTEntregaRepartidor.getText().toString().isEmpty()){

                flag_validacion_campos_confirmacion_ventas = true;

            }



            if (eTCantVentasRepartidor.getText().toString().isEmpty() && eTEntregaRepartidor.getText().toString().isEmpty()){

                Toast.makeText(getApplicationContext(),"Error! Los campos estan vacios. Por favor, complete al menos un campo",Toast.LENGTH_LONG).show();


                flag_validacion_campos_confirmacion_ventas = false;


            }






            /** Segunda Validación: Si uno de los campos contienen en el primer dígito  un cero o se trata
             *  de guardar valores nulos, entonces se arrojará un mensaje de error */

            if (eTCantVentasRepartidor.getText().toString().length() > 0 && eTCantVentasRepartidor.getText().toString().charAt(0) == '0'
                    || eTEntregaRepartidor.getText().toString().length() > 0 && eTEntregaRepartidor.getText().toString().charAt(0) == '0'){

                Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos" +
                        " o que el primer dígito comience con cero ", Toast.LENGTH_LONG).show();

                flag_validacion_campos_confirmacion_ventas = false;


            }







            /**VALIDACIÓN DE CAMPOS DE CANTIDAD DE ARTÍCULO Y LA ENTREGA PARA LOS NUEVOS ARTICULOS**/

            final LinearLayout llv_ventas_repartidores= (LinearLayout) findViewById(R.id.layout_vertical_ventas_repartidores);

            for(int l = 1 ; l < llv_ventas_repartidores.getChildCount() ; l++){

                final LinearLayout llh_nuevo_articulo_ventas_repartidores = (LinearLayout) llv_ventas_repartidores.getChildAt(l);

                final EditText et_cantidad_venta_del_nuevo_articulo_ventas_repartidores= (EditText) llh_nuevo_articulo_ventas_repartidores.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);



                /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!et_cantidad_venta_del_nuevo_articulo_ventas_repartidores.getText().toString().isEmpty()){

                    flag_validacion_campos_confirmacion_ventas = true;
                }


                if (et_cantidad_venta_del_nuevo_articulo_ventas_repartidores.getText().toString().isEmpty()) {

                    flag_validacion_campos_confirmacion_ventas = false;

                    Toast.makeText(getApplicationContext(), "Error! el campo del nuevo artículo añadido está vacío. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();


                }



                /** Segunda Validación: Si el campo fijo (eTCantVentasRepartidor) esta vacío pero el campo programático tiene un valor cargado, entonces deberá arrojar un mensaje
                 * de error.
                 */


                if (!et_cantidad_venta_del_nuevo_articulo_ventas_repartidores.getText().toString().isEmpty() && eTCantVentasRepartidor.getText().toString().isEmpty()) {

                    flag_validacion_campos_confirmacion_ventas = false;

                    Toast.makeText(getApplicationContext(), "Error! el primer campo de los artículos a la venta está vacío. Por favor, complete el campo correspondiente.", Toast.LENGTH_LONG).show();


                }





                /** Tercera Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                 *  de guardar valores nulos se arrojará un mensaje de error */


                if (et_cantidad_venta_del_nuevo_articulo_ventas_repartidores.getText().toString().length() > 0 && et_cantidad_venta_del_nuevo_articulo_ventas_repartidores.getText().toString().charAt(0) == '0'){

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

    } /*********************************FIN DE LA FUNCION ValidacionDeCamposAntesDeConfirmarVentaRepartidores() ****************************/






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

        SharedPreferences sharedPreferences = getSharedPreferences("Datos", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();





        int tope_de_articulos = 0;

        final Spinner spinner_fijo_ventas_repartidores = (Spinner) findViewById(R.id.sp_art_ventas_repartidores);

        final EditText editText_cantidad_articulo_fijo_repartidores = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas_repartidores);

        final EditText editText_entrega_venta_repartidores = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas_repartidores);




        String ArticuloSeleccionado =  spinner_fijo_ventas_repartidores.getSelectedItem().toString();

        editor.putString("Articulo_Seleccionado", ArticuloSeleccionado);

        editor.putString("Cantidad_Articulo", editText_cantidad_articulo_fijo_repartidores.getText().toString());

        editor.putString("Entrega_Venta_Repartidor", editText_entrega_venta_repartidores.getText().toString());




        final LinearLayout llv_ventas_repartidores = (LinearLayout) findViewById(R.id.layout_vertical_ventas_repartidores);

        tope_de_articulos = llv_ventas_repartidores.getChildCount()-1;




        for (int j = 1 ; j < llv_ventas_repartidores.getChildCount() ; j++){



            final LinearLayout llh_nuevo_articulo_ventas_repartidor = (LinearLayout) llv_ventas_repartidores.getChildAt(j);

            final Spinner spinner_nuevos_articulos_ventas_repartidor = (Spinner) llh_nuevo_articulo_ventas_repartidor.findViewById(R.id.sp_new_art_ventas_repartidor);

            final EditText et_carga_del_nuevo_articulo_venta_repartidor = (EditText) llh_nuevo_articulo_ventas_repartidor.findViewById(R.id.edtx_cantidad_ventas_new_art_repartidor);



            String ArticuloSeleccionadoSpinnerProgramatico =  spinner_nuevos_articulos_ventas_repartidor.getSelectedItem().toString();

            editor.putString("Nuevo Articulo Seleccionado - Posicion: " + j, ArticuloSeleccionadoSpinnerProgramatico);

            editor.putString("Cantidad Nuevo Articulo - Posicion: " + j, et_carga_del_nuevo_articulo_venta_repartidor.getText().toString());


        } //Fin del for del "j" ("Articulos Programáticos")







    }/*******************************FIN DE LA FUNCIÓN GuardarVentasRepartidoresEnSharedPreferences()******************************/






} /****************************** FIN DE LA ACTIVITY Ventas_Activity *******************************/
