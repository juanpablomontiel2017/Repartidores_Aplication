package com.example.jumpi.repartidores_aplication;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
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




    private utilsRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> parametro) {
        super(method, url, listener, errorListener);
        this.setParams(parametro);



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
                                             ArrayList arrayTandas,
                                             Response.Listener<String> listener) {

        String dirServer = vault.DIR_SERVER+vault.CARGA_DESCARGA_REQUEST;
        utilsRequest request;


        Map parametro;

        parametro = new HashMap<>();
        parametro.put("idSupervisor", idSupervisor);
        parametro.put("dniSupervisor", dniSupervisor);
        parametro.put("fecha", fecha);
        parametro.put("idRepartidor", idRepartidor);
        parametro.put("dniRepartidor", dniRepartidor);

        parametro.put("tandas", arrayTandas);

  
        request = new utilsRequest(Request.Method.POST, dirServer, listener, null, parametro);
        Log.d("TFSB", "retorna request");
        return request;


    }

}
