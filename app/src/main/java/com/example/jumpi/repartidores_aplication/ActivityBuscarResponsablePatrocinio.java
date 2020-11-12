package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;




import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityBuscarResponsablePatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener{



    /******************** DECLARACIÓN DE VARIABLES GLOBALES ***********************/

    LinearLayout LinearLayoutVerticalPadre;

    ArrayList<View> ArrayListItemEvento = new ArrayList<View>();

    ArrayList<String> arrayListNombreDeEventos = new ArrayList<String>();




    private ListView lv;

    private ArrayList<Responsable_Patrocinio> ResponsableArrayList = new ArrayList<Responsable_Patrocinio>();

    private com.example.jumpi.repartidores_aplication.ResponsableAdapter ResponsableAdapter;






    /************************* COMIENZO DEL onCreate() ******************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_responsable_patrocinio);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //LinearLayout que contiene a todos los eventos
        LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.parent_layout_vertical_buscador_evento);


        lv = (ListView) findViewById(R.id.list_view);






        FloatingActionButton fab = findViewById(R.id.fab_nuevo_responsable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent (ActivityBuscarResponsablePatrocinio.this, ActivityNuevoResponsablePatrocinio.class);

                startActivity(intent);

                finish();



            }/*********** FIN DEL EVENTO onClick() ****************/

        });/*********** FIN DEL EVENTO setOnClickListener() ****************/





        /*Llamada a la función: */
        CargaItemDeEvento();





    }/******************************* FIN DEL onCreate() ***********************************/




    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/



    @Override
    public boolean onQueryTextSubmit(String query) {

        // User pressed the search button

        return false;

    }




    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/



    @Override
    public boolean onQueryTextChange(String newText) {




        // User changed the text
        if(newText.equals("") ) {

            lv.setVisibility(View.GONE);


        } else {

            //BuscarResponsableParaPatrocinio.this.Responsableadapter.getFilter().filter(newText.toString()); me ayuda a filtrar con mejores resultados (buscar una solucion luego)
            lv.setVisibility(View.VISIBLE);



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {



                    Responsable_Patrocinio objResponsable;

                    objResponsable = (Responsable_Patrocinio) lv.getAdapter().getItem(position);

                    Integer idResponsable = objResponsable.getId();


                    Intent intent = new Intent(ActivityBuscarResponsablePatrocinio.this, ActivityNuevoEventoPatrocinio.class);

                    intent.putExtra("Indice_Item", idResponsable);

                    startActivity(intent);

                    finish();



                }
            });



       }



        return false;

    }/********************* FIN DE LA FUNCIÓN onQueryTextChange() ********************/






    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/




    public void CargaItemDeEvento(){

        int dimension = 0;

        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        String DimensionEvento = preferences.getString("DimensionDeEvento", "");

        if(DimensionEvento != ""){

            dimension = Integer.parseInt(DimensionEvento);

            for(int i=0; i <= dimension; i++){

                ObtenerItemEvento(i);

            }//Fin del for



        }//Fin del if




    }/******************************* FIN DE LA FUNCIÓN CargaItemDeEvento() ***********************************/





    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/





    int ChildNuevoItemEvento = 0;

    public  void ObtenerItemEvento(final int indice_evento){


        SharedPreferences preferences_evento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        SharedPreferences preferences_responsable = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);



        /***** Evento Abierto *****/
        if(preferences_evento.getBoolean("Estado_Evento" + indice_evento, false)){


            View NuevoItemInflado;

            /*Llamada a la función: */
            NuevoItemInflado = AgregarItemEvento();

            ArrayListItemEvento.add(NuevoItemInflado);




            /*Instanciamos las vistas del item_evento*/

            final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);

            final LinearLayout LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);

            final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);

            final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);

            final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);

            final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);

            final TextView TV_Fecha_Fin_Estimada_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_fin_recibir);
            TV_Fecha_Fin_Estimada_Evento.setText("FECHA DE FIN ESTIMADA");

            final EditText ET_Valor_Fecha_Fin_Estimada_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_fin_recibir);

            Button btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);

            Button btn_ver_datos_personales_responsable_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_datos_personales_item_evento);




            /* Obtenemos los valores guardados del evento de "x" activity*/
            String Nombre_Evento = preferences_evento.getString("Nombre_Evento" + indice_evento, "");
            arrayListNombreDeEventos.add(Nombre_Evento);

            String Fecha_Inicio_Evento = preferences_evento.getString("Fecha_Inicio_Evento" + indice_evento, "");
            String Fecha_Fin_Estimada_Evento = preferences_evento.getString("Fecha_Fin_Evento" + indice_evento, "");
            int Indice_Responsable = preferences_evento.getInt("Indice_Responsable" + indice_evento, 0);




            /* Seteamos los valores guardados del evento*/
            ET_Nombre_Evento.setText(Nombre_Evento);
            ET_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
            ET_Valor_Fecha_Fin_Estimada_Evento.setText(Fecha_Fin_Estimada_Evento);


            /* Obtenemos los valores guardados del responsable de "x" activity*/
            String Nombre_Responsable = preferences_responsable.getString("Nombre_Responsable" + Indice_Responsable, "");
            String Apellido_Responsable = preferences_responsable.getString("Apellido_Responsable" + Indice_Responsable, "");


            /* Seteamos los valores guardados del responsable*/
            ET_Nombre_Responsable_Evento.setText(Nombre_Responsable);
            ET_Apellido_Responsable_Evento.setText(Apellido_Responsable);



            /*Guardamos en un ListView el nombre, apellido y el id del responsable */
            ResponsableArrayList.add(new Responsable_Patrocinio(Nombre_Responsable, Apellido_Responsable, Indice_Responsable));
            ResponsableAdapter = new ResponsableAdapter(ActivityBuscarResponsablePatrocinio.this, ResponsableArrayList);
            lv.setAdapter(ResponsableAdapter);
            lv.setTextFilterEnabled(true);






            /**********************************************************************/
            /**********************************************************************/
            /**********************************************************************/
            /**********************************************************************/
            /**********************************************************************/


            /*Cierro el evento donde su fecha de fin ha expirado, por ende
             * cambio los colores de las vistas */
            String Fecha_Actual = UtilidadFecha.getFecha("dd/MM/yyyy");

            if(Fecha_Fin_Estimada_Evento.equals(Fecha_Actual)) {


                view_estado_evento.setBackgroundColor(Color.parseColor("#78909c"));

                LinearLayoutVerticalPadre.setBackgroundColor(Color.parseColor("#37474f"));

                btn_ver_detalle_entrega_retiro_envases_patrocinio.getBackground().setColorFilter(Color.parseColor("#616161"), PorterDuff.Mode.SRC_ATOP);

                btn_ver_datos_personales_responsable_patrocinio.getBackground().setColorFilter(Color.parseColor("#424242"), PorterDuff.Mode.SRC_ATOP);


            } //Fin del primer if(Fecha_Fin_Estimada_Evento.equals(Fecha_Actual))


            /**********************************************************************/
            /**********************************************************************/
            /**********************************************************************/
            /**********************************************************************/
            /**********************************************************************/


            /*Hacemos un intent para ir hacia una activity en cuestión*/

            btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);
            btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(ActivityBuscarResponsablePatrocinio.this, ActivityEntregaRetiroEnvasesPatrocinio.class);

                    intent.putExtra("Indice_Evento", indice_evento);

                    intent.putExtra("Nombre_del_evento", ET_Nombre_Evento.getText().toString());

                    intent.putExtra("Nombre_del_responsable", ET_Nombre_Responsable_Evento.getText().toString());

                    intent.putExtra("Apellido_del_responsable", ET_Apellido_Responsable_Evento.getText().toString());


                    startActivity(intent);

                    finish();

                }
            });


            btn_ver_datos_personales_responsable_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_datos_personales_item_evento);
            btn_ver_datos_personales_responsable_patrocinio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int ValorDeLaPosicion;

                    ValorDeLaPosicion = EncontrarPosicionDeItemEvento(view);

                    Intent intent = new Intent(ActivityBuscarResponsablePatrocinio.this, ActivityDatosPersonalesPatrocinio.class);

                    intent.putExtra("Indice_Item", ValorDeLaPosicion);

                    startActivity(intent);

                    finish();

                }
            });



        }  /******* FIN DEL if (Evento Abierto) *******/



        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/
        /*************************************************************************************/





        /***** Evento Cerrado *****/
        else {


                View NuevoItemInflado;

                /*Llamada a la función: */
                NuevoItemInflado = AgregarItemEvento();

                ArrayListItemEvento.add(NuevoItemInflado);



                /*Instanciamos las vistas del item_evento*/

                final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);
                view_estado_evento.setBackgroundColor(Color.parseColor("#78909c"));


                final LinearLayout ItemEvento_LinearLayoutVerticalHijoDelContenedorPrincipal = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);
                ItemEvento_LinearLayoutVerticalHijoDelContenedorPrincipal.setBackgroundColor(Color.parseColor("#37474f"));


                final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);

                final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);

                final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);

                final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);




                final TextView TV_Fecha_Fin_Estimada_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_fin_recibir);
                TV_Fecha_Fin_Estimada_Evento.setText("FECHA DE FIN");


                final EditText ET_Valor_Fecha_Fin_Estimada_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_fin_recibir);


                final Button btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);
                btn_ver_detalle_entrega_retiro_envases_patrocinio.getBackground().setColorFilter(Color.parseColor("#616161"), PorterDuff.Mode.SRC_ATOP);


                final Button btn_ver_datos_personales_responsable_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_datos_personales_item_evento);
                btn_ver_datos_personales_responsable_patrocinio.getBackground().setColorFilter(Color.parseColor("#424242"), PorterDuff.Mode.SRC_ATOP);




                /* Obtenemos los valores guardados del evento de "x" activity */
                String Nombre_Evento = preferences_evento.getString("Nombre_Evento" + indice_evento, "");
                arrayListNombreDeEventos.add(Nombre_Evento);
                String Fecha_Inicio_Evento = preferences_evento.getString("Fecha_Inicio_Evento" + indice_evento, "");
                String Fecha_Fin_Estimada_Evento = preferences_evento.getString("Fecha_Fin_Evento" + indice_evento, "");
                int Indice_Responsable = preferences_evento.getInt("Indice_Responsable" + indice_evento, 0);


                /*Seteo los valores del evento para mostrarlos en pantalla*/
                ET_Nombre_Evento.setText(Nombre_Evento);
                ET_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
                ET_Valor_Fecha_Fin_Estimada_Evento.setText(Fecha_Fin_Estimada_Evento);


                /* Obtenemos los valores guardados del responsable de "x" activity*/
                String Nombre_Responsable = preferences_responsable.getString("Nombre_Responsable" + Indice_Responsable, "");
                String Apellido_Responsable = preferences_responsable.getString("Apellido_Responsable" + Indice_Responsable, "");


                /*Seteo los valores del responsable para mostrarlos en pantalla*/
                ET_Nombre_Responsable_Evento.setText(Nombre_Responsable);
                ET_Apellido_Responsable_Evento.setText(Apellido_Responsable);




                /*Hacemos el intent hacia la activity en cuestión */
                btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(ActivityBuscarResponsablePatrocinio.this, ActivityEntregaRetiroEnvasesPatrocinio.class);

                        intent.putExtra("Nombre_del_evento", ET_Nombre_Evento.getText().toString());

                        intent.putExtra("Nombre_del_responsable", ET_Nombre_Responsable_Evento.getText().toString());

                        intent.putExtra("Apellido_del_responsable", ET_Apellido_Responsable_Evento.getText().toString());


                        startActivity(intent);

                        finish();

                    }
                });




                btn_ver_datos_personales_responsable_patrocinio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        int ValorDeLaPosicion;

                        ValorDeLaPosicion = EncontrarPosicionDeItemEvento(view);

                        Intent intent = new Intent(ActivityBuscarResponsablePatrocinio.this, ActivityDatosPersonalesPatrocinio.class);

                        intent.putExtra("Indice_Item", ValorDeLaPosicion);

                        startActivity(intent);

                        finish();
                    }
                });



        }/********* FIN DEL else (Evento Cerrado) **/


    }/***************************FIN DE LA FUNCIÓN ObtenerItemEvento()*****************************/





    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/




    @SuppressLint("RestrictedApi")
    public View AgregarItemEvento(){


        LayoutInflater inflateItemEvento = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflateItemEvento.inflate(R.layout.item_evento, null, true);

        LinearLayoutVerticalPadre.addView(InflatedView,0);

        ChildNuevoItemEvento = LinearLayoutVerticalPadre.getChildCount();


        return InflatedView;


    } /***************************FIN DE LA FUNCIÓN AgregarItemEvento()*****************************/




    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/



    public View EncontrarItemPadreBotonVerDatosResponsble (View button_ver_responsable){


        LinearLayout linearLayoutHorizontalButtonVerResponsable = (LinearLayout) button_ver_responsable.getParent();

        LinearLayout linearLayoutVerticalContenedorVistas = (LinearLayout) linearLayoutHorizontalButtonVerResponsable.getParent();

        LinearLayout linearLayoutHorizontalPadreContenedorVistas = (LinearLayout) linearLayoutVerticalContenedorVistas.getParent();

        LinearLayout linearLayoutVerticalPadredePadres = (LinearLayout) linearLayoutHorizontalPadreContenedorVistas.getParent();

        LinearLayout linearLayoutVerticalContenedorDeTodoElItem = (LinearLayout) linearLayoutVerticalPadredePadres.getParent();


        return linearLayoutVerticalContenedorDeTodoElItem;


    } /******************************FIN DE LA FUNCION EncontrarItemPadreBotonVerDatosResponsble()*****************************/




    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/







    public int  EncontrarPosicionDeItemEvento(View button_ver_responsable){

        int posicion_item = 99;

        View Item = EncontrarItemPadreBotonVerDatosResponsble(button_ver_responsable);

        for(int i = 0 ; i < ArrayListItemEvento.size(); i++ ){

            if(ArrayListItemEvento.get(i) == Item ){

                posicion_item = i;

                break;

            }//Fin del if


        }//Fin del for


        return posicion_item;


    }/*********************************FIN DE LA FUNCIÓN EncontrarPosicionDeItemEvento()*******************************************/


    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/







    SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar_responsable_patrocinio, menu);



        MenuItem searchItem = menu.findItem(R.id.search);

        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Buscar responsable");




        searchView.setOnQueryTextListener(this);

        /* Aparece una "lupa" en el lado derecho */
        searchView.setIconifiedByDefault(false);

        //searchView.setIconified(false);

        /* Ingresar a la activity con el buscador "activado" */
        searchItem.expandActionView();


        SearchView.SearchAutoComplete searchTextView = searchView.findViewById(R.id.search_src_text);

        return true;

    } /************** Fin del método onCreateOptionsMenu() ******************/






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        return super.onOptionsItemSelected(item);


    } /***** FIN DEL onOptionsItemSelected()******/


    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/






}/*************************** FIN DE LA ACTIVITY BuscarResponsableParaPatrocinio ******************************/