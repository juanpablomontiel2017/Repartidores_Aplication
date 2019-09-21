package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class articulo {



    private String idArticulo;
    private String  nombreArticulo;
    private String precio;


    public articulo(){

    }

    public articulo(String idArticulo, String nombreArticulo, String precio){
        this.setIdArticulo(idArticulo);
        this.setNombreArticulo(nombreArticulo);
        this.setPrecio(precio);


    }


    public void guardarArticulosEnUnSharedPreferences(Context context, int indice){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Datos_Articulos", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("idArticulo_"+indice,getIdArticulo());
        editor.commit();


        editor.putString("nombreArticulo_"+indice,this.getNombreArticulo());
        editor.commit();

        editor.putString("precioArticulo_"+indice, this.getPrecio());
        editor.commit();










    }
    public  static void borrarArticulosDelSharedPreferences(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Datos_Articulos", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        String dimension = sharedPreferences.getString("dimensionArticulos","");

        if (dimension!=""){

            for (int i = 0 ; i < Integer.valueOf(dimension); i++){

                editor.remove("nombreArticulo_"+i).commit();
                editor.remove("idArticulo_"+i).commit();
                editor.remove("precioArticulo_"+i).commit();
            }

        }

        editor.remove("dimensionArticulos").commit();
    }



    public articulo obtenerArticuloPorIndice(Context context, int índice){

        SharedPreferences preferences = context.getSharedPreferences("Datos_Articulos", MODE_PRIVATE);

        if (preferences.getString("dimensionArticulos","") != ""){

            this.setIdArticulo(preferences.getString("idArticulo_"+índice,""));
            this.setNombreArticulo(preferences.getString("nombreArticulo_"+índice,""));
            this.setPrecio(preferences.getString("precioArticulo_"+índice,""));
            return this;

        }else{
            return null;

        }




    }









    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String  Precio) {
        precio = Precio;
    }
}
