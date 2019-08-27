package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ventas_Activity extends AppCompatActivity {



    /** Declaración de Variables Globales**/




    /**Variables tipo Spinner**/

    Spinner spinner_ventas;




    /** Matríz Clásica tipo Cadena */



    String [] ListaDeArticulosEnVenta =  {"Bidones", "Dispenser Plástico", "Canillas", "Dispenser Eléctrico","Envases vacíos"};




    /** Variables tipo TextView*/

    TextView Nombre_Cliente_Ventas;

    TextView Apellido_Cliente_Ventas;

    TextView Direccion_Cliente_Ventas;

    TextView Barrio_Cliente_Ventas;




    /**Variables tipo EditText*/

    EditText eTCantVentas;

    EditText eTEntrega;




    /** Variables tipo ImageButton*/

    ImageButton btnAgregarNuevoArticulo;



    LinearLayout LinearLayoutVerticalVentas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ventas_por_cliente);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ventas);
        setSupportActionBar(toolbar);


        Apellido_Cliente_Ventas  = (TextView) findViewById(R.id.apellido_cliente_realizar_ventas);

        Nombre_Cliente_Ventas  = (TextView) findViewById(R.id.nombre_cliente_realizar_ventas);

        Direccion_Cliente_Ventas  = (TextView) findViewById(R.id.direccion_cliente_realizar_ventas);

        Barrio_Cliente_Ventas  = (TextView) findViewById(R.id.barrio_cliente_realizar_ventas);




        eTCantVentas = (EditText) findViewById(R.id.edtx_cantidad_productos_ventas);

        eTEntrega = (EditText) findViewById(R.id.cantidad_entrega_productos_ventas);




        spinner_ventas = (Spinner)findViewById(R.id.sp_art);

        /*Llamada a la función: */

        setSpinner(spinner_ventas, eTCantVentas);



        LinearLayoutVerticalVentas = (LinearLayout) findViewById(R.id.layout_vertical_ventas);



        btnAgregarNuevoArticulo = (ImageButton) findViewById(R.id.add_art);

        /**Método para añadir nuevos artículos pero que deberá cumplir ciertas condiciones para que se cumpla dicha acción**/
        btnAgregarNuevoArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ObtenerNuevoArticulo(0,""
                ,LinearLayoutVerticalVentas);


            } /*Fin del método OnClick*/

        }); /**Fin del método setOnClickListener**/



        /*Llamada a la función: */
        RecibirParametrosDeClientesDesdeRecyclerViewAdapter();




        final Button buttonConfirmarVentas = (Button) findViewById(R.id.button_confirmar_ventas);

        buttonConfirmarVentas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ValidacionAntesDeConfirmarVenta();

            }
        });





    } /**********************************FIN DEL OnCreate()***********************************/




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






    public void setSpinner(final Spinner spinner, final EditText EditTextCarga) {

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.spinner_ventas, ListaDeArticulosEnVenta);
        spinner.setAdapter(adaptador);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String text = spinner.getSelectedItem().toString();

                Toast to = Toast.makeText(getApplicationContext(), "Ha seleccionado " + text, Toast.LENGTH_LONG);
                to.show();


            }





            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }   /******************************FIN DE LA FUNCION setSpinner()*****************************/




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






    public void ValidacionAntesDeConfirmarVenta(){




        String AuxiliarCantVentas  = eTCantVentas.getText().toString();

        String AuxiliarEntrega = eTEntrega.getText().toString();


        if (AuxiliarCantVentas.isEmpty() && AuxiliarEntrega.isEmpty() ){

            Toast.makeText(getApplicationContext(),"Error! Los campos estan vacios. Por favor, complete al menos un campo",Toast.LENGTH_LONG).show();


        }

        else {


            Toast.makeText(getApplicationContext(),"Venta realizada con éxito",Toast.LENGTH_LONG).show();
            finish();

        }

    } /******************************FIN DE LA FUNCION ValidacionAntesDeConfirmarVenta()*****************************/






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




    public void RecibirParametrosDeClientesDesdeRecyclerViewAdapter(){



        String extras = getIntent().getStringExtra("Apellido");

        Apellido_Cliente_Ventas.setText(extras);





        extras = getIntent().getStringExtra("Nombre");

        Nombre_Cliente_Ventas.setText(extras);






        extras = getIntent().getStringExtra("Direccion");

        Direccion_Cliente_Ventas.setText(extras);




        extras = getIntent().getStringExtra("Barrio");

        Barrio_Cliente_Ventas.setText(extras);



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




    int ChildNuevoArticulo = 0;

    public void ObtenerNuevoArticulo(int ValorElementoSeleccionadoSpinnerProgramatico,
                                     String Valor_a_SetearDelEditTextCargaParaNuevoArticuloPrimerTanda,
                                     final LinearLayout linearLayout) {

        View NuevoArticuloInflado;

        /*Llamada a la función: */
        NuevoArticuloInflado = AgregarNuevoArticulo(linearLayout);


        /** Instanciamos las vistas del diseño XML: "nuevo_articulo_ventas.xml" **/

        final ImageButton btnEliminarArticulo = (ImageButton) NuevoArticuloInflado.findViewById(R.id.delete_art);

        btnEliminarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EliminarNuevoArticulo(v);


            }
        });



        final EditText EditText_Cantidad_Ventas_Nuevo_Articulo = (EditText) NuevoArticuloInflado.findViewById(R.id.edtx_cantidad_new_art);


        if(Valor_a_SetearDelEditTextCargaParaNuevoArticuloPrimerTanda != ""){


            EditText_Cantidad_Ventas_Nuevo_Articulo.setText(Valor_a_SetearDelEditTextCargaParaNuevoArticuloPrimerTanda);


        }





        final Spinner spinner_nuevos_articulos = (Spinner) NuevoArticuloInflado.findViewById(R.id.sp_new_art_ventas);

        setSpinner(spinner_nuevos_articulos, EditText_Cantidad_Ventas_Nuevo_Articulo);

        spinner_nuevos_articulos.setSelection(ValorElementoSeleccionadoSpinnerProgramatico);









    } /**********************************FIN DE LA FUNCIÓN ObtenerNuevoArticulo()*************************************/






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




    public View AgregarNuevoArticulo(final LinearLayout LayoutVerticalVentas) {


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflater.inflate(R.layout.nuevo_articulo_ventas, null, true);

        LayoutVerticalVentas.addView(InflatedView);

        ChildNuevoArticulo = LayoutVerticalVentas.getChildCount();


        return InflatedView;




    } /*****************************FIN DE LA FUNCION AgregarNuevoArticulo()************************************/





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




    public void EliminarNuevoArticulo(View btnEliminarNuevoArticulo) {


        final LinearLayout ChildLinearLayoutHorizontalNuevosArticulos = (LinearLayout) btnEliminarNuevoArticulo.getParent();

        final LinearLayout LLV_Padre = (LinearLayout) ChildLinearLayoutHorizontalNuevosArticulos.getParent();

        LLV_Padre.removeView(ChildLinearLayoutHorizontalNuevosArticulos);



    } /*************************************FIN DE LA FUNCIÓN EliminarNuevoArticulo()***********************************************/




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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_realizar_ventas, menu);
        return true;


    }/************************ FIN DEL onCreateOptionsMenu() *************************/






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings_ventas) {
            return true;
        }




        if (id == R.id.id_cancelar_venta) {


            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);



    }/************************ FIN DEL onOptionsItemSelected() *************************/






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






} /****************************** FIN DE LA Activity Ventas_Activity *******************************/
