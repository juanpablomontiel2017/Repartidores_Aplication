package com.example.jumpi.repartidores_aplication;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class utilsRequest extends StringRequest {

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private Map<String, String> params;




    private utilsRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> params) {
        super(method, url, listener, errorListener);
        this.setParams(params);



    }

    public static utilsRequest loginRequest(String username, String password, String intento, Response.Listener<String> listener) {

        String dirServer = vault.DIR_SERVER+vault.LOGIN_REQUEST;
        utilsRequest request;


        Map<String, String> parametro;

        parametro = new HashMap<>();
        parametro.put("usuario", username);
        parametro.put("contraseña", password);
        parametro.put("intento", intento);

        request = new utilsRequest(Request.Method.POST, dirServer, listener, null, parametro);

        return request;

    }


    public static utilsRequest cargaDescarga(String idSupervisor,
                                             String dniSupervisor,
                                             String fecha,
                                             String idRepartidor,
                                             String dniRepartidor,
                                             JSONArray arrayTandas,
                                             Response.Listener<String> listener) {

        String dirServer = vault.DIR_SERVER+vault.CARGA_DESCARGA_REQUEST;
        utilsRequest request;


        Map params;

        params = new HashMap<>();
        params.put("idSupervisor", idSupervisor);
        params.put("dniSupervisor", dniSupervisor);
        params.put("fecha", fecha);
        params.put("idRepartidor", idRepartidor);
        params.put("dniRepartidor", dniRepartidor);

        params.put("tandas", arrayTandas);


        request = new utilsRequest(Request.Method.POST, dirServer, listener, null, params);
        Log.d("TFSB", "retorna request");
        return request;


    }

}
