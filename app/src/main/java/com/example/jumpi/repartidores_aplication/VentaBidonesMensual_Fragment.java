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
 * {@link VentaBidonesMensual_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VentaBidonesMensual_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */







public class VentaBidonesMensual_Fragment extends Fragment {




    /************************** DECLARACIÓN DE VARIABLES GLOBALES **********************************/


    private BarChart barChart;

    private String[] Semanas = new String[]{"Semana 1","Semana 2","Semana 3","Semana 4"};

    private int[] Ventas_Bidones = new int[] {25,30,38,10};

    private int[] colors = new int[] {Color.BLACK,Color.RED,Color.GREEN,Color.BLUE};










    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;







    public VentaBidonesMensual_Fragment() {


        // Required empty public constructor

    }/******************* FIN DEL CONSTRUCTOR VentaBidonesMensual_Fragment() **********************/








    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VentaBidonesMensual_Fragment.
     */
    // TODO: Rename and change types and number of parameters





    public static VentaBidonesMensual_Fragment newInstance(String param1, String param2) {

        VentaBidonesMensual_Fragment fragment = new VentaBidonesMensual_Fragment();

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

        }


    }/************* FIN DEL onCreate() **********************/





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venta_bidones_mensual, container, false);




        barChart = (BarChart) view.findViewById(R.id.barChart);




        /*Llamada a la función: */
        createCharts();




        return view;



    }/******************* FIN DEL onCreateView() *******************/




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

        if (mListener != null) {

            mListener.onFragmentInteraction(uri);

        }

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


    }/***************** FIN DEL MÉTODO OnFragmentInteractionListener() ***********************/





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




    private void Leyenda(Chart chart){


        Legend legend = chart.getLegend();

        ArrayList<LegendEntry> entries = new ArrayList<>();

        LegendEntry entry = new LegendEntry();

        entry.formColor = Color.BLACK;

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



        for(int i = 0; i < Ventas_Bidones.length; i++){

            entries.add(new BarEntry(i,Ventas_Bidones[i]));


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

        axis.setValueFormatter(new IndexAxisValueFormatter(Semanas));

        axis.setTextSize(30);

        axis.setTextColor(Color.WHITE);

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

        axis.setTextColor(Color.WHITE);




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


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.BLACK,3000);

        barChart.setDrawGridBackground(true);

        barChart.setDrawBarShadow(true);




        /*Llamada a la función: */
        barChart.setData(getBarData());

        barChart.invalidate();


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

        barData.setBarWidth(0.45f);




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















}/****************************** FIN DE LA CLASE VentaBdionesMensual_Fragment *******************************/



