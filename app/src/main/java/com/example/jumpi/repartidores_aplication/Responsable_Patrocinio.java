package com.example.jumpi.repartidores_aplication;

public class Responsable_Patrocinio {

    private String nombre;
    private String apellido;

    public Responsable_Patrocinio(String name, String apellido){
        this.nombre = name;
        this.apellido = apellido;
    }

    public String getName(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }
}