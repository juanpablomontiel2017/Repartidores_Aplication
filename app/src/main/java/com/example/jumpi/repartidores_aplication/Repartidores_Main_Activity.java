package com.example.jumpi.repartidores_aplication;

import android.content.Intent;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class Repartidores_Main_Activity extends AppCompatActivity {






    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPagerAdapter adapter;

    private CustomViewPager mViewPager;







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



/*

        // Obtienes el Bundle del Intent
        Bundle bundle = getIntent().getExtras();

        // Obtienes el texto
        final String dia = bundle.getString("dia");

        // Creamos un nuevo Bundle
        Bundle args = new Bundle();

        // Colocamos el String
        args.putString("dia", dia);






        ClientesFragment clientesFragment = new ClientesFragment();

        clientesFragment.setArguments(args);

*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (CustomViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);


        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fragment Here
        adapter.AddFragment (new StockEnVehiculoFragment(),"Stock en Vehículo");

        //adapter.AddFragment(new MapaRepartidoresFragment(),"Mapa");


   //     adapter.AddFragment (clientesFragment,"Clientes");


        mViewPager.setAdapter(adapter);


        /*Llamada a la función: */
        mViewPager.setPagingEnabled(false);




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);



        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));




        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(false);




        FloatingActionButton fab_mapa_repartidores = (FloatingActionButton) findViewById(R.id.fab_ir_mapa_repartidores);

        fab_mapa_repartidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), Mapa_Repartidores.class);

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


        getMenuInflater().inflate(R.menu.menu_second_, menu);


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









}/*************************** FIN DE LA Activity Repartidores_Main_Activity ********************************/
