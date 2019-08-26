package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.Arrays;

import static android.view.View.GONE;
import static com.example.jumpi.repartidores_aplication.Utils_Spinner.RefrescarOtrosSpinner;

public class EntregaRetiroEnvasesPatrocinio extends AppCompatActivity {



    /*******DECLARACIÓN DE VARIABLES GLOBALES**/


    /******ScrollView********/

    private ScrollView parent_scrollView_erep;



    /** Variables tipo LinearLayout*/
    LinearLayout LinearLayoutVerticalPadre_Patrocinio, LinearLayoutVerticalHijo_Patrocinio, LinearLayoutVerticalTercerTupla_Patrocinio;



    /******ArrayList<>********/

    /*Almacenaremos los campos de entrega y retiro incluyendo los nuevos artículos para todas las vueltas.*/

    ArrayList<View> ArrayListVueltas = new ArrayList<View>();






    /**Variables tipo Spinner**/

    Spinner spinner_para_patrocinio;




    /** Matríz Clásica tipo Cadena */

    String [] ListaDeArticulosParaPatrocinio =  {"Bidones", "Dispenser plástico", "Canillas", "Dispenser eléctrico","Envases rotos/pinchados","Dispenser plástico roto","Dispenser eléctrico descompuesto"};



    /******Variables tipo String********/

    String ArticuloSeleccionadoAnterior;






    /**Variables tipo EditText*/

    EditText eTcantEntrega;
    EditText eTcantRetiro;



    /** Variables tipo ImageButton*/

    ImageButton btnAgregarNuevoArticuloParaPatrocinio;


    /*Para incrementar el número de cada vuelta añadida*/

    int vuelta_numero = 0;


    /******FloatingActionButton********/

