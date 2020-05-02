package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static android.view.View.GONE;

public class Cargas_Descargas extends AppCompatActivity {

    Boolean flag = false;


    /************** DECLARACIÓN DE VARIABLES GLOBALES***********/


    /******ScrollView********/

    private ScrollView parent_scrollView;


    /******LinearLayout********/

    /*Declaración de variable de tipo LinearLayout para implementarla como contenedor padre de los demás LinearLayoutHorizontales.*/
    private LinearLayout LinearLayoutNuevaTanda;

    private LinearLayout LinearLayoutVerticalPrimerTanda;

    private LinearLayout LinearLayoutBordesTanda;

    private LinearLayout LinearLayoutHorizontalSegundaTupla;

    private LinearLayout LinearLayoutVerticalTercerTupla;



    /******ImageButton********/

    ImageButton btnAgregarNuevoArticulo;




    /******Spinner********/

     /*Declaración de variables tipo Spinner que contiene los artículos correspondientes y una matríz de tipo cadena que contendrá
     los nombres de dichos artículos. Por el momento, serán un total de 6 artículos*/

    Spinner spinner_fijo;


    /******Matriz clásica********/

    String[] ArticulosDelArrayClasico = {"Bidones", "Dispenser plástico", "Canillas", "Dispenser eléctrico", "Envases rotos/pinchados", "Envases \n estro- \n peados","Combus- \n tible"};


    /******Variables tipo String********/


    String ArticuloSeleccionadoAnterior;

    String nombre_apellido_recibir;


    /******EditText********/

    /*Declaración de variables tipo EditText para que representan las cargas y descargas de artículos y dinero con respecto a la tanda del XML.*/
    EditText eTcantCarga;
    EditText eTcantDescarga;
    EditText eTcantMoneyCarga;
    EditText eTcantMoneyDescarga;


    /******ArrayList<>********/

    /*Almacenaremos los campos de carga y descarga de artículos, dinero y además los nuevos artículos para todas las tandas.*/

    ArrayList<View> ArrayListTandas = new ArrayList<View>();


    /******FloatingActionButton********/

    private FloatingActionButton fab_nueva_tanda;
    private FloatingActionButton fab_cancel_tanda;


    /******Variables Cerrojos********/

    boolean Estado_Tanda = true;


    /******Contadores********/

    /*Para incrementar el número de cada tanda añadida*/

    int tanda_numero = 0;

    int contador_de_inicializacion = 0;



    /*****************************COMIENZO DEL OnCreate********************************************/



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargas_descargas);


        /**Recibir como parámetro el Nombre&Apellido del repartidor de la activity de EleccionRepartidores**/
        nombre_apellido_recibir = getIntent().getStringExtra("nombre_apellido_repartidor");

        Estado_Tanda = Boolean.parseBoolean(LeerConfiguracionDeActivityEnUnSharedPreferences("EstadoTanda" + nombre_apellido_recibir));

        String Nombre_Dia = UtilidadFecha.getNombreDelDia();

        String FechaActualDelSistema = UtilidadFecha.getFecha("dd/MM/yyyy");

        //String FechaGuardada;
        String FechaGuardada = "02/05/2020";

