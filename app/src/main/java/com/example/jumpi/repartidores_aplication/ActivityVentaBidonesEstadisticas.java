package com.example.jumpi.repartidores_aplication;

import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class ActivityVentaBidonesEstadisticas extends AppCompatActivity implements View.OnClickListener, FragmentVentaBidonesMensual.OnFragmentInteractionListener, FragmentVentaBidonesSemestral.OnFragmentInteractionListener, FragmentVentaBidonesAnual.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_bidones_estadisticas);




        /*Inicialización del Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        FragmentVentaBidonesMensual fragment_vbm= new FragmentVentaBidonesMensual();
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
        getMenuInflater().inflate(R.menu.menu_filtro_venta_bidones, menu);




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


            FragmentVentaBidonesMensual fragment_vbm = new FragmentVentaBidonesMensual();

            FragmentTransaction transition = getSupportFragmentManager().beginTransaction();

            transition.replace(R.id.contenedor_para_fragment_venta_bidones,fragment_vbm);

            transition.commit();




            return true;
        }







        if (id == R.id.action_venta_bidones_por_seis_meses) {



            FragmentVentaBidonesSemestral fragment_vbs = new FragmentVentaBidonesSemestral();

            FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();

            transition1.replace(R.id.contenedor_para_fragment_venta_bidones,fragment_vbs);

            transition1.commit();



            return true;
        }





        if (id == R.id.action_venta_bidones_por_anio) {





            FragmentVentaBidonesAnual fragment_vba = new FragmentVentaBidonesAnual();

            FragmentTransaction transition2 = getSupportFragmentManager().beginTransaction();

            transition2.replace(R.id.contenedor_para_fragment_venta_bidones,fragment_vba);

            transition2.commit();



            return true;
        }





        return super.onOptionsItemSelected(item);


    }/*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/





}/********* FIN DE LA ACTIVITY VentaBidonesEstadisticas *******/
