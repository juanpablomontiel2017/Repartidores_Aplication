package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GastoCombustibleAnual_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */





public class GastoCombustibleAnual_Fragment extends Fragment {





    /************************** DECLARACIÓN DE VARIABLES GLOBALES **********************************/


    private BarChart barChart;

    private String[] Meses_Anio = new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
            "Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    private int[] Costo_Combustible = new int[] {25,30,38,10,15,5,9,20,40,35,27,32};

    private int[] colors = new int[] {Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA,
            Color.CYAN,Color.parseColor("#b388ff"),Color.parseColor("#00695c"),
            Color.parseColor("#ef6c00"), Color.parseColor("#7b1fa2"),
            Color.parseColor("#795548")};





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    public GastoCombustibleAnual_Fragment() {


        // Required empty public constructor



    }/******************* FIN DEL CONSTRUCTOR GastoCombustibleAnual_Fragment() **********************/






    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GastoCombustibleAnual_Fragment.
     */




    // TODO: Rename and change types and number of parameters

    public static GastoCombustibleAnual_Fragment newInstance(String param1, String param2) {

        GastoCombustibleAnual_Fragment fragment = new GastoCombustibleAnual_Fragment();

        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);

        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);

        return fragment;


    } /**************** FIN DE MÉTODO newInstance() ***************/






    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if (getArguments() != null) {


            mParam1 = getArguments().getString(ARG_PARAM1);

            mParam2 = getArguments().getString(ARG_PARAM2);


        }//Fin del if



    }/************* FIN DEL onCreate() **********************/






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gasto_combustible_anual, container, false);





        barChart = (BarChart) view.findViewById(R.id.barChart);






        /*Llamada a la función: */
        createCharts();




        return view;


    }/******************* FIN DEL onCreateView() *******************/









    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {


        if (mListener != null) {


            mListener.onFragmentInteraction(uri);


        }//Fin del if


    }/******************** FIN DEL MÉTODO onButtonPressed() ***********************/








    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {

            mListener = (OnFragmentInteractionListener) context;


        } else {


            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");


        }



    }/**************************** FIN DEL MÉTODO onAttach() *******************************/









    @Override
    public void onDetach() {

        super.onDetach();

        mListener = null;


    }/************************ FIN DEL MÉTODO onDetach()***********************/







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */







    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name

        void onFragmentInteraction(Uri uri);


    }/********* FIN DEL MÉTODO interface() ***********************/





    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/





    /********COMIENZO DE LAS FUNCIONES PARA AGREGAR EL GRÁFICO DE BARRAS ************/




    private Chart getSameChart(Chart chart, String descripcion, int textColor, int background, int animateY){


        chart.getDescription().setText(descripcion);

        chart.getDescription().setTextSize(15);

        chart.setBackgroundColor(background);

        chart.animateY(animateY);



        /*Llamada a la función: */
        Leyenda(chart);





        return chart;


    }/***************************FIN DE LA FUNCIÓN getSameChart()*************************************/






    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/




    /******** Leyenda en blanco  ********/

    private void Leyenda(Chart chart){




        Legend legend = chart.getLegend();

        ArrayList<LegendEntry> entries = new ArrayList<>();

        LegendEntry entry = new LegendEntry();

        entry.formColor = Color.WHITE;

        entries.add(entry);



        legend.setCustom(entries);



    }/***************************FIN DE LA FUNCIÓN Leyenda()*************************************/





    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/







    private ArrayList<BarEntry> getBarEntries(){


        ArrayList<BarEntry> entries = new ArrayList<>();



        for(int i = 0; i < Costo_Combustible.length; i++){

            entries.add(new BarEntry(i,Costo_Combustible[i]));


        }//Fin del for


        return entries;

    }/***************************FIN DE LA FUNCIÓN getBarEntries()*************************************/






    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/






    private void axisX(XAxis axis){

        axis.setGranularityEnabled(true);

        axis.setPosition(XAxis.XAxisPosition.BOTTOM);

        axis.setValueFormatter(new IndexAxisValueFormatter(Meses_Anio));


        axis.setTextSize(30);

        axis.setTextColor(Color.BLACK);

        axis.setTypeface(Typeface.DEFAULT_BOLD);



    }/***************************FIN DE LA FUNCIÓN axisX()*************************************/





    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/







    private void axisLeft(YAxis axis){

        axis.setSpaceTop(30);

        axis.setAxisMinimum(0);

        axis.setTextSize(20);

    }/***************************FIN DE LA FUNCIÓN axisLeft()*************************************/







    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/





    private void axisRight(YAxis axis){

        axis.setEnabled(false);

    }/***************************FIN DE LA FUNCIÓN axisRight()*************************************/






    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/






    public void createCharts(){


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.WHITE,3000);

        barChart.setDrawGridBackground(true);

        barChart.setDrawBarShadow(true);

        /*Llamada a la función: */
        barChart.setData(getBarData());

        barChart.invalidate();

        barChart.zoom(2.5f,2.5f,2.5f,2.5f);




        /*Llamadas a las siguientes funciones: */

        axisX(barChart.getXAxis());

        axisLeft(barChart.getAxisLeft());

        axisRight(barChart.getAxisRight());















    }/***************************FIN DE LA FUNCIÓN createCharts()*************************************/







    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/



    private DataSet getData(DataSet dataSet){


        dataSet.setColors(colors);

        dataSet.setValueTextSize(Color.WHITE);

        dataSet.setValueTextSize(20);


        return dataSet;

    }/***************************FIN DE LA FUNCIÓN getData()*************************************/







    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/









    private BarData getBarData(){


        BarDataSet barDataSet = (BarDataSet) getData(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.GRAY);

        BarData barData = new BarData(barDataSet);

        barData.setBarWidth(0.75f);




        return barData;





    }/***************************FIN DE LA FUNCIÓN getBarData()*************************************/






    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/
    /****************************************************************************/











}/****************************** FIN DE LA CLASE GastoCombustibleAnual_Fragment *******************************/