/*

        if(LeerConfiguracionDeActivityEnUnSharedPreferences("Fecha").equals("true")){

            FechaGuardada = (UtilidadFecha.SetearFecha(2000,00,01));

        }//Fin del if

        else {

            FechaGuardada = LeerConfiguracionDeActivityEnUnSharedPreferences("Fecha");

        } //Fin del else


*/



        if(FechaGuardada.equals(FechaActualDelSistema)) {

            Toast.makeText(Cargas_Descargas.this, "Dia: " + Nombre_Dia, Toast.LENGTH_LONG).show();


        } //Fin del primer if




        else {

            GuardarConfiguracionDeActivityEnUnSharedPreferences("EstadoTanda" + nombre_apellido_recibir, "true");

            Estado_Tanda = true;


            if(Estado_Tanda){

                /*Llamada a la función: */
                CierreDeTandaCorrecto(FechaActualDelSistema);

            } else{

                /*Llamada a la función: */
                CierreDeTandaAbrupto(FechaActualDelSistema);


            }


        }//Fin del else








        /** Tandas cerradas **/
        if(!Estado_Tanda){


            /*Llamada a la función:  */
            CargarVistasTandasDeshabilitadas();

        } //FIN DEL if


        /** Tandas habilitadas **/
        else{

            /*Llamada a la función:  */
            CargarVistasTandasHabilitadas();


        }//FIN DE else

    } /*********************************FIN DEL onCreate()*******************************************/








    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/



    public void CargarVistasTandasHabilitadas(){




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



        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        /*Recibir como parámetro el nombre_&_apellido del repartidor */
        toolbar.setTitle(nombre_apellido_recibir);
        setSupportActionBar(toolbar);




        /*Llamada a la función:  */
        CargarReferenciasPrimerTandaHabilitada(window,toolbar);




        /* Guardaremos las "Nuevas Tandas" creadas en ArrayTandas */
        ArrayListTandas.add(LinearLayoutVerticalPrimerTanda);



        MostrarValoresDelSharedPreferences();







        /*CASO ESPECIAL: Si estamos parados en la Primer Tanda, el botón flotante "T" no debería ser visible */

        if (ArrayListTandas.size() > 1) {

            fab_nueva_tanda.setVisibility(View.VISIBLE);
            fab_cancel_tanda.setVisibility(GONE);

        }






    }/******************************FIN DE LA FUNCIÓN CargarVistasTandasHabilitadas()*****************************/




    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/


    public void CargarReferenciasPrimerTandaHabilitada(Window window, Toolbar toolbar){


        parent_scrollView = (ScrollView) findViewById(R.id.scroll_parent);

        LinearLayoutNuevaTanda = (LinearLayout) findViewById(R.id.parent_layout_vertical);

        LinearLayoutVerticalPrimerTanda = (LinearLayout) findViewById(R.id.layout_vertical);



        LinearLayoutVerticalPrimerTanda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                /*Llamada a la función: */
                  EliminarCualquierTanda(v);


                return false;


            }/***************FIN DEL EVENTO onLongClick************************/


        });



        LinearLayoutVerticalTercerTupla = (LinearLayout) findViewById(R.id.layout_vertical_tercer_tupla);



        /*Inicialización de la variable de tipo TextView creada en XML para hacer referencia al número de tanda en el que estamos parados */
        TextView textViewTanda = (TextView) findViewById(R.id.tandas);
        tanda_numero++;
        textViewTanda.setText("Tanda N° " + tanda_numero);




        /*Inicialización de variable del botón "+" para añadir un nuevo artículo*/
        btnAgregarNuevoArticulo = (ImageButton) findViewById(R.id.add_art);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
         btnAgregarNuevoArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /*Llamada a la función: */
                ValidarCamposParaAñadirNuevoArticulo(LinearLayoutVerticalTercerTupla);




            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/





        spinner_fijo = (Spinner) findViewById(R.id.sp_art);



        /*Inicialización de los campos de carga y descarga de artículos y dinero en el diseño XML*/
        eTcantCarga = (EditText) findViewById(R.id.edtx_carga);
        eTcantCarga.requestFocus();
        eTcantCarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        eTcantDescarga = (EditText) findViewById(R.id.edtx_descarga);
        eTcantDescarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTcantMoneyCarga = (EditText) findViewById(R.id.edtx_carga_money);
        eTcantMoneyCarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        eTcantMoneyDescarga = (EditText) findViewById(R.id.edtx_descarga_money);
        eTcantMoneyDescarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




        /*Llamada a la función: */
        setSpinner(spinner_fijo, eTcantCarga,true, eTcantDescarga);





        /*Inicialización de un FloatingActionButton para AÑADIR una nueva tanda*/
        fab_nueva_tanda = findViewById(R.id.new_tanda_id);

        fab_nueva_tanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils_Spinner.contador_de_inicializacion--;

                ObtenerNuevaTanda("", "", "",
                        "", "");

            }
        });




        /*Inicialización de un FloatingActionButton para ELIMINAR una nueva tanda*/
        fab_cancel_tanda = findViewById(R.id.cancel_tanda_id);







        /************ Cambiar color del cursor de cada EditText *************/
        Field f = null;

        try {

            f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(eTcantCarga, R.drawable.color_cursor_supervisores);
            f.set(eTcantDescarga, R.drawable.color_cursor_supervisores);
            f.set(eTcantMoneyCarga, R.drawable.color_cursor_supervisores);
            f.set(eTcantMoneyDescarga, R.drawable.color_cursor_supervisores);



        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }




    }/******************************FIN DE LA FUNCION CargarReferenciasPrimerTandaHabilitada()*****************************/





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



    public void CargarVistasTandasDeshabilitadas(){


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


        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        /*Recibir como parámetro el nombre_&_apellido del repartidor */
        toolbar.setTitle(nombre_apellido_recibir);
        setSupportActionBar(toolbar);





        /*Llamada a la función:  */
        CargarReferenciasPrimerTandaFinDia(window,toolbar);





        /* Guardaremos las "Nuevas Tandas" creadas en ArrayTandas */
        ArrayListTandas.add(LinearLayoutVerticalPrimerTanda);



        MostrarValoresDelSharedPreferences();


        /*CASO ESPECIAL: Si estamos parados en la Primer Tanda, el botón flotante "T" no debería ser visible */

        if (ArrayListTandas.size() > 1) {

            fab_nueva_tanda.setVisibility(View.VISIBLE);
            fab_cancel_tanda.setVisibility(GONE);

        }




    }/******************************FIN DE LA FUNCION CargarVistasTandasDeshabilitadas()*****************************/





    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/



    public void CargarReferenciasPrimerTandaFinDia(Window window, Toolbar toolbar){




        parent_scrollView = (ScrollView) findViewById(R.id.scroll_parent);

        LinearLayoutNuevaTanda = (LinearLayout) findViewById(R.id.parent_layout_vertical);

        LinearLayoutVerticalPrimerTanda = (LinearLayout) findViewById(R.id.layout_vertical);

        LinearLayoutBordesTanda = (LinearLayout) findViewById(R.id.llv_contenedor_para_bordes);
        LinearLayoutBordesTanda.setBackground(getDrawable(R.drawable.borde_linear_layout_evento_deshabilitado));


        LinearLayoutHorizontalSegundaTupla = (LinearLayout) findViewById(R.id.layout_horizontal_segunda_tupla);
        LinearLayoutHorizontalSegundaTupla.setBackgroundColor(Color.parseColor("#616161"));



        LinearLayoutVerticalPrimerTanda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                /*Llamada a la función: */
                EliminarCualquierTanda(v);


                return false;


            }/***************FIN DEL EVENTO onLongClick************************/


        });



        LinearLayoutVerticalTercerTupla = (LinearLayout) findViewById(R.id.layout_vertical_tercer_tupla);



        /*Inicialización de la variable de tipo TextView creada en XML para hacer referencia al número de tanda en el que estamos parados */
        TextView textViewTanda = (TextView) findViewById(R.id.tandas);
        textViewTanda.setBackgroundColor(Color.parseColor("#424242"));
        tanda_numero++;
        textViewTanda.setText("Tanda N° " + tanda_numero);




        /*Inicialización de variable del botón "+" para añadir un nuevo artículo*/
        btnAgregarNuevoArticulo = (ImageButton) findViewById(R.id.add_art);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /*Llamada a la función: */
                ValidarCamposParaAñadirNuevoArticulo(LinearLayoutVerticalTercerTupla);




            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/





        spinner_fijo = (Spinner) findViewById(R.id.sp_art);



        /*Inicialización de los campos de carga y descarga de artículos y dinero en el diseño XML*/
        eTcantCarga = (EditText) findViewById(R.id.edtx_carga);
        eTcantCarga.requestFocus();
        eTcantCarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        eTcantDescarga = (EditText) findViewById(R.id.edtx_descarga);
        eTcantDescarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        eTcantMoneyCarga = (EditText) findViewById(R.id.edtx_carga_money);
        eTcantMoneyCarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        eTcantMoneyDescarga = (EditText) findViewById(R.id.edtx_descarga_money);
        eTcantMoneyDescarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




        /*Llamada a la función: */
        setSpinner(spinner_fijo, eTcantCarga,true, eTcantDescarga);




        /*Inicialización de un FloatingActionButton para AÑADIR una nueva tanda*/
        fab_nueva_tanda = findViewById(R.id.new_tanda_id);

        fab_nueva_tanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils_Spinner.contador_de_inicializacion--;

                ObtenerNuevaTanda("", "", "",
                        "", "");

            }
        });




        /*Inicialización de un FloatingActionButton para ELIMINAR una nueva tanda*/
        fab_cancel_tanda = findViewById(R.id.cancel_tanda_id);





    }/******************************FIN DE LA FUNCIÓN CargarReferenciasPrimerTandaFinDia()*****************************/





    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/


    public void CierreDeTandaCorrecto(final String FechaActualDelSistema){


        AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this);
        builder.setIcon(R.drawable.ic_msj_alerta);
        builder.setTitle("¡Buen día!");
        builder.setMessage("¡Un nuevo día laboral ha comenzado! Consulte las tandas del día de ayer si así lo requiera");



        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {


                dialog.cancel(); //Cierra dialogo.


            }

        });



        AlertDialog dialog = builder.create();
        dialog.show();


        BorrarValoresDelSharedPreferences();

        MostrarValoresDelSharedPreferences();

        GuardarConfiguracionDeActivityEnUnSharedPreferences("Fecha" + nombre_apellido_recibir, FechaActualDelSistema);





    }/***********************FIN DE LA FUNCIÓN CierreDeTandaCorrecto()********************************/






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




    public void CierreDeTandaAbrupto(final String FechaActualDelSistema){


        AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this);
        builder.setIcon(R.drawable.ic_msj_alerta);
        builder.setTitle("¡Las tandas se han cerrado automáticamente!");
        builder.setMessage("¡Esto se debe a que se ha olvidado de cerrar las tandas del días de ayer!");



        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {


                dialog.cancel(); //Cierra dialogo.


            }

        });



        AlertDialog dialog = builder.create();
        dialog.show();


        BorrarValoresDelSharedPreferences();

        MostrarValoresDelSharedPreferences();

        GuardarConfiguracionDeActivityEnUnSharedPreferences("Fecha" + nombre_apellido_recibir, FechaActualDelSistema);




    }/***********************FIN DE LA FUNCIÓN CierreDeTandaAbrupto()********************************/


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




    public void EliminarCualquierTanda(final View v){



                if(Estado_Tanda){


                AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this);
                builder.setIcon(R.drawable.ic_msj_alerta);
                builder.setTitle("Desea eliminar la tanda seleccionada?!");
                builder.setMessage("Al presionar el botón 'Eliminar Tanda' se borrará la tanda que fue seleccionada. ¿Desea continuar?");


                builder.setPositiveButton("Eliminar Tanda", new DialogInterface.OnClickListener() {

                    @SuppressLint("RestrictedApi")
                    public void onClick(DialogInterface dialog, int id) {






                        if (ArrayListTandas.size() > 1) {

                            int ValorDeLaPosicion;

                            ValorDeLaPosicion = EncontrarPosicionDeTanda(v);



                            if(ValorDeLaPosicion != 99){



                                /*Llamada a la función:  */
                                BorrarValoresDelSharedPreferences();



                                ArrayListTandas.remove(v);




                                LinearLayoutNuevaTanda.removeAllViews();



                                /*Llamada a la función:  */
                                GuardarValoresEnSharedPreferences();



                                tanda_numero = 0;


                                ArrayListTandas.clear();


                                /*Llamada a la función:  */
                                RefrescarTandasEnPantalla();




                                /****Tener en cuenta las siguientes condiciones al momento de eliminar la tanda: **/

                                fab_nueva_tanda.setVisibility(View.VISIBLE);

                                fab_cancel_tanda.setVisibility(GONE);

                                DeshabilitarVistasDeLasTandasAlGuardarCambios(true);


                                Toast.makeText(Cargas_Descargas.this, "La tanda ha sido eliminada", Toast.LENGTH_LONG).show();








                            } else {


                                Toast.makeText(Cargas_Descargas.this, "Posicion: " + ValorDeLaPosicion, Toast.LENGTH_LONG).show();


                            }


                        } else {

                            Toast.makeText(Cargas_Descargas.this, "¡No es posible eliminar la primer tanda. Por favor, tenga en cuenta crear una nueva tanda!", Toast.LENGTH_LONG).show();

                        }


                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();


                }//Fin del primer if



    }/*********************************FIN DE LA FUNCIÓN EliminarCualquierTanda()*******************************************/



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



    public int  EncontrarPosicionDeTanda(View v){

        int posicion_tanda = 99;

        for(int i = 0 ; i < ArrayListTandas.size(); i++ ){

            if(ArrayListTandas.get(i) == v ){

                posicion_tanda = i;

                break;

            }//Fin del if


        }//Fin del for


      return posicion_tanda;


    }/*********************************FIN DE LA FUNCIÓN EncontrarPosicionDeTanda()*******************************************/


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





    public void RefrescarTandasEnPantalla(){


            SharedPreferences preferences = getSharedPreferences("Datos_Cargas_Descargas", MODE_PRIVATE);






            String DimensionArrayNuevasTandas = preferences.getString("Repartidor: " + nombre_apellido_recibir + "DimensionArrayNuevasTandas", "");



            if (DimensionArrayNuevasTandas != "") {

                for (int indice_tandas = 0; indice_tandas < Integer.valueOf(DimensionArrayNuevasTandas); indice_tandas++) {



                    String ElementoSeleccionadoSpinnerFijo = preferences.getString("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado - " + "Tanda Numero: " + indice_tandas, "");
                    String ValorCargaArticulosNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadCargaArticuloNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");
                    String ValorDescargaArticulosNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaArticuloNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");
                    String ValorCargaMoneyNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadCargaMoneyNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");
                    String ValorDescargaMoneyNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaMoneyNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");


                        /* Llamada a la función: */
                        ObtenerNuevaTanda(ElementoSeleccionadoSpinnerFijo,ValorCargaArticulosNuevaTanda, ValorDescargaArticulosNuevaTanda,
                                ValorCargaMoneyNuevaTanda,ValorDescargaMoneyNuevaTanda);



                    String DimensionArticulosProgramaticos = preferences.getString("Repartidor: " + nombre_apellido_recibir + "DimensionArticulosProgramaticos - TandaNumero: " + indice_tandas, "");

                    if (DimensionArticulosProgramaticos != "") {

                        for (int j = 1; j <= Integer.valueOf(DimensionArticulosProgramaticos); j++) {



                            String ElementoSeleccionadoSpinnerProgramatico = preferences.getString("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado_NUEVA_TANDA - " + "Tanda Numero: " + indice_tandas + " - " + "Posicion: " + j, "");
                            String ValorCargaNuevoArticuloNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDeCargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + indice_tandas + " - " + "Posicion: " + j, "");
                            String ValorDescargaNuevoArticuloNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDeDescargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + indice_tandas + " - " + "Posicion: " + j, "");


                            if (ValorCargaNuevoArticuloNuevaTanda != "" || ValorDescargaNuevoArticuloNuevaTanda != "") {

                                final View tanda = ArrayListTandas.get(indice_tandas);

                                final LinearLayout tercerTuplaNuevaTanda = (LinearLayout) tanda.findViewById(R.id.layout_vertical_tercer_tupla);


                                    /*Llamada a la función */
                                    ObtenerNuevoArticulo(ElementoSeleccionadoSpinnerProgramatico,ValorCargaNuevoArticuloNuevaTanda, ValorDescargaNuevoArticuloNuevaTanda, tercerTuplaNuevaTanda);


                            } //Fin del if

                        } //Fin del for



                    } //Fin del primer if





                } //Fin del primer for


            } //Fin del primer if



            if(preferences.getBoolean("GuardarLasVistasDeLasTandasDeshabilitadas",false)){

                DeshabilitarVistasDeLasTandasAlGuardarCambios(true);

            }








    }/*****************FIN DE LA FUNCIÓN RefrescarTandasEnPantalla()**************************/









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












    public void EliminarTandaEnElSharedPreferences(View tanda, SharedPreferences.Editor editor, int i) {



        int tope_de_articulos = 0;


        editor.remove("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado - " + "Tanda Numero: " + i).commit();

        editor.remove("Repartidor: " + nombre_apellido_recibir + "CantidadCargaArticuloNuevaTanda - " + "Tanda Numero: " + i).commit();

        editor.remove("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaArticuloNuevaTanda - " + "Tanda Numero: " + i).commit();

        editor.remove("Repartidor: " + nombre_apellido_recibir + "CantidadCargaMoneyNuevaTanda - " + "Tanda Numero: " + i).commit();

        editor.remove("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaMoneyNuevaTanda - " + "Tanda Numero: " + i).commit();



        final LinearLayout LLV_Tercer_Tupla = (LinearLayout) tanda.findViewById(R.id.layout_vertical_tercer_tupla);


        tope_de_articulos = LLV_Tercer_Tupla.getChildCount()-1;




        for(int j = 1 ; j < LLV_Tercer_Tupla.getChildCount() ; j++){



            editor.remove("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado_NUEVA_TANDA - " + "Tanda Numero: " + i + " - " + "Posicion: " + j).commit();

            editor.remove("Repartidor: " + nombre_apellido_recibir + "CantidadDeCargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + i + " - " + "Posicion: " + j).commit();

            editor.remove("Repartidor: " + nombre_apellido_recibir + "CantidadDeDescargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + i + " - " + "Posicion: " + j).commit();



        } //Fin del for del "j" ("Articulos Programáticos")






        editor.remove("Repartidor: " + nombre_apellido_recibir + "DimensionArticulosProgramaticos - TandaNumero: " + i).commit();




}/*********************************FIN DE LA FUNCIÓN EliminarTandaEnElSharedPreferences()*******************************************/







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









    public void setSpinner(final Spinner spinner, final EditText EditTextCarga,boolean EsSpinnerFijo,
                           final EditText EditTextDescarga) {


    /*********************************************************************************************************/

        ArrayAdapter<String> adaptador;


        if(EsSpinnerFijo){



            adaptador = new ArrayAdapter<String>(this, R.layout.spinner, ArticulosDelArrayClasico);




        } else{



            ArrayList<String> ArticulosReducidos = new ArrayList<String>(Arrays.asList(ArticulosDelArrayClasico));


            /*Llamada a la función: */
            ArticulosReducidos = Utils_Spinner.ReducirListaDeArticulos(spinner, ArticulosReducidos);


            adaptador = new ArrayAdapter<String>(this, R.layout.spinner, ArticulosReducidos);







            /** Si el último artículo programático contiene en su lista un único elemento, entonces se llama a la función:  */
            if(ArticulosReducidos.size() == 1){


                ImageButton btnAgregarNuevoArticulo = Utils_Spinner.ObtenerBotonParaAgregarNuevosArticulos(spinner);

                btnAgregarNuevoArticulo.setEnabled(false);

            } //Fin del if




        } //Fin de else


        /******************************************************************************************/


        spinner.setAdapter(adaptador);



        final String ArticuloSeleccionado = spinner.getSelectedItem().toString();



        /*Llamada a la función: */
        Utils_Spinner.RefrescarOtrosSpinner(spinner ,null, ArticuloSeleccionado,this);



        /*Llamada a la función: */
        Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner(ArticuloSeleccionado,spinner,EditTextCarga,EditTextDescarga);


        /*****************************************************************************/




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
                    Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner(text,spinner,EditTextCarga,EditTextDescarga);





                    /*Llamada a la función: */
                    Utils_Spinner.RefrescarOtrosSpinner((Spinner)adapterView,ArticuloSeleccionadoAnterior,text,Cargas_Descargas.this);




                } //Fin del primer else










            }/**Fin del evento onItemSelected() */





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }/**Fin del evento onNothingSelected() */








        }); /**Fin del evento setOnItemSelectedListener() */





    }   /******************************FIN DE LA FUNCION setSpinner()*****************************/




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


    public void Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner(String ArticuloSeleccionadoText,Spinner spinner, EditText EditTextCarga,
                                                                            EditText EditTextDescarga){





        if (ArticuloSeleccionadoText == "Envases rotos/pinchados" || ArticuloSeleccionadoText == "Envases \n estro- \n peados") {

            EditTextCarga.setEnabled(false);
            EditTextCarga.setHint(" ");
            EditTextCarga.setText("");
            EditTextDescarga.setHint("Cantidad");


        } else {

            EditTextCarga.setEnabled(true);
            EditTextCarga.setHint("Cantidad");
            EditTextDescarga.setHint("Cantidad");


        } //Fin del else



        if (ArticuloSeleccionadoText == "Combus- \n tible") {

            EditTextCarga.setHint("$");
            EditTextDescarga.setEnabled(false);
            EditTextDescarga.setHint("");
            EditTextDescarga.setText("");

            EditTextDescarga.setBackgroundDrawable(getDrawable(R.mipmap.gas_64px));


        } else {

            EditTextDescarga.setEnabled(true);
            EditTextDescarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

        }



    }/***************** Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner() *****************/





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





    @SuppressLint("RestrictedApi")
    public void MostrarValoresDelSharedPreferences() {


        SharedPreferences preferences = getSharedPreferences("Datos_Cargas_Descargas", MODE_PRIVATE);



        if (preferences.getBoolean("Repartidor: " + nombre_apellido_recibir + "flag_nueva_tanda", false)) {

            fab_nueva_tanda.setVisibility(View.VISIBLE);

        }





        /******>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>###########<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<******/
        /*******************Mostrar los valores de los campos que permanecen fijos de las nuevas tandas****************/
        /******>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>###########<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<******/


        String DimensionArrayNuevasTandas = preferences.getString("Repartidor: " + nombre_apellido_recibir + "DimensionArrayNuevasTandas", "");



        if (DimensionArrayNuevasTandas != "") {

            for (int indice_tandas = 0; indice_tandas < Integer.valueOf(DimensionArrayNuevasTandas); indice_tandas++) {



                String ElementoSeleccionadoSpinnerFijo = preferences.getString("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado - " + "Tanda Numero: " + indice_tandas, "");
                String ValorCargaArticulosNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadCargaArticuloNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");
                String ValorDescargaArticulosNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaArticuloNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");
                String ValorCargaMoneyNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadCargaMoneyNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");
                String ValorDescargaMoneyNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaMoneyNuevaTanda - " + "Tanda Numero: " + indice_tandas, "");


                    if(indice_tandas == 0){

                        spinner_fijo.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ElementoSeleccionadoSpinnerFijo,spinner_fijo));
                        eTcantCarga.setText(ValorCargaArticulosNuevaTanda);
                        eTcantDescarga.setText(ValorDescargaArticulosNuevaTanda);
                        eTcantMoneyCarga.setText(ValorCargaMoneyNuevaTanda);
                        eTcantMoneyDescarga.setText(ValorDescargaMoneyNuevaTanda);


                    } else {

                        /* Llamada a la función: */
                        ObtenerNuevaTanda(ElementoSeleccionadoSpinnerFijo,ValorCargaArticulosNuevaTanda, ValorDescargaArticulosNuevaTanda,
                                ValorCargaMoneyNuevaTanda,ValorDescargaMoneyNuevaTanda);


                    }








                /******>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>###########<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<******/
                /*******Ahora llevar a cabo lo mismo pero para salvar los valores de los campos de los nuevos artículos*******/
                /******>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>###########<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<******/


                String DimensionArticulosProgramaticos = preferences.getString("Repartidor: " + nombre_apellido_recibir + "DimensionArticulosProgramaticos - TandaNumero: " + indice_tandas, "");

                if (DimensionArticulosProgramaticos != "") {

                    for (int j = 1; j <= Integer.valueOf(DimensionArticulosProgramaticos); j++) {



                        String ElementoSeleccionadoSpinnerProgramatico = preferences.getString("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado_NUEVA_TANDA - " + "Tanda Numero: " + indice_tandas + " - " + "Posicion: " + j, "");
                        String ValorCargaNuevoArticuloNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDeCargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + indice_tandas + " - " + "Posicion: " + j, "");
                        String ValorDescargaNuevoArticuloNuevaTanda = preferences.getString("Repartidor: " + nombre_apellido_recibir + "CantidadDeDescargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + indice_tandas + " - " + "Posicion: " + j, "");



                        if (ValorCargaNuevoArticuloNuevaTanda != "" || ValorDescargaNuevoArticuloNuevaTanda != "") {

                            final View tanda = ArrayListTandas.get(indice_tandas);

                            final LinearLayout tercerTuplaNuevaTanda = (LinearLayout) tanda.findViewById(R.id.layout_vertical_tercer_tupla);


                            if (indice_tandas == 0) {

                                Utils_Spinner.contador_de_inicializacion--;

                                ObtenerNuevoArticulo(ElementoSeleccionadoSpinnerProgramatico,ValorCargaNuevoArticuloNuevaTanda, ValorDescargaNuevoArticuloNuevaTanda, tercerTuplaNuevaTanda);

                            } else{

                                Utils_Spinner.contador_de_inicializacion--;

                                ObtenerNuevoArticulo(ElementoSeleccionadoSpinnerProgramatico,ValorCargaNuevoArticuloNuevaTanda, ValorDescargaNuevoArticuloNuevaTanda, tercerTuplaNuevaTanda);

                            }

                        } //Fin del primer if

                    } //Fin del for

                } //Fin del primer if





            } //Fin del primer for


         } //Fin del primer if




        if(preferences.getBoolean("Repartidor: " + nombre_apellido_recibir + "GuardarLasVistasDeLasTandasDeshabilitadas",false)){

            DeshabilitarVistasDeLasTandasAlGuardarCambios(true);

        }






    }  /*****************FIN DE LA FUNCION MostrarValoresDelSharedPreferences()*******************












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





    int ChildNuevoArticulo = 0;

    public void ObtenerNuevoArticulo(String ValorElementoSeleccionadoSpinnerProgramatico,String Valor_a_SetearDelEditTextCargaParaNuevoArticuloPrimerTanda,
                                                                  String Valor_a_SetearDelEditTextDescargaParaNuevoArticuloPrimerTanda, final LinearLayout tercerTupla) {

        View NuevoArticuloInflado;

        /*Llamada a la función: */
        NuevoArticuloInflado = AgregarNuevoArticulo(tercerTupla);



        /* Para cambiar el color del puntero o "burbuja" del EditText */
        setTheme(R.style.AppTheme_CursorSupervisor);

        /** Instanciamos las vistas del diseño XML: "nuevo_articulo.xml" **/

        final ImageButton btnEliminarArticulo = (ImageButton) NuevoArticuloInflado.findViewById(R.id.delete_art);

        btnEliminarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EliminarNuevoArticulo(v);



            }
        });



        final EditText EditText_Carga_Nuevo_Articulo = (EditText) NuevoArticuloInflado.findViewById(R.id.edtx_carga_new_art);
        EditText_Carga_Nuevo_Articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


        if(Valor_a_SetearDelEditTextCargaParaNuevoArticuloPrimerTanda != ""){


            EditText_Carga_Nuevo_Articulo.setText(Valor_a_SetearDelEditTextCargaParaNuevoArticuloPrimerTanda);


        }




        final EditText EditText_Descarga_Nuevo_Articulo= (EditText) NuevoArticuloInflado.findViewById(R.id.edtx_descarga_new_art);
        EditText_Descarga_Nuevo_Articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



        if(Valor_a_SetearDelEditTextDescargaParaNuevoArticuloPrimerTanda != ""){


            EditText_Descarga_Nuevo_Articulo.setText(Valor_a_SetearDelEditTextDescargaParaNuevoArticuloPrimerTanda);


        }





        final Spinner spinner_nuevos_articulos = (Spinner) NuevoArticuloInflado.findViewById(R.id.sp_new_art);


        /*Llamada a la función: */
        setSpinner(spinner_nuevos_articulos, EditText_Carga_Nuevo_Articulo,false, EditText_Descarga_Nuevo_Articulo);


        ArticuloSeleccionadoAnterior = spinner_nuevos_articulos.getSelectedItem().toString();


        spinner_nuevos_articulos.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerProgramatico,spinner_nuevos_articulos));





        if (ValorElementoSeleccionadoSpinnerProgramatico != ""){


            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinner(spinner_nuevos_articulos,ArticuloSeleccionadoAnterior,ValorElementoSeleccionadoSpinnerProgramatico,this);


        } else{

             Utils_Spinner.contador_de_inicializacion = 0;

            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinner(spinner_nuevos_articulos,null,ArticuloSeleccionadoAnterior,this);


        }


        /************ Cambiar color del cursor de cada EditText *************/
        Field f = null;

        try {

            f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(EditText_Carga_Nuevo_Articulo, R.drawable.color_cursor_supervisores);
            f.set(EditText_Descarga_Nuevo_Articulo, R.drawable.color_cursor_supervisores);




        } catch (NoSuchFieldException | IllegalAccessException e) {
        e.printStackTrace();
        }






    } /**************************************FIN DE LA FUNCION ObtenerNuevoArticulo()*******************************************/







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





    /********** Función para añadir una identica "tercer tupla" con cada clic del boton "add_art"*********/



    public View AgregarNuevoArticulo(final LinearLayout LayoutVerticalTercerTupla) {


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflater.inflate(R.layout.nuevo_articulo, null, true);

        LayoutVerticalTercerTupla.addView(InflatedView);

        ChildNuevoArticulo = LayoutVerticalTercerTupla.getChildCount();


        return InflatedView;




    } /***************************FIN DE LA FUNCION AgregarNuevoArticulo()************************************/



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




    public void EliminarNuevoArticulo(View btnEliminarNuevoArticulo) {


        /*Llamada a la función: */
        ImageButton btnAgregarNuevoArticulo = Utils_Spinner.ObtenerBotonParaAgregarNuevosArticulos(btnEliminarNuevoArticulo);

        btnAgregarNuevoArticulo.setEnabled(true);




        LinearLayout LinearHorizontal = (LinearLayout) btnEliminarNuevoArticulo.getParent();

        Spinner spinner = null;

        String ArticuloSeleccionado = null;

        for(int contador_item = 0; contador_item < LinearHorizontal.getChildCount(); contador_item++){


            View v = LinearHorizontal.getChildAt(contador_item);


            if(v instanceof Spinner){


            spinner = (Spinner) v;

            ArticuloSeleccionado = spinner.getSelectedItem().toString();

            } //Fin del if


        }//Fin del for





      /*Llamada a la función: */
        Utils_Spinner.RefrescarOtrosSpinner(spinner,ArticuloSeleccionado,null,this);






        final LinearLayout ChildLinearLayoutHorizontalNuevosArticulos = (LinearLayout) btnEliminarNuevoArticulo.getParent();

        final LinearLayout LLV_Padre = (LinearLayout) ChildLinearLayoutHorizontalNuevosArticulos.getParent();

        LLV_Padre.removeView(ChildLinearLayoutHorizontalNuevosArticulos);




    } /***********************************FIN DE LA FUNCIÓN EliminarNuevoArticulo()*****************************************/



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








    int ChildNuevaTanda = 0;

    public void ObtenerNuevaTanda(String ValorElementoSeleccionadoSpinnerFijo,String ValorSeteadoDelEditTextCargaDeArticulosFijoParaNuevaTanda,
                                                            String ValorSeteadoDelEditTextDescargaDeArticulosFijoParaNuevaTanda, String ValorSeteadoDelEditTextCargaDeMoneyFijoParaNuevaTanda,
                                                            String ValorSeteadoDelEditTextDescargaDeMoneyFijoParaNuevaTanda) {



        /****Evento abierto****/
        if(Estado_Tanda){



            View NuevaTandaInflada;

            /*Llamada a la función: */
             NuevaTandaInflada = AgregarNuevaTanda();

            ArrayListTandas.add(NuevaTandaInflada);


            /* Para cambiar el color del puntero o "burbuja" del EditText */
            setTheme(R.style.AppTheme_CursorSupervisor);



            final LinearLayout LinearLayoutTercerTuplaProgramatica = (LinearLayout) NuevaTandaInflada.findViewById(R.id.layout_vertical_tercer_tupla);


            /**Acceder a la vista TextView "Tanda N°2" y las siguientes**/

            TextView textViewNuevaTanda = (TextView) NuevaTandaInflada.findViewById(R.id.tandas);
            tanda_numero++;
            textViewNuevaTanda.setText("Tanda N° " + tanda_numero);



            /**Instanciamos las vistas para la Nueva Tanda*/

            final ImageButton btnAgregarNuevoArticuloParaLaNuevaTanda = (ImageButton) NuevaTandaInflada.findViewById(R.id.add_art);

            btnAgregarNuevoArticuloParaLaNuevaTanda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ValidarCamposParaAñadirNuevoArticulo(LinearLayoutTercerTuplaProgramatica);


                }
            });


            final EditText et_carga_nueva_tanda = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_carga);
            et_carga_nueva_tanda.requestFocus();
            et_carga_nueva_tanda.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
            et_carga_nueva_tanda.setText(ValorSeteadoDelEditTextCargaDeArticulosFijoParaNuevaTanda);



            final EditText et_descarga_nueva_tanda = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_descarga);
            et_descarga_nueva_tanda.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
            et_descarga_nueva_tanda.setText(ValorSeteadoDelEditTextDescargaDeArticulosFijoParaNuevaTanda);



            final EditText et_carga_nueva_tanda_money = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_carga_money);
            et_carga_nueva_tanda_money.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
            et_carga_nueva_tanda_money.setText(ValorSeteadoDelEditTextCargaDeMoneyFijoParaNuevaTanda);




            final EditText et_descarga_nueva_tanda_money = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_descarga_money);
            et_descarga_nueva_tanda_money.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));
            et_descarga_nueva_tanda_money.setText(ValorSeteadoDelEditTextDescargaDeMoneyFijoParaNuevaTanda);






            final Spinner spinner_fijo_nueva_tanda = (Spinner) NuevaTandaInflada.findViewById(R.id.sp_art);


            Utils_Spinner.contador_de_inicializacion = 0;


            /*Llamada a la función: */
            setSpinner(spinner_fijo_nueva_tanda, et_carga_nueva_tanda,true, et_descarga_nueva_tanda);


            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_tanda.getSelectedItem().toString();


            spinner_fijo_nueva_tanda.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerFijo,spinner_fijo_nueva_tanda));


            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_tanda.getSelectedItem().toString();



            /************ Cambiar color del cursor de cada EditText *************/
            Field f = null;

            try {

                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                f.setAccessible(true);
                f.set(et_carga_nueva_tanda, R.drawable.color_cursor_supervisores);
                f.set(et_descarga_nueva_tanda, R.drawable.color_cursor_supervisores);
                f.set(et_carga_nueva_tanda_money, R.drawable.color_cursor_supervisores);
                f.set(et_descarga_nueva_tanda_money, R.drawable.color_cursor_supervisores);



            }  catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }


        }/***** FIN tandas habilitadas *****/




        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/
        /*******************************************************************************************/




        else{



            View NuevaTandaInflada;

            /*Llamada a la función: */
            NuevaTandaInflada = AgregarNuevaTanda();

            ArrayListTandas.add(NuevaTandaInflada);


            /* Para cambiar el color del puntero o "burbuja" del EditText */
            setTheme(R.style.AppTheme_CursorSupervisor);



            final LinearLayout LinearLayoutVerticalProgramaticoConBorde = (LinearLayout) findViewById(R.id.llv_contenedor_para_bordes);
            LinearLayoutVerticalProgramaticoConBorde.setBackground(getDrawable(R.drawable.borde_linear_layout_evento_deshabilitado));


            final LinearLayout LinearLayoutSegundaTuplaProgramatica_Patrocinio = (LinearLayout) findViewById(R.id.layout_horizontal_segunda_tupla);
            LinearLayoutSegundaTuplaProgramatica_Patrocinio.setBackgroundColor(Color.parseColor("#616161"));




            final LinearLayout LinearLayoutTercerTuplaProgramatica = (LinearLayout) NuevaTandaInflada.findViewById(R.id.layout_vertical_tercer_tupla);


            /**Acceder a la vista TextView "Tanda N°2" y las siguientes**/

            TextView textViewNuevaTanda = (TextView) NuevaTandaInflada.findViewById(R.id.tandas);
            textViewNuevaTanda.setBackgroundColor(Color.parseColor("#424242"));
            tanda_numero++;
            textViewNuevaTanda.setText("Tanda N° " + tanda_numero);



            /**Instanciamos las vistas para la Nueva Tanda*/

            final ImageButton btnAgregarNuevoArticuloParaLaNuevaTanda = (ImageButton) NuevaTandaInflada.findViewById(R.id.add_art);

            btnAgregarNuevoArticuloParaLaNuevaTanda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ValidarCamposParaAñadirNuevoArticulo(LinearLayoutTercerTuplaProgramatica);


                }
            });


            final EditText et_carga_nueva_tanda = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_carga);
            et_carga_nueva_tanda.requestFocus();
            et_carga_nueva_tanda.setText(ValorSeteadoDelEditTextCargaDeArticulosFijoParaNuevaTanda);



            final EditText et_descarga_nueva_tanda = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_descarga);
            et_descarga_nueva_tanda.setText(ValorSeteadoDelEditTextDescargaDeArticulosFijoParaNuevaTanda);



            final EditText et_carga_nueva_tanda_money = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_carga_money);
            et_carga_nueva_tanda_money.setText(ValorSeteadoDelEditTextCargaDeMoneyFijoParaNuevaTanda);




            final EditText et_descarga_nueva_tanda_money = (EditText) NuevaTandaInflada.findViewById(R.id.edtx_descarga_money);
            et_descarga_nueva_tanda_money.setText(ValorSeteadoDelEditTextDescargaDeMoneyFijoParaNuevaTanda);






            final Spinner spinner_fijo_nueva_tanda = (Spinner) NuevaTandaInflada.findViewById(R.id.sp_art);


            Utils_Spinner.contador_de_inicializacion = 0;


            /*Llamada a la función: */
            setSpinner(spinner_fijo_nueva_tanda, et_carga_nueva_tanda,true, et_descarga_nueva_tanda);


            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_tanda.getSelectedItem().toString();


            spinner_fijo_nueva_tanda.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerFijo,spinner_fijo_nueva_tanda));


            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_tanda.getSelectedItem().toString();





        }/** FIN DEL else tandas cerradas **/



    } /*****************************FIN DE LA FUNCIÓN ObtenerNuevaTanda()*****************************/


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







    @SuppressLint("RestrictedApi")
    public View AgregarNuevaTanda(){


    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    final View InflatedView = inflater.inflate(R.layout.nueva_tanda, null, true);


    LinearLayoutNuevaTanda.addView(InflatedView,0);







        InflatedView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                /*Llamada a la función: */
                EliminarCualquierTanda(v);

                return false;


            }/**Fin del evento onLongClick() */


        });  /**Fin del evento setOnLongClickListener() */








    ChildNuevaTanda = LinearLayoutNuevaTanda.getChildCount();


    /*Propiedades que permiten esconder el fab de añadir nueva tanda y de hacer visible
      al fab de eliminar tanda*/
    fab_nueva_tanda.setVisibility(GONE);
    fab_cancel_tanda.setVisibility(View.VISIBLE);

        /*Mensaje una vez añadida la nueva tanda*/
        Snackbar.make(InflatedView, "Nueva tanda añadida", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();





    return InflatedView;


    } /***************************FIN DE LA FUNCIÓN AgregarNuevaTanda()*****************************/





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



    @SuppressLint("RestrictedApi")
    public void CancelarNuevaTanda(View v) {



         LinearLayoutNuevaTanda.removeViewAt(0);



        /*Una vez que se elimine la tanda, el botón para añadir una nueva tanda se hará visible,
          mientras que el boton de cancelar tanda se esconderá*/

        fab_cancel_tanda.setVisibility(View.GONE);
        fab_nueva_tanda.setVisibility(View.VISIBLE);




        /*Si eliminamos una nueva tanda...*/
        tanda_numero--;




        /*Mensaje una vez añadida la nueva tanda*/
        Snackbar.make(v, "Tanda eliminada", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();




        /* Para eliminar todas las vistas incluyendo valores de los campos de una Tanda específica */
        ArrayListTandas.remove(ArrayListTandas.size() -1);




    } /**********************FIN DE LA FUNCIÓN CancelarNuevaTanda()********************************/






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












    Boolean bandera_boleana_nuevos_articulos = false;

    /**
     * FUNCION PARA CONFIRMAR QUE ANTES DE AÑADIR UN NUEVO ARTICULO SE VALIDE ALGUNOS DE LOS CAMPOS FIJOS DE LA NUEVA TANDA
     **/



    public void ValidarCamposParaAñadirNuevoArticulo(LinearLayout tercerTupla){



        for(int i=0; i<ArrayListTandas.size();i++){


            final EditText editText_carga = (EditText) ArrayListTandas.get(i).findViewById(R.id.edtx_carga);

            final EditText editText_descarga = (EditText) ArrayListTandas.get(i).findViewById(R.id.edtx_descarga);

            /** Primer Validación: Si al menos uno de los campos de Carga y Descarga de artículos contiene
             * un valor se guardarán los cambios. Caso contrario se arrojará un mensaje de error*/


            if (!editText_carga.getText().toString().isEmpty() || !editText_descarga.getText().toString().isEmpty() ){

                bandera_boleana_nuevos_articulos = true;

            }



            if(editText_carga.getText().toString().isEmpty() && editText_descarga.getText().toString().isEmpty() ){

                Toast.makeText(getApplicationContext(), "Recuerde! Para añadir un nuevo artículo el campo de 'Carga' o 'Descarga' de artículos debe estar completo.", Toast.LENGTH_LONG).show();

                bandera_boleana_nuevos_articulos = false;

            }



            /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
             *  de guardar valores nulos se arrojará un mensaje de error */

            if (editText_carga.getText().toString().length() > 0 && editText_carga.getText().toString().charAt(0) == '0'
                    || editText_descarga.getText().toString().length() > 0 && editText_descarga.getText().toString().charAt(0) == '0'){

                Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos o que el primer" +
                        " dígito comience con cero ", Toast.LENGTH_LONG).show();

                bandera_boleana_nuevos_articulos = false;
            }




            /**VALIDACIÓN DE CAMPOS DE CARGA Y DESCARGA PARA EL NUEVO ARTICULO**/


            final LinearLayout LLV_Tercer_Tupla = (LinearLayout) ArrayListTandas.get(i).findViewById(R.id.layout_vertical_tercer_tupla);

            for(int j = 1 ; j < LLV_Tercer_Tupla.getChildCount() ; j++){

                final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla.getChildAt(j);

                final EditText et_carga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                final EditText et_descarga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);


                /** Primer Validación: Si al menos uno de los campos ya mencionados contiene un valor se guardarán los cambios. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!et_carga_del_nuevo_articulo.getText().toString().isEmpty() || !et_descarga_del_nuevo_articulo.getText().toString().isEmpty()){

                    bandera_boleana_nuevos_articulos = true;
                }


                if (et_carga_del_nuevo_articulo.getText().toString().isEmpty() && et_descarga_del_nuevo_articulo.getText().toString().isEmpty()) {

                    bandera_boleana_nuevos_articulos = false;

                    Toast.makeText(getApplicationContext(), "Error! los campos del nuevo artículo añadido están vacíos. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();

                }




                /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                 *  de guardar valores nulos se arrojará un mensaje de error */

                if (et_carga_del_nuevo_articulo.getText().toString().length() > 0 && et_carga_del_nuevo_articulo.getText().toString().charAt(0) == '0'
                        || et_descarga_del_nuevo_articulo.getText().toString().length() > 0 && et_descarga_del_nuevo_articulo.getText().toString().charAt(0) == '0'){

                    Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos o que el primer" +
                            " dígito comience con cero ", Toast.LENGTH_LONG).show();

                    bandera_boleana_nuevos_articulos = false;
                }








            } //Fin del for del "j" ("Articulos Programáticos")



        } //Fin del primer for



        if (bandera_boleana_nuevos_articulos) {



            /*Llamar a la función*/

            ObtenerNuevoArticulo("","","",tercerTupla);

            Toast.makeText(getApplicationContext(), "Nuevo artículo añadido", Toast.LENGTH_LONG).show();

        } //Fin del  if




    }/****************FIN DE LA FUNCIÓN ValidarCamposParaAñadirNuevoArticulo()*****************************/










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







    boolean flag_validacion_campos = false;

    /**FUNCION PARA VALIDAR SI LOS CAMPOS DE TEXTOS CONTIENEN ALGÚN VALOR**/

    @SuppressLint("RestrictedApi")
    public boolean ValidarTodosLosCamposParaGuardarCambios() {


        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {


                for(int k=0; k < ArrayListTandas.size(); k++){

                    final EditText editText_carga = (EditText) ArrayListTandas.get(k).findViewById(R.id.edtx_carga);

                    final EditText editText_descarga = (EditText) ArrayListTandas.get(k).findViewById(R.id.edtx_descarga);

                    final EditText et_carga_money = (EditText)  ArrayListTandas.get(k).findViewById(R.id.edtx_carga_money);

                    final EditText et_descarga_money = (EditText)  ArrayListTandas.get(k).findViewById(R.id.edtx_descarga_money);



                    /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                     * se arrojará un mensaje de error*/

                    if (!editText_carga.getText().toString().isEmpty() || !editText_descarga.getText().toString().isEmpty()
                            || !et_carga_money.getText().toString().isEmpty() || !et_descarga_money.getText().toString().isEmpty()){

                        flag_validacion_campos = true;

                    }





                    if (editText_carga.getText().toString().isEmpty() && editText_descarga.getText().toString().isEmpty() && et_carga_money.getText().toString().isEmpty()
                       && et_descarga_money.getText().toString().isEmpty()){

                        Toast.makeText(getApplicationContext(), "Error! Los campos de la nueva tanda están vacíos." +
                                " Por favor, complete al menos uno de los campos", Toast.LENGTH_LONG).show();

                        flag_validacion_campos = false;

                    }






                    /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                     *  de guardar valores nulos se arrojará un mensaje de error */

                    if (editText_carga.getText().toString().length() > 0 && editText_carga.getText().toString().charAt(0) == '0'
                        || editText_descarga.getText().toString().length() > 0 && editText_descarga.getText().toString().charAt(0) == '0'
                     || et_carga_money.getText().toString().length() > 0 && et_carga_money.getText().toString().charAt(0) == '0'
                     || et_descarga_money.getText().toString().length() > 0 && et_descarga_money.getText().toString().charAt(0) == '0'){

                         Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos" +
                                 " o que el primer dígito comience con cero ", Toast.LENGTH_LONG).show();

                         flag_validacion_campos = false;
                    }







                    /**VALIDACIÓN DE CAMPOS DE CARGA Y DESCARGA PARA LOS NUEVOS ARTICULOS**/

                    final LinearLayout LLV_Tercer_Tupla = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.layout_vertical_tercer_tupla);

                     for(int l = 1 ; l < LLV_Tercer_Tupla.getChildCount() ; l++){

                        final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla.getChildAt(l);

                        final EditText et_carga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                        final EditText et_descarga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);


                         /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                          * se arrojará un mensaje de error*/

                         if (!et_carga_del_nuevo_articulo.getText().toString().isEmpty() || !et_descarga_del_nuevo_articulo.getText().toString().isEmpty()){

                            flag_validacion_campos = true;
                        }


                         if (et_carga_del_nuevo_articulo.getText().toString().isEmpty() && et_descarga_del_nuevo_articulo.getText().toString().isEmpty()) {

                            flag_validacion_campos = false;

                            Toast.makeText(getApplicationContext(), "Error! los campos del nuevo artículo añadido están vacíos. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();

                        }



                         /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                          *  de guardar valores nulos se arrojará un mensaje de error */


                         if (et_carga_del_nuevo_articulo.getText().toString().length() > 0 && et_carga_del_nuevo_articulo.getText().toString().charAt(0) == '0'
                                 || et_descarga_del_nuevo_articulo.getText().toString().length() > 0 && et_descarga_del_nuevo_articulo.getText().toString().charAt(0) == '0'){

                             Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos o que el primer" +
                                     " dígito comience con cero ", Toast.LENGTH_LONG).show();

                             flag_validacion_campos = false;
                         }






                    } //Fin del for del "l" ("Articulos Programáticos")




                } //Fin del primer for










            if (flag_validacion_campos) {

                Toast.makeText(getApplicationContext(), "Los cambios fueron guardados con éxito", Toast.LENGTH_LONG).show();

                /** Al comenzar con la primer tanda, en el momento de guardar se llevará a cabo lo siguiente: **/

                fab_nueva_tanda.setVisibility(View.VISIBLE);

                fab_cancel_tanda.setVisibility(GONE);


            } //Fin del if (flag){}


        } /*Fin del 'for'*/

        return flag_validacion_campos;

    } /*********************************FIN DE LA FUNCIÓN ValidarTodosLosCamposParaGuardarCambios() ****************************/






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





    /**********FUNCIÓN PARA GUARDAR CAMBIOS REALIZADOS EN CADA TANDA**************************/

        public boolean DeshabilitarVistasDeLasTandasAlGuardarCambios(boolean flag_enabled){

            if (flag_enabled) {


            /** Deshabilitar los campos de carga y descarga "fijos" y también de los nuevos artículos de todas las Tandas **/

                for(int k=0; k < ArrayListTandas.size(); k++){

                    final ImageButton btnAgregarNuevoArticuloParaLaNuevaTanda = (ImageButton) ArrayListTandas.get(k).findViewById(R.id.add_art);

                    final Spinner sp_nueva_tanda = (Spinner) ArrayListTandas.get(k).findViewById(R.id.sp_art);

                    final EditText editText_carga_nueva_tanda = (EditText) ArrayListTandas.get(k).findViewById(R.id.edtx_carga);

                    final EditText editText_descarga_nueva_tanda = (EditText) ArrayListTandas.get(k).findViewById(R.id.edtx_descarga);

                    final EditText editText_carga_money_nueva_tanda = (EditText)  ArrayListTandas.get(k).findViewById(R.id.edtx_carga_money);

                    final EditText editText_descarga_money_nueva_tanda = (EditText)  ArrayListTandas.get(k).findViewById(R.id.edtx_descarga_money);

                    btnAgregarNuevoArticuloParaLaNuevaTanda.setVisibility(GONE);



                    sp_nueva_tanda.setEnabled(false);




                    editText_carga_nueva_tanda.setFocusable(false);
                    editText_carga_nueva_tanda.setCursorVisible(false);
                    editText_carga_nueva_tanda.setHint("");
                    editText_carga_nueva_tanda.setHintTextColor(Color.parseColor("#fafafa"));
                    editText_carga_nueva_tanda.setBackgroundColor(Color.TRANSPARENT);



                    editText_descarga_nueva_tanda.setFocusable(false);
                    editText_descarga_nueva_tanda.setCursorVisible(false);
                    editText_descarga_nueva_tanda.setHint("");
                    editText_descarga_nueva_tanda.setHintTextColor(Color.parseColor("#fafafa"));
                    editText_descarga_nueva_tanda.setBackgroundColor(Color.TRANSPARENT);


                    editText_carga_money_nueva_tanda.setFocusable(false);
                    editText_carga_money_nueva_tanda.setCursorVisible(false);
                    editText_carga_money_nueva_tanda.setHint("");
                    editText_carga_money_nueva_tanda.setHintTextColor(Color.parseColor("#fafafa"));
                    editText_carga_money_nueva_tanda.setBackgroundColor(Color.TRANSPARENT);


                    editText_descarga_money_nueva_tanda.setFocusable(false);
                    editText_descarga_money_nueva_tanda.setCursorVisible(false);
                    editText_descarga_money_nueva_tanda.setHint("");
                    editText_descarga_money_nueva_tanda.setHintTextColor(Color.parseColor("#fafafa"));
                    editText_descarga_money_nueva_tanda.setBackgroundColor(Color.TRANSPARENT);









                    final LinearLayout LLV_Tercer_Tupla = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.layout_vertical_tercer_tupla);

                    for(int l = 1 ; l < LLV_Tercer_Tupla.getChildCount() ; l++){

                        final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla.getChildAt(l);

                        final ImageButton btnDeleteNuevoArticuloParaLaNuevaTanda = (ImageButton) LLH_Nuevo_Articulo.findViewById(R.id.delete_art);

                        final Spinner sp_nuevo_articulo_nueva_tanda = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                        final EditText et_carga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                        final EditText et_descarga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);

                        btnDeleteNuevoArticuloParaLaNuevaTanda.setVisibility(GONE);


                        sp_nuevo_articulo_nueva_tanda.setEnabled(false);



                        et_carga_del_nuevo_articulo.setFocusable(false);
                        et_carga_del_nuevo_articulo.setCursorVisible(false);
                        et_carga_del_nuevo_articulo.setHint("");
                        et_carga_del_nuevo_articulo.setHintTextColor(Color.parseColor("#fafafa"));
                        et_carga_del_nuevo_articulo.setBackgroundColor(Color.TRANSPARENT);



                        et_descarga_del_nuevo_articulo.setFocusable(false);
                        et_descarga_del_nuevo_articulo.setCursorVisible(false);
                        et_descarga_del_nuevo_articulo.setHint("");
                        et_descarga_del_nuevo_articulo.setHintTextColor(Color.parseColor("#fafafa"));
                        et_descarga_del_nuevo_articulo.setBackgroundColor(Color.TRANSPARENT);


                    } //Fin del for del "l" ("Articulos Programáticos")


                } //Fin del primer for


       }  /*Fin del primer if (flag_enabled) {}*/



        return flag_enabled;



        }/*******FIN DE LA FUNCIÓN DeshabilitarVistasDeLasTandasAlGuardarCambios() ********/




    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/
    /**####################################################################################**/







    public void EditarTandas(boolean flag_edit){

        if (flag_edit) {


            /** Habilitar todas las vistas de todas las Tandas creadas **/

            for(int k=0; k < ArrayListTandas.size(); k++){

                final ImageButton btnAgregarNuevoArticuloParaLaNuevaTanda = (ImageButton) ArrayListTandas.get(k).findViewById(R.id.add_art);

                final Spinner sp_nueva_tanda = (Spinner) ArrayListTandas.get(k).findViewById(R.id.sp_art);

                final EditText editText_carga = (EditText) ArrayListTandas.get(k).findViewById(R.id.edtx_carga);

                final EditText editText_descarga = (EditText) ArrayListTandas.get(k).findViewById(R.id.edtx_descarga);

                final EditText editText_carga_money = (EditText)  ArrayListTandas.get(k).findViewById(R.id.edtx_carga_money);

                final EditText editText_descarga_money = (EditText)  ArrayListTandas.get(k).findViewById(R.id.edtx_descarga_money);


                btnAgregarNuevoArticuloParaLaNuevaTanda.setVisibility(View.VISIBLE);




                sp_nueva_tanda.setEnabled(true);


                String text_spinner_fijo = sp_nueva_tanda.getSelectedItem().toString();



                if(text_spinner_fijo == "Envases rotos/pinchados" || text_spinner_fijo == "Envases \n estro- \n peados"){


                    editText_carga.setEnabled(false);
                    editText_carga.setHint(" ");
                    editText_carga.setText("");
                    editText_carga.setHint("Cantidad");


                } else {


                    editText_carga.setFocusableInTouchMode(true);
                    editText_carga.requestFocus();
                    editText_carga.setCursorVisible(true);
                    editText_carga.setHint("Cantidad");
                    editText_carga.setHintTextColor(Color.parseColor("#9e9e9e"));
                    editText_carga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));





                    editText_descarga.setFocusableInTouchMode(true);
                    editText_descarga.setCursorVisible(true);
                    editText_descarga.setHint("Cantidad");
                    editText_descarga.setHintTextColor(Color.parseColor("#9e9e9e"));
                    editText_descarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

                }


                if (text_spinner_fijo == "Combus- \n tible") {

                    editText_carga.setHint("$");
                    editText_descarga.setEnabled(false);
                    editText_descarga.setHint("");
                    editText_descarga.setText("");

                    editText_descarga.setBackgroundDrawable(getDrawable(R.drawable.ic_combustible));


                } else {

                    editText_descarga.setEnabled(true);
                    editText_descarga.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

                }




                editText_carga_money.setFocusableInTouchMode(true);
                editText_carga_money.setCursorVisible(true);
                editText_carga_money.setHint("$");
                editText_carga_money.setHintTextColor(Color.parseColor("#9e9e9e"));
                editText_carga_money.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                editText_descarga_money.setFocusableInTouchMode(true);
                editText_descarga_money.setCursorVisible(true);
                editText_descarga_money.setHint("$");
                editText_descarga_money.setHintTextColor(Color.parseColor("#9e9e9e"));
                editText_descarga_money.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                final LinearLayout LLV_Tercer_Tupla = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.layout_vertical_tercer_tupla);

                for(int l = 1 ; l < LLV_Tercer_Tupla.getChildCount() ; l++){

                    final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla.getChildAt(l);

                    final ImageButton btnDeleteNuevoArticulo = (ImageButton) LLH_Nuevo_Articulo.findViewById(R.id.delete_art);

                    final Spinner sp_nuevo_del_articulo = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                    final EditText et_carga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                    final EditText et_descarga_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);




                    btnDeleteNuevoArticulo.setVisibility(View.VISIBLE);






                    sp_nuevo_del_articulo.setEnabled(true);

                    String text_spinner_programatico = sp_nuevo_del_articulo.getSelectedItem().toString();


                    if(text_spinner_programatico == "Envases rotos/pinchados" || text_spinner_programatico == "Envases \n estro- \n peados"){


                        et_carga_del_nuevo_articulo.setEnabled(false);
                        et_carga_del_nuevo_articulo.setHint(" ");
                        et_carga_del_nuevo_articulo.setText("");
                        et_carga_del_nuevo_articulo.setHint("Cantidad");


                    } else {




                        et_carga_del_nuevo_articulo.setFocusableInTouchMode(true);
                        et_carga_del_nuevo_articulo.setCursorVisible(true);
                        et_carga_del_nuevo_articulo.setHint("Cantidad");
                        et_carga_del_nuevo_articulo.setHintTextColor(Color.parseColor("#9e9e9e"));
                        et_carga_del_nuevo_articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                        et_descarga_del_nuevo_articulo.setFocusableInTouchMode(true);
                        et_descarga_del_nuevo_articulo.setCursorVisible(true);
                        et_descarga_del_nuevo_articulo.setHint("Cantidad");
                        et_descarga_del_nuevo_articulo.setHintTextColor(Color.parseColor("#9e9e9e"));
                        et_descarga_del_nuevo_articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



                    }




                    if (text_spinner_programatico == "Combus- \n tible") {

                        et_carga_del_nuevo_articulo.setHint("$");
                        et_descarga_del_nuevo_articulo.setEnabled(false);
                        et_descarga_del_nuevo_articulo.setHint("");
                        et_descarga_del_nuevo_articulo.setText("");

                        et_descarga_del_nuevo_articulo.setBackgroundDrawable(getDrawable(R.drawable.ic_combustible));


                    } else {

                        et_descarga_del_nuevo_articulo.setEnabled(true);
                        et_descarga_del_nuevo_articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));

                    }





                } //Fin del for del "l" ("Articulos Programáticos")


            } //Fin del primer for


        }  /*Fin del primer if (flag_edit) {}*/

    }/*******FIN DE LA FUNCIÓN EditarTandas() ********/







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

        public void GuardarValoresEnSharedPreferences() {

            SharedPreferences sharedPreferences = getSharedPreferences("Datos_Cargas_Descargas", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            /*Guardar el estado del botón flotante "AgregarNuevaTanda" que después el SharedPrefrences lo leerá
             * cuando vuelva a entrar a la aplicación para que luego dicho botón se hará visible  */


            editor.putBoolean("Repartidor: " + nombre_apellido_recibir + "flag_nueva_tanda", true);

            editor.commit();


            /*****Guardar valor de Carga y Descarga de artículos y dinero, incluyendo los "nuevos artículos" de todas las Tandas*****/

                for (int i = 0; i < ArrayListTandas.size(); i++) {



                    int tope_de_articulos = 0;

                    final Spinner spinner_fijo = (Spinner) ArrayListTandas.get(i).findViewById(R.id.sp_art);

                    final EditText editText_carga = (EditText) ArrayListTandas.get(i).findViewById(R.id.edtx_carga);

                    final EditText editText_descarga = (EditText) ArrayListTandas.get(i).findViewById(R.id.edtx_descarga);

                    final EditText et_carga_money = (EditText)  ArrayListTandas.get(i).findViewById(R.id.edtx_carga_money);

                    final EditText et_descarga_money = (EditText)  ArrayListTandas.get(i).findViewById(R.id.edtx_descarga_money);



                    String ArticuloSeleccionado =  spinner_fijo.getSelectedItem().toString();

                    editor.putString("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado - " + "Tanda Numero: " + i, ArticuloSeleccionado);

                    editor.putString("Repartidor: " + nombre_apellido_recibir + "CantidadCargaArticuloNuevaTanda - " + "Tanda Numero: " + i, editText_carga.getText().toString());

                    editor.putString("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaArticuloNuevaTanda - " + "Tanda Numero: " + i, editText_descarga.getText().toString());

                    editor.putString("Repartidor: " + nombre_apellido_recibir + "CantidadCargaMoneyNuevaTanda - " + "Tanda Numero: " + i, et_carga_money.getText().toString());

                    editor.putString("Repartidor: " + nombre_apellido_recibir + "CantidadDescargaMoneyNuevaTanda - " + "Tanda Numero: " + i, et_descarga_money.getText().toString());




                    final LinearLayout LLV_Tercer_Tupla = (LinearLayout) ArrayListTandas.get(i).findViewById(R.id.layout_vertical_tercer_tupla);

                    tope_de_articulos = LLV_Tercer_Tupla.getChildCount()-1;




                    for(int j = 1 ; j < LLV_Tercer_Tupla.getChildCount() ; j++){



                        final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla.getChildAt(j);

                        final Spinner spinner_nuevos_articulos = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                        final EditText et_carga_del_nuevo_articulo_nueva_tanda = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                        final EditText et_descarga_del_nuevo_articulo_nueva_tanda = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);


                        String ArticuloSeleccionadoSpinnerProgramatico =  spinner_nuevos_articulos.getSelectedItem().toString();

                        editor.putString("Repartidor: " + nombre_apellido_recibir + "ElementoSeleccionado_NUEVA_TANDA - " + "Tanda Numero: " + i + " - " + "Posicion: " + j, ArticuloSeleccionadoSpinnerProgramatico);

                        editor.putString("Repartidor: " + nombre_apellido_recibir + "CantidadDeCargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + i + " - " + "Posicion: " + j, et_carga_del_nuevo_articulo_nueva_tanda.getText().toString());

                        editor.putString("Repartidor: " + nombre_apellido_recibir + "CantidadDeDescargaNuevoArticuloProgramatico_NUEVA_TANDA - " + "Tanda Numero: " + i + " - " + "Posicion: " + j, et_descarga_del_nuevo_articulo_nueva_tanda.getText().toString());


                    } //Fin del for del "j" ("Articulos Programáticos")

                    editor.putString("Repartidor: " + nombre_apellido_recibir + "DimensionArticulosProgramaticos - TandaNumero: " + i, String.valueOf(tope_de_articulos));

                } //Fin del primer for "i" = Tandas


                editor.putString("Repartidor: " + nombre_apellido_recibir + "DimensionArrayNuevasTandas", String.valueOf(ArrayListTandas.size()));
                editor.commit();


                editor.putBoolean("Repartidor: " + nombre_apellido_recibir + "GuardarLasVistasDeLasTandasDeshabilitadas", true);
                editor.commit();









        }/*******************************FIN DE LA FUNCION GuardarValoresEnSharedPreferences()******************************/





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




        public void BorrarValoresDelSharedPreferences(){

            SharedPreferences sharedPreferences = getSharedPreferences("Datos_Cargas_Descargas", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();



            editor.remove("Repartidor: " + nombre_apellido_recibir + "flag_nueva_tanda").commit();


            for (int i = 0; i < ArrayListTandas.size(); i++) {


                /*Llamada a la función */
                EliminarTandaEnElSharedPreferences(ArrayListTandas.get(i),editor,i);


            } //Fin del primer for "i" = Tandas


            editor.remove("Repartidor: " + nombre_apellido_recibir + "DimensionArrayNuevasTandas").commit();

            editor.remove("Repartidor: " + nombre_apellido_recibir + "GuardarLasVistasDeLasTandasDeshabilitadas").commit();




        }/*******************************FIN DE LA FUNCION BorrarValoresDelSharedPreferenceas()******************************/







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



    public void CambioDeColoresAlGuardarNuevamenteTandasCerradas(){


        for(int k=0; k < ArrayListTandas.size(); k++) {


            final LinearLayout llv_borde = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.llv_contenedor_para_bordes);
            llv_borde.setBackground(getDrawable(R.drawable.borde_linear_layout_evento_deshabilitado));


            final LinearLayout llh_segunda_tupla = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.layout_horizontal_segunda_tupla);
            llh_segunda_tupla.setBackgroundColor(Color.parseColor("#616161"));


            final TextView tv_tandas = (TextView) ArrayListTandas.get(k).findViewById(R.id.tandas);
            tv_tandas.setBackgroundColor(Color.parseColor("#424242"));

        }//Fin del primer for




    }/**************** FIN DE LA FUNCIÓN CambioDeColoresAlGuardarNuevamenteTandasCerradas() ***************/




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


    public void CambiarColoresEditarTandasCerradas(){


        for(int k=0; k < ArrayListTandas.size(); k++) {


            final LinearLayout llv_borde = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.llv_contenedor_para_bordes);
            llv_borde.setBackground(getDrawable(R.drawable.borde_linear_layout));


            final LinearLayout llh_segunda_tupla = (LinearLayout) ArrayListTandas.get(k).findViewById(R.id.layout_horizontal_segunda_tupla);
            llh_segunda_tupla.setBackgroundColor(Color.parseColor("#ff6d00"));


            final TextView tv_tandas = (TextView) ArrayListTandas.get(k).findViewById(R.id.tandas);
            tv_tandas.setBackgroundColor(Color.parseColor("#ff3d00"));

        }//Fin del primer for




    }/**************** FIN DE LA FUNCIÓN CambiarColoresEditarTandasCerradas() ***************/




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



    public String LeerConfiguracionDeActivityEnUnSharedPreferences(String clave){

        SharedPreferences preferences = getSharedPreferences("ConfiguracionActivityCargaDescarga", MODE_PRIVATE);

        return preferences.getString(clave, "true");


    }/*******************************FIN DE LA FUNCION LeerConfiguracionDeActivityEnUnSharedPreferences()******************************/




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




        public void GuardarConfiguracionDeActivityEnUnSharedPreferences(String clave, String valor){

            SharedPreferences sharedPreferences = getSharedPreferences("ConfiguracionActivityCargaDescarga", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(clave,valor);

            editor.commit();



        }/*******************************FIN DE LA FUNCION GuardarConfiguracionDeActivityEnUnSharedPreferences()******************************/





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





        MenuItem BotonGuardar,BotonEditarTanda,BotonHabilitarTanda,BotonFinalizarTanda;

        @SuppressLint("RestrictedApi")
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_cargas_descargas, menu);



            /** Si las tandas fueron cerradas **/
            if(!Estado_Tanda) {

                fab_nueva_tanda.setVisibility(GONE);

                menu.findItem(R.id.action_save_tanda).setVisible(false);

                menu.findItem(R.id.action_finish_tanda).setVisible(false);

                menu.findItem(R.id.action_edit_tanda).setVisible(false);

                menu.findItem(R.id.action_avaible_tanda).setVisible(true);




            }//Fin del if

            else {

                menu.findItem(R.id.action_avaible_tanda).setVisible(false);

            }



            BotonGuardar = menu.findItem(R.id.action_save_tanda);

            BotonEditarTanda = menu.findItem(R.id.action_edit_tanda);

            BotonHabilitarTanda = menu.findItem(R.id.action_avaible_tanda);

            BotonFinalizarTanda = menu.findItem(R.id.action_finish_tanda);


            return true;



        }/*************************FIN DE LA FUNCION onCreateOptionsMenu()****************/










        @Override
        public boolean onOptionsItemSelected (final MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();









            if (id == R.id.action_save_tanda) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this);
                builder.setIcon(R.drawable.ic_msj_alerta);
                builder.setTitle("Desea guardar los cambios realizados?!");
                builder.setMessage("Al presionar el botón 'Guardar' se guardarán los cambios realizados en cada tanda. ¿Desea continuar?");


                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        if(Estado_Tanda){


                            if(DeshabilitarVistasDeLasTandasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambios())){

                                /*Llamada a la función: */
                                GuardarValoresEnSharedPreferences();

                            }


                        }//FIN DEL if tandas habilitadas


                        //Tandas cerradas
                        else{



                            if(DeshabilitarVistasDeLasTandasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambios())){

                                /*Llamada a la función: */
                                GuardarValoresEnSharedPreferences();

                                CambioDeColoresAlGuardarNuevamenteTandasCerradas();

                            }//Fin del if

                        }//FIN DEL else "tandas cerradas"


                    }
                });


                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();


                return true;

            }//FIN DEL if (id == R.id.action_save_tanda)












            if(id == R.id.action_edit_tanda){




                AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this);
                builder.setIcon(R.drawable.ic_msj_alerta);
                builder.setTitle("¿Desea modificar algunas de las tandas realizadas?");
                builder.setMessage("Presione 'SI' en caso que desee editar los campos de algunas de las tandas.");


                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    public void onClick(DialogInterface dialog, int id) {




                        /*La tanda no fue cerrada*/
                        if(Estado_Tanda){

                            /*Llamada a la función:  */
                            EditarTandas(true);

                            fab_nueva_tanda.setVisibility(GONE);

                            BotonGuardar.setVisible(true);

                            BotonHabilitarTanda.setVisible(false);

                            BotonFinalizarTanda.setVisible(true);


                        }//Fin del if



                        /**Tandas cerradas **/
                        else {


                            /*Llamada a la función:  */
                            EditarTandas(true);

                            /*Llamada a la función: */
                            GuardarConfiguracionDeActivityEnUnSharedPreferences("EstadoTanda" + nombre_apellido_recibir,"true");

                            CambiarColoresEditarTandasCerradas();

                            //Estado_Tanda = true;

                            BotonGuardar.setVisible(true);

                            fab_nueva_tanda.setVisibility(GONE);


                        }//Fin del else


                    }
                });








                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();


                return true;



            }//FIN DEL if(id == R.id.action_edit_tanda)













            if(id == R.id.action_finish_tanda){


                AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this);
                builder.setIcon(R.drawable.ic_msj_alerta);
                builder.setTitle("Importante!");
                builder.setMessage("Está a punto de finalizar todas las tandas realizadas en el día. ¿Desea continuar?");


                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        SharedPreferences preferences = getSharedPreferences("Datos_Cargas_Descargas", MODE_PRIVATE);

                        Integer DimensionArrayNuevasTandas = Integer.parseInt(preferences.getString("Repartidor: " + nombre_apellido_recibir + "DimensionArrayNuevasTandas", "0"));



                        if(ArrayListTandas.size() == DimensionArrayNuevasTandas ){



                            if(DeshabilitarVistasDeLasTandasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambios())){


                                /*Llamada a la función: */
                                GuardarConfiguracionDeActivityEnUnSharedPreferences("EstadoTanda" + nombre_apellido_recibir,"false");


                                /*Llamada a la función: */
                                GuardarValoresEnSharedPreferences();

                                finish();

                            }



                        }//Fin del primer if


                        else{



                            Toast.makeText(getApplicationContext(), "Error! No es posible cerrar la tanda." +
                                    " Por favor, recuerde guardar los cambios realizados", Toast.LENGTH_LONG).show();


                        }//Fin del else





                    }
                });



                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();


                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();



                return true;


            }//FIN DEL if(id == R.id.action_finish_tanda)




            if(id == R.id.action_avaible_tanda){



                AlertDialog.Builder builder = new AlertDialog.Builder(Cargas_Descargas.this,R.style.AlertDialogStyleSupervisores);
                builder.setIcon(R.drawable.ic_msj_alerta);
                builder.setTitle("¡Importante!");
                builder.setMessage("Está a punto de habilitar las tandas. ¿Desea continuar?");


                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        /*Llamada a las funciones: */
                        CambiarColoresEditarTandasCerradas();

                        GuardarConfiguracionDeActivityEnUnSharedPreferences("EstadoTanda" + nombre_apellido_recibir,"true");


                        BotonEditarTanda.setVisible(true);

                        BotonHabilitarTanda.setVisible(false);

                        BotonFinalizarTanda.setVisible(true);


                    }
                });



                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();


                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();


                return true;


            }//FIN DEL if(id == R.id.action_avaible_tanda)









            return super.onOptionsItemSelected(item);



        }/****************************************FIN DE LA FUNCIÓN onOptionsItemSelected()*****************************************/








}/****************************************FIN DE LA Activity*****************************************/

