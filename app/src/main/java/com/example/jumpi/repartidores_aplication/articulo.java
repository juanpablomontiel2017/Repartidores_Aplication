package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class articulo {



    private int idArticulo;
    private String  nombreArticulo;
    private float precio;


    public articulo(){

    }

    public articulo(int idArticulo, String nombreArticulo, float precio){
        this.setIdArticulo(idArticulo);
        this.setNombreArticulo(nombreArticulo);
        this.setPrecio(precio);


    }


    public void guardarArticulosEnUnSharedPreferences(Context context, int indice){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Datos_Articulos", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("idArticulo_"+indice,Integer.toString(getIdArticulo()));
        editor.commit();


        editor.putString("nombreArticulo_"+indice,this.getNombreArticulo());
        editor.commit();

        editor.putString("precioArticulo_"+indice,Float.toString(getPrecio()));
        editor.commit();








    }/********************* FIN DE LA FUNCIÓN GuardarUsuarioEnUnSharedPreferences()***********************/















    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float Precio) {
        precio = precio;
    }
}
