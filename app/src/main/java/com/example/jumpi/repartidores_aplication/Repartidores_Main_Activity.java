package com.example.jumpi.repartidores_aplication;

import android.content.Intent;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Repartidores_Main_Activity extends AppCompatActivity {






    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPagerAdapter adapter;

    private CustomViewPager mViewPager;

    Button button_ir_mapa_repartidores;





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
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_repartidores);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (CustomViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);


        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fragment Here
        adapter.AddFragment (new StockEnVehiculoFragment(),"Stock en Vehículo");


        mViewPager.setAdapter(adapter);


        /*Llamada a la función: */
        mViewPager.setPagingEnabled(false);




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);



        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));




        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(false);



        button_ir_mapa_repartidores = (Button) findViewById(R.id.btn_ir_mapa_repartidores);

        button_ir_mapa_repartidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Intent intent = new Intent(getApplicationContext(), Mapa_Repartidores.class);

                //Intent intent = new Intent(getApplicationContext(), NavigationMapRouteActivity.class);

                Intent intent = new Intent(getApplicationContext(), MapaGoogleRepartidoresActivity.class);

                startActivity(intent);


            }
        });






    }/*******************************FIN DEL OnCreate*********************************/





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


            Intent intent = new Intent (Repartidores_Main_Activity.this, BuscarResponsableParaPatrocinio.class);

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





    public static class PlaceholderFragment extends Fragment {


        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {


        }/******FIN DEL CONSTRUCTOR PlaceholderFragment() ***/






        public static PlaceholderFragment newInstance(int sectionNumber) {


            PlaceholderFragment fragment = new PlaceholderFragment();


            Bundle args = new Bundle();


            args.putInt(ARG_SECTION_NUMBER, sectionNumber);


            fragment.setArguments(args);


            return fragment;


        }





        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_repartidores_main_activity, container, false);


            TextView textView = (TextView) rootView.findViewById(R.id.section_label);


            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


            return rootView;


        }/**************** FIN DEL onCreateView()***************/


    }/**************************** FIN DE LA CLASE FRAGMENT PlaceholderFragment() ***************************/



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




    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {


            switch (position) {


                case 0:


                    StockEnVehiculoFragment stock_en_vehiculo_fragment = new StockEnVehiculoFragment();


                    return stock_en_vehiculo_fragment;


            }


            return null;


        }/**************** FIN DE LA FUNCIÓN getItem() *******/






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
        public int getCount() {


            // Show 1 total pages.
            return 1;


        }





    }/***************************** FIN DE LA CLASE SectionsPagerAdapter() ******************************/












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










    public CharSequence getPageTitle (int position){


         switch (position) {


             case 0:


                 return "Resumen de Cargas";


             case 1:


                 return "Mapa";


         }


         return null;


    }/******************* FIN DE LA CLASE getPageTitle() *********************/




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


/*

    public List<List<HashMap <String,String>>> parse(JSONObject jObject){

        //Este método PARSEA el JSONObject que retorna del API de Rutas de Google devolviendo
        //una lista del lista de HashMap Strings con el listado de Coordenadas de Lat y Long,
        //con la cual se podrá dibujar pollinas que describan la ruta entre 2 puntos.


        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;


        try {

            jRoutes = jObject.getJSONArray("routes");

            for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();

                for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                    for(int k=0;k<jSteps.length();k++){
                        String polyline = "";
                        polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list = decodePoly(polyline);

                        for(int l=0;l<list.size();l++){
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                            hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                            path.add(hm);
                        }
                    }
                    Utilidades.routes.add(path);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
        }
        return Utilidades.routes;
    }






    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }


*/




}/*************************** FIN DE LA Activity Repartidores_Main_Activity ********************************/
