package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

public class BuscarResponsableParaPatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener {



    Button btnResponsableInactivo;

    public static Button btnResponsableActivo;


    LinearLayout LinearLayoutVerticalPadre;



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




    @SuppressLint("RestrictedApi")
    public View AgregarItemEvento(){


        LayoutInflater inflateItemEvento = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflateItemEvento.inflate(R.layout.item_evento, null, true);


        LinearLayoutVerticalPadre.addView(InflatedView,0);





        /*InflatedView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                EliminarCualquierVuelta(v);

                return false;


            }


        });*/





        return InflatedView;


    } /***************************FIN DE LA FUNCIÓN AgregarItemEvento()*****************************/




    public void RecibirDatosDelNuevoResponsableEvento(TextView tv_nombre_del_evento, TextView tv_nombre_apellido_responsable
                                                        , TextView tv_fecha_del_evento){

        String extras = getIntent().getStringExtra("NombreEvento");

        tv_nombre_del_evento.setText(extras);



        extras = getIntent().getStringExtra("ApellidoNombre");

       //Direccion_Cliente_Ventas_Supervisor.setText(extras);


        extras = getIntent().getStringExtra("FechaInicioEvento");

       //Direccion_Cliente_Ventas_Supervisor.setText(extras);



        extras = getIntent().getStringExtra("FechaFinEvento");

        //Direccion_Cliente_Ventas_Supervisor.setText(extras);




    }/***************** FIN DE LA FUNCIÓN RecibirDatosDelNuevoResponsableEvento ****************/






}/*************************** FIN DE LA ACTIVITY BuscarResponsableParaPatrocinio ******************************/
