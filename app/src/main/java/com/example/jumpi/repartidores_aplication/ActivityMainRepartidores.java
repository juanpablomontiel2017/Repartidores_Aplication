package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class ActivityMainRepartidores extends AppCompatActivity {


    /****************** DECLARACIÓN DE VARIABLES GLOBALES ****************/

    Button button_ir_mapa_repartidores;

    String ActivityProvenienteDe;





    /************* COMIENZO DEL onCreate() **************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_repartidores);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FragmentStockEnVehiculo fragment_stock_en_vehiculo = new FragmentStockEnVehiculo();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment_stock_en_vehiculo).commit();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        TabItem tabItem_StockEnVehiculo = (TabItem) findViewById(R.id.tab_stock_en_vehiculo);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);







        ActivityProvenienteDe = getIntent().getStringExtra("Activity");


        button_ir_mapa_repartidores = (Button) findViewById(R.id.btn_ir_mapa_repartidores);
        button_ir_mapa_repartidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               Intent intent = new Intent(getApplicationContext(), ActivityMapaRepartidores.class);

               intent.putExtra("Activity","Repartidores_Main_Activity");

               startActivity(intent);


            }
        });






    }/******************************* FIN DEL OnCreate() *********************************/





    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main_repartidores_, menu);


        return true;


    }/************* FIN DEL onCreateOptionsMenu() **************/



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_erep) {


            Intent intent = new Intent (ActivityMainRepartidores.this, ActivityBuscarResponsablePatrocinio.class);

            startActivity(intent);

            return true;


        }





        return super.onOptionsItemSelected(item);


    } /***** FIN DEL onOptionsItemSelected()******/





    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/





    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {


            finishAffinity();


        }

        return super.onKeyDown(keyCode, event);
    }





}/*************************** FIN DE LA Activity Repartidores_Main_Activity ********************************/
