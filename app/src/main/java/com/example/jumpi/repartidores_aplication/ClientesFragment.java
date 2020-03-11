package com.example.jumpi.repartidores_aplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ClientesFragment extends Fragment implements OnStartDragListener {






    private RecyclerView myrecyclerview;

    private List<Clientes> lstClientes;

    ItemTouchHelper mItemTouchHelper;

    BroadcastReceiver broadcastReceiver;

    String dia = null;


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




    public ClientesFragment() {



    }/************** FIN DEL CONSTRUCTOR ClientesFragment() ******************/


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        broadcastReceiver = new BroadcastReceiver() {

            @Override

            public void onReceive(Context context, Intent intent) {

               readFromLocalDbZonaReparto(dia);

            }

        };


        dia = getArguments().getString("dia");


        lstClientes = new ArrayList<>();
        readFromLocalDbZonaReparto(dia);



    }/************************* FIN DEL onCreate()***************************/




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









    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_clientes, container, false);

        myrecyclerview = (RecyclerView) v.findViewById(R.id.clientes_recyclerview);

        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstClientes, this);



        /*ItemTouchHelper.Callback callback = new EditItemTouchHelperCallback(recyclerAdapter);


        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        mItemTouchHelper = new ItemTouchHelper(callback);

        mItemTouchHelper.attachToRecyclerView(myrecyclerview);


        myrecyclerview.setAdapter(recyclerAdapter);
            */

        return v;


    }/**************************** FIN DEL onCreateView() ********************/






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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

        mItemTouchHelper.startDrag(viewHolder);

    }




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


    public boolean checkNetworkConnection()
    {

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!= null && networkInfo.isConnected());


    }



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



    private void readFromLocalDbZonaReparto(String dia){

        DbHelper dbHelperRead = new DbHelper(getContext());

        SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();

        Cursor cursor = dbHelperRead.readFromLocalDatabaseZonaReparto(databaseRead);

        lstClientes.clear();


        while (cursor.moveToNext())

        {

            int diaDB = Integer.parseInt(cursor.getString(cursor.getColumnIndex(dia)));



            if (diaDB == DbContract.DIA_OK){

                String apellido = cursor.getString(cursor.getColumnIndex(DbContract.APELLIDO));

                String nombre = cursor.getString(cursor.getColumnIndex(DbContract.NOMBRE));


                String direccion = cursor.getString(cursor.getColumnIndex(DbContract.DIRECCION));

                String barrio = cursor.getString(cursor.getColumnIndex(DbContract.BARRIO));

                String referencia = cursor.getString(cursor.getColumnIndex(DbContract.REFERENCIA));

                String telefono = cursor.getString(cursor.getColumnIndex(DbContract.TELEFONO));

                String correo = cursor.getString(cursor.getColumnIndex(DbContract.CORREO));

                String dni = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                String id = cursor.getString(cursor.getColumnIndex(DbContract.ID));

                int foto = R.drawable.leomessi;

                int sync_status = cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));



                lstClientes.add(new Clientes(Integer.parseInt(dni), Integer.parseInt(id), foto, apellido, nombre, direccion, barrio, referencia, telefono, correo));


            }




        }

        cursor.close();

        dbHelperRead.close();




    }/************************ readFromLocalDbZonaReparto()********************/





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





    private void actualizarMyRecyclerView(){

        DbHelper dbHelperRead = new DbHelper(getContext());

        SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();

        Cursor cursor = dbHelperRead.readFromLocalDatabaseZonaReparto(databaseRead);

        lstClientes.clear();


        while (cursor.moveToNext())

        {


            String apellido = cursor.getString(cursor.getColumnIndex(DbContract.APELLIDO));

            String nombre = cursor.getString(cursor.getColumnIndex(DbContract.NOMBRE));

            String direccion = cursor.getString(cursor.getColumnIndex(DbContract.DIRECCION));

            String barrio = cursor.getString(cursor.getColumnIndex(DbContract.BARRIO));

            String referencia = cursor.getString(cursor.getColumnIndex(DbContract.REFERENCIA));

            String telefono = cursor.getString(cursor.getColumnIndex(DbContract.TELEFONO));

            String correo = cursor.getString(cursor.getColumnIndex(DbContract.CORREO));

            String dni = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

            String id = cursor.getString(cursor.getColumnIndex(DbContract.ID));

            int foto = R.drawable.leomessi;

            int sync_status = cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));


            lstClientes.add(new Clientes(Integer.parseInt(dni), Integer.parseInt(id), foto, apellido, nombre, direccion, barrio, referencia, telefono, correo));


            myrecyclerview.notify();



        }

        cursor.close();

        dbHelperRead.close();


    }/************************************ actualizarMyRecyclerView() ************************************/




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




    private void saveToRemoteDbZonaReparto(final int DNI, final int IdPersona, final int foto, final String apellido, final String nombre, final String direccion, final String barrio, final String referencia, final String telefono, final String correo, final int lunes, final int martes, final int miercoles, final int jueves, final int viernes, final int sabado){

        /**
         * Sirve para guardar los clientes nuevos.
         * Envía los datos al server
         * Guarda los datos en la BD local
         *
         */


        if (checkNetworkConnection()){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_URL,


                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {


                            try {

                                JSONObject jsonObject = new JSONObject(response);

                                boolean success = jsonObject.getBoolean("exito");


                                if (success){

                                    saveToLocalDbZonaReparto(DNI, IdPersona, foto, apellido, nombre, direccion, barrio, referencia, telefono, correo, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_OK);

                                }


                                else

                                {

                                    saveToLocalDbZonaReparto(DNI, IdPersona, foto, apellido, nombre, direccion, barrio, referencia, telefono, correo, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_FAILED);


                                }


                            } catch (JSONException e) {

                                e.printStackTrace();


                            }


                        }



                        }, new Response.ErrorListener(){


                @Override
                public void onErrorResponse(VolleyError error) {


                    saveToLocalDbZonaReparto(DNI, IdPersona, foto, apellido, nombre, direccion, barrio, referencia, telefono, correo, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_FAILED);

                }


            })



            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {


                    Map<String,String> params =  new HashMap<>();

                    return super.getParams();

                }


            };




            MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);



        }



        else

        {


            saveToLocalDbZonaReparto(DNI, IdPersona, foto, apellido, nombre, direccion, barrio, referencia, telefono, correo, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_FAILED);


        }





    }/******************************* saveToRemoteDbZonaReparto() ********************************/





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




    public void saveToLocalDbZonaReparto(int DNI, int IdPersona, int foto,String apellido, String nombre, String direccion, String barrio,String referencia, String telefono, String correo, int lunes, int martes, int miercoles, int jueves, int viernes, int sabado, int sync){
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        dbHelper.saveToLocalDatabaseZonaReparto(DNI, IdPersona, apellido, nombre, direccion, barrio,referencia, telefono, correo, foto, lunes, martes, miercoles, jueves, viernes, sabado, sync, database);
        readFromLocalDbZonaReparto(dia);
        dbHelper.close();
    }




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
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(DbContract.UI_UPDATE_BROADCAST));
    }






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
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }








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







}/****************************** FIN DE LA CLASE FRAGMENT ClientesFragment ************************/