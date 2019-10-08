package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class BuscarResponsableParaPatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener {



    Button btnResponsableInactivo;

    public static Button btnResponsableActivo;

    private ScrollView parent_scrollView;


    LinearLayout LinearLayoutVerticalPadre;


    ArrayList<View> ArrayListItemEvento = new ArrayList<View>();



    /************************* COMIENZO DEL onCreate() ******************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_responsable_para_patrocinio);











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



        parent_scrollView = (ScrollView) findViewById(R.id.scroll_parent_brpp);

        LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.parent_layout_vertical_buscador_evento);




        /*


        btnResponsableInactivo = (Button) findViewById(R.id.btn_responsable_inactivo);

        btnResponsableInactivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this);
                builder.setIcon(R.drawable.ic_notificacion_nuevo_evento);
                builder.setTitle("¿Desea añadir un nuevo evento para esta persona?");
                builder.setMessage("Presione el botón 'ACEPTAR' para completar los datos necesarios del nuevo evento.");


                builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoEventoPatrocinio.class);

                        startActivity(intent);

                    }
                });





                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();


            }


        }); */







/*


        btnResponsableActivo = (Button) findViewById(R.id.btn_responsable_activo);

        btnResponsableActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

                startActivity(intent);




            }



        });


*/

/*

        btnResponsableActivo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this);
                builder.setIcon(R.drawable.ic_notificacion_nuevo_evento);
                builder.setTitle("¿Desea añadir un nuevo evento para esta persona?");
                builder.setMessage("Presione el botón 'ACEPTAR' para completar los datos necesarios del nuevo evento.");


                builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoEventoPatrocinio.class);

                        startActivity(intent);

                    }


                });





                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();






                return false;
            }



        });   */




        FloatingActionButton fab = findViewById(R.id.fab_nuevo_responsable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this);
                builder.setIcon(R.drawable.ic_notificacion_nuevo_responsable);
                builder.setTitle("¿Desea añadir un nuevo responsable a la lista?");
                builder.setMessage("Presione el botón 'CONTINUAR' para completar el formulario con los datos requeridos del responsable del evento.");


                builder.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoResponsablePatrocinio.class);

                        startActivity(intent);



                    }
                });





                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();




            }/*********** FIN DEL EVENTO onClick() ****************/



        });/*********** FIN DEL EVENTO setOnClickListener() ****************/







        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
            setSupportActionBar(toolbar);


            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#03a9f4")));

        }







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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar_responsable_o_evento_por_patrocinio, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        searchView.setIconified(false);


        return true;

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




    public void CargaItemDeEvento(){

        int dimension = 0;

        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);

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

    public  void ObtenerItemEvento(int indice){


        View NuevoItemInflado;

        /*Llamada a la función: */
        NuevoItemInflado = AgregarItemEvento();

        ArrayListItemEvento.add(NuevoItemInflado);





        final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);


        final LinearLayout LinearLayoutVerticalContenedorHijo = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


        final TextView TV_Nombre_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_nombre_evento_recibir);


        final TextView TV_Nombre_Apellido_Responsable_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_nombre_apellido_responsable_recibir);


        final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


        final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


        final TextView TV_Valor_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_valor_fecha_inicio_recibir);


        final LinearLayout LinearLayoutHorizontalFechaFin = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


        final TextView TV_Fecha_Fin_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_fin_recibir);


        final TextView TV_Valor_Fecha_Fin_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_valor_fecha_fin_recibir);





        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);



        String Nombre_Evento = preferences.getString("Nombre_Evento" + indice, "");
        String Nombre_Apellido_Responsable = preferences.getString("Nombre_Apellido_Responsable" + indice, "");
        String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento" + indice, "");
        String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento" + indice, "");




        TV_Nombre_Evento.setText(Nombre_Evento);
        TV_Nombre_Apellido_Responsable_Evento.setText(Nombre_Apellido_Responsable);
        TV_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
        TV_Valor_Fecha_Fin_Evento.setText(Fecha_Fin_Evento);



        final Button btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);
        btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

                intent.putExtra("Nombre_del_evento", TV_Nombre_Evento.getText());

                intent.putExtra("Nombre_del_responsable", TV_Nombre_Apellido_Responsable_Evento.getText());


                startActivity(intent);

            }
        });


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


    public void LeerEventoEnSharedPreferences(){

        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);



        String DNI_Responsable = preferences.getString("DNI_Responsable", "");

        String Nombre_Apellido_Responsable =  preferences.getString("Nombre_Apellido_Responsable", "");

        String Telefono_Responsable = preferences.getString("Telefono_Responsable", "");

        String Direccion_Responsable = preferences.getString("Direccion_Responsable", "");

        String Barrio_Responsable = preferences.getString("Barrio_Responsable", "");

        String Correo_Responsable = preferences.getString("Correo_Responsable", "");

        String Referencia_Responsable = preferences.getString("Referencia_Responsable", "");




        String Nombre_Evento = preferences.getString("Nombre_Evento", "");

        String Direccion_Evento = preferences.getString("Direccion_Evento", "");

        String Barrio_Evento = preferences.getString("Barrio_Evento", "");

        String Referencia_Evento = preferences.getString("Referencia_Evento", "");

        String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento", "");

        String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento", "");






    }/******************** FIN DE LA FUNCIÓN LeerEventoEnSharedPreferences() *******************/










}/*************************** FIN DE LA ACTIVITY BuscarResponsableParaPatrocinio ******************************/