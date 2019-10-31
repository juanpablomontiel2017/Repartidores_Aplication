package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;

public class BuscarResponsableParaPatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener{



    public static Button btnResponsableActivo;


    LinearLayout LinearLayoutVerticalPadre;


    ArrayList<View> ArrayListItemEvento = new ArrayList<View>();

    private ListView lv;

    ArrayAdapter<String> adapter;

    // Arreglo de nombres de responsables
    String tokens[] = {"Almagro José","Gonzalez Alfredo","Ricardo Martinez","Andrada Esteban","Mendez Luis","Aldino Sebastián"};



    /************************* COMIENZO DEL onCreate() ******************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_responsable_para_patrocinio);











        /**Añadir "manualmente" color al StatusBar **/

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(Color.parseColor("#b71c1c"));


        /* Para cambiar el color del puntero o "burbuja" del EditText */
        setTheme(R.style.AppTheme_Cursor);





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.parent_layout_vertical_buscador_evento);





        FloatingActionButton fab = findViewById(R.id.fab_nuevo_responsable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this);
                builder.setIcon(R.drawable.ic_notificacion_nuevo_responsable);
                builder.setTitle("¿Desea añadir un nuevo responsable a la lista?");
                builder.setMessage("Presione el botón 'CONTINUAR' para completar el formulario con los datos requeridos del responsable del evento.");


                builder.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoResponsablePatrocinio.class);

                        startActivity(intent);



                    }
                });





                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();




            }/*********** FIN DEL EVENTO onClick() ****************/



        });/*********** FIN DEL EVENTO setOnClickListener() ****************/







        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            // finally change the color
            window.setStatusBarColor(Color.parseColor("#303F9F"));


            toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
            setSupportActionBar(toolbar);


            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#03a9f4")));

        }




        /*Llamada a la función: */
        CargaItemDeEvento();






        lv = (ListView) findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(this,R.layout.list_nombres_responsables_item, R.id.codigo_token,tokens);

        lv.setAdapter(adapter);

        lv.setTextFilterEnabled(true);






    }/******************************* FIN DEL onCreate() ***********************************/



    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar_responsable_o_evento_por_patrocinio, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        searchView.setIconified(false);


        return true;

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.editar_item_evento) {



            AlertDialog.Builder builder = new AlertDialog.Builder(BuscarResponsableParaPatrocinio.this);
            builder.setIcon(R.drawable.ic_msj_alerta);
            builder.setTitle("¿Desea modificar algunas de las tandas realizadas?");
            builder.setMessage("Presione 'SI' en caso que desee editar los campos de algunas de las tandas.");


            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {




                        /*Llamada a la función:  */
                        EditarDatosItemEvento(true);




                }
            });








            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                    dialog.dismiss();

                }
            });



            AlertDialog dialog = builder.create();
            dialog.show();


            return true;



        }//FIN DEL if(id == R.id.editar_item_evento


        return super.onOptionsItemSelected(item);


    } /***** FIN DEL onOptionsItemSelected()******/


    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/





    @Override
    public boolean onQueryTextSubmit(String query) {

        // User pressed the search button


        return false;

    }







    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/





    @Override
    public boolean onQueryTextChange(String newText) {

        // User changed the text

        if(newText.equals("") ) {

            lv.setVisibility(View.GONE);


        } else {

            BuscarResponsableParaPatrocinio.this.adapter.getFilter().filter(newText.toString());
            lv.setVisibility(View.VISIBLE);



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, NuevoEventoPatrocinio.class);

                    intent.putExtra("DNI_Responsable_Enviar", "38.765.245");

                    intent.putExtra("Nombre_Apellido_Responsable_Enviar", "Juan Pablo Montiel");

                    intent.putExtra("Telefono_Responsable_Enviar", "3734-446587");

                    intent.putExtra("Direccion_Responsable_Enviar", "Calle 6 entre 11 y 13");

                    intent.putExtra("Barrio_Responsable_Enviar", "Centro");

                    intent.putExtra("Referencia_Responsable_Enviar", "Al lado de una funeraria");

                    intent.putExtra("Correo_Responsable_Enviar", "juanpablomontiel2015@gmail.com");





                    startActivity(intent);
                }
            });



       }



        return false;

    }






    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/




    public void CargaItemDeEvento(){

        int dimension = 0;

        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);

        String DimensionEvento = preferences.getString("DimensionDeEvento", "");

        if(DimensionEvento != ""){

            dimension = Integer.parseInt(DimensionEvento);

            for(int i=0; i <= dimension; i++){


                ObtenerItemEvento(i);


            }//Fin del for



        }//Fin del if




    }/******************************* FIN DE LA FUNCIÓN CargaItemDeEvento() ***********************************/





    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/



    int ChildNuevoItemEvento = 0;

    public  void ObtenerItemEvento(int indice){


        View NuevoItemInflado;

        /*Llamada a la función: */
        NuevoItemInflado = AgregarItemEvento();

        ArrayListItemEvento.add(NuevoItemInflado);





        final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);


        final LinearLayout LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);


        final LinearLayout LinearLayoutVerticalContenedorHijo = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


        final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);


        final EditText ET_Nombre_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_apellido_responsable_recibir);


        final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


        final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


        final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);


        final LinearLayout LinearLayoutHorizontalFechaFin = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


        final TextView TV_Fecha_Fin_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_fin_recibir);


        final EditText ET_Valor_Fecha_Fin_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_fin_recibir);





        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);



        String Nombre_Evento = preferences.getString("Nombre_Evento" + indice, "");
        String Nombre_Apellido_Responsable = preferences.getString("Nombre_Apellido_Responsable" + indice, "");
        String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento" + indice, "");
        String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento" + indice, "");




        ET_Nombre_Evento.setText(Nombre_Evento);
        ET_Nombre_Apellido_Responsable_Evento.setText(Nombre_Apellido_Responsable);
        ET_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
        ET_Valor_Fecha_Fin_Evento.setText(Fecha_Fin_Evento);



        final Button btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);
        btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

                intent.putExtra("Nombre_del_evento", ET_Nombre_Evento.getText().toString());

                intent.putExtra("Nombre_del_responsable", ET_Nombre_Apellido_Responsable_Evento.getText().toString());


                startActivity(intent);

            }
        });




        /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
         * de Patrocinio**/

        Usuario usuario = new Usuario();

        usuario.LeerUsuarioEnUnSharedPreferences(this);

        if(usuario.getTipo_de_Usuario().equals("repartidor")){

            LinearLayoutVerticalPadre.setBackgroundColor(Color.parseColor("#2962ff"));

            btn_ver_detalle_entrega_retiro_envases_patrocinio.getBackground().setColorFilter(Color.parseColor("#0d47a1"), PorterDuff.Mode.SRC_ATOP);

        }





    }/***************************FIN DE LA FUNCIÓN ObtenerItemEvento()*****************************/





    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/




    @SuppressLint("RestrictedApi")
    public View AgregarItemEvento(){


        LayoutInflater inflateItemEvento = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View InflatedView = inflateItemEvento.inflate(R.layout.item_evento, null, true);

        LinearLayoutVerticalPadre.addView(InflatedView,0);

        ChildNuevoItemEvento = LinearLayoutVerticalPadre.getChildCount();


        return InflatedView;


    } /***************************FIN DE LA FUNCIÓN AgregarItemEvento()*****************************/





    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/




    public void EditarDatosItemEvento(boolean flag){


        if(flag){

            for(int k=0; k < ArrayListItemEvento.size(); k++) {


                final EditText ET_Nombre_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_nombre_evento_recibir);

                final EditText ET_Nombre_Apellido_Responsable_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_nombre_apellido_responsable_recibir);

                final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_valor_fecha_inicio_recibir);

                final EditText ET_Valor_Fecha_Fin_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_valor_fecha_fin_recibir);


                ET_Nombre_Evento.setFocusableInTouchMode(true);
                ET_Nombre_Evento.requestFocus();
                ET_Nombre_Evento.setCursorVisible(true);
                ET_Nombre_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                ET_Nombre_Apellido_Responsable_Evento.setFocusableInTouchMode(true);
                ET_Nombre_Apellido_Responsable_Evento.setCursorVisible(true);
                ET_Nombre_Apellido_Responsable_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


                ET_Valor_Fecha_Inicio_Evento.setFocusableInTouchMode(true);
                ET_Valor_Fecha_Inicio_Evento.setCursorVisible(true);
                ET_Valor_Fecha_Inicio_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));


                ET_Valor_Fecha_Fin_Evento.setFocusableInTouchMode(true);
                ET_Valor_Fecha_Fin_Evento.setCursorVisible(true);
                ET_Valor_Fecha_Fin_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));



            }//Fin del for







        }//Fin flag



    }/*******FIN DE LA FUNCIÓN EditarDatosItemEvento() ********/






    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/


    public void LeerEventoEnSharedPreferences(){

        SharedPreferences preferences = getSharedPreferences("Datos_Evento_Responsable", MODE_PRIVATE);



        String DNI_Responsable = preferences.getString("DNI_Responsable", "");

        String Nombre_Apellido_Responsable =  preferences.getString("Nombre_Apellido_Responsable", "");

        String Telefono_Responsable = preferences.getString("Telefono_Responsable", "");

        String Direccion_Responsable = preferences.getString("Direccion_Responsable", "");

        String Barrio_Responsable = preferences.getString("Barrio_Responsable", "");

        String Correo_Responsable = preferences.getString("Correo_Responsable", "");

        String Referencia_Responsable = preferences.getString("Referencia_Responsable", "");




        String Nombre_Evento = preferences.getString("Nombre_Evento", "");

        String Direccion_Evento = preferences.getString("Direccion_Evento", "");

        String Barrio_Evento = preferences.getString("Barrio_Evento", "");

        String Referencia_Evento = preferences.getString("Referencia_Evento", "");

        String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento", "");

        String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento", "");






    }/******************** FIN DE LA FUNCIÓN LeerEventoEnSharedPreferences() *******************/



    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/


