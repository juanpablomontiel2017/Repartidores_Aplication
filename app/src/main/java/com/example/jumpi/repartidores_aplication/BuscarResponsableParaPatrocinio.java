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
import android.view.KeyEvent;
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
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.GONE;

public class BuscarResponsableParaPatrocinio extends AppCompatActivity implements SearchView.OnQueryTextListener{



    public static Button btnResponsableActivo;


    LinearLayout LinearLayoutVerticalPadre;


    ArrayList<View> ArrayListItemEvento = new ArrayList<View>();

    private ListView lv;

    ArrayAdapter<String> adapter;

    // Arreglo de nombres y apellidos de todos los responsables
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

        adapter = new ArrayAdapter<String>(this,R.layout.list_nombres_responsables_item, R.id.codigo_token, tokens);

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

                    intent.putExtra("Nombre_Responsable_Enviar", "Juan Pablo");

                    intent.putExtra("Apellido_Responsable_Enviar", "Montiel");

                    intent.putExtra("Codigo_Area_Responsable_Enviar", "3734");

                    intent.putExtra("Telefono_Responsable_Enviar", "446587");

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

        SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

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

    public  void ObtenerItemEvento(final int indice_evento){


        SharedPreferences preferences_estado_evento = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

        if(preferences_estado_evento.getBoolean("Estado_Evento" + indice_evento, false)) {


            View NuevoItemInflado;

            /*Llamada a la función: */
            NuevoItemInflado = AgregarItemEvento();

            ArrayListItemEvento.add(NuevoItemInflado);


            final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);


            final LinearLayout LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);


            final LinearLayout LinearLayoutVerticalContenedorHijo = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


