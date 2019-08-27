package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


public class Repartidores_Main_Activity extends AppCompatActivity {






    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPagerAdapter adapter;

    private ViewPager mViewPager;






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

        setContentView(R.layout.repartidores_activity_main);





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




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);


        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fragment Here
        adapter.AddFragment (new StockDisponibleFragment(),"Registro de ventas");


        adapter.AddFragment (clientesFragment,"Clientes");


        mViewPager.setAdapter(adapter);




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));




        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent fab = new Intent(Repartidores_Main_Activity.this, AgregarCliente.class);

                startActivity(fab);


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
        if (id == R.id.action_settings_second) {


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


                    StockDisponibleFragment stock_disponible_fragment = new StockDisponibleFragment();


                    return stock_disponible_fragment;


                case 1:


                    ClientesFragment clientesFragment = new ClientesFragment();


                    return clientesFragment;


            }


            return null;


        }/****************   FIN DE LA FUNCIÓN getItem() *******/






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


            // Show 3 total pages.
            return 3;


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


                 return "Clientes";


             case 2:


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
