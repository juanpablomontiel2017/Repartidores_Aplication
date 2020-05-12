package com.example.jumpi.repartidores_aplication;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class CombustibleEstadisticas extends AppCompatActivity implements View.OnClickListener, GastoCombustiblePorMes_Fragment.OnFragmentInteractionListener, GastoCombustibleSemestral_Fragment.OnFragmentInteractionListener, GastoCombustibleAnual_Fragment.OnFragmentInteractionListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combustible_estadisticas);



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








        GastoCombustiblePorMes_Fragment fragment_gcm = new GastoCombustiblePorMes_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_para_fragment, fragment_gcm).commit();




    }



    @Override
    public void onClick(View v) {


    }





    @Override
    public void onFragmentInteraction(Uri uri) {

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filtro_gc, menu);




        return true;



    } /**************FIN DE LA FUNCIÓN onCreateOptionsMenu()********/




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_gasto_combustible_por_mes) {


            GastoCombustiblePorMes_Fragment fragment_gcm = new GastoCombustiblePorMes_Fragment();

            FragmentTransaction transition = getSupportFragmentManager().beginTransaction();

            transition.replace(R.id.contenedor_para_fragment,fragment_gcm);

            transition.commit();




            return true;
        }







        if (id == R.id.action_gasto_combustible_por_seis_meses) {



            GastoCombustibleSemestral_Fragment fragment_gcs = new GastoCombustibleSemestral_Fragment();

            FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();

            transition1.replace(R.id.contenedor_para_fragment,fragment_gcs);

            transition1.commit();



            return true;
        }





        if (id == R.id.action_gasto_combustible_por_anio) {





            GastoCombustibleAnual_Fragment fragment_gca = new GastoCombustibleAnual_Fragment();

            FragmentTransaction transition2 = getSupportFragmentManager().beginTransaction();

            transition2.replace(R.id.contenedor_para_fragment,fragment_gca);

            transition2.commit();



            return true;
        }





        return super.onOptionsItemSelected(item);


    }/*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/






}/****************************** FIN DE LA Activity CombustibleEstadisticas ***************************/