            final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);


            final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);


            final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);


            final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


            final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


            final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);


            final LinearLayout LinearLayoutHorizontalFechaFin = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


            final TextView TV_Fecha_Fin_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_fin_recibir);


            final EditText ET_Valor_Fecha_Fin_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_fin_recibir);





            /*Aca leemos los valores que nos interesan y que están guardados en el SharedPreferences de la otra Activity */

            SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

            String Nombre_Evento = preferences.getString("Nombre_Evento" + indice_evento, "");
            String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento" + indice_evento, "");
            String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento" + indice_evento, "");
            int Indice_Responsable = preferences.getInt("Indice_Responsable" + indice_evento, 0);


            //ArrayList<String> NombreApellidoResponsable = new ArrayList<>();

            //NombreApellidoResponsable = ObtenerApellidoNombreResponsable(Indice_Responsable);


            ET_Nombre_Evento.setText(Nombre_Evento);
            ET_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
            ET_Valor_Fecha_Fin_Evento.setText(Fecha_Fin_Evento);


            /** De esta manera anduvo **/

            SharedPreferences preferences_responsable = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);

            String Nombre_Responsable = preferences_responsable.getString("Nombre_Responsable" + Indice_Responsable, "");
            String Apellido_Responsable = preferences_responsable.getString("Apellido_Responsable" + Indice_Responsable, "");

            ET_Nombre_Responsable_Evento.setText(Nombre_Responsable);
            ET_Apellido_Responsable_Evento.setText(Apellido_Responsable);


            /** DE ESTA FORMA MUESTRA EL NOMBRE Y APELLIDO DEL ÚLTIMO RESPONSABLE CREADO EN TODOS LOS ITEMS ***/

            //ET_Nombre_Responsable_Evento.setText(NombreApellidoResponsable.get(0));
            //ET_Apellido_Responsable_Evento.setText(NombreApellidoResponsable.get(1));


            final Button btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);
            btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

                    intent.putExtra("Indice_Evento", indice_evento);

                    intent.putExtra("Nombre_del_evento", ET_Nombre_Evento.getText().toString());

                    intent.putExtra("Nombre_del_responsable", ET_Nombre_Responsable_Evento.getText().toString());

                    intent.putExtra("Apellido_del_responsable", ET_Apellido_Responsable_Evento.getText().toString());


                    startActivity(intent);

                }
            });


            final Button btn_ver_datos_personales_responsable_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_datos_personales_item_evento);
            btn_ver_datos_personales_responsable_patrocinio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int ValorDeLaPosicion;

                    ValorDeLaPosicion = EncontrarPosicionDeItemEvento(view);

                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, DatosPersonalesResponsable.class);

                    intent.putExtra("Indice_Item", ValorDeLaPosicion);

                    startActivity(intent);

                }
            });


            /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
             * de Patrocinio**/

            Usuario usuario = new Usuario();

            usuario.LeerUsuarioEnUnSharedPreferences(this);

            if (usuario.getTipo_de_Usuario().equals("repartidor")) {

                LinearLayoutVerticalPadre.setBackgroundColor(Color.parseColor("#2962ff"));

                btn_ver_detalle_entrega_retiro_envases_patrocinio.getBackground().setColorFilter(Color.parseColor("#0d47a1"), PorterDuff.Mode.SRC_ATOP);

            }


        }//FIN DEL if


        else {




            View NuevoItemInflado;

            /*Llamada a la función: */
            NuevoItemInflado = AgregarItemEvento();

            ArrayListItemEvento.add(NuevoItemInflado);


            final View view_estado_evento = (View) NuevoItemInflado.findViewById(R.id.view_estado_evento);

            view_estado_evento.setBackgroundColor(Color.parseColor("#0d47a1"));


            final LinearLayout LinearLayoutVerticalPadre = (LinearLayout) findViewById(R.id.linear_layout_vertical_padre);


            final LinearLayout LinearLayoutVerticalContenedorHijo = (LinearLayout) NuevoItemInflado.findViewById(R.id.llv_contenedor_hijo_de_evento);


            final EditText ET_Nombre_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_evento_recibir);


            final EditText ET_Nombre_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_nombre_responsable_recibir);


            final EditText ET_Apellido_Responsable_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_apellido_responsable_recibir);


            final LinearLayout LinearLayoutHorizontalFechaInicio = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_inicio);


            final TextView TV_Fecha_Inicio_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_inicio_recibir);


            final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_inicio_recibir);


            final LinearLayout LinearLayoutHorizontalFechaFin = (LinearLayout) NuevoItemInflado.findViewById(R.id.llh_fecha_fin);


            final TextView TV_Fecha_Fin_Evento = (TextView) NuevoItemInflado.findViewById(R.id.tv_fecha_fin_recibir);


            final EditText ET_Valor_Fecha_Fin_Evento = (EditText) NuevoItemInflado.findViewById(R.id.et_valor_fecha_fin_recibir);






            SharedPreferences preferences = getSharedPreferences("Datos_Evento", MODE_PRIVATE);

            String Nombre_Evento = preferences.getString("Nombre_Evento" + indice_evento, "");
            String Fecha_Inicio_Evento = preferences.getString("Fecha_Inicio_Evento" + indice_evento, "");
            String Fecha_Fin_Evento = preferences.getString("Fecha_Fin_Evento" + indice_evento, "");
            int Indice_Responsable = preferences.getInt("Indice_Responsable" + indice_evento, 0);



            ET_Nombre_Evento.setText(Nombre_Evento);
            ET_Valor_Fecha_Inicio_Evento.setText(Fecha_Inicio_Evento);
            ET_Valor_Fecha_Fin_Evento.setText(Fecha_Fin_Evento);




            SharedPreferences preferences_responsable = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);

            String Nombre_Responsable = preferences_responsable.getString("Nombre_Responsable" + Indice_Responsable, "");
            String Apellido_Responsable = preferences_responsable.getString("Apellido_Responsable" + Indice_Responsable, "");

            ET_Nombre_Responsable_Evento.setText(Nombre_Responsable);
            ET_Apellido_Responsable_Evento.setText(Apellido_Responsable);



            final Button btn_ver_detalle_entrega_retiro_envases_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_detalle_item_evento);
            btn_ver_detalle_entrega_retiro_envases_patrocinio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, EntregaRetiroEnvasesPatrocinio.class);

                    intent.putExtra("Nombre_del_evento", ET_Nombre_Evento.getText().toString());

                    intent.putExtra("Nombre_del_responsable", ET_Nombre_Responsable_Evento.getText().toString());

                    intent.putExtra("Apellido_del_responsable", ET_Apellido_Responsable_Evento.getText().toString());


                    startActivity(intent);

                }
            });


            final Button btn_ver_datos_personales_responsable_patrocinio = (Button) NuevoItemInflado.findViewById(R.id.btn_ver_datos_personales_item_evento);
            btn_ver_datos_personales_responsable_patrocinio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int ValorDeLaPosicion;

                    ValorDeLaPosicion = EncontrarPosicionDeItemEvento(view);

                    Intent intent = new Intent(BuscarResponsableParaPatrocinio.this, DatosPersonalesResponsable.class);

                    intent.putExtra("Indice_Item", ValorDeLaPosicion);

                    startActivity(intent);

                }
            });


            /** Pregunta si el usuario es un "repartidor" entonces habrá un cambio de colores en las activity's
             * de Patrocinio**/

            Usuario usuario = new Usuario();

            usuario.LeerUsuarioEnUnSharedPreferences(this);

            if (usuario.getTipo_de_Usuario().equals("repartidor")) {

                LinearLayoutVerticalPadre.setBackgroundColor(Color.parseColor("#2962ff"));

                btn_ver_detalle_entrega_retiro_envases_patrocinio.getBackground().setColorFilter(Color.parseColor("#0d47a1"), PorterDuff.Mode.SRC_ATOP);

            }




        }//FIN DEL else


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



     public ArrayList<String> ObtenerApellidoNombreResponsable(int indice_responsable){


         ArrayList<String> NombreApellidoResponsable = new ArrayList<>();

         SharedPreferences preferences = getSharedPreferences("Datos_Responsable", MODE_PRIVATE);


         NombreApellidoResponsable.add(preferences.getString("Nombre_Responsable" + indice_responsable, ""));
         NombreApellidoResponsable.add(preferences.getString("Apellido_Responsable" + indice_responsable, ""));



         return NombreApellidoResponsable;


     }/***************************FIN DE LA FUNCIÓN ObtenerApellidoNombreResponsable()*****************************/



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





    public int  EncontrarPosicionDeItemEvento(View button_ver_responsable){

        int posicion_item = 99;

        View Item = EncontrarItemPadre(button_ver_responsable);

        for(int i = 0 ; i < ArrayListItemEvento.size(); i++ ){

            if(ArrayListItemEvento.get(i) == Item ){

                posicion_item = i;

                break;

            }//Fin del if


        }//Fin del for


        return posicion_item;


    }/*********************************FIN DE LA FUNCIÓN EncontrarPosicionDeItemEvento()*******************************************/






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




    public View EncontrarItemPadre (View button_ver_responsable){


        LinearLayout linearLayoutHorizontalButtonVerResponsable = (LinearLayout) button_ver_responsable.getParent();

        LinearLayout linearLayoutVerticalContenedorVistas = (LinearLayout) linearLayoutHorizontalButtonVerResponsable.getParent();

        LinearLayout linearLayoutHorizontalPadreContenedorVistas = (LinearLayout) linearLayoutVerticalContenedorVistas.getParent();

        LinearLayout linearLayoutVerticalPadredePadres = (LinearLayout) linearLayoutHorizontalPadreContenedorVistas.getParent();

        LinearLayout linearLayoutVerticalContenedorDeTodoElItem = (LinearLayout) linearLayoutVerticalPadredePadres.getParent();




        return linearLayoutVerticalContenedorDeTodoElItem;



    } /******************************FIN DE LA FUNCION EncontrarItemPadre()*****************************/




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

                final EditText ET_Nombre_Responsable_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_nombre_responsable_recibir);

                final EditText ET_Apellido_Responsable_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_apellido_responsable_recibir);

                final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_valor_fecha_inicio_recibir);

                final EditText ET_Valor_Fecha_Fin_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_valor_fecha_fin_recibir);


                ET_Nombre_Evento.setFocusableInTouchMode(true);
                ET_Nombre_Evento.requestFocus();
                ET_Nombre_Evento.setCursorVisible(true);
                ET_Nombre_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                ET_Nombre_Responsable_Evento.setFocusableInTouchMode(true);
                ET_Nombre_Responsable_Evento.setCursorVisible(true);
                ET_Nombre_Responsable_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




                ET_Apellido_Responsable_Evento.setFocusableInTouchMode(true);
                ET_Apellido_Responsable_Evento.setCursorVisible(true);
                ET_Apellido_Responsable_Evento.setBackgroundDrawable(getDrawable(R.drawable.edit_text_material_customizado));




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




    public boolean DeshabilitarVistasDeCadaItemAlGuardarCambios(boolean flag_enabled){

        if (flag_enabled) {



            for(int k=0; k < ArrayListItemEvento.size(); k++){



                final EditText ET_Nombre_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_nombre_evento_recibir);

                final EditText ET_Nombre_Responsable_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_nombre_responsable_recibir);

                final EditText ET_Apellido_Responsable_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_apellido_responsable_recibir);

                final EditText ET_Valor_Fecha_Inicio_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_valor_fecha_inicio_recibir);

                final EditText ET_Valor_Fecha_Fin_Evento = (EditText) ArrayListItemEvento.get(k).findViewById(R.id.et_valor_fecha_fin_recibir);






                ET_Nombre_Evento.setFocusable(false);
                ET_Nombre_Evento.setCursorVisible(false);
                ET_Nombre_Evento.setBackgroundColor(Color.TRANSPARENT);



                ET_Nombre_Responsable_Evento.setFocusable(false);
                ET_Nombre_Responsable_Evento.setCursorVisible(false);
                ET_Nombre_Responsable_Evento.setBackgroundColor(Color.TRANSPARENT);



                ET_Apellido_Responsable_Evento.setFocusable(false);
                ET_Apellido_Responsable_Evento.setCursorVisible(false);
                ET_Apellido_Responsable_Evento.setBackgroundColor(Color.TRANSPARENT);



                ET_Valor_Fecha_Inicio_Evento.setFocusable(false);
                ET_Valor_Fecha_Inicio_Evento.setCursorVisible(false);
                ET_Valor_Fecha_Inicio_Evento.setBackgroundColor(Color.TRANSPARENT);



                ET_Valor_Fecha_Fin_Evento.setFocusable(false);
                ET_Valor_Fecha_Fin_Evento.setCursorVisible(false);
                ET_Valor_Fecha_Fin_Evento.setBackgroundColor(Color.TRANSPARENT);




            } //Fin del for


        }  /*Fin del primer if (flag_enabled) {}*/



        return flag_enabled;



    }/*******FIN DE LA FUNCIÓN DeshabilitarVistasDeCadaItemAlGuardarCambios() ********/







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

        /* Aparece una "lupa" en el lado derecho */
        searchView.setIconifiedByDefault(false);

        //searchView.setIconified(false);

        /* Ingresar a la activity con el buscador "activado" */
        searchItem.expandActionView();



        return true;

    } /************** Fin del método onCreateOptionsMenu() ******************/






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


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
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {

            Intent intent = new Intent (BuscarResponsableParaPatrocinio.this, MainActivity.class);

            startActivity(intent);

        }

        return super.onKeyDown(keyCode, event);
    }


}/*************************** FIN DE LA ACTIVITY BuscarResponsableParaPatrocinio ******************************/