/*
    public void GuardarCambiosEnElEventoEnUnSharedPreferences(){

        int indice = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("Datos_Item_Evento_Responsable", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        String DimensionEvento = sharedPreferences.getString("DimensionItemDelEvento", "");

        if(DimensionEvento == ""){

            indice = 0;


        }//Fin del if


        else {

            indice = Integer.parseInt(DimensionEvento) + 1;

        }//Fin del else

        //editor.putString("DNI_Responsable" + indice, et_dni_nuevo_responsable.getText().toString());

        editor.putString("Nombre_Apellido_Responsable" + indice, et_nombre_apellido_nuevo_responsable.getText().toString());

        //editor.putString("Telefono_Responsable" + indice, et_telefono_nuevo_responsable.getText().toString());

        //editor.putString("Direccion_Responsable" + indice, et_direccion_nuevo_responsable.getText().toString());

        //editor.putString("Barrio_Responsable" + indice, et_barrio_nuevo_responsable.getText().toString());

        //editor.putString("Correo_Responsable" + indice, et_correo_nuevo_responsable.getText().toString());

        //editor.putString("Referencia_Responsable" + indice, et_referencia_nuevo_responsable.getText().toString());




        editor.putString("Nombre_Evento" + indice, et_nombre_del_evento_nuevo_responsable.getText().toString());

        //editor.putString("Direccion_Evento" + indice, et_direccion_del_evento_nuevo_responsable.getText().toString());

        //editor.putString("Barrio_Evento" + indice, et_barrio_del_evento_nuevo_responsable.getText().toString());

        //editor.putString("Referencia_Evento" + indice, et_referencia_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Inicio_Evento" + indice, et_fecha_inicio_del_evento_nuevo_responsable.getText().toString());

        editor.putString("Fecha_Fin_Evento" + indice, et_fecha_fin_del_evento_nuevo_responsable.getText().toString());


        editor.putString("DimensionDeEvento", String.valueOf(indice));

        editor.commit();



    } */ /******************** FIN DE LA FUNCIÓN GuardarEventoEnSharedPreferences() *******************/





    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/









}/*************************** FIN DE LA ACTIVITY BuscarResponsableParaPatrocinio ******************************/