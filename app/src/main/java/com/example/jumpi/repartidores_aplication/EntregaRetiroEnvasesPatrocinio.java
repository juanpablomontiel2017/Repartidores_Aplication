package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.Arrays;

import static android.view.View.GONE;

public class EntregaRetiroEnvasesPatrocinio extends AppCompatActivity {



    /*******DECLARACIÓN DE VARIABLES GLOBALES**/


    /******ScrollView********/

    private ScrollView parent_scrollView_erep;



    /** Variables tipo LinearLayout*/
    LinearLayout LinearLayoutVerticalPadre_Patrocinio, LinearLayoutVerticalHijo_Patrocinio, LinearLayoutVerticalTercerTupla_Patrocinio,
                 LinearLayoutVerticalBorde, LinearLayoutHorizontalSegundaTupla;


    /******ArrayList<>********/

    /*Almacenaremos los campos de entrega y retiro incluyendo los nuevos artículos para todas las vueltas.*/

    ArrayList<View> ArrayListVueltas = new ArrayList<View>();




    /******Variables Cerrojos********/

    boolean Estado_Evento = true;




    /**Variables tipo Spinner**/

    Spinner spinner_para_patrocinio;




    /** Matríz Clásica tipo Cadena */

    String [] ListaDeArticulosParaPatrocinio =  {"Bidones", "Dispenser plástico", "Canillas", "Dispenser eléctrico","Envases rotos/pinchados","Dispenser plástico roto","Dispenser eléctrico \n averiado"};



    /******Variables tipo String********/

    String ArticuloSeleccionadoAnterior;



    /**Variables tipo TextView*/

    TextView TV_Titulo_Evento;

    /**Variables tipo EditText*/

    EditText eTcantEntrega;
    EditText eTcantRetiro;



    /** Variables tipo ImageButton*/

    ImageButton btnAgregarNuevoArticuloParaPatrocinio;


    /*Para incrementar el número de cada vuelta añadida*/

    int vuelta_numero = 0;

    int Indice_Evento;

    /******FloatingActionButton********/

