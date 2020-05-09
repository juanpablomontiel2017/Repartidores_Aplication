package com.example.jumpi.repartidores_aplication;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
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
 * Esta clase maneja todos las solicitudes al servidor
 * Establece la forma y tipos de datos requeridos para conectarse con la api
 * Utiliza Json para comunicarse con la api
 * */


    private utilsRequest( String url, JSONObject params,  Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, params, listener, errorListener);
        //this.setParams(params);




    }

    /**
     * Cambia el content type del header a aplication json como
     * espera la api
     * */

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        return params;
    }

    public static utilsRequest loginRequest(String username, String password, String intento, Response.Listener<JSONObject> listener) {

        String dirServer = vault.DIR_SERVER+vault.LOGIN_REQUEST;
        utilsRequest request;


        JSONObject parametro = new JSONObject();

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



        JSONObject params = new JSONObject();

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


    public class Request extends AsyncTask<String,Void, String> {

        int CONSULTA;
        InterfaceRequest RESPUESTA = interfaceRequest;
        Notificacion NOTIFICACION = Notificacion;
        String URL_REQUEST;
        String METHOD;
        String PARAMETROS;

        @Override
        protected String doInBackground(String... params) {

            CONSULTA = Integer.parseInt(params[0]);
            URL_REQUEST = params[1];
            METHOD = params[2];
            try{
                PARAMETROS = params[3];
            }catch (Exception e){
                PARAMETROS = "";
            }

            HttpClient httpClient = new DefaultHttpClient();

            System.out.println("CONSULTA SERVER"+ URL_REQUEST +"PARAMETROS"+PARAMETROS);

            switch (METHOD) {
                case "GET":
                    try {
                        HttpGet get = new HttpGet(URL_REQUEST);
                        get.setHeader("content-type", "application/json");
                        get.setHeader("Authorization",Token);
                        HttpResponse resp = httpClient.execute(get);
                        return EntityUtils.toString(resp.getEntity());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "Error" + e;
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                        return "Error" + e;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "Error" + e;
                    }
                case "POST":
                    try {
                        HttpPost post = new HttpPost(URL_REQUEST);
                        post.setHeader("content-type", "application/json");
                        post.setHeader("Authorization",Token);
                        StringEntity entity = new StringEntity(PARAMETROS,"UTF-8");
                        post.setEntity(entity);


                        HttpResponse resp = httpClient.execute(post);
                        return EntityUtils.toString(resp.getEntity());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "Error" + e;
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                        return "Error" + e;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "Error" + e;
                    }

                default:
                    return "";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("RESPUESTA SERVER "+result);
            try {

                if(!result.equals("null")){
                    RESPUESTA.ResultJson(result,CONSULTA);
                }else{
                    RESPUESTA.ResultError("Hubo un error en el servidor, intente nuevamente");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                RESPUESTA.ResultError(e.toString());
            }
        }

    }

}
