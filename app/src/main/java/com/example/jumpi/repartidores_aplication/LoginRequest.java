package com.example.jumpi.repartidores_aplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
// "http://192.168.1.14/av/login/login.php"
   //"http://192.168.1.117/av/login/login2deprueba.php"
// "https://aquavital.000webhostapp.com/login/login.php"

    private static final String LOGIN_REQUEST_URL = "http://192.168.1.5/av/login/login2deprueba.php";

    private Map<String, String> params;

        public LoginRequest(String username, String password, String intento, Response.Listener<String> listener) {
            super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("usuario", username);
        params.put("contraseña", password);
        params.put("intento", intento);
    }


    public Map<String, String> getParams() {
        return params;
    }
}
