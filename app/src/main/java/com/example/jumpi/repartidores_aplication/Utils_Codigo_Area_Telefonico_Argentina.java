package com.example.jumpi.repartidores_aplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils_Codigo_Area_Telefonico_Argentina {



    public static boolean Prueba(Context context, String codigo_area) {



    boolean flag = false;


        InputStream archivo = context.getResources().openRawResource(R.raw.codigos_de_area_telefonicos_de_argentina);

        BufferedReader buffer = new BufferedReader(new InputStreamReader(archivo));


        try {


            String line;


            while ((line = buffer.readLine()) != null) {

                  /*Llamada a la función*/

                if(line.equals(codigo_area)){

                    return true;
                }


            }

            buffer.close() ;

        }   catch (IOException e) {

            e.printStackTrace();

        }


        return flag;


    }/************ FIN DE LA FUNCIÓN RecorrerArchivoDeCodigoArea() *******************/










}
