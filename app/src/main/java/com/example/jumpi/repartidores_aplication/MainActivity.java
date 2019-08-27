package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {



    /****DECLARACIÓN DE VARIABLES GLOBALES *****/





    /***VARIABLES TIPO LinearLayout ****/

    LinearLayout LinearLayoutVerticalCargaDescarga;

    LinearLayout LinearLayoutVerticalVerEstadisticas;

    LinearLayout LinearLayoutVerticalRealizarVentas;

    LinearLayout LinearLayoutVerticalVerMapa;





    /***VARIABLES TIPO String ****/

    String nombre_apellido_cliente = "Montiel Juan Pablo";

    String direccion_cliente = "Calle 22 entre 11 y 13";

    String barrio_cliente ="Centro";



    /***VARIABLES TIPO ImageButton ****/

    ImageButton img_btn_carga_descarga;

    ImageButton img_btn_ver_estadisticas;

    ImageButton img_btn_realizar_venta;

    ImageButton img_btn_ver_mapa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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

            Intent intent = new Intent (MainActivity.this, EleccionRepartidores.class);

            intent.putExtra("activity","Carga_Descarga");

            startActivity(intent);


        }
    });

    img_btn_carga_descarga = (ImageButton) findViewById(R.id.img_btn_cargas_descargas);

    img_btn_carga_descarga.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent (MainActivity.this, EleccionRepartidores.class);

            intent.putExtra("activity","Carga_Descarga");

            startActivity(intent);



        }
    });





    LinearLayoutVerticalVerEstadisticas = (LinearLayout) findViewById(R.id.llv_uno_punto_dos);

    LinearLayoutVerticalVerEstadisticas.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent (MainActivity.this, OpcionesEstadisticas.class);

            startActivity(intent);



        }
    });

        img_btn_ver_estadisticas = (ImageButton) findViewById(R.id.img_btn_estadisticas);

        img_btn_ver_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (MainActivity.this, OpcionesEstadisticas.class);

                startActivity(intent);


            }
        });









        LinearLayoutVerticalRealizarVentas = (LinearLayout) findViewById(R.id.llv_dos_punto_uno);

        LinearLayoutVerticalRealizarVentas.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent (MainActivity.this, RealizarVentasClientesSupervisor.class);

            intent.putExtra("Nombre_Apellido_Cliente_Supervisor",nombre_apellido_cliente);

            intent.putExtra("Direccion_Cliente_Supervisor",direccion_cliente);

            intent.putExtra("Barrio_Cliente_Supervisor",barrio_cliente);



            startActivity(intent);



            }


    });


        img_btn_realizar_venta = (ImageButton) findViewById(R.id.img_btn_realizar_ventas_directas_supervisor);

        img_btn_realizar_venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (MainActivity.this, RealizarVentasClientesSupervisor.class);



                //ESTO DEJARLO COMO COMENTARIO POR EL MOMENTO

                intent.putExtra("Nombre_Apellido_Cliente_Supervisor",nombre_apellido_cliente);

                intent.putExtra("Direccion_Cliente_Supervisor",direccion_cliente);

                intent.putExtra("Barrio_Cliente_Supervisor",barrio_cliente);



                startActivity(intent);

            }
        });






    LinearLayoutVerticalVerMapa = (LinearLayout) findViewById(R.id.llv_dos_punto_dos);

    img_btn_ver_mapa = (ImageButton) findViewById(R.id.img_btn_ver_mapa_supervisor);







    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);





    } /***********************************FIN DEL onCreate()*************************************/






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



                    Intent intent = new Intent (MainActivity.this, BuscarResponsableParaPatrocinio.class);

                    startActivity(intent);


            return true;
        }

        return super.onOptionsItemSelected(item);


    } /*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/





}/*********************************FIN DE LA Activity************************************/
