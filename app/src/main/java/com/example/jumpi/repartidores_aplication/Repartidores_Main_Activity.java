package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;


public class Repartidores_Main_Activity extends AppCompatActivity {






    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPagerAdapter adapter;

    private CustomViewPager mViewPager;

    Button button_ir_mapa_repartidores;

    String ActivityProvenienteDe;



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








        ActivityProvenienteDe = getIntent().getStringExtra("Activity");


        button_ir_mapa_repartidores = (Button) findViewById(R.id.btn_ir_mapa_repartidores);

        button_ir_mapa_repartidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               Intent intent = new Intent(getApplicationContext(), Mapa_Repartidores.class);

               intent.putExtra("Activity","Repartidores_Main_Activity");

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


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {


            finishAffinity();


        }

        return super.onKeyDown(keyCode, event);
    }





}/*************************** FIN DE LA Activity Repartidores_Main_Activity ********************************/
