package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivitySupervisores extends AppCompatActivity {



    /****DECLARACIÓN DE VARIABLES GLOBALES *****/




    LinearLayout LinearLayoutVerticalCargaDescarga;

    LinearLayout LinearLayoutVerticalVerEstadisticas;

    LinearLayout LinearLayoutVerticalRealizarVentas;

    LinearLayout LinearLayoutVerticalVerMapa;



    String nombre_apellido_cliente = "Montiel Juan Pablo";

    String direccion_cliente = "Calle 22 entre 11 y 13";

    String barrio_cliente ="Centro";





    ImageButton img_btn_carga_descarga;

    ImageButton img_btn_ver_estadisticas;

    ImageButton img_btn_realizar_venta;

    ImageButton img_btn_ver_mapa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_supervisores);




        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));









    LinearLayoutVerticalCargaDescarga = (LinearLayout) findViewById(R.id.llv_uno_punto_uno);

    LinearLayoutVerticalCargaDescarga.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (MainActivitySupervisores.this, EleccionRepartidores.class);

            intent.putExtra("activity","Carga_Descarga");

            startActivity(intent);


        }
    });

    img_btn_carga_descarga = (ImageButton) findViewById(R.id.img_btn_cargas_descargas);

    img_btn_carga_descarga.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent (MainActivitySupervisores.this, EleccionRepartidores.class);

            intent.putExtra("activity","Carga_Descarga");

            startActivity(intent);



        }
    });





    LinearLayoutVerticalVerEstadisticas = (LinearLayout) findViewById(R.id.llv_uno_punto_dos);

    LinearLayoutVerticalVerEstadisticas.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent (MainActivitySupervisores.this, OpcionesEstadisticas.class);

            startActivity(intent);



        }
    });

        img_btn_ver_estadisticas = (ImageButton) findViewById(R.id.img_btn_estadisticas);

        img_btn_ver_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (MainActivitySupervisores.this, OpcionesEstadisticas.class);

                startActivity(intent);


            }
        });









        LinearLayoutVerticalRealizarVentas = (LinearLayout) findViewById(R.id.llv_dos_punto_uno);

        LinearLayoutVerticalRealizarVentas.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent (MainActivitySupervisores.this, BuscarClienteParaRealizarVenta.class);

            startActivity(intent);

        }


    });


        img_btn_realizar_venta = (ImageButton) findViewById(R.id.img_btn_realizar_ventas_directas_supervisor);

        img_btn_realizar_venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (MainActivitySupervisores.this, BuscarClienteParaRealizarVenta.class);

                startActivity(intent);

            }
        });






    LinearLayoutVerticalVerMapa = (LinearLayout) findViewById(R.id.llv_dos_punto_dos);

    LinearLayoutVerticalVerMapa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


                Intent intent = new Intent (MainActivitySupervisores.this, Mapa_Supervisores.class);

                startActivity(intent);



        }


    });





    img_btn_ver_mapa = (ImageButton) findViewById(R.id.img_btn_ver_mapa_supervisor);

    img_btn_ver_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (MainActivitySupervisores.this, Mapa_Supervisores.class);

                startActivity(intent);

            }

    });


    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);





    } /***********************************FIN DEL onCreate()*************************************/






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_supervisores, menu);
        return true;
    } /**************FIN DE LA FUNCIÓN onCreateOptionsMenu()********/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_erep) {



            Intent intent = new Intent (MainActivitySupervisores.this, BuscarResponsableParaPatrocinio.class);

            startActivity(intent);


            return true;

        }






        return super.onOptionsItemSelected(item);


    } /*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/








    /**Con este bloque de código es posible salir de la aplicación al presionar el botón "volver atrás" **/


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Add your Dialogue or whatever to alert
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        onKeyDown(KeyEvent.KEYCODE_BACK, null);
    }


}/*********************************FIN DE LA Activity************************************/