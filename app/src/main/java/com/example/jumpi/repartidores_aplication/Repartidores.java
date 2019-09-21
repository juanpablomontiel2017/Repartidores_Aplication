package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Repartidores {

    private String Nombre_Apellido_Repartidor;
    private int ImgRepartidor;
    private int ID_repartidor;
    private int dniRepartidor;






    public Repartidores(String nombre_apellido_repartidor, int img_repartidores, int id_repartidor, int dniRepartidor) {
        this.Nombre_Apellido_Repartidor = nombre_apellido_repartidor;
        this.ImgRepartidor = img_repartidores;
        this.ID_repartidor = id_repartidor;
        this.dniRepartidor = dniRepartidor;
    }

    public void GuardarRepartidoresEnUnSharedPreferences(Context context, int indice){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Datos_Repartidores", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putInt("IdPersona_"+indice,this.getIdRepartidor());
        editor.commit();


        editor.putInt("dni_"+indice,this.getDniRepartidor());
        editor.commit();

        editor.putInt("imgRepartidor_"+indice,this.getImagenRepartidor());
        editor.commit();


        editor.putString("nombreApellido_"+indice,this.getNombreApellido());
        editor.commit();


        ITEMS.add(this);





    }/********************* FIN DE LA FUNCIÓN GuardarUsuarioEnUnSharedPreferences()***********************/


    public static void borrarRepartidoresDelSharedPreferences(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Datos_Repartidores", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        String dimension = sharedPreferences.getString("dimensionRepartidores","");

        if (dimension!=""){

            for (int i = 0 ; i < Integer.valueOf(dimension); i++){

                editor.remove("IdPersona_"+i).commit();
                editor.remove("dni_"+i).commit();
                editor.remove("imgRepartidor_"+i).commit();
                editor.remove("nombreApellido_"+i).commit();
            }

        }





        editor.remove("dimensionRepartidores").commit();
        ITEMS.removeAll(ITEMS);
    }






    public String getNombreApellido() {


        return Nombre_Apellido_Repartidor;


    }


    public int getImagenRepartidor() {

        return ImgRepartidor;

    }



    public int getIdRepartidor() {

        return ID_repartidor;

    }


    public int getId() {

        return Nombre_Apellido_Repartidor.hashCode();

    }




/**
    public static Repartidores[] ITEMS = {

            new Repartidores("Samuel Arévalo", R.mipmap.belasteguin, 3, 27353531),
            new Repartidores("Marcelo Cañete", R.mipmap.messi,4, 39656655),
            new Repartidores("Jonathan Medina", R.mipmap.lebron,5,18040323),
            new Repartidores("Carlos Trnka", R.mipmap.federer,6,23762400),

    };

*/

    public static ArrayList<Repartidores> ITEMS = new ArrayList<Repartidores>();





    public static Repartidores getItem(int id) {

        for (Repartidores item : ITEMS) {

            if (item.getId() == id) {

                return item;
            }
        }
        return null;


    }

    public int getDniRepartidor() {
        return dniRepartidor;
    }

    public void setDniRepartidor(int dniRepartidor) {
        this.dniRepartidor = dniRepartidor;
    }












}/**FIN DE LA CLASE Repartidores*******/