    private FloatingActionButton fab_nueva_vuelta;
    private FloatingActionButton fab_cancelar_vuelta;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_retiro_envases_patrocinio);




        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        /*Llamada a la función:  */
        CargarReferenciasPrimerVuelta();






        /* Guardaremos las "Nuevas Vueltas" creadas en ArrayVueltas, donde esas vueltas se ubicarán en el Layout Vertical Padre */
        ArrayListVueltas.add(LinearLayoutVerticalHijo_Patrocinio);











        fab_nueva_vuelta = findViewById(R.id.fab_nueva_entrega_retiro);

        fab_nueva_vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils_Spinner.contador_de_inicializacion--;

                ObtenerNuevaVuelta("","", "");

            }
        });



        fab_cancelar_vuelta = findViewById(R.id.fab_cancelar_entrega_retiro);








        /* Llamada a la función: */
        MostrarValoresDelSharedPreferencesPatrocinio();




        /*CASO ESPECIAL: Si estamos parados en la Primer Tanda, el botón flotante para añadir una nueva
         vuelta no debería ser visible */

        if (ArrayListVueltas.size() > 1) {

            fab_nueva_vuelta.setVisibility(View.VISIBLE);
            fab_cancelar_vuelta.setVisibility(GONE);

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





    public void CargarReferenciasPrimerVuelta(){


        parent_scrollView_erep = (ScrollView) findViewById(R.id.scroll_parent_erep);

        LinearLayoutVerticalPadre_Patrocinio = (LinearLayout) findViewById(R.id.parent_layout_vertical_erep);

        LinearLayoutVerticalHijo_Patrocinio = (LinearLayout) findViewById(R.id.layout_vertical_erep);



        LinearLayoutVerticalHijo_Patrocinio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                /*Llamada a la función: */
                //EliminarCualquierTanda(v);


                return false;


            }/***************FIN DEL EVENTO onLongClick************************/


        });



        LinearLayoutVerticalTercerTupla_Patrocinio = (LinearLayout) findViewById(R.id.layout_vertical_tercer_tupla_erep);











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





    }/******************************FIN DE LA FUNCION CargarReferenciasPrimerVuelta()*****************************/






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



        for(int i=0; i<ArrayListVueltas.size();i++){


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











    } /**************************************FIN DE LA FUNCION ObtenerNuevoArticuloEntregaRetiroPorPatrocinio()*******************************************/







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
        RefrescarOtrosSpinner(spinner ,null, ArticuloSeleccionado, this);




        /*************************************************************************/




        if (ArticuloSeleccionado == "Envases rotos/pinchados" || ArticuloSeleccionado == "Dispenser plástico roto" ||
                ArticuloSeleccionado == "Dispenser eléctrico descompuesto") {

            EditTextEntregaPatrocinio.setEnabled(false);
            EditTextEntregaPatrocinio.setHint("");
            EditTextEntregaPatrocinio.setText("");


        } else {

            EditTextEntregaPatrocinio.setEnabled(true);
            EditTextEntregaPatrocinio.setHint("Cantidad");

        } //Fin del else


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


                    if (text == "Envases rotos/pinchados" || text == "Dispenser plástico roto" ||
                            text == "Dispenser eléctrico descompuesto") {

                        EditTextEntregaPatrocinio.setEnabled(false);
                        EditTextEntregaPatrocinio.setHint("");
                        EditTextEntregaPatrocinio.setText("");


                    } else {

                        EditTextEntregaPatrocinio.setEnabled(true);
                        EditTextEntregaPatrocinio.setHint("Cantidad");

                    } //Fin del else


                    /*Llamada a la función: */
                    Utils_Spinner.RefrescarOtrosSpinner((Spinner)adapterView,ArticuloSeleccionadoAnterior,text, EntregaRetiroEnvasesPatrocinio.this);


                }//Fin del primer else




            }/******************************FIN DEL EVENTO onItemSelected()*****************************/





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }





        });/******************************FIN DEL EVENTO setOnItemSelectedListener()*****************************/





    }/******************************FIN DE LA FUNCIÓN setSpinner()*****************************/





    int ChildNuevaVuelta = 0;

    public void ObtenerNuevaVuelta(String ValorElementoSeleccionadoSpinnerFijo,String ValorSeteadoDelEditTextEntregaDeArticulosParaNuevaVuelta,
                                  String ValorSeteadoDelEditTextRetiroDeArticulosParaNuevaVuelta) {


        View NuevaVueltaInflada;

        /*Llamada a la función: */
        NuevaVueltaInflada = AgregarNuevaVuelta();

        ArrayListVueltas.add(NuevaVueltaInflada);



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




    } /*****************************FIN DE LA FUNCION ObtenerNuevaVuelta()*****************************/


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






        ChildNuevaVuelta = LinearLayoutVerticalPadre_Patrocinio.getChildCount();


        fab_nueva_vuelta.setVisibility(GONE);
        fab_cancelar_vuelta.setVisibility(View.VISIBLE);

        /*Mensaje una vez añadida la nueva tanda*/
        Snackbar.make(InflatedView, "Nueva vuelta añadida", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();





        return InflatedView;


    } /***************************FIN DE LA FUNCION AgregarNuevaVuelta()*****************************/





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




    } /**********************FIN DE LA FUNCION CancelarNuevaVuelta()********************************/








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

    } /*********************************FIN DE LA FUNCION ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta() ****************************/













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



    }/*******FIN DE LA FUNCION DeshabilitarVistasDeLasVueltasAlGuardarCambios() ********/




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

                final ImageButton btnAgregarNuevoArticuloParaLaNuevaVuelta = (ImageButton) ArrayListVueltas.get(k).findViewById(R.id.add_art_erep);

                final Spinner sp_nueva_vuelta = (Spinner) ArrayListVueltas.get(k).findViewById(R.id.sp_art_erep);

                final EditText editText_entrega_nueva_vuelta = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_entrega_erep);

                final EditText editText_retiro_nueva_vuelta = (EditText) ArrayListVueltas.get(k).findViewById(R.id.edtx_retiro_erep);



                btnAgregarNuevoArticuloParaLaNuevaVuelta.setVisibility(View.VISIBLE);




                sp_nueva_vuelta.setEnabled(true);



                editText_entrega_nueva_vuelta.setFocusableInTouchMode(true);
                editText_entrega_nueva_vuelta.requestFocus();
                editText_entrega_nueva_vuelta.setCursorVisible(true);
                editText_entrega_nueva_vuelta.setHint("Cantidad");
                editText_entrega_nueva_vuelta.setHintTextColor(Color.parseColor("#9e9e9e"));
                editText_entrega_nueva_vuelta.setBackgroundColor(Color.TRANSPARENT);





                editText_retiro_nueva_vuelta.setFocusableInTouchMode(true);
                editText_retiro_nueva_vuelta.setCursorVisible(true);
                editText_retiro_nueva_vuelta.setHint("Cantidad");
                editText_retiro_nueva_vuelta.setHintTextColor(Color.parseColor("#9e9e9e"));
                editText_retiro_nueva_vuelta.setBackgroundColor(Color.TRANSPARENT);







                final LinearLayout LLV_Tercer_Tupla_Patrocinio = (LinearLayout) ArrayListVueltas.get(k).findViewById(R.id.layout_vertical_tercer_tupla_erep);

                for(int l = 1 ; l < LLV_Tercer_Tupla_Patrocinio.getChildCount() ; l++){

                    final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla_Patrocinio.getChildAt(l);

                    final ImageButton btnDeleteNuevoArticuloParaLaNuevaVuelta = (ImageButton) LLH_Nuevo_Articulo.findViewById(R.id.delete_art);

                    final Spinner sp_nuevo_articulo_nueva_vuelta = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                    final EditText et_entrega_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                    final EditText et_retiro_del_nuevo_articulo = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);




                    btnDeleteNuevoArticuloParaLaNuevaVuelta.setVisibility(View.VISIBLE);



                    sp_nuevo_articulo_nueva_vuelta.setEnabled(true);



                    et_entrega_del_nuevo_articulo.setFocusableInTouchMode(true);
                    et_entrega_del_nuevo_articulo.setCursorVisible(true);
                    et_entrega_del_nuevo_articulo.setHint("Cantidad");
                    et_entrega_del_nuevo_articulo.setHintTextColor(Color.parseColor("#9e9e9e"));
                    et_entrega_del_nuevo_articulo.setBackgroundColor(Color.TRANSPARENT);




                    et_retiro_del_nuevo_articulo.setFocusableInTouchMode(true);
                    et_retiro_del_nuevo_articulo.setCursorVisible(true);
                    et_retiro_del_nuevo_articulo.setHint("Cantidad");
                    et_retiro_del_nuevo_articulo.setHintTextColor(Color.parseColor("#9e9e9e"));
                    et_retiro_del_nuevo_articulo.setBackgroundColor(Color.TRANSPARENT);




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


        SharedPreferences preferences = getSharedPreferences("Datos", MODE_PRIVATE);



        if (preferences.getBoolean("flag_nueva_vuelta", false)) {

            fab_nueva_vuelta.setVisibility(View.VISIBLE);

        }



        String DimensionArrayNuevasVueltas = preferences.getString("DimensionArrayNuevasVueltas", "");



        if (DimensionArrayNuevasVueltas != "") {

            for (int indice_vueltas = 0; indice_vueltas < Integer.valueOf(DimensionArrayNuevasVueltas); indice_vueltas++) {


                String ElementoSeleccionadoSpinnerFijo = preferences.getString("ElementoSeleccionado - " + "Vuelta Numero: " + indice_vueltas, "");
                String ValorEntregaArticulosNuevaVuelta = preferences.getString("CantidadEntregaArticuloNuevaVuelta - " + "Vuelta Numero: " + indice_vueltas, "");
                String ValorRetiroArticulosNuevaVuelta = preferences.getString("CantidadRetiroArticuloNuevaVuelta - " + "Vuelta Numero: " + indice_vueltas, "");


                if(indice_vueltas == 0){

                    spinner_para_patrocinio.setSelection(Utils_Spinner.ObtenerPosicionDelElementoEnElSpinner(ElementoSeleccionadoSpinnerFijo,spinner_para_patrocinio));
                    eTcantEntrega.setText(ValorEntregaArticulosNuevaVuelta);
                    eTcantRetiro.setText(ValorRetiroArticulosNuevaVuelta);



                } else {



                    /* Llamada a la función: */
                    ObtenerNuevaVuelta(ElementoSeleccionadoSpinnerFijo,ValorEntregaArticulosNuevaVuelta, ValorRetiroArticulosNuevaVuelta);


                }







                String DimensionArticulosProgramaticos = preferences.getString("DimensionArticulosProgramaticos - VueltaNumero: " + indice_vueltas, "");

                if (DimensionArticulosProgramaticos != "") {

                    for (int j = 1; j <= Integer.valueOf(DimensionArticulosProgramaticos); j++) {


                        String ElementoSeleccionadoSpinnerProgramatico = preferences.getString("ElementoSeleccionado_NUEVA_VUELTA - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");
                        String ValorEntregaNuevoArticuloNuevaVuelta = preferences.getString("CantidadDeEntregaNuevoArticuloProgramatico_NUEVA_VUELTA - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");
                        String ValorRetiroNuevoArticuloNuevaVuelta = preferences.getString("CantidadDeRetiroNuevoArticuloProgramatico_NUEVA_VUELTA - " + "Vuelta Numero: " + indice_vueltas + " - " + "Posicion: " + j, "");


                        if (ValorEntregaNuevoArticuloNuevaVuelta != "" && ValorRetiroNuevoArticuloNuevaVuelta != "") {

                            final View tanda = ArrayListVueltas.get(indice_vueltas);

                            final LinearLayout tercerTuplaNuevaVuelta = (LinearLayout) tanda.findViewById(R.id.layout_vertical_tercer_tupla_erep);


                            if (indice_vueltas == 0) {


                                Utils_Spinner.contador_de_inicializacion--;


                                ObtenerNuevoArticuloEntregaRetiroPorPatrocinio(ElementoSeleccionadoSpinnerProgramatico,ValorEntregaNuevoArticuloNuevaVuelta, ValorRetiroNuevoArticuloNuevaVuelta, tercerTuplaNuevaVuelta);

                            } else{


                                Utils_Spinner.contador_de_inicializacion--;


                                ObtenerNuevoArticuloEntregaRetiroPorPatrocinio(ElementoSeleccionadoSpinnerProgramatico,ValorEntregaNuevoArticuloNuevaVuelta, ValorRetiroNuevoArticuloNuevaVuelta, tercerTuplaNuevaVuelta);

                            }

                        } //Fin del primer if

                    } //Fin del for

                } //Fin del primer if





            } //Fin del primer for


        } //Fin del primer if




        if(preferences.getBoolean("GuardarLasVistasDeLasVueltasDeshabilitadas",false)){

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

        SharedPreferences sharedPreferences = getSharedPreferences("Datos", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putBoolean("flag_nueva_vuelta", true);
        editor.commit();



        for (int i = 0; i < ArrayListVueltas.size(); i++) {



            int tope_de_articulos = 0;


            final Spinner spinner_fijo = (Spinner) ArrayListVueltas.get(i).findViewById(R.id.sp_art_erep);

            final EditText editText_entrega = (EditText) ArrayListVueltas.get(i).findViewById(R.id.edtx_entrega_erep);

            final EditText editText_retiro = (EditText) ArrayListVueltas.get(i).findViewById(R.id.edtx_retiro_erep);


            String ArticuloSeleccionado =  spinner_fijo.getSelectedItem().toString();

            editor.putString("ElementoSeleccionado - " + "Vuelta Numero: " + i, ArticuloSeleccionado);

            editor.putString("CantidadEntregaArticuloNuevaVuelta - " + "Vuelta Numero: " + i, editText_entrega.getText().toString());

            editor.putString("CantidadRetiroArticuloNuevaVuelta - " + "Vuelta Numero: " + i, editText_retiro.getText().toString());



            final LinearLayout LLV_Tercer_Tupla_Patrocinio = (LinearLayout) ArrayListVueltas.get(i).findViewById(R.id.layout_vertical_tercer_tupla_erep);

            tope_de_articulos = LLV_Tercer_Tupla_Patrocinio.getChildCount()-1;




            for(int j = 1 ; j < LLV_Tercer_Tupla_Patrocinio.getChildCount() ; j++){



                final LinearLayout LLH_Nuevo_Articulo = (LinearLayout) LLV_Tercer_Tupla_Patrocinio.getChildAt(j);



                final Spinner spinner_nuevos_articulos = (Spinner) LLH_Nuevo_Articulo.findViewById(R.id.sp_new_art);

                final EditText et_entrega_del_nuevo_articulo_nueva_vuelta = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_carga_new_art);

                final EditText et_retiro_del_nuevo_articulo_nueva_vuelta = (EditText) LLH_Nuevo_Articulo.findViewById(R.id.edtx_descarga_new_art);




                String ArticuloSeleccionadoSpinnerProgramatico =  spinner_nuevos_articulos.getSelectedItem().toString();

                editor.putString("ElementoSeleccionado_NUEVA_VUELTA - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j, ArticuloSeleccionadoSpinnerProgramatico);

                editor.putString("CantidadDeEntregaNuevoArticuloProgramatico_NUEVA_VUELTA - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j, et_entrega_del_nuevo_articulo_nueva_vuelta.getText().toString());

                editor.putString("CantidadDeRetiroNuevoArticuloProgramatico_NUEVA_VUELTA - " + "Vuelta Numero: " + i + " - " + "Posicion: " + j, et_retiro_del_nuevo_articulo_nueva_vuelta.getText().toString());


            } //Fin del for del "j" ("Articulos Programáticos")

            editor.putString("DimensionArticulosProgramaticos - VueltaNumero: " + i, String.valueOf(tope_de_articulos));

        } //Fin del primer for

        editor.putString("DimensionArrayNuevasVueltas", String.valueOf(ArrayListVueltas.size()));
        editor.commit();


        editor.putBoolean("GuardarLasVistasDeLasVueltasDeshabilitadas", true);
        editor.commit();









    }/*******************************FIN DE LA FUNCION GuardarValoresEnSharedPreferencesPatrocinio()******************************/









    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entrega_retiro_envases_patrocinio, menu);



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

                    if(DeshabilitarVistasDeLasVueltasAlGuardarCambios(ValidarTodosLosCamposParaGuardarCambiosEnCadaVuelta())){

                        /*Llamada a la función: */

                        GuardarValoresEnSharedPreferencesPatrocinio();

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


            return true;

        }//FIN DEL if (id == R.id.action_save_vuelta)












        if(id == R.id.action_edit_vuelta){




            AlertDialog.Builder builder = new AlertDialog.Builder(EntregaRetiroEnvasesPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("¿Desea modificar algunas de las tandas realizadas?");
            builder.setMessage("Presione 'SI' en caso que desee editar los campos de algunas de las tandas.");


            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                    /*Llamada a la función: */
                    EditarVueltas(true);

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








        return super.onOptionsItemSelected(item);



    }/****************************************FIN DE LA FUNCIÓN onOptionsItemSelected()*****************************************/





















}/*******************************FIN DE LA Activity******************************/
