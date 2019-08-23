package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BuscarResponsableParaPatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener {



    Button btnResponsableInactivo, btnResponsableActivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_responsable_para_patrocinio);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
        });











        btnResponsableActivo = (Button) findViewById(R.id.btn_responsable_activo);


        btnResponsableActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

                startActivity(intent);


            }



        });



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
        });




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





            }
        });







    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar_responsable_o_evento_por_patrocinio, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        searchView.setIconified(false);


        return true;

    }




    @Override
    public boolean onQueryTextSubmit(String query) {

        // User pressed the search button
        return false;

    }

    @Override

    public boolean onQueryTextChange(String newText) {

        // User changed the text
        return false;

    }









}
