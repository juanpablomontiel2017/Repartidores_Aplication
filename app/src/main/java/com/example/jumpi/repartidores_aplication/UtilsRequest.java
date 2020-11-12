package com.example.jumpi.repartidores_aplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UtilsRequest extends StringRequest {

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private Map<String, String> params;




    private UtilsRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> parametro) {
        super(method, url, listener, errorListener);
        this.setParams(parametro);



    }

    public static UtilsRequest loginRequest(String username, String password, String intento, Response.Listener<String> listener) {

        String dirServer = vault.DIR_SERVER+vault.LOGIN_REQUEST;
        UtilsRequest request;


        Map<String, String> parametro;

        parametro = new HashMap<>();
        parametro.put("usuario", username);
        parametro.put("contraseña", password);
        parametro.put("intento", intento);

        request = new UtilsRequest(Request.Method.POST, dirServer, listener, null, parametro);

        return request;

    }

}
