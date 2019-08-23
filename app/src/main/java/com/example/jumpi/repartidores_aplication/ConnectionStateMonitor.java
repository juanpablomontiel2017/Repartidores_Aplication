package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionStateMonitor extends ConnectivityManager.NetworkCallback {




    Context cntx;

    final NetworkRequest networkRequest;




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




    public ConnectionStateMonitor(Context context) {

        this.cntx = context;

        networkRequest = new NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build();


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




    public void enable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) cntx.getSystemService(Context.CONNECTIVITY_SERVICE);

        connectivityManager.registerNetworkCallback(networkRequest , this);


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
    public void onAvailable(Network network) {


        final DbHelper dbHelper = new DbHelper(cntx);

        final SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = dbHelper.readFromLocalDatabaseZonaReparto(database);



        while (cursor.moveToNext()){

            int sync_status = cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));


            if (sync_status==DbContract.SYNC_STATUS_FAILED)

            {


                final String apellido = cursor.getString(cursor.getColumnIndex(DbContract.APELLIDO));

                final String nombre = cursor.getString(cursor.getColumnIndex(DbContract.NOMBRE));

                final String direccion = cursor.getString(cursor.getColumnIndex(DbContract.DIRECCION));

                final String barrio = cursor.getString(cursor.getColumnIndex(DbContract.BARRIO));

                final String referencia = cursor.getString(cursor.getColumnIndex(DbContract.REFERENCIA));

                final String telefono = cursor.getString(cursor.getColumnIndex(DbContract.TELEFONO));

                final String correo = cursor.getString(cursor.getColumnIndex(DbContract.CORREO));

                final String dni = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                final String id = cursor.getString(cursor.getColumnIndex(DbContract.ID));

                final int foto = R.drawable.leomessi;




                final String lunes = cursor.getString(cursor.getColumnIndex(DbContract.LUNES));

                final String martes = cursor.getString(cursor.getColumnIndex(DbContract.MARTES));


                final String miercoles = cursor.getString(cursor.getColumnIndex(DbContract.MIERCOLES));

                final String jueves = cursor.getString(cursor.getColumnIndex(DbContract.JUEVES));

                final String viernes = cursor.getString(cursor.getColumnIndex(DbContract.VIERNES));

                final String sabado = cursor.getString(cursor.getColumnIndex(DbContract.SABADO));



                StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_URL,
                        new Response.Listener<String>() {

                            @Override

                            public void onResponse(String response) {


                                try {

                                    JSONObject jsonObject = new JSONObject(response);

                                    boolean success = jsonObject.getBoolean("exito");

                                    if (success)


                                    {
                                        dbHelper.updateLocalDatabaseZonaReparto(Integer.parseInt(dni), Integer.parseInt(dni), null,null,null,null,null, null, null, DbContract.ENTERO_NULO, DbContract.ENTERO_NULO, DbContract.ENTERO_NULO, DbContract.ENTERO_NULO, DbContract.ENTERO_NULO, DbContract.ENTERO_NULO,  DbContract.SYNC_STATUS_OK, database);

                                        cntx.sendBroadcast(new Intent(DbContract.UI_UPDATE_BROADCAST));

                                    }


                                } catch (JSONException e) {

                                    e.printStackTrace();

                                }


                            }
                        }, new Response.ErrorListener() {


                    @Override

                    public void onErrorResponse(VolleyError error) {



                    }
                })



                {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        Map<String,String> params = new HashMap<>();

                        params.put("dni",dni);

                        params.put("id",id);

                        params.put("apellido",apellido);

                        params.put("foto",String.valueOf(foto));

                        params.put("nombre",nombre);

                        params.put("direccion",direccion);

                        params.put("barrio",barrio);

                        params.put("referencia",referencia);

                        params.put("telefono",telefono);

                        params.put("correo",correo);

                        params.put("lunes",lunes);

                        params.put("martes",martes);

                        params.put("miercoles",miercoles);

                        params.put("jueves",jueves);

                        params.put("viernes",viernes);

                        params.put("sabado",sabado);



                        return params;


                    }



                }


                        ;



                MySingleton.getInstance(cntx).addToRequestQueue(stringRequest);
            }




        }



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






}/******************************** FIN DE LA CLASE ConnectionStateMonitor ********************************/
