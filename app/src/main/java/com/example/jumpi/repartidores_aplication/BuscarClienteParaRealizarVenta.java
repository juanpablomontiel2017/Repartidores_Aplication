package com.example.jumpi.repartidores_aplication;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

public class BuscarClienteParaRealizarVenta extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente_para_realizar_venta);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar_cliente_para_realizar_venta, menu);

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

}/*********************** FIN DE LA ACTIVITY *************************/
