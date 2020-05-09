package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class utilsRequest  {
/**
 * Esta clase maneja todos las solicitudes al servidor
 * Establece la forma y tipos de datos requeridos para conectarse con la api
 * Utiliza Json para comunicarse con la api
 * */

    private String URL_SERVER;
    interfaceRequest interfaceRequest = null;
    private Context context;
    private String Token;


    public utilsRequest(Context context, interfaceRequest interfacerequest){
        this.interfaceRequest = interfacerequest;
        this.context = context;
        URL_SERVER = vault.DIR_SERVER;
       // DB db = new DB(context);
        //Token = db.getUsuario().getToken();

    }


    public void cargaDescarga (String idSupervisor,
                               String dniSupervisor,
                               String fecha,
                               String idRepartidor,
                               String dniRepartidor,
                               JSONArray arrayTandas,
                               Integer  idRequest  ) throws JSONException{

        Request request = new Request();
        JSONObject params = new JSONObject();


        params.put("idSupervisor", idSupervisor);
        params.put("dniSupervisor", dniSupervisor);
        params.put("fecha", fecha);
        params.put("idRepartidor", idRepartidor);
        params.put("dniRepartidor", dniRepartidor);
        params.put("tandas", arrayTandas);

        request.execute(Integer.toString(idRequest),URL_SERVER+vault.CARGA_DESCARGA_REQUEST,"POST",params.toString());

        Log.d("DEBUG", "termina utilsRequest.cargaDescarga");



    }

    public class Request extends AsyncTask<String,Void, String> {

    int CONSULTA;
    interfaceRequest RESPUESTA = interfaceRequest;
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
                   // get.setHeader("Authorization",Token);
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
                    //post.setHeader("Authorization",Token);
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
            case "PUT":
                try {
                    HttpPut put = new HttpPut(URL_REQUEST);
                    put.setHeader("content-type", "application/json");
                    //put.setHeader("Authorization",Token);
                    StringEntity entity = new StringEntity(PARAMETROS,"UTF-8");
                    put.setEntity(entity);
                    HttpResponse resp = httpClient.execute(put);
                    String code = "{ \"code\":\""+resp.getStatusLine().getStatusCode()+"\"}";

                    return code;
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
            case "DELETE":
                try {
                    HttpDelete del = new HttpDelete(URL_REQUEST);
                    del.setHeader("content-type", "application/json");
                    //del.setHeader("Authorization",Token);
                    HttpResponse resp = httpClient.execute(del);
                    String code = "{ \"code\":\""+resp.getStatusLine().getStatusCode()+"\"}";

                    return code;

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