    private FloatingActionButton fab_nueva_vuelta;
    private FloatingActionButton fab_cancelar_vuelta;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_retiro_envases_patrocinio);



        /*Recibir los parámetros de la activity "BuscarResponsableParaPatrocinio */

        Indice_Evento = getIntent().getIntExtra("Indice_Evento",0);




        /** Preguntar si se cerro el evento **/

        if(LeerEstadoDeEvento(Indice_Evento)){ /*Evento abierto */


            /*Llamada a la función: */
            CargarVistasEventoHabilitado();



        }

        else {  /* Evento cerrado */


            /*Llamada a la función: */
            CargarVistasEventoDeshabilitado();


        }





    }/***************************FIN DEL onCreate()*****************/




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
    public void CargarVistasEventoHabilitado(){




        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));






        final TextView tv_nombre_evento_recibir = (TextView) findViewById(R.id.tv_evento_titulo);

        String extras = getIntent().getStringExtra("Nombre_del_evento");

        tv_nombre_evento_recibir.setText(extras);





        String Recibir_Nombre_Responsable = getIntent().getStringExtra("Nombre_del_responsable");

        String Recibir_Apellido_Responsable = getIntent().getStringExtra("Apellido_del_responsable");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Recibir_Nombre_Responsable + " " + Recibir_Apellido_Responsable);
        setSupportActionBar(toolbar);



        /*Llamada a la función:  */
        CargarReferenciasPrimerVuelta(window,toolbar);






        /* Guardaremos las "Nuevas Vueltas" creadas en ArrayVueltas */
        ArrayListVueltas.add(LinearLayoutVerticalHijo_Patrocinio);







        /*Llamada a la función:  */
        MostrarValoresDelSharedPreferencesPatrocinio();







        /*CASO ESPECIAL: Si estamos parados en la Primer Vuelta, el botón flotante para añadir una nueva
         vuelta no debería ser visible */

        if (ArrayListVueltas.size() > 1) {

            fab_nueva_vuelta.setVisibility(View.VISIBLE);
            fab_cancelar_vuelta.setVisibility(GONE);

        }









    }/******************************FIN DE LA FUNCIÓN CargarVistasEventoHabilitado()*****************************/



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




    public void CargarVistasEventoDeshabilitado(){




        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));








        final TextView tv_nombre_evento_recibir = (TextView) findViewById(R.id.tv_evento_titulo);

        String extras = getIntent().getStringExtra("Nombre_del_evento");

        tv_nombre_evento_recibir.setText(extras);





        String Recibir_Nombre_Responsable = getIntent().getStringExtra("Nombre_del_responsable");

        String Recibir_Apellido_Responsable = getIntent().getStringExtra("Apellido_del_responsable");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Recibir_Nombre_Responsable + " " + Recibir_Apellido_Responsable);
        setSupportActionBar(toolbar);



        /*Llamada a la función:  */
        CargarReferenciasPrimerVueltaEventoCerrado(window,toolbar);






        /* Guardaremos las "Nuevas Vueltas" creadas en ArrayVueltas */
        ArrayListVueltas.add(LinearLayoutVerticalHijo_Patrocinio);







        /*Llamada a la función:  */
        MostrarValoresDelSharedPreferencesPatrocinio();







        /*CASO ESPECIAL: Si estamos parados en la Primer Vuelta, el botón flotante para añadir una nueva
         vuelta no debería ser visible */

        if (ArrayListVueltas.size() > 1) {

            fab_nueva_vuelta.setVisibility(View.VISIBLE);
            fab_cancelar_vuelta.setVisibility(GONE);

        }









    }/******************************FIN DE LA FUNCIÓN CargarVistasEventoDeshabilitado()*****************************/




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


    public void CambiarColoresEditarEventoFinalizado(){


        for(int k=0; k < ArrayListVueltas.size(); k++) {


            final LinearLayout llv_borde = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.llv_contenedor_para_bordes_erep);
            llv_borde.setBackground(getDrawable(R.drawable.borde_linear_layout));



            final LinearLayout llh_segunda_tupla = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.layout_horizontal_segunda_tupla_erep);
            llh_segunda_tupla.setBackgroundColor(Color.parseColor("#d32f2f"));



            final TextView tv_vuelta = (TextView) ArrayListVueltas.get(k).findViewById(R.id.vuelta_erep);
            tv_vuelta.setBackgroundColor(Color.parseColor("#b71c1c"));

        }//Fin del primer for




    }/**************** FIN DE LA FUNCIÓN CambiarColoresEditarEventoFinalizado() ***************/


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






    public void CambioDeColoresAlGuardarNuevamenteEventoFinalizado(){


        for(int k=0; k < ArrayListVueltas.size(); k++) {


            final LinearLayout llv_borde = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.llv_contenedor_para_bordes_erep);
            llv_borde.setBackground(getDrawable(R.drawable.borde_linear_layout_evento_deshabilitado));


            final LinearLayout llh_segunda_tupla = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.layout_horizontal_segunda_tupla_erep);
            llh_segunda_tupla.setBackgroundColor(Color.parseColor("#616161"));


            final TextView tv_vuelta = (TextView) ArrayListVueltas.get(k).findViewById(R.id.vuelta_erep);
            tv_vuelta.setBackgroundColor(Color.parseColor("#424242"));

        }//Fin del primer for




    }/**************** FIN DE LA FUNCIÓN CambioDeColoresAlGuardarNuevamenteEventoFinalizado() ***************/




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

    public void CargarReferenciasPrimerVuelta(Window window, Toolbar toolbar){


        parent_scrollView_erep = (ScrollView) findViewById(R.id.scroll_parent_erep);

        LinearLayoutVerticalPadre_Patrocinio = (LinearLayout) findViewById(R.id.parent_layout_vertical_erep);

        LinearLayoutVerticalHijo_Patrocinio = (LinearLayout) findViewById(R.id.layout_vertical_erep);



        LinearLayoutVerticalHijo_Patrocinio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                /*Llamada a la función: */
                EliminarCualquierVuelta(v);


                return false;


            }/***************FIN DEL EVENTO onLongClick************************/


        });


        LinearLayoutVerticalBorde = (LinearLayout) findViewById(R.id.llv_contenedor_para_bordes_erep);

        LinearLayoutHorizontalSegundaTupla = (LinearLayout) findViewById(R.id.layout_horizontal_segunda_tupla_erep);

        LinearLayoutVerticalTercerTupla_Patrocinio = (LinearLayout) findViewById(R.id.layout_vertical_tercer_tupla_erep);





        TV_Titulo_Evento = (TextView) findViewById(R.id.tv_evento_titulo);







        /*Inicialización de la variable de tipo TextView creada en XML para hacer referencia al número de tanda en el que estamos parados */
        TextView textViewVuelta = (TextView) findViewById(R.id.vuelta_erep);
        vuelta_numero++;
        textViewVuelta.setText("Vuelta N°: " + vuelta_numero);









        /*Inicialización de variable del botón "+" para añadir un nuevo artículo*/
        btnAgregarNuevoArticuloParaPatrocinio = (ImageButton) findViewById(R.id.add_art_erep);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticuloParaPatrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /*Llamada a la función: */
                ValidarCamposParaAñadirNuevoArticuloPatrocinio(LinearLayoutVerticalTercerTupla_Patrocinio);




            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/









        spinner_para_patrocinio = (Spinner) findViewById(R.id.sp_art_erep);



        eTcantEntrega = (EditText) findViewById(R.id.edtx_entrega_erep);
        eTcantEntrega.requestFocus();


        eTcantRetiro = (EditText) findViewById(R.id.edtx_retiro_erep);





        /*Llamada a la función: */
        setSpinner(spinner_para_patrocinio, eTcantEntrega,true);



        fab_nueva_vuelta = findViewById(R.id.fab_nueva_entrega_retiro);

        fab_nueva_vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils_Spinner.contador_de_inicializacion--;

                ObtenerNuevaVuelta("","", "");

            }
        });



        fab_cancelar_vuelta = findViewById(R.id.fab_cancelar_entrega_retiro);









        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
            setSupportActionBar(toolbar);

            TV_Titulo_Evento.setTextColor(Color.parseColor("#1a237e"));


            LinearLayoutVerticalBorde.setBackgroundDrawable(getDrawable(R.drawable.borde_linear_layout_entrega_retiro_patrocinio_repartidores));


            textViewVuelta.setBackgroundColor(Color.parseColor("#2962ff"));


            LinearLayoutHorizontalSegundaTupla.setBackgroundColor(Color.parseColor("#0091ea"));


        }//Fin del if




    }/******************************FIN DE LA FUNCIÓN CargarReferenciasPrimerVuelta()*****************************/






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






    public void CargarReferenciasPrimerVueltaEventoCerrado(Window window, Toolbar toolbar){


        parent_scrollView_erep = (ScrollView) findViewById(R.id.scroll_parent_erep);

        LinearLayoutVerticalPadre_Patrocinio = (LinearLayout) findViewById(R.id.parent_layout_vertical_erep);

        LinearLayoutVerticalHijo_Patrocinio = (LinearLayout) findViewById(R.id.layout_vertical_erep);



        LinearLayoutVerticalHijo_Patrocinio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                /*Llamada a la función: */
                EliminarCualquierVuelta(v);


                return false;


            }/***************FIN DEL EVENTO onLongClick************************/


        });


        LinearLayoutVerticalBorde = (LinearLayout) findViewById(R.id.llv_contenedor_para_bordes_erep);
        LinearLayoutVerticalBorde.setBackground(getDrawable(R.drawable.borde_linear_layout_evento_deshabilitado));

        LinearLayoutHorizontalSegundaTupla = (LinearLayout) findViewById(R.id.layout_horizontal_segunda_tupla_erep);
        LinearLayoutHorizontalSegundaTupla.setBackgroundColor(Color.parseColor("#616161"));



        LinearLayoutVerticalTercerTupla_Patrocinio = (LinearLayout) findViewById(R.id.layout_vertical_tercer_tupla_erep);





        TV_Titulo_Evento = (TextView) findViewById(R.id.tv_evento_titulo);
        TV_Titulo_Evento.setTextColor(Color.BLACK);






        /*Inicialización de la variable de tipo TextView creada en XML para hacer referencia al número de tanda en el que estamos parados */
        TextView textViewVuelta = (TextView) findViewById(R.id.vuelta_erep);
        textViewVuelta.setBackgroundColor(Color.parseColor("#424242"));
        vuelta_numero++;
        textViewVuelta.setText("Vuelta N°: " + vuelta_numero);





        /*Inicialización de variable del botón "+" para añadir un nuevo artículo*/
        btnAgregarNuevoArticuloParaPatrocinio = (ImageButton) findViewById(R.id.add_art_erep);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticuloParaPatrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /*Llamada a la función: */
                ValidarCamposParaAñadirNuevoArticuloPatrocinio(LinearLayoutVerticalTercerTupla_Patrocinio);




            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/









        spinner_para_patrocinio = (Spinner) findViewById(R.id.sp_art_erep);



        eTcantEntrega = (EditText) findViewById(R.id.edtx_entrega_erep);
        eTcantEntrega.requestFocus();


        eTcantRetiro = (EditText) findViewById(R.id.edtx_retiro_erep);





        /*Llamada a la función: */
        setSpinner(spinner_para_patrocinio, eTcantEntrega,true);



        fab_nueva_vuelta = findViewById(R.id.fab_nueva_entrega_retiro);

        fab_nueva_vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils_Spinner.contador_de_inicializacion--;

                ObtenerNuevaVuelta("","", "");

            }
        });



        fab_cancelar_vuelta = findViewById(R.id.fab_cancelar_entrega_retiro);









        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
            setSupportActionBar(toolbar);

            TV_Titulo_Evento.setTextColor(Color.parseColor("#1a237e"));


            LinearLayoutVerticalBorde.setBackgroundDrawable(getDrawable(R.drawable.borde_linear_layout_entrega_retiro_patrocinio_repartidores));


            textViewVuelta.setBackgroundColor(Color.parseColor("#2962ff"));


            LinearLayoutHorizontalSegundaTupla.setBackgroundColor(Color.parseColor("#0091ea"));


        }//Fin del if




    }/******************************FIN DE LA FUNCIÓN CargarReferenciasPrimerVueltaEventoCerrado()*****************************/



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





    public void CierreDeEvento(int indice_evento) {



            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("La vuelta del día de ayer se ha cerrado abruptamente!");
            builder.setMessage("¡Esto se debe porque ha comenzado un nuevo día laboral. Por favor verifique si existen incosistencias.!");


            builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {


                    /* Llamada a la función: */
                    BorrarValoresDelSharedPreferencesPatrocinio();


                    /* Llamada a la función: */
                    MostrarValoresDelSharedPreferencesPatrocinio();


                }


            }); /**FIN DEL  builder.setPositiveButton()  **/


            AlertDialog dialog = builder.create();
            dialog.show();





    }/***********************FIN DE LA FUNCIÓN CierreDeEvento()********************************/






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








    public void EliminarCualquierVuelta(final View v){


        if(Estado_Evento){


            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("Desea eliminar la vuelta seleccionada?!");
            builder.setMessage("Al presionar el botón 'Eliminar Vuelta' se borrará la vuelta que fue seleccionada. ¿Desea continuar?");


            builder.setPositiveButton("Eliminar Vuelta", new DialogInterface.OnClickListener() {

                @SuppressLint("RestrictedApi")
                public void onClick(DialogInterface dialog, int id) {






                    if (ArrayListVueltas.size() > 1) {

                        int ValorDeLaPosicion;

                        ValorDeLaPosicion = EncontrarPosicionDeVuelta(v);



                        if(ValorDeLaPosicion != 99){



                            /*Llamada a la función:  */
                            BorrarValoresDelSharedPreferencesPatrocinio();



                            ArrayListVueltas.remove(v);




                            LinearLayoutVerticalPadre_Patrocinio.removeAllViews();



                            /*Llamada a la función:  */
                            GuardarValoresEnSharedPreferencesPatrocinio();



                            vuelta_numero = 0;


                            ArrayListVueltas.clear();


                            /*Llamada a la función:  */
                            RefrescarVueltasEnPantalla();




                            /****Tener en cuenta las siguientes condiciones al momento de eliminar la vuelta: **/

                            fab_nueva_vuelta.setVisibility(View.VISIBLE);

                            fab_cancelar_vuelta.setVisibility(GONE);

                            DeshabilitarVistasDeLasVueltasAlGuardarCambios(true);


                            Toast.makeText(EntregaRetiroEnvasesPatrocinio.this, "La vuelta ha sido eliminada", Toast.LENGTH_LONG).show();








                        } else {


                            Toast.makeText(EntregaRetiroEnvasesPatrocinio.this, "Posicion: " + ValorDeLaPosicion, Toast.LENGTH_LONG).show();


                        }


                    } else {

                        Toast.makeText(EntregaRetiroEnvasesPatrocinio.this, "¡No es posible eliminar la primer vuelta. Por favor, tenga en cuenta crear una nueva vuelta!", Toast.LENGTH_LONG).show();

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



    }/*********************************FIN DE LA FUNCIÓN EliminarCualquierVuelta()*******************************************/







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







    public int  EncontrarPosicionDeVuelta(View v){

        int posicion_vuelta = 99;

        for(int i = 0 ; i < ArrayListVueltas.size(); i++ ){

            if(ArrayListVueltas.get(i) == v ){

                posicion_vuelta = i;

                break;

            }//Fin del if


        }//Fin del for


        return posicion_vuelta;


    }/*********************************FIN DE LA FUNCIÓN EncontrarPosicionDeVuelta()*******************************************/




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






    public void RefrescarVueltasEnPantalla(){


        SharedPreferences preferences = getSharedPreferences("Datos_Patrocinio_Supervisor", MODE_PRIVATE);



        String DimensionArrayNuevasVueltas = preferences.getString("DimensionArrayNuevasVueltas", "");



        if (DimensionArrayNuevasVueltas != "") {

            for (int indice_vueltas = 0; indice_vueltas < Integer.valueOf(DimensionArrayNuevasVueltas); indice_vueltas++) {



                String ElementoSeleccionadoSpinnerFijo = preferences.getString("ElementoSeleccionadoSpinnerFijo - " + "Vuelta Numero: " + indice_vueltas, "");
                String ValorEntregaArticulosFijo = preferences.getString("CantidadEntregaArticuloFijo - " + "Vuelta Numero: " + indice_vueltas, "");
                String ValorRetiroArticulosFijo = preferences.getString("CantidadRetiroArticuloFijo - " + "Vuelta Numero: " + indice_vueltas, "");


                /* Llamada a la función: */
                ObtenerNuevaVuelta(ElementoSeleccionadoSpinnerFijo,ValorEntregaArticulosFijo, ValorRetiroArticulosFijo);



                String DimensionArticulosProgramaticos = preferences.getString("DimensionArticulosProgramaticos - VueltaNumero: " + indice_vueltas, "");

                if (DimensionArticulosProgramaticos != "") {

                    for (int j = 1; j <= Integer.valueOf(DimensionArticulosProgramaticos); j++) {



                        String ElementoSeleccionadoSpinnerProgramatico = preferences.getString("ElementoSeleccionadoSpinnerProgramatico - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");
                        String ValorEntregaNuevoArticuloProgramatico = preferences.getString("CantidadDeEntregaNuevoArticuloProgramatico - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");
                        String ValorRetiroNuevoArticuloProgramatico = preferences.getString("CantidadDeRetiroNuevoArticuloProgramatico - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");


                        if (ValorEntregaNuevoArticuloProgramatico != "" || ValorRetiroNuevoArticuloProgramatico != "") {

                            final View vuelta = ArrayListVueltas.get(indice_vueltas);

                            final LinearLayout tercerTuplaProgramatica = (LinearLayout) vuelta.findViewById(R.id.layout_vertical_tercer_tupla_erep);


                            /*Llamada a la función */
                            ObtenerNuevoArticuloEntregaRetiroPorPatrocinio(ElementoSeleccionadoSpinnerProgramatico,
                                    ValorEntregaNuevoArticuloProgramatico, ValorRetiroNuevoArticuloProgramatico, tercerTuplaProgramatica);


                        } //Fin del if

                    } //Fin del for



                } //Fin del primer if





            } //Fin del primer for


        } //Fin del primer if





        if(preferences.getBoolean("GuardarLasVistasDeLasVueltasDeshabilitadas",false)){

            DeshabilitarVistasDeLasVueltasAlGuardarCambios(true);

        }





    }/*****************FIN DE LA FUNCIÓN RefrescarVueltasEnPantalla()**************************/









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





    public void EliminarVueltaEnElSharedPreferences(View vuelta, SharedPreferences.Editor editor, int i) {



        int tope_de_articulos = 0;


        editor.remove("ElementoSeleccionadoSpinnerFijo - " + "Vuelta Numero: " + i).commit();

        editor.remove("CantidadEntregaArticuloFijo - " + "Vuelta Numero: " + i).commit();

        editor.remove("CantidadRetiroArticuloFijo - " + "Vuelta Numero: " + i).commit();



        final LinearLayout LLV_Tercer_Tupla_Vuelta = (LinearLayout) vuelta.findViewById(R.id.layout_vertical_tercer_tupla_erep);


        tope_de_articulos = LLV_Tercer_Tupla_Vuelta.getChildCount()-1;




        for(int j = 1 ; j < LLV_Tercer_Tupla_Vuelta.getChildCount() ; j++){



            editor.remove("ElementoSeleccionadoSpinnerProgramatico - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j).commit();

            editor.remove("CantidadDeEntregaNuevoArticuloProgramatico - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j).commit();

            editor.remove("CantidadDeRetiroNuevoArticuloProgramatico - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j).commit();



        } //Fin del for del "j" ("Articulos Programáticos")






        editor.remove("DimensionArticulosProgramaticos - VueltaNumero: " + i).commit();




    }/*********************************FIN DE LA FUNCIÓN EliminarVueltaEnElSharedPreferences()*******************************************/







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












    Boolean bandera_boleana_nuevos_articulos_patrocinio = false;


    public void ValidarCamposParaAñadirNuevoArticuloPatrocinio(LinearLayout TercerTuplaPatrocinio){



        for(int i = 0; i < ArrayListVueltas.size();i++){


            final EditText editText_entrega = (EditText) ArrayListVueltas.get(i).findViewById(R.id.edtx_entrega_erep);

            final EditText editText_retiro = (EditText) ArrayListVueltas.get(i).findViewById(R.id.edtx_retiro_erep);

            /** Primer Validación: Si al menos uno de los campos de Entrega y Retiro de artículos contiene
             * un valor se guardarán los cambios. Caso contrario se arrojará un mensaje de error*/


            if (!editText_entrega.getText().toString().isEmpty() || !editText_retiro.getText().toString().isEmpty() ){

                bandera_boleana_nuevos_articulos_patrocinio = true;

            }



            if(editText_entrega.getText().toString().isEmpty() && editText_retiro.getText().toString().isEmpty() ){

                Toast.makeText(getApplicationContext(), "Recuerde! Para añadir un nuevo artículo el campo de 'Entrega' o 'Retiro' de artículos debe estar completo.", Toast.LENGTH_LONG).show();

                bandera_boleana_nuevos_articulos_patrocinio = false;

            }



            /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
             *  de guardar valores nulos se arrojará un mensaje de error */

            if (editText_entrega.getText().toString().length() > 0 && editText_entrega.getText().toString().charAt(0) == '0'
                    || editText_retiro.getText().toString().length() > 0 && editText_retiro.getText().toString().charAt(0) == '0'){

                Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos o que el primer" +
                        " dígito comience con cero ", Toast.LENGTH_LONG).show();

                bandera_boleana_nuevos_articulos_patrocinio = false;
            }




            /**VALIDACIÓN DE CAMPOS DE ENTREGA Y RETIRO PARA EL NUEVO ARTICULO**/


            final LinearLayout LLV_Tercer_Tupla = (LinearLayout) ArrayListVueltas.get(i).findViewById(R.id.layout_vertical_tercer_tupla_erep);

            for(int j = 1 ; j < LLV_Tercer_Tupla.getChildCount() ; j++){

                final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla.getChildAt(j);

                final EditText et_entrega_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                final EditText et_retiro_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);


                /** Primer Validación: Si al menos uno de los campos ya mencionados contiene un valor se guardarán los cambios. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!et_entrega_del_nuevo_articulo.getText().toString().isEmpty() || !et_retiro_del_nuevo_articulo.getText().toString().isEmpty()){

                    bandera_boleana_nuevos_articulos_patrocinio = true;
                }


                if (et_entrega_del_nuevo_articulo.getText().toString().isEmpty() && et_retiro_del_nuevo_articulo.getText().toString().isEmpty()) {

                    bandera_boleana_nuevos_articulos_patrocinio = false;

                    Toast.makeText(getApplicationContext(), "Error! los campos del nuevo artículo añadido están vacíos. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();

                }




                /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                 *  de guardar valores nulos se arrojará un mensaje de error */

                if (et_entrega_del_nuevo_articulo.getText().toString().length() > 0 && et_entrega_del_nuevo_articulo.getText().toString().charAt(0) == '0'
                        || et_retiro_del_nuevo_articulo.getText().toString().length() > 0 && et_retiro_del_nuevo_articulo.getText().toString().charAt(0) == '0'){

                    Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos o que el primer" +
                            " dígito comience con cero ", Toast.LENGTH_LONG).show();

                    bandera_boleana_nuevos_articulos_patrocinio = false;
                }








            } //Fin del for del "j" ("Articulos Programáticos")



        } //Fin del primer for



        if (bandera_boleana_nuevos_articulos_patrocinio) {



            /*Llamada a la función: */
            ObtenerNuevoArticuloEntregaRetiroPorPatrocinio("","","",TercerTuplaPatrocinio);

            Toast.makeText(getApplicationContext(), "Nuevo artículo añadido", Toast.LENGTH_LONG).show();

        } //Fin del  if




    }/****************FIN DE LA FUNCIÓN ValidarCamposParaAñadirNuevoArticuloPatrocinio()*****************************/






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






    int ChildNuevoArticuloPatrocinio = 0;

    public void ObtenerNuevoArticuloEntregaRetiroPorPatrocinio(String ValorElementoSeleccionadoSpinnerProgramatico,String Valor_a_SetearDelEditTextEntregaNuevoArticulo,
                                     String Valor_a_SetearDelEditTextRetiroNuevoArticulo, final LinearLayout TercerTuplaPatrocinio) {

        View NuevoArticuloInfladoPatrocinio;

        /*Llamada a la función: */
        NuevoArticuloInfladoPatrocinio = AgregarNuevoArticuloEntregaRetiroPorPatrocinio(TercerTuplaPatrocinio);


        /** Instanciamos las vistas del diseño XML: "nuevo_articulo.xml" **/


        final ImageButton btnEliminarArticuloPatrocinio = (ImageButton) NuevoArticuloInfladoPatrocinio.findViewById(R.id.delete_art);

        btnEliminarArticuloPatrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EliminarNuevoArticuloPatrocinio(v);



            }
        });



        final EditText EditText_Entrega_Nuevo_Articulo_Patrocinio = (EditText) NuevoArticuloInfladoPatrocinio.findViewById(R.id.edtx_carga_new_art);


        if(Valor_a_SetearDelEditTextEntregaNuevoArticulo != ""){


            EditText_Entrega_Nuevo_Articulo_Patrocinio.setText(Valor_a_SetearDelEditTextEntregaNuevoArticulo);


        }




        final EditText EditText_Retiro_Nuevo_Articulo_Patrocinio = (EditText) NuevoArticuloInfladoPatrocinio.findViewById(R.id.edtx_descarga_new_art);

        if(Valor_a_SetearDelEditTextRetiroNuevoArticulo != ""){


            EditText_Retiro_Nuevo_Articulo_Patrocinio.setText(Valor_a_SetearDelEditTextRetiroNuevoArticulo);


        }





        final Spinner spinner_nuevos_articulos_patrocinio = (Spinner) NuevoArticuloInfladoPatrocinio.findViewById(R.id.sp_new_art);


        /*Llamada a la función: */
        setSpinner(spinner_nuevos_articulos_patrocinio, EditText_Entrega_Nuevo_Articulo_Patrocinio,false);





        ArticuloSeleccionadoAnterior = spinner_nuevos_articulos_patrocinio.getSelectedItem().toString();


        spinner_nuevos_articulos_patrocinio.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerProgramatico,spinner_nuevos_articulos_patrocinio));





        if (ValorElementoSeleccionadoSpinnerProgramatico != ""){


            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinner(spinner_nuevos_articulos_patrocinio,ArticuloSeleccionadoAnterior,ValorElementoSeleccionadoSpinnerProgramatico, this);


        } else{

            Utils_Spinner.contador_de_inicializacion = 0;

            /*Llamada a la función: */
            Utils_Spinner.RefrescarOtrosSpinner(spinner_nuevos_articulos_patrocinio,null,ArticuloSeleccionadoAnterior, this);


        }








    } /**************************************FIN DE LA FUNCIÓN ObtenerNuevoArticuloEntregaRetiroPorPatrocinio()*******************************************/







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






    public View AgregarNuevoArticuloEntregaRetiroPorPatrocinio(final LinearLayout LayoutVerticalTercerTuplaPatrocinio) {


        LayoutInflater inflaterPatrocinio = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflaterPatrocinio.inflate(R.layout.nuevo_articulo, null, true);

        LayoutVerticalTercerTuplaPatrocinio.addView(InflatedView);

        ChildNuevoArticuloPatrocinio = LayoutVerticalTercerTuplaPatrocinio.getChildCount();


        return InflatedView;




    } /***************************FIN DE LA FUNCIÓN AgregarNuevoArticuloEntregaRetiroPorPatrocinio()************************************/





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




    public void EliminarNuevoArticuloPatrocinio(View btnEliminarNuevoArticuloPatrocinio) {




        /*Llamada a la función: */
        ImageButton btnAgregarNuevoArticulo = Utils_Spinner.ObtenerBotonParaAgregarNuevosArticulos(btnEliminarNuevoArticuloPatrocinio);

        btnAgregarNuevoArticulo.setEnabled(true);




        LinearLayout LinearHorizontal = (LinearLayout) btnEliminarNuevoArticuloPatrocinio.getParent();

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
        Utils_Spinner.RefrescarOtrosSpinner(spinner,ArticuloSeleccionado,null, this);










        final LinearLayout HijoLinearLayoutHorizontalNuevosArticulosPatrocinio = (LinearLayout) btnEliminarNuevoArticuloPatrocinio.getParent();

        final LinearLayout llv_padre_patrocinio = (LinearLayout) HijoLinearLayoutHorizontalNuevosArticulosPatrocinio.getParent();

        llv_padre_patrocinio.removeView(HijoLinearLayoutHorizontalNuevosArticulosPatrocinio);




    } /*************************************FIN DE LA FUNCIÓN EliminarNuevoArticuloPatrocinio()***********************************************/






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







    public void setSpinner(final Spinner spinner, final EditText EditTextEntregaPatrocinio,boolean EsSpinnerFijo) {


        /*********************************************************************************************************/

        ArrayAdapter<String> adaptador;


        if(EsSpinnerFijo){



            adaptador = new ArrayAdapter<String>(this, R.layout.spinner, ListaDeArticulosParaPatrocinio);




        }

        else{



            ArrayList<String> ArticulosReducidos = new ArrayList<String>(Arrays.asList(ListaDeArticulosParaPatrocinio));


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
        Utils_Spinner.RefrescarOtrosSpinner(spinner ,null, ArticuloSeleccionado, this);



        /*Llamada a la función: */
        Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner(ArticuloSeleccionado,spinner,EditTextEntregaPatrocinio);



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
                    Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner(text,spinner,EditTextEntregaPatrocinio);


                    /*Llamada a la función: */
                    Utils_Spinner.RefrescarOtrosSpinner((Spinner)adapterView,ArticuloSeleccionadoAnterior,text, EntregaRetiroEnvasesPatrocinio.this);


                }//Fin del primer else




            }/******************************FIN DEL EVENTO onItemSelected()*****************************/





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }





        });/******************************FIN DEL EVENTO setOnItemSelectedListener()*****************************/





    }/******************************FIN DE LA FUNCIÓN setSpinner()*****************************/






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








    public void Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner(String ArticuloSeleccionadoText,Spinner spinner,
                                                                            EditText EditTextEntrega){






        if (ArticuloSeleccionadoText == "Envases rotos/pinchados" || ArticuloSeleccionadoText == "Dispenser plástico roto"
        || ArticuloSeleccionadoText == "Dispenser eléctrico \n averiado") {

            EditTextEntrega.setEnabled(false);
            EditTextEntrega.setHint("");
            EditTextEntrega.setText("");


        } else {

            EditTextEntrega.setEnabled(true);
            EditTextEntrega.setHint("Cantidad");

        }






    }/***************** FIN DE LA FUNCIÓN Habilitar_Deshabilitar_Campos_ElementosEspecialesDelSpinner() *****************/





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







    int ChildNuevaVuelta = 0;

    public void ObtenerNuevaVuelta(String ValorElementoSeleccionadoSpinnerFijo,String ValorSeteadoDelEditTextEntregaDeArticulosParaNuevaVuelta,
                                  String ValorSeteadoDelEditTextRetiroDeArticulosParaNuevaVuelta) {





        if(LeerEstadoDeEvento(Indice_Evento)){ /*Evento abierto */




            View NuevaVueltaInflada;

            /*Llamada a la función: */
            NuevaVueltaInflada = AgregarNuevaVuelta();

            ArrayListVueltas.add(NuevaVueltaInflada);



            final LinearLayout LinearLayoutVerticalProgramaticoConBorde = (LinearLayout) findViewById(R.id.llv_contenedor_para_bordes_erep);

            final LinearLayout LinearLayoutSegundaTuplaProgramatica_Patrocinio = (LinearLayout) findViewById(R.id.layout_horizontal_segunda_tupla_erep);

            final LinearLayout LinearLayoutTercerTuplaProgramatica_Patrocinio = (LinearLayout) NuevaVueltaInflada.findViewById(R.id.layout_vertical_tercer_tupla_erep);



            TextView textViewNuevaVuelta = (TextView) NuevaVueltaInflada.findViewById(R.id.vuelta_erep);
            vuelta_numero++;
            textViewNuevaVuelta.setText("Vuelta N°: " + vuelta_numero);




            final ImageButton btnAgregarNuevoArticuloParaLaNuevaVuelta = (ImageButton) NuevaVueltaInflada.findViewById(R.id.add_art_erep);

            btnAgregarNuevoArticuloParaLaNuevaVuelta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ValidarCamposParaAñadirNuevoArticuloPatrocinio(LinearLayoutTercerTuplaProgramatica_Patrocinio);


                }
            });






            final EditText et_entrega_nueva_vuelta = (EditText) NuevaVueltaInflada.findViewById(R.id.edtx_entrega_erep);
            et_entrega_nueva_vuelta.requestFocus();


            et_entrega_nueva_vuelta.setText(ValorSeteadoDelEditTextEntregaDeArticulosParaNuevaVuelta);








            final EditText et_retiro_nueva_vuelta = (EditText) NuevaVueltaInflada.findViewById(R.id.edtx_retiro_erep);

            et_retiro_nueva_vuelta.setText(ValorSeteadoDelEditTextRetiroDeArticulosParaNuevaVuelta);









            final Spinner spinner_fijo_nueva_vuelta = (Spinner) NuevaVueltaInflada.findViewById(R.id.sp_art_erep);


            Utils_Spinner.contador_de_inicializacion = 0;





            /*Llamada a la función: */
            setSpinner(spinner_fijo_nueva_vuelta, et_entrega_nueva_vuelta,true);





            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_vuelta.getSelectedItem().toString();


            spinner_fijo_nueva_vuelta.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerFijo,spinner_fijo_nueva_vuelta));


            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_vuelta.getSelectedItem().toString();






            /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
             * de Patrocinio**/

            Usuario usuario = new Usuario();

            usuario.LeerUsuarioEnUnSharedPreferences(this);

            if(usuario.getTipo_de_Usuario().equals("repartidor")){

                LinearLayoutVerticalProgramaticoConBorde.setBackgroundDrawable(getDrawable(R.drawable.borde_linear_layout_entrega_retiro_patrocinio_repartidores));

                textViewNuevaVuelta.setBackgroundColor(Color.parseColor("#2962ff"));

                LinearLayoutSegundaTuplaProgramatica_Patrocinio.setBackgroundColor(Color.parseColor("#0091ea"));


            }//Fin del if





        }//FIN del if principal







        else {  /* Evento cerrado */



            View NuevaVueltaInflada;

            /*Llamada a la función: */
            NuevaVueltaInflada = AgregarNuevaVuelta();

            ArrayListVueltas.add(NuevaVueltaInflada);



            final LinearLayout LinearLayoutVerticalProgramaticoConBorde = (LinearLayout) findViewById(R.id.llv_contenedor_para_bordes_erep);
            LinearLayoutVerticalProgramaticoConBorde.setBackground(getDrawable(R.drawable.borde_linear_layout_evento_deshabilitado));


            final LinearLayout LinearLayoutSegundaTuplaProgramatica_Patrocinio = (LinearLayout) findViewById(R.id.layout_horizontal_segunda_tupla_erep);
            LinearLayoutSegundaTuplaProgramatica_Patrocinio.setBackgroundColor(Color.parseColor("#616161"));


            final LinearLayout LinearLayoutTercerTuplaProgramatica_Patrocinio = (LinearLayout) NuevaVueltaInflada.findViewById(R.id.layout_vertical_tercer_tupla_erep);



            TextView textViewNuevaVuelta = (TextView) NuevaVueltaInflada.findViewById(R.id.vuelta_erep);
            textViewNuevaVuelta.setBackgroundColor(Color.parseColor("#424242"));
            vuelta_numero++;
            textViewNuevaVuelta.setText("Vuelta N°: " + vuelta_numero);





            final ImageButton btnAgregarNuevoArticuloParaLaNuevaVuelta = (ImageButton) NuevaVueltaInflada.findViewById(R.id.add_art_erep);

            btnAgregarNuevoArticuloParaLaNuevaVuelta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ValidarCamposParaAñadirNuevoArticuloPatrocinio(LinearLayoutTercerTuplaProgramatica_Patrocinio);


                }
            });






            final EditText et_entrega_nueva_vuelta = (EditText) NuevaVueltaInflada.findViewById(R.id.edtx_entrega_erep);
            et_entrega_nueva_vuelta.requestFocus();


            et_entrega_nueva_vuelta.setText(ValorSeteadoDelEditTextEntregaDeArticulosParaNuevaVuelta);








            final EditText et_retiro_nueva_vuelta = (EditText) NuevaVueltaInflada.findViewById(R.id.edtx_retiro_erep);

            et_retiro_nueva_vuelta.setText(ValorSeteadoDelEditTextRetiroDeArticulosParaNuevaVuelta);









            final Spinner spinner_fijo_nueva_vuelta = (Spinner) NuevaVueltaInflada.findViewById(R.id.sp_art_erep);


            Utils_Spinner.contador_de_inicializacion = 0;





            /*Llamada a la función: */
            setSpinner(spinner_fijo_nueva_vuelta, et_entrega_nueva_vuelta,true);





            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_vuelta.getSelectedItem().toString();


            spinner_fijo_nueva_vuelta.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ValorElementoSeleccionadoSpinnerFijo,spinner_fijo_nueva_vuelta));


            ArticuloSeleccionadoAnterior = spinner_fijo_nueva_vuelta.getSelectedItem().toString();






            /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
             * de Patrocinio**/

            Usuario usuario = new Usuario();

            usuario.LeerUsuarioEnUnSharedPreferences(this);

            if(usuario.getTipo_de_Usuario().equals("repartidor")){

                LinearLayoutVerticalProgramaticoConBorde.setBackgroundDrawable(getDrawable(R.drawable.borde_linear_layout_entrega_retiro_patrocinio_repartidores));

                textViewNuevaVuelta.setBackgroundColor(Color.parseColor("#2962ff"));

                LinearLayoutSegundaTuplaProgramatica_Patrocinio.setBackgroundColor(Color.parseColor("#0091ea"));


            }//Fin del if






        }//FIN del else












    } /*****************************FIN DE LA FUNCIÓN ObtenerNuevaVuelta()*****************************/


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
    public View AgregarNuevaVuelta(){


        LayoutInflater inflaterVuelta = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflaterVuelta.inflate(R.layout.nueva_entrega_retiro_patrocinio, null, true);


        LinearLayoutVerticalPadre_Patrocinio.addView(InflatedView,0);





        InflatedView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                /*Llamada a la función: */
                EliminarCualquierVuelta(v);

                return false;


            }/**Fin del evento onLongClick() */


        });  /**Fin del evento setOnLongClickListener() */







        ChildNuevaVuelta = LinearLayoutVerticalPadre_Patrocinio.getChildCount();


        fab_nueva_vuelta.setVisibility(GONE);
        fab_cancelar_vuelta.setVisibility(View.VISIBLE);

        /*Mensaje una vez añadida la nueva tanda*/
        Snackbar.make(InflatedView, "Nueva vuelta añadida", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();





        return InflatedView;


    } /***************************FIN DE LA FUNCIÓN AgregarNuevaVuelta()*****************************/





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
    public void CancelarNuevaVuelta(View v) {



        LinearLayoutVerticalPadre_Patrocinio.removeViewAt(0);




        fab_cancelar_vuelta.setVisibility(View.GONE);
        fab_nueva_vuelta.setVisibility(View.VISIBLE);




        /*Si eliminamos una nueva tanda...*/
        vuelta_numero--;




        /*Mensaje una vez añadida la nueva tanda*/
        Snackbar.make(v, "Tanda eliminada", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();




        ArrayListVueltas.remove(ArrayListVueltas.size() -1);




    } /**********************FIN DE LA FUNCIÓN CancelarNuevaVuelta()********************************/








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









    boolean flag_validacion_campos_patrocinio = false;


    @SuppressLint("RestrictedApi")
    public boolean ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta() {


        //Estructura repetitiva para duplicar el tiempo de duración del Toast
        for (int i = 0; i < 2; i++) {


            for(int k=0; k < ArrayListVueltas.size(); k++){

                final EditText editText_entrega = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_entrega_erep);

                final EditText editText_retiro = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_retiro_erep);



                /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                 * se arrojará un mensaje de error*/

                if (!editText_entrega.getText().toString().isEmpty()
                        || !editText_retiro.getText().toString().isEmpty()){

                    flag_validacion_campos_patrocinio = true;

                }





                if (editText_entrega.getText().toString().isEmpty()
                        && editText_retiro.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Error! Los campos de la nueva vuelta están vacíos." +
                            " Por favor, complete al menos uno de los campos", Toast.LENGTH_LONG).show();

                    flag_validacion_campos_patrocinio = false;

                }






                /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                 *  de guardar valores nulos se arrojará un mensaje de error */

                if (editText_entrega.getText().toString().length() > 0 && editText_entrega.getText().toString().charAt(0) == '0'
                        || editText_retiro.getText().toString().length() > 0 && editText_retiro.getText().toString().charAt(0) == '0'){

                    Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos" +
                            " o que el primer dígito comience con cero ", Toast.LENGTH_LONG).show();

                    flag_validacion_campos_patrocinio = false;
                }







                final LinearLayout LLV_Tercer_Tupla_Patrocinio = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.layout_vertical_tercer_tupla_erep);

                for(int l = 1 ; l < LLV_Tercer_Tupla_Patrocinio.getChildCount() ; l++){

                    final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla_Patrocinio.getChildAt(l);

                    final EditText et_entrega_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                    final EditText et_retiro_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);


                    /** Primer Validación: Si al menos uno de los campos contiene un valor se guardarán los cambios. Caso contrario
                     * se arrojará un mensaje de error*/

                    if (!et_entrega_del_nuevo_articulo.getText().toString().isEmpty() || !et_retiro_del_nuevo_articulo.getText().toString().isEmpty()){

                        flag_validacion_campos_patrocinio = true;
                    }


                    if (et_entrega_del_nuevo_articulo.getText().toString().isEmpty() && et_retiro_del_nuevo_articulo.getText().toString().isEmpty()) {

                        flag_validacion_campos_patrocinio = false;

                        Toast.makeText(getApplicationContext(), "Error! los campos del nuevo artículo añadido están vacíos. Por favor, complete al menos uno de los campos correspondientes.", Toast.LENGTH_LONG).show();

                    }



                    /** Segunda Validación: Si uno o más campos contienen en el primer dígito  un cero o se trata
                     *  de guardar valores nulos se arrojará un mensaje de error */


                    if (et_entrega_del_nuevo_articulo.getText().toString().length() > 0 && et_entrega_del_nuevo_articulo.getText().toString().charAt(0) == '0'
                            || et_retiro_del_nuevo_articulo.getText().toString().length() > 0 && et_retiro_del_nuevo_articulo.getText().toString().charAt(0) == '0'){

                        Toast.makeText(getApplicationContext(), "Error! No está permitido completar los campos con valores nulos o que el primer" +
                                " dígito comience con cero ", Toast.LENGTH_LONG).show();

                        flag_validacion_campos_patrocinio = false;
                    }






                } //Fin del for del "l" ("Articulos Programáticos")




            } //Fin del primer for










            if (flag_validacion_campos_patrocinio) {

                Toast.makeText(getApplicationContext(), "Los cambios fueron guardados con éxito", Toast.LENGTH_LONG).show();

                /** Al comenzar con la primer vuelta, en el momento de guardar se llevará a cabo lo siguiente: **/

                fab_nueva_vuelta.setVisibility(View.VISIBLE);

                fab_cancelar_vuelta.setVisibility(GONE);


            } //Fin del if (flag_validacion_campos_patrocinio){}


        } /*Fin del 'for'*/

        return flag_validacion_campos_patrocinio;

    } /*********************************FIN DE LA FUNCIÓN ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta() ****************************/













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








    public boolean DeshabilitarVistasDeLasVueltasAlGuardarCambios(boolean flag_enabled_vueltas){

        if (flag_enabled_vueltas) {



            for(int k=0; k < ArrayListVueltas.size(); k++){

                final ImageButton btnAgregarNuevoArticuloParaLaNuevaVuelta = (ImageButton) ArrayListVueltas.get(k).findViewById(R.id.add_art_erep);

                final Spinner sp_nueva_vuelta = (Spinner) ArrayListVueltas.get(k).findViewById(R.id.sp_art_erep);

                final EditText editText_entrega_nueva_vuelta = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_entrega_erep);

                final EditText editText_retiro_nueva_vuelta = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_retiro_erep);


                btnAgregarNuevoArticuloParaLaNuevaVuelta.setVisibility(GONE);

                sp_nueva_vuelta.setEnabled(false);

                editText_entrega_nueva_vuelta.setFocusable(false);
                editText_entrega_nueva_vuelta.setCursorVisible(false);
                editText_entrega_nueva_vuelta.setHint("");
                editText_entrega_nueva_vuelta.setHintTextColor(Color.parseColor("#fafafa"));
                editText_entrega_nueva_vuelta.setBackgroundColor(Color.TRANSPARENT);


                editText_retiro_nueva_vuelta.setFocusable(false);
                editText_retiro_nueva_vuelta.setHint("");
                editText_retiro_nueva_vuelta.setHintTextColor(Color.parseColor("#fafafa"));
                editText_retiro_nueva_vuelta.setBackgroundColor(Color.TRANSPARENT);




                final LinearLayout LLV_Tercer_Tupla_Patrocinio = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.layout_vertical_tercer_tupla_erep);

                for(int l = 1 ; l < LLV_Tercer_Tupla_Patrocinio.getChildCount() ; l++){

                    final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla_Patrocinio.getChildAt(l);

                    final ImageButton btnDeleteNuevoArticuloParaLaNuevaVuelta = (ImageButton) LLH_Nuevo_Articulo.findViewById(R.id.delete_art);

                    final Spinner sp_nuevo_articulo_nueva_vuelta = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                    final EditText et_entrega_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                    final EditText et_retiro_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);

                    btnDeleteNuevoArticuloParaLaNuevaVuelta.setVisibility(GONE);

                    sp_nuevo_articulo_nueva_vuelta.setEnabled(false);

                    et_entrega_del_nuevo_articulo.setFocusable(false);
                    et_entrega_del_nuevo_articulo.setHint("");
                    et_entrega_del_nuevo_articulo.setHintTextColor(Color.parseColor("#fafafa"));
                    et_entrega_del_nuevo_articulo.setBackgroundColor(Color.TRANSPARENT);


                    et_retiro_del_nuevo_articulo.setFocusable(false);
                    et_retiro_del_nuevo_articulo.setHint("");
                    et_retiro_del_nuevo_articulo.setHintTextColor(Color.parseColor("#fafafa"));
                    et_retiro_del_nuevo_articulo.setBackgroundColor(Color.TRANSPARENT);




                } //Fin del for del "l" ("Articulos Programáticos")


            } //Fin del primer for


        }  /*Fin del primer if (flag_enabled_vueltas) {}*/



        return flag_enabled_vueltas;



    }/*******FIN DE LA FUNCIÓN DeshabilitarVistasDeLasVueltasAlGuardarCambios() ********/




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






    public void EditarVueltas(boolean flag_edit_vuelta){

        if (flag_edit_vuelta) {



            for(int k=0; k < ArrayListVueltas.size(); k++){

                final ImageButton btnAgregarNuevoArticuloVuelta = (ImageButton) ArrayListVueltas.get(k).findViewById(R.id.add_art_erep);

                final Spinner sp_vuelta = (Spinner) ArrayListVueltas.get(k).findViewById(R.id.sp_art_erep);

                final EditText editText_entrega_vuelta = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_entrega_erep);

                final EditText editText_retiro_vuelta = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_retiro_erep);



                btnAgregarNuevoArticuloVuelta.setVisibility(View.VISIBLE);




                sp_vuelta.setEnabled(true);



                String text_spinner_fijo = sp_vuelta.getSelectedItem().toString();


                if(text_spinner_fijo == "Envases rotos/pinchados" || text_spinner_fijo == "Dispenser plástico roto"
                   || text_spinner_fijo == "Dispenser eléctrico \n averiado"){


                    editText_entrega_vuelta.setEnabled(false);
                    editText_entrega_vuelta.setHint(" ");
                    editText_entrega_vuelta.setText("");
                    editText_entrega_vuelta.setHint("Cantidad");


                } else {



                    editText_entrega_vuelta.setFocusableInTouchMode(true);
                    editText_entrega_vuelta.requestFocus();
                    editText_entrega_vuelta.setCursorVisible(true);
                    editText_entrega_vuelta.setHint("Cantidad");
                    editText_entrega_vuelta.setHintTextColor(Color.parseColor("#9e9e9e"));
                    editText_entrega_vuelta.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));





                    editText_retiro_vuelta.setFocusableInTouchMode(true);
                    editText_retiro_vuelta.setCursorVisible(true);
                    editText_retiro_vuelta.setHint("Cantidad");
                    editText_retiro_vuelta.setHintTextColor(Color.parseColor("#9e9e9e"));
                    editText_retiro_vuelta.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                }








                final LinearLayout LLV_Tercer_Tupla_Patrocinio = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.layout_vertical_tercer_tupla_erep);

                for(int l = 1 ; l < LLV_Tercer_Tupla_Patrocinio.getChildCount() ; l++){

                    final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla_Patrocinio.getChildAt(l);

                    final ImageButton btnDeleteNuevoArticuloVuelta = (ImageButton) LLH_Nuevo_Articulo.findViewById(R.id.delete_art);

                    final Spinner sp_nuevo_articulo_vuelta = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                    final EditText et_entrega_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                    final EditText et_retiro_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);




                    btnDeleteNuevoArticuloVuelta.setVisibility(View.VISIBLE);



                    sp_nuevo_articulo_vuelta.setEnabled(true);




                    String text_spinner_programatico = sp_nuevo_articulo_vuelta.getSelectedItem().toString();


                    if(text_spinner_programatico == "Envases rotos/pinchados" ||
                            text_spinner_programatico == "Dispenser plástico roto" ||
                            text_spinner_programatico == "Dispenser eléctrico averiado"){


                        et_entrega_del_nuevo_articulo.setEnabled(false);
                        et_entrega_del_nuevo_articulo.setHint(" ");
                        et_entrega_del_nuevo_articulo.setText("");
                        et_entrega_del_nuevo_articulo.setHint("Cantidad");


                    } else {

                        et_entrega_del_nuevo_articulo.setFocusableInTouchMode(true);
                        et_entrega_del_nuevo_articulo.setCursorVisible(true);
                        et_entrega_del_nuevo_articulo.setHint("Cantidad");
                        et_entrega_del_nuevo_articulo.setHintTextColor(Color.parseColor("#9e9e9e"));
                        et_entrega_del_nuevo_articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                        et_retiro_del_nuevo_articulo.setFocusableInTouchMode(true);
                        et_retiro_del_nuevo_articulo.setCursorVisible(true);
                        et_retiro_del_nuevo_articulo.setHint("Cantidad");
                        et_retiro_del_nuevo_articulo.setHintTextColor(Color.parseColor("#9e9e9e"));
                        et_retiro_del_nuevo_articulo.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



                    }//Fin del else




                } //Fin del for del "l" ("Articulos Programáticos")


            } //Fin del primer for


        }  /*Fin del primer if (flag_edit_vuelta) {}*/

    }/*******FIN DE LA FUNCION EditarVueltas() ********/




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





    public void MostrarValoresDelSharedPreferencesPatrocinio() {


        SharedPreferences preferences = getSharedPreferences("Datos_Patrocinio_Supervisor", MODE_PRIVATE);



        if (preferences.getBoolean("Indice_Evento" + Indice_Evento + "flag_nueva_vuelta", false)) {

            fab_nueva_vuelta.setVisibility(View.VISIBLE);

        }



        String DimensionArrayNuevasVueltas = preferences.getString("Indice_Evento" + Indice_Evento + "DimensionArrayNuevasVueltas", "");



        if (DimensionArrayNuevasVueltas != "") {

            for (int indice_vueltas = 0; indice_vueltas < Integer.valueOf(DimensionArrayNuevasVueltas); indice_vueltas++) {


                String ElementoSeleccionadoSpinnerFijo = preferences.getString("Indice_Evento" + Indice_Evento + "ElementoSeleccionadoSpinnerFijo - " + "Vuelta Numero: " + indice_vueltas, "");
                String ValorEntregaArticulosFijo = preferences.getString("Indice_Evento" + Indice_Evento + "CantidadEntregaArticuloFijo - " + "Vuelta Numero: " + indice_vueltas, "");
                String ValorRetiroArticulosFijo = preferences.getString("Indice_Evento" + Indice_Evento + "CantidadRetiroArticuloFijo - " + "Vuelta Numero: " + indice_vueltas, "");


                if(indice_vueltas == 0){

                    spinner_para_patrocinio.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ElementoSeleccionadoSpinnerFijo,spinner_para_patrocinio));
                    eTcantEntrega.setText(ValorEntregaArticulosFijo);
                    eTcantRetiro.setText(ValorRetiroArticulosFijo);



                } else {



                    /* Llamada a la función: */
                    ObtenerNuevaVuelta(ElementoSeleccionadoSpinnerFijo,ValorEntregaArticulosFijo, ValorRetiroArticulosFijo);


                }







                String DimensionArticulosProgramaticos = preferences.getString("Indice_Evento" + Indice_Evento + "DimensionArticulosProgramaticos - VueltaNumero: " + indice_vueltas, "");

                if (DimensionArticulosProgramaticos != "") {

                    for (int j = 1; j <= Integer.valueOf(DimensionArticulosProgramaticos); j++) {


                        String ElementoSeleccionadoSpinnerProgramatico = preferences.getString("Indice_Evento" + Indice_Evento + "ElementoSeleccionadoSpinnerProgramatico - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");
                        String ValorEntregaNuevoArticuloProgramatico = preferences.getString("Indice_Evento" + Indice_Evento + "CantidadDeEntregaNuevoArticuloProgramatico - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");
                        String ValorRetiroNuevoArticuloProgramatico = preferences.getString("Indice_Evento" + Indice_Evento + "CantidadDeRetiroNuevoArticuloProgramatico - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");


                        if (ValorEntregaNuevoArticuloProgramatico != "" || ValorRetiroNuevoArticuloProgramatico != "") {

                            final View tanda = ArrayListVueltas.get(indice_vueltas);

                            final LinearLayout tercerTuplaNuevaVuelta = (LinearLayout) tanda.findViewById(R.id.layout_vertical_tercer_tupla_erep);


                            if (indice_vueltas == 0) {


                                Utils_Spinner.contador_de_inicializacion--;


                                ObtenerNuevoArticuloEntregaRetiroPorPatrocinio(ElementoSeleccionadoSpinnerProgramatico,ValorEntregaNuevoArticuloProgramatico, ValorRetiroNuevoArticuloProgramatico, tercerTuplaNuevaVuelta);

                            } else{


                                Utils_Spinner.contador_de_inicializacion--;


                                ObtenerNuevoArticuloEntregaRetiroPorPatrocinio(ElementoSeleccionadoSpinnerProgramatico,ValorEntregaNuevoArticuloProgramatico, ValorRetiroNuevoArticuloProgramatico, tercerTuplaNuevaVuelta);

                            }

                        } //Fin del primer if

                    } //Fin del for

                } //Fin del primer if





            } //Fin del primer for


        } //Fin del primer if




        if(preferences.getBoolean("Indice_Evento" + Indice_Evento + "GuardarLasVistasDeLasVueltasDeshabilitadas",false)){

            DeshabilitarVistasDeLasVueltasAlGuardarCambios(true);

        }




    }  /*****************FIN DE LA FUNCION MostrarValoresDelSharedPreferencesPatrocinio()*******************






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





    public void GuardarValoresEnSharedPreferencesPatrocinio() {

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Patrocinio_Supervisor", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putBoolean("Indice_Evento" + Indice_Evento + "flag_nueva_vuelta", true);
        editor.commit();



        for (int i = 0; i < ArrayListVueltas.size(); i++) {



            int tope_de_articulos = 0;


            final Spinner spinner_fijo = (Spinner) ArrayListVueltas.get(i).findViewById(R.id.sp_art_erep);

            final EditText editText_entrega = (EditText) ArrayListVueltas.get(i).findViewById(R.id.edtx_entrega_erep);

            final EditText editText_retiro = (EditText) ArrayListVueltas.get(i).findViewById(R.id.edtx_retiro_erep);


            String ArticuloSeleccionado =  spinner_fijo.getSelectedItem().toString();

            editor.putString("Indice_Evento" + Indice_Evento + "ElementoSeleccionadoSpinnerFijo - " + "Vuelta Numero: " + i, ArticuloSeleccionado);

            editor.putString("Indice_Evento" + Indice_Evento + "CantidadEntregaArticuloFijo - " + "Vuelta Numero: " + i, editText_entrega.getText().toString());

            editor.putString("Indice_Evento" + Indice_Evento + "CantidadRetiroArticuloFijo - " + "Vuelta Numero: " + i, editText_retiro.getText().toString());



            final LinearLayout LLV_Tercer_Tupla_Patrocinio = (LinearLayout) ArrayListVueltas.get(i).findViewById(R.id.layout_vertical_tercer_tupla_erep);

            tope_de_articulos = LLV_Tercer_Tupla_Patrocinio.getChildCount()-1;




            for(int j = 1 ; j < LLV_Tercer_Tupla_Patrocinio.getChildCount() ; j++){



                final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla_Patrocinio.getChildAt(j);



                final Spinner spinner_nuevos_articulos = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                final EditText et_entrega_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                final EditText et_retiro_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);




                String ArticuloSeleccionadoSpinnerProgramatico =  spinner_nuevos_articulos.getSelectedItem().toString();

                editor.putString("Indice_Evento" + Indice_Evento + "ElementoSeleccionadoSpinnerProgramatico - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j, ArticuloSeleccionadoSpinnerProgramatico);

                editor.putString("Indice_Evento" + Indice_Evento + "CantidadDeEntregaNuevoArticuloProgramatico - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j, et_entrega_del_nuevo_articulo.getText().toString());

                editor.putString("Indice_Evento" + Indice_Evento + "CantidadDeRetiroNuevoArticuloProgramatico - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j, et_retiro_del_nuevo_articulo.getText().toString());


            } //Fin del for del "j" ("Articulos Programáticos")

            editor.putString("Indice_Evento" + Indice_Evento + "DimensionArticulosProgramaticos - VueltaNumero: " + i, String.valueOf(tope_de_articulos));

        } //Fin del primer for

        editor.putString("Indice_Evento" + Indice_Evento + "DimensionArrayNuevasVueltas", String.valueOf(ArrayListVueltas.size()));
        editor.commit();


        editor.putBoolean("Indice_Evento" + Indice_Evento + "GuardarLasVistasDeLasVueltasDeshabilitadas", true);
        editor.commit();









    }/*******************************FIN DE LA FUNCION GuardarValoresEnSharedPreferencesPatrocinio()******************************/









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







    public void BorrarValoresDelSharedPreferencesPatrocinio(){

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Patrocinio_Supervisor", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();



        editor.remove("flag_nueva_vuelta").commit();


        for (int i = 0; i < ArrayListVueltas.size(); i++) {


            /*Llamada a la función */
            EliminarVueltaEnElSharedPreferences(ArrayListVueltas.get(i),editor,i);


        } //Fin del primer for "i" = Vueltas


        editor.remove("DimensionArrayNuevasVueltas").commit();

        editor.remove("GuardarLasVistasDeLasVueltasDeshabilitadas").commit();




    }/*******************************FIN DE LA FUNCION BorrarValoresDelSharedPreferenceasPatrocinio()******************************/







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


    public void CambiarEstadoDeEvento(boolean event_estado){


        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("Estado_Evento" + Indice_Evento, event_estado);
        editor.commit();



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



    public boolean LeerEstadoDeEvento (int indice_de_evento){


        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        return preferences.getBoolean("Estado_Evento" + Indice_Evento,false);


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







    MenuItem BotonGuardar, BotonEditarEvento;


    @Override
    public boolean onCreateOptionsMenu (Menu menu){


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entrega_retiro_envases_patrocinio, menu);


        if(!LeerEstadoDeEvento(Indice_Evento)) {


            fab_nueva_vuelta.setVisibility(GONE);

            menu.findItem(R.id.action_save_vuelta).setVisible(false);

            menu.findItem(R.id.action_finish_evento).setVisible(false);

            menu.findItem(R.id.action_avaible_evento).setVisible(true);

        }//Fin del if


        else {

            menu.findItem(R.id.action_avaible_evento).setVisible(false);

        }




        BotonEditarEvento = menu.findItem(R.id.action_edit_vuelta);


        BotonGuardar = menu.findItem(R.id.action_save_vuelta);




        return true;



    }/*************************FIN DE LA FUNCION onCreateOptionsMenu()****************/










    @Override
    public boolean onOptionsItemSelected (final MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();









        if (id == R.id.action_save_vuelta) {

            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("Desea guardar los cambios realizados?!");
            builder.setMessage("Al presionar el botón 'Guardar' se guardarán los cambios realizados en cada vuelta. ¿Desea continuar?");


            builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {



                    /*Si el evento no fue cerrado*/

                    if(LeerEstadoDeEvento(Indice_Evento)){


                        if(DeshabilitarVistasDeLasVueltasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta())){



                            /*Llamada a la función: */
                            GuardarValoresEnSharedPreferencesPatrocinio();



                        }//Fin del if





                    }//Fin del primer if




                    else {


                        if(DeshabilitarVistasDeLasVueltasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta())){



                            /*Llamada a las funciones: */
                            GuardarValoresEnSharedPreferencesPatrocinio();

                            CambioDeColoresAlGuardarNuevamenteEventoFinalizado();


                        }//Fin del if



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

        }//FIN DEL if (id == R.id.action_save_vuelta)












        if(id == R.id.action_edit_vuelta){




            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("¿Desea modificar algunas de las vueltas realizadas?");
            builder.setMessage("Presione 'SI' en caso que desee editar los campos de algunas de las vueltas.");


            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                    /*Si el evento no fue cerrado*/

                    if(LeerEstadoDeEvento(Indice_Evento)){


                        /*Llamada a la función:  */
                        EditarVueltas(true);

                        fab_nueva_vuelta.setVisibility(GONE);

                        //BotonGuardar.setVisible(true);


                    }//Fin del if




                    else {


                        /*Llamada a la función:  */
                        EditarVueltas(true);

                        CambiarColoresEditarEventoFinalizado();

                        BotonGuardar.setVisible(true);

                        fab_nueva_vuelta.setVisibility(View.VISIBLE);


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



        }//FIN DEL if(id == R.id.action_edit_vuelta)









        if(id == R.id.action_finish_evento){


            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("Importante!");
            builder.setMessage("Está a punto de finalizar el evento. ¿Desea continuar?");


            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {



                    /**Si el evento se cierra sin INCONSISTENCIAS **/


                    SharedPreferences preferences = getSharedPreferences("Datos_Patrocinio_Supervisor", MODE_PRIVATE);

                    Integer DimensionArrayNuevasVueltas = Integer.parseInt(preferences.getString("Indice_Evento" + Indice_Evento + "DimensionArrayNuevasVueltas", "0"));



                    if(ArrayListVueltas.size() == DimensionArrayNuevasVueltas ){



                        if(DeshabilitarVistasDeLasVueltasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta())){


                            /*Llamada a la función: */
                            GuardarValoresEnSharedPreferencesPatrocinio();

                            /*Llamada a la función: */
                            CambiarEstadoDeEvento(false);

                            Intent intent = new Intent(EntregaRetiroEnvasesPatrocinio.this, BuscarResponsableParaPatrocinio.class);

                            startActivity(intent);

                            finish();

                        }



                    }//Fin del primer if


                    else{



                        Toast.makeText(getApplicationContext(), "Error! No es posible cerrar el evento." +
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


        }//FIN DEL if(id == R.id.action_finish_evento)








        if(id == R.id.action_avaible_evento){


            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("Importante!");
            builder.setMessage("Está a punto de habilitar el evento. ¿Desea continuar?");


            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                    /*Llamada a las funciones: */
                    CambiarColoresEditarEventoFinalizado();

                    CambiarEstadoDeEvento(true);



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


        }//FIN DEL if(id == R.id.action_avaible_evento)











        return super.onOptionsItemSelected(item);



    }/****************************************FIN DE LA FUNCIÓN onOptionsItemSelected()*****************************************/





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {

            Intent intent = new Intent (EntregaRetiroEnvasesPatrocinio.this, BuscarResponsableParaPatrocinio.class);

            startActivity(intent);

        }

        return super.onKeyDown(keyCode, event);
    }








                            /*Refrescar activity
                            finish();
                            overridePendingTransition(0,0);
                            startActivity(getIntent());
                            overridePendingTransition(0,0);
                            */



}/******************************* FIN DE LA ACTIVITY EntregaRetiroEnvasesPatrocinio ******************************/
