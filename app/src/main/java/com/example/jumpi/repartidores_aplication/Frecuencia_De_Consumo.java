package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class Frecuencia_De_Consumo extends AppCompatActivity {



    /*********************** DECLARACIÓN DE VARIABLES GLOBALES ***********************/


    private BarriosRecyclerViewAdapter adapter;
    private List<Barrios> barriosList;

    RecyclerView recyclerView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frecuencia__de__consumo);



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




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        /*Llamada a las siguientes funciones: */
        ListaDeBarrios();

        setUpRecyclerView();




    }/*************************** FIN DEL onCreate() ******************************/


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




    private void ListaDeBarrios() {

        barriosList = new ArrayList<>();

        barriosList.add(new Barrios(1, R.mipmap.img_barrio,"Centro", "500"));

        barriosList.add(new Barrios(2, R.mipmap.img_barrio,"Puerta del Sol","245"));

        barriosList.add(new Barrios(3, R.mipmap.img_barrio,"Ensanche","623"));

        barriosList.add(new Barrios(4, R.mipmap.img_barrio,"Belgrano","780"));

        barriosList.add(new Barrios(5, R.mipmap.img_barrio,"La Madrid","329"));

        barriosList.add(new Barrios(6, R.mipmap.img_barrio,"Ruchi","444"));

        barriosList.add(new Barrios(7, R.mipmap.img_barrio,"San Martin","553"));

        barriosList.add(new Barrios(8, R.mipmap.img_barrio,"Mariano Moreno", "212"));

        barriosList.add(new Barrios(9, R.mipmap.img_barrio,"Kichnner","111"));

        barriosList.add(new Barrios(10, R.mipmap.img_barrio,"Obrero","322"));

        barriosList.add(new Barrios(11, R.mipmap.img_barrio,"Monseñor De Carlo","100"));

        barriosList.add(new Barrios(12, R.mipmap.img_barrio,"713 Viviendas","543"));


    }/****************** FIN DE LA FUNCIÓN ListaDeBarrios() ***************/


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





    private void setUpRecyclerView() {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new BarriosRecyclerViewAdapter(barriosList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setVisibility(View.GONE);

    }/****************** FIN DE LA FUNCIÓN setUpRecyclerView() ***************/




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

        getMenuInflater().inflate(R.menu.menu_buscar_barrio_frecuencia_consumo, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem == null) {
            return true;
        }

        SearchView searchView = (SearchView) searchItem.getActionView();

        /* Aparece una "lupa" en el lado derecho */
        searchView.setIconifiedByDefault(false);

        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);






        /* Ingresar a la activity con el buscador "activado" */
        searchItem.expandActionView();


        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);




        /** Cambiar color del SearchView **/
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {



                // Set styles for expanded state here
                if (getSupportActionBar() != null) {
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d50000")));
                }

                return true;

            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {


                // Set styles for expanded state here
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d50000")));
                }


                return true;
            }
        });





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                recyclerView.setVisibility(View.VISIBLE);

                adapter.getFilter().filter(newText);

                if(newText.toString().length() < 1) {

                    recyclerView.setVisibility(View.GONE);

                }

                return false;
            }
        });


        return true;

    }/********************* FIN DEL onCreateOptionsMenu() ******************/






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






}
