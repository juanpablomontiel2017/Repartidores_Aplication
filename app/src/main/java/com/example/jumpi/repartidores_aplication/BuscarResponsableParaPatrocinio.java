package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;




import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.plugins.annotation.Line;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.view.View.GONE;

public class BuscarResponsableParaPatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener{



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
        setContentView(R.layout.activity_buscar_responsable_para_patrocinio);





        /** Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));






        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Este es el linearLayout que contiene a todos los eventos
        LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.parent_layout_vertical_buscador_evento);


        lv = (ListView) findViewById(R.id.list_view);






        FloatingActionButton fab = findViewById(R.id.fab_nuevo_responsable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Usuario usuario = new Usuario();

                usuario.LeerUsuarioEnUnSharedPreferences(BuscarResponsableParaPatrocinio.this);

                if(usuario.getTipo_de_Usuario().equals("repartidor")){


                    AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this,R.style.AlertDialogStyleRepartidores);
                    builder.setIcon(R.drawable.ic_ad_addresponsable_repartidores_32px);
                    builder.setTitle("¿Desea añadir un nuevo responsable a la lista?");
                    builder.setMessage("Presione el botón 'CONTINUAR' para completar el formulario con los datos requeridos del responsable del evento.");


                    builder.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {



                            Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoResponsablePatrocinio.class);

                            startActivity(intent);

                            finish();


                        }
                    });





                    builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();

                        }
                    });



                    AlertDialog dialog = builder.create();
                    dialog.show();


                }//FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))




                else {


                    AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this,R.style.AlertDialogStyleSupervisores);
                    builder.setIcon(R.drawable.ic_ad_addresponsable_supervisores_64px);
                    builder.setTitle("¿Desea añadir un nuevo responsable a la lista?");
                    builder.setMessage("Presione el botón 'CONTINUAR' para completar el formulario con los datos requeridos del responsable del evento.");


                    builder.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {



                            Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoResponsablePatrocinio.class);

                            startActivity(intent);

                            finish();


                        }
                    });





                    builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();

                        }
                    });



                    AlertDialog dialog = builder.create();
                    dialog.show();

                }//FIN DEL else (usuario = SUPERVISOR)



            }/*********** FIN DEL EVENTO onClick() ****************/



        });/*********** FIN DEL EVENTO setOnClickListener() ****************/





        /*Llamada a la función: */
        CargaItemDeEvento();


        ArrayListItemEvento.add(LinearLayoutVerticalPadre);





        Usuario usuario = new Usuario();
        usuario.LeerUsuarioEnUnSharedPreferences(this);

        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/
        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            /* Para cambiar el color del puntero o "burbuja" del EditText */
            setTheme(R.style.AppTheme_CursorRepartidor);


            toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
            setSupportActionBar(toolbar);


            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#212121")));

        }//FIN DEL if (usuario.getTipo_de_Usuario().equals("repartidor"))





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


                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, NuevoEventoPatrocinio.class);

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


            final LinearLayout LinearLayoutVerticalContenedorPadre = (LinearLayout) findViewById(R.id.llh_contenedor_evento);


            final LinearLayout LinearLayoutVerticalContenedorHijo = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


            final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);


            final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);


            final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);


            final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


            final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


            final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);


            final LinearLayout LinearLayoutHorizontalFechaFinEstimada = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


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
            ResponsableAdapter = new ResponsableAdapter(BuscarResponsableParaPatrocinio.this, ResponsableArrayList);
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


                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

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

                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, DatosPersonalesResponsable.class);

                    intent.putExtra("Indice_Item", ValorDeLaPosicion);

                    startActivity(intent);

                    finish();

                }
            });




            /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
             * de Patrocinio**/

            Usuario usuario = new Usuario();

            usuario.LeerUsuarioEnUnSharedPreferences(this);

            if (usuario.getTipo_de_Usuario().equals("repartidor")) {

                LinearLayoutVerticalPadre.setBackgroundColor(Color.parseColor("#2962ff"));

                btn_ver_detalle_entrega_retiro_envases_patrocinio.getBackground().setColorFilter(Color.parseColor("#0d47a1"), PorterDuff.Mode.SRC_ATOP);

                btn_ver_datos_personales_responsable_patrocinio.getBackground().setColorFilter(Color.parseColor("#00bcd4"), PorterDuff.Mode.SRC_ATOP);


            }//FIN DEL if (usuario.getTipo_de_Usuario().equals("repartidor"))



        }/******* FIN DEL if (Evento Abierto) *******/



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


            Usuario usuario = new Usuario();
            usuario.LeerUsuarioEnUnSharedPreferences(this);



            if (usuario.getTipo_de_Usuario().equals("repartidor")) {



                View NuevoItemInflado;

                /*Llamada a la función: */
                NuevoItemInflado = AgregarItemEvento();

                ArrayListItemEvento.add(NuevoItemInflado);


                /*Instanciamos las vistas del item_evento*/

                final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);
                view_estado_evento.setBackgroundColor(Color.parseColor("#78909c"));


                final LinearLayout LinearLayoutVerticalPadreDeEvento = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);
                LinearLayoutVerticalPadreDeEvento.setBackgroundColor(Color.parseColor("#37474f"));


                LinearLayoutVerticalPadreDeEvento.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        /*Llamada a la función: */
                        EliminarEvento(v);


                        return false;


                    }/***************FIN DEL EVENTO onLongClick************************/


                });




                final LinearLayout LinearLayoutVerticalContendorEvento = (LinearLayout) findViewById (R.id.llh_contenedor_evento);


                final LinearLayout LinearLayoutVerticalContenedorHijo = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


                final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);


                final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);


                final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);


                final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


                final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


                final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);


                final LinearLayout LinearLayoutHorizontalFechaFinEstimada = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


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


                /*Seteo los valores del evento para mostrarlos en pantalla */
                ET_Nombre_Evento.setText(Nombre_Evento);
                ET_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
                ET_Valor_Fecha_Fin_Estimada_Evento.setText(Fecha_Fin_Estimada_Evento);


                /* Obtenemos los valores guardados del responsable de "x" activity */
                String Nombre_Responsable = preferences_responsable.getString("Nombre_Responsable" + Indice_Responsable, "");
                String Apellido_Responsable = preferences_responsable.getString("Apellido_Responsable" + Indice_Responsable, "");


                /*Seteo los valores del responsable para mostrarlos en pantalla */
                ET_Nombre_Responsable_Evento.setText(Nombre_Responsable);
                ET_Apellido_Responsable_Evento.setText(Apellido_Responsable);



                /*Hacemos el intent hacia la activity en cuestión*/

                btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

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

                        Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, DatosPersonalesResponsable.class);

                        intent.putExtra("Indice_Item", ValorDeLaPosicion);

                        startActivity(intent);

                        finish();
                    }
                });


            }/************ FIN DEL if (usuario.getTipo_de_Usuario().equals("repartidor")) ************/






        /**** Usuario = SUPERVISOR *******/
            else {

                View NuevoItemInflado;

                /*Llamada a la función: */
                NuevoItemInflado = AgregarItemEvento();

                ArrayListItemEvento.add(NuevoItemInflado);



                /*Instanciamos las vistas del item_evento*/

                final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);
                view_estado_evento.setBackgroundColor(Color.parseColor("#78909c"));


                final LinearLayout ItemEvento_LinearLayoutVerticalContenedorPadrePrincipal = (LinearLayout) findViewById(R.id.llv_contenedor_principal_item);


                ItemEvento_LinearLayoutVerticalContenedorPadrePrincipal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        /*Llamada a la función: */
                        EliminarEvento(v);



                    }/***************FIN DEL EVENTO onLongClick************************/


                });


                final LinearLayout ItemEvento_LinearLayoutVerticalHijoDelContenedorPrincipal = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);
                ItemEvento_LinearLayoutVerticalHijoDelContenedorPrincipal.setBackgroundColor(Color.parseColor("#37474f"));


                final LinearLayout ItemEvento_LinearLayoutHorizontalContendorEvento = (LinearLayout) findViewById (R.id.llh_contenedor_evento);


                final LinearLayout ItemEvento_LinearLayoutVerticalContenedorHijoDeEvento = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


                final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);


                final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);


                final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);


                final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


                final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


                final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);


                final LinearLayout LinearLayoutHorizontalFechaFinEstimada = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


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


                        Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

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

                        Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, DatosPersonalesResponsable.class);

                        intent.putExtra("Indice_Item", ValorDeLaPosicion);

                        startActivity(intent);

                        finish();
                    }
                });


            }/*******FIN DEL else (usuario = Supervisor) *********/







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



    public void EliminarEvento(final View v){


        SharedPreferences preferences_evento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences_evento.edit();


        //if(preferences_estado_evento.getBoolean("Estado_Evento" + indice_evento, true)) {


            AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("Desea eliminar el evento seleccionado?!");
            builder.setMessage("Al presionar el botón 'Eliminar Evento' se borrará dicho evento seleccionado. ¿Desea continuar?");


            builder.setPositiveButton("Eliminar Evento", new DialogInterface.OnClickListener() {

                @SuppressLint("RestrictedApi")
                public void onClick(DialogInterface dialog, int id) {



                    if (ArrayListItemEvento.size() > 1) {

                        int ValorDeLaPosicion;

                        ValorDeLaPosicion = EncontrarPosicionDeEvento(v);



                        if(ValorDeLaPosicion != 99){


                           String nombreDeEventoBorrado = arrayListNombreDeEventos.get(ValorDeLaPosicion);

                           //Creo un backup de la colección Datos_Evento
                            BackUpDatosEvento();

                            //Borro toda la colección de Datos_Evento
                            editor.clear().commit();



                           // ArrayListItemEvento.remove(v);



                            //Borro todos los item en pantalla
                            LinearLayoutVerticalPadre.removeAllViews();


                            //Restauro la colección Datos_Evento. No se guarda el evento eliminado
                            restaurarBackupSinElementoBorrado(nombreDeEventoBorrado);


                            //Limpio los arrayList
                            ArrayListItemEvento.clear();
                            arrayListNombreDeEventos.clear();


                            //Se vuelven a recargar los arrayList y los ítem en pantalla
                            RefrescarEventosEnPantalla();


                            Toast.makeText(BuscarResponsableParaPatrocinio.this, "El evento ha sido eliminado", Toast.LENGTH_LONG).show();


                        } else {


                            Toast.makeText(BuscarResponsableParaPatrocinio.this, "Posicion: " + ValorDeLaPosicion, Toast.LENGTH_LONG).show();


                        }


                    } else {

                        Toast.makeText(BuscarResponsableParaPatrocinio.this, "¡No es posible eliminar la primer vuelta. Por favor, tenga en cuenta crear una nueva vuelta!", Toast.LENGTH_LONG).show();

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


       // }//Fin del primer if



    } /*********************************FIN DE LA FUNCIÓN EliminarEvento()*******************************************/







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





    public void BackUpDatosEvento(){


        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);



        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Item_Evento", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionEvento = preferences.getString("DimensionDeEvento", "");


        for (int i = 0; i < Integer.parseInt(DimensionEvento) ; i++) {


          String Nombre_Evento = preferences.getString("Nombre_Evento" + i, "");

          String Direccion_Evento = preferences.getString("Direccion_Evento" + i,"");

          String Barrio_Evento = preferences.getString("Barrio_Evento" + i,"");

          String Referencia_Evento = preferences.getString("Referencia_Evento" + i,"");

          String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento" + i, "");

          String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento" + i, "");

          int Indice_Responsable = preferences.getInt("Indice_Responsable" + i, 0);

          Boolean Estado_Evento = preferences.getBoolean("Estado_Evento" + i, true);


          editor.putString("Nombre_Evento" + i, Nombre_Evento);

          editor.putString("Direccion_Evento" + i, Direccion_Evento);

          editor.putString("Barrio_Evento" + i, Barrio_Evento);

          editor.putString("Referencia_Evento" + i, Referencia_Evento);

          editor.putString("Fecha_Inicio_Evento" + i, Fecha_Inicio_Evento);

          editor.putString("Fecha_Fin_Evento" + i, Fecha_Fin_Evento);

          editor.putInt("Indice_Responsable" + i, Indice_Responsable);

          editor.putBoolean("Estado_Evento" + i, Estado_Evento);


        }//FIN DEL for


        editor.putString("DimensionDeEvento", String.valueOf(DimensionEvento));
        editor.commit();




    }/***************** FIN DE LA FUNCIÓN BackUpDatosEvento() **********************/



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


    public int  EncontrarPosicionDeEvento(View v){

        int posicion_vuelta = 99;

        for(int i = 0 ; i < ArrayListItemEvento.size(); i++ ){

            if(ArrayListItemEvento.get(i) == v ){

                posicion_vuelta = i;

                break;

            }//Fin del if


        }//Fin del for


        return posicion_vuelta;


    }/*********************************FIN DE LA FUNCIÓN EncontrarPosicionDeVuelta()*******************************************/





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



    public View EncontrarNombreEvento (View v){







        return v;

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




    public void restaurarBackupSinElementoBorrado(String nombreDeEventoBorrado) {

        SharedPreferences sharedPreferencesDatosItemEvento = getSharedPreferences("Datos_Item_Evento", MODE_PRIVATE);

        SharedPreferences.Editor editorDatosItemEvento = sharedPreferencesDatosItemEvento.edit();


        String DimensionEvento = sharedPreferencesDatosItemEvento.getString("DimensionDeEvento", "");

        SharedPreferences sharedPreferencesDatosEvento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        SharedPreferences.Editor editorDatosEvento = sharedPreferencesDatosEvento.edit();


        Integer índiceNuevaColección = 0;
        for (int i = 0; i < Integer.parseInt(DimensionEvento); i++) {


            String nombreEvento = sharedPreferencesDatosItemEvento.getString("Nombre_Evento" + i, "");

            if (!nombreEvento.equals(nombreDeEventoBorrado)){



                String Direccion_Evento = sharedPreferencesDatosItemEvento.getString("Direccion_Evento" + i,"");

                String Barrio_Evento = sharedPreferencesDatosItemEvento.getString("Barrio_Evento" + i,"");

                String Referencia_Evento = sharedPreferencesDatosItemEvento.getString("Referencia_Evento" + i,"");

                String Fecha_Inicio_Evento = sharedPreferencesDatosItemEvento.getString("Fecha_Inicio_Evento" + i, "");

                String Fecha_Fin_Evento = sharedPreferencesDatosItemEvento.getString("Fecha_Fin_Evento" + i, "");

                int Indice_Responsable = sharedPreferencesDatosItemEvento.getInt("Indice_Responsable" + i, 0);

                Boolean Estado_Evento = sharedPreferencesDatosItemEvento.getBoolean("Estado_Evento" + i, true);


                editorDatosEvento.putString("Nombre_Evento" + índiceNuevaColección, nombreEvento);

                editorDatosEvento.putString("Direccion_Evento" + índiceNuevaColección, Direccion_Evento);

                editorDatosEvento.putString("Barrio_Evento" + índiceNuevaColección, Barrio_Evento);

                editorDatosEvento.putString("Referencia_Evento" + índiceNuevaColección, Referencia_Evento);

                editorDatosEvento.putString("Fecha_Inicio_Evento" + índiceNuevaColección, Fecha_Inicio_Evento);

                editorDatosEvento.putString("Fecha_Fin_Evento" + índiceNuevaColección, Fecha_Fin_Evento);

                editorDatosEvento.putInt("Indice_Responsable" + índiceNuevaColección, Indice_Responsable);

                editorDatosEvento.putBoolean("Estado_Evento" + índiceNuevaColección, Estado_Evento);

                índiceNuevaColección++;
                editorDatosEvento.putString("DimensionDeEvento", String.valueOf(índiceNuevaColección));
                editorDatosEvento.commit();



            }else{
                //por acá pasa cuando encuentra el elemento eliminado y no lo guarda en la coleccion de
                //Datos_Evento

            }





        } //Fin del primer for

        editorDatosItemEvento.clear().commit();





    } /*******************************FIN DE LA FUNCIÓN restaurarBackupSinElementoBorrado()******************************/


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


    public void RefrescarEventosEnPantalla(){


        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);



        String DimensionDeEvento = preferences.getString("DimensionDeEvento", "");



        if (DimensionDeEvento != "") {

            for (int indice_ev = 0; indice_ev < Integer.valueOf(DimensionDeEvento); indice_ev++) {



                /* Llamada a la función: */
                ObtenerItemEvento(indice_ev);


            } //Fin del primer for


        } //Fin del primer if








    }/*****************FIN DE LA FUNCIÓN RefrescarEventosEnPantalla()**************************/





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

        getMenuInflater().inflate(R.menu.menu_buscar_responsable_o_evento_por_patrocinio, menu);



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



        Usuario usuario = new Usuario();
        usuario.LeerUsuarioEnUnSharedPreferences(this);


        if(usuario.getTipo_de_Usuario().equals("repartidor")) {


            /************ Cambiar color del cursor de cada EditText *************/
            Field f = null;

            try {

                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                f.setAccessible(true);
                f.set(searchTextView, R.drawable.color_cursor_repartidores);

            } catch (Exception e) {

            }



        }//FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))

        else {




            /************ Cambiar color del cursor de cada EditText *************/
            Field f = null;

            try {

                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                f.setAccessible(true);
                f.set(searchTextView, R.drawable.color_cursor_supervisores);

            } catch (Exception e) {

            }







        }//FIN DEL else (usuario = SUPERVISOR)



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