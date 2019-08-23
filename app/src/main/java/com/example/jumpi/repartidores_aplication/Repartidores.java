package com.example.jumpi.repartidores_aplication;

public class Repartidores {

    private String Nombre_Apellido_Repartidor;
    private int ImgRepartidor;
    private int ID_repartidor;






    public Repartidores(String nombre_apellido_repartidor, int img_repartidores, int id_repartidor) {
        this.Nombre_Apellido_Repartidor = nombre_apellido_repartidor;
        this.ImgRepartidor = img_repartidores;
        this.ID_repartidor = id_repartidor;
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





    public static Repartidores[] ITEMS = {

            new Repartidores("Samuel Arévalo", R.mipmap.belasteguin, 01),
            new Repartidores("Marcelo Cañete", R.mipmap.messi,02),
            new Repartidores("Jonathan Medina", R.mipmap.lebron,03),
            new Repartidores("Carlos Trnka", R.mipmap.federer,04),

    };









    public static Repartidores getItem(int id) {

        for (Repartidores item : ITEMS) {

            if (item.getId() == id) {

                return item;
            }
        }
        return null;


    }












}/**FIN DE LA CLASE Repartidores*******/
