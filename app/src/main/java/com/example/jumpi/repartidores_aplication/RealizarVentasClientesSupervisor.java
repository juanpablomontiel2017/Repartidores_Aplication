package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class RealizarVentasClientesSupervisor extends AppCompatActivity {


    /*******DECLARACIÓN DE VARIABLES GLOBALES**/





    /**Variables tipo Spinner**/

    Spinner spinner_ventas_supervisor;




    /** Matríz Clásica tipo Cadena */

    String [] ListaDeArticulosEnVenta =  {"Bidones", "Dispenser Plástico", "Canillas", "Dispenser Eléctrico","Envases vacíos retirados"};




    /** Variables tipo TextView*/

    TextView Nombre_Apellido_Cliente_Ventas_Supervisor;

    TextView Direccion_Cliente_Ventas_Supervisor;

    TextView Barrio_Cliente_Ventas_Supervisor;


    /******Variables tipo String********/

    String ArticuloSeleccionadoAnterior;






    /**Variables tipo EditText*/

    EditText eTCantVentas;

    EditText eTEntrega;




    /** Variables tipo ImageButton*/

    ImageButton btnAgregarNuevoArticuloParaVentas;

    ImageButton buttonConfirmarVentas;




    /** Variables tipo LinearLayout*/
    LinearLayout LinearLayoutVerticalVentas;




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









        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ventas_supervisor);
        toolbar.setTitle("VENTAS");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);



        Nombre_Apellido_Cliente_Ventas_Supervisor  = (TextView) findViewById(R.id.nombre_apellido_cliente_realizar_ventas_supervisor);

        Direccion_Cliente_Ventas_Supervisor  = (TextView) findViewById(R.id.direccion_cliente_realizar_ventas_supervisor);

        Barrio_Cliente_Ventas_Supervisor  = (TextView) findViewById(R.id.barrio_cliente_realizar_ventas_supervisor);




        /*Llamada a la función: */
        RecibirParametrosDeLosClientesVentasSupervisores();





        LinearLayoutVerticalVentas = (LinearLayout) findViewById(R.id.layout_vertical_ventas_supervisor);





        btnAgregarNuevoArticuloParaVentas = (ImageButton) findViewById(R.id.add_art_ventas_supervisor);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticuloParaVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Llamada a la función:  */
                ValidarCamposParaAñadirNuevoArticuloVentasSupervisor(LinearLayoutVerticalVentas);



            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/




        spinner_ventas_supervisor = (Spinner)findViewById(R.id.sp_art_ventas_supervisor);



        eTCantVentas = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas_supervisor);




        /*Llamada a la función: */

        setSpinner(spinner_ventas_supervisor,true);








        buttonConfirmarVentas = (ImageButton) findViewById(R.id.img_button_confirmar_ventas_supervisor);



        buttonConfirmarVentas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ValidacionDeCamposAntesDeConfirmarVenta();

                GuardarVentasSupervisoresEnSharedPreferences();

            }
        });




        eTEntrega = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas_supervisor);


        /*Llamada a la función: */
        ActualizarColorDelCheckBoton();





    }/*******************************FIN DEL onCreate()**************************************/






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



        String extras = getIntent().getStringExtra("Nombre_Apellido_Cliente_Supervisor");

        Nombre_Apellido_Cliente_Ventas_Supervisor.setText(extras);



        extras = getIntent().getStringExtra("Direccion_Cliente_Supervisor");

        Direccion_Cliente_Ventas_Supervisor.setText(extras);




        extras = getIntent().getStringExtra("Barrio_Cliente_Supervisor");

        Barrio_Cliente_Ventas_Supervisor.setText(extras);



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


        /** Instanciamos las vistas del diseño XML: "nuevo_articulo.xml" **/

        final ImageButton btnEliminarArticuloVentasSupervisores = (ImageButton) NuevoArticuloInfladoVentasSupervisores.findViewById(R.id.delete_art_ventas_supervisor);







        final EditText EditText_Carga_Nuevo_Articulo_Ventas_Supervisores = (EditText) NuevoArticuloInfladoVentasSupervisores.findViewById(R.id.edtx_cantidad_ventas_new_art_supervisor);

        EditText_Carga_Nuevo_Articulo_Ventas_Supervisores.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



                if(s.toString().length() > 0 && s.toString().charAt(0) != '0' ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_verde);


                } else {


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


                if(!s.toString().isEmpty() && EditText_Carga_Nuevo_Articulo_Ventas_Supervisores.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }

                if(s.toString().isEmpty() && EditText_Carga_Nuevo_Articulo_Ventas_Supervisores.getText().toString().isEmpty() ){


                    buttonConfirmarVentas.setImageResource(R.drawable.ic_check_rojo);

                }



            }
        });



        final Spinner spinner_nuevos_articulos_ventas_supervisores = (Spinner) NuevoArticuloInfladoVentasSupervisores.findViewById(R.id.sp_new_art_ventas_supervisor);


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





        btnEliminarArticuloVentasSupervisores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EliminarNuevoArticuloVentasSupervisores(v,spinner_nuevos_articulos_ventas_supervisores);



            }
        });




    } /**************************************FIN DE LA FUNCION ObtenerNuevoArticuloParaVentasSupervisores()*******************************************/






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

        final View InflatedViewVentasSupervisores = inflater.inflate(R.layout.nuevo_articulo_ventas_supervisor, null, true);

        LayoutVertical.addView(InflatedViewVentasSupervisores);

        ChildNuevoArticuloVentasSupervisores = LayoutVertical.getChildCount();


        return InflatedViewVentasSupervisores;




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


            final LinearLayout llv_ventas_supervisor = (LinearLayout) findViewById(R.id.layout_vertical_ventas_supervisor);

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

                final LinearLayout llv_ventas_supervisor = (LinearLayout) findViewById(R.id.layout_vertical_ventas_supervisor);

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

            final Spinner spinner_fijo_ventas_supervisores = (Spinner) findViewById(R.id.sp_art_ventas_supervisor);

            final EditText editText_cantidad_articulo_fijo_supervisores = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas_supervisor);

            final EditText editText_entrega_venta_supervisores = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas_supervisor);




            String ArticuloSeleccionado =  spinner_fijo_ventas_supervisores.getSelectedItem().toString();

            editor.putString("Articulo_Seleccionado", ArticuloSeleccionado);

            editor.putString("Cantidad_Articulo", editText_cantidad_articulo_fijo_supervisores.getText().toString());

            editor.putString("Entrega_Venta_Supervisor", editText_entrega_venta_supervisores.getText().toString());




            final LinearLayout llv_ventas_supervisores = (LinearLayout) findViewById(R.id.layout_vertical_ventas_supervisor);

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




}/************FIN DE LA Activity***************/
