package com.example.jumpi.repartidores_aplication;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class CustomRequest extends Request<JSONObject> {

    private Listener<JSONObject> listener;
    private Map<String, String> params;

    public CustomRequest(String url, Map<String, String> params,
                         Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }

    public CustomRequest(int method, String url, Map<String, String> params,
                         Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    };

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }

// código para ejecutar en tu activity

    /**           String url = "http://192.168.1.13:4000/av/modelo/cargaDescarga.php";


     Map<String, String> parametro;

     parametro = new HashMap<>();
     parametro.put("idSupervisor", idSupervisor);
     parametro.put("dniSupervisor", dniSupervisor);
     parametro.put("fecha",  UtilidadFecha.getFecha("yyyy/MM/dd"));
     parametro.put("idRepartidor", idRepartidor);
     parametro.put("dniRepartidor", dniRepartidor);
     parametro.put("plata", plataCarga);
     parametro.put("carga", carga);
     parametro.put("descarga", descarga);
     parametro.put("idArticulo", idArticulo);


     Response.Listener<JSONObject> RequestSuccessListener = new Response.Listener<JSONObject>() {
    @Override
    public void onResponse(JSONObject response) {

    Log.d("TFSB", "onResponse del server");


    }
    };
     Response.ErrorListener errorListener = new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
    Log.d("TFSB", "onErrorResponse del server");
    Log.d("TFSB", "                            ");
    Log.d("TFSB", error.toString());


    }
    };
     RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
     CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, parametro, RequestSuccessListener, errorListener);

     requestQueue.add(jsObjRequest);


     */
}
