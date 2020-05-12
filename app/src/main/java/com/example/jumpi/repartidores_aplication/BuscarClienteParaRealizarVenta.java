package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mapbox.mapboxsdk.style.layers.Property;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class BuscarClienteParaRealizarVenta extends AppCompatActivity{


    /*********************** DECLARACIÓN DE VARIABLES GLOBALES ***********************/


    private ClientesRecyclerViewAdapter_ListaCompleta adapter;
    private List<Clientes> clientesList;

    RecyclerView recyclerView;

    Usuario usuario;


    /*********************** COMIENZO DEL onCreate() ************************/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente_para_realizar_venta);



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


        Usuario usuario = new Usuario();
        usuario.LeerUsuarioEnUnSharedPreferences(this);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        /*Llamada a las siguientes funciones: */
        ListaCompletaDeClientes();

        setUpRecyclerView();


        /**********************************************/
        /**********************************************/
        /**********************************************/
        /**********************************************/
        /**********************************************/



        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
        * de Patrocinio**/
        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            /* Para cambiar el color del puntero o "burbuja" del EditText */
            setTheme(R.style.AppTheme_CursorRepartidor);


            toolbar.setBackgroundColor(Color.parseColor("#283593"));
            setSupportActionBar(toolbar);


        }// FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))






    }/********************* FIN DEL onCreate()**************************/




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






    private void ListaCompletaDeClientes() {

        clientesList = new ArrayList<>();

        clientesList.add(new Clientes(38765245,1, R.mipmap.cliente_img1_hombrebarbudo_512px,"Kanje",
                "George", "Calle 6 entre 9 y 11","Belgrano",
                "Casi esquina 11, en mano izquierda con un portón de chapa. Tocar timbre 2","364","4445654",
                "No tiene correo"));

        clientesList.add(new Clientes(35543234,2,R.mipmap.cliente_img2_hombrerulos_512px, "Calamaro",
                "Andrés","Calle 29 entre 12 y 14", "‣Centro","Casa azul con frente floreado",
                "364","4665543","No tiene correo"));

        clientesList.add(new Clientes(38235123,3,R.mipmap.cliente_img3_profesor_512px, "Smoker",
                "Alejandro","Calle 29 entre 12 y 10","Centro","Casa de 2 pisos, al lado tiene un portón como garage","364","4654321","No tiene correo"));

        clientesList.add(new Clientes(32456953,4,R.mipmap.cliente_img4_policia_512px, "Gomez",
                "Alberto","Calle 1 entre 12 y 10", "Centro", "Comisaría N°92",
                "364","4911999","No tiene correo"));

        clientesList.add(new Clientes(35444999,5,R.mipmap.cliente_img5_sra_512px, "Ruiz",
                "Viviana", "Calle 8 entre 22 y 24", "La Madrid", "Casa blanca con portón de madera",
                "364","4965433", "vivana_ruiz@gmail.com"));

        clientesList.add(new Clientes(34445885,6,R.mipmap.cliente_img6_profesor_512px, "Iznardo",
                "Natanael","Calle 19 esquina 14","Centro", "Ferretería Norte",
                "364","4595959","natanaeliznardo@gmail.com"));

        clientesList.add(new Clientes(27456432,7,R.mipmap.cliente_img7_abuelo_512px, "Montiel",
                "Timoteo", "Calle 12 entre 21 y 23","Centro", "Al lado de la universidad Siglo 21", "364","4461211","No tiene correo"));

        clientesList.add(new Clientes(38456321,8,R.mipmap.cliente_img8_chica_512px, "Medina",
                "Belén","Calle 11 entre 3 y 5", "Loma Linda","Al lado del papa Francisco",
                "364","4456788","No tiene correo"));

        clientesList.add(new Clientes(34567965,9,R.mipmap.cliente_img9_dentista_512px, "Uribarri",
                "Celeste","Calle 12 entre 47 y 49","San Martín","Mansión de 2 pisos con letrero con el nombre de la cliente","364","4998822","No tiene correo"));




    }/****************** FIN DE LA FUNCIÓN ListaCompletaDeClientes() ***************/





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
        adapter = new ClientesRecyclerViewAdapter_ListaCompleta(clientesList);

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

        getMenuInflater().inflate(R.menu.menu_buscar_cliente_para_realizar_venta, menu);

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

                Usuario usuario = new Usuario();
                usuario.LeerUsuarioEnUnSharedPreferences(getApplicationContext());


                if(usuario.getTipo_de_Usuario().equals("repartidor")){

                    // Set styles for expanded state here
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#283593")));
                    }

                }// FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))


                else {

                    // Set styles for expanded state here
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d50000")));
                    }

                }


                return true;


            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {


                Usuario usuario = new Usuario();
                usuario.LeerUsuarioEnUnSharedPreferences(getApplicationContext());


                if(usuario.getTipo_de_Usuario().equals("repartidor")){

                    // Set styles for expanded state here
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#283593")));
                    }

                }// FIN DEL if(usuario.getTipo_de_Usuario().equals("repartidor"))


                else {

                    // Set styles for expanded state here
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d50000")));
                    }

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





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_add_cliente_buscar_cliente_para_venta) {


            Intent intent = new Intent (BuscarClienteParaRealizarVenta.this, AgregarCliente.class);

            startActivity(intent);


            return true;

        }




        return super.onOptionsItemSelected(item);


    } /*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/





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


}/*********************** FIN DE LA ACTIVITY BuscarClienteParaRealizarVenta *************************/
