package com.example.jumpi.repartidores_aplication;

import org.json.JSONException;

public interface interfaceRequest {
        void ResultJson(String result,int consulta) throws JSONException;
        void ResultError(String error);


}
