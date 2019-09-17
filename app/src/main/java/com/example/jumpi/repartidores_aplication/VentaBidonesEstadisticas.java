package com.example.jumpi.repartidores_aplication;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class VentaBidonesEstadisticas extends AppCompatActivity implements View.OnClickListener, VentaBidonesMensual_Fragment.OnFragmentInteractionListener, VentaBidonesSemestral_Fragment.OnFragmentInteractionListener, VentaBidonesAnual_Fragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_bidones_estadisticas);


        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));





        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        VentaBidonesMensual_Fragment fragment_vbm= new VentaBidonesMensual_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_para_fragment_venta_bidones, fragment_vbm).commit();



    }/******************************* FIN DEL onCreate() *******************************/




    @Override
    public void onClick(View v) {


    }






    @Override
    public void onFragmentInteraction(Uri uri) {

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filtro_vb, menu);




        return true;



    } /**************FIN DE LA FUNCIÓN onCreateOptionsMenu()********/




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_venta_bidones_por_mes) {


            VentaBidonesMensual_Fragment fragment_vbm = new VentaBidonesMensual_Fragment();

            FragmentTransaction transition = getSupportFragmentManager().beginTransaction();

            transition.replace(R.id.contenedor_para_fragment_venta_bidones,fragment_vbm);

            transition.commit();




            return true;
        }







        if (id == R.id.action_venta_bidones_por_seis_meses) {



            VentaBidonesSemestral_Fragment fragment_vbs = new VentaBidonesSemestral_Fragment();

            FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();

            transition1.replace(R.id.contenedor_para_fragment_venta_bidones,fragment_vbs);

            transition1.commit();



            return true;
        }





        if (id == R.id.action_venta_bidones_por_anio) {





            VentaBidonesAnual_Fragment fragment_vba = new VentaBidonesAnual_Fragment();

            FragmentTransaction transition2 = getSupportFragmentManager().beginTransaction();

            transition2.replace(R.id.contenedor_para_fragment_venta_bidones,fragment_vba);

            transition2.commit();



            return true;
        }





        return super.onOptionsItemSelected(item);


    }/*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/





}/********* FIN DE LA ACTIVITY VentaBidonesEstadisticas *******/
