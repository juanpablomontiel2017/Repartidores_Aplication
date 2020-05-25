package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class Utils_Spinner {





    /******Contadores********/


    public static int contador_spinner = 1;

    public static int contador_de_inicializacion = 0;











    public Utils_Spinner(Context context) {



    }




    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/





    public static int ObtenerPosicionDelElementoEnElSpinner(String Nombre_Articulo, Spinner spinner){

        int Posicion_a_Devolver = 99;

        if(Nombre_Articulo != "") {


            ArrayList<String> ListaDeArticulos = ObtenerTodosLosElementosDelSpinner(spinner);


            for (int i = 0; i < ListaDeArticulos.size(); i++) {


                if (ListaDeArticulos.get(i).equals(Nombre_Articulo)) {


                    Posicion_a_Devolver = i;


                }

            } //Fin del for


        } //Fin del primer if

        else {

            Posicion_a_Devolver = 0;

        } //Fin del else


        return Posicion_a_Devolver;

    }/*************FIN DE LA FUNCIÓN ObtenerPosicionDelElementoEnElSpinner()**************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    public static ArrayList<String> ObtenerTodosLosElementosDelSpinner(Spinner theSpinner) {

        Adapter adapter = theSpinner.getAdapter();

        int n = adapter.getCount();

        ArrayList <String> ListaDelSpinner = new ArrayList <String> (n);

        for (int i = 0; i < n; i++) {

            String Articulos = (String) adapter.getItem(i);

            ListaDelSpinner.add(Articulos);

        }

        return ListaDelSpinner;

    }/******************************FIN DE LA FUNCIÓN ObtenerTodosLosElementosDelSpinner()*****************************/






    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/





        public static boolean ExisteElElemento(ArrayList <String> ArticulosReducidos, String Articulos){

            boolean bandera = false;

            for (int i = 0; i < ArticulosReducidos.size(); i++) {


                if(ArticulosReducidos.get(i).equals(Articulos)) {

                    bandera = true;

                }


            } //Fin del for



            return bandera;

        }/******************************FIN DE LA FUNCIÓN ExisteElElemento()*****************************/













    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/






    public static void AgregarArticulosAlArrayList(ArrayList<String> ArticulosReducidos, String Articulos) {


        if(ExisteElElemento(ArticulosReducidos,Articulos)){



        } else {

            ArticulosReducidos.add(Articulos);

        }




    }/******************************FIN DE LA FUNCIÓN AgregarArticulosAlArrayList()*****************************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/





    public static ArrayList<String>  QuitarArticulosAlSpinner(Spinner spinner, String Articulos) {


        ArrayList<String> ArticulosReducidos = ObtenerTodosLosElementosDelSpinner(spinner);

        if(ExisteElElemento(ArticulosReducidos,Articulos)){


            ArticulosReducidos.remove(Articulos);

        }


        return ArticulosReducidos;

    }/******************************FIN DE LA FUNCIÓN QuitarArticulosAlSpinner()*****************************/








    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/










    public static ArrayList<String> QuitarArticulosAlArrayList(ArrayList<String> ArticulosReducidos, String Articulos) {


        if(ExisteElElemento(ArticulosReducidos,Articulos)){

            ArticulosReducidos.remove(Articulos);

        }


        return ArticulosReducidos;

    }/******************************FIN DE LA FUNCIÓN QuitarArticulosAlArrayList()*****************************/








    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    /** Articulo_a_Agregar: El articulo a agregar a los otros Spinner
     *  Articulo_a_Quitar: Quita el elemento de los otros Spinner*/

    public static void RefrescarOtrosSpinner(Spinner spinner, String Articulo_a_Agregar, String Articulo_a_Quitar, Context context){


        LinearLayout linearLayoutHorizontalArticulo = (LinearLayout) spinner.getParent();

        LinearLayout linearLayoutVerticalContenedorArticulos = (LinearLayout) linearLayoutHorizontalArticulo.getParent();



        for (int itemPos = 0; itemPos < linearLayoutVerticalContenedorArticulos.getChildCount(); itemPos++) {


            LinearLayout view = (LinearLayout) linearLayoutVerticalContenedorArticulos.getChildAt(itemPos);





            if (view != linearLayoutHorizontalArticulo) {






                for (int itemLinearHorizontal = 0; itemLinearHorizontal < view.getChildCount(); itemLinearHorizontal++) {


                    View widgetLinearHorizontal = view.getChildAt(itemLinearHorizontal);






                    if (widgetLinearHorizontal instanceof Spinner ) {



                        Spinner spinner_a_refrescar = (Spinner) widgetLinearHorizontal;


                        String Articulo_Seleccionado = spinner_a_refrescar.getSelectedItem().toString();


                        ArrayList<String> ArticulosReducidos = ObtenerTodosLosElementosDelSpinner(spinner_a_refrescar);







                        if(Articulo_a_Quitar != null){


                            /*Llamada a la función: */
                            ArticulosReducidos = QuitarArticulosAlSpinner(spinner_a_refrescar, Articulo_a_Quitar);


                        } //Fin del if











                        if(Articulo_a_Agregar != null){

                            /*Llamada a la función: */
                            AgregarArticulosAlArrayList(ArticulosReducidos,Articulo_a_Agregar);


                        } //Fin del if





                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, R.layout.spinner, ArticulosReducidos);


                        spinner_a_refrescar.setAdapter(adaptador);


                        spinner_a_refrescar.setSelection(ObtenerPosicionDelElementoEnElSpinner(Articulo_Seleccionado,spinner_a_refrescar));


                        contador_de_inicializacion-- ;


                    } //Fin del if (widgetLinearHorizontal instanceof Spinner )


                } //Fin del for



            }//Fin del if (view != linearLayoutHorizontalArticulo)


        } //Fin del primer for




    }/******************************FIN DE LA FUNCION RefrescarOtrosSpinner()*****************************/






    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/





    /**** Misma funcionalidad pero teniendo en cuenta que el PADRE del spinner es un LinearLayoutHorizontal con bordes ***/

    public static void RefrescarOtrosSpinnerConBordes(Spinner spinner, String Articulo_a_Agregar, String Articulo_a_Quitar, Context context){

        LinearLayout linearLayoutHorizontalContenedorSpinner = (LinearLayout) spinner.getParent();

        LinearLayout linearLayoutHorizontalContenedorArticulos = (LinearLayout) linearLayoutHorizontalContenedorSpinner.getParent();

        LinearLayout linearLayoutVerticalTercerTupla = (LinearLayout) linearLayoutHorizontalContenedorArticulos.getParent();




        for (int itemPos = 0; itemPos < linearLayoutVerticalTercerTupla.getChildCount(); itemPos++) {


            LinearLayout LinearLayoutHorizontalDeArticulo = (LinearLayout) linearLayoutVerticalTercerTupla.getChildAt(itemPos);


            if (LinearLayoutHorizontalDeArticulo != linearLayoutHorizontalContenedorArticulos) {


                for (int contador_item = 0; contador_item < LinearLayoutHorizontalDeArticulo.getChildCount(); contador_item++) {


                    View item = LinearLayoutHorizontalDeArticulo.getChildAt(contador_item);

                    if (item instanceof LinearLayout) {


                        Spinner spinner_a_refrescar = (Spinner) ((LinearLayout) item).getChildAt(0);

                        String Articulo_Seleccionado = spinner_a_refrescar.getSelectedItem().toString();

                        ArrayList<String> ArticulosReducidos = ObtenerTodosLosElementosDelSpinner(spinner_a_refrescar);






                        if(Articulo_a_Quitar != null){


                            /*Llamada a la función: */
                            ArticulosReducidos = QuitarArticulosAlSpinner(spinner_a_refrescar, Articulo_a_Quitar);


                        } //Fin del if











                        if(Articulo_a_Agregar != null){

                            /*Llamada a la función: */
                            AgregarArticulosAlArrayList(ArticulosReducidos,Articulo_a_Agregar);


                        } //Fin del if





                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, R.layout.spinner, ArticulosReducidos);


                        spinner_a_refrescar.setAdapter(adaptador);


                        spinner_a_refrescar.setSelection(ObtenerPosicionDelElementoEnElSpinner(Articulo_Seleccionado,spinner_a_refrescar));


                        contador_de_inicializacion-- ;







                    }//Fin del if

                }//Fin del for contador_item


            }//Fin del if "LinearLayoutHorizontalDeArticulo


        }//Fin del for item_pos






}/******************************FIN DE LA FUNCION RefrescarOtrosSpinnerConBordes()*****************************/



    /**** Obtengo el valor escrito en el editText ***/

    public static Integer obtenerCantidadDeArticulo(Spinner spinner){

        LinearLayout linearLayoutHorizontalContenedorSpinner = (LinearLayout) spinner.getParent();

        LinearLayout linearLayoutHorizontalContenedorArticulos = (LinearLayout) linearLayoutHorizontalContenedorSpinner.getParent();


        String cantidadDeArticulos = "";


        for (int itemPos = 0; itemPos < linearLayoutHorizontalContenedorArticulos.getChildCount(); itemPos++) {



            View item = linearLayoutHorizontalContenedorArticulos.getChildAt(itemPos);

            if (item instanceof EditText) {


                EditText etCantidadArticulo = (EditText) item;

                cantidadDeArticulos = etCantidadArticulo.getText().toString();

            }



        }//Fin del for item_pos



        return Integer.parseInt(cantidadDeArticulos);


    }/******************************FIN DE LA FUNCION obtenerCantidadDeArticulo()*****************************/











    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    public static ArrayList<String> ReducirListaDeArticulos(Spinner spinner, ArrayList<String> ArticulosReducidos ){


        LinearLayout linearLayoutHorizontalArticulo = (LinearLayout) spinner.getParent();

        LinearLayout linearLayoutVerticalContenedorArticulos = (LinearLayout) linearLayoutHorizontalArticulo.getParent();


        for (int itemPos = 0; itemPos < linearLayoutVerticalContenedorArticulos.getChildCount(); itemPos++) {


            LinearLayout view = (LinearLayout) linearLayoutVerticalContenedorArticulos.getChildAt(itemPos);


            if (view != linearLayoutHorizontalArticulo) {


                for (int itemLinearHorizontal = 0; itemLinearHorizontal < view.getChildCount(); itemLinearHorizontal++) {


                    View widgetLinearHorizontal = view.getChildAt(itemLinearHorizontal);


                    if (widgetLinearHorizontal instanceof Spinner) {


                        Spinner spinnerAnterior = (Spinner) widgetLinearHorizontal;

                        String ValorSpinnerAnterior = spinnerAnterior.getSelectedItem().toString();



                        /*Llamada a la función: */
                        ArticulosReducidos = QuitarArticulosAlArrayList(ArticulosReducidos,ValorSpinnerAnterior);



                    }//Fin del if(widgetLinearHorizontal instanceof Spinner)


                } //Fin del for



            } //Fin del if(view != linearLayoutHorizontalArticulo)


        } //Fin del primer for


        return ArticulosReducidos;


    } /******************************FIN DE LA FUNCION ReducirListaDeArticulos()*****************************/





    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/






    public static ArrayList<String> ReducirListaDeArticulosDeSpinnerConBordes(Spinner spinner, ArrayList<String> ArticulosReducidos ){


        LinearLayout linearLayoutHorizontalContenedorSpinner = (LinearLayout) spinner.getParent();

        LinearLayout linearLayoutHorizontalContenedorArticulos = (LinearLayout) linearLayoutHorizontalContenedorSpinner.getParent();

        LinearLayout linearLayoutVerticalTercerTupla = (LinearLayout) linearLayoutHorizontalContenedorArticulos.getParent();





        for (int itemPos = 0; itemPos < linearLayoutVerticalTercerTupla.getChildCount(); itemPos++) {


            LinearLayout LinearLayoutHorizontalDeArticulo = (LinearLayout) linearLayoutVerticalTercerTupla.getChildAt(itemPos);


            if (LinearLayoutHorizontalDeArticulo != linearLayoutHorizontalContenedorArticulos) {


                for (int contador_item = 0; contador_item < LinearLayoutHorizontalDeArticulo.getChildCount(); contador_item++) {


                    View item = LinearLayoutHorizontalDeArticulo.getChildAt(contador_item);

                    if (item instanceof LinearLayout) {


                        Spinner spinnerAnterior = (Spinner) ((LinearLayout) item).getChildAt(0);

                        String ValorSpinnerAnterior = spinnerAnterior.getSelectedItem().toString();

                        /*Llamada a la función: */
                        ArticulosReducidos = QuitarArticulosAlArrayList(ArticulosReducidos, ValorSpinnerAnterior);


                    }//Fin del if

                }//Fin del  for (contador_item)


            } //Fin del primer if (LinearLayoutHorizontalDeArticulo)


        }//Fin del for (item_pos)

        return ArticulosReducidos;


    } /******************************FIN DE LA FUNCION ReducirListaDeArticulosDeSpinnerConBordes()*****************************/











    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/






    /**Función que devuelve el ImageButton "+" para después deshabilitarlo cuando se llega a un tope de "x"
     * cantidad de articulos*/

    public static ImageButton ObtenerBotonParaAgregarNuevosArticulos(View v){

        LinearLayout linearLayoutHorizontalArticulo = (LinearLayout) v.getParent();

        LinearLayout linearLayoutVerticalContenedorArticulos = (LinearLayout) linearLayoutHorizontalArticulo.getParent();

        LinearLayout view = (LinearLayout) linearLayoutVerticalContenedorArticulos.getChildAt(0);

        ImageButton botonMas = null;

        for (int itemLinearHorizontal = 0; itemLinearHorizontal < view.getChildCount(); itemLinearHorizontal++) {


            View widgetLinearHorizontal = view.getChildAt(itemLinearHorizontal);


            if (widgetLinearHorizontal instanceof ImageButton) {


                botonMas = (ImageButton) widgetLinearHorizontal;

                return botonMas;

            } //Fin del if


        } //Fin del for



        return botonMas;


    } /******************************FIN DE LA FUNCION ObtenerBotonParaAgregarNuevosArticulos()*****************************/




    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/




    public static ImageButton ObtenerBotonParaAgregarNuevosArticulos_SpinnerConBordes(View v){



        LinearLayout linearLayoutHorizontalContenedorSpinner = (LinearLayout) v.getParent();

        LinearLayout linearLayoutHorizontalContenedorArticulos = (LinearLayout) linearLayoutHorizontalContenedorSpinner.getParent();

        LinearLayout linearLayoutVerticalTercerTupla = (LinearLayout) linearLayoutHorizontalContenedorArticulos.getParent();

        LinearLayout view = (LinearLayout) linearLayoutVerticalTercerTupla.getChildAt(0);




        ImageButton botonMas = null;



        for (int itemLinearHorizontal = 0; itemLinearHorizontal < view.getChildCount(); itemLinearHorizontal++) {

            View widgetLinearHorizontal = view.getChildAt(itemLinearHorizontal);


            if (widgetLinearHorizontal instanceof ImageButton) {


                botonMas = (ImageButton) widgetLinearHorizontal;

                return botonMas;

            } //Fin del if

        }//Fin del for (itemLinearHorizontal)



        return botonMas;


    } /******************************FIN DE LA FUNCION ObtenerBotonParaAgregarNuevosArticulos_SpinnerConBordes()*****************************/








    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/










}/****************************FIN DE LA CLASE Utils_Spinner**********************/
