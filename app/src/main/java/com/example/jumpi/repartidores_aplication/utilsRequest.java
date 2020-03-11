package com.example.jumpi.repartidores_aplication;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class utilsRequest extends JsonObjectRequest {
/**
    private JSONObject params;


    public JSONObject getParams() {

        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }

*/




    private utilsRequest( String url, JSONObject params,  Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, params, listener, errorListener);
        //this.setParams(params);



    }

    public static utilsRequest loginRequest(String username, String password, String intento, Response.Listener<JSONObject> listener) {

        String dirServer = vault.DIR_SERVER+vault.LOGIN_REQUEST;
        utilsRequest request;


        JSONObject parametro = new JSONObject();

        //parametro = new HashMap<>();
        try {
            parametro.put("usuario", username);
            parametro.put("contraseña", password);
            parametro.put("intento", intento);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        request = new utilsRequest(dirServer, parametro, listener, null );

        return request;

    }


    public static utilsRequest cargaDescarga(String idSupervisor,
                                             String dniSupervisor,
                                             String fecha,
                                             String idRepartidor,
                                             String dniRepartidor,
                                             JSONArray arrayTandas,
                                             Response.Listener<JSONObject> listener) {

        String dirServer = vault.DIR_SERVER+vault.CARGA_DESCARGA_REQUEST;
        utilsRequest request;


        //Map params;
        JSONObject params = new JSONObject();
        //params = new HashMap<>();
        try {
            params.put("idSupervisor", idSupervisor);
            params.put("dniSupervisor", dniSupervisor);
            params.put("fecha", fecha);
            params.put("idRepartidor", idRepartidor);
            params.put("dniRepartidor", dniRepartidor);

            params.put("tandas", arrayTandas);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        request = new utilsRequest(dirServer, params, listener, null);
        Log.d("TFSB", "retorna request");
        return request;


    }

}
